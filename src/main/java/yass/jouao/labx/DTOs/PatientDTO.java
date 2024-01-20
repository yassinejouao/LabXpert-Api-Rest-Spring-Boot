package yass.jouao.labx.DTOs;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.DTOs.AnalysisDTO.viewAnalysis;
import yass.jouao.labx.enums.Sex;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {

	public interface viewPatient {
	}

	public interface savePatient {
	}

	public interface updatePatient {
	}

	@JsonView({ viewPatient.class, updatePatient.class })
	private long id;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class, viewAnalysis.class })
	private String firstname;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class, viewAnalysis.class })
	private String lastname;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private LocalDate dateOfBirth;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private Sex sex;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private String phone;
}
