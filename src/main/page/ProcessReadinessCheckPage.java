
package com.grip.page;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.grip.utils.ExcelUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class ProcessReadinessCheckPage extends AbstractPage {

	
	public ProcessReadinessCheckPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	Logger LOGGER = Logger.getLogger(Statuspage.class);
	ExcelUtils excel = new ExcelUtils();
	
	@FindBy(xpath = "//a[@id='ui-id-6']")
	private ExtendedWebElement statusLink;
	
	@FindBy(xpath = "//a[@class='dynatree-title' and text()='GRIP']")
	private ExtendedWebElement GRIP;
	
	@FindBy(xpath = "//a[text()='ICE 1']/preceding-sibling::img")
	private ExtendedWebElement ICE1Colour;
	
	@FindBy(xpath = "//a[text()='ICE1-A']/preceding-sibling::img")
	private ExtendedWebElement ICE1AColour;
	
	@FindBy(xpath = "//a[text()='ICE1-B']/preceding-sibling::img")
	private ExtendedWebElement ICE1BColour;
	
	@FindBy(xpath = "(//td[text()='YES'])[3]")
	private ExtendedWebElement processReady;
	
	@FindBy(xpath = "//td[text()=' Inconsistency in Index Cache Detected ']")
	private ExtendedWebElement processReadiness;
	
		
		
		public void clickExpandButton(String nodeType) {
			try {
				ExtendedWebElement ele = findExtendedWebElement(By.xpath("//a[contains(text(),'" + nodeType + "')]/preceding-sibling::span"));
				ele.isElementPresent();
				ele.click();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on expand type button " + nodeType + "-" + e.getMessage());
			}
		}


		public void clickStatus() {
		try {
			statusLink.isElementPresent();
			statusLink.click();
			driver.switchTo().frame("statusframe");
			GRIP.isElementPresent();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}


		public void verifyParentNodeColour(String nodeColour) {			
			try {				
				
				ExtendedWebElement ele = findExtendedWebElement(By.xpath("//a[text()='" + nodeColour + "']/preceding-sibling::img"));
				if(ele.getAttribute("src").contains("green"))
				{
					Assert.assertTrue(ele.getAttribute("src").contains("green"));
					LOGGER.info("Parent Node colour is green");
				}
				else
				{
					Assert.assertTrue(ele.getAttribute("src").contains("red"));
					LOGGER.info("Parent Node colour is Red");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
			}
			
		}
		
		


		public void verifyChildNodeColour(String nodeA,String nodeB) {
			try {				
				ExtendedWebElement eleA = findExtendedWebElement(By.xpath("//a[text()='" + nodeA + "']/preceding-sibling::img"));
				ExtendedWebElement eleB = findExtendedWebElement(By.xpath("//a[text()='" + nodeB + "']/preceding-sibling::img"));
				if(eleA.getAttribute("src").contains("green"))
				{
					Assert.assertTrue(eleA.getAttribute("src").contains("green")||eleA.getAttribute("src").contains("blue"));
					Assert.assertTrue(eleB.getAttribute("src").contains("green")||eleB.getAttribute("src").contains("blue"));
				}
				else
				{
					Assert.assertTrue(eleA.getAttribute("src").contains("red")||eleA.getAttribute("src").contains("green")||eleA.getAttribute("src").contains("blue"));
					Assert.assertTrue(eleB.getAttribute("src").contains("red")||eleA.getAttribute("src").contains("green")||eleA.getAttribute("src").contains("blue"));
					LOGGER.info("child node colour is red");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
			}
		}


		public void verifyStatus() {
			try {	
				
				if(processReady.getText().contains("YES"))
				{
					Assert.assertTrue(processReady.getText().contains("YES"));
					LOGGER.info("processReady status is YES");
				}
				else
				{
					Assert.assertTrue(processReady.getText().contains("NO"));
					Assert.assertTrue(processReadiness.getText().contains("Inconsistency in Index Cache Detected"));
					LOGGER.info("processReady status is NO");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
			}
			
		}


		public void clickonNode(String nodeclick) {
			try {							
					ExtendedWebElement ele = findExtendedWebElement(By.xpath("//a[text()='" + nodeclick + "']/preceding-sibling::img"));
					ele.click();
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
			}
		}


		public void scrollDown() {
			try {	
				
				WebElement tickerLogger = driver.findElement(By.xpath("(//a[contains(text(),'Tick Logger')]/preceding-sibling::span)[2]"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", tickerLogger);
				tickerLogger.click();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
			}
		}

}
		

	



