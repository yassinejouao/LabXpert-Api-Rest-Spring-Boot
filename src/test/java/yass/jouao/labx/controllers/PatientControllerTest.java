package yass.jouao.labx.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
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

import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.enums.Sex;
import yass.jouao.labx.serviceImpl.PatientServiceImpl;

@WebMvcTest(controllers = PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PatientServiceImpl patientServiceImpl;

	@Test
	@DisplayName("test for save patient")
	void savePatient() throws Exception {
		Patient patient = Patient.builder().firstname("patient").lastname("lastName").dateOfBirth(LocalDate.now())
				.sex(Sex.MAN).phone("2039374").build();
		PatientDTO patientDTO = PatientDTO.builder().firstname("patient").lastname("lastName")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("2039374").build();
		given(patientServiceImpl.addPatientService(ArgumentMatchers.any()))
				.willAnswer(invocation -> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(post("/patient/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patientDTO)));

		response.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstname", CoreMatchers.is(patient.getFirstname())));
	}

	@Test
	@DisplayName("test for update patient")
	void testUpdatePatient() throws Exception {
		Long id = 1L;
		PatientDTO updatedPatientDTO = PatientDTO.builder().id(id).firstname("patient").lastname("lastName")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("2039374").build();

		given(patientServiceImpl.updatePatientService(anyLong(), ArgumentMatchers.any())).willReturn(updatedPatientDTO);
		ResultActions response = mockMvc.perform(post("/patient/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedPatientDTO)));

		response.andExpect(MockMvcResultMatchers.status().is(200)).andExpect(jsonPath("$.firstname").value("patient"))
				.andExpect(jsonPath("$.sex").value("MAN")).andDo(MockMvcResultHandlers.print());
	}

	@Test
	void TestGetAllPatient() throws Exception {
		PatientDTO updatedPatientDTO1 = PatientDTO.builder().id(1L).firstname("patient1").lastname("lastName1")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("20393741").build();
		PatientDTO updatedPatientDTO2 = PatientDTO.builder().id(2L).firstname("patient2").lastname("lastName2")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("20393742").build();
		List<PatientDTO> patientDTOs = Arrays.asList(updatedPatientDTO1, updatedPatientDTO2);
		when(patientServiceImpl.getAllPatientsService()).thenReturn(patientDTOs);

		ResultActions response = mockMvc.perform(get("/patient/all").contentType(MediaType.APPLICATION_JSON));
		response.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(patientDTOs.size())));
	}
}