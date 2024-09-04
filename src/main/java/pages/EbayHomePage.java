package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EbayHomePage extends BasePage {

    @FindBy(id = "gh-ac")
    WebElement searchBox;

    @FindBy(id = "gh-btn")
    WebElement searchButton;

    public EbayHomePage(WebDriver driver) {
        super(driver);
    }

    public void searchForItem(String item) {
        searchBox.sendKeys(item);
        this.wait.until(ExpectedConditions.visibilityOf(searchButton)).click();
    }
}
