package ua.testing.user_service.model.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private String avatar;

    private String banner;

    private String description;

    private String location;

    private String website;

    private LocalDate birthDate;

    private LocalDate joinDate;
}
