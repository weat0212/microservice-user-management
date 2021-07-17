package com.example.microserviceusermanagement.controller;

import com.example.microserviceusermanagement.model.Role;
import com.example.microserviceusermanagement.model.User;
import com.example.microserviceusermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/service/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findUsers(null));
    }

    @GetMapping("/service/login")
    public ResponseEntity<?> getUser(Principal principal) {
//        Principal principal = request.getUserPrincipal();
        if (principal == null || principal.getName() == null) {
            //This means logout will be successful. login?logout
            return new ResponseEntity<>(HttpStatus.OK);
        }
        //username = principal.getName()
        return ResponseEntity.ok(userService.findByUsername(principal.getName()));
    }

    @PostMapping("/service/registration")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            //status code: 409
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //Default role = user
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PostMapping("/service/names")
    public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList) {
        return ResponseEntity.ok(userService.findUsers(idList));
    }

    @GetMapping("/service/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("It is working...");
    }

//    @DeleteMapping("/service/user/delete")
//    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
//        return ResponseEntity.ok(userService.delete(id));
//    }
}
