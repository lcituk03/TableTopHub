package pl.coderslab.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalResponseDTO {
    private Long id;
    //biore username i gametitle zamiast ich id, zeby bylo czytelniej
    private String gameTitle;
    private String username;
    private LocalDate rentalDate;
    private LocalDate returnDate;
}
