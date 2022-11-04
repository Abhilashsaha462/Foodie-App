package com.niit.AdminService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Restaurant Already Exists")
public class RestaurantAlreadyExistsException extends Exception {

}
