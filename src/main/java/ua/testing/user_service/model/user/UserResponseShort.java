package ua.testing.user_service.model.user;

import lombok.Data;

@Data
public class UserResponseShort {
    private Long id;

    private String name;

    private String userTag;
}
