package framework;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameworkElement implements WebElement {

    private WebDriver driver;
    private HashMap<String, String> propertyMap;
    private By by;
    private int waitForElement;
    private FrameworkUtils utils;

    public FrameworkElement(HashMap<String, String> propertyMap, FrameworkUtils utils, WebDriver driver, By by) {
        this.driver = driver;
        this.by = by;
        this.propertyMap = propertyMap;
        this.utils = utils;
        this.waitForElement = Integer.parseInt(propertyMap.get("waitForElement"));
    }

    public WebElement findElement(By by) {
        return new FrameworkElement(this.propertyMap, this.utils, this.driver, by);
    }

    public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
        return ((TakesScreenshot) this.driver).getScreenshotAs(arg0);
    }

    public void clear() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        this.driver.findElement(by).clear();
        takeEvidenceWithStep("clear");

    }

    public void click() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        this.driver.findElement(by).click();
        this.utils.addLogStep("Passed", "O objeto é clicado com sucesso", "O objeto n�o foi clicado com sucesso");
        takeEvidenceWithStep("click");

    }

    public List<WebElement> findElements(By arg0) {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).findElements(arg0);
    }

    public String getAttribute(String arg0) {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        takeEvidenceWithStep("getAttribute");
        return this.driver.findElement(by).getAttribute(arg0);

    }

    public String getCssValue(String arg0) {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).getCssValue(arg0);
    }

    public Point getLocation() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).getLocation();
    }

    public Rectangle getRect() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).getRect();
    }

    public Dimension getSize() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).getSize();
    }

    public String getTagName() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).getTagName();
    }

    public String getText() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        takeEvidenceWithStep("getText");
        return this.driver.findElement(by).getText();

    }

    public void selectByVisibleText(String text) {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        new Select(this.driver.findElement(by)).selectByVisibleText(text);
        takeEvidenceWithStep("select");

    }

    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).isDisplayed();
    }

    public boolean isEnabled() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).isEnabled();
    }

    public boolean isSelected() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // TODO implement Evidence
        return this.driver.findElement(by).isSelected();
    }

    public void sendKeys(CharSequence... charSeq) {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        this.driver.findElement(by).sendKeys(charSeq);
        takeEvidenceWithStep("sendKeys");
    }

    public void submit() {
        WebDriverWait wait = new WebDriverWait(this.driver, this.waitForElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        this.driver.findElement(by).submit();
        // TODO implement Evidence

    }

    public String takeEvidenceWithStep(String action) {
        this.utils.addStep();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String evidenceStep = this.utils.getEvidencePath() + "/" + this.utils.getStep() + "_" + action + ".png";
        File screenFile = new File(evidenceStep);
        File scrFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, screenFile);
        } catch (IOException e) {
        }
        return evidenceStep;

    }

}
