package guru.springframework.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private final String description;
	private final BigDecimal amount;
	
	@OneToOne
	private final UnitOfMeasure uom;
	
	@ManyToOne
	@Setter
	private Recipe recipe;
}
