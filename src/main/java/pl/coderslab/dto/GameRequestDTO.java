package pl.coderslab.dto;

import lombok.Data;

import java.util.List;

@Data
public class GameRequestDTO {
    private String title;
    private int minPlayers;
    private int maxPlayers;
    private double complexityLevel;
    private String description;
    private Long publisherId;
    private List<Long> categoryIds;
}
