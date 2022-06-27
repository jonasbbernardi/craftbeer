package com.beerhouse.controllers;

import com.beerhouse.controllers.assembler.BeerAssembler;
import com.beerhouse.domain.entity.Beer;
import com.beerhouse.services.BeerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/beers")
@Validated
public class BeersController {

    BeerService service;
    BeerAssembler assembler;

    public BeersController(BeerService service, BeerAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public @ResponseBody CollectionModel<EntityModel<Beer>> list() {
        return assembler.toCollectionModel(service.list());
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody EntityModel<Beer> get(@PathVariable int id) {
        Beer beer = service.get(id);
        return assembler.toModel(beer);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Beer>> create(@Valid @RequestBody Beer beer) {
        EntityModel<Beer> beerModel = assembler.toModel(service.create(beer));
        return ResponseEntity
                .created(beerModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(beerModel);
    }

    @PutMapping(path = "/{id}")
    public EntityModel<Beer> fullUpdate(
            @Valid @RequestBody Beer beer,
            @PathVariable int id
    ) {
        beer.setId(id);
        service.update(beer);
        return assembler.toModel(beer);
    }

    @PatchMapping(path = "/{id}")
    public EntityModel<Beer> partialUpdate(
            @RequestBody Map<String, Object> partialBeer,
            @PathVariable int id
    ) {
        Beer beer = service.partialUpdate(id, partialBeer);
        return assembler.toModel(beer);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EntityModel<Beer>> delete(@PathVariable int id) {
        EntityModel<Beer> beer = assembler.toModel(service.delete(id));
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(beer);
    }
}
