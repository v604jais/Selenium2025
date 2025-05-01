package hpe.inta.fas.gui.listeners;
import hpe.inta.fas.gui.base.BaseClass;
import hpe.inta.fas.gui.utilities.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentManager.startTest(testName);
        ExtentManager.logStep("🟡 Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentManager.logStepWithScreenshot(
                BaseClass.getDriver(),
                "✅ Test Passed Successfully!",
                "✔ Test End: " + testName
        );
        ExtentManager.getReporter().flush();

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String failureMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown failure";
        ExtentManager.logStep("❌ " + failureMessage);
        ExtentManager.logFailure(
                BaseClass.getDriver(),
                "Test Failed",
                "✘ Test End: " + testName
        );
        ExtentManager.getReporter().flush();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentManager.logSkip("⚠️ Test Skipped: " + testName);
        ExtentManager.getReporter().flush();

    }

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.getReporter();  // Initialize the report
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.endTest();  // Flush the report
    }
}
