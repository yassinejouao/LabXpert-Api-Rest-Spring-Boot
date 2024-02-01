package yass.jouao.labx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.DTOs.AnalysisResultDTO;
import yass.jouao.labx.DTOs.DataRapportDTO;
import yass.jouao.labx.serviceImpl.AnalysisImpl;

@RestController
@RequestMapping("/analysis")
@CrossOrigin("*")
public class AnalysisController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AnalysisImpl analysisImpl;

	@PostMapping("/add")
	public ResponseEntity<?> addAnalysis(
			@RequestBody @JsonView(AnalysisDTO.saveAnalysis.class) AnalysisDTO analysisDTO) {
		try {
			AnalysisDTO aDTO = analysisImpl.addAnalysisService(analysisDTO);
			String json = objectMapper.writerWithView(AnalysisDTO.viewAnalysis.class).writeValueAsString(aDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("inprogress")
	public ResponseEntity<?> getAllInProgressAnalysis() {
		try {
			List<AnalysisDTO> aDTO = analysisImpl.getAllAnalysisInProgress();
			String json = objectMapper.writerWithView(AnalysisDTO.viewAnalysis.class).writeValueAsString(aDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("all")
	public ResponseEntity<?> getAllAnalysis() {
		try {
			List<AnalysisDTO> aDTO = analysisImpl.getAllAnalysis();
			String json = objectMapper.writerWithView(AnalysisDTO.viewAnalysis.class).writeValueAsString(aDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("patient/{patientId}")
	public ResponseEntity<String> getAllAnalysisByPatient(@PathVariable Long patientId) {
		try {
			List<AnalysisDTO> aDTO = analysisImpl.getAnalysisByIdPatientService(patientId);
			String json = objectMapper.writerWithView(AnalysisDTO.viewAnalysis.class).writeValueAsString(aDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("result/{Id}")
	public ResponseEntity<String> getAnalysisResult(@PathVariable Long Id) {
		try {
			AnalysisDTO aDTO = analysisImpl.getResultByAnalysisId(Id);
			String json = objectMapper.writerWithView(AnalysisDTO.result.class).writeValueAsString(aDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("rapport")
	public ResponseEntity<String> getAnalysisRapport(@RequestBody DataRapportDTO dataRapport) {
		try {

			List<AnalysisResultDTO> aDTO = analysisImpl.getAnalysisRapport(dataRapport.getAnalysisTypeId(),
					dataRapport.getStartDate(), dataRapport.getEndDate(), dataRapport.getIntervalRapport());
			String json = objectMapper.writeValueAsString(aDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("update")
	public ResponseEntity<String> updateAnalysis(
			@RequestBody @JsonView(AnalysisDTO.updateAnalysis.class) AnalysisDTO analysisDTO) {
		try {
			analysisDTO = analysisImpl.updateAnalysisService(analysisDTO);
			String json = objectMapper.writeValueAsString("Updated Successfully");
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
