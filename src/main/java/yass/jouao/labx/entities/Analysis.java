package yass.jouao.labx.entities;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
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
import yass.jouao.labx.enums.AnalysisStatus;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analysis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Sample sample;
	@ManyToOne
	private UserLab user;
	@ManyToOne
	private Patient patient;
	@ManyToOne
	private AnalysisType analysisType;
	@Column(nullable = false)
	private LocalDateTime startDate;
	@Column(nullable = false)
	private LocalDateTime endDate;
	private Boolean resultAnalysis;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AnalysisStatus status = AnalysisStatus.WAITING;
	@OneToMany(mappedBy = "analysis", fetch = FetchType.LAZY)
	private Collection<Test> tests;
}
