package at.fhainzinger.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "services")
public class ServiceIdEntity {
    @Id
    private int serviceId;
}
