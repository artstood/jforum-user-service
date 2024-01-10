package ua.testing.user_service.mapper.profile;


import org.aspectj.util.FileUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ua.testing.user_service.exception.utils.FileSystemUtilsException;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.model.profile.ProfileResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProfileWebMapper {

    @Mapping(source = "avatarFile", target = "avatarFile", qualifiedByName = "fileByteMap")
    @Mapping(source = "bannerFile", target = "bannerFile", qualifiedByName = "fileByteMap")
    ProfileResponse mapToResponse(Profile profile);

    List<ProfileResponse> mapToResponse(List<Profile> user);

    @Named("fileByteMap")
    default byte[] readFileBytes(File file) {
        try {
            return FileUtil.readAsByteArray(file);
        } catch (IOException e) {
            throw new FileSystemUtilsException(e.getMessage());
        }
    }
}
