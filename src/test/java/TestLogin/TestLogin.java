package TestLogin;

import Util.GetDriver;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestLogin {
    private final String login;
    private final String password;
    private final String wrontLogin;
    private WebDriver driver;

    public TestLogin() {
        GetDriver getDriver = new GetDriver();
        driver = getDriver.get();
        driver.get("https://mantis-prova.base2.com.br/login_page.php");
        driver.manage().window().maximize();

        this.login = "richar.carvalho";
        this.wrontLogin = "richar.carvalho23";
        this.password = "wd19051995";
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void testLogin() {
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys(this.login);
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys(this.password);
        driver.findElement(By.name("perm_login")).click();
        driver.findElement(By.cssSelector(".button")).click();
    }

    public void testWrongLogin() {
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys(this.wrontLogin);
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys(this.password);
        driver.findElement(By.name("perm_login")).click();
        driver.findElement(By.cssSelector(".button")).click();

                       

    }

    @Test
    public void testConfirmName(){
        this.testLogin();
        assertEquals(driver.findElement(By.cssSelector(".login-info-left > .italic")).getText(), "richard.carvalho");
        this.testLogout();
    }

    @Test
    public void testConfirmIncorrectName(){
        this.testLogin();
        assertNotEquals(driver.findElement(By.cssSelector(".login-info-left > .italic")).getText(), "richard.carvalho2");
        this.testLogout();
    }

    @Test
    public void testWrongLoginConfirmation(){
        this.testWrongLogin();
        assertEquals(driver.findElement(By.cssSelector("font")).getText(), "Your account may be disabled or blocked or the username/password you entered is incorrect.");
    }

    public void testLogout(){
        driver.findElement(By.linkText("Logout")).click();
    }
}
