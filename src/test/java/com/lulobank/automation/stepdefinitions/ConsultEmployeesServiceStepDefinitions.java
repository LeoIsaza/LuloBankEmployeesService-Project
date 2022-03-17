package com.lulobank.automation.stepdefinitions;

import com.lulobank.automation.questions.ValidateInfoResponseQuestion;
import com.lulobank.automation.questions.ValidateStatusResponseQuestion;
import com.lulobank.automation.tasks.ConsultEmployeesService;
import com.lulobank.automation.utils.ReportLuloBank;
import com.lulobank.automation.utils.ServicesUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.parsing.Parser;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.After;

import java.io.IOException;

public class ConsultEmployeesServiceStepDefinitions {
    Actor actor = Actor.named("Bank");

    @Given("^that the bank requires to carry out an operation on the employees$")
    public void PerformEmployeesApiQuery () throws IOException {
        actor.whoCan(CallAnApi.at(ServicesUtils.getUrlApi()));
        SerenityRest.setDefaultParser(Parser.JSON);
        OnStage.setTheStage(new OnlineCast());
    }

    @When("^make an API query to \"([^\"]*)\" with the data \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void getResponseFromApi(String operation, String id, String name, String Salary) {
        actor.attemptsTo(ConsultEmployeesService.consultEmployeesService(operation, id, name, Salary));
    }

    @Then("^validate and shows the information obtained$")
    public void valdateEmployeesInformation() {
        actor.asksFor(ValidateStatusResponseQuestion.consultEmployeesServiceQuestions());
        actor.asksFor(ValidateInfoResponseQuestion.validateInfoResponseQuestion());
    }

    @After
    public void after() {
        ReportLuloBank.finishReport();
    }
}
