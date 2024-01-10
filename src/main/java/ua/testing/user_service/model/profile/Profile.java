package ua.testing.user_service.model.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private String description;

    private String location;

    private String website;

    private LocalDate birthDate;

    private LocalDate joinDate;

    private String avatar;

    private String banner;

    private File avatarFile;

    private File bannerFile;
}
