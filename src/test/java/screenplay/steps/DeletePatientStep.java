package screenplay.steps;

import co.com.sofka.apiPacientes.test.questions.ResponseCode;
import co.com.sofka.apiPacientes.test.task.http.DeleteWithJWT;
import co.com.sofka.apiPacientes.test.task.taskProject.CreateNewPatient;
import co.com.sofka.apiPacientes.test.task.taskProject.SaveIdPatient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.util.EnvironmentVariables;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class DeletePatientStep {
    Actor actor;
    EnvironmentVariables variables;
    @Given("a {string} is correctly authenticated, he wants to delete a patient")
    public void aIsCorrectlyAuthenticatedHeWantsToDeleteAPatient(String name) {
        actor = Actor.named(name);
        actor.attemptsTo(CreateNewPatient.run());
        actor.attemptsTo(SaveIdPatient.save());

    }

    @When("a nursing assistant knows the id of the patient to be removed")
    public void aNursingAssistantKnowsTheIdOfThePatientToBeRemoved() {

        int id= Serenity.sessionVariableCalled("documentID");
        actor.attemptsTo(DeleteWithJWT
                .delete(variables.getProperty("deletePatient")+id));

    }

    @Then("the patient is correctly eliminated from the api, api responds with code {string}")
    public void thePatientIsCorrectlyEliminatedFromTheApiApiRespondsWithCode(String statusCode) {
        actor.should(
                seeThat("The patient was eliminated,The api delivered code 200 correctly",
                        ResponseCode.was(),
                        equalTo(parseInt(statusCode))));

    }


    @When("a nursing assistant does not know the id of the patient to be deleted, enters a wrong id")
    public void aNursingAssistantDoesNotKnowTheIdOfThePatientToBeDeletedEntersAWrongId() {
        int idWrong = 233333333;
        actor.attemptsTo(DeleteWithJWT
                .delete(variables.getProperty("deletePatient")+idWrong));

    }

    @Then("no patient is eliminated and the api responds with code {string}")
    public void noPatientIsEliminatedAndTheApiRespondsWithCode(String statusCode) {
        actor.should(
                seeThat("Error! the patient could not be removed,The api delivered code 404 correctly",
                        ResponseCode.was(),
                        equalTo(parseInt(statusCode))));
    }
}
