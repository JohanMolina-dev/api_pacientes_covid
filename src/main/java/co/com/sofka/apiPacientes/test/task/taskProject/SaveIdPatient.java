package co.com.sofka.apiPacientes.test.task.taskProject;


import co.com.sofka.apiPacientes.test.data.RequestAppointment;
import co.com.sofka.apiPacientes.test.task.http.PostRequestWithJWT;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.json.Json;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class SaveIdPatient implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        Serenity.setSessionVariable("documentID").to(SerenityRest.lastResponse()
                        .jsonPath()
                        .getObject("patientId", Integer.class));

    }
    public static SaveIdPatient save(){
        return Tasks.instrumented(SaveIdPatient.class);
    }
}
