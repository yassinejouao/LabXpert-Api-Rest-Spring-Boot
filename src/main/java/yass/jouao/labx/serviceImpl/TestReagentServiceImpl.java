package yass.jouao.labx.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestReagent;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.ITestReagentRepository;
import yass.jouao.labx.services.ITestReagentService;

@Service
public class TestReagentServiceImpl implements ITestReagentService {

	private ITestReagentRepository testReagentRepository;

	@Override
	@Transactional
	public List<TestReagent> getTestReagentsByTestService(Test t) {
		return testReagentRepository.findByTest(t);
	}

	@Override
	@Transactional
	public TestReagent addTestReagentService(TestReagent tr) {
		return testReagentRepository.save(tr);

	}

	@Override
	@Transactional
	public TestReagent updateTestReagentService(TestReagent tr) throws NotFoundException {
		if (testReagentRepository.existsById(tr.getId())) {
			return testReagentRepository.save(tr);
		} else {
			throw new NotFoundException("you can't update unexist Test Reagent");
		}
	}

	@Override
	@Transactional
	public void deleteTestReagentService(Long id) throws NotFoundException {
		if (testReagentRepository.existsById(id)) {
			testReagentRepository.deleteById(id);
		} else {
			throw new NotFoundException("Test Reagent not found");
		}

	}

	@Override
	@Transactional
	public void deleteByTestService(Test t) throws NotFoundException {
		if (testReagentRepository.existsByTest(t)) {
			testReagentRepository.deleteByTest(t);
		} else {
			throw new NotFoundException("Test Reagent not found");
		}

	}

}
