package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.DTOs.AnalysisTypeDTO;
import yass.jouao.labx.DTOs.SampleDTO;
import yass.jouao.labx.DTOs.TestTypeDTO;
import yass.jouao.labx.entities.*;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisRepository;
import yass.jouao.labx.repositories.IAnalysisTypeRepository;
import yass.jouao.labx.repositories.ITestTypeRepository;
import yass.jouao.labx.serviceImpl.Mappers.TestTypeMapper;
//import yass.jouao.labx.serviceImpl.Mappers.AnalysisMapper;
import yass.jouao.labx.services.ITestTypeService;

@Service
public class TestTypeServiceImpl implements ITestTypeService {

	@Autowired
	private ITestTypeRepository testTypeRepository;
	@Autowired
	private IAnalysisTypeRepository analysisTypeRepository;
	@Autowired
	private TestTypeMapper testTypeMapper;

	@Override
	public TestTypeDTO getTestTypeDTOByIdService(Long id) throws NotFoundException {
		Optional<TestType> optionalTestType = testTypeRepository.findById(id);
		if (optionalTestType.isPresent()) {
			TestTypeDTO testTypeDTO = testTypeMapper.fromTestTypeToTestTypeDTO(optionalTestType.get());
			return testTypeDTO;
		} else {
			throw new NotFoundException("Patient not found");
		}
	}
	@Override
	@Transactional
	public List<TestTypeDTO> getAllTestTypesService() {
		List<TestType> testTypes = testTypeRepository.findAll();
		List<TestTypeDTO> testTypeDTOs = testTypes.stream()
				.map(testType -> testTypeMapper.fromTestTypeToTestTypeDTO(testType))
				.collect(Collectors.toList());
		return testTypeDTOs;
	}

	@Override
	@Transactional
	public TestType getTestTypeByIdService(Long id) throws NotFoundException {
		Optional<TestType> optionalTestType = testTypeRepository.findById(id);
		if (optionalTestType.isPresent()) {
			TestType testType = optionalTestType.get();
			return testType;
		} else {
			throw new NotFoundException("TestType not found");
		}
	}

	@Override
	@Transactional
	public TestTypeDTO addTestTypeService(TestTypeDTO tt)  throws NotFoundException {
		TestType testType = testTypeMapper.fromTestTypeDTOToTestType(tt);
		Optional<AnalysisType> optionalAnalysisType = analysisTypeRepository.findById(tt.getIdAnalysisType());
		if (optionalAnalysisType.isPresent()) {
			testType.setAnalysisType(optionalAnalysisType.get());
			return testTypeMapper.fromTestTypeToTestTypeDTO(testTypeRepository.save(testType));
		} else {
			throw new NotFoundException("AnalyseType not found");
		}
	}

	@Override
	@Transactional
	public TestTypeDTO updateTestTypeService(Long id, TestTypeDTO tt) throws NotFoundException, IllegalAccessException {
		TestType testTypeToUpdate = getTestTypeByIdService(id);
		tt.setId(id);
		TestType testTypeNewData = testTypeMapper.fromTestTypeDTOToTestType(tt);
		updateNonNullFields(testTypeToUpdate, testTypeNewData);
		Optional<AnalysisType> optionalAnalysisType = analysisTypeRepository.findById(testTypeToUpdate.getAnalysisType().getId());
		testTypeToUpdate.setAnalysisType(optionalAnalysisType.get());
		TestTypeDTO testTypeDTO = testTypeMapper.fromTestTypeToTestTypeDTO(testTypeRepository.save(testTypeToUpdate));
		return testTypeDTO;
	}
	private void updateNonNullFields(TestType existingEntity, TestType updatedEntity) throws IllegalAccessException {
		Field[] fields = TestType.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object updatedValue = field.get(updatedEntity);
			if (updatedValue != null) {
				field.set(existingEntity, updatedValue);
			}
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
