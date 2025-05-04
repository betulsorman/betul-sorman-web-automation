package drivers;

import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        String browser = ConfigReader.get("browser").toUpperCase();
        BrowserType browserType = BrowserType.valueOf(browser);

        switch (browserType) {
            case CHROME -> {
                WebDriverManager.chromedriver().driverVersion("136.0.7103.39").setup();
                driver.set(new ChromeDriver());
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
            }
            default -> throw new IllegalArgumentException("Tanımsız browser tipi: " + browser);
        }

        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
