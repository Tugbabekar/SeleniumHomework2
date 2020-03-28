package Homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverUtility {



        public static WebDriver createDriver(String browserName){
            if(browserName.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().version("79").setup();
                return  new ChromeDriver();
            }else if (browserName.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                return  new FirefoxDriver();
            }else if(browserName.equalsIgnoreCase("edge")){
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }else if(browserName.equalsIgnoreCase("safari")){
                return new SafariDriver();
            }else {
                return null;
            }

    }
}
