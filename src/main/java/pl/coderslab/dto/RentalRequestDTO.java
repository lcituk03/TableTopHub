package pl.coderslab.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalRequestDTO {
    @NotNull
    private Long gameId;
    @NotNull
    private Long userId;
}
