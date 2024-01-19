package yass.jouao.labx.DTOs;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import yass.jouao.labx.enums.AnalysisStatus;

@Data
public class AnalysisDTO {
	public interface saveAnalysis {
	}

	public interface viewAnalysis {
	}

	public interface updateAnalysis {
	}

	@JsonView({ viewAnalysis.class })
	private long id;
	@JsonView({ viewAnalysis.class, saveAnalysis.class, updateAnalysis.class })
	private LocalDateTime startDate;
	@JsonView({ viewAnalysis.class, saveAnalysis.class, updateAnalysis.class })
	private LocalDateTime endDate;
	@JsonView({ viewAnalysis.class, saveAnalysis.class, updateAnalysis.class })
	private Boolean resultAnalysis;
	@JsonView({ viewAnalysis.class, saveAnalysis.class, updateAnalysis.class })
	private AnalysisStatus status;
	// USE ONLY IN ADD
	@JsonView({ saveAnalysis.class })
	private Long IdSample;
	@JsonView({ saveAnalysis.class })
	private Long IdPatient;
	@JsonView({ saveAnalysis.class })
	private Long IdUserLab;
	@JsonView({ saveAnalysis.class })
	private Long IdAnalysisType;

}
