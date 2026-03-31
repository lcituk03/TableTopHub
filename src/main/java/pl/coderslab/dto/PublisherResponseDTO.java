package pl.coderslab.dto;

import lombok.Data;

@Data
public class PublisherResponseDTO {
    private Long id;
    private String name;
    //dodatkowe pole z liczba gier danego publishera
    private int numberOfGames; // to do wyliczenia w serwsie!!
}
