package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.entities.Patient;

@Service
public class PatientMapper {

	public PatientDTO fromPatientToPatientDTO(Patient patient) {
		PatientDTO patientDTO = new PatientDTO();
		BeanUtils.copyProperties(patient, patientDTO);
		return patientDTO;
	}

	public Patient fromPatientDTOToPatient(PatientDTO patientDTO) {
		Patient patient = new Patient();
		BeanUtils.copyProperties(patientDTO, patient);
		return patient;
	}
}
