package com.beerhouse.controllers.advices;

import com.beerhouse.services.exceptions.BeerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handler for exception when beer was not found
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
@ControllerAdvice
public class BeerNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(BeerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String beerNotFoundHandler(BeerNotFoundException beerNotFoundException) {
        return beerNotFoundException.getMessage();
    }
}
