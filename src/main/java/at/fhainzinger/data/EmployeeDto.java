package at.fhainzinger.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeDto {
    private String name;
    private String password;
    private String longitude;
    private String latitude;

    public Employee convertToEmployee(){
        Employee result = new Employee();
        result.setId(-1);
        result.setName(name);
        result.setPassword(password);
        result.setLongitude(longitude);
        result.setLatitude(latitude);
        return result;
    }
}