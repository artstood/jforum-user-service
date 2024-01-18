package ua.testing.user_service.swagger.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ua.testing.user_service.model.error.SimpleErrorResponse;
import ua.testing.user_service.model.user.UserResponseShort;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Operation(summary = "Get compact information for single user by their user tag")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns user by its user tag",
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserResponseShort.class))}),
        @ApiResponse(responseCode = "400", description = "User with provided user tag was not found",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = SimpleErrorResponse.class)))})
public @interface GetShortUserOpenAPI {
}
