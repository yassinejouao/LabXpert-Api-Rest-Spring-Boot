package yass.jouao.labx.DTOs;

import java.time.LocalDateTime;

import lombok.Data;
import yass.jouao.labx.enums.IntervalRapport;

@Data
public class DataRapportDTO {
	private Long analysisTypeId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private IntervalRapport intervalRapport;

}
