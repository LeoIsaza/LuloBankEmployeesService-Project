package com.lulobank.automation.tasks;

import com.lulobank.automation.utils.ReportLuloBank;
import com.lulobank.automation.utils.ServicesUtils;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;


public class ConsultEmployeesService implements Task{
    String operation, id, name, salary;

    public ConsultEmployeesService(String operation, String id, String name, String salary) {
        this.operation = operation;
        this.id = id;
        this.salary = salary;
        this.name = name;
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        ReportLuloBank.startReport(operation);
        actor.attemptsTo(Get.resource(ServicesUtils.readEndPoints(operation,id,name,salary)).with(requestSpecification -> requestSpecification));
    }
    public static ConsultEmployeesService consultEmployeesService(String operation, String id, String name, String salary){
        return Tasks.instrumented(ConsultEmployeesService.class,operation,id,name,salary);
    }
}
