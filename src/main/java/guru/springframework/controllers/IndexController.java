package guru.springframework.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping({"", "/", "/index"})
@Slf4j
public class IndexController {
	
	private final RecipeService recipeService;
	
	@Autowired
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
		log.info("Controller instantiated");
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		
		return "index";
	}
}
