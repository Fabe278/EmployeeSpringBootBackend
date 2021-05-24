package at.fhainzinger.exceptions.employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeInternalServerErrorException extends RuntimeException{
    public EmployeeInternalServerErrorException(String message){
        super(message);
    }
}
