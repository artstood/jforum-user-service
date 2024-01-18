package ua.testing.user_service.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.testing.user_service.enumeration.filesystem.ProfileResource;
import ua.testing.user_service.swagger.resource.GetProfileResourceOpenAPI;

public interface ResourceController {

    @GetProfileResourceOpenAPI
    @GetMapping(value = "profile/{userId}", produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity<byte[]> getProfileResource(@PathVariable("userId") Long userId, @RequestParam("resource") ProfileResource resourceType);
}
