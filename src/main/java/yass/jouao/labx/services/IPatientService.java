package yass.jouao.labx.services;

import java.util.Optional;

import yass.jouao.labx.entities.Patient;

public interface IPatientService {
	Optional<Patient> getPatientByIdService(Long id);

	Patient addPatientService(Patient p);

	Patient updatePatientService(Patient p);

	void deletePatientService(Long id);
}
