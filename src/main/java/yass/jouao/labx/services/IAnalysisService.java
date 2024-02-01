package yass.jouao.labx.services;

import java.time.LocalDateTime;
import java.util.List;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.DTOs.AnalysisResultDTO;
import yass.jouao.labx.enums.IntervalRapport;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IAnalysisService {

	List<AnalysisDTO> getAllAnalysisInProgress() throws NotFoundException;

	List<AnalysisDTO> getAllAnalysis() throws NotFoundException;

	AnalysisDTO getResultByAnalysisId(Long id) throws NotFoundException;

	List<AnalysisDTO> getAnalysisByIdPatientService(Long id) throws NotFoundException;

	AnalysisDTO addAnalysisService(AnalysisDTO a) throws NotFoundException;

	AnalysisDTO updateAnalysisService(AnalysisDTO a) throws NotFoundException, IllegalAccessException;

	List<AnalysisResultDTO> getAnalysisRapport(Long analysisTypeId, LocalDateTime startDate, LocalDateTime endDate,
			IntervalRapport interfRapport);

}
