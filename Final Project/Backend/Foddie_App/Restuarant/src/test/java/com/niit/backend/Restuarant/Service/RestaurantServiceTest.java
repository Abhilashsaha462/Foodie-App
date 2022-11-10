package com.niit.backend.Restuarant.Service;
import com.niit.backend.Restuarant.Domain.Menu;
import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Exception.RestaurantAlreadyExistException;
import com.niit.backend.Restuarant.Exception.RestaurantNotFoundException;
import com.niit.backend.Restuarant.Repository.RestaurantRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;
    @InjectMocks
    private RestaurantServiceImpl restaurantService;
    private Restaurant restaurant1,restaurant2;
    List<Restaurant> restaurantList;
    Menu menu1,menu2;
    List<Menu> menuList;
    byte[] bytes={(byte) 204, 29, (byte) 207, (byte) 217};
    @BeforeEach
    public void setUp(){
        menu1 = new Menu("Mutter Paneer",200.00);
        menu2 = new Menu("Aaloo Mutter",160.00);
        menuList = Arrays.asList(menu1,menu2);
        restaurant1 = new Restaurant(101,"abc123@gmail.com","Vrundavan Multi Cuisine Restaurant","Kalyan",bytes,menuList);
        restaurant2 = new Restaurant(102,"xyz123@gmail.com","Gurudev Snacks Corner","Thane",bytes,menuList);
    }
    @AfterEach
    public void tearDown(){
        restaurant1 = null;
        restaurant2 = null;
    }
    @Test
    public void givenRestaurantToSaveReturnSavedRestaurantSuccess()throws RestaurantAlreadyExistException{
        when(restaurantRepository.findById(restaurant1.getRestId())).thenReturn(Optional.ofNullable(null));
        when(restaurantRepository.save(any())).thenReturn(restaurant1);
        assertEquals(restaurant1,restaurantService.addRestaurant(restaurant1,bytes));
        verify(restaurantRepository,times(1)).save(any());
        verify(restaurantRepository,times(1)).findById(any());
    }
    @Test
    public void givenRestaurantToDelete()throws RestaurantNotFoundException {
        when(restaurantRepository.findById(restaurant2.getRestId())).thenReturn(Optional.of(restaurant2));

    }
}
