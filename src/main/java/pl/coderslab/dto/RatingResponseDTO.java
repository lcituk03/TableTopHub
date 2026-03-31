package pl.coderslab.dto;

import lombok.Data;

@Data
public class RatingResponseDTO {
    //nazwy zamiast id
    private String username;
    private String gameTitle;
    private int score;
}
