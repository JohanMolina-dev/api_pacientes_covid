package screenplay.steps;

import co.com.sofka.apiPacientes.test.data.RequestAppointment;
import co.com.sofka.apiPacientes.test.data.RequestAuth;
import co.com.sofka.apiPacientes.test.data.RequestPatient;
import co.com.sofka.apiPacientes.test.model.JsonAppointment;
import co.com.sofka.apiPacientes.test.model.JsonPatient;
import co.com.sofka.apiPacientes.test.questions.ResponseCode;
import co.com.sofka.apiPacientes.test.task.http.PostRequestWithJWT;
import co.com.sofka.apiPacientes.test.task.taskProject.CreateNewPatient;
import co.com.sofka.apiPacientes.test.task.taskProject.JWTRequest;
import co.com.sofka.apiPacientes.test.task.taskProject.SaveIdPatient;
import co.com.sofka.apiPacientes.test.task.taskProject.SaveJWT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class NewAppointmentStep {
    Actor actor;

    EnvironmentVariables variables;
    @Given("the {string} knows the path of the api for creating patients the patient is successfully created")
    public void theKnowsThePathOfTheApiForCreatingPatientsThePatientIsSuccessfullyCreated(String name) {
        actor = Actor.named(name);
        actor.attemptsTo(CreateNewPatient.run());
    }

    @When("the information of the medical appointment is possessed")
    public void theInformationOfTheMedicalAppointmentIsPossessed() {
        actor.attemptsTo(SaveIdPatient.save());
        actor.attemptsTo(PostRequestWithJWT.run(variables.getProperty("pathAppointment"),
                RequestAppointment.getAppointmentInformation(Serenity.sessionVariableCalled("documentID"))));

    }

    @Then("the medical appointment is created in the api, the api responds with a code {string}")
    public void theMedicalAppointmentIsCreatedInTheApiTheApiRespondsWithACode(String statusCode) {
        actor.should(
                seeThat("The api delivered code 201 correctly", ResponseCode.was(), equalTo(parseInt(statusCode))));
    }
}
