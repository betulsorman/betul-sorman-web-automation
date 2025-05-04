package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.util.List;

public class QaJobsPage {

    private final WebDriver driver;

    private final By jobListItems = By.cssSelector("div.position-list-item"); // güncel class'a göre değiştirilebilir
    private final By viewRoleButtons = By.xpath("//a[contains(text(),'View Role')]");

    // Filters (örnek locators, güncel HTML’e göre kontrol etmeliyiz)
    private final By locationDropdown = By.xpath("//select[@name='location']");
    private final By departmentDropdown = By.xpath("//select[@name='department']");

    public QaJobsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isQaJobsPageOpened() {
        return driver.getCurrentUrl().contains("/quality-assurance");
    }

    public void filterJobsByLocationAndDepartment(String location, String department) {
        WaitUtils.waitForVisibility(locationDropdown, 10).sendKeys(location);
        WaitUtils.waitForVisibility(departmentDropdown, 10).sendKeys(department);
    }

    public boolean areAllJobsValid(String expectedPosition, String expectedDepartment, String expectedLocation) {
        List<WebElement> jobs = WaitUtils.waitForVisibility(jobListItems, 10).findElements(By.xpath("./.."));

        for (WebElement job : jobs) {
            String position = job.getText();
            if (!(position.contains(expectedPosition) && position.contains(expectedDepartment) && position.contains(expectedLocation))) {
                return false;
            }
        }
        return true;
    }

    public String clickViewRoleButtonAndSwitchToLever() {
        String currentWindow = driver.getWindowHandle();
        WaitUtils.waitForClickability(viewRoleButtons, 10).click();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        return driver.getCurrentUrl();
    }
}
