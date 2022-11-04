package com.niit.backend.Restuarant.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "These Restaurants does not Exists...!!!")
public class RestaurantsNotFoundException extends Exception {
}
