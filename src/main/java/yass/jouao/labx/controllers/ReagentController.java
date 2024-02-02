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
import yass.jouao.labx.DTOs.ReagentDTO;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.serviceImpl.ReagentServiceImpl;

@RestController
@RequestMapping("/reagent")
@CrossOrigin("*")
public class ReagentController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ReagentServiceImpl reagentService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getReagentById(@PathVariable Long id) {
		try {
			ReagentDTO reagentDTOs = reagentService.getReagentDTOByIdService(id);
			String json = objectMapper.writerWithView(ReagentDTO.viewReagent.class).writeValueAsString(reagentDTOs);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllReagent() {
		try {
			List<ReagentDTO> reagentDTOs = reagentService.getAllReagentsService();
			String json = objectMapper.writerWithView(ReagentDTO.viewReagent.class).writeValueAsString(reagentDTOs);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> addReagent(@RequestBody @JsonView(ReagentDTO.saveReagent.class) ReagentDTO rDTO) {
		try {
			ReagentDTO reagentDTO = reagentService.addReagentService(rDTO);
			String json = objectMapper.writerWithView(ReagentDTO.viewReagent.class).writeValueAsString(reagentDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/fournisseur/{id}")
	public ResponseEntity<?> getReagentsByFournisseur(@PathVariable Long id) {
		try {
			List<ReagentDTO> reagentDTOs = reagentService.getReagentsByIdFournisseur(id);
			String json = objectMapper.writerWithView(ReagentDTO.viewReagent.class).writeValueAsString(reagentDTOs);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateReagent(@PathVariable Long id,
			@RequestBody @JsonView(ReagentDTO.updateReagent.class) ReagentDTO reagentDTO) {
		try {
			reagentDTO = reagentService.updateReagentService(id, reagentDTO);
			String json = objectMapper.writerWithView(ReagentDTO.viewReagent.class).writeValueAsString(reagentDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
