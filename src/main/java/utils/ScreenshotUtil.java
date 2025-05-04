package utils;

import drivers.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotUtil {

    public static void captureScreenshot(String testName) {
        try {
            File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File("target/screenshots/" + testName + ".png");
            FileUtils.copyFile(srcFile, destFile);
        } catch (Exception e) {
            System.out.println("Screenshot alınamadı: " + e.getMessage());
        }
    }
}
