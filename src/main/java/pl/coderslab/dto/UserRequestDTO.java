package pl.coderslab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Username jest wymagany")
    private String username;
    @Email(message = "Podaj poprawny e-mail")
    @NotBlank
    private String email;
}
