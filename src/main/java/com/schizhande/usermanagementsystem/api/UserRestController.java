package com.schizhande.usermanagementsystem.api;


import com.schizhande.usermanagementsystem.exceptions.ErrorBody;
import com.schizhande.usermanagementsystem.model.User;
import com.schizhande.usermanagementsystem.service.UserService;
import com.schizhande.usermanagementsystem.service.request.NewUserRequest;
import com.schizhande.usermanagementsystem.service.request.VerifyUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUserRequest request, WebRequest webRequest) {
        User user = userService.saveUser(request, webRequest);
        if (user == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to save user.").build();
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        User user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/", params = {"page", "size"})
    public Page<User> getAllUsers(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return userService.findAll(page,size);
    }

    @GetMapping(value = "/all")
    public Collection<User> getAllUsers(){
        return userService.findAll();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody NewUserRequest request){
        User user = userService.update(userId, request);
        if (user == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to update user.").build();
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        User user = userService.deleteUser(userId);
        if (user == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to delete user.").build();
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/role/{roleId}", params = {"page", "size"})
    public Page<User> getUserByRole(@PathVariable Long roleId, @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return userService.findByRole(roleId , page, size);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> enableUser(@RequestParam boolean enable ,@PathVariable Long userId){
        User user = userService.enableUser(userId, enable);
        if (user == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to enable or disable user.").build();
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserRequest request){
        User user = userService.verifyUser(request);
        if (user == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to Verify user.").build();
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(user);
    }




}
