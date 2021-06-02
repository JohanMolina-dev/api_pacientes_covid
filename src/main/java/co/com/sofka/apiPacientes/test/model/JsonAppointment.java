package co.com.sofka.apiPacientes.test.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonAppointment {
     private boolean conclude;
     private String date;
     private int patientId;
     private String recommendations;
     private String time;
}
