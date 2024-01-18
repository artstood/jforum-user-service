package ua.testing.user_service.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.testing.user_service.exception.service.profile.ProfileService;
import ua.testing.user_service.exception.service.user.UserNotFoundException;
import ua.testing.user_service.exception.service.user.UsernameTakenException;
import ua.testing.user_service.mapper.user.UserPersistenceMapper;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;
import ua.testing.user_service.model.user.UserData;
import ua.testing.user_service.repository.UserRepository;
import ua.testing.user_service.service.user.UserService;
import ua.testing.user_service.service.user.UserTagService;
import ua.testing.user_service.utils.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserTagService userTagService;

    private final ProfileService profileService;

    private final UserPersistenceMapper userPersistenceMapper;

    private final UserRepository userRepository;

    private final StringUtils stringUtils;

    private static final String EMAIL_ALREADY_PRESENT = "User with email '%s' already exist";
    private static final String USER_NOT_FOUND = "Cannot find user under user tag '%s'";

    @Override
    public User createUser(PasswordUser passwordUser, LocalDate birthDate) {
        if (userRepository.existsByEmail(passwordUser.getEmail())) {
            throw new UsernameTakenException(String.format(EMAIL_ALREADY_PRESENT, passwordUser.getEmail()));
        }

        passwordUser.setProfile(profileService.createNewProfile(birthDate));

        passwordUser.setUserTag(userTagService.provideUniqueUserTag());

        UserData userData = userPersistenceMapper.mapToData(passwordUser);

        userData.setPassword(stringUtils.sha256Encode(passwordUser.getPassword()));

        return userPersistenceMapper.mapToUser(userRepository.save(userData));
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistenceMapper.mapToUser(userRepository.findAll());
    }

    @Override
    public User getUser(String userTag) {
        UserData existingUser = userRepository
                .findByUserTag(userTag)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, userTag)));

        return userPersistenceMapper.mapToUser(existingUser);
    }

    @Override
    public User updateUser(Long userId, User newUserInfo, byte[] avatar, byte[] banner) {
        Optional<UserData> existingOldUser = userRepository.findById(userId);

        if (existingOldUser.isEmpty()) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND, userId));
        }

        if (userRepository.existsByUserTag(newUserInfo.getUserTag())) {
            throw new UsernameTakenException(String.format(EMAIL_ALREADY_PRESENT, newUserInfo.getUserTag()));
        }

        Profile updatedProfile = profileService.updateProfileMedia(existingOldUser.get().getProfile(), newUserInfo.getProfile(), avatar, banner);
        newUserInfo.setProfile(updatedProfile);

        UserData updatedUser = userRepository.save(
                userPersistenceMapper.updateUserFields(existingOldUser.get(), newUserInfo));

        return userPersistenceMapper.mapToUser(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND, userId));
        }

        userRepository.deleteById(userId);
    }
}
