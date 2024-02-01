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
import yass.jouao.labx.DTOs.SampleDTO;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.serviceImpl.SampleServiceImpl;

@RestController
@RequestMapping("/sample")
@CrossOrigin("*")
public class SampleController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SampleServiceImpl sampleServiceImpl;

	@GetMapping("/all")
	public ResponseEntity<?> patients() {
		try {

			List<SampleDTO> sampleDTOs = sampleServiceImpl.getAllSamplesService();
			String json = objectMapper.writerWithView(SampleDTO.viewSample.class).writeValueAsString(sampleDTOs);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/add")
	public ResponseEntity<?> addSample(@RequestBody @JsonView(SampleDTO.saveSample.class) SampleDTO sampleDTO) {
		try {
			SampleDTO sDTO = sampleServiceImpl.addSampleService(sampleDTO);
			String json = objectMapper.writerWithView(SampleDTO.viewSample.class).writeValueAsString(sDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/patient/{id}")
	public ResponseEntity<?> getSamplesByPatient(@PathVariable Long id) {
		try {
			List<SampleDTO> samples = sampleServiceImpl.getSamplesByIdPatient(id);
			String json = objectMapper.writerWithView(SampleDTO.viewSample.class).writeValueAsString(samples);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/updateStatus/{sampleId}")
	public ResponseEntity<?> updateStatusSample(@PathVariable Long sampleId,
			@RequestBody @JsonView(SampleDTO.updateStatus.class) SampleDTO sampleDTO) {
		try {
			sampleDTO = sampleServiceImpl.updateSampleService(sampleId, sampleDTO);
			String json = objectMapper.writerWithView(SampleDTO.viewSample.class).writeValueAsString(sampleDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
