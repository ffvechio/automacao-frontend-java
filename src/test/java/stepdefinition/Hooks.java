package stepdefinition;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import framework.FrameworkWordEvidence;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import framework.FrameworkDriver;
import utils.DriverFactory;
/**
 * A Classe Hooks é responsável por agrupar métodos simples com anotações especiais
 * dentro de suas classes de contexto podendo utilizar tags para um controle mais refinado.
 *
 * @author Felipe Bessa <ffvechio@gmail.com>
 * @version 1.0
 */
public class Hooks {

    public static HashMap<String, String> variableMap = new HashMap<String, String>();

    public static WebDriver driver;
    List<String> wordTableString;


    @Dado("^que utilizo o word para evidências com as informações de execução$")
    public void que_utilizo_o_word_para_evidências_com_as_seguintes_informações_id_nomeCT_objetivo_resultado_esperado_resultado_obtido_executor_sp_e_cdt(
            DataTable wordTable) throws Throwable {
        this.wordTableString = wordTable.asList(String.class);
    }

    @After("@generate-word")
    public void generateWord(Scenario s) throws Exception {
        this.wordTableString.get(1);
        Properties prop = new Properties();
        String pathProperties;
        pathProperties = "src/main/resources/properties/config.properties";
        prop.load(new FileInputStream(pathProperties));
        String path = ((FrameworkDriver) Hooks.driver).utils.getEvidencePath();
        String fileName = ((FrameworkDriver) Hooks.driver).utils.getExampleName();
        FrameworkWordEvidence evidence = new FrameworkWordEvidence();
        WordprocessingMLPackage template = evidence.getTemplate(prop.getProperty("templateWord"));

        evidence.replacePlaceholder(template, this.wordTableString.get(0), "<ambiente>");
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        evidence.replacePlaceholder(template, timeStamp, "<data>");
        evidence.replacePlaceholder(template, this.wordTableString.get(1), "<id_nomeCT>");
        evidence.replacePlaceholder(template, this.wordTableString.get(2), "<objetivo>");
        evidence.replacePlaceholder(template, this.wordTableString.get(3), "<resultado_esperado>");
        evidence.replacePlaceholder(template, this.wordTableString.get(4), "<resultado_obtido>");
        evidence.replacePlaceholder(template, this.wordTableString.get(5), "<executor>");
        evidence.replacePlaceholder(template, this.wordTableString.get(6), "<sp>");
        evidence.replacePlaceholder(template, this.wordTableString.get(7), "<cdt>");

        evidence.createWordEvidence(template, path, fileName);
    }


    @Before("@chrome-same-session")
    public void openChromeBrowserDeskopSameSession(Scenario s) throws Exception {

        if (driver == null) {
            driver = DriverFactory.getDriver(DriverFactory.Browsers.CHROME);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.navigate().refresh();
        }

        ((FrameworkDriver) driver).setScenarioName(s.getName());

    }

    @Before("@chrome")
    public void openChromeBrowserDeskop() throws Exception {

        driver = DriverFactory.getDriver(DriverFactory.Browsers.CHROME);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Before("@firefox")
    public void openFirefoxBrowserDeskop() throws Exception {

        driver = DriverFactory.getDriver(DriverFactory.Browsers.FIREFOX);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Before("@firefox-same-session")
    public void openFirefoxSameSessionBrowserDeskop(Scenario s) throws Exception {

        if (driver == null) {
            driver = DriverFactory.getDriver(DriverFactory.Browsers.FIREFOX);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        ((FrameworkDriver) driver).setScenarioName(s.getName());
    }

    @After("@closeBrowser")
    public void closeBrowser() throws Exception {

        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @After("@refreshBrowser")
    public void refreshBrowser() throws Exception {

        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

