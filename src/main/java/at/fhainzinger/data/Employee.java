package at.fhainzinger.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Employee {
    private int id;
    private String name;
    private String password;
    private String longitude;
    private String latitude;

    public EmployeeResource convertToEmployeeResource(){
        EmployeeResource result = new EmployeeResource();
        result.setId(id);
        result.setName(name);
        result.setPassword(password);
        result.setLongitude(longitude);
        result.setLatitude(latitude);
        return result;
    }

    public EmployeeEntity convertToEmployeeEntity(){
        EmployeeEntity result = new EmployeeEntity();
        if(id != -1)
            result.setId(id);
        result.setName(name);
        result.setPassword(password);
        result.setLongitude(longitude);
        result.setLatitude(latitude);
        return result;
    }
}
