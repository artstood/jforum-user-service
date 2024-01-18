package ua.testing.user_service.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.testing.user_service.controller.UserController;
import ua.testing.user_service.exception.controller.user.UserUpdateException;
import ua.testing.user_service.mapper.user.UserWebMapper;
import ua.testing.user_service.model.profile.ProfileUpdateRequest;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;
import ua.testing.user_service.model.user.UserRequest;
import ua.testing.user_service.model.user.UserResponse;
import ua.testing.user_service.model.user.UserResponseShort;
import ua.testing.user_service.model.user.UserUpdateRequest;
import ua.testing.user_service.service.user.UserService;

import java.io.IOException;
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
    public ResponseEntity<UserResponseShort> getUserCompactByUserTag(String userTag) {
        return new ResponseEntity<>(userWebMapper.mapToShortResponse(userService.getUser(userTag)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long userId, UserUpdateRequest userRequest, MultipartFile avatar, MultipartFile banner) {
        if (userRequest == null) {
            userRequest = new UserUpdateRequest();
        }

        if (userRequest.getProfile() == null) {
            userRequest.setProfile(new ProfileUpdateRequest());
        }

        User updateData = userWebMapper.mapToUser(userRequest);
        updateData.setId(userId);
        updateData.getProfile().setId(userId);

        try {
            User updateUser = userService.updateUser(
                    userId,
                    updateData,
                    avatar == null ? new byte[]{} : avatar.getBytes(),
                    banner == null ? new byte[]{} : banner.getBytes());

            return new ResponseEntity<>(userWebMapper.mapToResponse(updateUser), HttpStatus.OK);

        } catch (IOException e) {
            throw new UserUpdateException("Cannot read incoming files");
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}