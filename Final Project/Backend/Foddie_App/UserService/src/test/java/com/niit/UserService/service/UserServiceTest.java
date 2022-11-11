package com.niit.UserService.service;

import UserDefinedException.UserAlreadyExistsException;
import com.niit.UserService.Model.Address;
import com.niit.UserService.Model.Menu;
import com.niit.UserService.Model.Restaurant;
import com.niit.UserService.Model.User;
import com.niit.UserService.Repository.UserRepository;
import com.niit.UserService.Services.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1, user2;

    private Address address1, address2;

    private Restaurant restaurant1, restaurant2;


    private Menu menu1,menu2;

//    List<Menu> menuList;

    List<Address> address;
    List<Restaurant> favorites;

    @BeforeEach
    public void setup(){
        user1 = new User("abhi@gmail.com","abhil123","AbhiLash","7318656296",address,favorites);
        user2 = new User("swapnil2022@gmail.com","swapnil1","Swapnil","8373861031",address,favorites);
        address1 = new Address(1017,"MG Road","Siliguri",734006L);
        address2 = new Address(1020,"College Street Road","Kolkata",734014L);
        address = Arrays.asList(address1,address2);
        menu1 = new Menu("Chicken Biriyani",90.0);
        menu2 = new Menu("Butter Nan",70.0);
//        menuList = Arrays.asList(menu1,menu2);
        restaurant1 = new Restaurant(1001,"Ranjit Hotel","Siliguri");
        restaurant2 = new Restaurant(1002,"Zaika Biriyani","Kolkata");
        favorites = Arrays.asList(restaurant1,restaurant2);
    }

    @AfterEach
    public void tearDown(){
        user1 = null;
        user2 = null;
    }

    @Test
    public void givenUserToSaveReturnSavedUserSuccess() throws UserAlreadyExistsException {
        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(any())).thenReturn(user1);
        assertEquals(user1,userService.register(user1));
        verify(userRepository,times(1)).save(any());
        verify(userRepository,times(1)).findById(any());
    }

//    @Test
//    public void givenUserToDelete() {
//        when(userRepository.findById(user2.getEmail())).thenReturn(Optional.of(user2));
//    }

}
