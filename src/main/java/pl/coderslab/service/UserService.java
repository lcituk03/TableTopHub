package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.UserRequestDTO;
import pl.coderslab.dto.UserResponseDTO;
import pl.coderslab.entity.User;
import pl.coderslab.repository.RentalRepository;
import pl.coderslab.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public UserService(UserRepository userRepository, RentalRepository rentalRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    //READ
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono usera o id " + id));
        return mapToDTO(user);
    }

    //CREATE
    public UserResponseDTO createUser(UserRequestDTO req) {

        //czy username i email unikalny
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Ten username jest już zajęty!");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Ten email ma już konto!");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setMembershipType("BRONZE");

        return mapToDTO(userRepository.save(user));
    }

    //UPDATE
    public UserResponseDTO updateUser(Long id, UserRequestDTO req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika o id " + id));

        if (req.getUsername() != null && !req.getUsername().equals(user.getUsername())) {
            if (userRepository.existsByUsernameAndIdNot(req.getUsername(), id)) {
                throw new RuntimeException("Ten username jest już zajęty!");
            }
            user.setUsername(req.getUsername());
        }

        if (req.getEmail() != null && !req.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmailAndIdNot(req.getEmail(), id)) {
                throw new RuntimeException("Ten e-mail jest już zajęty!");
            }
            user.setEmail(req.getEmail());
        }

        return mapToDTO(userRepository.save(user));
    }


    //DELETE
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Nie można usunąć usera o id:  " + id + " taki user nie istnieje!");
        }
        userRepository.deleteById(id);
    }


    private UserResponseDTO mapToDTO(User user) {

        long totalHistory = rentalRepository.countByUserId(user.getId());

        String currentStatus = calculateStatus(totalHistory);

        if (!currentStatus.equals(user.getMembershipType())) {
            user.setMembershipType(currentStatus);
            userRepository.save(user);
        }

        List<String> activeGameTitles = rentalRepository.findAllByUserIdAndReturnDateIsNull(user.getId())
                .stream()
                .map(rental -> rental.getGame().getTitle())
                .collect(Collectors.toList());


        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setMembershipType(user.getMembershipType());
        dto.setActiveRentalsCount(activeGameTitles.size());
        dto.setCurrentlyBorrowedGames(activeGameTitles);

        return dto;
    }


    //metoda pomocnicza do wyliczania statusu
    private String calculateStatus(long totalRentals) {
        if (totalRentals > 10) return "GOLD";
        if (totalRentals > 3) return "SILVER";
        return "BRONZE";
    }


}
