package yass.jouao.labx.DTOs;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import yass.jouao.labx.enums.Sex;

@Data
public class PatientDTO {
	public interface viewPatient {
	}

	public interface savePatient {
	}

	@JsonView({ viewPatient.class })
	private long id;
	@JsonView({ viewPatient.class, savePatient.class })
	private String firstname;
	@JsonView({ viewPatient.class, savePatient.class })
	private String lastname;
	@JsonView({ viewPatient.class, savePatient.class })
	private LocalDate dateOfBirth;
	@JsonView({ viewPatient.class, savePatient.class })
	private Sex sex;
	@JsonView({ viewPatient.class, savePatient.class })
	private String phone;
}
