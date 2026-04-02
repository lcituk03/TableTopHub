package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.CategoryRequestDTO;
import pl.coderslab.dto.CategoryResponseDTO;
import pl.coderslab.entity.Category;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.repository.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final GameRepository gameRepository;

    public CategoryService(CategoryRepository categoryRepository, GameRepository gameRepository) {
        this.categoryRepository = categoryRepository;
        this.gameRepository = gameRepository;
    }

    //READ
    public CategoryResponseDTO getById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o id: " + id));

        return mapToDTO(category);
    }

    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    //CREATE
    public CategoryResponseDTO create(CategoryRequestDTO req) {
        Category c = new Category();
        c.setName(req.getName());
        return mapToDTO(categoryRepository.save(c));
    }

    //UPDATE
    public CategoryResponseDTO update(Long id, CategoryRequestDTO req) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o id: " + id));
        c.setName(req.getName());
        return mapToDTO(categoryRepository.save(c));
    }

    //DELETE
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }


    public CategoryResponseDTO mapToDTO(Category c) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        // ile gier mamy z danej kategorii
        dto.setGameCount(gameRepository.findAllByCategoriesId(c.getId()).size());
        return dto;
    }
}
