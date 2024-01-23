package yass.jouao.labx.DTOs;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.DTOs.AnalysisDTO.result;
import yass.jouao.labx.enums.ResultTest;
import yass.jouao.labx.enums.TestStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {

	private long id;
	@JsonView({ result.class })
	private Double resultTest;
	@JsonView({ result.class })
	private ResultTest result;
	@JsonView({ result.class })
	private TestStatus status;
	// USE ONLY IN ADD
	@JsonView({ result.class })
	private Double min;
	@JsonView({ result.class })
	private Double max;
	@JsonView({ result.class })
	private String nameTest;

}
