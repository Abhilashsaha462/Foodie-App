package com.niit.backend.Restuarant.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "This Owner does Exists...!!!")
public class OwnerNotFoundException extends Exception{
}
