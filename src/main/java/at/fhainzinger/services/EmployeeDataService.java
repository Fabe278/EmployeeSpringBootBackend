package at.fhainzinger.services;

import at.fhainzinger.data.*;
import at.fhainzinger.database.EmployeeRepository;
import at.fhainzinger.exceptions.employees.EmployeeMSBadRequestException;
import at.fhainzinger.exceptions.employees.EmployeeMSResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDataService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LocationIQDataService locationIQDataService;

    public List<EmployeeResource> getEmployeeResources() {
        List<EmployeeResource> result = new ArrayList<>();
        for (EmployeeEntity e : employeeRepository.findAll()){
            result.add(e.convertToEmployee().convertToEmployeeResource());
        }
        return result;
    }

    public EmployeeResource addEmployee(EmployeeDto employeeDto) {
        checkDto(employeeDto);

        Employee employee = new Employee();
        employee.setId(-1);
        employee.setName(employeeDto.getName());
        LongitudeLatitude longitudeLatitude = locationIQDataService.getLongitudeLatitudeByAddress(employeeDto.getAddress());
        employee.setLatitude(longitudeLatitude.getLatitude());
        employee.setLongitude(longitudeLatitude.getLongitude());
        employee.setPassword(employeeDto.getPassword());

        EmployeeEntity storedEntity = employeeRepository.save(employee.convertToEmployeeEntity());

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

        LongitudeLatitude longitudeLatitude = locationIQDataService.getLongitudeLatitudeByAddress(employeeDto.getAddress());
        employee.setLatitude(longitudeLatitude.getLatitude());
        employee.setLongitude(longitudeLatitude.getLongitude());
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
        if(isNullOrEmpty(employeeDto.getName()))
            throw new EmployeeMSBadRequestException("The param name is not valid");
        if(isNullOrEmpty(employeeDto.getPassword()))
            throw new EmployeeMSBadRequestException("The param password is not valid");
        if(isNullOrEmpty(employeeDto.getAddress()))
            throw new EmployeeMSBadRequestException("The param address is not valid");
        if(employeeDto.getName().length() <= 4)
            throw new EmployeeMSBadRequestException("The param name is not valid. Length must be > 4");
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }
}
