package springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
// global thingy across all controllers
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception ex) {
        log.error(ex.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("response", "400 Number Format Exception");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
