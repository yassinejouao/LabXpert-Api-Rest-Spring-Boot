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
import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.serviceImpl.FournisseurServiceImpl;
import yass.jouao.labx.serviceImpl.Mappers.FournisseurMapper;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;
import yass.jouao.labx.serviceImpl.PatientServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fournisseur")
public class FournisseurController {
	@Autowired
	private FournisseurServiceImpl fournisseurServiceImpl;
	@Autowired
	private FournisseurMapper fournisseurMapper;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/all")
	public ResponseEntity<?> fournisseurs() {
		try{
			List<FournisseurDTO> fournisseurDTOs = fournisseurServiceImpl.getAllFournisseursService();
			String json = objectMapper.writerWithView(FournisseurDTO.viewFournisseur.class).writeValueAsString(fournisseurDTOs);
			return  new ResponseEntity<>(json,HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>("Internal server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{fournisseurId}")
	public ResponseEntity<?> getFournisseurById(@PathVariable Long fournisseurId) {
		try {
			FournisseurDTO fournisseurDTO = fournisseurServiceImpl.getFournisseurByIdService(fournisseurId);
			String json = objectMapper.writerWithView(FournisseurDTO.viewFournisseur.class).writeValueAsString(fournisseurDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/save")
	public ResponseEntity<?> saveFournisseur(@RequestBody @JsonView(FournisseurDTO.saveFournisseur.class) FournisseurDTO fournisseurDTO){
		try {
			fournisseurDTO = fournisseurServiceImpl.addFournisseurService(fournisseurDTO);
			String json = objectMapper.writerWithView(FournisseurDTO.saveFournisseur.class).writeValueAsString(fournisseurDTO);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateFournisseur(@PathVariable Long id,@RequestBody FournisseurDTO fournisseurDTO){
		try {
			fournisseurDTO = fournisseurServiceImpl.updateFournisseurService(id, fournisseurDTO);
			String json = objectMapper.writerWithView(FournisseurDTO.viewFournisseur.class).writeValueAsString(fournisseurDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (NotFoundException e) {
			MessageErrorDTO errorResponse = new MessageErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}

}
