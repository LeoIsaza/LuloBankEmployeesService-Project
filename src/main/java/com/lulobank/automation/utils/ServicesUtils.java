package com.lulobank.automation.utils;

import net.serenitybdd.rest.SerenityRest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServicesUtils {
    private static String operation;

    public static String readEndPoints(String operation, String id, String name, String salary) throws IOException {
        Properties propiedades = new Properties();
        propiedades.load(new FileReader("serenity.properties"));
        String EndPoint = "";
        String msg = "";
        ServicesUtils.operation = operation;
        switch (operation) {
            case "Consultar Salario de los empleados":
                EndPoint = propiedades.getProperty("endpointConsultEmployees");
                break;
            case "Consultar si es empleado de LuloBank":
                EndPoint = propiedades.getProperty("endpointConsultEmploye");
                if (!id.equals("NA")) {
                    EndPoint = EndPoint.concat(id);
                    msg = " para el empleado con ID Número: " + id + ".";
                }
                break;
            case "Eliminar empleado":
                EndPoint = propiedades.getProperty("endpointDeleteEmploye");
                if (!id.equals("NA")) {
                    EndPoint = EndPoint.concat(id);
                    msg = " para el empleado con ID Número: " + id + ".";
                }
                break;
            case "Crear nuevo empleado":
                EndPoint = propiedades.getProperty("endpointCreateEmploye");
                msg = " para el empleado con ";
                if (!id.equals("NA")) {
                    EndPoint = EndPoint.concat("?id=" + id);
                    msg = msg + "ID Número: " + id + "- ";
                }
                if (!name.equals("NA")) {
                    EndPoint = EndPoint.concat("&employee_name=" + name.trim());
                    msg = msg + "Nombre: " + name + "- ";
                }
                if (!salary.equals("NA")) {
                    EndPoint = EndPoint.concat("&employee_salary=" + salary);
                    msg = msg + "Salario: " + salary + ".";
                }
                if (msg.length() <= 20) {
                    ReportLuloBank.reports("FAIL", "No se puede agregar el usuario debido a que no se ingresaron Datos: " +
                            "Name=NA, ID=NA, SALARY=NA");
                }
                break;
        }
        ReportLuloBank.reports("INFO", "Se inicia con la Operación de '" + operation + "' en la API de LuloBank, " + msg);
        System.out.println(EndPoint);
        return EndPoint;
    }

    public static String getUrlApi() throws IOException {
        Properties propiedades = new Properties();
        propiedades.load(new FileReader("serenity.properties"));
        return propiedades.getProperty("Api");
    }

    public static String getTestCase() {
       return  operation;
    }
}