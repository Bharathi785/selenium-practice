import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StringConstant {
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
            Select organizationType = new Select(driver.findElement(By.id("register_organization_type")));
            organizationType.selectByValue("1");
            System.out.println("Organization value is passed successfully");
            //passing the company name
            WebElement companyName = driver.findElement(By.id("register_organization_name"));
            companyName.sendKeys("abcd");
            //passing the job title
            Select jobTitle = new Select(driver.findElement(By.id("register_job_title")));
            jobTitle.selectByValue("98");
            System.out.println("Job Title is passed successfully");
            // passing the unique job title
            WebElement uniqueJobTitle = driver.findElement(By.id("register_unique_job_title"));
            uniqueJobTitle.sendKeys("TestAssociate");
            //passing the email id in text box
            WebElement register_email = driver.findElement(By.id("register_email"));
            register_email.sendKeys("bharathibalaji.b@gmail.com");
            //passing the extension value
            WebElement register_extension = driver.findElement(By.id("register_extension"));
            register_extension.sendKeys("123456");
            //passing the password
            WebElement register_password = driver.findElement(By.id("register_password"));
            register_password.sendKeys("bbroypine4567");
            //clicking on the checkbox
            WebElement register_email_preference = driver.findElement(By.id("register_email_preference"));
            register_email_preference.click();
            register_email_preference.click();
            //clicking on the register button
            WebElement register_button = driver.findElement(By.id("register-button"));
















































































































































































































































































































































            register_button.click();
            System.out.println("registration is successful");
            driver.navigate().back();
            System.out.println("navigation is successful");
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            WebElement creativeImages = driver.findElement(By.xpath("//ul[@class='landing-top-menu scroll-x--hidden']/child::li[1]"));
            creativeImages.click();
            String strImageTitle = driver.getTitle();
            System.out.println("creative tab is clicked on successfully:" + strImageTitle);

            //Find total No of images present on the web page
            List<WebElement> total_images = driver.findElements(By.tagName("a"));
            System.out.println("Total Number of images found on page = " + total_images.size());
            for (int i = 0; i < total_images.size(); i++) {
                String imageNames = total_images.get(i).getText();
                System.out.println(imageNames);
                WebElement board = driver.findElement(By.linkText("BOARDS"));
                board.click();
                System.out.println("board has been clicked successfully");

                //driver.quit();
            }
        }
    }
}