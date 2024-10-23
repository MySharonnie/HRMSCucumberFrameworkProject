package Steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

public class DashboardSteps extends CommonMethods {

    @Then("user clicks on the PIM tab")
    public void user_clicks_on_the_pim_tab() {
        WebElement pimOption=driver.findElement(By.id("menu_pim_viewPimModule"));
        pimOption.click();
    }

    @Then("user clicks on Add Employee option")
    public void user_clicks_on_add_employee_option() {
        click(dashboardPage.addEmployeeButton);
    }


}
