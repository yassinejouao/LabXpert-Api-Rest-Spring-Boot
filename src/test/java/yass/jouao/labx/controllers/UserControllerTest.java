package yass.jouao.labx.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import yass.jouao.labx.DTOs.UserLabDTO;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.StatusUser;
import yass.jouao.labx.serviceImpl.UserLabServiceImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserLabServiceImpl userServiceImpl;
    @Test
    @DisplayName("test for save user")
    void saveUserLab() throws Exception {
        UserLab user = UserLab.builder().name("User").userRole(RoleUser.MANAGER).password("password").status(StatusUser.ACTIVE).build();
        UserLabDTO userDTO = UserLabDTO.builder().id(1L).name("User").userRole(RoleUser.MANAGER).password("password").status(StatusUser.ACTIVE).build();
        given(userServiceImpl.addUserLabService(ArgumentMatchers.any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(user.getName())));
    }
    @Test
    @DisplayName("test for update user")
    void testUpdateUser() throws Exception {
        Long id = 1L;
        UserLabDTO updateUserDTO = UserLabDTO.builder().id(id).name("UpdatedUser").userRole(RoleUser.MANAGER).password("newpassword").status(StatusUser.ACTIVE).build();

        given(userServiceImpl.updateUserLabService(anyLong(), ArgumentMatchers.any()))
                .willReturn(updateUserDTO);

        String requestBody = objectMapper.writerWithView(UserLabDTO.saveUser.class).writeValueAsString(updateUserDTO);

        mockMvc.perform(post("/user/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("UpdatedUser"))
                .andExpect(jsonPath("$.userRole").value("MANAGER"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }
    @Test
    @DisplayName("test for update user status to ACTIVE")
    void testEnableUser() throws Exception {
        Long id = 1L;
        UserLabDTO updateUserDTO = UserLabDTO.builder().id(id).name("User").userRole(RoleUser.MANAGER).password("password").status(StatusUser.INACTIVE).build();
        updateUserDTO.setStatus(StatusUser.ACTIVE);

        given(userServiceImpl.updateUserLabService(anyLong(), ArgumentMatchers.any()))
                .willReturn(updateUserDTO);

        mockMvc.perform(get("/user/enable/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }
    @Test
    @DisplayName("test for update user status to INACTIVE")
    void testDisableUser() throws Exception {
        Long id = 1L;
        UserLabDTO updateUserDTO = UserLabDTO.builder().id(id).name("User").userRole(RoleUser.MANAGER).password("password").status(StatusUser.ACTIVE).build();
        updateUserDTO.setStatus(StatusUser.INACTIVE);

        given(userServiceImpl.updateUserLabService(anyLong(), ArgumentMatchers.any()))
                .willReturn(updateUserDTO);

        mockMvc.perform(get("/user/disable/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("INACTIVE"));
    }

}