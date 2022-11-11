package com.niit.AdminService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Menu;
import com.niit.AdminService.domain.Restaurant;
import com.niit.AdminService.domain.User;
import com.niit.AdminService.service.AdminService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AdminService adminService;

    private Admin admin1, admin2;
    private User user1, user2;

    private Restaurant restaurant1, restaurant2;

    private Menu menu1,menu2;

    List<Menu> menuList;

    List<Restaurant> favorites;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setup(){
        user1 = new User("abhi@gmail.com","abhil123","AbhiLash","7318656296",null);
        user2 = new User("swapnil2022@gmail.com","swapnil1","Swapnil","8373861031",null);
        menu1 = new Menu("Chicken Biriyani",90.0);
        menu2 = new Menu("Butter Nan",70.0);
        menuList = Arrays.asList(menu1,menu2);
        restaurant1 = new Restaurant(1001,"abhi@gmail.com","Ranjit Hotel","Siliguri",null,menuList);
        restaurant2 = new Restaurant(1002,"swapnil2022@gmail.com","Zaika Biriyani","Kolkata",null,menuList);
        favorites = Arrays.asList(restaurant1,restaurant2);
        admin1 = new Admin("abhi@gmail.com","abhil123","AbhiLash","7318656296",null);
        admin2 = new Admin("swapnil2022@gmail.com","swapnil1","Swapnil","8373861031",null);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @AfterEach
    public void tearDown(){
        admin1 = null;
        admin2 = null;
    }

    @Test
    public void toRegisterAdminUserSuccess()throws Exception{
        when(adminService.registerUser(any())).thenReturn(admin1);
        mockMvc.perform(post("/admin-service/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(admin1))).andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(adminService,times(1)).registerUser(any());
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
