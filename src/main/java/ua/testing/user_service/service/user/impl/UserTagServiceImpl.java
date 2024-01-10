package ua.testing.user_service.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.testing.user_service.repository.UserRepository;
import ua.testing.user_service.service.user.UserTagService;
import ua.testing.user_service.utils.StringUtils;

@Service
@RequiredArgsConstructor
public class UserTagServiceImpl implements UserTagService {
    final StringUtils stringUtils;
    final UserRepository userRepository;

    private static final int USER_TAG_LENGTH = 8;

    @Override
    public String provideUniqueUserTag() {
        String userTag;
        do {
            userTag = stringUtils.randomString(USER_TAG_LENGTH);
        } while (checkIfUserTagExists(userTag));

        return userTag;
    }

    @Override
    public boolean checkIfUserTagExists(String userTag) {
        return userRepository.existsByUserTag(userTag);
    }
}
