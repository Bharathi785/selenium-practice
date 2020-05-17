import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Xpath {
    public static void main(String args[]) throws InterruptedException {
        {
            System.out.println("****************");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.get("https://www.facebook.com/freecrm/");

            driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String strPageTitle = driver.getTitle();
            System.out.println("PageTitle is: " + strPageTitle);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement email = driver.findElement(By.xpath("//input[@type ='email' and @class='inputtext login_form_input_box']"));
            email.sendKeys("testadmin@gmail.com");
            driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement LogIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginbutton")));
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            LogIn.click();
            driver.quit();

        }
    }
}