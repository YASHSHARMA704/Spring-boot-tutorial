package com.learning.Springboot.tutorial.Repository;

import com.learning.Springboot.tutorial.Entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department randomDepartment = Department.builder().departmentName("IT").departmentCode("IT-1").departmentAddress("IT-2").build();
        entityManager.persist(randomDepartment);
    }

    @Test
    public void whenFindById_thenReturnDepartment(){
        Optional<Department> department = departmentRepository.findById(1L);
        assertTrue(department.isPresent());
        assertEquals("IT", department.get().getDepartmentName());
    }
}