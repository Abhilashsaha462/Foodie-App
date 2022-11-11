package com.niit.OrderServices.service;

import UserDefinedException.OrderAlreadyExistsException;
import UserDefinedException.OrderNotFoundException;
import com.niit.OrderServices.Controller.OrderController;
import com.niit.OrderServices.Model.*;
import com.niit.OrderServices.Repository.OrderRepository;
import com.niit.OrderServices.Services.OrderServiceImpl;
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

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order1, order2;

    private User user1, user2;

    private Bill bill1, bill2;

    private Address address1, address2;

    private Menu menu1,menu2,menu3,menu4;

    List<Menu> menuList;

    List<Menu> menuList1;

    List<Address> addressList;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
//        user1 = new User("1000","Abhi","8373861031",new Address(1000,"MG Road","Bangalore",734006L));
//        user2 = new User("1001","Swapnil","7318656296",new Address(1001,"Mahatma Road","Thane",730104L));
        menu1 = new Menu("Chicken Lollipop",50.0);
        menu2 = new Menu("Uttapam",100.0);
        menuList = Arrays.asList(menu1,menu2);
        menu3 = new Menu("Chicken Biriyani",100.0);
        menu4 = new Menu("Egg Biriyani",100.0);
        menuList1 = Arrays.asList(menu3,menu4);
//        bill1 = new Bill("1000",menuList,150.0);
//        bill2 = new Bill("1001",menuList1,200.0);
        address1 = new Address(1030,"Highway Road","Hyderabad",734014L);
        address2 = new Address(1031,"MG Road","Chennai",734006L);
        addressList = Arrays.asList(address1,address2);
        order1 = new Order(1050,new User("sid@gmail.com","Siddharth","8373861031", addressList),new Bill(1000,menuList,150.0));
        order2 = new Order(1051,new User("rushi@gmail.com","Rushikesh","7318656296", addressList),new Bill(1001,menuList1,200.0));
    }

    @AfterEach
    public void tearDown() {
        order1 = null;
        order2 = null;
    }

    @Test
    public void givenOrderToSaveReturnSavedOrderSuccess() throws OrderAlreadyExistsException {
        when(orderRepository.findById(order1.getOrderId())).thenReturn(Optional.ofNullable(null));
        when(orderRepository.save(any())).thenReturn(order1);
        assertEquals(order1, orderService.generateOrder(order1));
        verify(orderRepository, times(1)).save(any());
        verify(orderRepository, times(1)).findById(any());
    }

//    @Test
//    public void givenOrderToDelete() throws OrderNotFoundException {
//        when(orderRepository.findById(order2.getOrderId())).thenReturn(Optional.of(order2));
//
//    }

}
