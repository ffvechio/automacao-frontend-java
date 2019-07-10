package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.BaseWebPageFactory;

public class LoginPage extends BaseWebPageFactory {

    //Locators

    @FindBy(how = How.ID, using = "email")
    private WebElement setFieldMail;

    @FindBy(how = How.ID, using = "senha")
    private WebElement setFieldPassword;

    @FindBy(how = How.XPATH, using = "//button[@type='submit' and @class='btn btn-primary']") //arrumar XPATH
    private WebElement submitFieldLogin;

    @FindBy(how = How.XPATH, using = "//*[@class='alert alert-success']")
    private WebElement validateMessagemLogin;

    @FindBy(how = How.XPATH, using = "//a[@href='/logout']")
    private WebElement submitFieldLogout;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUrl(String pUrl) {
        driver.navigate().to(pUrl);
    }

    public void setFieldMail(String pEmail) {
        setFieldMail.sendKeys(pEmail);
    }

    public void setFieldPassword(CharSequence pSenha) {
        setFieldPassword.sendKeys(pSenha);
    }

    public void submitFieldLogin() {
        submitFieldLogin.click();
    }

    public String getMessageLoginSuccess() throws InterruptedException {
        waitUntilElementIsVisible(validateMessagemLogin);
        return validateMessagemLogin.getText();
    }

    public void submitFieldLogout() {
        submitFieldLogout.click();
    }

}
