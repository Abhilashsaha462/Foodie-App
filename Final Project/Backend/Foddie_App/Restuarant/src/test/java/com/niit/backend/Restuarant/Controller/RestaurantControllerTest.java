package com.niit.backend.Restuarant.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.backend.Restuarant.Domain.Menu;
import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Service.RestaurantService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private RestaurantService restaurantService;
    private Restaurant restaurant1,restaurant2;
    Menu menu1,menu2;
    List<Menu> menuList;
    List<Restaurant> restaurantList;
    byte[] bytes={(byte) 204, 29, (byte) 207, (byte) 217};
    @InjectMocks
    private RestaurantController restaurantControllerTest;
    @BeforeEach
    public void setup(){
        menu1 = new Menu("Mutter Paneer",200.00);
        menu2 = new Menu("Aaloo Mutter",160.00);
        menuList = Arrays.asList(menu1,menu2);
        restaurant1 = new Restaurant(101,"abc12@gmail.com","Vrundavan Multi Cuisine Restaurant","Kalyan", new byte[]{(byte) 204, 29, (byte) 207, (byte) 217},menuList);
        restaurant2 = new Restaurant(102,"xyz12@gmai.com","Gurudev Snacks Corner","Thane",new byte[]{ (byte) 204, 29, (byte) 207, (byte) 217 },menuList);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantControllerTest).build();
    }
    @AfterEach
    public void tearDown(){
        restaurant1 = null;
        restaurant2 = null;
    }
//    @Test
//    public void givenRestaurantToSaveRestaurantSuccess()throws Exception{
//        when(restaurantService.addRestaurant(any(),any())).thenReturn(restaurant1);
//        mockMvc.perform(post("/api/v2/add").contentType(MediaType.APPLICATION_JSON).content(jsonToString(restaurant1,bytes))).andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(restaurantService,times(1)).addRestaurant(any(),any());
//    }
//    private String jsonToString(final Object object, byte[] bytes)throws JsonProcessingException{
//        String result;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(object);
//            System.out.println("JSON Content has been Posted : \n"+jsonContent);
//            result = jsonContent;
//        }catch (JsonProcessingException e){
//            result = "JSON Processing Error...!!!";
//        }
//        return result;
//    }
    @Test
    public void trialMethodTest() throws Exception {
        when(restaurantService.findByName("Vrundavan Multi Cuisine Restaurant")).thenReturn(restaurant1);
        mockMvc.perform(get("/api/v2/Vrundavan Multi Cuisine Restaurant")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllRestaurantList()throws Exception{
        when(restaurantService.getAllRestaurants()).thenReturn(restaurantList);
        mockMvc.perform(get("/api/v2/all")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

}
