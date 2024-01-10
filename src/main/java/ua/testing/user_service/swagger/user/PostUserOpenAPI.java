package ua.testing.user_service.swagger.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ua.testing.user_service.model.error.SimpleErrorResponse;
import ua.testing.user_service.model.user.UserResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Operation(summary = "Create user")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created",
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserResponse.class))}),
        @ApiResponse(responseCode = "400", description = "User with email already exist",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = SimpleErrorResponse.class)))})
public @interface PostUserOpenAPI {
}
