package yass.jouao.labx.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisResultDTO {
	private LocalDate groupedDate;
	private int analysisCount;
}
