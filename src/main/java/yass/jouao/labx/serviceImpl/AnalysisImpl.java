package yass.jouao.labx.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.enums.TestStatus;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisRepository;
import yass.jouao.labx.services.IAnalysisService;

@Service
public class AnalysisImpl implements IAnalysisService {

	@Autowired
	private TestTypeServiceImpl testTypeServiceImpl;
	@Autowired
	private IAnalysisRepository analysisRepository;
	@Autowired
	private TestServiceImpl testServiceImpl;

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisService() {
		return analysisRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<Analysis> getAnalysisByIdService(Long id) {
		return analysisRepository.findById(id);
	}

	@Override
	@Transactional
	public Analysis addAnalysisService(Analysis a) {
		List<TestType> testTypes = testTypeServiceImpl.getAllTestTypesByAnalysis(a);
		for (TestType testType : testTypes) {
			Test test = new Test();
			test.setTestType(testType);
			test.setAnalysis(a);
			test.setStatus(TestStatus.WAITING);
			testServiceImpl.addTestService(test);
		}
		return analysisRepository.save(a);
	}

	@Override
	@Transactional
	public Analysis updateAnalysisService(Analysis a) throws NotFoundException {
		if (analysisRepository.existsById(a.getId())) {
			return analysisRepository.save(a);
		} else {
			throw new NotFoundException("you can't update unexist analysis");
		}

	}

	@Override
	@Transactional
	public void addTestTypesToTestByAnalysis(Analysis a) {

	}

}
