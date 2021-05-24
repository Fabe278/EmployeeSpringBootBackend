package at.fhainzinger.exceptions.employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeMSBadRequestException extends RuntimeException{
    public EmployeeMSBadRequestException(String message){
        super(message);
    }
}
