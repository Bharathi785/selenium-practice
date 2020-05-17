import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class LocatorConcept {
    public static void main(String args[]) throws InterruptedException {
        {
            System.out.println("****************");
            System.setProperty("webdriver.ie.driver", "C:\\Users\\Lenovo\\IdeaProjects\\selenium-practice\\lib\\IEDriverServer.exe");
            WebDriver driver = new InternetExplorerDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.get("https://login.yahoo.com/");
            driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            String strPageTitle = driver.getTitle();
            System.out.println("PageTitle is: " + strPageTitle);
            Thread.sleep(3000);

            // 1.By id
            WebElement email = driver.findElement(By.id("login-username"));
            email.sendKeys("test@yahoo.com");

            WebElement next_btn = driver.findElement(By.id("login-signin"));
            next_btn.click();
            // 2.By name
            WebElement next_btn_name = driver.findElement(By.name("signin"));
            next_btn_name.click();
            //3.By Xpath
            WebElement email_xpath = driver.findElement(By.xpath("//input[@id='login-username']"));
            email_xpath.sendKeys("test@yahoo.com");
            WebElement next = driver.findElement(By.xpath("//input[@id='login-signin']"));
            next.click();
            //By CSS Selector
            WebElement email_selector = driver.findElement(By.cssSelector("#login-username"));
            email_selector.sendKeys("test@yahoo.com");
            WebElement signin = driver.findElement(By.cssSelector("#login-signin"));
            signin.click();
            //By Linktext
            driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            WebElement account = driver.findElement(By.linkText("Create an account"));
            driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            account.click();
            //By Partial Link text
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            WebElement account1 = driver.findElement(By.partialLinkText("account"));
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

            //BY Class name

            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            WebElement phone = driver.findElement(By.className("phone-no"));
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            phone.sendKeys("9789804704");
        }
    }
}
