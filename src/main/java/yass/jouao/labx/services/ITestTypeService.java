package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.DTOs.SampleDTO;
import yass.jouao.labx.DTOs.TestTypeDTO;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.exeptions.NotFoundException;

public interface ITestTypeService {
	List<TestTypeDTO> getAllTestTypesService();
	TestTypeDTO getTestTypeDTOByIdService(Long id) throws NotFoundException;
	List<TestType> getAllTestTypesByAnalysis(Analysis a);

	TestType getTestTypeByIdService(Long id) throws NotFoundException;

	TestTypeDTO addTestTypeService(TestTypeDTO tt) throws NotFoundException ;

	TestTypeDTO updateTestTypeService(Long id, TestTypeDTO tt) throws NotFoundException, IllegalAccessException;

	void deleteTestTypeService(Long id) throws NotFoundException;

}
