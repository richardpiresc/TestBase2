package TestReportIssue;

import TestLogin.TestLogin;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TestReportIssue {

    private WebDriver driver;
    private TestLogin login;

    public TestReportIssue() {
        login = new TestLogin();
        driver = login.getDriver();
    }

    @Test
    public void testBlancFields(){
        this.login.testLogin();
        driver.findElement(By.linkText("Report Issue")).click();

        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        for(WebElement button: buttons){
            if(button.getAttribute("value").equals("Submit Report")){
                button.click();
                break;
            }
        }
        assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) .center")).getText(),("A necessary field \"Summary\" was empty. Please recheck your inputs."));
    }

    @Test
    public void testBlancDescription(){
        this.login.testLogin();
        driver.findElement(By.linkText("Report Issue")).click();

        driver.findElement(By.name("summary")).click();
        driver.findElement(By.name("summary")).sendKeys("test");

        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        for(WebElement button: buttons){
            if(button.getAttribute("value").equals("Submit Report")){
                button.click();
                break;
            }
        }
        assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) .center")).getText(),("A necessary field \"Description\" was empty. Please recheck your inputs."));
    }

    @Test
    public void testBlancCategory(){
        this.login.testLogin();
        driver.findElement(By.linkText("Report Issue")).click();

        driver.findElement(By.name("summary")).click();
        driver.findElement(By.name("summary")).sendKeys("test");

        driver.findElement(By.name("description")).click();
        driver.findElement(By.name("description")).sendKeys("test");

        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        for(WebElement button: buttons){
            if(button.getAttribute("value").equals("Submit Report")){
                button.click();
                break;
            }
        }
        assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) .center")).getText(),("A necessary field \"Category\" was empty. Please recheck your inputs."));
    }

    @Test
    public void testRequiredFieldsFilled(){
        this.login.testLogin();
        driver.findElement(By.linkText("Report Issue")).click();

        driver.findElement(By.name("summary")).click();
        driver.findElement(By.name("summary")).sendKeys("test");

        driver.findElement(By.name("description")).click();
        driver.findElement(By.name("description")).sendKeys("test");

        Select select = new Select(driver.findElement(By.name("category_id")));
        select.selectByValue("33");



        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        for(WebElement button: buttons){
            if(button.getAttribute("value").equals("Submit Report")){
                button.click();
                break;
            }
        }
        String resultValue = driver.findElement(By.cssSelector("div:nth-child(5)")).getText().split("\\n")[0];

        assertEquals(resultValue, "Operation successful.");
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
