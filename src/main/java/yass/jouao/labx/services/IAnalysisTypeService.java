package yass.jouao.labx.services;

import yass.jouao.labx.DTOs.AnalysisTypeDTO;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IAnalysisTypeService {
	AnalysisTypeDTO getAnalysisTypeByIdService(Long id) throws NotFoundException;

	AnalysisTypeDTO addAnalysisTypeService(AnalysisTypeDTO at);

	AnalysisTypeDTO updateAnalysisTypeService(Long Id, AnalysisTypeDTO at)
			throws NotFoundException, IllegalAccessException;

	void deleteAnalysisTypeService(Long id) throws NotFoundException;
}
