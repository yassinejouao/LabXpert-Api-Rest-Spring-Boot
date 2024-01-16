package yass.jouao.labx.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisTypeRepository;
import yass.jouao.labx.services.IAnalysisTypeService;

public class AnalysisTypeImpl implements IAnalysisTypeService {

	@Autowired
	private IAnalysisTypeRepository analysisTypeRepository;

	@Override
	public Optional<AnalysisType> getAnalysisTypeByIdService(Long id) {
		return analysisTypeRepository.findById(id);
	}

	@Override
	public AnalysisType addAnalysisType(AnalysisType at) {
		return analysisTypeRepository.save(at);
	}

	@Override
	public AnalysisType updateAnalysisType(AnalysisType at) {
		if (analysisTypeRepository.existsById(at.getId())) {
			return analysisTypeRepository.save(at);
		} else {
			throw new NotFoundException("you can't update unexist analysis");
		}
	}

	@Override
	public void deleteAnalysisType(Long id) {
		if (analysisTypeRepository.existsById(id)) {
			analysisTypeRepository.deleteById(id);
		} else {
			throw new NotFoundException("Analysis Type not found");
		}
	}

}
