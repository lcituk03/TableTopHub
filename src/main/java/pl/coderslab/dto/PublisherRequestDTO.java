package pl.coderslab.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PublisherRequestDTO {
    @NotBlank(message = "Nazwa wydawcy nie może być pusta")
    String name;
}
