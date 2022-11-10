package com.niit.backend.Restuarant.Controller;

import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Exception.RestaurantAlreadyExistException;
import com.niit.backend.Restuarant.Exception.RestaurantNotFoundException;
import com.niit.backend.Restuarant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class RestaurantController {
    private RestaurantService restaurantService;
    private ResponseEntity responseEntity;
    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addNewRestaurant(@RequestBody Restaurant restaurant)throws RestaurantAlreadyExistException {
        try {
            responseEntity = new ResponseEntity(restaurantService.addRestaurant(restaurant), HttpStatus.CREATED);
        }catch (RestaurantAlreadyExistException e){
            throw new RestaurantAlreadyExistException();
        }
        return responseEntity;
    }
    @GetMapping("/{restName}")
    public ResponseEntity<?> findRestaurant(@PathVariable String restName)throws RestaurantNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(restaurantService.findByName(restName),HttpStatus.OK);
        }catch (RestaurantNotFoundException e){
            throw new RestaurantNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllRestaurants(){
        responseEntity = new ResponseEntity(restaurantService.getAllRestaurants(),HttpStatus.OK);
        return responseEntity;
    }
    @PutMapping("/rest/update")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant)throws RestaurantNotFoundException{
        try{
            restaurantService.updateRestaurant(restaurant);
            responseEntity = new ResponseEntity("Updated!", HttpStatus.OK);
        }catch (RestaurantNotFoundException ex){
            throw ex;
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error while Updating! Please Try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
