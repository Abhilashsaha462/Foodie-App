package com.niit.AdminService.service;

import com.niit.AdminService.Producer.Producer;
import com.niit.AdminService.Proxy.UserProxy;
import com.niit.AdminService.Rabbitmq.Domain.RestaurantDTO;
import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Restaurant;
import com.niit.AdminService.domain.User;
import com.niit.AdminService.exception.AdminAlreadyExistsException;
import com.niit.AdminService.exception.AdminNotFoundException;
import com.niit.AdminService.exception.RestaurantAlreadyExistsException;
import com.niit.AdminService.exception.UserAlreadyExistsException;
import com.niit.AdminService.repository.AdminRepository;
import com.niit.AdminService.repository.RestaurantRepository;
import com.niit.AdminService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {


    private RestaurantRepository adminRestaurantRepo;
    private AdminRepository adminRepo;

    private UserRepository userRepo;
    @Autowired
    private UserProxy userProxy;
    @Autowired
    private Producer producer;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepo, RestaurantRepository adminRestaurantRepo, UserRepository userRepo) {
        this.adminRepo = adminRepo;
        this.adminRestaurantRepo = adminRestaurantRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Admin registerUser(Admin admin) throws AdminAlreadyExistsException {
        if(adminRepo.findById(admin.getEmail()).isPresent()){
            throw new AdminAlreadyExistsException();
        }

        return adminRepo.save(admin);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant,byte[] bytes) throws RestaurantAlreadyExistsException {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestId(restaurant.getRestId());
        restaurantDTO.setRestName(restaurant.getRestName());
        restaurantDTO.setCity(restaurant.getCity());
        restaurantDTO.setMenuList(restaurant.getMenuList());
        restaurantDTO.setEmail(restaurant.getEmail());
        restaurantDTO.setUrl(bytes);
        if(adminRestaurantRepo.findById(restaurant.getRestId()).isPresent()){
            throw new RestaurantAlreadyExistsException();
        }else {
            restaurant.setUrl(bytes);
             adminRestaurantRepo.save(restaurant);
            System.out.println("Data Going to Save into RabbitMQ Server...");
            producer.sendMessageToRabbitmq(restaurantDTO);
        }
        return restaurant;
    }

    @Override
    public Boolean removeRestaurant(int restId) {
        adminRestaurantRepo.deleteById(restId);
        return true;
    }

    @Override
    public Admin updateRestaurant(String restaurantId,Restaurant restaurant){
        Admin admin = adminRepo.findById(restaurantId).get();
//        admin.getFavorite().removeIf(p -> p.getRestId()==restaurant.getRestId());
//        if(admin.getFavorite() == null)
//        {
//            admin.setFavorite(Arrays.asList(restaurant));
//        }
//        else{
//            admin.getFavorite().add(restaurant);
//        }
        return adminRepo.save(admin);
    }

    @Override
    public Admin findByEmailAndPasswrd(String email,String password) throws AdminNotFoundException {
        Admin admin = adminRepo.findByEmailAndPassword(email, password);
        System.out.println("service"+admin);
        if (admin == null)
        {
            throw new AdminNotFoundException();
        }
        return admin;

    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        userProxy.registerUserFunction(user);
        if(userRepo.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        return userRepo.save(user);
    }

}

