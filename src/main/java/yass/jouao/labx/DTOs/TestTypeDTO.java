package yass.jouao.labx.DTOs;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestTypeDTO {
	public interface viewTestType {
	}

	public interface saveTestType {
	}

	public interface updateTestType {
	}

	@JsonView({ viewTestType.class, updateTestType.class })
	private long id;
	@JsonView({ viewTestType.class, saveTestType.class, updateTestType.class })
	private String name;
	@JsonView({ viewTestType.class, saveTestType.class, updateTestType.class })
	private Double max;
	@JsonView({ viewTestType.class, saveTestType.class, updateTestType.class })
	private Double min;
	@JsonView({ saveTestType.class })
	private Long idAnalysisType;
}
