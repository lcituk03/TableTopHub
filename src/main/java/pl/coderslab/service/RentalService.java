package pl.coderslab.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.coderslab.dto.RentalRequestDTO;
import pl.coderslab.dto.RentalResponseDTO;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.Rental;
import pl.coderslab.entity.User;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.repository.RentalRepository;
import pl.coderslab.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    //READ

    public List<RentalResponseDTO> getAll(){
        return rentalRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<RentalResponseDTO> getAllUserRentals(Long userId){
        return rentalRepository.findAllByUserId(userId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<RentalResponseDTO> getAllActiveUserRentals(Long userId){
        return rentalRepository.findAllByUserIdAndReturnDateIsNull(userId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    //CREATE
    @Transactional //dzieki temu jak cos sie nie uda to nie bedzie balaganu (albo wszytsko albo nic sie wykona)
    public RentalResponseDTO rentGame(RentalRequestDTO req) {

        Game game = gameRepository.findById(req.getGameId()).orElseThrow(() -> new RuntimeException("Nie ma gry o id " + req.getGameId()));

        User user = userRepository.findById(req.getUserId()).orElseThrow(() -> new RuntimeException("Nie ma usera o id " + req.getUserId()));

        // gra musi byc dostepna
        if (!game.isAvailable()) {
            throw new RuntimeException("Gra nie jest dostępna, niestety ktoś inny ją wypożyczył.");
        }

        //user moze miec na raz max3 gry wypozyczone
        long activeRentals = rentalRepository.findAllByUserIdAndReturnDateIsNull(user.getId()).size();
        if(activeRentals>=3){
            throw new RuntimeException("Użytkownik ma już 3 wypożyczone gry, musi coś zwrócić, żeby wypożyczyć nową");
        }

        //wypozyczanie
        Rental rental = new Rental();
        rental.setGame(game);
        rental.setUser(user);
        rental.setRentalDate(LocalDate.now());

        //gra staje sie niedostepna
        game.setAvailable(false);
        gameRepository.save(game);

        return mapToDTO(rentalRepository.save(rental));
    }

    //UPDATE
    @Transactional
    public RentalResponseDTO returnGame(Long rentalId){
        Rental rental = rentalRepository.findById(rentalId).orElseThrow( ()-> new RuntimeException("Nie ma rentala o takim id"));

        if(rental.getReturnDate() != null){
            throw new RuntimeException("Ta gra została już zwrócona!!");
        }

        //zwracam
        rental.setReturnDate(LocalDate.now());

        //gra znow dostepna
        Game game = rental.getGame();
        game.setAvailable(true);
        gameRepository.save(game);

        return mapToDTO(rentalRepository.save(rental));
    }

    //DELETE nie robie bo raczej sie nie usuwa tego z historii

    private RentalResponseDTO mapToDTO(Rental r) {
        RentalResponseDTO dto = new RentalResponseDTO();
        dto.setId(r.getId());
        dto.setGameTitle(r.getGame().getTitle());
        dto.setUsername(r.getUser().getUsername());
        dto.setRentalDate(r.getRentalDate());
        dto.setReturnDate(r.getReturnDate());
        return dto;
    }


}
