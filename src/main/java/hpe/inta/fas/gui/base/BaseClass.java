package hpe.inta.fas.gui.base;
import hpe.inta.fas.gui.actiondriver.ActionDriver;
import hpe.inta.fas.gui.utilities.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class BaseClass {

    protected static WebDriver driver;
    protected static Properties prop;
    private  static ActionDriver actionDriver;

    public static final Logger logger = LoggerManager.getLogger(BaseClass.class);

    public void loadConfig() throws IOException {
        // creating properties object
        prop = new Properties();
        // creating fis object to do io operation
        FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties");
        //loading file
        prop.load(fis);
    }

    //launch browser
    private void launchBrowser() {
        //driver initialization based on browser
        String browser = prop.getProperty("browser");

        switch (browser) {

            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 2);  // 1: Allow, 2: Block
                options.setExperimentalOption("prefs", prefs);
                // Uncomment this to run headless
                // options.addArguments("--headless");
                //options.setAcceptInsecureCerts(true);
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("dom.webnotifications.enabled", false);
                FirefoxOptions foptions = new FirefoxOptions();
                foptions.setAcceptInsecureCerts(true);
                foptions.setProfile(profile);

                driver = new FirefoxDriver(foptions);
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid Browser " + browser);
        }
    }

    //configureBrowser
    private void configBrowser() {
        //implicitwait through out session
        int implicitwait = Integer.parseInt(prop.getProperty("implicitwait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitwait));
        // maximize the driver
        driver.manage().window().maximize();
        // navigate to  url
        driver.get(prop.getProperty("url"));
    }

    //initializing the driver and launching browser
    public void setUP() throws IOException {

        //load config
        loadConfig();
        logger.info(" running load config from setUp method ");
        //browser set up
        launchBrowser();
        logger.info(" launching browser ");
        //browser config
        configBrowser();
        logger.info(" configuring browser ");
        // actionDriver
        if(actionDriver == null){
            actionDriver = new ActionDriver(driver);
            logger.info(" initializing action driver ");
        }

    }

    //driver getter method
    public static WebDriver getDriver() {
        if (driver == null ){
            //System.out.println(" WebDriver is not initialized ");
            logger.error(" WebDriver is not initialized " );
            throw new IllegalArgumentException(" WebDriver is not initialized ");
        }
        return driver;
    }
    //actionDriver getter method
    public static ActionDriver getActionDriver() {
        if (actionDriver == null ){
            //System.out.println(" actionDriver is not initialized ");
            logger.error(" actionDriver is not initialized " );
            throw new IllegalArgumentException(" actionDriver is not initialized ");
        }
        return actionDriver;
    }

    //driver setter method
    public void setDriver(WebDriver driver) {
        logger.info(" setting driver ");
        this.driver = driver;
    }

    //static wait
    public void staticWait(int time) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(time));
        logger.info(" Static wait applied ");
    }

    //get prop
    public static Properties getProp() {
        logger.info(" returning prop ");
        return prop;

    }

    //quit driver
    public void quitDriver() {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("quiting the driver");
            }
        } catch (Exception e) {
            logger.error("problem in quiting the driver :: "+e.getMessage());
            //System.out.println("problem in quiting the driver :: "+e.getMessage());
        }
        driver=null;
        actionDriver=null;
    }

}
