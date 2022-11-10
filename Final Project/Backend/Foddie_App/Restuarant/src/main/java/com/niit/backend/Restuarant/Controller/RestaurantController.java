package com.niit.backend.Restuarant.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.niit.backend.Restuarant.Domain.RestOwner;
import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Exception.*;
import com.niit.backend.Restuarant.Repository.RestaurantRepository;
import com.niit.backend.Restuarant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    private RestaurantService restaurantService;
    private ResponseEntity responseEntity;
    Gson gson = new Gson();
    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }
    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewRestaurant(@RequestParam("file") MultipartFile file,@RequestParam("details") String details)throws RestaurantAlreadyExistException {
        try {
            Restaurant restaurant = new ObjectMapper().readValue(details,Restaurant.class);
//            Restaurant restaurant = gson.fromJson(details,Restaurant.class);
            responseEntity = new ResponseEntity(restaurantService.addRestaurant(restaurant,file.getBytes()), HttpStatus.CREATED);
        }catch (RestaurantAlreadyExistException e){
            throw new RestaurantAlreadyExistException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }
    @GetMapping(path = "/search/{restName}")
    public ResponseEntity<?> findRestaurant(@PathVariable String restName, Principal principal)throws RestaurantNotFoundException {
        List<Restaurant> myList = restaurantRepository.findByRestNameContaining(restName);
        return responseEntity = new ResponseEntity(myList,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllRestaurants(){
        responseEntity = new ResponseEntity(restaurantService.getAllRestaurants(),HttpStatus.OK);
        return responseEntity;
    }
    @PutMapping(path = "/rest/update")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant)throws RestaurantNotFoundException{
        try{
//            System.out.println("inside");
//            Restaurant restaurant = new ObjectMapper().readValue(details,Restaurant.class);
//            Restaurant restaurant = gson.fromJson(details,Restaurant.class);

            responseEntity = new ResponseEntity(restaurantService.updateRestaurant(restaurant), HttpStatus.CREATED);
        }catch (RestaurantNotFoundException ex){
            throw ex;
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error while Updating! Please Try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
    @GetMapping(path = "/getRest/{data}")
    public ResponseEntity<?> getRestaurants(@PathVariable(value = "data") String restId)throws RestaurantsNotFoundException{
        System.out.println("hello");
        String[] x = restId.split(",");
        int[] y = Arrays.stream(x).mapToInt(Integer::parseInt).toArray();
        List<Restaurant> restaurantList = restaurantService.getRestaurants(y);
        return responseEntity = new ResponseEntity(restaurantList,HttpStatus.OK);
    }
    @GetMapping(path = "/{restName}")
    public ResponseEntity<?> getMyRestaurant(@PathVariable String restName)throws RestaurantNotFoundException{
        System.out.println("Name"+restName);
        try {
          //  Restaurant restaurant = restaurantService.findByName(restName);

            responseEntity = new ResponseEntity<>(restaurantService.findByName(restName.trim()),HttpStatus.OK);
        }catch (RestaurantNotFoundException e){
            throw new RestaurantNotFoundException();
        }
        return responseEntity;
    }
    @DeleteMapping("/deleterestaurant/{restId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable int restId)throws RestaurantNotFoundException{
        try {
            responseEntity = new ResponseEntity(restaurantService.deleteRestaurant(restId),HttpStatus.OK);
        }catch (RestaurantNotFoundException e){
            throw new RestaurantNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error While deleting Restaurant...!!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PostMapping("/ownerRegister")
    public ResponseEntity<?> ownerRegistration(@RequestBody RestOwner restOwner)throws OwnerAlreadyExistException{
        try {
            responseEntity = new ResponseEntity(restaurantService.addOwner(restOwner),HttpStatus.OK);
        }catch (OwnerAlreadyExistException exception){
            throw new OwnerAlreadyExistException();
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error While Registration Owner...",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PostMapping("/ownerLogin")
    public ResponseEntity<?> loginOwner(@RequestBody RestOwner restOwner)throws OwnerNotFoundException{
        try {
            RestOwner restOwner1 = restaurantService.loginOwner(restOwner.getEmail(),restOwner.getPassword());
            if (restOwner1.getEmail().equals(restOwner.getEmail()) && restOwner1.getPassword().equals(restOwner.getPassword())){
                responseEntity = new ResponseEntity(restOwner,HttpStatus.OK);
            }

        }catch (OwnerNotFoundException e){
            throw new OwnerNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity("Login Failed...",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/ownerRest/{email}")
    public ResponseEntity<?> getOwnerRest(@PathVariable String email)throws RestaurantsNotFoundException{
        try {
            responseEntity = new ResponseEntity(restaurantService.getRestByEmail(email),HttpStatus.OK);
        }catch (RestaurantsNotFoundException ex){
            throw new RestaurantsNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity("No Restaurants are available for this Owner...",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }
//    @GetMapping("/{restName}/restaurant")
//    public String showContactDetail(@PathVariable("restName") String restName, Model model, Principal principal) {
//        System.out.println("CID " + restName);
//        Restaurant restaurant = this.restaurantRepository.findByRestName(restName);
//
//        Optional<Restaurant> contactOptional = this.restaurantRepository.findById(restaurant.getRestId());
//        Restaurant contact = contactOptional.get();
//
//        //
//        String userName = principal.getName();
//
//
//            model.addAttribute("contact", contact);
//            model.addAttribute("title", contact.getRestName());
//
//
//        return "/display";
//    }

}
