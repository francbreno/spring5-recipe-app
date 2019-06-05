package guru.springframework.domain;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class UnitOfMeasure extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private final String description;
}
