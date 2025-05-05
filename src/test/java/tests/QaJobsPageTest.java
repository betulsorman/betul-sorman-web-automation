package tests;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.QaJobsPage;

import java.util.List;


public class QaJobsPageTest extends BaseTest {

    private QaJobsPage qaJobsPage;

    @BeforeMethod
    public void openQaJobsPage() {
        qaJobsPage = new QaJobsPage(DriverFactory.getDriver());
        qaJobsPage.openQaJobsPage();
    }

    @Test(description = "Filter QA jobs by Istanbul & QA and verify job list appears")
    public void shouldShowFilteredJobsList() {
        qaJobsPage.clickSeeAllQaJobs();
        qaJobsPage.filterByLocationAndDepartment("Istanbul, Turkiye", "Quality Assurance");
        Assert.assertTrue(qaJobsPage.isJobListVisible(), "Job list is not displayed after filtering.");
    }

    @Test(description = "Ensure all jobs are filtered by QA and Istanbul")
    public void shouldValidateFilteredJobAttributes() {
        qaJobsPage.clickSeeAllQaJobs();
        qaJobsPage.filterByLocationAndDepartment("Istanbul, Turkiye", "Quality Assurance");

        qaJobsPage.waitUntilAllJobsContainText("Quality Assurance");

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
            Assert.assertTrue(location.contains("Istanbul, Turkiye"),
                    "Location mismatch: " + location);
        }
    }

    @Test(description = "Verify clicking 'View Role' redirects to Lever application page")
    public void verifyRedirectToLeverApplication() {
        WebDriver driver = DriverFactory.getDriver();
        qaJobsPage.clickSeeAllQaJobs();
        qaJobsPage.filterByLocationAndDepartment("Istanbul, Turkiye", "Quality Assurance");

        String originalWindow = driver.getWindowHandle();

        qaJobsPage.waitForJobListToUpdate();
        qaJobsPage.hoverAndClickViewRoleButton();
        qaJobsPage.switchToNewWindow(originalWindow);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("lever.co"),
                "Expected to be redirected to Lever, but got: " + currentUrl);
    }
}
