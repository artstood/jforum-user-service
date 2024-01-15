package ua.testing.user_service.mapper.profile;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.model.profile.ProfileData;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProfilePersistenceMapper {

    ProfileData mapToData(Profile profile);

    Profile mapToProfile(ProfileData profileData);

    List<Profile> mapToProfile(Iterable<ProfileData> userDataList);
}