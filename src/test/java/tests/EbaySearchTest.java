package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EbayHomePage;
import pages.EbaySearchResultsPage;

import java.time.Duration;

public class EbaySearchTest {
    WebDriver driver;
    EbayHomePage ebayHomePage;
    EbaySearchResultsPage ebaySearchResultsPage;
    private String pathtakeScreenshotEbaySearchTest = System.getProperty("user.dir") + "\\src\\test\\resources\\tests.EbaySearchTest\\";


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
        driver.get("https://www.ebay.com/");
        ebayHomePage = new EbayHomePage(driver);
    }

    @Test
    public void testSearchForElectricGuitar() {
        ebayHomePage.searchForItem("Electric Guitar");
        ebaySearchResultsPage = new EbaySearchResultsPage(driver);
        String price = ebaySearchResultsPage.getFirstItemPrice();
        if (!price.isEmpty()) {
            System.out.println("Price of the first item: " + price);
            ebaySearchResultsPage.takeScreenshot(pathtakeScreenshotEbaySearchTest + "\\testSearchForElectricGuitar_Ok.jpg");
        } else {
            ebaySearchResultsPage.takeScreenshot(pathtakeScreenshotEbaySearchTest + "\\testSearchForElectricGuitar_fail.jpg");
            Assert.fail("[WARNING] Fallo el test testSearchForElectricGuitar");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}