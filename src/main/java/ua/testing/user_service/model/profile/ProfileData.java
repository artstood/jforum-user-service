package ua.testing.user_service.model.profile;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.testing.user_service.model.user.UserData;

import java.time.LocalDate;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
public class ProfileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String avatar;

    private String banner;

    private String description;

    private String location;

    private String website;

    private LocalDate birthDate;

    private LocalDate joinDate;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private UserData user;
}
