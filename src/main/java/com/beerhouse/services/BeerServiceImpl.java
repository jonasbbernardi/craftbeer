package com.beerhouse.services;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.services.exceptions.BeerAlreadyExistsException;
import com.beerhouse.services.exceptions.BeerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BeerServiceImpl implements BeerService{

    @Autowired
    private BeerRepository beerRepository;

    @Override
    public List<Beer> list() {
        return beerRepository.findAll();
    }

    @Override
    public Beer get(Integer id) {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }

    @Override
    public Beer create(Beer beer) {
        try {
            // Verify if already exists (if id is provided)
            this.get(beer.getId());
        } catch (BeerNotFoundException exception) {
            return beerRepository.save(beer);
        }
        throw new BeerAlreadyExistsException(beer.getId());
    }

    @Override
    public Beer update(Beer beer) {
        // Ensure beer exists before save it
        this.get(beer.getId());
        return beerRepository.save(beer);
    }

    @Override
    public Beer partialUpdate(int id, Map<String, Object> partialBeer) {
        // Ensure beer exists before save it
        Beer beer = this.get(id);
        // For each entry in request, set property on entity
        for (Map.Entry<String, Object> entry : partialBeer.entrySet()) {
            System.out.print(entry);
            switch (entry.getKey()) {
                case "name":            beer.setName(entry.getValue().toString()); break;
                case "ingredients":     beer.setIngredients(entry.getValue().toString()); break;
                case "alcoholContent":  beer.setAlcoholContent(entry.getValue().toString()); break;
                case "price":           beer.setPrice(Double.valueOf(entry.getValue().toString())); break;
                case "category":        beer.setCategory(entry.getValue().toString()); break;
            }
        }
        // Save and return entity saved
        return this.beerRepository.save(beer);
    }

    @Override
    public Beer delete(Integer id) {
        Beer beer = this.get(id);
        this.beerRepository.deleteById(id);
        return beer;
    }
}
