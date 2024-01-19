package yass.jouao.labx.DTOs;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class TestTypeDTO {
	public interface viewTestType {
	}

	public interface saveTestType  {
	}
	public interface updateTestType  {
	}
	@JsonView({ viewTestType.class, updateTestType.class})
	private long id;
	@JsonView({ viewTestType.class, saveTestType.class, updateTestType.class })
	private String name;
	@JsonView({ viewTestType.class, saveTestType.class, updateTestType.class })
	private Double max;
	@JsonView({ viewTestType.class, saveTestType.class, updateTestType.class })
	private Double min;
	@JsonView({saveTestType.class})
	private Long idAnalysisType;
}
