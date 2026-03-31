package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.GameResponseDTO;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Game;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.repository.RatingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;

    public GameService(GameRepository gameRepository, RatingRepository ratingRepository) {
        this.gameRepository = gameRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<GameResponseDTO> getAllGames(){
        return gameRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public GameResponseDTO getGameById(Long id) {
        return gameRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    //funkcja do mapowania na dto
    private GameResponseDTO mapToDTO(Game game) {
        GameResponseDTO dto = new GameResponseDTO();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setMinPlayers(game.getMinPlayers());
        dto.setMaxPlayers(game.getMaxPlayers());
        dto.setComplexityLevel(game.getComplexityLevel());
        dto.setDescription(game.getDescription());
        dto.setAvailable(game.isAvailable());

        //tylko nazwa publishera
        if (game.getPublisher() != null) {
            dto.setPublisherName(game.getPublisher().getName());
        }

        //tylko lista nazw kategorii
        if (game.getCategories() != null) {
            dto.setCategoryNames(game.getCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.toList()));
        }

        //  wyliczanie średniej ocen dla gry
        Double avg = ratingRepository.getAverageRating(game.getId());
        dto.setAverageRating(avg != null ? Math.round(avg * 100.0) / 100.0 : 0.0); // Zaokrąglamy do 2 miejsc

        return dto;
    }
}
