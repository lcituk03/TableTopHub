package pl.coderslab.dto;

import lombok.Data;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;

    //liczba gier danej kategorii
    private int gameCount;
}