package at.fhainzinger.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServiceList {
    private List<ServiceResource> services = new ArrayList<>();

    public ServiceList(List<ServiceResource> services){
        this.services = services;
    }
}
