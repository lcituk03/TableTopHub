package pl.coderslab.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

// zrobilam ta klase zeby przy edycji oceny mozna bylo zmieniac tylko ocene a nie tez inne rzeczy (a na to pozwalal requestDTO)

@Data
public class RatingUpdateDTO {

    @Min(value = 1, message = "Minimalna ocena to 1")
    @Max(value = 10, message = "Maksymalna ocena to 10")
    private int score;
}
