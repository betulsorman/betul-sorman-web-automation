package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class HomePage {

    private final WebDriver driver;

    // Locators
    private final By companyMenu = By.xpath("//a[contains(@class, 'nav-link') and text()='Company']");
    private final By careersLink = By.xpath("//a[text()='Careers']");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void open() {
        driver.get("https://useinsider.com/");
    }

    public boolean isHomePageOpened() {
        return driver.getTitle().toLowerCase().contains("insider");
    }

    public void clickCompanyMenu() {
        WaitUtils.waitForClickability(companyMenu, 10).click();
    }

    public void clickCareersLink() {
        WaitUtils.waitForClickability(careersLink, 10).click();
    }
}
