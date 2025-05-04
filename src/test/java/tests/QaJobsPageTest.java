package tests;

import annotations.FrameworkAnnotation;
import drivers.DriverFactory;
import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.QaJobsPage;


import java.time.Duration;
import java.util.List;

public class QaJobsPageTest extends BaseTest {

    @Test(description = "Navigate to QA jobs, filter by Istanbul and QA, and verify job list is shown.")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testQaJobsFilteringAndListing() {
        QaJobsPage qaJobsPage = new QaJobsPage(DriverFactory.getDriver());
        qaJobsPage.openQaJobsPage();

        qaJobsPage.clickSeeAllQaJobs();
        qaJobsPage.filterByLocationAndDepartment("Istanbul, Turkiye", "Quality Assurance");

        Assert.assertTrue(qaJobsPage.isJobListVisible(), "Job list is not displayed after filtering.");
    }

    @Test(description = "Verify all jobs are filtered correctly by QA and Istanbul")
    public void verifyJobListFilteredCorrectly() {
        QaJobsPage qaJobsPage = new QaJobsPage(DriverFactory.getDriver());

        qaJobsPage.openQaJobsPage();

        qaJobsPage.clickSeeAllQaJobs();
        qaJobsPage.filterByLocationAndDepartment("Istanbul, Turkiye", "Quality Assurance");

        List<WebElement> jobList = qaJobsPage.getJobListItems();

        for (WebElement job : jobList) {
            String position = qaJobsPage.getJobPosition(job);
            String department = qaJobsPage.getJobDepartment(job);
            String location = qaJobsPage.getJobLocation(job);

            System.out.println("Position: " + position);
            System.out.println("Department: " + department);
            System.out.println("Location: " + location);

            Assert.assertTrue(position.contains("Quality Assurance"),
                    "Position mismatch: " + position);
            Assert.assertTrue(department.contains("Quality Assurance"),
                    "Department mismatch: " + department);
            Assert.assertTrue(location.contains("Istanbul, Turkey"),
                    "Location mismatch: " + location);
        }
    }

    @Test(description = "Verify clicking 'View Role' redirects to Lever application page")
    public void verifyRedirectToLeverApplication() {
        WebDriver driver = DriverFactory.getDriver();
        QaJobsPage qaJobsPage = new QaJobsPage(DriverFactory.getDriver());

        qaJobsPage.openQaJobsPage();
        qaJobsPage.clickSeeAllQaJobs();
        qaJobsPage.filterByLocationAndDepartment("Istanbul, Turkiye", "Quality Assurance");

        String originalWindow = driver.getWindowHandle();

        qaJobsPage.waitForJobListToUpdate();

        qaJobsPage.hoverOverFirstJobCard();


        qaJobsPage.clickFirstViewRoleButton();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> d.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> d.getCurrentUrl().contains("lever.co"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Redirected URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("lever.co"),
                "❌ Not redirected to Lever: " + currentUrl);
    }

}
