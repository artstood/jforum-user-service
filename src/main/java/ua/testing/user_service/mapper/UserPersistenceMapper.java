package ua.testing.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;
import ua.testing.user_service.model.user.UserData;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserPersistenceMapper {
    UserData mapToData(PasswordUser passwordUser);

    User mapToUser(UserData userData);

    List<User> mapToUser(Iterable<UserData> userDataList);
}
