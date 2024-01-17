package yass.jouao.labx.services;

import java.util.List;

import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestReagent;
import yass.jouao.labx.exeptions.NotFoundException;

public interface ITestReagentService {
	List<TestReagent> getTestReagentsByTestService(Test t);

	TestReagent addTestReagentService(TestReagent tr);

	TestReagent updateTestReagentService(TestReagent tr) throws NotFoundException;

	void deleteTestReagentService(Long id) throws NotFoundException;

	void deleteByTestService(Test t) throws NotFoundException;
}
