package yass.jouao.labx.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.services.IPatientService;

public class PatientServiceImpl implements IPatientService {

	@Autowired
	private IPatientRepository patientRepository;

	@Override
	public Optional<Patient> getPatientByIdService(Long id) {
		return patientRepository.findById(id);
	}

	@Override
	public Patient addPatientService(Patient p) {
		return patientRepository.save(p);
	}

	@Override
	public Patient updatePatientService(Patient p) {
		if (patientRepository.existsById(p.getId())) {
			return patientRepository.save(p);
		} else {
			throw new NotFoundException("you can't update unexist patient");
		}
	}

	@Override
	public void deletePatientService(Long id) {
		if (patientRepository.existsById(id)) {
			patientRepository.deleteById(id);
		} else {
			throw new NotFoundException("Patient not found");
		}

	}

}
