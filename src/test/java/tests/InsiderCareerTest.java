package tests;

import drivers.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class InsiderCareerTest extends BaseTest {

    @Test
    public void testHomePageIsOpened() {
        String expectedTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";
        String actualTitle = DriverFactory.getDriver().getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Ana sayfa başlığı beklenenden farklı.");
    }
}
