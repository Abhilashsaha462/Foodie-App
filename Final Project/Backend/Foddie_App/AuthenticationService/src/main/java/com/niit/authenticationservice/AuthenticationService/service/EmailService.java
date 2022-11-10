package com.niit.authenticationservice.AuthenticationService.service;

import com.niit.authenticationservice.AuthenticationService.domain.EmailDetails;
import com.niit.authenticationservice.AuthenticationService.domain.User;

public interface EmailService {
    String sendSimpleEmail(User user);
    String sendEmailWithAttachments(User user);
}
