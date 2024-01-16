package yass.jouao.labx.DTOs;

import java.time.LocalDate;
import java.util.Collection;

import lombok.Data;
import yass.jouao.labx.enums.Sex;

@Data
public class PatientDTO {
	private long id;
	private String firstname;
	private String lastname;
	private LocalDate dateOfBirth;
	private Sex sex;
	private String phone;
	private Collection<SampleDTO> samples;
	private Collection<AnalysisDTO> analysis;
}
