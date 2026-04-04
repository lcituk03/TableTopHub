package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.dto.UserRequestDTO;
import pl.coderslab.dto.UserResponseDTO;
import pl.coderslab.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody UserRequestDTO req) {
        return userService.createUser(req);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable("id") Long id, @RequestBody UserRequestDTO req) {
        return userService.updateUser(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
