package yass.jouao.labx.services;

import java.util.List;

import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IPatientService {
	List<PatientDTO> getAllPatientsService();

	PatientDTO getPatientByIdService(Long id) throws NotFoundException;

	PatientDTO addPatientService(PatientDTO p) throws NotFoundException;

	PatientDTO updatePatientService(Long patientId, PatientDTO p) throws NotFoundException, IllegalAccessException;

	void deletePatientService(Long id) throws NotFoundException;
}
