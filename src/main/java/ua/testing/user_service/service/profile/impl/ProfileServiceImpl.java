package ua.testing.user_service.service.profile.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.testing.user_service.enumeration.filesystem.ProfileMedia;
import ua.testing.user_service.filesystem.profile.ProfileFileSystemRepository;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.service.profile.ProfileService;
import ua.testing.user_service.utils.Constants;
import ua.testing.user_service.utils.LocalDateTimeUtils;

import java.io.File;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final LocalDateTimeUtils localDateTimeUtils;

    private final ProfileFileSystemRepository profileFileSystemRepository;

    @Override
    public Profile createNewProfile(LocalDate birthDate) {
        Profile profile = new Profile();
        profile.setJoinDate(localDateTimeUtils.getTodayDate());
        profile.setBirthDate(birthDate);
        return profile;
    }

    @Override
    public File getAvatar(Long userId, boolean getDefault) {
        return getDefault ?
                profileFileSystemRepository.getDefaultImage(ProfileMedia.AVATAR) :
                profileFileSystemRepository.getProfileImage(userId,
                        ProfileMedia.AVATAR.getFileName() + Constants.Extensions.PNG);
    }

    @Override
    public File getBanner(Long userId, boolean getDefault) {
        return getDefault ?
                profileFileSystemRepository.getDefaultImage(ProfileMedia.BANNER) :
                profileFileSystemRepository.getProfileImage(userId,
                        ProfileMedia.BANNER.getFileName() + Constants.Extensions.PNG);

    }
}
