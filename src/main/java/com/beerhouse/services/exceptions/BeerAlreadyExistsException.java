package com.beerhouse.services.exceptions;

/**
 * Throwed when trying to create a beer with id of an existent beer
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
public class BeerAlreadyExistsException extends RuntimeException {
    public BeerAlreadyExistsException(Integer id) {
        super(String.format("Beer already exists %d", id));
    }
}
