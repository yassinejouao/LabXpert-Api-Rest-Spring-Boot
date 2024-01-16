package yass.jouao.labx.DTOs;

import java.time.LocalDateTime;
import java.util.Collection;

import lombok.Data;
import yass.jouao.labx.enums.SampleStatus;
import yass.jouao.labx.enums.SampleType;

@Data
public class SampleDTO {
	private long id;
	private SampleType type;
	private SampleStatus status;
	private LocalDateTime date;
	private Collection<AnalysisDTO> analysis;

}
