package tests;

import annotations.FrameworkAnnotation;
import constants.NavBarData;
import drivers.DriverFactory;
import enums.BrowserType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.List;
import java.util.stream.Collectors;

public class HomePageTest extends BaseTest {

    protected HomePage homePage;

    @BeforeMethod
    public void openHomePage(){
        homePage = new HomePage(DriverFactory.getDriver());
        homePage.open();
    }

    @Test(description = "Verify the Insider homepage is displayed successfully")
    public void testHomePageLoadsSuccessfully() {
        Assert.assertTrue(homePage.isHomePageOpened(), "Homepage could not be loaded!");
    }

    @Test(description = "Verify navigation bar menus are listed and click on Company.")
    public void testTopNavBarItemsAndClickCompany() {
        List<String> actualNavItems = homePage.getTopNavBarItemsText();
        Assert.assertTrue(
                actualNavItems.containsAll(NavBarData.TOP_NAV_ITEMS),
                "Missing top nav items: " + getMissingItems(NavBarData.TOP_NAV_ITEMS, actualNavItems)
        );

        homePage.hoverTopNavBarItem("Company");

        List<String> actualDropdownItems = homePage.getCompanyDropdownItemsText();
        Assert.assertTrue(
                actualDropdownItems.containsAll(NavBarData.COMPANY_DROPDOWN_ITEMS),
                "Missing Company dropdown items: " + getMissingItems(NavBarData.COMPANY_DROPDOWN_ITEMS, actualDropdownItems)
        );

        homePage.clickItemFromCompanyDropdown("Careers");

        String currentUrl = DriverFactory.getDriver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(
                currentUrl.contains("career"),
                "Navigation to Careers page failed. Actual URL: " + currentUrl
        );
    }

    private List<String> getMissingItems(List<String> expected, List<String> actual) {
        return expected.stream()
                .filter(item -> !actual.contains(item))
                .collect(Collectors.toList());
    }
}
