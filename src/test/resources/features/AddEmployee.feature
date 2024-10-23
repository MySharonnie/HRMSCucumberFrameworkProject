Feature: Add Employee

  Background:
    When user enters a valid "<username>" and a valid "<password>"
    And user clicks the login button
    Then user is navigated to the dashboard page
    And user clicks on the PIM tab
    And user clicks on Add Employee option

  @addEmployeeByName
  Scenario: Add employee by first name, middle name, and last name
    When the user verifies a unique "<employeeID>" is generated
    And user enters "James" in the first name field, "T" in the middle name field, and "Kirk" in the last name field
    And user clicks the save button
    Then user added employee successfully



  @addEmployeeByDataTable
  Scenario: Add employee by name and unique employee ID using a datatable
    When user enters employee data using a data table
      | firstname | middlename | lastname | employeeID |
      | James     | T          | Kirk     | 7485969    |
      | Jimi      | Dope       | Hendrix  | 7411152    |
      | Janis     | Crazy      | Joplin   | 1452369    |

    @addEmployeeBlankFirstName
    Scenario: Add employee leaving the first name field blank
      When user adds "Fox" in the last name field
      And user clicks the save button
      Then "Required" displays under the first name field

      @addEmployeeBlankLastName
      Scenario: Add employee leaving the last name field blank
        When user enters the "Sally" in the first name field
        And user clicks the save button
        Then "Required" displays under the last name field

        @addEmployeeWithExistingEmployeeID
        Scenario: Add an employee using an existing employee's name and employeeID
          When user enters "John A" in the first name field, "12345" in the last name field and "9608586" in the employee ID field
          And user clicks the save button
          Then user receives "Failed To Save: Employee Id Exists" error message



