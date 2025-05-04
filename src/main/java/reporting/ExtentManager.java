package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            extentReports = createExtentReport();
        }
        return extentReports;
    }

    private static ExtentReports createExtentReport() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/TestReport_" + timestamp + ".html");

        sparkReporter.config().setReportName("Insider QA Automation Test Report");
        sparkReporter.config().setDocumentTitle("Insider QA Automation Results");
        sparkReporter.config().setEncoding("utf-8");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("Author", "QA Automation Engineer");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Browser", "Chrome & Firefox");

        return extentReports;
    }

    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
