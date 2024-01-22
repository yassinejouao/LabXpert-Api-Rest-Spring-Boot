package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;
import yass.jouao.labx.services.IPatientService;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements IPatientService {


	private PatientMapper patientMapper;
	private IPatientRepository patientRepository;

	@Override
	@Transactional
	public PatientDTO getPatientByIdService(Long id) throws NotFoundException {
		Optional<Patient> optionalPatient = patientRepository.findById(id);
		if (optionalPatient.isPresent()) {
			PatientDTO patientDTO = patientMapper.fromPatientToPatientDTO(optionalPatient.get());
			return patientDTO;
		} else {
			throw new NotFoundException("Patient not found");
		}
	}

	@Override
	@Transactional
	public PatientDTO addPatientService(PatientDTO p) {
		Patient patient = patientMapper.fromPatientDTOToPatient(p);
		PatientDTO patientDTO = patientMapper.fromPatientToPatientDTO(patientRepository.save(patient));
		return patientDTO;
	}

	@Override
	@Transactional
	public void deletePatientService(Long id) throws NotFoundException {
		if (patientRepository.existsById(id)) {
			patientRepository.deleteById(id);
		} else {
			throw new NotFoundException("Patient not found");
		}

	}

	@Override
	public List<PatientDTO> getAllPatientsService() {
		List<Patient> patients = patientRepository.findAll();
		List<PatientDTO> patientDTOs = patients.stream().map(patient -> patientMapper.fromPatientToPatientDTO(patient))
				.collect(Collectors.toList());
		return patientDTOs;
	}

	@Override
	@Transactional
	public PatientDTO updatePatientService(Long patientId, PatientDTO p)
			throws NotFoundException, IllegalAccessException {
		Patient patientToUpdate = patientMapper.fromPatientDTOToPatient(getPatientByIdService(patientId));
		p.setId(patientId);
		Patient patientNewData = patientMapper.fromPatientDTOToPatient(p);
		updateNonNullFields(patientToUpdate, patientNewData);
		PatientDTO patientDTO = patientMapper.fromPatientToPatientDTO(patientRepository.save(patientToUpdate));
		return patientDTO;
	}

	public void updateNonNullFields(Patient existingEntity, Patient updatedEntity) throws IllegalAccessException {
		Field[] fields = Patient.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object updatedValue = field.get(updatedEntity);
			if (updatedValue != null) {
				field.set(existingEntity, updatedValue);
			}
		}
	}

}
