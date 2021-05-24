package at.fhainzinger.services;

import at.fhainzinger.data.ServiceList;
import at.fhainzinger.data.ServiceResource;
import at.fhainzinger.exceptions.services.ServiceDataServiceException;
import at.fhainzinger.exceptions.services.ServiceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ServiceDataService {
    private static final String SERVICE_MICROSERVICE_EMPLOYEE_BY_ID_URL = "http://localhost:9002/services/{serviceId}";
    private static final String SERVICE_MICROSERVICE_ALL_EMPLOYEES_URL = "http://localhost:9002/services/emp/{employeeId}";

    public ServiceResource getServiceById(int id) throws ServiceNotFoundException, ServiceDataServiceException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();
        vars.put("serviceId", id + "");
        try{
            return restTemplate.getForObject(SERVICE_MICROSERVICE_EMPLOYEE_BY_ID_URL, ServiceResource.class, vars);
        } catch (RestClientResponseException e) {
            if(e.getRawStatusCode() == 400) {
                throw new ServiceNotFoundException(String.format("The service with the id %d was not found", id));
            } else {
                throw new ServiceDataServiceException(e.getResponseBodyAsString());
            }
        }
    }

    public List<ServiceResource> getServicesByEmployeeId(int id) throws ServiceNotFoundException, ServiceDataServiceException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();
        vars.put("employeeId", id + "");
        try{
            return Objects.requireNonNull(restTemplate.getForObject(SERVICE_MICROSERVICE_ALL_EMPLOYEES_URL, ServiceList.class, vars)).getServices();
        } catch (RestClientResponseException e) {
            throw new ServiceDataServiceException(e.getResponseBodyAsString());
        }
    }
}