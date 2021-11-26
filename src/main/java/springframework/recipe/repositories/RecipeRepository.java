package springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
