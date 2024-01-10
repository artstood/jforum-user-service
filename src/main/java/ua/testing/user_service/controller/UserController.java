package ua.testing.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.testing.user_service.model.user.UserRequest;
import ua.testing.user_service.model.user.UserResponse;
import ua.testing.user_service.model.user.UserResponseShort;
import ua.testing.user_service.swagger.user.DeleteUserOpenAPI;
import ua.testing.user_service.swagger.user.GetAllUsersOpenAPI;
import ua.testing.user_service.swagger.user.GetUserOpenAPI;
import ua.testing.user_service.swagger.user.PatchUserOpenAPI;
import ua.testing.user_service.swagger.user.PostUserOpenAPI;

import java.util.List;

public interface UserController {
    @PostUserOpenAPI
    @PostMapping()
    ResponseEntity<UserResponseShort> createUser(@RequestBody UserRequest userRequest);

    @GetAllUsersOpenAPI
    @GetMapping()
    ResponseEntity<List<UserResponseShort>> getAllUsers();

    @GetUserOpenAPI
    @GetMapping(value = "{userTag}")
    ResponseEntity<UserResponse> getUserByUserTag(@PathVariable("userTag") String userTag);

    @PatchUserOpenAPI
    @PatchMapping("{userId}")
    ResponseEntity<UserResponse> updateUser(@PathVariable("userId") Long userId, @RequestBody UserRequest userRequest);

    @DeleteUserOpenAPI
    @DeleteMapping("{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId);
}
