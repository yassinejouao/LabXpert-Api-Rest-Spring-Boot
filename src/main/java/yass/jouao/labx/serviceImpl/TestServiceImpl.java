package yass.jouao.labx.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Test;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.ITestRepository;
import yass.jouao.labx.services.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	private ITestRepository testRepository;

	@Override
	@Transactional
	public List<Test> getAllTestService() {
		return testRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<Test> getTestByIdService(Long id) {
		return testRepository.findById(id);
	}

	@Override
	@Transactional
	public Test addTestService(Test t) {
		return testRepository.save(t);
	}

	@Override
	@Transactional
	public Test updateTestService(Test t) {
		if (testRepository.existsById(t.getId())) {
			return testRepository.save(t);
		} else {
			throw new NotFoundException("you can't update unexist Test");
		}
	}

}
