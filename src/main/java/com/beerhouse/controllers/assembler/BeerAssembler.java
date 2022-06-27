package com.beerhouse.controllers.assembler;

import com.beerhouse.controllers.BeersController;
import com.beerhouse.domain.entity.Beer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BeerAssembler implements RepresentationModelAssembler<Beer, EntityModel<Beer>> {
    @Override
    public EntityModel<Beer> toModel(Beer beer) {
        return EntityModel.of(beer,
                linkTo(methodOn(BeersController.class).get(beer.getId())).withSelfRel(),
                linkTo(methodOn(BeersController.class).list()).withRel("all")
        );
    }

    @Override
    public CollectionModel<EntityModel<Beer>> toCollectionModel(Iterable<? extends Beer> beers) {
        List<Beer> list = new ArrayList<Beer>();
        beers.forEach(list::add);

        List<EntityModel<Beer>> beersCollection = list
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(beersCollection,
                linkTo(methodOn(BeersController.class).list()).withSelfRel()
        );

    }
}
