package com.beerhouse.domain.repository;

import com.beerhouse.domain.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Integer> {
}
