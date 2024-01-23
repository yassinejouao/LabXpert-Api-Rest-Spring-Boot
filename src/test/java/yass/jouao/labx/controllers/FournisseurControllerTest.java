package yass.jouao.labx.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import yass.jouao.labx.DTOs.FournisseurDTO;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.serviceImpl.FournisseurServiceImpl;

@WebMvcTest(controllers = FournisseurController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class FournisseurControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FournisseurServiceImpl fournisseurServiceImpl;

	@Test
	void CreateFournisseur() throws Exception {
		Fournisseur fournisseur = Fournisseur.builder().id(1L).name("FournisseurName").build();
		FournisseurDTO fournisseurDTO = FournisseurDTO.builder().id(1L).name("FournisseurName").build();
		given(fournisseurServiceImpl.addFournisseurService(ArgumentMatchers.any()))
				.willAnswer(invocation -> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(post("/fournisseur/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(fournisseurDTO)));

		response.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(fournisseur.getName())));
//				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	void TestGetAllFournisseur() throws Exception {
		FournisseurDTO fournisseurDTO1 = FournisseurDTO.builder().name("fournisseu1").build();
		FournisseurDTO fournisseurDTO2 = FournisseurDTO.builder().name("fournisseu2").build();
		List<FournisseurDTO> fournisseurDTOs = Arrays.asList(fournisseurDTO1, fournisseurDTO2);
		when(fournisseurServiceImpl.getAllFournisseursService()).thenReturn(fournisseurDTOs);

		ResultActions response = mockMvc.perform(get("/fournisseur/all").contentType(MediaType.APPLICATION_JSON));
		response.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(fournisseurDTOs.size())));
	}

	@Test
	void TestUpdateFournisseur() throws Exception {
		Long id = 1L;
		FournisseurDTO updatedfournisseurDTO = FournisseurDTO.builder().id(id).name("fournisseu2").build();
		given(fournisseurServiceImpl.updateFournisseurService(anyLong(), ArgumentMatchers.any()))
				.willReturn(updatedfournisseurDTO);

		ResultActions response = mockMvc.perform(post("/fournisseur/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedfournisseurDTO)));
		response.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(updatedfournisseurDTO.getName())))
				.andDo(MockMvcResultHandlers.print());
		;
	}
}
