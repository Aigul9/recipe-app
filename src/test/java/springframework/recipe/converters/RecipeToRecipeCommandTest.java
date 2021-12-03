package springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.recipe.commands.RecipeCommand;
import springframework.recipe.domain.Category;
import springframework.recipe.domain.Difficulty;
import springframework.recipe.domain.Ingredient;
import springframework.recipe.domain.Notes;
import springframework.recipe.domain.Recipe;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    RecipeToRecipeCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        recipe.setNotes(notes);

        Category category = new Category();
        Category category2 = new Category();

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        Ingredient ingredient2 = new Ingredient();

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());
    }
}