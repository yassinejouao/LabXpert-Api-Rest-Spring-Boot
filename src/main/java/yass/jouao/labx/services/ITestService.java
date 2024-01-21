package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.entities.Test;
import yass.jouao.labx.exeptions.NotFoundException;

public interface ITestService {
	List<Test> getAllTestService();

	Optional<Test> getTestByIdService(Long id);

	Test addTestService(Test t);

	boolean updateResultTest(Long idTest, Double result) throws NotFoundException;

	boolean addReagentToTest(Long idTest, Long idReagent, Long quantity)
			throws NotFoundException, IllegalAccessException;

}
