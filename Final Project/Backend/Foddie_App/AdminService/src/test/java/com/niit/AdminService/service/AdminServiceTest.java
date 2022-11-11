package com.niit.AdminService.service;

import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Menu;
import com.niit.AdminService.domain.Restaurant;
import com.niit.AdminService.domain.User;
import com.niit.AdminService.exception.AdminAlreadyExistsException;
import com.niit.AdminService.exception.AdminNotFoundException;
import com.niit.AdminService.repository.AdminRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    private Admin admin1, admin2;
    private User user1, user2;

    private Restaurant restaurant1, restaurant2;

    private Menu menu1, menu2;

    List<Menu> menuList;

    List<Restaurant> favorites;

    @BeforeEach
    public void setup() {
        user1 = new User("abhi@gmail.com", "abhil123", "AbhiLash", "7318656296",null);
        user2 = new User("swapnil2022@gmail.com", "swapnil1", "Swapnil", "8373861031",null);
        menu1 = new Menu("Chicken Biriyani", 90.0);
        menu2 = new Menu("Butter Nan", 70.0);
        menuList = Arrays.asList(menu1, menu2);
        restaurant1 = new Restaurant(1001,"abhi@gmail.com","Ranjit Hotel", "Siliguri",null, menuList);
        restaurant2 = new Restaurant(1002,"swapnil2022@gmail.com","Zaika Biriyani", "Kolkata",null,menuList);
        favorites = Arrays.asList(restaurant1, restaurant2);
        admin1 = new Admin("abhi@gmail.com", "abhil123", "AbhiLash", "7318656296", null);
        admin2 = new Admin("swapnil2022@gmail.com", "swapnil1", "Swapnil", "8373861031", null);

    }

    @AfterEach
    public void tearDown() {
        admin1 = null;
        admin2 = null;
    }

    @Test
    public void givenAdminToSaveReturnSavedAdminSuccess() throws AdminAlreadyExistsException {
        when(adminRepository.findById(admin1.getEmail())).thenReturn(Optional.ofNullable(null));
        when(adminRepository.save(any())).thenReturn(admin1);
        assertEquals(admin1, adminService.registerUser(admin1));
        verify(adminRepository, times(1)).save(any());
        verify(adminRepository, times(1)).findById(any());
    }

//    @Test
//    public void givenAdminToDelete() throws AdminNotFoundException {
//        when(adminRepository.findById(admin2.getEmail())).thenReturn(Optional.of(admin2));
//
//    }

}

