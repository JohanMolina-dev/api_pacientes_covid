package co.com.sofka.apiPacientes.test.task.taskProject;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class SaveJWT implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        Serenity.setSessionVariable("TOKEN").to(SerenityRest.lastResponse()
                .jsonPath()
                .getObject("jwt", String.class)
        );
    }
    public static SaveJWT run(){
        return Tasks.instrumented(SaveJWT.class);
    }
}
