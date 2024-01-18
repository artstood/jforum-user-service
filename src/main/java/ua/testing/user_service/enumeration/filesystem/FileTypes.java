package ua.testing.user_service.enumeration.filesystem;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTypes {
    PNG(".png");

    private String extension;
}
