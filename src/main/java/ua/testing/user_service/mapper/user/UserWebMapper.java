package ua.testing.user_service.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.testing.user_service.mapper.profile.ProfileWebMapper;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;
import ua.testing.user_service.model.user.UserRequest;
import ua.testing.user_service.model.user.UserResponse;
import ua.testing.user_service.model.user.UserResponseShort;
import ua.testing.user_service.model.user.UserUpdateRequest;

import java.util.List;

@Mapper(uses = ProfileWebMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserWebMapper {

    UserResponse mapToResponse(User user);

    UserResponseShort mapToShortResponse(User user);

    PasswordUser mapToPasswordUser(UserRequest userRequest);

    List<UserResponseShort> mapToShortResponse(List<User> user);

    User mapToUser(UserUpdateRequest userUpdateRequest);
}
