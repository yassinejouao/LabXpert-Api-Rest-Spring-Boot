package yass.jouao.labx.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.enums.IntervalRapport;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRapportDTO {

	private Long analysisTypeId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private IntervalRapport intervalRapport;

}
