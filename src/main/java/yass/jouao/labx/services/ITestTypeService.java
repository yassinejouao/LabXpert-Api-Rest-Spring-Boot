package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.TestType;

public interface ITestTypeService {
	List<TestType> getAllTestTypesService();

	List<TestType> getAllTestTypesByAnalysis(Analysis a);

	Optional<TestType> getTestTypeByIdService(Long id);

	TestType addTestTypeService(TestType tt);

	TestType updateTestTypeService(TestType tt);

	void deleteTestTypeService(Long id);

}
