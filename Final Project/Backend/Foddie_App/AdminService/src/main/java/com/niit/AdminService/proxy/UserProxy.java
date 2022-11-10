package com.niit.AdminService.Proxy;

import com.niit.AdminService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserProxy {
    @PostMapping("/user-service/user/register")
    public ResponseEntity<?> registerUserFunction(@RequestBody User user);
}
