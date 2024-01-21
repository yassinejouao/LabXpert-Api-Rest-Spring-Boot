package yass.jouao.labx.entities;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reagent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String name;
	private String description;
	@Column(nullable = false)
	private Long stock;
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false)
	private LocalDate expirationDate;
	@ManyToOne
	private Fournisseur fournisseur;
	@OneToMany(mappedBy = "reagent", fetch = FetchType.LAZY)
	private Collection<TestReagent> testReagents;
}
