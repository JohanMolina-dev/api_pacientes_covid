package co.com.sofka.apiPacientes.test.model;

import groovy.transform.BaseScript;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonPatient {
    private String address;
    private Long cellPhoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private Long patientId;

}
