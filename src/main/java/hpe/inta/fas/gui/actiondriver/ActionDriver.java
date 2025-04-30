package hpe.inta.fas.gui.actiondriver;

import hpe.inta.fas.gui.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ActionDriver {

    private WebDriver driver;
    private WebDriverWait wait;


    //constructor
    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(BaseClass.getProp().getProperty("explicitwait"))));
    }

    //method to enter text
    public void enterText(By by, String value) {
        try {
            waitForElementToBeVisible(by);
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(value);
        } catch (Exception e) {
            System.out.println("not able to enter value in input box :: " + e.getMessage());
        }
    }

    //method to get text from an input field
    public String getTextFromInput(By by) {
        try {
            waitForElementToBeVisible(by);
            return driver.findElement(by).getText();
        } catch (Exception e) {
            System.out.println(" unable to get the text from input field :: " + e.getMessage());
            return "";
        }
    }

    //method to compare two text
    public void compareText(By by, String expected) {
        waitForElementToBeVisible(by);
        String actual = getTextFromInput(by);
        try {
            if (expected.equals(actual)) {
                System.out.println(" Text are mathcing " + actual + " equals " + expected);
            } else {
                System.out.println(" Text are not mathcing " + actual + " not equals " + expected);
            }
        } catch (Exception e) {
            System.out.println(" unable to compare text " + e.getMessage());
        }
    }

    //  is displayed
    public boolean isDisplayed(By by) {
        try {
            waitForElementToBeVisible(by);
            boolean isDisplayed = driver.findElement(by).isDisplayed();
            if (isDisplayed) {
                System.out.println("element is visible ");
                return isDisplayed;
            } else {
                return isDisplayed;
            }
        } catch (Exception e) {
            System.out.println(" element is not displayed :: " + e.getMessage());
            return false;
        }
    }

    // scroll to element
    public void scrollToElement(By by) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(by);
            js.executeScript("argument[0].scrollIntoView(true)", element);
        } catch (Exception e) {
            System.out.println(" unable to scroll :: " + e.getMessage());
        }
    }

    //wait for page load
    public void waitForPageLoad(int expWait) {
        try {
            wait.withTimeout(Duration.ofSeconds(expWait)).until(WebDriver -> ((JavascriptExecutor) WebDriver))
                    .executeScript(" return document.readyState").equals("complete");
            System.out.println(" page loaded successfully ");
        } catch (Exception e) {
            System.out.println(" page not loaded successfully :: " + e.getMessage());
        }
    }

    //method to click an element
    public void clickAnElement(By by) {
        try {
            waitForElementToBeClickable(by);
            driver.findElement(by).click();
        } catch (Exception e) {
            System.out.println(" unable to click element :: " + e.getMessage());
        }
    }

    //get title of the page
    public String getTitle() {
        waitForPageLoad(20);
        return driver.getTitle();
    }

    //waitForElementToBeClickable
    private void waitForElementToBeClickable(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            System.out.println("element is not clickable :: " + e.getMessage());
        }
    }

    //waitForElementToBeVisible
    private void waitForElementToBeVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("element is not visible :: " + e.getMessage());
        }
    }


}
