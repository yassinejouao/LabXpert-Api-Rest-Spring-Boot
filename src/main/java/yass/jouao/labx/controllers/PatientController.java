package yass.jouao.labx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import yass.jouao.labx.DTOs.MessageErrorDTO;
import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.serviceImpl.PatientServiceImpl;

@RestController
@RequestMapping("/patient")
@CrossOrigin("*")
public class PatientController {
	@Autowired
	private PatientServiceImpl patientServiceImpl;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/all")
	public ResponseEntity<?> patients() {
		try {

			List<PatientDTO> patientDTOs = patientServiceImpl.getAllPatientsService();
			String json = objectMapper.writerWithView(PatientDTO.viewPatient.class).writeValueAsString(patientDTOs);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientById(@PathVariable Long patientId) {
		try {
			PatientDTO patientDTO = patientServiceImpl.getPatientByIdService(patientId);
			String json = objectMapper.writerWithView(PatientDTO.viewPatient.class).writeValueAsString(patientDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/save")
	public ResponseEntity<?> savePatient(@RequestBody @JsonView(PatientDTO.savePatient.class) PatientDTO patientDTO) {
		try {

			patientDTO = patientServiceImpl.addPatientService(patientDTO);
			String json = objectMapper.writerWithView(PatientDTO.savePatient.class).writeValueAsString(patientDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update/{patientId}")
	public ResponseEntity<?> updatePatient(@PathVariable Long patientId,
			@RequestBody @JsonView(PatientDTO.savePatient.class) PatientDTO patientDTO) {
		try {
			patientDTO = patientServiceImpl.updatePatientService(patientId, patientDTO);
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
