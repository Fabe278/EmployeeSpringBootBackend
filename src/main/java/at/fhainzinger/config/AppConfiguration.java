package at.fhainzinger.config;

import at.fhainzinger.data.Employee;
import at.fhainzinger.services.EmployeeDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public EmployeeDataService createEmployeeDataService() {
        EmployeeDataService employeeDataService = new EmployeeDataService();
//        employeeDataService.createInitials();
        return employeeDataService;
    }
}
