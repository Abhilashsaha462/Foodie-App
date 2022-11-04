package com.niit.UserService.Services;

import UserDefinedException.RestaurantNotFoundException;
import UserDefinedException.UserAlreadyExistsException;
import UserDefinedException.UserNotFoundException;
import com.niit.UserService.Model.Restaurant;
import com.niit.UserService.Model.User;
import com.niit.UserService.Proxy.AuthProxy;
import com.niit.UserService.Repository.RestaurantRepository;
import com.niit.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private AuthProxy authProxy;
    private RestaurantRepository restaurantRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository, AuthProxy authProxy){
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.authProxy = authProxy;
    }

    @Override
    public User register(User user) throws UserAlreadyExistsException {                                         //Working
        if (userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        ResponseEntity<?> response = authProxy.saveUser(user);
        if (response.getStatusCodeValue()==201)
        {
            return userRepository.save(user);
        }
        else {
        return null;
      }
    }

    @Override
    public User addToFavorite(Restaurant restaurant, String email) throws UserNotFoundException {               //Working
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findByEmail(email);
        if(user.getFavorites() == null){
            user.setFavorites(Arrays.asList(restaurant));
        }
        else{
            List<Restaurant> tempList = user.getFavorites();
            tempList.add(restaurant);
            user.setFavorites(tempList);
        }

        return userRepository.save(user);
    }

    @Override
    public User removeFromFavorite(int id, String email) throws RestaurantNotFoundException, UserNotFoundException{                // Working But Not Desirable Output
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findByEmail(email);
        if(user.getFavorites() == null){
           throw new RestaurantNotFoundException();
        }
        else{
            List<Restaurant> tempList = user.getFavorites();
            Optional<Restaurant> optional = tempList.stream().filter(x->x.getRestId() == id).findFirst();
            if(optional.isEmpty()){
                throw new RestaurantNotFoundException();
            }
            Restaurant restaurant = tempList.stream().filter(p->p.getRestId() == id).findFirst().orElse(null);
            tempList.remove(restaurant);
            user.setFavorites(tempList);
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUserDetails(User user) throws UserNotFoundException {                                             // Working
        if(userRepository.findById(user.getEmail()).isEmpty()){
            throw new UserNotFoundException();
        }
        ResponseEntity<?> response = authProxy.updateUserDetailsFunction(user);
        if (response.getStatusCodeValue()==201){
            return userRepository.save(user);
        }else {
            return null;
        }
    }

    @Override
    public User getUser(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user.getEmail().equals(email)){
            return user;
        }else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<Restaurant> getFavorite(String email) throws UserNotFoundException {                                    // Working
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findByEmail(email).getFavorites();
    }
}
