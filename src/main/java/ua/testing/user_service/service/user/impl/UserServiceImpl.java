package ua.testing.user_service.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ua.testing.user_service.mapper.UserPersistenceMapper;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;
import ua.testing.user_service.model.user.UserData;
import ua.testing.user_service.repository.UserRepository;
import ua.testing.user_service.service.exception.UserNotFoundException;
import ua.testing.user_service.service.exception.UsernameTakenException;
import ua.testing.user_service.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserPersistenceMapper userPersistenceMapper;

    private final UserRepository userRepository;

    private static final String USERNAME_ALREADY_TAKEN = "Username '%s' already taken";
    private static final String USER_NOT_FOUND = "Cannot find user under id '%s'";

    @Override
    public User createUser(PasswordUser passwordUser) {
        if (userRepository.existsByUsername(passwordUser.getUsername())) {
            throw new UsernameTakenException(String.format(USERNAME_ALREADY_TAKEN, passwordUser.getUsername()));
        }
        UserData userData = userPersistenceMapper.mapToData(passwordUser);

        return userPersistenceMapper.mapToUser(userRepository.save(userData));
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistenceMapper.mapToUser(userRepository.findAll());
    }

    @Override
    public Page<User> getAllUsersPageable() {
        throw new NotImplementedException();
    }

    @Override
    public User getUser(Long userId) {
        UserData existingUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, userId)));

        return userPersistenceMapper.mapToUser(existingUser);
    }

    @Override
    public User updateUser(Long userId, PasswordUser updatedUser) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND, userId));
        }
        if (userRepository.existsByUsername(updatedUser.getUsername())) {
            throw new UsernameTakenException(String.format(USERNAME_ALREADY_TAKEN, updatedUser.getUsername()));
        }

        UserData updatedEntity = userPersistenceMapper.mapToData(updatedUser);
        updatedEntity.setId(userId);

        return userPersistenceMapper.mapToUser(userRepository.save(updatedEntity));
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND, userId));
        }

        userRepository.deleteById(userId);
    }
}
