package ua.testing.user_service.service.user;

import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User createUser(PasswordUser passwordUser, LocalDate birthdate);

    List<User> getAllUsers();

    User getUser(String userTag);

    User updateUser(Long userId, PasswordUser updatedUser);

    void deleteUser(Long userId);
}
