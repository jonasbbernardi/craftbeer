package com.beerhouse.controllers.advices;

import com.beerhouse.services.exceptions.BeerAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handler for exception when beer already exists
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
@ControllerAdvice
public class BeerAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(BeerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String beerNotFoundHandler(BeerAlreadyExistsException beerAlreadyExistsException) {
        return beerAlreadyExistsException.getMessage();
    }
}
