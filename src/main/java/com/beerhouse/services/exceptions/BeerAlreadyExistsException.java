package com.beerhouse.services.exceptions;

public class BeerAlreadyExistsException extends RuntimeException {
    public BeerAlreadyExistsException(Integer id) {
        super(String.format("Beer already exists %d", id));
    }
}
