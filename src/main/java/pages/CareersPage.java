package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class CareersPage {

    private final WebDriver driver;


    private final By locationsBlock = By.id("location-slider");
    private final By teamsBlock = By.id("career-find-our-calling");
    private final By lifeAtInsiderBlock = By.className("swiper-wrapper");

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLocationsBlockVisible() {
        return WaitUtils.waitForVisibility(locationsBlock, 10).isDisplayed();
    }

    public boolean isTeamsBlockVisible() {
        return WaitUtils.waitForVisibility(teamsBlock, 10).isDisplayed();
    }

    public boolean isLifeAtInsiderBlockVisible() {
        return WaitUtils.waitForVisibility(lifeAtInsiderBlock, 10).isDisplayed();
    }

}
