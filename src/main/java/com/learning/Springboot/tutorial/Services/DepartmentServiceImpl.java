package com.learning.Springboot.tutorial.Services;

import com.learning.Springboot.tutorial.Entity.Department;
import com.learning.Springboot.tutorial.Error.DepartmentNotFoundException;
import com.learning.Springboot.tutorial.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentUsingId(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department not found in database");
        }

        return department.get();
    }

    @Override
    public void deleteDepartmentUsingId(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public void updateDepartmentUsingId(Long departmentId, Department department) {
        Department current = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
            current.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
            current.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            current.setDepartmentAddress(department.getDepartmentAddress());
        }

        departmentRepository.save(current);
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }
}
