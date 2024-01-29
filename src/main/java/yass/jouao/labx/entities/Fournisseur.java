package yass.jouao.labx.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.enums.StatusField;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fournisseur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String name;
	@Enumerated(EnumType.STRING)
	private StatusField status = StatusField.ACTIVE;
	@OneToMany(mappedBy = "fournisseur", fetch = FetchType.LAZY)
	private Collection<Reagent> reagents;
}
