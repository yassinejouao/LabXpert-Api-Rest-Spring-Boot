package yass.jouao.labx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import yass.jouao.labx.DTOs.UserLabDTO;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.serviceImpl.UserLabServiceImpl;
import yass.jouao.labx.serviceImpl.Mappers.UserMapper;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserLabServiceImpl userLabServiceImpl;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody @JsonView(UserLabDTO.saveUser.class) UserLabDTO userLabDTO) {
		try {
			UserLab user = userMapper.fromUserDTOToUser(userLabDTO);
			userLabDTO = userMapper.fromUserToUserDTO(userLabServiceImpl.addUserLabService(user));
			String json = objectMapper.writerWithView(UserLabDTO.saveUser.class).writeValueAsString(userLabDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody @JsonView(UserLabDTO.updateUser.class) UserLabDTO userLabDTO) {
		try {
			UserLab user = userMapper.fromUserDTOToUser(userLabDTO);
			userLabDTO = userMapper.fromUserToUserDTO(userLabServiceImpl.addUserLabService(user));
			String json = objectMapper.writerWithView(UserLabDTO.saveUser.class).writeValueAsString(userLabDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
