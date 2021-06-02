package co.com.sofka.apiPacientes.test.task.taskProject;

import co.com.sofka.apiPacientes.test.data.RequestAuth;
import co.com.sofka.apiPacientes.test.data.RequestPatient;
import co.com.sofka.apiPacientes.test.model.JsonPatient;
import co.com.sofka.apiPacientes.test.task.http.PostRequestWithJWT;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

public class CreateNewPatient implements Task {
    JsonPatient patientSend;
    EnvironmentVariables variables;
    @Override
    public <T extends Actor> void performAs(T actor) {
        String URLBase = variables.getProperty("baseurl");
        actor.attemptsTo(JWTRequest.run(RequestAuth.getValidUser()));
        actor.attemptsTo(SaveJWT.run());
        actor.whoCan(CallAnApi.at(URLBase));
        patientSend = RequestPatient.getPatientInformation();
        actor.attemptsTo(PostRequestWithJWT.run(
                variables.getProperty("pathPatient"),patientSend));
    }
    public static CreateNewPatient run(){
        return Tasks.instrumented(CreateNewPatient.class);
    }
}
