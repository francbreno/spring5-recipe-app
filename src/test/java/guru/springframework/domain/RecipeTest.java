package guru.springframework.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;

public class RecipeTest {

	@Test
	public void givenANewRecipeAndACategoryWhenCategoryAddedToRecipeCategoriesThenRecipeIsAddedToCategoryRecipes() {
		// given
		Recipe r = new Recipe("",0, 0, 0, "", "", "", null);
		r.setId(1L);
		Category c = new Category("");
		c.setId(3L);

		// when
		r.addCategory(c);

		// then
		assertThat(r.getCategories()).contains(c);
		assertThat(c.getRecipes()).contains(r);
	}

	@Test
	public void givenRecipeContainsCategoryWhenCategoryAddedToRecipeNothingChange() {
		// given
		Recipe r = new Recipe("",0, 0, 0, "", "", "", null);
		r.setId(1L);
		Category c = new Category("");
		c.setId(3L);
		Category extraCategory = new Category("");
		extraCategory.setId(3L);
		r.addCategory(c);
		int numCategories = r.getCategories().size();
		int numRecipes = c.getRecipes().size();

		// when
		r.addCategory(extraCategory);
		
		// then
		assertThat(r.getCategories().size()).isEqualTo(numCategories);
		assertThat(c.getRecipes().size()).isEqualTo(numRecipes);
	}

}
