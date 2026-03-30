package pl.coderslab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa kategorii nie może być pusta")
    private String name;

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude // bo uzywam @data i zeby nie bylo petli w logach
    private List<Game> games;
}