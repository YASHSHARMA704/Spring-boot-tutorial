package com.learning.Springboot.tutorial.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.Springboot.tutorial.Entity.Department;
import com.learning.Springboot.tutorial.Error.DepartmentNotFoundException;
import com.learning.Springboot.tutorial.Services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class) // Enables Mockito in JUnit 5
class DepartmentControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock // Use @Mock to create a mock service
    private DepartmentService departmentService;

    @InjectMocks // Inject the mocked service into the controller
    private DepartmentController departmentController;

    private Department randomDepartment;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build(); // Manually set up MockMvc

        randomDepartment = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentCode("IT-1")
                .departmentAddress("IT-2")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-1")
                .departmentAddress("IT-2")
                .build();

        Mockito.when(departmentService.saveDepartment(Mockito.any(Department.class)))
                .thenReturn(randomDepartment);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDepartment)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentUsingId(1L))
                .thenReturn(randomDepartment);  // ✅ This should work

        // Perform the request using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(1L));
    }

}
