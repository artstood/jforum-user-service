package ua.testing.user_service.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.testing.user_service.mapper.user.UserPersistenceMapper;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;
import ua.testing.user_service.model.user.UserData;
import ua.testing.user_service.repository.UserRepository;
import ua.testing.user_service.service.exception.UserNotFoundException;
import ua.testing.user_service.service.exception.UsernameTakenException;
import ua.testing.user_service.service.profile.ProfileService;
import ua.testing.user_service.service.user.UserService;
import ua.testing.user_service.service.user.UserTagService;
import ua.testing.user_service.utils.StringUtils;

import java.time.LocalDate;
import java.util.List;

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

        User user = userPersistenceMapper.mapToUser(existingUser);

        Profile userProfile = user.getProfile();

        userProfile.setBannerFile(
                profileService.getBanner(existingUser.getId(), stringUtils.isEmpty(userProfile.getBanner())));

        userProfile.setAvatarFile(
                profileService.getAvatar(existingUser.getId(), stringUtils.isEmpty(userProfile.getAvatar())));

        return user;
    }

    @Override
    public User updateUser(Long userId, PasswordUser updatedUser) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND, userId));
        }

        if (userRepository.existsByUserTag(updatedUser.getUserTag())) {
            throw new UsernameTakenException(String.format(EMAIL_ALREADY_PRESENT, updatedUser.getUserTag()));
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
