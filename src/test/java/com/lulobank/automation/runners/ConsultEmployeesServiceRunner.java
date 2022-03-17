package com.lulobank.automation.runners;

import com.lulobank.automation.utils.BeforeSuite;
import com.lulobank.automation.utils.DataToFeature;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(RunnerPersonalizado.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue="com.lulobank.automation.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@ConsultEmployeesService")
public class ConsultEmployeesServiceRunner {
    @BeforeSuite
    public static void test() throws InvalidFormatException, IOException {
        DataToFeature.overrideFeatureFiles("src/test/resources/features");
    }
}
