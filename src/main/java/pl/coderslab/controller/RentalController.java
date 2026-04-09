package pl.coderslab.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dto.RentalRequestDTO;
import pl.coderslab.dto.RentalResponseDTO;
import pl.coderslab.service.RentalService;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentalResponseDTO> getAll(){
        return rentalService.getAll();
    }

    @GetMapping("/{id}")
    public List<RentalResponseDTO> getAllUserRentals(@PathVariable("id") Long userId){
        return rentalService.getAllUserRentals(userId);
    }

    @GetMapping("/active/{id}")
    public List<RentalResponseDTO> getAllActiveRentals(@PathVariable("id") Long userId){
        return rentalService.getAllActiveUserRentals(userId);
    }

    @PostMapping("/rent")
    public RentalResponseDTO rentGame(@RequestBody @Valid RentalRequestDTO req){
        return rentalService.rentGame(req);
    }

    @PutMapping("/return/{id}")
    public RentalResponseDTO returnGame(@PathVariable ("id") Long rentalId){
        return rentalService.returnGame(rentalId);
    }

}
