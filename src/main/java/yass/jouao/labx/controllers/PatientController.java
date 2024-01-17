package yass.jouao.labx.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import yass.jouao.labx.DTOs.MessageErrorDTO;
import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.serviceImpl.PatientServiceImpl;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientServiceImpl patientServiceImpl;
	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/all")
	public List<PatientDTO> patients() {
		List<Patient> patients = patientServiceImpl.getAllPatientsService();
		List<PatientDTO> patientDTOs = patients.stream().map(patient -> patientMapper.fromPatientToPatientDTO(patient))
				.collect(Collectors.toList());
		return patientDTOs;
	}

	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientById(@PathVariable Long patientId) {
		try {
			Patient patient = patientServiceImpl.getPatientByIdService(patientId);
			PatientDTO patientDTO = patientMapper.fromPatientToPatientDTO(patient);
			String json = objectMapper.writerWithView(PatientDTO.viewPatient.class).writeValueAsString(patientDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
