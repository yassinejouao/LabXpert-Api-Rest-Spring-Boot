package yass.jouao.labx.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class AnalysisType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String name;
	@OneToMany(mappedBy = "analysisType", fetch = FetchType.LAZY)
	private Collection<Analysis> analysis;
	@OneToMany(mappedBy = "analysisType", fetch = FetchType.LAZY)
	private Collection<TestType> testTypes;
}
