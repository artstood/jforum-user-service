package ua.testing.user_service.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.testing.user_service.model.profile.Profile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private String email;

    private String userTag;

    private String name;

    private Profile profile;

}
