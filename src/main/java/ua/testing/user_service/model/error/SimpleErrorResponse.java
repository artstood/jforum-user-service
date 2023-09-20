package ua.testing.user_service.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleErrorResponse {
    private String message;
}
