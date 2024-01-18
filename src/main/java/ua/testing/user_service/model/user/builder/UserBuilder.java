package ua.testing.user_service.model.user.builder;

import lombok.Data;
import lombok.experimental.Accessors;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.model.user.PasswordUser;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class UserBuilder {

    private Long id;

    private String email;

    private String password;

    private String userTag;

    private String name;

    private String description;

    private String location;

    private String website;

    private LocalDate birthDate;

    private LocalDate joinDate;

    private String avatar;

    private String banner;

    public PasswordUser build() {
        Profile profile = new Profile(
                id,
                description,
                location,
                website,
                birthDate,
                joinDate,
                avatar,
                banner);

        return new PasswordUser(
                id,
                email,
                password,
                userTag,
                name,
                profile);
    }
}
