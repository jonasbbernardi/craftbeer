package com.beerhouse.beersController;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.services.exceptions.BeerNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test endpoint that return a beer
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
public class BeersControllerGetTest extends BeersControllerTest{
    private final int existentBeerId = 1;
    private final int notExistentBeerId = 5;

    @Before
    public void setUpGet() {
        Optional<Beer> beer = Optional.ofNullable(this.getBeer());
        Optional<Beer> nullBeer = Optional.empty();
        when(beerRepository.findById(existentBeerId)).thenReturn(beer);
        when(beerRepository.findById(notExistentBeerId)).thenReturn(nullBeer);
    }

    @Test
    public void get_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                    .get("/beers/"+existentBeerId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alcoholContent").value("5%"));
    }

    @Test(expected = BeerNotFoundException.class)
    public void get_NotFound() throws Throwable {
        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/beers/" + notExistentBeerId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError());
        } catch (NestedServletException nestedServletException) {
            throw nestedServletException.getRootCause();
        }
    }
}