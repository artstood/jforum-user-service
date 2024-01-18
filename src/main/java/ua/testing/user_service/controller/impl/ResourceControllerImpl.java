package ua.testing.user_service.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.testing.user_service.controller.ResourceController;
import ua.testing.user_service.enumeration.filesystem.ProfileResource;
import ua.testing.user_service.exception.service.profile.ProfileService;
import ua.testing.user_service.exception.utils.FileSystemUtilsException;
import ua.testing.user_service.utils.FileSystemUtils;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/resource")
public class ResourceControllerImpl implements ResourceController {
    private final ProfileService profileService;
    private final FileSystemUtils fileSystemUtils;

    @Override
    public ResponseEntity<byte[]> getProfileResource(Long userId, ProfileResource media) {
        File file = profileService.getProfileResource(userId, media);
        byte[] fileBytes = new byte[]{};
        if (file.exists()) {
            try {
                fileBytes = fileSystemUtils.readBytes(file.toPath());
            } catch (IOException e) {
                throw new FileSystemUtilsException("cannot read stored file");
            }
        }

        return new ResponseEntity<>(fileBytes, HttpStatus.OK);
    }
}
