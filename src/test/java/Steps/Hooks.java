package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

import java.io.IOException;

public class Hooks extends CommonMethods {

    @Before
    public void startApp() throws IOException {
        openBrowserAndLaunchApp();
    }

    @After
    public void endApp(Scenario scenario) throws IOException {
        byte[] pic;
        if (scenario.isFailed()) {
            pic = captureScreenshot("failed/" + scenario.getName());
        } else {
            pic = captureScreenshot("passed/"+scenario.getName());
        }

        scenario.attach(pic, "image/png", scenario.getName());

        closerBrowser();
    }
}
