package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.entities.Analysis;

public interface IAnalysisService {
	List<Analysis> getAllAnalysisService();

	Optional<Analysis> getAnalysisByIdService(Long id);

	Analysis addAnalysisService(Analysis a);

	Analysis updateAnalysisService(Analysis a);

	void addTestTypesToTestByAnalysis(Analysis a);
}
