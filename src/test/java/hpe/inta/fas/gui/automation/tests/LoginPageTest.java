package hpe.inta.fas.gui.automation.tests;

import hpe.inta.fas.gui.base.BaseClass;
import hpe.inta.fas.gui.pages.HomePage;
import hpe.inta.fas.gui.pages.LoginPage;
import hpe.inta.fas.gui.utilities.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginPageTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;
    String filePath = System.getProperty("user.dir") +"/src/test/resources/testdata/Data.xlsx";
    @BeforeMethod
    public void setupPages() throws IOException {
        logger.info("Executing setUp Page ");
        //setting up driver
        setUP();

        //set up pages
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }

   /* @Test(groups = "login", priority = 1)
    public void loginValidation() throws InterruptedException {
        logger.info("Executing loginValidation ");
        loginPage.loginUi(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(homePage.titleVerification(), "Unified OSS Console", "On Wrong Page Title MisMatch");
        System.out.println(homePage.titleVerification());
    }*/
    @Test
    public void readExcelTest(){
        //ExcelUtils.readExcel(filePath).stream().forEach(n-> System.out.println(n.get("USERNAME")));
       // ExcelUtils.readExcel(filePath).stream().forEach(n-> System.out.println(n.get("PASSWORD")));

        List<Map<String,String>> testCases= ExcelUtils.readExcel(filePath);
        for(Map<String,String> testData : testCases){
            System.out.println("username-->>"+testData.get("USERNAME"));
            System.out.println("pssword-->>"+testData.get("PASSWORD"));
        }

    }
    @AfterMethod
    public void tearDown() {
        logger.info("Executing tearDown ");
        quitDriver();
    }

}
