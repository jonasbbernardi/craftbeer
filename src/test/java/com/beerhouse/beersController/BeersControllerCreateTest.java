package com.beerhouse.beersController;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.services.exceptions.BeerAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
 * Test endpoint used to create a beer
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
public class BeersControllerCreateTest extends BeersControllerTest {
    private final int existentBeerId = 1;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Before
    public void setUpEdit() {
        Beer savedBeer = this.getBeer();
        Optional<Beer> optionalBeer = Optional.ofNullable(savedBeer);
        Optional<Beer> nullBeer = Optional.empty();
        when(beerRepository.findById(existentBeerId)).thenReturn(optionalBeer);
        when(beerRepository.findById(0)).thenReturn(nullBeer);
        assert savedBeer != null;
        when(beerRepository.save(savedBeer)).thenReturn(savedBeer);
    }

    @Test
    public void post_Success() throws Exception {
        String content = objectWriter.writeValueAsString(this.getBeer());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/beers")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.alcoholContent").value("5%"));
    }

    @Test(expected = BeerAlreadyExistsException.class)
    public void post_AlreadyExists() throws Throwable {
        Beer beer = this.getBeer();
        beer.setId(existentBeerId);
        String content = objectWriter.writeValueAsString(beer);
        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/beers")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError());
        } catch (NestedServletException nestedServletException) {
            throw nestedServletException.getRootCause();
        }
    }

    @Override
    protected Beer getBeer() {
        Beer mockedBeer = super.getBeer();
        return Beer.builder()
                .name("New " + mockedBeer.getName())
                .ingredients(mockedBeer.getIngredients())
                .alcoholContent(mockedBeer.getAlcoholContent())
                .price(mockedBeer.getPrice())
                .category(mockedBeer.getCategory())
                .build();
    }
}
