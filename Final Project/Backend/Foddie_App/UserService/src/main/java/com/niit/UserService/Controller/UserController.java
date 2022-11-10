package com.niit.UserService.Controller;

import UserDefinedException.RestaurantNotFoundException;
import UserDefinedException.UserAlreadyExistsException;
import UserDefinedException.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.UserService.Model.EmailDetails;
import com.niit.UserService.Model.Restaurant;
import com.niit.UserService.Model.User;
import com.niit.UserService.Services.UserService;
import com.niit.UserService.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user-service/")
public class UserController {

    private ResponseEntity responseEntity;
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //  Register a New User
    @PostMapping("user/register")
    public ResponseEntity<?> registerUserFunction(@RequestBody User user) throws UserAlreadyExistsException {
        try{
            userService.register(user);
            responseEntity = new ResponseEntity(user, HttpStatus.CREATED);
        }catch (UserAlreadyExistsException ex1){
            throw ex1;
        }catch (Exception ex){
            responseEntity = new ResponseEntity("Error while register new User", HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }

        return responseEntity;
    }

    //  add Restaurant to favorites
    @PutMapping("user/favorite/add/{email}")
    public ResponseEntity<?> addToFavoriteFunction(@RequestBody Restaurant restaurant, @PathVariable String email) throws UserNotFoundException{
        try{
            //Restaurant restaurant = new ObjectMapper().readValue(details,Restaurant.class);
            User user = userService.addToFavorite(restaurant, email);
            responseEntity = new ResponseEntity(user, HttpStatus.CREATED);
        }catch (UserNotFoundException ex1){
            throw ex1;
        }catch (Exception ex){
            responseEntity = new ResponseEntity("Error while Adding Details to favorite", HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }

        return responseEntity;
    }

    //  remove restaurant from the favorite
    @DeleteMapping("user/favorite/delete/{email}/{id}")
    public ResponseEntity<?> removeFromFavoriteFunction(@PathVariable String email, @PathVariable int id) throws RestaurantNotFoundException, UserNotFoundException{
        try{
            User user = userService.removeFromFavorite(id, email);
            responseEntity = new ResponseEntity(user, HttpStatus.ACCEPTED);
        }catch (RestaurantNotFoundException ex1){
            throw ex1;
        }catch (UserNotFoundException ex2){
            throw ex2;
        }catch (Exception ex){
            responseEntity = new ResponseEntity("Error while Deleting Details from favorite", HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }

        return responseEntity;
    }

    //  Update All Details of User
    @PutMapping("user/update")
    public ResponseEntity<?> updateUserDetailsFunction(@RequestBody User user) throws UserNotFoundException{
        try{
            userService.updateUserDetails(user);
            responseEntity = new ResponseEntity(user, HttpStatus.CREATED);
        }catch (UserNotFoundException ex1){
            throw ex1;
        }catch (Exception ex){
            responseEntity = new ResponseEntity("Error while Updating", HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }

        return responseEntity;
    }

    //  get Favorite List
    @GetMapping("user/favorite/get/{email}")
    public ResponseEntity<?> getFavoriteFunction(@PathVariable String email) throws UserNotFoundException{
        try{
            List<Restaurant> favorites = userService.getFavorite(email);
            responseEntity = new ResponseEntity(favorites, HttpStatus.OK);
        }catch (UserNotFoundException ex1){
            throw ex1;
        }catch (Exception ex){
            responseEntity = new ResponseEntity("Error while getting favorite List", HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }

        return responseEntity;
    }
    @GetMapping("get/{email}")
    public ResponseEntity<?> showUser(@PathVariable String email)throws UserNotFoundException{
        try {
            User user = userService.getUser(email);
            responseEntity = new ResponseEntity(user,HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw e;
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error While Getting the User...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        responseEntity = new ResponseEntity(userService.getAllUsers(),HttpStatus.OK);
        return responseEntity;
    }
    @DeleteMapping("remove/{email}")
    public ResponseEntity<?> deteteUser(@PathVariable String email)throws UserNotFoundException{
        try {
            responseEntity = new ResponseEntity(userService.deleteUser(email),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity("Error While Removing User...!!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
