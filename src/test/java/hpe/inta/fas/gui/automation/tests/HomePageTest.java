package hpe.inta.fas.gui.automation.tests;

import hpe.inta.fas.gui.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseClass {

    @Test
    public void homePageTest()
    {


        String actualTitle = driver.get().getTitle();
        String expectedTitile = prop.getProperty("expectedTitile");
        Assert.assertEquals(actualTitle,expectedTitile);
    }
}



