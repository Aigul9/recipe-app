package springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springframework.recipe.commands.IngredientCommand;
import springframework.recipe.commands.RecipeCommand;
import springframework.recipe.commands.UnitOfMeasureCommand;
import springframework.recipe.services.IngredientService;
import springframework.recipe.services.RecipeService;
import springframework.recipe.services.UnitOfMeasureService;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Ingredient list for recipe #" + recipeId);
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredients(@PathVariable String recipeId,
                                  @PathVariable String ingredientId,
                                  Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUom());

        return "recipe/ingredient/ingredientForm";
    }


    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId,
                                   @PathVariable String ingredientId,
                                   Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId), Long.valueOf(ingredientId)
        ));
        model.addAttribute("uomList", unitOfMeasureService.listAllUom());

        return "recipe/ingredient/ingredientForm";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        log.debug("Saved recipe id: " + savedCommand.getRecipeId());
        log.debug("Saved ingredient id: " + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String ingredientId){
        log.debug("Deleting ingredient id: " + ingredientId);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
