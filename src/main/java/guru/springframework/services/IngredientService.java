package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long IngredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long id);
}
