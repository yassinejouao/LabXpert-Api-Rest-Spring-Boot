package yass.jouao.labx.services;

import java.util.List;

import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestReagent;

public interface ITestReagentService {
	List<TestReagent> getTestReagentsByTestService(Test t);

	TestReagent addTestReagentService(TestReagent tr);

	TestReagent updateTestReagentService(TestReagent tr);

	void deleteTestReagentService(Long id);

	void deleteByTestService(Test t);
}
