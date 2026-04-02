package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.RatingRequestDTO;
import pl.coderslab.dto.RatingResponseDTO;
import pl.coderslab.dto.RatingUpdateDTO;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.Rating;
import pl.coderslab.entity.User;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.repository.RatingRepository;
import pl.coderslab.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    //READ - nie dawalam czytania ogolnie wszytskich bo to bez sensu raczej
    public List<RatingResponseDTO> getAllByGameId(Long gameId) {
        return ratingRepository.findAllByGameId(gameId)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }


    //CREATE
    public RatingResponseDTO addRating(RatingRequestDTO req) {

        Game game = gameRepository.findById(req.getGameId()).orElseThrow(() -> new RuntimeException("Nie ma gry o id " + req.getGameId()));
        User user = userRepository.findById(req.getUserId()).orElseThrow(() -> new RuntimeException("Nie ma usera o id " + req.getUserId()));

        if (ratingRepository.existsByGameIdAndUserId(req.getGameId(), req.getUserId())) {
            throw new RuntimeException("Ta gra już została oceniona przez użytkownika " + user.getUsername() + " możesz edytować swoją poprzednią opinię");
        }

        Rating rating = new Rating();
        rating.setGame(game);
        rating.setUser(user);
        rating.setScore(req.getScore());

        return mapToDto(ratingRepository.save(rating));
    }

    //UPDATE
    public RatingResponseDTO updateRating(Long ratingId, RatingUpdateDTO req) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Nie ma ratingu o id " + ratingId));
        rating.setScore(req.getScore());

        return mapToDto(ratingRepository.save(rating));
    }

    //DELETE
    public void deleteRating(Long ratingId){
        ratingRepository.deleteById(ratingId);
    }

    public RatingResponseDTO mapToDto(Rating rating) {
        RatingResponseDTO dto = new RatingResponseDTO();
        dto.setGameTitle(rating.getGame().getTitle());
        dto.setUsername(rating.getUser().getUsername());
        dto.setScore(rating.getScore());
        return dto;
    }
}
