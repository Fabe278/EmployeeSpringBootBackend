package at.fhainzinger.exceptions.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceDataServiceException extends RuntimeException {
    public ServiceDataServiceException(String message) { super(message);}
}
