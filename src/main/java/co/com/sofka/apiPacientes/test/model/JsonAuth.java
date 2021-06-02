package co.com.sofka.apiPacientes.test.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonAuth {
    private String username;
    private String password;
}
