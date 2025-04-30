package hpe.inta.fas.gui.pages;

import hpe.inta.fas.gui.actiondriver.ActionDriver;
import hpe.inta.fas.gui.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    //action driver
    private ActionDriver actionDriver;

    //locators using By class
    private By username = By.id("username");
    private By password = By.id("password");
    private By signIn =  By.tagName("button");

    //constructor
    public LoginPage(WebDriver driver) {

        this.actionDriver = new ActionDriver(driver);
    }



    //actions
    public void loginUi(String userName, String passWord){

        actionDriver.enterText(username,userName);
        actionDriver.enterText(password,passWord);
        actionDriver.clickAnElement(signIn);

    }


}
