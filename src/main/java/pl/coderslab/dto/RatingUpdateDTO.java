package pl.coderslab.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

// zrobilam ta klase zeby przy edycji oceny mozna bylo zmieniac tylko ocene a nie tez inne rzeczy (a na to pozwalal requestDTO)

@Data
public class RatingUpdateDTO {

    @Min(1)
    @Max(10)
    private int score;
}
