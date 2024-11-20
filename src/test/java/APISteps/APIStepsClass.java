package APISteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIStepsClass {

    RequestSpecification request;
    Response response;
    public static String employee_id;
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT bearer token is generated")
    public void a_jwt_bearer_token_is_generated() {
         request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body("{\n" +
                        "    \"email\":\"RonnieSyntax@yahoo.com\",\n" +
                        "    \"password\": \"djsjdjfnejafnoienan\"\n" +
                        "}");

        response = request.when().post(APIConstants.GENERATE_TOKEN);
        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }

    @Given("a request is prepared for creating the employee")
    public void a_request_is_prepared_for_creating_the_employee() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstants.createEmployeeJsonPayload());
    }

    @When("a POST call is made to create the employee")
    public void a_post_call_is_made_to_create_the_employee() {
         response = request.when().post(APIConstants.CREATE_EMPLOYEE);
    }

    @Then("the status code for this request is {int}")
    public void the_status_code_for_this_request_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} is stored and values are validated")
    public void the_employee_id_is_stored_and_values_are_validated(String empPath) {
        employee_id = response.jsonPath().getString(empPath);
        System.out.println("The employee ID is " + employee_id);
        response.then().assertThat().body("Employee.emp_firstname", equalTo("asana"));
    }

    @Given("a request is prepared to get the created employee")
    public void a_request_is_prepared_to_get_the_created_employee() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                queryParam("employee_id", employee_id);
    }

    @When("a get call is made to get the created employee")
    public void a_get_call_is_made_to_get_the_created_employee() {
        Response response = request.when().get(APIConstants.GET_ONE_EMPLOYEE);
    }

    @Then("the employee has the same employee id {string} as the global empID")
    public void the_employee_has_the_same_employee_id_as_the_global_emp_id(String empID) {
        String temporaryEmpID = response.jsonPath().getString(empID);
        Assert.assertEquals(employee_id, temporaryEmpID);
    }

    @Then("the data coming from the get call should equal to the data used in the post call")
    public void the_data_coming_from_the_get_call_should_equal_to_the_data_used_in_the_post_call
            (io.cucumber.datatable.DataTable dataTable) {

        //data is coming from feature file
        List<Map<String, String>> expectedData = dataTable.asMaps();

        //data coming from response body - getting all the values from the object "employee"
        Map<String, String> actualData = response.jsonPath().get("employee");

        //retrieve the keys in a way there are no duplicates
        for (Map<String, String> employeeData : expectedData) {
            Set<String> keys = employeeData.keySet();
            for (String key : keys) {
                String expectedValue = employeeData.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

    @Then("the status code for the GET call is {int}")
    public void theStatusCodeForTheGetCallIs(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Given("a request is prepared for creating the employee using json payload")
    public void aRequestIsPreparedForCreatingTheEmployeeUsingJsonPayload() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @Given("a request is prepared using data {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void a_request_is_prepared_using_data(String firstName, String lastName, String middleName, String gender, String birthday, String status, String jobTitle) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(firstName, lastName, middleName, gender, birthday, status, jobTitle));
    }

    @When("a PUT call is made to update an existing employee")
    public void a_put_call_is_made_to_update_an_existing_employee() {
        Response response = request.when().put(APIConstants.UPDATE_EMPLOYEE);
    }

    @Given("a request is prepared to get the updated employee")
    public void aRequestIsPreparedToGetTheUpdatedEmployee() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                queryParam("employee_id", employee_id);
    }

    @And("the data coming from the GET call should equal to the data used in the PUT call")
    public void theDataComingFromTheGetCallShouldEqualToTheDataUsedInThePutCall
            (io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> expectedEmployeeData = dataTable.asMaps();

        Map<String, String> actualEmployeeData = response.jsonPath().get("employee");

        for (Map<String, String> employeeData : expectedEmployeeData) {

            Set<String> keys = employeeData.keySet();
            for (String key : keys) {
                String expectedValue = employeeData.get(key);
                String actualValue = actualEmployeeData.get(key);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

    @When("a GET call is made to get the updated employee information")
    public void aGETCallIsMadeToGetTheUpdatedEmployeeInformation() {
        Response response = request.when().get(APIConstants.GET_ONE_EMPLOYEE);
    }

    @Then("the updated employee information {string}, {string}, and {string} are stored and values are validated")
    public void theUpdatedEmployeeInformationAndAreStoredAndValuesAreValidated(String lastName, String birthday, String status) {

        String updatedLastName = response.jsonPath().getString(lastName);
        String updatedBirthday = response.jsonPath().getString(birthday);
        String updatedStatus = response.jsonPath().getString(status);

        response.then().assertThat().body("Employee.emp_lastname", equalTo(updatedLastName));
        response.then().assertThat().body("Employee.emp_birthday", equalTo(updatedBirthday));
        response.then().assertThat().body("Employee.emp_status", equalTo(updatedStatus));
    }
}
