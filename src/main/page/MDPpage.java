package com.grip.page;


import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class MDPpage extends AbstractPage {

	
	public MDPpage(WebDriver driver) {
		super(driver);
	}
	Logger LOGGER = Logger.getLogger(Statuspage.class);
	ExcelUtils excel = new ExcelUtils();
	
	@FindBy(xpath = "//div[@class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active']")
	private ExtendedWebElement index_Family;
	
	@FindBy(xpath = "//a[@id='ui-id-4']")
	public  ExtendedWebElement researchTab;
	
	
	
	@FindBy(xpath = "//span[@data-bind='text: eodExchange']")
	public  ExtendedWebElement eodExchange;
	
	@FindBy(xpath = "//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1]")
	private ExtendedWebElement selectFirstData;
	
	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[11]")
	private ExtendedWebElement copyID;
	
	@FindBy(xpath = "//textarea[@id=\"copyIdTextareaId\"]")
	private ExtendedWebElement textArea;
	
	@FindBy(xpath = "/html/body/div[8]/div[3]/div/button[1]/span")
	private ExtendedWebElement copyToClipboard;
	
	@FindBy(xpath = "//*[starts-with(@id,'exchangeTable')]//input")
	private ExtendedWebElement ExchangesSearchBox;
	
	@FindBy(xpath = "//div[@class='statusBar1 statusBar ui-widget-header']//span")
	private ExtendedWebElement totalCount;
	
	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[1]")
	private ExtendedWebElement showStock;
	
	@FindBy(xpath = "//span[@class='statusBarSpan' and @id='statusBarSpan2']")
	private ExtendedWebElement total_record_second;
	
	@FindBy(xpath = "//div[@class='window_header window_header_normal ui-widget-header ui-corner-top no-resizable  researchWindowheader']")
	private ExtendedWebElement listTitle;

	public void validatedashboard() {
		ExchangePage exc = new ExchangePage(getDriver());
		exc.getTimeInDashboard();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		if (index_Family.isElementPresent()) {

			LOGGER.info("Research Page Loaded Successfully");
	}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("dashboardframe");
	
	}

	public void clickEodExchange() {
		try {	
			
			eodExchange.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("researchframe");
					
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}

	public void verifyEodExchangeID() {
		try {
			selectFirstData.click();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			copyID.click();
			/*driver.findElement(By.xpath("/html/body/div[8]/div[2]/table/tbody/tr/td[1]/textarea"))
				.sendKeys(Keys.chord(Keys.CONTROL, "a"));*/
			String textAreaText = textArea.getText();
			copyToClipboard.click(1000);
			ExchangeSQLStatementsLocal.eodExchange();
			Assert.assertTrue(textAreaText.equalsIgnoreCase(ExchangeSQLStatementsLocal.EODExchange));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}

	public void verifyEodExchangeCount() {
		try {
			String total = totalCount.getText();
			System.out.println(total);
			ExchangeSQLStatementsLocal.eodExchangeTotalCount();
			LOGGER.info(ExchangeSQLStatementsLocal.EODExchangeTotal);
			Assert.assertTrue(total.contains(ExchangeSQLStatementsLocal.EODExchangeTotal));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
		
	}

	public void verifyShowStock() {
		try {
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(10);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			Thread.sleep(2000);
			selectFirstData.rightClick(2000);
			showStock.click();
			if (listTitle.getText().contains(searchText))
			{
				LOGGER.info("Stock list window has been opened with records of stocks available in selected exchange");
			} else {
				LOGGER.info("Error in identifying the exchange");
			}			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}

	public void verifyStockCount() {
		try
		{
		ExchangeSQLStatementsLocal.totalCount(57);					
		Assert.assertTrue(total_record_second.getText().contains(ExchangeSQLStatementsLocal.totalCountvalue));
		}			
	
	
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}

	public void verifyTableColumn() {
		try
		{
			ExchangeSQLStatementsLocal.mviewReuters(1);
		}			
	
	
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}

	public void verifymviewReuters() {
		try
		{
			ExchangeSQLStatementsLocal.mviewReuters(1);
			Assert.assertTrue(ExchangeSQLStatementsLocal.feedSourceId.equals("15"));
			Assert.assertTrue(ExchangeSQLStatementsLocal.subcriptionEnabled.equals("1"));
			Assert.assertTrue(ExchangeSQLStatementsLocal.isPrimary.equals("1"));
		}			
	
	
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}

	public void verifyTable() {
		try
		{
			ExchangeSQLStatementsLocal.mviewReuters(0);
		}			
	
	
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}

	public void verifymviewIDC() {
		try
		{
			ExchangeSQLStatementsLocal.mviewReuters(0);
			Assert.assertTrue(ExchangeSQLStatementsLocal.feedSourceId.contains("16"));
			Assert.assertTrue(ExchangeSQLStatementsLocal.subcriptionEnabled.contains("0"));
			Assert.assertTrue(ExchangeSQLStatementsLocal.isPrimary.contains("0"));
		}			
	
	
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}
	
	public static String getDate(String format) 
	{
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		return df.format(date);	
	}

	public void verifyTickLogTable() {
		try
		{
			int locateDate = 0;
			int locatedateNumber = 0;
			if (getDate("E").equalsIgnoreCase("mon") || getDate("E").equalsIgnoreCase("sun")) {
				locateDate = Integer.parseInt(getDate("dd")) - 3;
			} else {
				locateDate = Integer.parseInt(getDate("dd")) - 1;
			}
			
			if(locateDate==1||locateDate==2||locateDate==3||locateDate==4||locateDate==5||locateDate==6
					||locateDate==7||locateDate==8||locateDate==9)			
			{
			
				String original = getDate("yyyy")+getDate("-")+getDate("MM")+getDate("-")+String.format("%02d",locateDate);
				LOGGER.info(original);
				ExchangeSQLStatementsLocal.tickLogTable(original);
				Assert.assertTrue(Double.parseDouble(ExchangeSQLStatementsLocal.tickLogTableCount)!=0);
			
		} else {
				String locateDay = Integer.toString(locateDate);				
				String original = getDate("yyyy")+getDate("-")+getDate("MM")+getDate("-")+locateDay;
				ExchangeSQLStatementsLocal.tickLogTable(original);
				Assert.assertTrue(Double.parseDouble(ExchangeSQLStatementsLocal.tickLogTableCount)!=0);
			}
		}
	
	
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}

	public void verifyHistoryTable() {
		try
		{
			ExchangeSQLStatementsLocal.historyTable();
			LOGGER.info(ExchangeSQLStatementsLocal.historyTableCount);
			Assert.assertTrue(Double.parseDouble(ExchangeSQLStatementsLocal.historyTableCount)!=0);
		}			
	
	
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}
}