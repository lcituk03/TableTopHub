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
    @Min(value = 1, message = "Minimalna ocena to 1")
    @Max(value = 10, message = "Maksymalna ocena to 10")
    private int score;
}
