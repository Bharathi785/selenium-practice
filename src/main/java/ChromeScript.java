import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ChromeScript {
    public static void main(String args[]) throws InterruptedException {
        {
            System.out.println("****************");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().deleteAllCookies();

            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.get("https://www.gettyimages.in");

            driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            Thread.sleep(3000);
            String strPageTitle = driver.getTitle();
            System.out.println("PageTitle is: " + strPageTitle);
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("SIGN IN")));
            signIn.click();
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            System.out.println("User icon is clicked successfully");
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            WebElement register = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='option-picker__cta action--with-emphasis register-tab']")));
            register.click();
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            System.out.println("register icon is clicked successfully");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            // passing the value in first name textbox
            WebElement register_first_name = driver.findElement(By.id("register_first_name"));
            register_first_name.sendKeys("BharathiBalaji");
            //passing the value in lastname texbox
            WebElement register_last_name = driver.findElement(By.id("register_last_name"));
            register_last_name.sendKeys("Rangaraj");

            //passing the company type
            Select organizationType =new Select( driver.findElement(By.id("register_organization_type")));
            organizationType.selectByValue("1");
            System.out.println("Organization value is passed successfully");
            //passing the company name
            WebElement companyName=driver.findElement(By.id("register_organization_name"));
            companyName.sendKeys("abcd");
            //passing the job title
            Select jobTitle =new Select( driver.findElement(By.id("register_job_title")));
            jobTitle.selectByValue("22");
            System.out.println("Job Title is passed successfully");
            //passing the email id in text box
            WebElement register_email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//label[text()='Company email']")));
            register_email.sendKeys("bharathibalaji.b@gmail.com");
            //passing the extension value
            WebElement register_extension = wait.until(ExpectedConditions.elementToBeClickable(By.id("register_telephone")));
            register_extension.sendKeys("123456");
            //passing the password
            WebElement register_password = wait.until(ExpectedConditions.elementToBeClickable(By.id("register_password")));
            register_password.sendKeys("bbroypine4567");
            //clicking on the checkbox
            WebElement register_email_preference = wait.until(ExpectedConditions.elementToBeClickable(By.id("register_email_preference")));
            register_email_preference.click();
            //clicking on the register button
            WebElement register_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='register_email_preference']/span")));
            register_button.click();

            //create a board
            WebElement create_board =driver.findElement(By.linkText("BOARDS"));
            create_board.click();
            driver.quit();

        }
    }
}