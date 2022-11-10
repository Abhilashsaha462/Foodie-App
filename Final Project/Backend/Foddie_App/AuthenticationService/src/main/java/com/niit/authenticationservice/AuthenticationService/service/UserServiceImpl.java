package com.niit.authenticationservice.AuthenticationService.service;

import com.niit.authenticationservice.AuthenticationService.domain.User;
import com.niit.authenticationservice.AuthenticationService.exception.UserNotFoundException;
import com.niit.authenticationservice.AuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null)
        {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        if (userRepository.findById(user.getEmail()).isEmpty()){
            throw new UserNotFoundException();
        }
        User user1 = userRepository.findById(user.getEmail()).get();
        if (user.getPassword()==null) {
            user.setPassword(user1.getPassword());
            userRepository.save(user);
        }
        return userRepository.save(user);
    }

    @Override
    public User forgotPassword(User user) throws UserNotFoundException {
        if (userRepository.findById(user.getEmail()).isEmpty()){
            throw new UserNotFoundException();
        }else {
            User user1 = userRepository.findById(user.getEmail()).get();
            user1.setEmail(user1.getEmail());
            user1.setUserName(user1.getUserName());
            user1.setPassword(user.getPassword());
         return userRepository.save(user1);
        }
    }
}
