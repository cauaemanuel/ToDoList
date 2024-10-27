package com.todolist.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.todolist.entities.User;
import com.todolist.mapDTO.UserDTO;
import com.todolist.repository.UserRepository;
import com.todolist.service.UserService;
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
    private UserService service;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserDTO user){

        User userCreated = service.create(user);
        if (userCreated == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O Usuario ja existe");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

    }
}
