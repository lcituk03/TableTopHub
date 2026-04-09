package pl.coderslab.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class GameRequestDTO {
    @NotBlank(message = "Tytuł jest wymagany")
    private String title;
    private int minPlayers;
    private int maxPlayers;
    private double complexityLevel;
    private String description;
    private Long publisherId;
    private List<Long> categoryIds;
}
