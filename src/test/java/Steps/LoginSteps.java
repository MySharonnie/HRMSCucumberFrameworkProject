package Steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;

import java.io.IOException;


public class LoginSteps extends CommonMethods {


    private DataTable dataTable;

    @When("user enters a valid {string} and a valid {string}")
    public void userEntersAValidAndAValid(String username, String password) throws IOException {
        sendText(loginPage.usernameField, ConfigReader.read("username"));
        sendText(loginPage.passwordField, ConfigReader.read("password"));
    }

    @When("user clicks the login button")
    public void user_clicks_the_login_button() {
        click(loginPage.loginButton);
    }

    @Then("user is navigated to the dashboard page")
    public void user_is_navigated_to_the_dashboard_page() {
        boolean dashboardIsPresent =dashboardPage.dashboardHeading.isDisplayed();
        Assert.assertTrue(dashboardIsPresent);
    }


    @When("user enters a valid {string} leaving the password field blank")
    public void user_enters_a_valid_leaving_the_password_field_blank(String username) throws IOException {
        sendText(loginPage.usernameField, ConfigReader.read("username"));
        click(loginPage.loginButton);
    }


    @Then("user receives {string} for the empty password field")
    public void user_receives_for_the_empty_password_field(String passwordError) {
        String errorMsg = loginPage.errorMessageField.getText();
        Assert.assertEquals(errorMsg, passwordError);
    }

    @When("user leaves the username field blank and enters a valid {string}")
    public void user_leaves_the_username_field_blank_and_enters_a_valid(String password) throws IOException {
        sendText(loginPage.passwordField, ConfigReader.read("password"));
        click(loginPage.loginButton);
    }

    @Then("user receives {string} for the empty username field")
    public void user_receives_for_the_empty_username_field(String usernameError) {
        String userNameError = loginPage.errorMessageField.getText();
        Assert.assertEquals(userNameError, usernameError);
    }


    @When("user enters an invalid {string} or an invalid {string}")
    public void userEntersAnInvalidOrAnInvalid(String username, String password) {
        sendText(loginPage.usernameField, username);
        sendText(loginPage.passwordField, password);
    }

    @Then("user receives an {string} error message next to the login button")
    public void userReceivesAnErrorMessageNextToTheLoginButton(String errorMsg) {
        String invalidCred = loginPage.errorMessageField.getText();
        Assert.assertEquals(invalidCred, errorMsg);
    }
}











