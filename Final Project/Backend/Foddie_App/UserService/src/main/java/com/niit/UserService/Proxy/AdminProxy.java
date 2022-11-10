package com.niit.UserService.Proxy;

import com.niit.UserService.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "ADMIN-SERVICE")
public interface AdminProxy {

    @RequestMapping(value = "/admin-service/user/register", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user);
}