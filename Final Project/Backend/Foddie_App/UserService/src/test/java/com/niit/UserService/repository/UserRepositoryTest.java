package com.niit.UserService.repository;


import com.niit.UserService.Model.Address;
import com.niit.UserService.Model.Menu;
import com.niit.UserService.Model.Restaurant;
import com.niit.UserService.Model.User;
import com.niit.UserService.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user, user2;

    private Address address1, address2;

    private Restaurant restaurant1, restaurant2;


    private Menu menu1,menu2;

//    List<Menu> menuList;

    List<Address> address;
    List<Restaurant> favorites;

    @BeforeEach
    public void setup(){
        user = new User("abhilashsaha1@gmail.com","abhil123","AbhiLash","7318656296",address,favorites);
        user2 = new User("swapnil2022@gmail.com","swapnil1","Swapnil","8373861031",address,favorites);
        address1 = new Address(1017,"MG Road","Siliguri",734006L);
        address2 = new Address(1020,"College Street Road","Kolkata",734014L);
        menu1 = new Menu("Chicken Biriyani",90.0);
        menu2 = new Menu("Butter Nan",70.0);
//        menuList = Arrays.asList(menu1,menu2);
        restaurant1 = new Restaurant(1001,"Ranjit Hotel","Siliguri");
        restaurant2 = new Restaurant(1002,"Zaika Biriyani","Kolkata");
        favorites = Arrays.asList(restaurant1,restaurant2);
    }

    @AfterEach
    public void tearDown(){
        user = null;
        user2 = null;
    }

    @Test
    public void givenUserToSaveShouldSave()throws Exception{
        userRepository.insert(user);
        User user1 = userRepository.findById(user.getEmail()).get();
        assertNotNull(user);
        assertEquals(user.getEmail(),user1.getEmail());
    }

    @Test
    public void giveUserIdtoDeleteShouldDeleteUser(){
        userRepository.insert(user2);
        User user1 = userRepository.findById(user2.getEmail()).get();
        userRepository.delete(user1);
        assertEquals(Optional.empty(),userRepository.findById(user2.getEmail()));
    }

}
