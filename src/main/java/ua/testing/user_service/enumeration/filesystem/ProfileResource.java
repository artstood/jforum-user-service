package ua.testing.user_service.enumeration.filesystem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProfileResource {
    AVATAR("avatar"),
    BANNER("banner");

    private final String fileName;
}
