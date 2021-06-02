package screenplay.steps;

import co.com.sofka.apiPacientes.test.data.RequestAuth;
import co.com.sofka.apiPacientes.test.data.RequestPatient;
import co.com.sofka.apiPacientes.test.model.JsonPatient;
import co.com.sofka.apiPacientes.test.questions.ResponseCode;
import co.com.sofka.apiPacientes.test.task.http.PostRequestWithJWT;
import co.com.sofka.apiPacientes.test.task.taskProject.JWTRequest;
import co.com.sofka.apiPacientes.test.task.taskProject.SaveJWT;
import io.cucumber.java.en.And;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddPatientSteps {
    Actor actor;
    EnvironmentVariables variables;
    JsonPatient patientSend;
    JsonPatient patientReceived;
    @Given("a {string} is correctly authenticated in the api")
    public void aIsCorrectlyAuthenticatedInTheApi(String name) {
        actor = Actor.named(name);
        actor.attemptsTo(JWTRequest.run(RequestAuth.getValidUser()));
        actor.attemptsTo(SaveJWT.run());
    }

    @And("the nursing assistant knows the path of the api for creating patients")
    public void theNursingAssistantKnowsThePathOfTheApiForCreatingPatients() {
        String URLBase = variables.getProperty("baseurl");
        actor.whoCan(CallAnApi.at(URLBase));
    }

    @When("the complete information of a patient is available")
    public void theCompleteInformationOfAPatientIsAvailable() {
        patientSend = RequestPatient.getPatientInformation();
        actor.attemptsTo(PostRequestWithJWT.run(
                variables.getProperty("pathPatient"),patientSend));
    }

    @Then("the patient can be created in the api, the api responds with a code {string}")
    public void thePatientCanBeCreatedInTheApiTheApiRespondsWithACode(String statusCode) {
        actor.should(
                seeThat("The api delivered code 201 correctly", ResponseCode.was(), equalTo(parseInt(statusCode)))
        );
        patientReceived = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", JsonPatient.class);
        assertThat(patientSend).isEqualTo(patientReceived);
    }
}
