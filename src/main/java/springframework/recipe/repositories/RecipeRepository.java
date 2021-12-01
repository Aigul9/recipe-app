package springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springframework.recipe.domain.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
