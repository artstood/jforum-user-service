package ua.testing.user_service.swagger.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ua.testing.user_service.model.error.SimpleErrorResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Operation(summary = "Get requested media from user profile. Resources type are ['avatar', 'banner']")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns media by user id and selected media",
                content = {@Content(mediaType = "image/png")}),
        @ApiResponse(responseCode = "400", description = "User with provided user tag was not found",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = SimpleErrorResponse.class)))
})
public @interface GetProfileResourceOpenAPI {
}
