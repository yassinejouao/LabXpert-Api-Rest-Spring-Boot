package yass.jouao.labx.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisTypeRepository;
import yass.jouao.labx.services.IAnalysisTypeService;

@Service
public class AnalysisTypeImpl implements IAnalysisTypeService {

	@Autowired
	private IAnalysisTypeRepository analysisTypeRepository;

	@Override
	@Transactional
	public Optional<AnalysisType> getAnalysisTypeByIdService(Long id) {
		return analysisTypeRepository.findById(id);
	}

	@Override
	@Transactional
	public AnalysisType addAnalysisType(AnalysisType at) {
		return analysisTypeRepository.save(at);
	}

	@Override
	@Transactional
	public AnalysisType updateAnalysisType(AnalysisType at) {
		if (analysisTypeRepository.existsById(at.getId())) {
			return analysisTypeRepository.save(at);
		} else {
			throw new NotFoundException("you can't update unexist analysis");
		}
	}

	@Override
	@Transactional
	public void deleteAnalysisType(Long id) {
		if (analysisTypeRepository.existsById(id)) {
			analysisTypeRepository.deleteById(id);
		} else {
			throw new NotFoundException("Analysis Type not found");
		}
	}

}
