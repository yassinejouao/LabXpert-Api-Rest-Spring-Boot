package yass.jouao.labx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import yass.jouao.labx.DTOs.AnalysisTypeDTO;
import yass.jouao.labx.DTOs.MessageErrorDTO;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.serviceImpl.AnalysisTypeImpl;

@RestController
@RequestMapping("/analysistype")
public class AnalysisTypeController {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AnalysisTypeImpl analysisTypeImpl;

	@PostMapping("/add")
	public ResponseEntity<?> addAnalysisType(@RequestBody AnalysisTypeDTO analysisTypeDTO) {
		try {
			AnalysisTypeDTO aDto = analysisTypeImpl.addAnalysisTypeService(analysisTypeDTO);
			String json = objectMapper.writeValueAsString(aDto);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateAnalysisType(@PathVariable Long id, @RequestBody AnalysisTypeDTO analysisTypeDTO) {
		try {
			analysisTypeDTO = analysisTypeImpl.updateAnalysisTypeService(id, analysisTypeDTO);
			String json = objectMapper.writeValueAsString(analysisTypeDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
