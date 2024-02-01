package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.SampleDTO;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.entities.Sample;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.repositories.ISampleRepository;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;
import yass.jouao.labx.serviceImpl.Mappers.SampleMapper;
import yass.jouao.labx.services.ISampleService;

@Service
public class SampleServiceImpl implements ISampleService {

	@Autowired
	private SampleMapper sampleMapper;
	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private IPatientRepository patientRepository;
	@Autowired
	private ISampleRepository sampleRepository;

	@Override
	public SampleDTO getSampleDTOByIdService(Long id) throws NotFoundException {
		Optional<Sample> optionalSample = sampleRepository.findById(id);
		if (optionalSample.isPresent()) {
			SampleDTO sampleDTO = sampleMapper.fromSampleToSampleDTO(optionalSample.get());
			return sampleDTO;
		} else {
			throw new NotFoundException("Patient not found");
		}
	}

	@Override
	public List<SampleDTO> getAllSamplesService() {
		List<Sample> samples = sampleRepository.findAll();
		List<SampleDTO> sampleDTOs = samples.stream().map(sample -> {
			Patient patient = sample.getPatient();
			SampleDTO sampleDTO = sampleMapper.fromSampleToSampleDTO(sample);
			sampleDTO.setPatientDTO(patientMapper.fromPatientToPatientDTO(patient));
			return sampleDTO;
		}).collect(Collectors.toList());
		return sampleDTOs;
	}

	@Override
	public Sample getSampleByIdService(Long id) throws NotFoundException {
		Optional<Sample> optionalSample = sampleRepository.findById(id);
		if (optionalSample.isPresent()) {
			Sample sample = optionalSample.get();
			return sample;
		} else {
			throw new NotFoundException("Patient not found");
		}
	}

	@Override
	public SampleDTO addSampleService(SampleDTO s) throws NotFoundException {
		Sample sample = sampleMapper.fromSampleDTOToSample(s);
		Optional<Patient> optionalPatient = patientRepository.findById(s.getIdPatient());
		if (optionalPatient.isPresent()) {
			sample.setPatient(optionalPatient.get());
			return sampleMapper.fromSampleToSampleDTO(sampleRepository.save(sample));

		} else {
			throw new NotFoundException("Patient not found");
		}
	}

	@Override
	public List<SampleDTO> getSamplesByIdPatient(Long id) throws NotFoundException {
		Optional<Patient> optionalPatient = patientRepository.findById(id);
		if (optionalPatient.isPresent()) {
			Collection<Sample> samples = optionalPatient.get().getSamples();
			List<SampleDTO> samplesDTO = samples.stream().map(sample -> sampleMapper.fromSampleToSampleDTO(sample))
					.collect(Collectors.toList());
			return samplesDTO;
		} else {
			throw new NotFoundException("Patient not found");
		}
	}

	@Override
	public SampleDTO updateSampleService(Long id, SampleDTO s) throws NotFoundException, IllegalAccessException {
		Sample sampleToUpdate = getSampleByIdService(id);
		s.setId(id);
		Sample sampleNewData = sampleMapper.fromSampleDTOToSample(s);
		updateNonNullFields(sampleToUpdate, sampleNewData);
		Optional<Patient> optionalPatient = patientRepository.findById(sampleToUpdate.getPatient().getId());
		sampleToUpdate.setPatient(optionalPatient.get());
		SampleDTO sampleDTO = sampleMapper.fromSampleToSampleDTO(sampleRepository.save(sampleToUpdate));
		return sampleDTO;
	}

	private void updateNonNullFields(Sample existingEntity, Sample updatedEntity) throws IllegalAccessException {
		Field[] fields = Sample.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object updatedValue = field.get(updatedEntity);
			if (updatedValue != null) {
				field.set(existingEntity, updatedValue);
			}
		}
	}

}
