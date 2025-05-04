package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class CareersPage {

    private WebDriver driver;

    // Constructor
    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public boolean isCareerPageOpened() {
        return driver.getCurrentUrl().contains("/careers");
    }

    public boolean isJobListDisplayed() {
        // Burada job list elementini direkt olarak findElement ile buluyoruz
        WebElement jobList = driver.findElement(By.id("job-list"));
        return jobList.isDisplayed();
    }
}
