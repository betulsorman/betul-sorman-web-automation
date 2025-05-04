package tests;

import annotations.FrameworkAnnotation;
import drivers.DriverFactory;
import enums.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test(description = "Insider ana sayfa doğru açılıyor mu?")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testHomePageLoadsSuccessfully() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isHomePageOpened(), "Ana sayfa yüklenemedi.");
    }

    @Test(description = "Company > Careers menüsüne tıklanabiliyor mu?")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testCompanyToCareersNavigation() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
        homePage.clickCompanyMenu();
        homePage.clickCareersLink();
        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("careers"), "Careers sayfasına yönlendirme başarısız.");
    }
}
