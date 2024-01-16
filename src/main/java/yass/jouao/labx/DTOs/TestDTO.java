package yass.jouao.labx.DTOs;

import java.util.Collection;

import lombok.Data;
import yass.jouao.labx.enums.ResultTest;
import yass.jouao.labx.enums.TestStatus;

@Data
public class TestDTO {
	private long id;
	private Double resultTest;
	private ResultTest result;
	private TestStatus status;
	private Collection<TestReagentDTO> testReagents;
}
