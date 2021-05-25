package at.fhainzinger.data;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String password;

    private String longitude;

    private String latitude;

    public EmployeeEntity() {
    }

    public EmployeeEntity(Integer id, String name, String password, String longitude, String latitude) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Employee convertToEmployee(){
        Employee result = new Employee();
        result.setId(id);
        result.setName(name);
        result.setPassword(password);
        result.setLongitude(longitude);
        result.setLatitude(latitude);
        return result;
    }
}
