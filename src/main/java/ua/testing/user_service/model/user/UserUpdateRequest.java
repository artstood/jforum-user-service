package ua.testing.user_service.model.user;

import lombok.Data;
import ua.testing.user_service.model.profile.ProfileUpdateRequest;

@Data
public class UserUpdateRequest {

    private String name;

    private String email;

    private String userTag;

    private ProfileUpdateRequest profile;
}
