package yass.jouao.labx.DTOs;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import yass.jouao.labx.DTOs.AnalysisDTO.result;
import yass.jouao.labx.enums.ResultTest;
import yass.jouao.labx.enums.TestStatus;

@Data
public class TestDTO {

	private long id;
	@JsonView({ result.class })
	private Double resultTest;
	@JsonView({ result.class })
	private ResultTest result;
	@JsonView({ result.class })
	private TestStatus status;

}
