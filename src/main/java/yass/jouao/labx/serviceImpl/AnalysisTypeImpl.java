package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.AnalysisTypeDTO;
import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisTypeRepository;
import yass.jouao.labx.serviceImpl.Mappers.AnalysisTypeMapper;
import yass.jouao.labx.services.IAnalysisTypeService;

@Service
public class AnalysisTypeImpl implements IAnalysisTypeService {

	@Autowired
	private AnalysisTypeMapper analysisTypeMapper;
	@Autowired
	private IAnalysisTypeRepository analysisTypeRepository;

	@Override
	public AnalysisTypeDTO getAnalysisTypeByIdService(Long id) throws NotFoundException {
		Optional<AnalysisType> optional = analysisTypeRepository.findById(id);
		if (optional.isPresent()) {
			AnalysisTypeDTO analysisTypeDTO = analysisTypeMapper.fromAnalysisTypeToAnalysisTypeDTO(optional.get());
			return analysisTypeDTO;
		} else {
			throw new NotFoundException("Analysis not found");
		}

	}

	@Override
	public AnalysisTypeDTO addAnalysisTypeService(AnalysisTypeDTO at) {
		AnalysisType analysisType = analysisTypeMapper.fromAnalysisTypeDTOToAnalysisType(at);
		AnalysisTypeDTO analysisTypeDTO = analysisTypeMapper
				.fromAnalysisTypeToAnalysisTypeDTO(analysisTypeRepository.save(analysisType));
		return analysisTypeDTO;
	}

	@Override
	public AnalysisTypeDTO updateAnalysisTypeService(Long Id, AnalysisTypeDTO at)
			throws NotFoundException, IllegalAccessException {
		AnalysisType analysisTypeToUpdate = analysisTypeMapper
				.fromAnalysisTypeDTOToAnalysisType(getAnalysisTypeByIdService(Id));
		at.setId(Id);
		AnalysisType analysisTypeNewData = analysisTypeMapper.fromAnalysisTypeDTOToAnalysisType(at);
		updateNonNullFields(analysisTypeToUpdate, analysisTypeNewData);
		AnalysisTypeDTO analysisTypeDTO = analysisTypeMapper
				.fromAnalysisTypeToAnalysisTypeDTO(analysisTypeRepository.save(analysisTypeToUpdate));
		return analysisTypeDTO;
	}

	@Override
	public void deleteAnalysisTypeService(Long id) throws NotFoundException {
		if (analysisTypeRepository.existsById(id)) {
			analysisTypeRepository.deleteById(id);
		} else {
			throw new NotFoundException("Analysis not found");
		}

	}

	private void updateNonNullFields(AnalysisType existingEntity, AnalysisType updatedEntity)
			throws IllegalAccessException {
		Field[] fields = AnalysisType.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object updatedValue = field.get(updatedEntity);
			if (updatedValue != null) {
				field.set(existingEntity, updatedValue);
			}
		}
	}

}
