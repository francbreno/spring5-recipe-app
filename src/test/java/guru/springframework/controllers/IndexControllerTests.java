package guru.springframework.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

public class IndexControllerTests {

	IndexController indexController;

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
	}

	@Test
	public void testMockMvc() throws Exception {
		MockMvcBuilders.standaloneSetup(indexController)
			.build()
			.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}

	@Test
	public void testIndex() {
		Set<Recipe> recipes = new HashSet<>();
		Recipe recipe1 = new Recipe("", 0, 0, 0, "", "", "", null);
		recipe1.setId(1L);
		Recipe recipe2 = new Recipe("", 0, 0, 0, "", "", "", null);
		recipe2.setId(2L);
		recipes.add(recipe1);
		recipes.add(recipe2);

		when(recipeService.getRecipes()).thenReturn(recipes);

		@SuppressWarnings("unchecked")
		// obtaining an argumentCaptor for the set of recipes
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass((Set.class));

		assertThat(indexController.index(model)).isEqualTo("index");
		verify(recipeService, only()).getRecipes();
		// captures the value passed as second argument to addAttribute from the model
		// object
		verify(model, only()).addAttribute(eq("recipes"), argumentCaptor.capture());
		// and checks if the captured value is the expected one
		assertThat(argumentCaptor.getValue().size()).isEqualTo(2);
	}

}
