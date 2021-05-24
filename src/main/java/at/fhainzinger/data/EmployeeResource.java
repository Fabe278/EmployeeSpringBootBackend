package at.fhainzinger.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeResource {
    private int id;
    private String name;
    private String password;
    private String longitude;
    private String latitude;
}
