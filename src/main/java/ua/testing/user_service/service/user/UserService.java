package ua.testing.user_service.service.user;

import org.springframework.data.domain.Page;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;

import java.util.List;

public interface UserService {
    User createUser(PasswordUser passwordUser);

    List<User> getAllUsers();

    Page<User> getAllUsersPageable();

    User getUser(Long userId);

    User updateUser(Long userId, PasswordUser updatedUser);

    void deleteUser(Long userId);
}
