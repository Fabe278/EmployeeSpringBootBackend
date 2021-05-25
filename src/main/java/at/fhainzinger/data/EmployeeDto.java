package at.fhainzinger.data;

import at.fhainzinger.services.LocationIQDataService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeDto {
    @Autowired
    private LocationIQDataService locationIQDataService;

    private String name;
    private String address;
    private String password;
}