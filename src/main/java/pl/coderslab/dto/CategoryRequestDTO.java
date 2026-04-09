package pl.coderslab.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "Nazwa kategorii nie może być pusta")
    String name;
}
