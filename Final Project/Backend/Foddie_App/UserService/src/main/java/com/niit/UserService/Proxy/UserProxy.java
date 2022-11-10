package com.niit.UserService.Proxy;

import com.niit.UserService.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "AUTH-SERVICE")
public interface UserProxy {
    @RequestMapping(value = "/api/v3/register", method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody User user);
    @RequestMapping(value = "/api/v3/update", method = RequestMethod.PUT)
    public ResponseEntity updateUserDetailsFunction(@RequestBody User user);
}
