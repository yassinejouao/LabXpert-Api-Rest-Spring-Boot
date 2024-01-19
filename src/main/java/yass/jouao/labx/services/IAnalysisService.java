package yass.jouao.labx.services;

import java.util.List;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IAnalysisService {
	List<AnalysisDTO> getAllAnalysisService() throws NotFoundException;;

	AnalysisDTO getAnalysisDTOByIdService(Long id) throws NotFoundException;;

	Analysis getAnalysisByIdService(Long id) throws NotFoundException;;

	AnalysisDTO addAnalysisService(AnalysisDTO a) throws NotFoundException;;

	AnalysisDTO updateAnalysisService(AnalysisDTO a) throws NotFoundException, IllegalAccessException;;

	void addTestTypesToTestByAnalysis(Analysis a);
}
