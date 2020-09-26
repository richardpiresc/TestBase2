package ReportIssue;

import TestLogin.TestLogin;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class ReportIssue {

    private WebDriver driver;
    private TestLogin login;

    public ReportIssue() {
        login = new TestLogin();
        driver = login.getDriver();
    }

    @Test
    public void testBlancFields(){
        this.login.login();
        driver.findElement(By.linkText("Report Issue")).click();

        List<WebElement> buttons = driver.findElements(By.tagName("input"));

        for(WebElement button: buttons){
            if(button.getAttribute("value").equals("Submit Report")){
                button.click();
                break;
            }
        }

        // Assertions


        //driver = login.getDriver();

        //System.out.println(By.tagName("a").toString());
        //driver.findElement(By.tagName("a")).click();

        /*driver.findElement(By.name("bugnote_text")).click();
        driver.findElement(By.name("bugnote_text")).sendKeys("Testando");
        driver.findElement(By.cssSelector(".button:nth-child(1)")).click();*/



        //this.login.logout();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
