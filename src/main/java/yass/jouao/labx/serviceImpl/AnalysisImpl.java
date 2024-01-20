package yass.jouao.labx.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.entities.Sample;
import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.enums.AnalysisStatus;
import yass.jouao.labx.enums.ResultTest;
import yass.jouao.labx.enums.TestStatus;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisRepository;
import yass.jouao.labx.repositories.IAnalysisTypeRepository;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.repositories.ISampleRepository;
import yass.jouao.labx.repositories.ITestRepository;
import yass.jouao.labx.repositories.IUserLabRepository;
import yass.jouao.labx.serviceImpl.Mappers.AnalysisMapper;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;
import yass.jouao.labx.services.IAnalysisService;

@Service
public class AnalysisImpl implements IAnalysisService {

	@Autowired
	private AnalysisMapper analysisMapper;
	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private TestTypeServiceImpl testTypeServiceImpl;
	@Autowired
	private IAnalysisRepository analysisRepository;
	@Autowired
	private IPatientRepository patientRepository;
	@Autowired
	private ISampleRepository sampleRepository;
	@Autowired
	private IUserLabRepository userLabRepository;
	@Autowired
	private IAnalysisTypeRepository analysisTypeRepository;
	@Autowired
	private ITestRepository testRepository;

	@Override
	public List<AnalysisDTO> getAllAnalysisService() throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnalysisDTO> getAllAnalysisInProgress() throws NotFoundException {
		List<Analysis> analysisInProgress = analysisRepository.findByStatusIn(AnalysisStatus.ANALYZING,
				AnalysisStatus.WAITING);
		List<AnalysisDTO> analysisDTOs = analysisInProgress.stream().map(aIProgress -> {
			Patient patient = aIProgress.getPatient();
			AnalysisDTO analysisDTO = analysisMapper.fromAnalysisToAnalysisDTO(aIProgress);
			analysisDTO.setPatientDTO(patientMapper.fromPatientToPatientDTO(patient));
			return analysisDTO;

		}).collect(Collectors.toList());
		return analysisDTOs;
	}

	@Override
	public List<AnalysisDTO> getAnalysisByIdPatientService(Long id) throws NotFoundException {
		Optional<Patient> patient = patientRepository.findById(id);
		if (patient.isPresent()) {
			List<AnalysisDTO> analysisDTOs = analysisRepository.findAllByPatient(patient.get()).stream()
					.map(a -> analysisMapper.fromAnalysisToAnalysisDTO(a)).collect(Collectors.toList());
			return analysisDTOs;
		} else {
			throw new NotFoundException("not found patient");
		}
	}

	@Override
	public AnalysisDTO getAnalysisDTOByIdService(Long id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Analysis getAnalysisByIdService(Long id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisDTO addAnalysisService(AnalysisDTO a) throws NotFoundException {
		Analysis analysis = analysisMapper.fromAnalysisDTOToAnalysis(a);
		Optional<Patient> optionalPatient = patientRepository.findById(a.getIdPatient());
		Optional<Sample> optionalSample = sampleRepository.findById(a.getIdSample());
		Optional<UserLab> optionalUser = userLabRepository.findById(a.getIdUserLab());
		Optional<AnalysisType> optionalAnalysisType = analysisTypeRepository.findById(a.getIdAnalysisType());
		if (optionalPatient.isPresent() && optionalSample.isPresent() && optionalUser.isPresent()
				&& optionalAnalysisType.isPresent()) {
			if (!optionalPatient.get().getSamples().contains(optionalSample.get())) {
				throw new NotFoundException("Patient don't have this sample");
			}
			analysis.setPatient(optionalPatient.get());
			analysis.setSample(optionalSample.get());
			analysis.setUser(optionalUser.get());
			analysis.setAnalysisType(optionalAnalysisType.get());
			List<TestType> testTypes = testTypeServiceImpl.getAllTestTypesByAnalysis(analysis);
			Analysis analysis2 = analysisRepository.save(analysis);
			for (TestType testType : testTypes) {
				Test test = new Test();
				test.setTestType(testType);
				test.setAnalysis(analysis);
				test.setResult(null);
				test.setResultTest(null);
				test.setStatus(TestStatus.WAITING);
				test.setResult(ResultTest.WAITING);
				testRepository.save(test);
			}
			return analysisMapper.fromAnalysisToAnalysisDTO(analysis2);
		} else {
			throw new NotFoundException("Patient or sample or user not found");
		}
	}

	@Override
	public AnalysisDTO updateAnalysisService(AnalysisDTO a) throws NotFoundException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	@Transactional
//	public List<Analysis> getAllAnalysisService() {
//		return analysisRepository.findAll();
//	}
//
//	@Override
//	@Transactional
//	public Optional<Analysis> getAnalysisByIdService(Long id) {
//		return analysisRepository.findById(id);
//	}
//
//	@Override
//	@Transactional
//	public Analysis addAnalysisService(Analysis a) {
//		List<TestType> testTypes = testTypeServiceImpl.getAllTestTypesByAnalysis(a);
//		for (TestType testType : testTypes) {
//			Test test = new Test();
//			test.setTestType(testType);
//			test.setAnalysis(a);
//			test.setStatus(TestStatus.WAITING);
//			testServiceImpl.addTestService(test);
//		}
//		return analysisRepository.save(a);
//	}
//
//	@Override
//	@Transactional
//	public Analysis updateAnalysisService(Analysis a) throws NotFoundException {
//		if (analysisRepository.existsById(a.getId())) {
//			return analysisRepository.save(a);
//		} else {
//			throw new NotFoundException("you can't update unexist analysis");
//		}
//
//	}
//
//	@Override
//	@Transactional
//	public void addTestTypesToTestByAnalysis(Analysis a) {
//
//	}

}
