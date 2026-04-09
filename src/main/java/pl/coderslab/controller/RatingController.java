package pl.coderslab.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dto.RatingRequestDTO;
import pl.coderslab.dto.RatingResponseDTO;
import pl.coderslab.dto.RatingUpdateDTO;
import pl.coderslab.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{id}")
    public List<RatingResponseDTO> getAllRatingsByGameId(@PathVariable("id") Long id) {
        return ratingService.getAllByGameId(id);
    }

    @PostMapping
    public RatingResponseDTO addRating(@RequestBody @Valid RatingRequestDTO req) {
        return ratingService.addRating(req);
    }

    @PutMapping("/{id}")
    public RatingResponseDTO updateRating(@PathVariable("id") Long id, @RequestBody @Valid RatingUpdateDTO req) {
        return ratingService.updateRating(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable("id") Long id) {
        ratingService.deleteRating(id);
    }
}
