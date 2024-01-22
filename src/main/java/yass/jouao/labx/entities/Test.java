package yass.jouao.labx.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import yass.jouao.labx.enums.ResultTest;
import yass.jouao.labx.enums.TestStatus;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Double resultTest;
	@Enumerated(EnumType.STRING)
	private ResultTest result = ResultTest.WAITING;
	@ManyToOne
	private Analysis analysis;
	@ManyToOne
	private TestType testType;
	@Enumerated(EnumType.STRING)
	private TestStatus status = TestStatus.WAITING;
	@OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
	private Collection<TestReagent> testReagents;
}
