Feature: API Workflow

  Background:
    Given a JWT bearer token is generated


  @api
  Scenario: Create employee
    Given a request is prepared for creating the employee
    When a POST call is made to create the employee
    Then the status code for this request is 201
    Then the employee id "Employee.employee_id" is stored and values are validated


  @api
  Scenario: Get the created employee
    Given a request is prepared to get the created employee
    When a get call is made to get the created employee
    Then the status code for the GET call is 200
    And the employee has the same employee id "employee.employee_id" as the global empID
    And the data coming from the get call should equal to the data used in the post call
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title     |
      | Bob           | Builder      | The             | Male       | 2024-03-31   | employeed  | software engineer |

  @createEmployee
  Scenario: create employee using json payload
    Given a request is prepared for creating the employee using json payload
    When a POST call is made to create the employee
    Then the status code for this request is 201
    Then the employee id "Employee.employee_id" is stored and values are validated

  @dynamicjson
  Scenario: create employee using more dynamic json payload
    Given a request is prepared using data "Robert", "The", "Bruce", "M", "1987-03-31", "employed", "software engineer"
    When a POST call is made to create the employee
    Then the status code for this request is 201
    Then the employee id "Employee.employee_id" is stored and values are validated

  @employee
  Scenario: Update an employee using dynamic json payload
    Given a request is prepared using data "Bob", "The", "Bulldozer", "M", "1990-03-31", "employed", "software engineer"
    When a PUT call is made to update an existing employee
    Then the status code for this request is 200
    Then the updated employee information "Employee.emp_lastname", "Employee.emp_birthday", and "Employee.emp_status" are stored and values are validated


  @employee
  Scenario: Get the updated employee information
    Given a request is prepared to get the updated employee
    When a GET call is made to get the updated employee information
    Then the status code for the GET call is 200
    And the employee has the same employee id "employee.employee_id" as the global empID
    And the data coming from the GET call should equal to the data used in the PUT call
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title     |
      | Bob           | Bulldozer    | The             | M          | 1990-03-31   | employed   | software engineer |


