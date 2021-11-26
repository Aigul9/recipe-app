package springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springframework.recipe.repositories.CategoryRepository;
import springframework.recipe.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        System.out.println("TEST");
        categoryRepository.findByDescription("Italian")
                .ifPresent(category -> System.out.println("Category id: " + category.getId()));
        unitOfMeasureRepository.findByDescription("Tablespoon")
                .ifPresent(unitOfMeasure -> System.out.println("Unit of measure: " + unitOfMeasure.getId()));
        return "index";
    }
}
