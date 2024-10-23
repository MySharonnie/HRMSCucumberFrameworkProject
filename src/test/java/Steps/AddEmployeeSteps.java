package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {


    @When("user clicks the save button")
    public void user_clicks_the_save_button() {
        click(addEmployeePage.saveButton);
        getWait();
    }

    @Then("user added employee successfully")
    public void user_added_employee_successfully() {
        WebElement addSuccessfully = driver.findElement(By.xpath("//h1[text()='Personal Details']"));
        String personalDetailsHeading = addSuccessfully.getText();
        Assert.assertEquals(personalDetailsHeading, "Personal Details");
    }

    @When("user enters employee data using a data table")
    public void userEntersEmployeeDataUsingADataTable(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeeData = dataTable.asMaps();
        for (Map<String, String> employee : employeeData) {
            sendText(addEmployeePage.firstNameField, employee.get("firstname"));
            sendText(addEmployeePage.middleNameField, employee.get("middlename"));
            sendText(addEmployeePage.lastNameField, employee.get("lastname"));
            sendText((addEmployeePage.employeeID), employee.get("employeeID"));

            click(addEmployeePage.saveButton);
            WebElement pimOption = driver.findElement(By.xpath("//b[text()='PIM']"));
            click(dashboardPage.addEmployeeButton);
        }

    }

    @When("user adds {string} and {string}")
    public void user_adds_and(String lastName, String employeeID) {
        sendText(addEmployeePage.lastNameField, "lastname");
        sendText((addEmployeePage.employeeID), "employeeID");
        click(addEmployeePage.saveButton);
    }

    @Then("user receives an error message that first name is required")
    public void user_receives_an_error_message_that_first_name_is_required() {
        WebElement firstNameErrorMsg = driver.findElement(By.xpath("//span[@for='firstName']"));
        String blankFirstName = firstNameErrorMsg.getText();
        Assert.assertEquals(blankFirstName, "Required");
    }

    @When("user enters the {string} in the first name field")
    public void user_enters_the_in_the_first_name_field(String firstName) {
        sendText(addEmployeePage.firstNameField, firstName);
        sendText(addEmployeePage.lastNameField, "");
    }


    @When("user adds {string} in the last name field")
    public void user_adds_in_the_last_name_field(String lastname) {
        sendText(addEmployeePage.lastNameField, lastname);
        sendText(addEmployeePage.firstNameField, "");
        click(addEmployeePage.saveButton);
    }

    @Then("{string} displays under the first name field")
    public void displays_under_the_first_name_field(String error) {
        WebElement reqFirstName = driver.findElement(By.xpath("//span[@for='firstName']"));
        String reqTxt = reqFirstName.getText();
        Assert.assertEquals(reqTxt, error);
    }

    @Then("{string} displays under the last name field")
    public void displaysUnderTheLastNameField(String error1) {
        WebElement reqFirstName = driver.findElement(By.xpath("//span[@for='lastName']"));
        String reqTxt = reqFirstName.getText();
        Assert.assertEquals(reqTxt, error1);
    }

    @When("user enters {string} in the first name field, {string} in the last name field and {string} in the employee ID field")
    public void userEntersInTheFirstNameFieldInTheLastNameFieldAndInTheEmployeeIDField(String firstname, String lastname, String employeeID) {
        sendText(addEmployeePage.firstNameField, firstname);
        sendText(addEmployeePage.lastNameField, lastname);
        addEmployeePage.employeeID.clear();
        sendText(addEmployeePage.employeeID, employeeID);

    }

    @Then("user receives {string} error message")
    public void userReceivesErrorMessage(String failedToSave) {
        WebElement failedToSaveError = driver.findElement(By.xpath("//div[@class='message warning fadable']"));
        boolean saveError = failedToSaveError.isDisplayed();
        Assert.assertTrue(saveError);


    }

    @When("the user verifies a unique {string} is generated")
    public void theUserVerifiesAUniqueIsGenerated(String employeeId) {
        WebElement id = driver.findElement(By.id("employeeId"));
        String empID=id.getText();
        Assert.assertNotNull(empID);
    }

    @And("user enters {string} in the first name field, {string} in the middle name field, and {string} in the last name field")
    public void userEntersInTheFirstNameFieldInTheMiddleNameFieldAndInTheLastNameField(String firstname, String middlename, String lastname) {
        sendText(addEmployeePage.firstNameField, firstname);
        sendText(addEmployeePage.middleNameField, middlename);
        sendText(addEmployeePage.lastNameField, lastname);
    }
}


