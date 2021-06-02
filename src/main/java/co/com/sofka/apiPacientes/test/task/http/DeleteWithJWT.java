package co.com.sofka.apiPacientes.test.task.http;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteWithJWT implements Task {
     String path;

    public DeleteWithJWT(String path) {
        this.path = path;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Delete.from(path)
                .with(request -> request
                        .auth()
                        .oauth2(Serenity.sessionVariableCalled("TOKEN"))));

    }

    public static DeleteWithJWT delete(String path){

        return Tasks.instrumented(DeleteWithJWT.class,path);
    }
}
