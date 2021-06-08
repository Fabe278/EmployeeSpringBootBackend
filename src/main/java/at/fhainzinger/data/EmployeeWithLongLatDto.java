package at.fhainzinger.data;


import at.fhainzinger.services.LocationIQDataService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class EmployeeWithLongLatDto {
    @Autowired
    private LocationIQDataService locationIQDataService;

    private String name;
    private String longitude;
    private String latitude;
    private String password;
}