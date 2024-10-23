package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver;

    public static void openBrowserAndLaunchApp() throws IOException {
        String browserChoice = ConfigReader.read("browser");
        switch (browserChoice) {

            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser choice");
        }

        driver.manage().window().maximize();

        driver.get(ConfigReader.read("url"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        initialPageObjects();

    }

    public static void closerBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void sendText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait;
    }

    public static void waitUntilElementIsClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitUntilElementIsClickable(element);
        element.click();
    }

    public static void selectFromDropdownByIndex(WebElement dropdown, int index) {
        Select s = new Select(dropdown);
        s.selectByIndex(index);
    }

    public static void selectFromDropdownByValue(WebElement dropdown, String value) {
        Select s = new Select(dropdown);
        s.selectByValue(value);
    }

    public static void selectFromDropdownByVisibleText(WebElement dropdown, String text) {
        Select s = new Select(dropdown);
        s.selectByVisibleText(text);
    }

    public static byte[] captureScreenshot(String fileName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(Constants.PATH_TO_SCREENSHOTS + fileName + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;

    }

    public static String getTimeStamp(String pattern) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }


}


