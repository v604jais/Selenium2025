package hpe.inta.fas.gui.automation;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FasUiLaunch {


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("http://ossv142.gre.hpecorp.net:3000/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(50000));
        Thread.sleep(50000);
        driver.manage().timeouts().getPageLoadTimeout();
        String loginpagetitle = driver.getTitle();
        System.out.println(" Title "+ loginpagetitle);
        Thread.sleep(10000);
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement signin = driver.findElement(By.tagName("button"));
        Thread.sleep(10000);
        username.sendKeys("admin");
        Thread.sleep(10000);
        password.sendKeys("admin");
        Thread.sleep(10000);
        signin.click();
        Thread.sleep(10000);
        String workspacepage = driver.getTitle();
        System.out.println(" Title "+ workspacepage);
        //Unified OSS Console


        driver.quit();

    }
}