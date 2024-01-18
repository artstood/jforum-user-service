package ua.testing.user_service.mapper.user;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ua.testing.user_service.mapper.profile.ProfilePersistenceMapper;
import ua.testing.user_service.model.user.PasswordUser;
import ua.testing.user_service.model.user.User;
import ua.testing.user_service.model.user.UserData;

import java.util.List;

@Mapper(uses = ProfilePersistenceMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface UserPersistenceMapper {

    @Mapping(target = "password", ignore = true)
    UserData mapToData(PasswordUser passwordUser);

    User mapToUser(UserData userData);

    PasswordUser mapToPasswordUser(UserData userData);

    List<User> mapToUser(Iterable<UserData> userDataList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserData updateUserFields(@MappingTarget UserData oldUser, User newUser);
}
