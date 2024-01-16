package yass.jouao.labx.DTOs;

import java.time.LocalDateTime;
import java.util.Collection;

import lombok.Data;
import yass.jouao.labx.enums.AnalysisStatus;

@Data
public class AnalysisDTO {
	private long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Boolean resultAnalysis;
	private AnalysisStatus status;
	private Collection<TestDTO> tests;

}
