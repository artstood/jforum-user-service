package ua.testing.user_service.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.testing.user_service.controller.UserController;
import ua.testing.user_service.mapper.user.UserWebMapper;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.UserRequest;
import ua.testing.user_service.model.user.UserResponse;
import ua.testing.user_service.model.user.UserResponseShort;
import ua.testing.user_service.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserControllerImpl implements UserController {

    private final UserWebMapper userWebMapper;

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponseShort> createUser(UserRequest userRequest) {
        PasswordUser passwordUser = userWebMapper.mapToPasswordUser(userRequest);
        return new ResponseEntity<>(
                userWebMapper.mapToShortResponse(userService.createUser(passwordUser, userRequest.getBirthDate())),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UserResponseShort>> getAllUsers() {
        return new ResponseEntity<>(
                userWebMapper.mapToShortResponse(userService.getAllUsers()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> getUserByUserTag(String userTag) {
        return new ResponseEntity<>(userWebMapper.mapToResponse(userService.getUser(userTag)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long userId, UserRequest userRequest) {
        PasswordUser updateUser = userWebMapper.mapToPasswordUser(userRequest);
        return new ResponseEntity<>(
                userWebMapper.mapToResponse(userService.updateUser(userId, updateUser)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}