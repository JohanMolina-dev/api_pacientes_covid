package screenplay.steps;
import co.com.sofka.apiPacientes.test.questions.ResponseCode;
import co.com.sofka.apiPacientes.test.task.http.GetRequestWithJWT;
import co.com.sofka.apiPacientes.test.task.taskProject.CreateNewPatient;
import co.com.sofka.apiPacientes.test.task.taskProject.SaveIdPatient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.util.EnvironmentVariables;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class ConsultPatientSteps {
    private Actor actor;

    private EnvironmentVariables variables;
    @Given("a {string} is correctly authenticated in the covid-patients api")
    public void aIsCorrectlyAuthenticatedInTheCovidPatientsApi(String name) {
        actor = Actor.named(name);
        actor.attemptsTo(CreateNewPatient.run());
        actor.attemptsTo(SaveIdPatient.save());
    }

    @When("the nursing assistant knows the path of the patient consultation api")
    public void theNursingAssistantKnowsThePathOfThePatientConsultationApi() {
        actor.attemptsTo(GetRequestWithJWT.run("paciente/"));

    }

    @And("the nursing assistant has the patient's id")
    public void theNursingAssistantHasThePatientSId() {
        int id= Serenity.sessionVariableCalled("documentID");
        actor.attemptsTo(GetRequestWithJWT.run
                (variables.getProperty("pathConsulPatientId")+id
                ));
    }

    @Then("You can check the patient's data, the api responds with a code {string}")
    public void youCanCheckThePatientSDataTheApiRespondsWithACode(String statusCode) {
        actor.should(
                seeThat("The api delivered code 200 correctly", ResponseCode.was(), equalTo(parseInt(statusCode))));

    }

    @When("the nursing assistant knows the api route that allows patient consultation")
    public void theNursingAssistantKnowsTheApiRouteThatAllowsPatientConsultation() {
        actor.attemptsTo(GetRequestWithJWT.run(variables.getProperty("pathConsulPatientId")));
    }

    @And("the nursing assistant does not know the patient's id")
    public void theNursingAssistantDoesNotKnowThePatientSId() {
        actor.attemptsTo(GetRequestWithJWT.run(variables.getProperty("pathConsulPatientId")));
    }

    @Then("the patient data cannot be consulted, the api responds with a code {string}")
    public void thePatientDataCannotBeConsultedTheApiRespondsWithACode(String statusCode) {
        actor.should(
                seeThat("The api delivered code 404 correctly", ResponseCode.was(), equalTo(parseInt(statusCode))));

    }
}
