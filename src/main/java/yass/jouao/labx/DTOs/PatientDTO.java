package yass.jouao.labx.DTOs;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.DTOs.AnalysisDTO.result;
import yass.jouao.labx.DTOs.AnalysisDTO.viewAnalysis;
import yass.jouao.labx.DTOs.SampleDTO.viewSample;
import yass.jouao.labx.enums.Sex;
import yass.jouao.labx.enums.StatusField;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public interface viewPatient {
	}

	public interface savePatient {
	}

	public interface updatePatient {
	}

	@JsonView({ viewPatient.class, updatePatient.class })
	private long id;
	@JsonView({ viewPatient.class, result.class, savePatient.class, viewSample.class, updatePatient.class,
			viewAnalysis.class, viewSample.class })
	private String firstname;
	@JsonView({ viewPatient.class, result.class, savePatient.class, viewSample.class, updatePatient.class,
			viewAnalysis.class, viewSample.class })
	private String lastname;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private LocalDate dateOfBirth;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private Sex sex;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private String phone;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private StatusField status;
}
