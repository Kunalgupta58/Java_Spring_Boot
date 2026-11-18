package com.example.demo.service;


import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


//    public User getUserById(Long id) {
//
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent()){
//            return user.get();
//        }
//        else{
//            throw new RuntimeException("User not Found");
//        }
//    }
//
public User getUser(Long id) {

    return userRepository.findById(id)
            .orElseThrow(() ->
                    new UserNotFoundException(
                            "User not found with ID: " + id
                    )
            );
}


    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found with ID: " + id
                        )
                );

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}