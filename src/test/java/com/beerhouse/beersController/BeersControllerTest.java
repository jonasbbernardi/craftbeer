package com.beerhouse.beersController;

import com.beerhouse.controllers.BeersController;
import com.beerhouse.controllers.assembler.BeerAssembler;
import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.services.BeerService;
import com.beerhouse.services.impl.BeerServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to define mocked controller with injections needed
 *
 * @author Jonas B. Bernardi <jonasbbernardi@gmail.com>
 */
public abstract class BeersControllerTest {

    protected MockMvc mockMvc;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    protected BeerRepository beerRepository;

    Beer beer1 = new Beer(1,"Lager Awesome", "Malt, Hop, Barley", "5%", 0.0, "Lager");
    Beer beer2 = new Beer(2,"Ipa Tasty", "Malt, Hop, Barley", "5%", 0.0, "Ipa");
    Beer beer3 = new Beer(3,"Good Blonde Ale", "Malt, Hop, Barley", "5%", 0.0, "Blonde Ale");

    @InjectMocks private BeersController beersController;
    @InjectMocks private BeerAssembler beerAssembler;

    @Before
    public void setUp() {
        BeerService beerService = new BeerServiceImpl(beerRepository);
        beersController = new BeersController(beerService, beerAssembler);
        this.mockMvc = MockMvcBuilders.standaloneSetup(beersController).build();
    }

    protected List<Beer> getBeersList() {
        return new ArrayList<>(Arrays.asList(beer1, beer2, beer3));
    }

    protected Beer getBeer() {
        return beer2;
    }
}
