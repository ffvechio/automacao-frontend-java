package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.BaseWebPageFactory;

public class AddAccountPage extends BaseWebPageFactory {

    //Locators

    @FindBy(how = How.XPATH, using = "//*[@id='navbar']//*[@class='caret']")
    private WebElement submitBtnAccount;

    @FindBy(how = How.XPATH, using = "//a[@href='/addConta']")
    private WebElement submitBtnAdd;

    @FindBy(how = How.ID, using = "nome")
    private WebElement setFieldName;

    @FindBy(how = How.XPATH, using = "//button[@type='submit' and @class='btn btn-primary']")
    private WebElement submitBtnSave;

    @FindBy(how = How.XPATH, using = "//*[@class='alert alert-success']")
    private WebElement validateMessagemSucess;

    @FindBy(how = How.XPATH, using = "//*[@id=\"tabelaContas\"]//*[@class='glyphicon glyphicon-remove-circle']")
    private WebElement submitBtnDelete;

    @FindBy(how = How.XPATH, using = "//*[@id=\"tabelaContas\"]//td[1]")
    private WebElement validateAddAccount;

    public AddAccountPage(WebDriver driver) {
        super(driver);
    }

    public void setUrl(String pUrl) {
        driver.navigate().to(pUrl);
    }

    public void submitBtnAccount() throws InterruptedException {
        submitBtnAccount.click();
    }

    public void submitBtndAdd() throws InterruptedException {
        submitBtnAdd.click();
    }

    public String setFieldName(String pName) {
        setFieldName.sendKeys(pName);
        return pName;
    }

    public void submitBtndSave() {
        submitBtnSave.click();
    }

    public String getMessageSucess() throws InterruptedException {
        waitUntilElementIsVisible(validateMessagemSucess);
        return validateMessagemSucess.getText();
    }

    public void submitBtndDelete() {
        submitBtnDelete.click();
    }

    public String getAccountAdd() throws InterruptedException {
        waitUntilElementIsVisible(validateAddAccount);
        return validateAddAccount.getText();
    }

}
