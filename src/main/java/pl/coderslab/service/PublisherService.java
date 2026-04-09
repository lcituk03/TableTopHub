package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.PublisherRequestDTO;
import pl.coderslab.dto.PublisherResponseDTO;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.repository.PublisherRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final GameRepository gameRepository;

    public PublisherService(PublisherRepository publisherRepository, GameRepository gameRepository) {
        this.publisherRepository = publisherRepository;
        this.gameRepository = gameRepository;
    }

    //READ
    public PublisherResponseDTO getById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono publishera o id: " + id));
        return mapToDTO(publisher);
    }

    public List<PublisherResponseDTO> getAll() {
        return publisherRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    //CREATE
    public PublisherResponseDTO create(PublisherRequestDTO req) {
        Publisher p = new Publisher();
        p.setName(req.getName());
        return mapToDTO(publisherRepository.save(p));
    }

    //UPDATE
    public PublisherResponseDTO update(Long id, PublisherRequestDTO req) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono publishera o id: " + id));

        publisher.setName(req.getName());

        return mapToDTO(publisherRepository.save(publisher));
    }

    //DELETE
    public void delete(Long id) {

        if (!publisherRepository.existsById(id)) {
            throw new RuntimeException("Nie można usunąć publishera o id:  " + id + " taki publisher nie istnieje!");
        }
        publisherRepository.deleteById(id);
    }


    private PublisherResponseDTO mapToDTO(Publisher p) {
        PublisherResponseDTO dto = new PublisherResponseDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        // ile ma gier ten publisher:
        dto.setNumberOfGames(gameRepository.findAllByPublisherId(p.getId()).size());
        return dto;
    }

}