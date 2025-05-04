package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class CareersPage {

    private final WebDriver driver;

    // Locators
    private final By locationsBlock = By.xpath("//section[contains(@class,'career-location')]");
    private final By teamsBlock = By.xpath("//section[contains(@class,'career-position')]");
    private final By lifeAtInsiderBlock = By.xpath("//section[contains(@class,'career-life')]");

    private final By seeAllQaJobsButton = By.xpath("//a[contains(text(), 'See all QA jobs')]");

    // Constructor
    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    // Validations
    public boolean isCareerPageOpened() {
        return driver.getCurrentUrl().contains("/careers");
    }

    public boolean isLocationsBlockVisible() {
        WebElement el = WaitUtils.waitForVisibility(locationsBlock, 10);
        return el.isDisplayed();
    }

    public boolean isTeamsBlockVisible() {
        WebElement el = WaitUtils.waitForVisibility(teamsBlock, 10);
        return el.isDisplayed();
    }

    public boolean isLifeAtInsiderBlockVisible() {
        WebElement el = WaitUtils.waitForVisibility(lifeAtInsiderBlock, 10);
        return el.isDisplayed();
    }

    // Navigation
    public QaJobsPage navigateToQaJobs() {
        WaitUtils.waitForClickability(seeAllQaJobsButton, 10).click();
        return new QaJobsPage(driver);
    }
}
