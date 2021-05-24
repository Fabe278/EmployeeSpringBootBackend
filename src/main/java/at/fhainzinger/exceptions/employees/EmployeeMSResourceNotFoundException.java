package at.fhainzinger.exceptions.employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeMSResourceNotFoundException extends RuntimeException{
    public EmployeeMSResourceNotFoundException(String message){
        super(message);
    }
}
