@ConsultEmployeesService

Feature:As a bank,
  I want to perform operations on the employee API,
  to obtain important information from them

  Scenario Outline: Employee API Query
    Given that the bank requires to carry out an operation on the employees
    When make an API query to <Operation> with the data <Id>,<Name>,<Salary>
    Then validate and shows the information obtained

    Examples:
      | Operation | Id | Name | Salary |
    ##@externaldata@src/main/resources/com/lulobank/automation/DataEntrys/DataEntry_Api_Operations.xlsx@Hoja1

