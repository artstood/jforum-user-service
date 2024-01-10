package ua.testing.user_service.service.user;

public interface UserTagService {

    String provideUniqueUserTag();

    boolean checkIfUserTagExists(String userTag);
}
