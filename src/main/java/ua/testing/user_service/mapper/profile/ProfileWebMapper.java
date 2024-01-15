package ua.testing.user_service.mapper.profile;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.model.profile.ProfileResponse;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProfileWebMapper {

    ProfileResponse mapToResponse(Profile profile);

    List<ProfileResponse> mapToResponse(List<Profile> user);
}
