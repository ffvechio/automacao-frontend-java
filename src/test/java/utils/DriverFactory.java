package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import framework.FrameworkDriver;

public class DriverFactory {

    public enum Browsers {
        FIREFOX, CHROME
    }

    public static FrameworkDriver getDriver(Browsers browser) throws Exception {
        switch (browser) {
            case FIREFOX:
                File geckodriver = null;
                if (System.getProperty("os.name").contains("Window"))
                    geckodriver = new File("src/main/resources/drivers/geckodriver.exe");
                else
                    geckodriver = new File("src/main/resources/drivers/geckodriver");

                System.setProperty("webdriver.gecko.driver", geckodriver.getAbsolutePath());

                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", false);

                return new FrameworkDriver(capabilities);
            //return new FirefoxDriver(capabilities);

            case CHROME:
                File chromedriver = null;
                if (System.getProperty("os.name").contains("Window"))
                    chromedriver = new File("src/main/resources/drivers/chromedriver.exe");
                else
                    chromedriver = new File("src/main/resources/drivers/chromedriver");

                System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());

                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-infobars");
                if (!System.getProperty("os.name").contains("Window"))
                    options.addArguments("--start-fullscreen");
                //options.addArguments("--start-fullscreen");
                //TODO
                //return new ChromeDriver(options);
                return new FrameworkDriver(options);

            default:
                return null;
        }

    }


}
