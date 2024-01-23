package yass.jouao.labx.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.ReagentDTO;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.Reagent;
import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestReagent;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.enums.AnalysisStatus;
import yass.jouao.labx.enums.ResultTest;
import yass.jouao.labx.enums.TestStatus;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisRepository;
import yass.jouao.labx.repositories.IReagentRepository;
import yass.jouao.labx.repositories.ITestReagentRepository;
import yass.jouao.labx.repositories.ITestRepository;
import yass.jouao.labx.repositories.ITestTypeRepository;
import yass.jouao.labx.services.ITestService;

@Service
public class TestServiceImpl implements ITestService {
	@Autowired
	private ITestRepository testRepository;
	@Autowired
	private ITestTypeRepository testTypeRepository;
	@Autowired
	private IReagentRepository reagentRepository;
	@Autowired
	private ITestReagentRepository testReagentRepository;
	@Autowired
	private ReagentServiceImpl reagentServiceImpl;
	@Autowired
	private IAnalysisRepository analysisRepository;

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
	public boolean addReagentToTest(Long idTest, Long idReagent, Long quantity)
			throws NotFoundException, IllegalAccessException {
		Optional<Test> testOptional = testRepository.findById(idTest);
		Optional<Reagent> reagentOptional = reagentRepository.findById(idReagent);
		if (testOptional.isPresent() && reagentOptional.isPresent()) {
			if (quantity <= reagentOptional.get().getStock()) {
				TestReagent testReagent = new TestReagent();
				testReagent.setReagent(reagentOptional.get());
				testReagent.setTest(testOptional.get());
				testReagent.setQuantity(quantity);
				testReagentRepository.save(testReagent);
				ReagentDTO reagentDTO = new ReagentDTO();
				reagentDTO.setStock(reagentOptional.get().getStock() - quantity);
				reagentServiceImpl.updateReagentService(idReagent, reagentDTO);
				return true;
			} else {
				throw new NotFoundException("Insufficient Stock");
			}
		} else {
			throw new NotFoundException("Test or Reagent not found");
		}
	}

	@Override
	public boolean updateResultTest(Long idTest, Double result) throws NotFoundException {
		Optional<Test> testOptional = testRepository.findById(idTest);
		if (testOptional.isPresent()) {
			Long idtestType = testOptional.get().getTestType().getId();
			Optional<TestType> testType = testTypeRepository.findById(idtestType);
			System.out.println(testType.get().getName());
			if ((result <= testType.get().getMax()) && (result >= testType.get().getMin())) {
				testOptional.get().setResult(ResultTest.VALID);
			} else {
				testOptional.get().setResult(ResultTest.INVALID);
			}
			testOptional.get().setStatus(TestStatus.FINISHED);
			testOptional.get().setResultTest(result);
			testRepository.save(testOptional.get());
			Analysis analysis = testOptional.get().getAnalysis();
			List<Test> testsByanalysis = testRepository.findByAnalysis(analysis);
			Boolean analysisvalidity = false;
			int i = 0;
			for (Test test : testsByanalysis) {
				if (test.getResult() == ResultTest.WAITING) {
					return true;
				} else if (test.getResult() == ResultTest.INVALID) {
					analysisvalidity = false;
					break;
				} else if (test.getResult() == ResultTest.VALID) {
					analysisvalidity = true;
				}
				i++;
			}
			if (analysisvalidity || (!analysisvalidity && i < testsByanalysis.size())) {
				analysis.setResultAnalysis(analysisvalidity);
				analysis.setStatus(AnalysisStatus.FINISHED);
				analysisRepository.save(analysis);
			}
			return true;
		} else {
			throw new NotFoundException("Test or Reagent not found");
		}

	}

}
