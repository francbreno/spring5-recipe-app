package guru.springframework;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class RecipesBootstrap implements CommandLineRunner {

	private final RecipeRepository recipeRepo;
	private final UnitOfMeasureRepository unitOfMeasureRepo;
	private final CategoryRepository categoryRepo;
	
	@Autowired
	private RecipesBootstrap(RecipeRepository recipeRepo, UnitOfMeasureRepository unitOfMeasureRepo,
			CategoryRepository categoryRepo) {
		super();
		this.recipeRepo = recipeRepo;
		this.unitOfMeasureRepo = unitOfMeasureRepo;
		this.categoryRepo = categoryRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		recipeRepo.saveAll(this.getRecipes());
	}

	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		
		UnitOfMeasure teaspoonUoM = getUnitByDescription("Teaspoon");
		UnitOfMeasure tablespoonUoM = getUnitByDescription("Tablespoon");
		UnitOfMeasure cupUoM = getUnitByDescription("Cup");
		UnitOfMeasure pinchUoM = getUnitByDescription("Pinch");
		UnitOfMeasure ounceUoM = getUnitByDescription("Ounce");
		UnitOfMeasure eachUoM = getUnitByDescription("Each");
		UnitOfMeasure dashUoM = getUnitByDescription("Dash");
		UnitOfMeasure pintUoM = getUnitByDescription("Pint");
		
		Category american = getCategoryByDescription("American");
		Category mexican = getCategoryByDescription("Mexican");
	
		Recipe guacaRecipe = new Recipe(
				"Perfect Guacamole",
				10,
				0,
				2,
				"My Recipes book",
				"http://www.recipes.com",
				"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
						+ "\n"
						+ "Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
						+ "\n"
						+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" 
						+ "\n" 
						+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" 
						+ "\n" 
						+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste."
						+ "\n"
						+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" 
						+ "\n" 
						+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.",
				Difficulty.MODERATE);
		
		Notes guacaNotes =
			new Notes(
				"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" + 
				"\n" + 
				"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" + 
				"\n" + 
				"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" + 
				"\n" + 
				"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" + 
				"\n" + 
				"For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
		
		guacaRecipe.setNotes(guacaNotes);
		guacaRecipe.addIngredient(new Ingredient("ripe avocados", BigDecimal.valueOf(2), eachUoM));
		guacaRecipe.addIngredient(new Ingredient("Kosher salt", BigDecimal.valueOf(.5), teaspoonUoM));
		guacaRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", BigDecimal.valueOf(1), tablespoonUoM));
		guacaRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2), tablespoonUoM));
		guacaRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(2), eachUoM));
		guacaRecipe.addIngredient(new Ingredient("cilantro", BigDecimal.valueOf(2), tablespoonUoM));
		guacaRecipe.addIngredient(new Ingredient("freshly grated black pepper", BigDecimal.valueOf(2), dashUoM));
		guacaRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(0.5), eachUoM));
		
		guacaRecipe.getCategories().add(american);
		guacaRecipe.getCategories().add(mexican);
		
		recipes.add(guacaRecipe);
		
		return recipes;
	}
	
	private UnitOfMeasure getUnitByDescription(String description) {
		return unitOfMeasureRepo
				.findByDescription(description)
				.orElseThrow(() -> new RuntimeException("'" + description + "' not found"));
	}
	
	private Category getCategoryByDescription(String description) {
		return categoryRepo
				.findByDescription(description)
				.orElseThrow(() -> new RuntimeException("'" + description + "' not found"));
	}
}
