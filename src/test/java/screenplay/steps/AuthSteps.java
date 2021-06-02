package screenplay.steps;

import co.com.sofka.apiPacientes.test.data.RequestAuth;
import co.com.sofka.apiPacientes.test.questions.ResponseCode;
import co.com.sofka.apiPacientes.test.task.taskProject.JWTRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class AuthSteps {
    private Actor actor;
    @Given("a {string} that knows the path of the authentication api")
    public void aThatKnowsThePathOfTheAuthenticationApi(String name) {

        actor = Actor.named(name);
    }

    @When("the user has valid credentials")
    public void theUserHasValidCredentials() {
        actor.attemptsTo(JWTRequest.run(RequestAuth.getValidUser()));
    }

    @Then("the user authenticates successfully")
    public void theUserAuthenticatesSuccessfully() {
        actor.should(
                seeThatResponse("The api delivered code 200 correctly",
                        response -> response.statusCode(200))
        );
    }

    @When("user has invalid credentials")
    public void userHasInvalidCredentials() {
        actor.attemptsTo(JWTRequest.run(RequestAuth.getInvalidCredentials()));
    }

    @Then("the api answers with error code {string}")
    public void theApiAnswersWithErrorCode(String statusCode) {
        actor.should(
                seeThat("The expected response code is returned", ResponseCode.was(), equalTo(parseInt(statusCode)))
        );
    }
}
