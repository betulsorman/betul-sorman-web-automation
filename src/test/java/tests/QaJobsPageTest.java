package tests;

import annotations.FrameworkAnnotation;
import drivers.DriverFactory;
import enums.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.QaJobsPage;
import pages.CareersPage;
import pages.HomePage;

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
}
