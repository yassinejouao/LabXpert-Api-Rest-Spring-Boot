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
import yass.jouao.labx.DTOs.UserLabDTO;
import yass.jouao.labx.enums.StatusUser;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.serviceImpl.UserLabServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserLabServiceImpl userLabServiceImpl;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/all")
	public ResponseEntity<?> UserLabs() {
		try {
			List<UserLabDTO> userLabDTOs = userLabServiceImpl.getAllUserLabService();
			String json = objectMapper.writerWithView(UserLabDTO.viewUser.class).writeValueAsString(userLabDTOs);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		try {
			UserLabDTO userLabDTO = userLabServiceImpl.getUserLabByIdService(id);
			String json = objectMapper.writerWithView(UserLabDTO.viewUser.class).writeValueAsString(userLabDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody @JsonView(UserLabDTO.saveUser.class) UserLabDTO userLabDTO) {
		try {
			userLabDTO = userLabServiceImpl.addUserLabService(userLabDTO);
			String json = objectMapper.writerWithView(UserLabDTO.saveUser.class).writeValueAsString(userLabDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,
			@RequestBody @JsonView(UserLabDTO.saveUser.class) UserLabDTO userLabDTO) {
		try {
			userLabDTO = userLabServiceImpl.updateUserLabService(id, userLabDTO);
			String json = objectMapper.writerWithView(UserLabDTO.saveUser.class).writeValueAsString(userLabDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/enable/{id}")
	public ResponseEntity<?> enableUser(@PathVariable Long id) {
		try {
			UserLabDTO userLabDTO = new UserLabDTO();
			userLabDTO.setStatus(StatusUser.ACTIVE);
			userLabDTO = userLabServiceImpl.updateUserLabService(id, userLabDTO);
			String json = objectMapper.writerWithView(UserLabDTO.saveUser.class).writeValueAsString(userLabDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/disable/{id}")
	public ResponseEntity<?> disableUser(@PathVariable Long id) {
		try {
			UserLabDTO userLabDTO = new UserLabDTO();
			userLabDTO.setStatus(StatusUser.INACTIVE);
			userLabDTO = userLabServiceImpl.updateUserLabService(id, userLabDTO);
			String json = objectMapper.writerWithView(UserLabDTO.saveUser.class).writeValueAsString(userLabDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
