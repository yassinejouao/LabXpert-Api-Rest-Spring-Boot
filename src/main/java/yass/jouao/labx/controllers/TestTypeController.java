package yass.jouao.labx.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yass.jouao.labx.DTOs.FournisseurDTO;
import yass.jouao.labx.DTOs.MessageErrorDTO;
import yass.jouao.labx.DTOs.TestTypeDTO;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.serviceImpl.FournisseurServiceImpl;
import yass.jouao.labx.serviceImpl.Mappers.FournisseurMapper;
import yass.jouao.labx.serviceImpl.Mappers.TestTypeMapper;
import yass.jouao.labx.serviceImpl.TestTypeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/test-type")
public class TestTypeController {
	@Autowired
	private TestTypeServiceImpl testTypeService;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/all")
	public ResponseEntity<?> testType() {
		try{
			List<TestTypeDTO> testTypeDTOs = testTypeService.getAllTestTypesService();
			String json = objectMapper.writerWithView(TestTypeDTO.viewTestType.class).writeValueAsString(testTypeDTOs);
			return  new ResponseEntity<>(json,HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>("Internal server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTestTypeById(@PathVariable Long id) {
		try {
			TestTypeDTO testTypeDTO = testTypeService.getTestTypeDTOByIdService(id);
			String json = objectMapper.writerWithView(TestTypeDTO.viewTestType.class).writeValueAsString(testTypeDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/save")
	public ResponseEntity<?> saveTestType(@RequestBody @JsonView(TestTypeDTO.saveTestType.class) TestTypeDTO testTypeDTO){
		try {
			testTypeDTO = testTypeService.addTestTypeService(testTypeDTO);
			String json = objectMapper.writerWithView(TestTypeDTO.viewTestType.class).writeValueAsString(testTypeDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateTestType(@PathVariable Long id,@RequestBody TestTypeDTO testTypeDTO){
		try {
			testTypeDTO = testTypeService.updateTestTypeService(id, testTypeDTO);
			String json = objectMapper.writerWithView(TestTypeDTO.viewTestType.class).writeValueAsString(testTypeDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}
}
