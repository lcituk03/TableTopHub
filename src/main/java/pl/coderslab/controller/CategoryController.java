package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.dto.CategoryRequestDTO;
import pl.coderslab.dto.CategoryResponseDTO;
import pl.coderslab.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponseDTO> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getById(@PathVariable("id") Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public CategoryResponseDTO create(@RequestBody CategoryRequestDTO req){
        return categoryService.create(req);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO update(@PathVariable("id") Long id, @RequestBody CategoryRequestDTO req){
        return categoryService.update(id,req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        categoryService.delete(id);
    }

}
