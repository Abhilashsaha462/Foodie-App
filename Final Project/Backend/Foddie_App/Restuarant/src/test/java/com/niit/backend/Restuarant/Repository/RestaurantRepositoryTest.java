package com.niit.backend.Restuarant.Repository;
import com.niit.backend.Restuarant.Domain.Menu;
import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Service.RestaurantService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class RestaurantRepositoryTest {
    @Autowired
    private RestaurantRepository restaurantRepository;
    private Menu menu;
    private Restaurant restaurant,restaurant2;
    List<Menu> menuList;

    private RestaurantService restaurantService;

    @BeforeEach
    public void setUp(){
        menu = new Menu("Biryani",220.00);
        restaurant = new Restaurant(27,"abc123@gmail.com","Vrundavan Multi Cuisine Restaurant","Kalyan", new byte[]{(byte) 204, 29, (byte) 207, (byte) 217},menuList);
        restaurant2 = new Restaurant(45,"xyz123@gmail.com","Gurudev Multi Cuisine Restaurant","Kalyan", new byte[]{(byte) 204, 29, (byte) 207, (byte) 217},menuList);
    }
    @AfterEach
    public void tearDown(){
        restaurant = null;
        menu = null;
    }
    @Test
    public void givenRestaurantToSaveShouldSave()throws Exception{
        restaurantRepository.insert(restaurant);
        Restaurant restaurant1 = restaurantRepository.findById(restaurant.getRestId()).get();
        assertNotNull(restaurant1);
        assertEquals(restaurant.getRestId(),restaurant1.getRestId());
    }
    @Test
    public void giveRestaurantIdtoDeleteShouldDeleteRestaurant(){
    restaurantRepository.insert(restaurant2);
    Restaurant restaurant1 = restaurantRepository.findById(restaurant2.getRestId()).get();
    restaurantRepository.delete(restaurant1);
    assertEquals(Optional.empty(),restaurantRepository.findById(restaurant2.getRestId()));
    }
}
