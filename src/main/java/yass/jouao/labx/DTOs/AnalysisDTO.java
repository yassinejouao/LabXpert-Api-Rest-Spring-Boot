package yass.jouao.labx.DTOs;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import yass.jouao.labx.enums.AnalysisStatus;

@Data
public class AnalysisDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public interface saveAnalysis {
	}

	public interface viewAnalysis {
	}

	public interface updateAnalysis {
	}

	public interface result {
	}

	@JsonView({ viewAnalysis.class, result.class })
	private long id;
	@JsonView({ viewAnalysis.class, saveAnalysis.class, updateAnalysis.class })
	private LocalDateTime startDate;
	@JsonView({ viewAnalysis.class, saveAnalysis.class, updateAnalysis.class })
	private LocalDateTime endDate;
	@JsonView({ viewAnalysis.class, saveAnalysis.class, updateAnalysis.class, result.class })
	private Boolean resultAnalysis;
	@JsonView({ viewAnalysis.class, saveAnalysis.class, updateAnalysis.class, result.class })
	private AnalysisStatus status;
	@JsonView({ viewAnalysis.class })
	private PatientDTO patientDTO;
	@JsonView({ result.class })
	private List<TestDTO> testsDTO;
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
