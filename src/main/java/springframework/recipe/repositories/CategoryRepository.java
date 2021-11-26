package springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
