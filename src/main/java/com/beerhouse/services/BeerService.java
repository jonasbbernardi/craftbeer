package com.beerhouse.services;

import com.beerhouse.domain.entity.Beer;

import java.util.List;
import java.util.Map;

public interface BeerService {
    List<Beer> list();
    Beer get(Integer id);
    Beer create( Beer beer );
    Beer update( Beer beer );
    Beer partialUpdate( int id, Map<String, Object> beer );
    Beer delete(Integer id);
}
