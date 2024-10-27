package com.todolist.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.todolist.entities.User;
import com.todolist.mapDTO.MapperDTO;
import com.todolist.mapDTO.UserDTO;
import com.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MapperDTO map;


    public User create(UserDTO userDTO){
        User user = map.userDTOtoUser(userDTO);

        User userRepo = repository.findByUsername(user.getUsername());
        if (userRepo != null){
           return null;
        }

        String passwordHashed = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordHashed);

        User userCreated = repository.save(user);
        return userCreated;
    }
}
