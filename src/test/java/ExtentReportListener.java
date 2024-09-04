import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Listener de TestNG para generar reportes con ExtentReports.
 */
public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Reporte de Pruebas - TestNG");
        htmlReporter.config().setReportName("Resultado de Pruebas");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Tester", "Fernando Zelaya");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Prueba exitosa");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail("Prueba fallida");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Prueba omitida");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
