package com.niit.AdminService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Admin Already Exists")
public class AdminAlreadyExistsException extends Exception {

}
