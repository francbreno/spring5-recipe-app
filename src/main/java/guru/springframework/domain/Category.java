package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Category extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private final String description;
	
	@ManyToMany(mappedBy="categories")
	private Set<Recipe> recipes = new HashSet<>();

	public void addRecipe(Recipe recipe) {
		if(recipe != null && this.recipes.add(recipe)) {
			recipe.addCategory(this);
		}
	}
}
