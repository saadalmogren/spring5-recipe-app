package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService=new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

//        if findAll is invoked then return recipeData
        Mockito.when(recipeRepository.findAll()).thenReturn(recipesData);

//        will invoke findAll()
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

//        to verify that the method findAll() is invoked once
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}