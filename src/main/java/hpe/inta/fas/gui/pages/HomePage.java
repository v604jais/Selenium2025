package hpe.inta.fas.gui.pages;

import hpe.inta.fas.gui.actiondriver.ActionDriver;
import hpe.inta.fas.gui.base.BaseClass;
import org.openqa.selenium.WebDriver;

public class HomePage {

    //action driver
    private ActionDriver actionDriver;

    //define locators for home page

    //constructor
   /* public HomePage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }*/
    public HomePage(WebDriver driver){
        this.actionDriver = BaseClass.getActionDriver();
    }

    //actions
    public String titleVerification() {
        return actionDriver.getTitle();
    }

}
