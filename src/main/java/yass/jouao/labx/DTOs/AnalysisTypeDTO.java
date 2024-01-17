package yass.jouao.labx.DTOs;

import java.util.Collection;

import lombok.Data;

@Data
public class AnalysisTypeDTO {

	private long id;
	private String name;
	private Collection<AnalysisDTO> analysis;
	private Collection<TestTypeDTO> testTypes;

}
