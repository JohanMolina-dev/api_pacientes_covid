package co.com.sofka.apiPacientes.test.task.http;

import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class PostRequestWithJWT implements Task {
    String path;
    Object body;

    public PostRequestWithJWT(String path, Object body) {
        this.path = path;
        this.body = body;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(path)
                .with(request -> request.body(body)
                        .contentType(ContentType.JSON)
                        .auth().oauth2(Serenity.sessionVariableCalled("TOKEN")))
        );

    }

    public static PostRequestWithJWT run(String path, Object body){
        return Tasks.instrumented(PostRequestWithJWT.class,path,body);
    }
}
