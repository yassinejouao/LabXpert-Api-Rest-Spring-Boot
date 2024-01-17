package yass.jouao.labx.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.ITestTypeRepository;
import yass.jouao.labx.services.ITestTypeService;

@Service
public class TestTypeServiceImpl implements ITestTypeService {

	@Autowired
	private ITestTypeRepository testTypeRepository;

	@Override
	@Transactional
	public List<TestType> getAllTestTypesService() {
		return testTypeRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<TestType> getTestTypeByIdService(Long id) {
		return testTypeRepository.findById(id);
	}

	@Override
	@Transactional
	public TestType addTestTypeService(TestType testType) {
		return testTypeRepository.save(testType);
	}

	@Override
	@Transactional
	public TestType updateTestTypeService(TestType tt) throws NotFoundException {
		if (testTypeRepository.existsById(tt.getId())) {
			return testTypeRepository.save(tt);
		} else {
			throw new NotFoundException("you can't update unexist test Type");
		}
	}

	@Override
	@Transactional
	public void deleteTestTypeService(Long id) throws NotFoundException {
		if (testTypeRepository.findById(id).isPresent()) {
			testTypeRepository.deleteById(id);
		} else {
			throw new NotFoundException("TestType not exist");
		}

	}

	@Override
	@Transactional
	public List<TestType> getAllTestTypesByAnalysis(Analysis a) {
		return testTypeRepository.findByAnalysisType(a.getAnalysisType());
	}

}
