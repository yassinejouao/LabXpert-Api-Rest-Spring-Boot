package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IAnalysisService {
	List<Analysis> getAllAnalysisService();

	Optional<Analysis> getAnalysisByIdService(Long id);

	Analysis addAnalysisService(Analysis a);

	Analysis updateAnalysisService(Analysis a) throws NotFoundException;

	void addTestTypesToTestByAnalysis(Analysis a);
}