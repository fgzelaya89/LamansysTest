package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Locale;

public class GmailLoginPage extends BasePage {

    @FindBy(id = "identifierId")
    WebElement emailField;

    @FindBy(xpath = "//span[text()='Siguiente']")
    WebElement nextButton;

    @FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
    WebElement passwordField;

    @FindBy(xpath = "//div[@id='profileIdentifier']")
    WebElement profileIcon;

    @FindBy(css = "a.gb_A.gb_Wa.gb_X")
    WebElement accountElement;


    public GmailLoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        emailField.sendKeys(email);
        nextButton.click();


        wait.until(ExpectedConditions.visibilityOf(passwordField));

        passwordField.sendKeys(password);
        nextButton.click();

    }


    public boolean isAccountElement(String email) {
        try {
            WebElement isAccountElement = this.wait.until(ExpectedConditions.visibilityOf(accountElement));
            if (isAccountElement.isDisplayed()) {
                String ariaLabel = accountElement.getAttribute("aria-label");
                if (ariaLabel.contains(email.toLowerCase(Locale.ROOT))) {
                    return true;
                } else {
                    return false;
                }

            }
        } catch (TimeoutException e) {
            return false;
        }
        return false;
    }

}
