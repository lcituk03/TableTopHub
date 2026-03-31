package pl.coderslab.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//do formularza oceny gry

@Data
public class RatingRequestDTO {
    @NotNull
    private Long gameId;
    @NotNull
    private Long userId;
    @Min(1) @Max(5)
    private int score;
}
