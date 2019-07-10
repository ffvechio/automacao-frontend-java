package pages;

import utils.BaseWebPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewUserPage extends BaseWebPageFactory {

    //Locators

    @FindBy(how = How.ID, using = "nome")
    private WebElement setFieldName;

    @FindBy(how = How.ID, using = "email")
    private WebElement setFieldMail;

    @FindBy(how = How.ID, using = "senha")
    private WebElement setFieldPassword;

    @FindBy(how = How.XPATH, using = "//*[contains(@value,'Cadastrar')]")
    private WebElement submitFieldCreate;

    @FindBy(how = How.XPATH, using = "//*[@class='alert alert-success']")
    private WebElement validateMessagemSucess;

    @FindBy(how = How.XPATH, using = "//*[@class='alert alert-danger']")
    private WebElement validateMessagemDanger;

    public NewUserPage(WebDriver driver) {
        super(driver);
    }

    public void setUrl(String pUrl) {
        driver.navigate().to(pUrl);
    }

    public void setNameField(String pRegistration) {
        setFieldName.sendKeys(pRegistration);
    }

    public void setMailField(String pEmail) {
        setFieldMail.sendKeys(pEmail);
    }

    public void setFieldPassword(CharSequence pSenha) {
        setFieldPassword.sendKeys(pSenha);
    }

    public void submitFieldCreate() {
        submitFieldCreate.click();
    }

    public String getMessageSucess() throws InterruptedException {
        waitUntilElementIsVisible(validateMessagemSucess);
        return validateMessagemSucess.getText();
    }

    public String getMessageDanger() throws InterruptedException {
        waitUntilElementIsVisible(validateMessagemDanger);
        return validateMessagemDanger.getText();
    }

    private void waitForMessage(int time) {
        for (int i = 0; i < time; i++) {
            if (!validateMessagemSucess.getText().equals(""))
                return;
            if (!validateMessagemDanger.getText().equals(""))
                return;
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

}
