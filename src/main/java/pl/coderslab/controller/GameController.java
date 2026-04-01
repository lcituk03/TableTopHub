package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dto.GameRequestDTO;
import pl.coderslab.dto.GameResponseDTO;
import pl.coderslab.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // GET http://localhost:8080/api/games
    @GetMapping
    public List<GameResponseDTO> getAllGames() {
        return gameService.getAllGames();
    }

    // GET http://localhost:8080/api/games/1
    @GetMapping("/{id}")
    public GameResponseDTO getGameById(@PathVariable("id") Long id) {
        return gameService.getGameById(id);
    }

    @PostMapping
    public GameResponseDTO createGame(@RequestBody GameRequestDTO request) {
        return gameService.createGame(request);
    }

    @PutMapping("/{id}")
    public GameResponseDTO updateGame(@PathVariable("id") Long id, @RequestBody GameRequestDTO request) {
        return gameService.updateGame(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable("id") Long id) {
        gameService.deleteGame(id);
    }
}
