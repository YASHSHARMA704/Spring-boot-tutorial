package com.learning.Springboot.tutorial.Services;

import com.learning.Springboot.tutorial.Entity.Department;
import com.learning.Springboot.tutorial.Repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department randomDepartment = Department.builder().departmentId(1L).departmentName("IT").departmentAddress("IT-1").departmentCode("IT-2").build();

        Mockito.when(departmentRepository.findByDepartmentName(ArgumentMatchers.anyString())).thenReturn(randomDepartment);
    }

    @Test
    @DisplayName("Get Data Based on valid department name")
//    @Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        Department current = departmentService.findDepartmentByName(ArgumentMatchers.anyString());

        assertEquals(current.getDepartmentName(), "IT");
        assertNotNull(current);
    }
}