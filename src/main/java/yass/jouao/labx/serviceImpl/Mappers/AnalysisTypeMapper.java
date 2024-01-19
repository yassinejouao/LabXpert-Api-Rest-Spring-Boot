package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.AnalysisTypeDTO;
import yass.jouao.labx.entities.AnalysisType;

@Service
public class AnalysisTypeMapper {
	public AnalysisTypeDTO fromAnalysisTypeToAnalysisTypeDTO(AnalysisType analysisType) {
		AnalysisTypeDTO analysisTypeDTO = new AnalysisTypeDTO();
		BeanUtils.copyProperties(analysisType, analysisTypeDTO);
		return analysisTypeDTO;
	}

	public AnalysisType fromAnalysisTypeDTOToAnalysisType(AnalysisTypeDTO analysisTypeDTO) {
		AnalysisType analysisType = new AnalysisType();
		BeanUtils.copyProperties(analysisTypeDTO, analysisType);
		return analysisType;
	}
}
