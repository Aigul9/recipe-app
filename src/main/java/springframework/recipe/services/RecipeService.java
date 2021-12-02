package springframework.recipe.services;

import org.springframework.stereotype.Service;
import springframework.recipe.domain.Recipe;

import java.util.Set;

@Service
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
