package tests;

import annotations.FrameworkAnnotation;
import drivers.DriverFactory;
import enums.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.List;

public class HomePageTest extends BaseTest {

    @Test(description = "Verify the Insider homepage is displayed successfully")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testHomePageLoadsSuccessfully() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isHomePageOpened(), "Homepage could not be loaded!");
    }

    @Test(description = "Verify navigation bar menus are listed and click on Company.")
    @FrameworkAnnotation(browser = BrowserType.CHROME)
    public void testTopNavBarItemsAndClickCompany() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();

        List<String> actualNavItems = homePage.getTopNavBarItemsText();
        List<String> expectedNavItems = List.of("Why Insider", "Platform", "Solutions", "Customers", "Resources", "Company", "Explore Insider");

        Assert.assertTrue(actualNavItems.containsAll(expectedNavItems), "Navigation menü öğeleri eksik!");

        homePage.hoverTopNavBarItem("Company");

        List<String> actualDropdownItems = homePage.getCompanyDropdownItemsText();
        List<String> expectedDropdownItems = List.of(
                "About Us", "Newsroom", "Partnerships", "Integrations", "Careers", "Contact Us"
        );

        Assert.assertTrue(actualDropdownItems.containsAll(expectedDropdownItems), "Not all expected submenus are present!");

        homePage.clickItemFromCompanyDropdown("Careers");

        String url = DriverFactory.getDriver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(url.contains("career"), "Redirection to Careers page failed! Actual: " + url);
    }
}
