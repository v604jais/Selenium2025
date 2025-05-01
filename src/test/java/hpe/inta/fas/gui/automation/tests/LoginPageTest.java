package hpe.inta.fas.gui.automation.tests;

import hpe.inta.fas.gui.base.BaseClass;
import hpe.inta.fas.gui.pages.HomePage;
import hpe.inta.fas.gui.pages.LoginPage;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setupPages() throws IOException {
        //setting up driver
        setUP();
        //set up pages
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());

    }

    @Test(groups = "login", priority = 1)
    public void loginValidation() throws InterruptedException {

        loginPage.loginUi(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(homePage.titleVerification(), "Unified OSS Console", "On Wrong Page Title MisMatch");
        System.out.println(homePage.titleVerification());
    }


    @AfterMethod
    public void tearDown() {
        quitDriver();
    }

}
