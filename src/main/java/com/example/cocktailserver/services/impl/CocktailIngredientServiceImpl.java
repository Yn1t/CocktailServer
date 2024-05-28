package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.CocktailNotExistsException;
import com.example.cocktailserver.controllers.exceptions.IngredientNotExistsException;
import com.example.cocktailserver.controllers.models.CocktailIngredientDto;
import com.example.cocktailserver.controllers.models.CocktailIngredientRequest;
import com.example.cocktailserver.database.entities.Cocktail;
import com.example.cocktailserver.database.entities.CocktailIngredient;
import com.example.cocktailserver.database.entities.Ingredient;
import com.example.cocktailserver.database.repositories.CocktailIngredientRepository;
import com.example.cocktailserver.database.repositories.CocktailRepository;
import com.example.cocktailserver.database.repositories.IngredientRepository;
import com.example.cocktailserver.services.CocktailIngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CocktailIngredientServiceImpl implements CocktailIngredientService {

    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;
    private final ModelMapper mapper;

    public CocktailIngredientServiceImpl(CocktailRepository cocktailRepository, IngredientRepository ingredientRepository
            ,CocktailIngredientRepository cocktailIngredientRepository, ModelMapper mapper) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailIngredientRepository = cocktailIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

//    @Override
//    public String addIngredientToCocktail(CocktailIngredientRequest request) throws CocktailNotExistsException, IngredientNotExistsException {
//        Optional<Cocktail> existedCocktail = cocktailRepository.findById(request.getCocktailId());
//        Optional<Ingredient> existedIngredient = ingredientRepository.findById(request.getIngredientId());
//        existedCocktail.orElseThrow(CocktailNotExistsException::new);
//        existedIngredient.orElseThrow(IngredientNotExistsException::new);
//
//        CocktailIngredient cocktailIngredient = new CocktailIngredient();
//        cocktailIngredient.setCocktail(existedCocktail.get());
//        cocktailIngredient.setIngredient(existedIngredient.get());
//
//        cocktailIngredientRepository.save(cocktailIngredient);
//        return cocktailIngredient.getId();
//    }

    @Override
    public Map<String, List<CocktailIngredientDto>> addIngredientsToCocktail(List<CocktailIngredientRequest> ingredients) throws CocktailNotExistsException, IngredientNotExistsException {
        Map<String, List<CocktailIngredientDto>> map = new HashMap<>();
        List<CocktailIngredient> newCocktailIngredients = new ArrayList<>();

        Double sum_quantity = 0.0;

        Double all_alcohol = 0.0;

        Double bitterness = 0.0;
        Double spiciness = 0.0;
        Double sourness = 0.0;
        Double sweetness = 0.0;
        Double saltiness = 0.0;

        Double flavour = 0.0;

        Optional<Cocktail> existedCocktail = cocktailRepository.findOptionalByNameAndSubname(
                ingredients.get(0).getCocktailName(),
                ingredients.get(0).getCocktailSubName());
        map.put("ingredients", new ArrayList<>());

        Cocktail cocktail = existedCocktail.orElseThrow(CocktailNotExistsException::new);

        for (CocktailIngredientRequest request : ingredients) {
            Optional<Ingredient> existedIngredient = ingredientRepository.findOptionalByName(request.getIngredientName());
            existedIngredient.orElseThrow(IngredientNotExistsException::new);

            Ingredient ingredient = existedIngredient.get();
            CocktailIngredient cocktailIngredient = new CocktailIngredient();

            cocktailIngredient.setIngredient(ingredient);
            cocktailIngredient.setCocktail(existedCocktail.get());

            newCocktailIngredients.add(cocktailIngredient);

            CocktailIngredientDto dto = new CocktailIngredientDto();
            dto.setIngredientId(ingredient.getId());
            dto.setCocktailId(existedCocktail.get().getId());
            dto.setQuantity(request.getQuantity());
            map.get("ingredients").add(dto);

            bitterness += ingredient.getBitterness() * request.getQuantity();
            spiciness += ingredient.getSpiciness() * request.getQuantity();
            sourness += ingredient.getSaltiness() * request.getQuantity();
            sweetness += ingredient.getSweetness() * request.getQuantity();
            saltiness += ingredient.getSaltiness() * request.getQuantity();

            all_alcohol += request.getQuantity() * ingredient.getStrength();
            sum_quantity += request.getQuantity();
        }

        Double flavourSum = bitterness + spiciness + sourness + sweetness + saltiness;

        cocktail.setBitterness(bitterness/flavourSum);
        cocktail.setSweetness(sweetness/flavourSum);
        cocktail.setSaltiness(saltiness/flavourSum);
        cocktail.setSourness(sourness/flavourSum);
        cocktail.setSpiciness(spiciness/flavourSum);

        cocktail.setStrength(all_alcohol/sum_quantity);

        cocktailIngredientRepository.saveAll(newCocktailIngredients);

        return map;
    }
}
