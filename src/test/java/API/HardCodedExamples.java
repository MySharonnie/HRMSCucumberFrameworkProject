package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExamples {

    //baseURI = baseURL; don't include endpoints

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String employee_id;

    //prepare the request


    @Test
    public void aCreateAnEmployee() {

        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzEyNTM5MjIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTczMTI5NzEyMiwidXNlcklkIjoiNjkxNCJ9.yt_CTjMeaC_aJWWQwu9mU39etOqL_oeV9oi8BJdbf9w").
                body("{\n" +
                        "    \"emp_firstname\":\"Bob\",\n" +
                        "    \"emp_lastname\":\"Builder\",\n" +
                        "    \"emp_middle_name\": \"The\",\n" +
                        "    \"emp_gender\":\"M\",\n" +
                        "    \"emp_birthday\":\"2024-03-31\",\n" +
                        "    \"emp_status\":\"employeed\",\n" +
                        "    \"emp_job_title\":\"software engineer\"\n" +
                        "}");

        //response will be returned when we send the request
        Response response = request.when().post("/createEmployee.php");

        //print response
        response.prettyPrint();

        //validate status code
        response.then().assertThat().statusCode(201);

        //validate key value pairs; hamcrest matches
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Bob"));

        //retrieve empID after creating the employee. Store it in a global variable
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println("The employee ID is " + employee_id);
    }

    @Test
    public void bGetCreatedEmployee() {

        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzEyNTM5MjIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTczMTI5NzEyMiwidXNlcklkIjoiNjkxNCJ9.yt_CTjMeaC_aJWWQwu9mU39etOqL_oeV9oi8BJdbf9w").
                queryParam("employee_id", employee_id);

        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        String tempEmpID=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(tempEmpID, employee_id);
    }

    @Test
    public void cUpdateEmployee(){
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzEyNTM5MjIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTczMTI5NzEyMiwidXNlcklkIjoiNjkxNCJ9.yt_CTjMeaC_aJWWQwu9mU39etOqL_oeV9oi8BJdbf9w").
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",n" +
                        "  \"emp_firstname\": \"gisel\",\n" +
                        "  \"emp_lastname\": \"baker\",\n" +
                        "  \"emp_middle_name\": \"arif\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1990-02-15\",\n" +
                        "  \"emp_status\": \"employeed\",\n" +
                        "  \"emp_job_title\": \"Automatin engineer\"\n" +
                        "}");

        Response response = request.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);

    }


    //get calls , automate all calls

}

