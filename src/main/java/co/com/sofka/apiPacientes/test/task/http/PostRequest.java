package co.com.sofka.apiPacientes.test.task.http;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class PostRequest implements Task {
    String path;
    Object body;


    public PostRequest(String path, Object body) {
        this.path = path;
        this.body = body;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(path)
                .with(request -> request.body(body)
                .contentType(ContentType.JSON)));
    }
    public static PostRequest run(String path, Object body){
        return Tasks.instrumented(PostRequest.class,path,body);
    }
}
