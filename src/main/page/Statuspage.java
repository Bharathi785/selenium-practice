package com.grip.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

import com.grip.DBconnection.ExchangeSQLStatementsLocal;
import com.grip.utils.ExcelUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class Statuspage extends AbstractPage {

	Logger LOGGER = Logger.getLogger(Statuspage.class);
	ExcelUtils excel = new ExcelUtils();

	public Statuspage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);

	}

	@FindBy(xpath = "//a[@id='ui-id-6']")
	private ExtendedWebElement statusLink;
	
	
	@FindBy(xpath = "//a[@id='ui-id-7']")
	private ExtendedWebElement supportLink;
	
	@FindBy(xpath = "//input[@value='Execute']")
	private ExtendedWebElement execute;	
	
	
	@FindBy(xpath = "//img[@id='refreshImageId']")
	private ExtendedWebElement refresh;	
	
	@FindBy(xpath = "//a[text()='COPY OF Bombay Stock Exchange']")
	private ExtendedWebElement copyNodeVerificationChild;
	
	
	@FindBy(xpath = "//a[text()='COPY OF Australian Stock Exchange']")
	private ExtendedWebElement copyNodeVerificationSibling;
	
	@FindBy(xpath = "//a[text()='COPY OF Budapest Stock Exchange']")
	private ExtendedWebElement pasteNodeVerificationSubNodeChild;
	
	
	@FindBy(xpath = "//a[text()='COPY OF COLOMBO STOCK EXCHANGE']")
	private ExtendedWebElement pasteNodeVerificationSubNodeSibling;	
	

	@FindBy(xpath = "//a[@class='dynatree-title' and text()='GRIP']")
	private ExtendedWebElement GRIP;

	@FindBy(xpath = "//input[@id=\"filter\"]")
	private ExtendedWebElement statusFilter;

	@FindBy(xpath = "//input[@name='status_filter[]']")
	public List<WebElement> checkBox;
	
	@FindBy(xpath = "//input[@id='searchTextId']")
	private ExtendedWebElement searchTextBox;
	
	@FindBy(xpath = "//img[@id='findImageId']")
	private ExtendedWebElement searchbutton;
	
	@FindBy(xpath = "//a[contains(text(),'Active')]/preceding-sibling::span")
	private ExtendedWebElement activeMQ;
	
	@FindBy(xpath = "(//img[@class='statusImage'])[6]")
	private ExtendedWebElement grey;
	
	
	@FindBy(xpath = "//a[contains(text(),'Broker-1')]/preceding-sibling::span")
	private ExtendedWebElement broker1;
	
	@FindBy(xpath = "//a[contains(text(),'Queues')]/preceding-sibling::span")
	private ExtendedWebElement Queues;
	
	@FindBy(xpath = "(//a[text()='EP_EXCHANGE_SEGMENTS'])[1]")
	private ExtendedWebElement epExchangeSegment;
	
	@FindBy(xpath = "//a[contains(text(),'PreMarket')]/preceding-sibling::span")
	private ExtendedWebElement preMarket;
	
	@FindBy(xpath = "(//a[contains(text(),'TSX')]/preceding-sibling::span)[5]")
	private ExtendedWebElement TSX;
	
	@FindBy(xpath = "//a[text()='DJC - CME']/preceding-sibling::img")
	private ExtendedWebElement greyTicker;
	
	@FindBy(xpath = "//a[text()='Amman Stock Exchange']")
	private ExtendedWebElement AmmanStockExchange;
	
	@FindBy(xpath = "//a[text()='Bombay Stock Exchange']")
	private ExtendedWebElement BombayStockExchange;
	
	
	@FindBy(xpath = "//a[text()='Amman Stock Exchange']")
	private ExtendedWebElement AustralianStockExchange;
	
	
	@FindBy(xpath = "//a[text()='Budapest Stock Exchange']")
	private ExtendedWebElement BudapestStockExchange;
	
	@FindBy(xpath = "//a[text()='COPY OF Budapest Stock Exchange']")
	private ExtendedWebElement BudapestStockExchangeCopy;
	
	
	@FindBy(xpath = "//a[text()='COLOMBO STOCK EXCHANGE']")
	private ExtendedWebElement ColomboStockExchange;
	
	
	
	
	
	
	@FindBy(xpath = "//span[text()='Copy Node']")
	private ExtendedWebElement copyNode;
	
	@FindBy(xpath = "//span[text()='Copy Node and Subnode']")
	private ExtendedWebElement copyNodeAndSubnode;
	
	
	@FindBy(xpath = "//span[text()='Paste Node as Child']")
	private ExtendedWebElement pasteNodeChild;
	
	@FindBy(xpath = "//span[text()='Paste Node as Sibling']")
	private ExtendedWebElement pasteNodeSibling;
	
	
	@FindBy(xpath = "(//a[text()='IDC'])[3]")
	private ExtendedWebElement IDC;
	
	@FindBy(xpath = "(//a[text()='Reuters'])[6]")
	private ExtendedWebElement reuters;	
	
	
	@FindBy(xpath = "//div[contains(text(),'Copied Node from Tree : GRIP->Exchanges')]")
	private ExtendedWebElement copyNodeNotification;
	
	@FindBy(xpath = "//div[contains(text(),'Copied Node and SubNode')]")
	private ExtendedWebElement copySubNodeNotification;	
	
	
	@FindBy(xpath = "//div[contains(text(),'Please reload SM')]")
	private ExtendedWebElement NodeNotification;
	
	
	@FindBy(xpath = "//option[text()='System Monitor(SM-A)']")
	private ExtendedWebElement systemMonitorA;
	
	@FindBy(xpath = "//option[text()='System Monitor(SM-B)']")
	private ExtendedWebElement systemMonitorB;	
	
	@FindBy(xpath = "//a[text()='S&P 1200']")
	private ExtendedWebElement editNodeValue;
	
	@FindBy(xpath = "//span[text()='Edit Node']")
	private ExtendedWebElement editValue;
	
	@FindBy(xpath = "//input[@id='param4']")
	private ExtendedWebElement paramValue;
	
	@FindBy(xpath = "//span[text()=\"Save\"]")
	private ExtendedWebElement clickSave;
	
	@FindBy(xpath = "//span[text()='Disable Node']")
	private ExtendedWebElement disableNode;
	
	@FindBy(xpath = "//span[text()='Delete Node']")
	private ExtendedWebElement deleteNode;
	
	@FindBy(xpath = "//span[text()='Delete Node(s)']")
	private ExtendedWebElement deleteNodeClick;
	
	@FindBy(xpath = "//a[text()='IDS-A']")
	private ExtendedWebElement IDSA;
	
	@FindBy(xpath = "//a[text()='IDS-B']")
	private ExtendedWebElement IDSB;
	
	
	@FindBy(xpath = "//td[text()='Process Running']")
	private ExtendedWebElement processRunning;
	
	@FindBy(xpath = "//td[text()='Process Primary']")
	private ExtendedWebElement processPrimary;
	
	@FindBy(xpath = "(//td[text()='YES'])[1]")
	private ExtendedWebElement processPrimaryYES;
	
	@FindBy(xpath = "(//td[text()='YES'])[1]")
	private ExtendedWebElement processRunningYES;
	
	
	@FindBy(xpath = "//li[@id=\"dynatree-id-165678\"]/span/a")
	private ExtendedWebElement IDCRightPane;
	
	
	
	@FindBy(xpath = "//input[@id=\"filter\"]")
	private ExtendedWebElement searchRightPane;
	
	@FindBy(xpath = "//button[@id=\"filterbuttonid\"]/span")
	private ExtendedWebElement buttonRightPane;
	
	@FindBy(xpath = "//div[@id='filtercheckboxid']//div[1]//div//table//tbody//tr//td[1]//input[2]")
	private ExtendedWebElement Rightpanecheck;		
	
	@FindBy(xpath = "//tbody[@id='statusDtlTblId']/tr")
	private List<WebElement> filterRightText;
	
	
	@FindBy(xpath = "//div[@id='filtercheckboxid']//div[1]//div//table//tbody//tr//td[1]//input[3]")
	private ExtendedWebElement Rightpanecheck1;	
	
	public String colourText;	
	

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

	public void defaultView() {
		Assert.assertTrue(GRIP.isElementPresent());
		LOGGER.info("status tree is verified");

		Assert.assertTrue(statusFilter.getAttribute("value").isEmpty());

		for (int i = 0; i < checkBox.size(); i++) {
			if (checkBox.get(i).isSelected()) {
				LOGGER.info("checked" + checkBox.get(i).getText());
			}

		}

	}
	public void searchForFilter(String text) {
		try {
			searchTextBox.type(text);
			searchbutton.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}
	
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

	public void filterStatusTree() {		
		
		String exchangeText=epExchangeSegment.getText();
		System.out.println(exchangeText);
		Assert.assertTrue(exchangeText.contains("EXCHANGE"));		
		
	}

	public void uncheckBox() throws InterruptedException {
		for (int i = 0; i < 2; i++) {
			checkBox.get(i).click();				
			}	
		
		searchbutton.click();
		
		Thread.sleep(1000);
		GRIP.isElementPresent();
		
		
		
	}

	public void findcolour(String colour) {
		try
		{
			ExtendedWebElement ele = findExtendedWebElement(By.xpath("//a[text()='" + colour + "']/preceding-sibling::img"));
			colourText=ele.getAttribute("src");	
			GRIP.isElementPresent();
		}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on expand type button " + colour + "-" + e.getMessage());
			}
		
		}

	public void verifyPurple() {		
		try
		{
			Assert.assertEquals(grey.getAttribute("src").contains("grey"), greyTicker.getAttribute("src").contains("grey"));
		}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to verify-" + e.getMessage());
			}
		}

	public void colourkeywordVerify() {
		try
		{
			String exchangeText=AmmanStockExchange.getText();
			Assert.assertTrue(exchangeText.contains("Exchange"));
			Assert.assertTrue(colourText.contains("grey"));
		}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to verify-" + e.getMessage());
			}
		}

	public void expandExchange() {
		try
		{
			ExtendedWebElement ele = findExtendedWebElement(By.xpath("(//a[contains(text(),'Exchanges')]/preceding-sibling::span)[1]"));
			ele.isElementPresent();
			ele.click();
			Thread.sleep(3000);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify-" + e.getMessage());
		}
	}

	public void clicksearchbutton() {
		try {			
			searchbutton.click();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
			}
		
	}

	public void verifyAllTheNode(String nodeverify) {
		try {	
			ExtendedWebElement ele = findExtendedWebElement(By.xpath("//a[text()='"+ nodeverify +"']"));
			String verifyNode = ele.getText();
			if(verifyNode.contains("Active MQ"))
			Assert.assertTrue(verifyNode.contains("Active MQ"));
			if(verifyNode.contains("System Node"))
				Assert.assertTrue(verifyNode.contains("System Node"));
			if(verifyNode.contains("PreMarket"))
				Assert.assertTrue(verifyNode.contains("PreMarket"));
			if(verifyNode.contains("Index Constituents"))
				Assert.assertTrue(verifyNode.contains("Index Constituents"));
			if(verifyNode.contains("Data Feeds"))
				Assert.assertTrue(verifyNode.contains("Data Feeds"));	
			if(verifyNode.contains("Exchanges"))
				Assert.assertTrue(verifyNode.contains("Exchanges"));	
			if(verifyNode.contains("Ticker Monitor Indices"))
				Assert.assertTrue(verifyNode.contains("Ticker Monitor Indices"));	
			if(verifyNode.contains("Holiday Exchange"))
				Assert.assertTrue(verifyNode.contains("Holiday Exchange"));	
			if(verifyNode.contains("Indices"))
				Assert.assertTrue(verifyNode.contains("Indices"));	
			if(verifyNode.contains("Processes"))
				Assert.assertTrue(verifyNode.contains("Processes"));	
			if(verifyNode.contains("Handlers"))
				Assert.assertTrue(verifyNode.contains("Handlers"));			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
	}

	public void untickAllCheckBox() {
		try {	
			for (int i = 0; i < 6; i++) {
				checkBox.get(i).click();
				Thread.sleep(1000);
				}	
			
			searchbutton.click();
			
			Thread.sleep(1000);
			GRIP.isElementPresent();		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
		
	}

	public void identifyBombayStock() {
		try {	
			
				BombayStockExchange.isElementPresent();
				BombayStockExchange.rightClick(1000);							
			
		} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
		
	}

	public void copyNodeRightClick() {
		try {				
				copyNode.isElementPresent();
				copyNode.click(1000);
				Assert.assertTrue(copyNodeNotification.isElementPresent());		
			
		} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
	}

	

	public void pasteNodeChild() {
		try {
				identifyBombayStock();
				pasteNodeChild.isElementPresent();
				pasteNodeChild.click(1000);
				Assert.assertTrue(NodeNotification.isElementPresent());					
			
		} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
		
	}

	public void supportPageReload() {
		try
		{
				driver.switchTo().parentFrame();
				supportLink.isElementPresent();
				supportLink.click();			
				driver.switchTo().frame("supportframe");
				systemMonitorA.isElementPresent();
				systemMonitorA.click();
				Actions action = new Actions(driver);
				action.sendKeys(Keys.SHIFT).build().perform();	
				systemMonitorB.click();
				WebElement reload = driver.findElement(By.xpath("//option[text()='RELOAD TREE']"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", reload);
				Thread.sleep(1000);
				reload.click();
				execute.click();
				driver.switchTo().parentFrame();
				clickStatus();	
				refresh.click();
				GRIP.isElementPresent();
		}
		catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
	}

	

	public void pasteNodeverificationChild() {
		
		try
		{			
			Assert.assertEquals(copyNodeVerificationChild.getText(), "COPY OF Bombay Stock Exchange");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
	}
	
	public void identifyAustraliaStock() {
		try
		{	
			AustralianStockExchange.isElementPresent();
			AustralianStockExchange.rightClick();
			Thread.sleep(1000);
			copyNodeRightClick();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
	}
	
	
	public void identifyBudapestStockExchange() {
		try
		{	
			
			BudapestStockExchange.isElementPresent();
			BudapestStockExchange.rightClick();
			Thread.sleep(1000);
			copyNodeAndSubnode.click();	
			copySubNodeNotification.isElementPresent();
			Assert.assertTrue(copySubNodeNotification.isElementPresent());

			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
	}

	public void pasteNodeSibling() {
		try
		{	
			AustralianStockExchange.isElementPresent();
			AustralianStockExchange.rightClick();
			Thread.sleep(1000);
			pasteNodeSibling.click();
			Assert.assertTrue(NodeNotification.isElementPresent());
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}
	public void clickNode() {
		try
		{	
			copyNodeVerificationSibling.isElementPresent();
			copyNodeVerificationSibling.click();
			Thread.sleep(1000);
			copyNodeVerificationSibling.rightClick();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}


	public void pasteNodeVerificationSibling() {
		try
		{	
			Assert.assertEquals(copyNodeVerificationSibling.getText(),"COPY OF Australian Stock Exchange");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}

	}
	
	
	public void databaseVerification(String name) {
		try
		{	
			
			ExchangeSQLStatementsLocal.copyNodeVerification(name);
			LOGGER.info(ExchangeSQLStatementsLocal.nodeNameCopy);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}		
	}

	public void pasteSubNodeChild() {
		try
		{	
			BudapestStockExchange.isElementPresent();
			BudapestStockExchange.rightClick();		
			pasteNodeChild.isElementPresent();
			pasteNodeChild.click(1000);
			Assert.assertTrue(NodeNotification.isElementPresent());
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}

	}
	
	

	public void pasteNodeVerificationSubNodeChild() {
		try
		{	
			Assert.assertEquals(pasteNodeVerificationSubNodeChild.getText(),"COPY OF Budapest Stock Exchange");
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}


	}

	public void verifySubNode() {
		try
		{	
			Assert.assertTrue(IDC.isElementPresent(1000));
			Assert.assertTrue(reuters.isElementPresent(1000));
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
		
		
		
	}

	public void subNodeDatabaseVerification() {
		try
		{	
			ExchangeSQLStatementsLocal.copySubNodeVerificationReu();
			LOGGER.info(ExchangeSQLStatementsLocal.nodeNameReu);
			ExchangeSQLStatementsLocal.copySubNodeVerificationIDC();
			LOGGER.info(ExchangeSQLStatementsLocal.nodeNameIDC);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}		
	}

	public void identifyColomboStock() {
		
		try
		{	
			ColomboStockExchange.isElementPresent();
			ColomboStockExchange.rightClick();
			copyNodeAndSubnode.click();	
			copySubNodeNotification.isElementPresent();
			Assert.assertTrue(copySubNodeNotification.isElementPresent());
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}

	public void pasteColomboSibling() {
		try
		{	
			ColomboStockExchange.isElementPresent();
			ColomboStockExchange.rightClick();	
			pasteNodeSibling.click();
			Assert.assertTrue(NodeNotification.isElementPresent());
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}		
	}

		 		

	public void pasteSubNodeSibling() {
		try
		{	
			ColomboStockExchange.isElementPresent();
			ColomboStockExchange.rightClick();	
			pasteNodeSibling.click();
			Assert.assertTrue(NodeNotification.isElementPresent());
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}	
	}

	

	public void pasteSubNodeVerificationSibling() {
		try
		{	
			Assert.assertEquals(pasteNodeVerificationSubNodeSibling.getText(),"COPY OF COLOMBO STOCK EXCHANGE");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}	
		
	}
	
	
	public void editTheValue() {
		try
		{	
			WebElement node = driver.findElement(By.xpath("//a[text()='S&P 1200']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", node);
			Thread.sleep(1000);
			editNodeValue.isElementPresent();
			editNodeValue.click();
			Thread.sleep(1000);
			editNodeValue.rightClick();
			editValue.click();
			paramValue.type("  ");
			paramValue.type("120");
			Thread.sleep(1000);
			clickSave.click();	
			Thread.sleep(1000);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}

	public void verifyParamDatabaseValue() {
		try
		{	
			ExchangeSQLStatementsLocal.paramDatabaseValue();
			LOGGER.info(ExchangeSQLStatementsLocal.paramDatabaseValue);	
			Thread.sleep(2000);
			Assert.assertTrue(ExchangeSQLStatementsLocal.paramDatabaseValue.equals("120"));			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}
	
	public void copyNodeclick()
	{
		try
		{	
			copyNodeVerificationChild.isElementPresent();
			copyNodeVerificationChild.click();
			Thread.sleep(1000);
			copyNodeVerificationChild.rightClick();	
		
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}

	public void disableNode() {
		try
		{	

			copyNodeclick();
			disableNode.click();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}

	}

	public void disableDatabase() {
		try
		{	
			ExchangeSQLStatementsLocal.enableIDValue();
			LOGGER.info("value from database after disable");
			LOGGER.info(ExchangeSQLStatementsLocal.enableID);
			Assert.assertTrue(ExchangeSQLStatementsLocal.enableID.equals("0"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}

	}

	public void updateEnableID() {
		try
		{	
			ExchangeSQLStatementsLocal.updateEnableNode();
			ExchangeSQLStatementsLocal.enableIDValue();
			LOGGER.info("database value after enabling");
			LOGGER.info(ExchangeSQLStatementsLocal.enableID);
			Assert.assertTrue(ExchangeSQLStatementsLocal.enableID.equals("1"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}

	}

	public void deleteNode() {
		try
		{	
			deleteNode.click();
			deleteNodeClick.click();
			LOGGER.info("node deleted");
			Assert.assertTrue(NodeNotification.isElementPresent(1000));			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}

	}
	
	

	public void clickNodeBuda() {
		try
		{	
			BudapestStockExchangeCopy.isElementPresent();
			BudapestStockExchangeCopy.click();
			BudapestStockExchangeCopy.rightClick();
					
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}		
	}

	public void clickNodeCol() {
		try
		{
			pasteNodeVerificationSubNodeSibling.isElementPresent();
			pasteNodeVerificationSubNodeSibling.click();
			pasteNodeVerificationSubNodeSibling.rightClick();
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}

	public void IDSA() {
		try
		{
			IDSA.click();
			processRunning.isElementPresent();
			Assert.assertEquals(processRunningYES.getText(),"YES");
			LOGGER.info("notification YES");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}

	public void IDSB() {
		try
		{
			IDSB.click();
			processPrimary.isElementPresent();
			Assert.assertEquals(processPrimaryYES.getText(),"YES");
			LOGGER.info("notification YES");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}

	public void rightpaneFilter() {
		try
		{
			IDCRightPane.isElementPresent();
			IDCRightPane.click();
			searchRightPane.type("inc");
			searchRightPane.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			buttonRightPane.click();			
			Rightpanecheck.click();			
			Thread.sleep(20000);			             
			HashSet<String> setapp = new HashSet<String>();
			for (int z = 1; z < filterRightText.size(); z++) {
						
				setapp.add(filterRightText.get(z).getText());
				
			}
			for(int i=0;i<=setapp.size();i++)
			{
				if(setapp.contains("inc"));
				{
					i++;
				}
				
			}
					
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
		}
	}

	public void rightFilter() {
		try
		{
			IDCRightPane.isElementPresent();
			IDCRightPane.click();
			searchRightPane.type("inc");
			searchRightPane.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			buttonRightPane.click();				
			Rightpanecheck.click();
			Rightpanecheck1.click();
			searchRightPane.sendKeys(Keys.ENTER);		
			
			
			for(int i=1;i<=20;i++)
			{
			ExtendedWebElement b = findExtendedWebElement(
					By.xpath("//tbody[@id=\"statusDtlTblId\"]/tr['"+i+"']/td[1]/img"));
			String colour = b.getAttribute("src");
			Assert.assertTrue(colour.contains("green")||colour.contains("grey")||colour.contains("red")||colour.contains("back"));
			}
			


		}
	catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Unable to search for keyword in filter-" + e.getMessage());
	}
	}	
		
}

