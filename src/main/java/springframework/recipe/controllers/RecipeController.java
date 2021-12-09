package springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import springframework.recipe.commands.RecipeCommand;
import springframework.recipe.exceptions.NotFoundException;
import springframework.recipe.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipe(@PathVariable Long id, Model model) {
        log.debug("Getting recipe page");
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String getRecipeForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        log.debug("Updating: " + id);
        return "recipe/recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        log.debug("Saved: " + savedCommand.getId());
        return String.format("redirect:/recipe/%d/show", savedCommand.getId());
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        log.debug("Deleted: " + id);
        return "redirect:/";
    }

    private ModelAndView genericExceptionMethod(Exception ex, String response) {
        log.error(ex.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("response", response);
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("error");

        return modelAndView;
    }

    //handler takes higher precedence than exception class
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception ex) {
        return genericExceptionMethod(ex, "404 Not Found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception ex) {
        return genericExceptionMethod(ex, "400 Number Format Exception");
    }
}
