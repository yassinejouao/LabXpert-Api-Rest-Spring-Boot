package yass.jouao.labx.services;

import java.util.List;

import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IPatientService {
	List<Patient> getAllPatientsService();

	Patient getPatientByIdService(Long id) throws NotFoundException;

	Patient addPatientService(Patient p) throws NotFoundException;

	Patient updatePatientService(Patient p) throws NotFoundException;

	void deletePatientService(Long id) throws NotFoundException;
}
