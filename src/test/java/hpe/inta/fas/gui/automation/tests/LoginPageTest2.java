package hpe.inta.fas.gui.automation.tests;

import hpe.inta.fas.gui.base.BaseClass;
import hpe.inta.fas.gui.pages.HomePage;
import hpe.inta.fas.gui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest2 extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setupPages() throws IOException {
        logger.info("Executing setUp Page ");
        //setting up driver
        setUP();

        //set up pages
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }

    @Test(groups = "login", priority = 1)
    public void loginValidation() throws InterruptedException {
        logger.info("Executing loginValidation ");
        loginPage.loginUi(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(homePage.titleVerification(), "Unified OSS Console", "On Wrong Page Title MisMatch");
        System.out.println(homePage.titleVerification());
    }


    @AfterMethod
    public void tearDown() {
        logger.info("Executing tearDown ");
        quitDriver();
    }

}
