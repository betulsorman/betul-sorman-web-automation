package tests;

import annotations.FrameworkAnnotation;
import drivers.DriverFactory;
import enums.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;

public class CareerPageTest extends BaseTest {

    @Test(description = "Careers sayfasındaki bloklar görünüyor mu?")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testCareerPageSectionsAreVisible() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
        homePage.clickCompanyMenu();
        homePage.clickCareersLink();

        CareersPage careersPage = new CareersPage(DriverFactory.getDriver());

        Assert.assertTrue(careersPage.isCareerPageOpened(), "Careers sayfası açılmadı!");
        Assert.assertTrue(careersPage.isLocationsBlockVisible(), "Locations bloğu görünmüyor!");
        Assert.assertTrue(careersPage.isTeamsBlockVisible(), "Teams bloğu görünmüyor!");
        Assert.assertTrue(careersPage.isLifeAtInsiderBlockVisible(), "Life at Insider bloğu görünmüyor!");
    }

    @Test(description = "'See all QA jobs' ile QA jobs sayfasına geçiliyor mu?")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testNavigateToQaJobsPage() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
        homePage.clickCompanyMenu();
        homePage.clickCareersLink();

        CareersPage careersPage = new CareersPage(DriverFactory.getDriver());
        Assert.assertTrue(careersPage.isCareerPageOpened());

        careersPage.navigateToQaJobs();
        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("quality-assurance"));
    }
}
