package yass.jouao.labx.services;

import java.util.Optional;

import yass.jouao.labx.entities.AnalysisType;

public interface IAnalysisTypeService {
	Optional<AnalysisType> getAnalysisTypeByIdService(Long id);

	AnalysisType addAnalysisType(AnalysisType at);

	AnalysisType updateAnalysisType(AnalysisType at);

	void deleteAnalysisType(Long id);
}
