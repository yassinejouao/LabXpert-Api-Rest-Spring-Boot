package yass.jouao.labx.services;

import java.util.Optional;

import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IAnalysisTypeService {
	Optional<AnalysisType> getAnalysisTypeByIdService(Long id);

	AnalysisType addAnalysisType(AnalysisType at);

	AnalysisType updateAnalysisType(AnalysisType at) throws NotFoundException;

	void deleteAnalysisType(Long id) throws NotFoundException;
}
