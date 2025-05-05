package tests;

import annotations.FrameworkAnnotation;
import constants.NavBarData;
import drivers.DriverFactory;
import enums.BrowserType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;

import java.util.List;

public class CareerPageTest extends BaseTest {

    protected HomePage homePage;

    @BeforeMethod
    public void openHomePage(){
        homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
    }

    @Test(description = "Verify navigation bar menus are listed, click on Company > Careers, and check career page blocks.")
    public void testTopNavBarItemsAndCareerPageSections() {
        List<String> actualNavItems = homePage.getTopNavBarItemsText();
        Assert.assertTrue(actualNavItems.containsAll(NavBarData.TOP_NAV_ITEMS), "Some navigation bar items are missing.");

        homePage.hoverTopNavBarItem("Company");

        List<String> actualDropdownItems = homePage.getCompanyDropdownItemsText();
        Assert.assertTrue(actualDropdownItems.containsAll(NavBarData.COMPANY_DROPDOWN_ITEMS), "Some Company dropdown items are missing.");

        homePage.clickItemFromCompanyDropdown("Careers");

        String url = DriverFactory.getDriver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(url.contains("career"), "Redirection to Careers page failed. Actual URL: " + url);

        CareersPage careersPage = new CareersPage(DriverFactory.getDriver());
        Assert.assertTrue(careersPage.isLocationsBlockVisible(), "Locations section is not visible.");
        Assert.assertTrue(careersPage.isTeamsBlockVisible(), "Teams section is not visible.");
        Assert.assertTrue(careersPage.isLifeAtInsiderBlockVisible(), "Life at Insider section is not visible.");
    }
}
