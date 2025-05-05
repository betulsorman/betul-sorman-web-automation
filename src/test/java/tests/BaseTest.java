package tests;

import drivers.DriverFactory;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.ConfigReader;

import java.util.List;

@Listeners(TestListener.class)
public class BaseTest {

    private final By cookieAcceptButton = By.id("wt-cli-accept-all-btn");

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));

        acceptCookiesIfVisible(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    private void acceptCookiesIfVisible(WebDriver driver) {
        List<WebElement> cookieButtons = driver.findElements(cookieAcceptButton);
        if (!cookieButtons.isEmpty() && cookieButtons.get(0).isDisplayed()) {
            cookieButtons.get(0).click();
        }
    }
}
