package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GmailLoginPage;

import java.time.Duration;

public class GmailLoginTest {
    private WebDriver driver;
    private GmailLoginPage gmailLoginPage;
    private String pathtakeScreenshot = System.getProperty("user.dir") + "\\src\\test\\resources\\tests.GmailLoginTest\\";

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        String pathChromeDriver = System.getProperty("user.dir") + "\\src\\main\\drivers\\chromedriver.exe";
        ;
        System.out.println("[Info]Ruta driver " + pathChromeDriver);
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);

        options.addArguments("start-maximized");
        options.addArguments("incognito");
        options.setPageLoadTimeout(Duration.ofSeconds(60));

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        driver.get("https://mail.google.com/");
        gmailLoginPage = new GmailLoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        String email = "LamansysTest@gmail.com";
        String psw = "holaMundo1989";
        gmailLoginPage.login(email, psw);
        boolean result = gmailLoginPage.isAccountElement(email);
        if (result) {
            gmailLoginPage.takeScreenshot(pathtakeScreenshot + "\\testValidLogin_OK.jpg");
        } else {
            gmailLoginPage.takeScreenshot(pathtakeScreenshot + "\\testValidLogin_Fail.jpg");
            Assert.assertTrue(false, "[WARNING] Fallo al iniciar sesion");
        }

    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
