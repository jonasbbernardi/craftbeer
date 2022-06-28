package com.beerhouse.beersController;

import com.beerhouse.domain.entity.Beer;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test endpoint that return the list of all Beers
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
public class BeersControllerListTest extends BeersControllerTest {

    @Test
    public void getAll_Success() throws Exception {
        List<Beer> beers = this.getBeersList();
        Mockito.when(beerRepository.findAll()).thenReturn(beers);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(3)));
        ;
    }
}
