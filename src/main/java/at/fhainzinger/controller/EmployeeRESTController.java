package at.fhainzinger.controller;

import at.fhainzinger.data.EmployeeDto;
import at.fhainzinger.data.EmployeeResource;
import at.fhainzinger.services.EmployeeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeRESTController {
    @Autowired
    private EmployeeDataService employeeDataService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeResource>> getAllEmployees(){
        List<EmployeeResource> employeeResources = employeeDataService.getEmployeeResources();
        return new ResponseEntity<>(employeeResources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmployeeResource> addEmployee(@RequestBody EmployeeDto employeeDto){
        return  new ResponseEntity<>(employeeDataService.addEmployee(employeeDto), HttpStatus.OK);
    }

    @RequestMapping(value ="/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeResource> getEmployee(@PathVariable int employeeId){
        return new ResponseEntity<>(employeeDataService.getEmployee(employeeId), HttpStatus.OK);
    }

    @RequestMapping(value ="/{employeeId}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeResource> editEmployee(@PathVariable int employeeId, @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeDataService.editEmployee(employeeId, employeeDto), HttpStatus.OK);
    }

    @RequestMapping(value ="/{employeeId}", method = RequestMethod.DELETE)
    public ResponseEntity<EmployeeResource> deleteEmployee(@PathVariable int employeeId){
        return new ResponseEntity<>(employeeDataService.deleteEmployee(employeeId), HttpStatus.OK);
    }
}
