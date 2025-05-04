package tests;

import annotations.FrameworkAnnotation;
import drivers.DriverFactory;
import enums.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import pages.QaJobsPage;

public class QaJobsPageTest extends BaseTest {

    @Test(description = "Filtreleme sonrası tüm işler 'Quality Assurance' ve 'Istanbul' içeriyor mu?")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testQaJobsFilteredCorrectly() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
        homePage.clickCompanyMenu();
        homePage.clickCareersLink();

        CareersPage careersPage = new CareersPage(DriverFactory.getDriver());
        QaJobsPage qaJobsPage = careersPage.navigateToQaJobs();

        qaJobsPage.filterJobsByLocationAndDepartment("Istanbul, Turkey", "Quality Assurance");

        boolean allValid = qaJobsPage.areAllJobsValid("Quality Assurance", "Quality Assurance", "Istanbul, Turkey");
        Assert.assertTrue(allValid, "İş ilanları doğru filtrelenmedi veya beklenen değerleri içermiyor.");
    }

    @Test(description = "'View Role' butonuna tıklandığında Lever sayfası açılıyor mu?")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testViewRoleRedirectsToLever() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
        homePage.clickCompanyMenu();
        homePage.clickCareersLink();

        CareersPage careersPage = new CareersPage(DriverFactory.getDriver());
        QaJobsPage qaJobsPage = careersPage.navigateToQaJobs();

        String leverUrl = qaJobsPage.clickViewRoleButtonAndSwitchToLever();
        Assert.assertTrue(leverUrl.contains("lever.co"), "View Role butonu Lever'a yönlendirmedi!");
    }
}
