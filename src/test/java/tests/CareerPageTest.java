package tests;

import annotations.FrameworkAnnotation;
import drivers.DriverFactory;
import enums.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;

import java.util.List;

public class CareerPageTest extends BaseTest {

    @Test(description = "Verify navigation bar menus are listed, click on Company > Careers, and check career page blocks.")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testTopNavBarItemsAndCareerPageSections() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();

        List<String> actualNavItems = homePage.getTopNavBarItemsText();
        List<String> expectedNavItems = List.of("Why Insider", "Platform", "Solutions", "Customers", "Resources", "Company", "Explore Insider");
        Assert.assertTrue(actualNavItems.containsAll(expectedNavItems), "Some navigation bar items are missing.");

        homePage.hoverTopNavBarItem("Company");

        List<String> actualDropdownItems = homePage.getCompanyDropdownItemsText();
        List<String> expectedDropdownItems = List.of("About Us", "Newsroom", "Partnerships", "Integrations", "Careers", "Contact Us");
        Assert.assertTrue(actualDropdownItems.containsAll(expectedDropdownItems), "Some Company dropdown items are missing.");

        homePage.clickItemFromCompanyDropdown("Careers");

        String url = DriverFactory.getDriver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(url.contains("career"), "Redirection to Careers page failed. Actual URL: " + url);

        CareersPage careersPage = new CareersPage(DriverFactory.getDriver());
        Assert.assertTrue(careersPage.isLocationsBlockVisible(), "Locations section is not visible.");
        Assert.assertTrue(careersPage.isTeamsBlockVisible(), "Teams section is not visible.");
        Assert.assertTrue(careersPage.isLifeAtInsiderBlockVisible(), "Life at Insider section is not visible.");
    }
}
