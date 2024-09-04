package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EbaySearchResultsPage extends BasePage {


    private WebElement firstItemPrice;


    public EbaySearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstItemPrice() {
        try {
            // Localiza el 'ul' usando el selector CSS
            WebElement ulElement = driver.findElement(By.cssSelector(".srp-results.srp-list.clearfix"));
            // Obtén todos los 'li' dentro del 'ul'
            List<WebElement> liElements = ulElement.findElements(By.cssSelector(".s-item.s-item__pl-on-bottom"));
            firstItemPrice = liElements.get(0).findElement(By.cssSelector(".s-item__price"));
        }catch (Exception e){
            return null;
        }

       return firstItemPrice.getText();
    }
}
