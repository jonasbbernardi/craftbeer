package com.beerhouse.services.exceptions;

/**
 * Throwed when trying to get a beer that does not exists
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
public class BeerNotFoundException extends RuntimeException{
    public BeerNotFoundException(Integer id) {
        super(String.format("Beer not found with id %d", id));
    }
}
