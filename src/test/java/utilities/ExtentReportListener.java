package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static tests.BaseTest.takeScreenshot;


public class ExtentReportListener implements ITestListener {
    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html";
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;
    private static ExtentReports extent = init();
    private Logger log = LogManager.getLogger(this.getClass());

    private static ExtentReports init() {

        Path path = Paths.get(OUTPUT_FOLDER);
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("Open Cart Automation Test Results");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Application", "OpenCart");
        extentReports.setSystemInfo("Module", "Admin");
        extentReports.setSystemInfo("Sub Module", "Customers");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environemnt", "QA");
        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext testContext) {
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extentReports.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extentReports.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extentReports.setSystemInfo("Groups", includedGroups.toString());
        }
        log.info("Test Suite started!");

    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        log.info(("Test Suite is ending!"));
        extent.flush();
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        log.info("{} started!", methodName);
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        /*
         * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
         * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
         */
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    public synchronized void onTestSuccess(ITestResult result) {
        log.info((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
        test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.get().log(Status.PASS, result.getName() + " got successfully executed");
    }

    public synchronized void onTestFailure(ITestResult result) {
        log.info((result.getMethod().getMethodName() + " failed!"));
        test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.get().log(Status.FAIL, result.getName() + " got failed");
        test.get().log(Status.INFO, result.getThrowable().getMessage());
    }

    public synchronized void onTestSkipped(ITestResult result) {
        log.info((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.get().log(Status.SKIP, result.getName() + " got skipped");
        test.get().log(Status.INFO, result.getThrowable().getMessage());
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


}