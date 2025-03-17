package com.learning.Springboot.tutorial.Controller;

import com.learning.Springboot.tutorial.Entity.Department;
import com.learning.Springboot.tutorial.Error.DepartmentNotFoundException;
import com.learning.Springboot.tutorial.Services.DepartmentService;
import com.learning.Springboot.tutorial.Services.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // logger implementation
    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        log.info("Inside the saving department controller");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartment(){
        log.info("Inside the getting all department controller");
        return departmentService.findAllDepartment();
    }


    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable Long id) throws DepartmentNotFoundException {
        return departmentService.getDepartmentUsingId(id);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable Long id){
        departmentService.deleteDepartmentUsingId(id);
        return "Record deleted successfully !";
    }

    @PutMapping("/departments/{id}")
    public String updateDepartmentById(@PathVariable Long id , @RequestBody Department department){
        departmentService.updateDepartmentUsingId(id,department);
        return "Department Updated Successfully";
    }

    @GetMapping("/departments/name/{departmentName}")
    public Department getDepartmentByName(@PathVariable String departmentName){
        return departmentService.findDepartmentByName(departmentName);
    }
}
