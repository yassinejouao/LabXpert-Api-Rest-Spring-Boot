package yass.jouao.labx.DTOs;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.enums.SampleStatus;
import yass.jouao.labx.enums.SampleType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleDTO {

	public interface saveSample {
	}

	public interface viewSample {
	}

	public interface updateStatus {
	}

	@JsonView({ viewSample.class })
	private long id;
	@JsonView({ saveSample.class, viewSample.class })
	private SampleType type;
	@JsonView({ saveSample.class, viewSample.class, updateStatus.class })
	private SampleStatus status;
	@JsonView({ saveSample.class, viewSample.class })
	private LocalDateTime date;
	@JsonView({ viewSample.class })
	private PatientDTO patientDTO;
	// USE IN ADD TO AFFECT USER
	@JsonView({ saveSample.class })
	private long idPatient;
}
