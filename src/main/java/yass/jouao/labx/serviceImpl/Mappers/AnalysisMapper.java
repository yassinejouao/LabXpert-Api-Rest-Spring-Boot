package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.entities.Analysis;

@Service
public class AnalysisMapper {
	public AnalysisDTO fromAnalysisToAnalysisDTO(Analysis analysis) {
		AnalysisDTO analysisDTO = new AnalysisDTO();
		BeanUtils.copyProperties(analysis, analysisDTO);
		return analysisDTO;
	}

	public Analysis fromAnalysisDTOToAnalysis(AnalysisDTO analysisDTO) {
		Analysis analysis = new Analysis();
		BeanUtils.copyProperties(analysisDTO, analysis);
		return analysis;
	}

}
