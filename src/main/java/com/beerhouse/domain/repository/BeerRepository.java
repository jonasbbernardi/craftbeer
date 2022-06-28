package com.beerhouse.domain.repository;

import com.beerhouse.domain.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Beer persistence data interface
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
public interface BeerRepository extends JpaRepository<Beer, Integer> {
}
