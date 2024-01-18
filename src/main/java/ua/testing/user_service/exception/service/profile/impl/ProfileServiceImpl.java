package ua.testing.user_service.exception.service.profile.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.testing.user_service.enumeration.filesystem.ProfileResource;
import ua.testing.user_service.exception.service.profile.ProfileService;
import ua.testing.user_service.exception.service.user.UserNotFoundException;
import ua.testing.user_service.filesystem.profile.ProfileFileSystemRepository;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.model.profile.ProfileData;
import ua.testing.user_service.repository.ProfileRepository;
import ua.testing.user_service.utils.LocalDateTimeUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private static final String USER_NOT_FOUND = "Cannot find user under user tag '%s'";

    private final ProfileRepository profileRepository;

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
    public Profile updateProfileMedia(ProfileData oldProfile, Profile userProfile, byte[] avatar, byte[] banner) {
        Profile updatedProfile = userProfile.prototype();

        if (avatar.length != 0) {
            updatedProfile.setAvatar(profileFileSystemRepository.storeMedia(updatedProfile.getId(), avatar));
            if (!oldProfile.getAvatar().isEmpty()) {
                profileFileSystemRepository.deleteProfileMedia(oldProfile.getId(), oldProfile.getAvatar());
            }
        }

        if (banner.length != 0) {
            updatedProfile.setBanner(profileFileSystemRepository.storeMedia(updatedProfile.getId(), banner));
            if (!oldProfile.getBanner().isEmpty()) {
                profileFileSystemRepository.deleteProfileMedia(oldProfile.getId(), oldProfile.getBanner());
            }
        }

        return updatedProfile;
    }

    @Override
    public File getProfileResource(Long userId, ProfileResource profileResource) {
        Optional<ProfileData> existingProfile = profileRepository.findById(userId);

        if (existingProfile.isEmpty()) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND, userId));
        }

        String resourcePath = profileResource.equals(ProfileResource.AVATAR) ?
                existingProfile.get().getAvatar() :
                existingProfile.get().getBanner();

        return profileFileSystemRepository.getProfileImage(userId, resourcePath);
    }
}
