package ua.testing.user_service.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.testing.user_service.model.user.UserRequest;
import ua.testing.user_service.model.user.UserResponse;
import ua.testing.user_service.model.user.UserResponseShort;
import ua.testing.user_service.model.user.UserUpdateRequest;
import ua.testing.user_service.swagger.user.*;

import java.util.List;

public interface UserController {
    @PostUserOpenAPI
    @PostMapping()
    ResponseEntity<UserResponseShort> createUser(@RequestBody UserRequest userRequest);

    @GetAllUsersOpenAPI
    @GetMapping()
    ResponseEntity<List<UserResponseShort>> getAllUsers();

    @GetShortUserOpenAPI
    @GetMapping("compact/{userTag}")
    ResponseEntity<UserResponseShort> getUserCompactByUserTag(@PathVariable("userTag") String userTag);


    @GetUserOpenAPI
    @GetMapping(value = "{userTag}")
    ResponseEntity<UserResponse> getUserByUserTag(@PathVariable("userTag") String userTag);

    @PutUserOpenAPI
    @PutMapping(value = "{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<UserResponse> updateUser(
            @PathVariable("userId") Long userId,
            @RequestPart(name = "userUpdateRequest", required = false) UserUpdateRequest userRequest,
            @RequestPart(name = "avatar", required = false) MultipartFile avatar,
            @RequestPart(name = "banner", required = false) MultipartFile banner);

    @DeleteUserOpenAPI
    @DeleteMapping("{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId);
}
