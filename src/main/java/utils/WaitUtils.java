package utils;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public final class WaitUtils {

    private WaitUtils() {
        // Prevent instantiation
    }

    private static WebDriverWait getWait(int timeoutSeconds) {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds));
    }

    public static WebElement waitForVisibility(By locator, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickability(By locator, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForUrlContains(String urlFraction, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(ExpectedConditions.urlContains(urlFraction));
    }

    public static boolean waitForTitleContains(String titleFraction, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(ExpectedConditions.titleContains(titleFraction));
    }

    public static void waitForInvisibility(By locator, int timeoutSeconds) {
        getWait(timeoutSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForAlert(int timeoutSeconds) {
        getWait(timeoutSeconds).until(ExpectedConditions.alertIsPresent());
    }

    public static List<WebElement> waitForVisibilityOfAll(By locator, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void hoverOverElement(By locator) {
        WebElement element = getWait(10).until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void waitUntilTextIsPresentInElements(By locator, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(driver -> {
            List<WebElement> elements = driver.findElements(locator);
            return elements.stream().anyMatch(el -> el.getText().trim().equalsIgnoreCase(text));
        });
    }

}
