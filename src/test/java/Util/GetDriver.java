package Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetDriver {
    WebDriver driver;
    public WebDriver get(){
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }
}
