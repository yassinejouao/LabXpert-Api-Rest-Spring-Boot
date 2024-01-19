package yass.jouao.labx.DTOs;

import lombok.Data;

@Data
public class AnalysisTypeDTO {
	public interface viewAnalysisType {
	}

	public interface saveAnalysisType {
	}

	public interface updateAnalysisType {
	}
	@JsonView({viewAnalysisType.class, updateAnalysisType.class})
	private long id;
	@JsonView({viewAnalysisType.class, updateAnalysisType.class})
	private String name;

}
