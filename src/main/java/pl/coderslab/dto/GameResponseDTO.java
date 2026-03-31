package pl.coderslab.dto;

import lombok.Data;
import java.util.List;

@Data
public class GameResponseDTO {

    private Long id;
    private String title;
    private int minPlayers;
    private int maxPlayers;
    private double complexityLevel;
    private String description;
    private boolean available;
    private String publisherName;
    //nie przesylam obiektow tylko nazwy
    private List<String> categoryNames;
    // dodatkowe pole co go nie bylo w entity ( bedzie wyliczane w serwisie)
    private double averageRating;
}