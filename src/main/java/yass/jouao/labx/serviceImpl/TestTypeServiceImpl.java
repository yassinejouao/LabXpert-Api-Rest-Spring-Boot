package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.DTOs.TestTypeDTO;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.ITestTypeRepository;
import yass.jouao.labx.serviceImpl.Mappers.TestTypeMapper;
//import yass.jouao.labx.serviceImpl.Mappers.AnalysisMapper;
import yass.jouao.labx.services.ITestTypeService;

@Service
public class TestTypeServiceImpl implements ITestTypeService {

	@Autowired
	private ITestTypeRepository testTypeRepository;
	@Autowired
	private TestTypeMapper testTypeMapper;
//	@Autowired
//	private AnalysisMapper analysisMapper;

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
	public TestTypeDTO getTestTypeByIdService(Long id) throws NotFoundException {
		Optional<TestType> optionalTestType = testTypeRepository.findById(id);
		if (optionalTestType.isPresent()) {
			TestTypeDTO testTypeDTO = testTypeMapper.fromTestTypeToTestTypeDTO(optionalTestType.get());
			return testTypeDTO;
		} else {
			throw new NotFoundException("TestType not found");
		}
	}

	@Override
	@Transactional
	public TestTypeDTO addTestTypeService(TestTypeDTO tt) {
		TestType testTypeDto = testTypeMapper.fromTestTypeDTOToTestType(tt);
		TestType testType = testTypeMapper.fromTestTypeDTOToTestType(tt);

		TestTypeDTO testTypeDTO = testTypeMapper.fromTestTypeToTestTypeDTO(testTypeRepository.save(testType));
		return testTypeDTO;
	}

	@Override
	@Transactional
	public TestTypeDTO updateTestTypeService(Long id, TestTypeDTO tt) throws NotFoundException, IllegalAccessException {
		TestType testTypeToUpdate = testTypeMapper.fromTestTypeDTOToTestType(getTestTypeByIdService(id));
		tt.setId(id);
		TestType testTypeNewData = testTypeMapper.fromTestTypeDTOToTestType(tt);
		updateNonNullFields(testTypeToUpdate, testTypeNewData);
		TestTypeDTO testTypeDTO = testTypeMapper.fromTestTypeToTestTypeDTO(testTypeRepository.save(testTypeToUpdate));
		System.out.println("updated");
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
