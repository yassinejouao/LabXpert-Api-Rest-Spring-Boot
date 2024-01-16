package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.entities.Test;

public interface ITestService {
	List<Test> getAllTestService();

	Optional<Test> getTestByIdService(Long id);

	Test addTestService(Test t);

	Test updateTestService(Test t);

}
