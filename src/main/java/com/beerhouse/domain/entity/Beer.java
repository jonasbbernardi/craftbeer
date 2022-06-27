package com.beerhouse.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

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

    private String name;

    private String ingredients;

    @Column(name="alcohol_content")
    private String alcoholContent;

    @Column(precision = 10, scale = 2)
    @Min(0)
    private Double price;

    private String category;
}
