package com.todolist.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.todolist.entities.User;
import com.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody User user){
        User user1 = repository.findByUsername(user.getUsername());

        if (user1 != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        }
       String passwordHashed = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(passwordHashed);

        User userCreated = repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

    }
}
