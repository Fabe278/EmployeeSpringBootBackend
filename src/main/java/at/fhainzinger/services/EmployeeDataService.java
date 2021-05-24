package at.fhainzinger.services;

import at.fhainzinger.data.*;
import at.fhainzinger.database.EmployeeRepository;
import at.fhainzinger.exceptions.employees.EmployeeMSBadRequestException;
import at.fhainzinger.exceptions.employees.EmployeeMSResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDataService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ServiceDataService serviceDataService;

    public void createInitials(){
        if(employeeRepository.count() == 0){
            Employee emp1 = new Employee();
            emp1.setId(1);
            emp1.setName("Employee 1");
            emp1.setPassword("password");
            emp1.setLongitude("45.24565");
            emp1.setLatitude("45.67369");

            employeeRepository.save(emp1.convertToEmployeeEntity());

            Employee emp2 = new Employee();
            emp2.setId(1);
            emp2.setName("Employee 2");
            emp2.setPassword("password");
            emp2.setLongitude("48.245625");
            emp2.setLatitude("41.67369");

            employeeRepository.save(emp2.convertToEmployeeEntity());
        }
    }

    public List<EmployeeResource> getEmployeeResources() {
        List<EmployeeResource> result = new ArrayList<>();
        for (EmployeeEntity e : employeeRepository.findAll()){
            result.add(e.convertToEmployee().convertToEmployeeResource());
        }
        return result;
    }

    public EmployeeResource addEmployee(EmployeeDto employeeDto) {
        checkDto(employeeDto);
        EmployeeEntity storedEntity = employeeRepository.save(employeeDto.convertToEmployee().convertToEmployeeEntity());

        return storedEntity.convertToEmployee().convertToEmployeeResource();
    }

    public EmployeeResource getEmployee(int employeeId) {
        Optional<EmployeeEntity> foundEmployee = employeeRepository.findById(employeeId);
        if(!foundEmployee.isPresent())
            throw new EmployeeMSResourceNotFoundException(String.format("The employee with the id %d does not exist!", employeeId));
        return foundEmployee.get().convertToEmployee().convertToEmployeeResource();
    }

    public EmployeeResource editEmployee(int employeeId, EmployeeDto employeeDto) {
        Optional<EmployeeEntity> foundEntity = employeeRepository.findById(employeeId);
        if(!foundEntity.isPresent())
            throw new EmployeeMSResourceNotFoundException(String.format("The employeeId with the id %d does not exist!", employeeId));
        checkDto(employeeDto);
        Employee employee = foundEntity.get().convertToEmployee();

        employee.setLatitude(employeeDto.getLatitude());
        employee.setLongitude(employeeDto.getLongitude());
        employee.setName(employeeDto.getName());
        employee.setPassword(employeeDto.getPassword());

        EmployeeEntity changedEntity = employeeRepository.save(employee.convertToEmployeeEntity());

        return changedEntity.convertToEmployee().convertToEmployeeResource();
    }

    public EmployeeResource deleteEmployee(int employeeId) {
        Optional<EmployeeEntity> foundDepartment = employeeRepository.findById(employeeId);
        if(!foundDepartment.isPresent())
            throw new EmployeeMSResourceNotFoundException(String.format("The employeeId with the id %d does not exist!", employeeId));
        employeeRepository.delete(foundDepartment.get());
        return foundDepartment.get().convertToEmployee().convertToEmployeeResource();
    }

    private void checkDto(EmployeeDto employeeDto){
        if(employeeDto.getName().equals("") || employeeDto.getName() == null)
            throw new EmployeeMSBadRequestException("The param name is not valid");
        if(employeeDto.getPassword().equals("") || employeeDto.getPassword() == null)
            throw new EmployeeMSBadRequestException("The param password is not valid");
        if(employeeDto.getLatitude().equals("") || employeeDto.getLatitude() == null)
            throw new EmployeeMSBadRequestException("The param latitude is not valid");
        if(employeeDto.getLongitude().equals("") || employeeDto.getLongitude() == null)
            throw new EmployeeMSBadRequestException("The param longitude is not valid");
        if(employeeDto.getName().length() <= 4)
            throw new EmployeeMSBadRequestException("The param name is not valid. Length must be > 4");
    }


    public List<ServiceResource> getServicesOfEmployee(int employeeId) {
        Optional<EmployeeEntity> foundEntity = employeeRepository.findById(employeeId);
        if(!foundEntity.isPresent())
            throw new EmployeeMSResourceNotFoundException(String.format("The employeeId with the id %d does not exist!", employeeId));
        return serviceDataService.getServicesByEmployeeId(employeeId);
    }
}
