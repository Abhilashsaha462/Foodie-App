package com.niit.UserService.Proxy;

import com.niit.UserService.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "AUTH-SERVICE")
public interface AuthProxy {
    @RequestMapping(value = "/api/v3/register", method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody User user);
    @RequestMapping(value = "/api/v3/update", method = RequestMethod.PUT)
    public ResponseEntity updateUserDetailsFunction(@RequestBody User user);
}
