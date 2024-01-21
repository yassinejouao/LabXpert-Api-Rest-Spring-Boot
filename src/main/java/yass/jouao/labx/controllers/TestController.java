package yass.jouao.labx.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import yass.jouao.labx.DTOs.MessageErrorDTO;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.services.ITestService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private ITestService testService;
	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/addreagent")
	public ResponseEntity<?> addReagentToTest(@RequestBody Map<String, Long> requestBody) {
		try {
			Long idTest = requestBody.get("idTest");
			Long idReagent = requestBody.get("idReagent");
			Long Quantity = requestBody.get("quantity");
			System.out.println(idTest);
			System.out.println(idReagent);
			System.out.println(Quantity);
			if (testService.addReagentToTest(idTest, idReagent, Quantity)) {
				String json = objectMapper.writeValueAsString("add successfully");
				return new ResponseEntity<>(json, HttpStatus.OK);
			} else {
				String json = objectMapper.writeValueAsString("operation failed");
				return new ResponseEntity<>(json, HttpStatus.OK);
			}
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{idTest}/{result}")
	public ResponseEntity<?> updateResultTest(@PathVariable Long idTest, @PathVariable Double result) {
		try {
			if (testService.updateResultTest(idTest, result)) {
				String json = objectMapper.writeValueAsString("1");
				return new ResponseEntity<>(json, HttpStatus.OK);
			} else {
				String json = objectMapper.writeValueAsString("0");
				return new ResponseEntity<>(json, HttpStatus.OK);
			}
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
