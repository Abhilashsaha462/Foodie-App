package com.niit.UserService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.UserService.Controller.UserController;
import com.niit.UserService.Model.Address;
import com.niit.UserService.Model.Menu;
import com.niit.UserService.Model.Restaurant;
import com.niit.UserService.Model.User;
import com.niit.UserService.Services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    private User user1, user2;

    private Address address1, address2;

    private Restaurant restaurant1, restaurant2;


    private Menu menu1,menu2;

//    List<Menu> menuList;

    List<Address> address;
    List<Restaurant> favorites;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup(){
        user1 = new User("abhi@gmail.com","abhil123","AbhiLash","7318656296",address,favorites);
        user2 = new User("swapnil2022@gmail.com","swapnil1","Swapnil","8373861031",address,favorites);
        address1 = new Address(1017,"MG Road","Siliguri",734006L);
        address2 = new Address(1020,"College Street Road","Kolkata",734014L);
        menu1 = new Menu("Chicken Biriyani",90.0);
        menu2 = new Menu("Butter Nan",70.0);
//        menuList = Arrays.asList(menu1,menu2);
        restaurant1 = new Restaurant(1001,"Ranjit Hotel","Siliguri");
        restaurant2 = new Restaurant(1002,"Zaika Biriyani","Kolkata");
        favorites = Arrays.asList(restaurant1,restaurant2);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    public void tearDown(){
        user1 = null;
        user2 = null;
    }

    @Test
    public void toRegisterUserSuccess()throws Exception{
        when(userService.register(any())).thenReturn(user1);
        mockMvc.perform(post("/user-service/user/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user1))).andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).register(any());
    }

    private String jsonToString(final Object object)throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(object);
            System.out.println("JSON Content has been Posted : \n"+jsonContent);
            result = jsonContent;
        }catch (JsonProcessingException e){
            result = "JSON Processing Error...!!!";
        }
        return result;
    }

}
