package guru.springframework.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {
	Category category;


	@Before
	public void setUp() throws Exception {
		category = new Category("Italian");
	}

	@Test
	public void givenACategoryAndISetItsIdWhenGetItsIdThenTheValueShouldBeTheOneISetBefore() {
		Long id = 436L;
		category.setId(id);
		
		assertThat(category.getId()).isEqualTo(id);
	}
}
