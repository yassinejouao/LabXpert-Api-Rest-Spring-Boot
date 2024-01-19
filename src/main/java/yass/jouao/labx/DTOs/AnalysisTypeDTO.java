package yass.jouao.labx.DTOs;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonView;
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
	private Collection<AnalysisDTO> analysis;
	private Collection<TestTypeDTO> testTypes;

}
