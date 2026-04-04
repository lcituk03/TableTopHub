package pl.coderslab.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String membershipType;
    private int activeRentalsCount;
    private List<String> currentlyBorrowedGames;

}
