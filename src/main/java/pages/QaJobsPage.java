package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class QaJobsPage {

    private final WebDriver driver;

    private final By seeAllQaJobsButton = By.xpath("//a[contains(text(), 'See all QA jobs')]");
    private final By locationFilter = By.id("select2-filter-by-location-container");
    private final By departmentFilter = By.id("select2-filter-by-department-container");

    private final By activeDropdownOptions = By.cssSelector(".select2-container--open li.select2-results__option");

    private final By jobListItems = By.cssSelector("div.position-list-item");

    private final By positionTitle = By.cssSelector(".position-title");
    private final By positionDepartment = By.cssSelector(".position-department");
    private final By positionLocation = By.cssSelector(".position-location");

    private final By viewRoleButton = By.xpath("//a[contains(text(), 'View Role')]");

    public QaJobsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openQaJobsPage() {
        driver.get(ConfigReader.get("qaJobsPageUrl"));
    }

    public void clickSeeAllQaJobs() {
        WaitUtils.waitForClickability(seeAllQaJobsButton, 10).click();
    }

    public void filterByLocationAndDepartment(String location, String department) {
        selectFromDropdown(departmentFilter, department);
        selectFromDropdown(locationFilter, location);
    }


    private void selectFromDropdown(By filterLocator, String valueToSelect) {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement dropdownTrigger = WaitUtils.waitForClickability(filterLocator, 10);
        dropdownTrigger.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
            List<WebElement> options = d.findElements(activeDropdownOptions);
            return options.stream()
                    .anyMatch(opt -> opt.getText().trim().equalsIgnoreCase(valueToSelect));
        });

        List<WebElement> options = driver.findElements(activeDropdownOptions);
        for (WebElement option : options) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(valueToSelect)) {
                option.click();
                return;
            }
        }

        throw new RuntimeException("Option not found in dropdown: " + valueToSelect);
    }


    public boolean isJobListVisible() {
        return !driver.findElements(jobListItems).isEmpty();
    }

    public List<WebElement> getJobListItems() {
        return driver.findElements(jobListItems);
    }

    public String getJobPosition(WebElement jobCard) {
        return jobCard.findElement(positionTitle).getText().trim();
    }

    public String getJobDepartment(WebElement jobCard) {
        return jobCard.findElement(positionDepartment).getText().trim();
    }

    public String getJobLocation(WebElement jobCard) {
        return jobCard.findElement(positionLocation).getText().trim();
    }

    public void hoverAndClickViewRoleButton() {
        List<WebElement> jobCards = WaitUtils.waitForVisibilityOfAll(jobListItems, 10);
        if (jobCards.isEmpty()) {
            throw new RuntimeException("No job cards found!");
        }

        WebElement firstJobCard = jobCards.get(0);

        Actions actions = new Actions(driver);
        actions.moveToElement(firstJobCard).perform();

        WebElement viewRoleBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        firstJobCard.findElement(viewRoleButton)
                ));

        viewRoleBtn.click();
    }


    public void waitForJobListToUpdate() {
        By firstJobTitle = positionTitle;

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            List<WebElement> titles = driver.findElements(firstJobTitle);
            return titles.stream().anyMatch(el -> el.getText().contains("Quality Assurance"));
        });
    }

    public void switchToNewWindow(String originalWindow) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> d.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> d.getCurrentUrl().contains("lever.co"));

    }

    public void waitUntilAllJobsContainText(String expectedText) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> {
            List<WebElement> jobList = getJobListItems();
            return jobList.stream().allMatch(job -> getJobPosition(job).contains(expectedText));
        });
    }
}
