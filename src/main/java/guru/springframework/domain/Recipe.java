package guru.springframework.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Recipe extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private final String description;
	private final Integer prepTime;
	private final Integer cookTime;
	private final Integer servings;
	private final String source;
	private final String url;
	
	@Lob
	private final String directions;

	@Enumerated(value = EnumType.STRING) 
	private final Difficulty difficulty;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private List<Ingredient> ingredients = new ArrayList<>();
	
	@Lob
	private Byte[] image;
	
	@OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL)
	private Notes notes;
	
	@ManyToMany
	@JoinTable(
			name = "recipe_category",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public void setNotes(Notes notes) {
		if(!(notes == null || notes.equals(this.notes))) {
			notes.setRecipe(this);
		}
		this.notes = notes;
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
		ingredient.setRecipe(this);
	}
	
	public void removeIngredient(Ingredient ingredient) {
		this.ingredients.remove(ingredient);
		ingredient.setRecipe(null);
	}

	public void addCategory(Category category) {
		if(category != null && this.categories.add(category)) {
			category.addRecipe(this);
		}
	}
}
