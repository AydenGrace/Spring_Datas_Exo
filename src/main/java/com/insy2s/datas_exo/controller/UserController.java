package com.insy2s.datas_exo.controller;

import com.insy2s.datas_exo.entity.User;
import com.insy2s.datas_exo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        System.out.println("request to get all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        System.out.println("request to get user with id : "+id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        System.out.println("request to delete user with id : "+id);
        userService.deleteUser(id);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User body){
        System.out.println("request to create new user");
        userService.createUser(body);
        return ResponseEntity.ok("User created");
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User body){
        System.out.println("request to update user by id");
        userService.updateUserLastname(id,body.getLastname());
    }
}
