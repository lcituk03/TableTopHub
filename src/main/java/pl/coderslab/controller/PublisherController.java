package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dto.GameResponseDTO;
import pl.coderslab.dto.PublisherRequestDTO;
import pl.coderslab.dto.PublisherResponseDTO;
import pl.coderslab.service.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<PublisherResponseDTO> getAll() {
        return publisherService.getAll();
    }

    @GetMapping("/{id}")
    public PublisherResponseDTO getById(@PathVariable("id") Long id) {
        return publisherService.getById(id);
    }

    @PostMapping
    public PublisherResponseDTO create(@RequestBody PublisherRequestDTO req) {
        return publisherService.create(req);
    }

    @PutMapping("/{id}")
    public PublisherResponseDTO update(@PathVariable("id") Long id, @RequestBody PublisherRequestDTO req){
        return publisherService.update(id,req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        publisherService.delete(id);
    }
}
