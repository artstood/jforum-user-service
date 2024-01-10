package ua.testing.user_service.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.testing.user_service.model.profile.ProfileResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String email;

    private String userTag;

    private String name;

    private ProfileResponse profile;

}
