//package com.niit.authenticationservice.AuthenticationService.controller;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.niit.authenticationservice.AuthenticationService.domain.User;
//import com.niit.authenticationservice.AuthenticationService.service.UserService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//
//public class AuthenticationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    private User user1 , user2 ;
//
//    @InjectMocks
//    private UserController userController;
//
//    @BeforeEach
//    public void setUp() {
//        user1 = new User("seema123@gmail.com","Seema123");
//        user2 = new User("manas123@gmail.com","Manas123");
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        user1 = null;
//        user2 = null;
//    }
//
//    @Test
//    public void saveUserToAuthenticate() throws Exception {
//        when(userService.saveUser(any())).thenReturn(user1);
////        when(userService.saveUser(any())).thenReturn(user2);
//        mockMvc.perform(post("/api/v3/register")
//                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user1)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(userService,times(1)).saveUser(any());
//    }
//
//    private static String jsonToString(final Object ob) throws JsonProcessingException
//    {
//        String result;
//        try
//        {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(ob);
//            System.out.println("Json Content that has been posted:\n"+jsonContent);
//            result = jsonContent;
//        }
//        catch (JsonProcessingException e)
//        {
//            result = "JSON processing error";
//        }
//        return result;
//    }
//
//}