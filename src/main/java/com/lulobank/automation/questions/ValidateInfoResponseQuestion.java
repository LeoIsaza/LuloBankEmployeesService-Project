package com.lulobank.automation.questions;

import com.lulobank.automation.utils.ReportLuloBank;
import com.lulobank.automation.utils.ServicesUtils;
import io.cucumber.java.eo.Se;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.ArrayList;
import java.util.Arrays;

import static com.lulobank.automation.utils.GetInformationService.showResults;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ValidateInfoResponseQuestion implements Question {
    @Override
    public Object answeredBy(Actor actor) {
        ArrayList<String> informationEmployees=showResults();
        if(!informationEmployees.isEmpty() && informationEmployees.get(0).contains("Successfully!")){
            ReportLuloBank.reports("PASS", "Se realiza correctamente el evento de '"+ ServicesUtils.getTestCase()+"' en la API de empleados de Lulobank");
        }else{
            ReportLuloBank.reports("FAIL", "No fue posible realizar el evento de '"+ ServicesUtils.getTestCase()+"' en la API de empleados de Lulobank, Por favor revisar evidencias");
        }
        assertThat(true,is(not(informationEmployees.isEmpty())));
        assertThat(true, is(informationEmployees.get(0).contains("Successfully!")));
        ReportLuloBank.reports("INFO", SerenityRest.lastResponse().body().prettyPrint());

        return null;
    }

    public static ValidateInfoResponseQuestion validateInfoResponseQuestion(){
        return new ValidateInfoResponseQuestion();
    }
}
