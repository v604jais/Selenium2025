package hpe.inta.fas.gui.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    //initialize the extentReport
    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/src/test/resources/report/ExtentReport.html";
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
            extentSparkReporter.config().setReportName("FAS GUI TEST REPORT");
            extentSparkReporter.config().setDocumentTitle("FAS Test Report");
            extentSparkReporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();

            extent.attachReporter(extentSparkReporter); // ✅ THIS LINE IS MISSING

            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
        }
        return extent;
    }

    //start ExtentTest Report
    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = getReporter().createTest(testName);
        extentTest.set(test);
        return test;
    }

    //End ExtentTest Report
    public static synchronized void endTest() {
        getReporter().flush();
    }
    // get ExtentTest
    public static synchronized ExtentTest getTest(){
        return extentTest.get();
    }
    // get Method Name
    public static synchronized String getTestName(){
        ExtentTest currentTest = getTest();
        if(currentTest != null ){
            return currentTest.getModel().getName();
        }else{
            return " No Active Test for current Thread ";
        }
    }
    //log step
    public static synchronized void logStep(String logMessage){
         getTest().info(logMessage);
    }
    //log step validation with screenshot
    public static synchronized void logStepWithScreenshot(WebDriver driver,String logMessage, String screenShotMessage){
        String colorMessage = String.format("<span style='color:green;'>%s</span>",logMessage);
        getTest().pass(colorMessage);
        //screenshot
        attachScreenshot(driver,screenShotMessage);
    }
    // log a failure with screenshot
    public static  void logFailure(WebDriver driver,String logMessage, String screenShotMessage){
        String colorMessage = String.format("<span style='color:red;'>%s</span>",logMessage);
        getTest().fail(colorMessage);
        //screenshot
        attachScreenshot(driver,screenShotMessage);
    }
    //log skip
    public static void logSkip(String logMessage){
        String colorMessage = String.format("<span style='color:orange;'>%s</span>",logMessage);
        getTest().skip(colorMessage);
    }
    //screenshot method with date and time
    public static synchronized String takeScreenShot(WebDriver driver, String screenShotName){
        TakesScreenshot ts = (TakesScreenshot)driver;
        //src
        File src = ts.getScreenshotAs(OutputType.FILE);
        //Format date and Time for file name
        String timestamp = new SimpleDateFormat("dd-MM-yy_HH-mm-ss").format(new Date());
        //dest path
        String dest = System.getProperty("user.dir")+"/src/test/resources/screenshots/"+ screenShotName +"_"+ timestamp +".png";
        // final path
        File finalPath = new File(dest);
        //copy src to finalpath
        try {
            FileUtils.copyFile(src,finalPath);
        } catch (IOException e) {
            getTest().fail("error while taking snapshopt and copying files");
        }
        //convert string to BASE 64 for embedding in report
        String base64Format = convertToBase64(finalPath);
        return base64Format;
    }

    // base64 conv method
    public static String convertToBase64(File screenShotFile){
        String base64Format = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(screenShotFile);
            base64Format = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            extentTest.get().info(" error while encoding to base64 "+e.getMessage());
        }
        return base64Format;
    }
    // Attach screenshots to report using base64
    public static synchronized void attachScreenshot(WebDriver driver,String message){
        try {
            String screenShotBase64 = takeScreenShot(driver,getTestName());
            getTest().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotBase64).build());
        } catch (Exception e) {
            getTest().fail(" Failed to attach screenshot "+message );
        }
    }
}
