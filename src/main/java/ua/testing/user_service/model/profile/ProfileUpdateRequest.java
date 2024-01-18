package ua.testing.user_service.model.profile;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequest {

    private String description;

    private String location;

    private String website;

    private LocalDate birthDate;
}

