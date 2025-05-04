package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void open() {
        driver.get("https://useinsider.com/");
    }

    public boolean isHomePageOpened() {
        return driver.getTitle().contains("Insider");
    }

    // Navigate to CareersPage
    public CareersPage navigateToCareers() {
        // Menüyü ve 'Careers' linkini tıklıyoruz
        click(By.xpath("//nav//a[text()='Company']"));
        click(By.xpath("//nav//a[text()='Careers']"));
        return new CareersPage(driver);
    }

    // Helper method for click actions
    private void click(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }
}
