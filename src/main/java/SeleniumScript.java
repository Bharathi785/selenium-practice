import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumScript {
    public static void main(String args[]) {
        {
            System.out.println("****************");
            System.setProperty("webdriver.ie.driver", "C:\\Users\\Lenovo\\Desktop\\IEDriverServer.exe");
            WebDriver driver = new InternetExplorerDriver();
            driver.manage().window().maximize();

            driver.manage().timeouts().pageLoadTimeout(3,TimeUnit.SECONDS);
            driver.get("https://en-gb.facebook.com/");

            driver.manage().timeouts().pageLoadTimeout(3,TimeUnit.SECONDS);
            String strPageTitle = driver.getTitle();
            System.out.println("PageTitle is: " + strPageTitle);
            driver.quit();

        }
    }
}