import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumScript {
    public static void main(String args[]) {
        {
            System.out.println("****************");
            System.setProperty("webdriver.ie.driver", "C:\\Users\\Lenovo\\IdeaProjects\\selenium-practice\\lib\\IEDriverServer.exe");
            WebDriver driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            driver.get("https://www.google.co.in/");
//            String strPageTitle = driver.getTitle();
//            System.out.println("PageTitle is: " + strPageTitle);
            driver.quit();

        }
    }
}