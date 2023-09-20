package ua.testing.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;
import ua.testing.user_service.model.user.UserRequest;
import ua.testing.user_service.model.user.UserResponse;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserWebMapper {

    UserResponse mapToResponse(User user);

    PasswordUser mapToPasswordUser(UserRequest userRequest);

    List<UserResponse> mapToResponse(List<User> user);
}
