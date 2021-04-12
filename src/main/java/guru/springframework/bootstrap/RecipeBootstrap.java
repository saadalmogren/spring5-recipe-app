package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RecipeBootstrap implements CommandLineRunner {
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasure;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;


    public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasure, CategoryRepository categoryRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasure = unitOfMeasure;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        BigDecimal bigDecimal1 = new BigDecimal(0.25);
        BigDecimal bigDecimal2 = new BigDecimal(0.5);

        Ingredient salt = new Ingredient();
        salt.setDescription("Salt");
        salt.setAmount(bigDecimal1);
        salt.setUom(unitOfMeasure.findByDescription("Tablespoon").get());

        Ingredient pepper = new Ingredient();
        pepper.setDescription("Pepper");
        pepper.setAmount(bigDecimal2);
        pepper.setUom(unitOfMeasure.findByDescription("Tablespoon").get());

        Recipe chickenTacos = new Recipe();
        chickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        chickenTacos.setPrepTime(20);
        chickenTacos.setCookTime(15);
        chickenTacos.setServings(6);
        chickenTacos.setDifficulty(Difficulty.MODERATE);
        chickenTacos.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat." +
                "2. Make the marinade and coat the chicken. " +
                "3. Grill the chicken. " +
                "4. Warm the tortillas" +
                "5. Assemble the tacos");
        chickenTacos.setSource("Simply Recipe");
        chickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTacos.getIngredients().add(salt);

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("How to Make Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setDirections("1. Cut the avocado, remove flesh." +
                "2. Mash with a fork. " +
                "3. Add salt, lime juice, and the rest. " +
                "4. Serve");
        perfectGuacamole.setSource("Simply Recipe");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.getIngredients().add(pepper);

        salt.setRecipe(chickenTacos);
        pepper.setRecipe(perfectGuacamole);
        recipeRepository.save(chickenTacos);
        recipeRepository.save(perfectGuacamole);
        System.out.println("Loaded");
    }
}
