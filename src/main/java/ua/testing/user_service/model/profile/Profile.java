package ua.testing.user_service.model.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private Long id;

    private String description;

    private String location;

    private String website;

    private LocalDate birthDate;

    private LocalDate joinDate;

    private String avatar;

    private String banner;

    public Profile prototype(){
        return new Profile(
                id,
                description,
                location,
                website,
                birthDate,
                joinDate,
                avatar,
                banner
        );
    }
}
