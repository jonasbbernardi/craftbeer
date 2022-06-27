package com.beerhouse.services.exceptions;

public class BeerNotFoundException extends RuntimeException{
    public BeerNotFoundException(Integer id) {
        super(String.format("Beer not found with id %d", id));
    }
}
