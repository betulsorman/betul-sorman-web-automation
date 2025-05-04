package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private final WebDriver driver;


    private final By navBarItems = By.cssSelector("ul.navbar-nav > li > a");
    private By getTopNavItemByText(String itemText) {
        return By.xpath("//ul[contains(@class, 'navbar-nav')]//a[normalize-space()='" + itemText + "']");
    }
    private final By companyDropdownItems = By.xpath("//div[@class='new-menu-dropdown-layout-6-left-container' or @class='new-menu-dropdown-layout-6-mid-container']/a[@class='dropdown-sub']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    public boolean isHomePageOpened() {
        return driver.getTitle().toLowerCase().contains("insider");
    }

    public List<String> getTopNavBarItemsText() {
        List<WebElement> items = WaitUtils.waitForVisibilityOfAll(navBarItems, 10);
        List<String> texts = new ArrayList<>();
        for (WebElement item : items) {
            texts.add(item.getText().trim());
        }
        return texts;
    }

    public void clickTopNavBarItem(String itemName) {
        By dynamicNavItem = getTopNavItemByText(itemName);
        WaitUtils.waitForClickability(dynamicNavItem, 10).click();
    }

    public void hoverTopNavBarItem(String itemName) {
        By dynamicNavItem = getTopNavItemByText(itemName);
        WaitUtils.hoverOverElement(dynamicNavItem);
    }

    public List<String> getCompanyDropdownItemsText() {
        List<WebElement> elements = WaitUtils.waitForVisibilityOfAll(companyDropdownItems, 10);
        List<String> itemTexts = new ArrayList<>();
        for (WebElement el : elements) {
            itemTexts.add(el.getText().trim());
        }
        return itemTexts;
    }

    public void clickItemFromCompanyDropdown(String itemName) {
        List<WebElement> elements = WaitUtils.waitForVisibilityOfAll(companyDropdownItems, 10);
        for (WebElement el : elements) {
            if (el.getText().trim().equalsIgnoreCase(itemName)) {
                el.click();
                return;
            }
        }
        throw new RuntimeException(itemName + " not found!");
    }

}
