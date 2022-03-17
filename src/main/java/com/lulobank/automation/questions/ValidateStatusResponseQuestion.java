package com.lulobank.automation.questions;

import com.lulobank.automation.utils.ReportLuloBank;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateStatusResponseQuestion implements Question {

    @Override
    public Object answeredBy(Actor actor) {
        if(String.valueOf(SerenityRest.lastResponse().statusCode()).equals("200")){
            ReportLuloBank.reports("PASS", "Se obtuvo el código de respuesta: "+SerenityRest.lastResponse().statusCode());
        }else{
            ReportLuloBank.reports("FAIL", "Se obtuvo el código de respuesta: "+SerenityRest.lastResponse().statusCode());
            ReportLuloBank.reports("FAIL",SerenityRest.lastResponse().getBody().prettyPrint());
        }
        assertThat(SerenityRest.lastResponse().statusCode(), is(200));
        return null;
    }
    public static ValidateStatusResponseQuestion consultEmployeesServiceQuestions(){
        return new ValidateStatusResponseQuestion();
    }
}
