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
    private WebDriver driver;

    public TestLogin() {
        GetDriver getDriver = new GetDriver();
        driver = getDriver.get();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void login() {
        driver.get("https://mantis-prova.base2.com.br/login_page.php");
        driver.manage().window().maximize();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("richard.carvalho");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("wd19051995");
        driver.findElement(By.name("perm_login")).click();
        driver.findElement(By.cssSelector(".button")).click();
    }

    public void wrongLogin() {
        driver.get("https://mantis-prova.base2.com.br/login_page.php");
        driver.manage().window().maximize();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("richard.carvalho2");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("wd19051995");
        driver.findElement(By.name("perm_login")).click();
        driver.findElement(By.cssSelector(".button")).click();

    }

    @Test
    public void confirmName(){
        this.login();
        assertEquals(driver.findElement(By.cssSelector(".login-info-left > .italic")).getText(), "richard.carvalho");
        this.logout();
    }

    @Test
    public void confirmIncorrectName(){
        this.login();
        assertNotEquals(driver.findElement(By.cssSelector(".login-info-left > .italic")).getText(), "richard.carvalho2");
        this.logout();
    }

    @Test
    public void confirmWrongLogin(){
        this.wrongLogin();
        assertEquals(driver.findElement(By.cssSelector("font")).getText(), "Your account may be disabled or blocked or the username/password you entered is incorrect.");
    }

    public void logout(){
        driver.findElement(By.linkText("Logout")).click();
    }
}
