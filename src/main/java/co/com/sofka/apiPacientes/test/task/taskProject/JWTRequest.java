package co.com.sofka.apiPacientes.test.task.taskProject;

import co.com.sofka.apiPacientes.test.task.http.PostRequest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

public class JWTRequest implements Task {
    private Object body;
    private EnvironmentVariables variables = null;


    public JWTRequest(Object body) {
        this.body = body;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String URLBase = variables.getProperty("baseurl");
        actor.whoCan(CallAnApi.at(URLBase));
        actor.attemptsTo(
                PostRequest.run(variables.getProperty("pathLogin"),
                        body)
        );
    }
    public static JWTRequest run(Object body){
        return Tasks.instrumented(JWTRequest.class,body);
    }
}
