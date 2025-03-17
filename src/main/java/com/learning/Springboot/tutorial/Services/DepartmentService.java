package com.learning.Springboot.tutorial.Services;

import com.learning.Springboot.tutorial.Entity.Department;
import com.learning.Springboot.tutorial.Error.DepartmentNotFoundException;
import com.learning.Springboot.tutorial.Repository.DepartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentService{
    public Department saveDepartment(Department department);

    public List<Department> findAllDepartment();
    public Department getDepartmentUsingId(Long id) throws DepartmentNotFoundException;

    public void deleteDepartmentUsingId(Long id);

    void updateDepartmentUsingId(Long id, Department department);

    Department findDepartmentByName(String departmentName);
}
