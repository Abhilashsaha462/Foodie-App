package com.niit.AdminService.repository;

import com.niit.AdminService.controller.AdminController;
import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Menu;
import com.niit.AdminService.domain.Restaurant;
import com.niit.AdminService.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

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
        admin1 = new Admin("abhilash@gmail.com","abhil123","AbhiLash","7318656296",null);
        admin2 = new Admin("swapnil2022@gmail.com","swapnil1","Swapnil","8373861031",null);

    }

    @AfterEach
    public void tearDown(){
        admin1 = null;
        admin2 = null;
    }

    @Test
    public void givenAdminToSaveShouldSave()throws Exception{
        adminRepository.insert(admin1);
        Admin admin = adminRepository.findById(admin1.getEmail()).get();
        assertNotNull(admin);
        assertEquals(admin1.getEmail(),admin.getEmail());
    }

    @Test
    public void giveAdminIdtoDeleteShouldDeleteAdmin(){
        adminRepository.insert(admin2);
        Admin admin = adminRepository.findById(admin2.getEmail()).get();
        adminRepository.delete(admin);
        assertEquals(Optional.empty(),adminRepository.findById(admin2.getEmail()));
    }

}
