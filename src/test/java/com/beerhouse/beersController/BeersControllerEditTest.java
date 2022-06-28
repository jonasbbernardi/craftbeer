package com.beerhouse.beersController;

import com.beerhouse.domain.entity.Beer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test edit endpoints - partial (path) and full (put)
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
public class BeersControllerEditTest extends BeersControllerTest {
    private final int existentBeerId = 1;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Before
    public void setUpEdit() {
        Beer savedBeer = this.getEditedBeer();
        Optional<Beer> beer = Optional.ofNullable(super.getBeer());
        when(beerRepository.findById(existentBeerId)).thenReturn(beer);
        when(beerRepository.save(savedBeer)).thenReturn(savedBeer);
    }

    @Test
    public void patch_Success() throws Exception {
        String content = objectWriter.writeValueAsString(this.getEditedBeer());
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/beers/"+existentBeerId)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alcoholContent").value("5%"))
                .andExpect(jsonPath("$.price").value(1.0));
    }

    @Test
    public void put_Success() throws Exception {
        String content = objectWriter.writeValueAsString(this.getEditedBeer());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/beers/"+existentBeerId)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alcoholContent").value("5%"))
                .andExpect(jsonPath("$.price").value(1.0));
    }

    protected Beer getEditedBeer() {
        Beer beer = super.getBeer();
        beer.setPrice(1.0);
        return beer;
    }
}
