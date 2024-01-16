package yass.jouao.labx.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.services.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private IPatientRepository patientRepository;

	@Override
	@Transactional
	public Optional<Patient> getPatientByIdService(Long id) {
		return patientRepository.findById(id);
	}

	@Override
	@Transactional
	public Patient addPatientService(Patient p) {
		return patientRepository.save(p);
	}

	@Override
	@Transactional
	public Patient updatePatientService(Patient p) {
		if (patientRepository.existsById(p.getId())) {
			return patientRepository.save(p);
		} else {
			throw new NotFoundException("you can't update unexist patient");
		}
	}

	@Override
	@Transactional
	public void deletePatientService(Long id) {
		if (patientRepository.existsById(id)) {
			patientRepository.deleteById(id);
		} else {
			throw new NotFoundException("Patient not found");
		}

	}

}
