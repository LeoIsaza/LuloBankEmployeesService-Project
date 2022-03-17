package com.lulobank.automation.utils;

import net.serenitybdd.rest.SerenityRest;
import java.util.ArrayList;

public class GetInformationService {
    static ArrayList<String> informationEmployees = new ArrayList<>();

    public static ArrayList<String> showResults() {
        switch (ServicesUtils.getTestCase()) {
            case "Consultar Salario de los empleados":
                showResultsEmployeesEndPoint();
                break;
            case "Consultar si es empleado de LuloBank":
                showResultsEmployeEndPoint();
                break;
            case "Eliminar empleado":
                showResultsDeleteEmployeEndPoint();
                break;
            case "Crear nuevo empleado":
                showResultsCreateEmployeEndPoint();
                break;
        }
        return informationEmployees;
    }

    public static ArrayList<String> showResultsEmployeesEndPoint() {
        String message = SerenityRest.lastResponse().getBody().jsonPath().getString("message");
        String[] ids = SerenityRest.lastResponse().getBody().jsonPath().getString("data.id").split(",");
        String[] ages = SerenityRest.lastResponse().getBody().jsonPath().getString("data.employee_age").split(",");
        String[] salaries = SerenityRest.lastResponse().getBody().jsonPath().getString("data.employee_salary").split(",");
        ReportLuloBank.reports("INFO", "SE INICIA LA EXTRACIÓN DE INFORMACIÓN DE EMPLEADOS PARA EL AREA DE INGENIERIA DE LULOBANK");
        int idQunantity = ids.length;
        int ageAvg = 0;
        Double salaryAvg = 0.0;
        for (int i = 0; i < ids.length; i++) {
            ageAvg = ageAvg + Integer.parseInt(ages[i].replace("[", "").replace("]", "").trim());
            salaryAvg = salaryAvg + Integer.parseInt(salaries[i].replace("[", "").replace("]", "").trim());
        }
        ReportLuloBank.reports("INFO", "NUMERO DE EMPLEADOS:             " + idQunantity);
        ReportLuloBank.reports("INFO", "PROMEDIO DE EDAD:                " + ageAvg / idQunantity + " AÑOS");
        ReportLuloBank.reports("INFO", "PAGO NOMINA TOTAL PARA ESTE MES: " + salaryAvg  + " COP");
        informationEmployees.add(String.valueOf(message));
        informationEmployees.add(String.valueOf(ageAvg));
        informationEmployees.add(String.valueOf(salaryAvg));
        return informationEmployees;
    }

    public static ArrayList<String> showResultsEmployeEndPoint() {
        String name = "", salary = "";
        String message = SerenityRest.lastResponse().getBody().jsonPath().getString("message");
        ReportLuloBank.reports("INFO", "SE INICIA LA VERIFICACIÓN DE LA IMFORMACIÓN DEL EMPLEADO");
        try {
            String id = SerenityRest.lastResponse().getBody().jsonPath().getString("id");
            name = SerenityRest.lastResponse().getBody().jsonPath().getString("data.employee_name");
            salary = SerenityRest.lastResponse().getBody().jsonPath().getString("data.employee_salary");
            if (!id.equals("null")) {
                ReportLuloBank.reports("PASS", "El empleado si se encuentra registrado en la API de LuloBank, con los siguientes datos:");
                ReportLuloBank.reports("INFO", "id: " + id);
                ReportLuloBank.reports("INFO", "NOMBRE: " + name);
                ReportLuloBank.reports("INFO", "SALARIO: " + salary);
            }else{
                ReportLuloBank.reports("WARNING", "El empleado consultado no se encuenra inscrito en la Nomina de LuloBank");
            }
        } catch (Exception e) {
            ReportLuloBank.reports("WARNING", "El usuario consultado no hace parte de la Nomina de LuloBank");
        }
        informationEmployees.add(String.valueOf(message));
        informationEmployees.add(String.valueOf(name));
        informationEmployees.add(String.valueOf(salary));
        return informationEmployees;
    }

    public static ArrayList<String> showResultsDeleteEmployeEndPoint() {
        String message = SerenityRest.lastResponse().getBody().jsonPath().getString("message");
        String id = SerenityRest.lastResponse().getBody().jsonPath().getString("data");
        ReportLuloBank.reports("INFO", "SE INICIA LA VERIFICACIÓN DE LA ELIMINACIÓN DEL EMPLEADO");
        if (message.contains("Successfully!")) {
            ReportLuloBank.reports("PASS", "Se elimina el empleado con Id: " + id + " corretamente!");
        } else {
            ReportLuloBank.reports("FAILED", "No se pudo eliminar el empleado con id: " + id + " corretamente!");
        }
        informationEmployees.add(message);
        return informationEmployees;
    }

    public static ArrayList<String> showResultsCreateEmployeEndPoint() {
        String message = SerenityRest.lastResponse().getBody().jsonPath().getString("message");
        String name = SerenityRest.lastResponse().getBody().jsonPath().getString("data.employee_name");
        String salary = SerenityRest.lastResponse().getBody().jsonPath().getString("data.employee_salary");
        String id = SerenityRest.lastResponse().getBody().jsonPath().getString("id");
        ReportLuloBank.reports("INFO", "SE INICIA LA VERIFICACIÓN DE LA CREACIÓN DEL EMPLEADO");
        if (message.contains("Successfully!")) {
            ReportLuloBank.reports("PASS", "Se crea el usuario con Nombre: " + name + " y " + salary + " corretamente!");
            ReportLuloBank.reports("WARNING", "El Id registrado es el " + id + ", el cual fue asignado dinamicamente por la API");
        } else {
            ReportLuloBank.reports("FAILED", "No se pudo crear el empleado " + name + " corretamente!");
        }
        informationEmployees.add((message));
        informationEmployees.add(String.valueOf(name));
        informationEmployees.add(String.valueOf(salary));
        return informationEmployees;
    }
}
