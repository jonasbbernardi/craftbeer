package com.beerhouse.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Beer Entity
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
@Entity
@Table(name = "beer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nome deve ser preenchido")
    private String name;

    private String ingredients;

    @Column(name="alcohol_content")
    private String alcoholContent;

    @Column(precision = 10, scale = 2)
    @Min(value = 0, message = "Preço deve ser maior que zero")
    private Double price;

    private String category;
}
