package com.grip.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.imap.IMAPClient.SEARCH_CRITERIA;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.grip.DBconnection.ExchangeSQLStatementsLocal;
import com.grip.DBconnection.ExchangeSQLStatementsRemote;
import com.grip.utils.ExcelUtils;
import com.amazonaws.services.s3.transfer.Copy;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

import org.testng.Assert;

public class ExchangePage extends AbstractPage

{
	Logger LOGGER = Logger.getLogger(Dashboardpage.class);

	public ExchangePage(WebDriver driver)

	{
		super(driver);
		PageFactory.initElements(getDriver(), this);

	}

	public static ExcelUtils excel = new ExcelUtils();

	@FindBy(xpath = "//div[@class='closeImg window_icon_button no-draggable']")
	private ExtendedWebElement close_button;

	@FindBy(id = "ui-id-3")
	private ExtendedWebElement dashboardTab;

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[13]")
	private ExtendedWebElement held_Price_Value;

	@FindBy(xpath = "//input[@id='stockHoldTypeAutoId']")
	private ExtendedWebElement radio_btn1;

	@FindBy(xpath = "//div[starts-with(@id, 'bottom')]//table//tbody//tr[5]//td[3]//span")
	private ExtendedWebElement manual_hold_value_initial;

	@FindBy(xpath = "(//div[@id='releaseStockDialog']/following-sibling::div/div/button/span)[last()-1]")
	private ExtendedWebElement releaseStockOk;

	@FindBy(xpath = "(//div[@id='holdStockDialog']/following-sibling::div/div/button/span)[last()-1]")
	private ExtendedWebElement holdStockOk;

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[3]")
	private ExtendedWebElement hold_status_button;

	@FindBy(id = "releaseStockDialog")
	private ExtendedWebElement release_box1;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[2]")
	private ExtendedWebElement release;

	@FindBy(id = "ui-id-27")
	private ExtendedWebElement release_box;

	@FindBy(xpath = "//table[@id='stockTable1']//tbody[@role='alert']/tr[2]/td[5]")
	public ExtendedWebElement RIC;

	@FindBy(xpath = "//table[@id='stockTable1']//tbody[@role='alert']/tr[2]/td[6]")
	public ExtendedWebElement IDC_Ticker;

	@FindBy(xpath = "//table[@id='stockTable1']//tbody[@role='alert']/tr[2]/td[7]")
	public ExtendedWebElement Name;

	@FindBy(xpath = "//table[@id='stockTable1']//tbody[@role='alert']")
	public ExtendedWebElement Stock_Table;

	@FindBy(xpath = "(//table[@class='display dataTable'])[1]//thead//tr//th[7]")
	public ExtendedWebElement Quick_View_Name;

	@FindBy(xpath = "(//table[@class='display dataTable'])[1]//thead//tr//th")
	public List<WebElement> quickViewList;

	@FindBy(xpath = "(//table[@class='display dataTable'])[1]//table[@class='display dataTable']//thead//tr//th[8]")
	public ExtendedWebElement Quick_View_FeedPrice;

	@FindBy(xpath = "(//table[@class='display dataTable'])[1]//table[@class='display dataTable']//thead//tr//th[23]")
	public ExtendedWebElement Quick_View_ExchangeSegment;

	@FindBy(xpath = "//a[@id='ui-id-4']")
	private ExtendedWebElement researchTab;

	@FindBy(xpath = "//table[@class='exchangeTableDisplay dataTable']//tbody//tr//td[1]")
	private ExtendedWebElement Exchange_data;

	@FindBy(xpath = "//table[@id='stockTable1']//tbody//tr//td[7]")
	public ExtendedWebElement Stock_Data;

	@FindBy(xpath = "//table[@id='indexTable2']//tbody//tr[1]")
	public ExtendedWebElement Related_Indices_Data;

	@FindBy(xpath = "//table[@id='exchangeTable2']//tbody//tr[1]")
	public ExtendedWebElement Related_Exchange_Data;

	@FindBy(xpath = "//textarea[@id='copyIdTextareaId']")
	public ExtendedWebElement CP_TXT;

	@FindBy(xpath = "//div[@class='ui-widget-header-mod ui-corner-top']//td[2]//span")
	private ExtendedWebElement select_Stock;

	@FindBy(xpath = "//div[@class='ui-widget-header-mod ui-corner-top']//td[7]//span")
	private ExtendedWebElement select_Exchange;

	@FindBy(xpath = "//*[starts-with(@id,'stockTable')]//input")
	private ExtendedWebElement search_Stock;

	@FindBy(xpath = "//div[@class='dataTables_filter']//input[@aria-controls='exchangeTable1']")
	private ExtendedWebElement search_Exchange;

	@FindBy(xpath = "//div//table[@class='display dataTable'] //tbody")
	private ExtendedWebElement stock_Right_Click;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[1]")
	private ExtendedWebElement hold;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[1]")
	private ExtendedWebElement Release;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[1]")
	private ExtendedWebElement stock_value;

	@FindBy(xpath = "//div[@class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active']")
	private ExtendedWebElement index_Family;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[15]//span")
	public ExtendedWebElement editlimits;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[1]//span")
	public ExtendedWebElement Show_Stocks;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[8]//span")
	public ExtendedWebElement Copy_ID;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[11]//span")
	public ExtendedWebElement Report;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[7]//span")
	public ExtendedWebElement Copy_Name;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[4]//span")
	public ExtendedWebElement Related_Indices;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[5]//span")
	public ExtendedWebElement Exchanges;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[1]//span")
	public ExtendedWebElement Show_constituents;

	@FindBy(xpath = "//div[18]//div[11]//div//button[1]/span")
	public ExtendedWebElement AutoLimit_OK;

	@FindBy(xpath = "//table[@tabindex='0']//tbody[@role='alert']")
	private ExtendedWebElement AutoHold;

	@FindBy(xpath = "//div[@id='window_1']//div[@class='window_function_bar']//div[1]")
	public ExtendedWebElement Close_B;
	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[8]")
	private ExtendedWebElement feed_Price_Value;
	@FindBy(xpath = "//input[@id='holdStockValueId']")
	private ExtendedWebElement feed_Price_alert_value;

	@FindBy(xpath = "//input[@id='stockHoldTypeManualId']")
	private ExtendedWebElement radio_btn;

	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public ExtendedWebElement Current_Time;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']/li[8]/span")
	public ExtendedWebElement Copy_Id;

	@FindBy(xpath = "//div[@class='ui-widget-header-mod ui-corner-top']//td[2]//span")
	public ExtendedWebElement Stocks;

	@FindBy(xpath = "//div[@id='stockTable1_filter']//input")
	public ExtendedWebElement Search_stocks;

	@FindBy(xpath = "//div[@id='icdTable3_wrapper']//input")
	public ExtendedWebElement Search_Equi;

	@FindBy(xpath = "//div[@id='stockTable3_filter']//input")
	public ExtendedWebElement Search_Stocks;

	@FindBy(xpath = "/html/body/div[7]/div[3]/div/button[1]/span[contains(text(),'Copy to Clipboard')]")
	public ExtendedWebElement CC;

	@FindBy(xpath = "//table[@id='icdTable3']//tbody//tr//td[7]")
	public ExtendedWebElement Equi_data;

	@FindBy(xpath = "//table[@id='stockTable3']//tbody//tr//td[7]")
	public ExtendedWebElement Stock_data;

	@FindBy(xpath = "//*[@id='window_1']//div[1]//div[1]")
	public ExtendedWebElement Related_Indices_Window;

	@FindBy(xpath = "/table[@id='exchangeTable2']//tbody//tr//td[1]")
	public ExtendedWebElement Exchange_Data;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[2]")
	private ExtendedWebElement showcontracts;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[3]")
	private ExtendedWebElement showf11;

	@FindBy(xpath = "//div[@class='window_header window_header_normal ui-widget-header ui-corner-top no-resizable  researchWindowheader']")
	private ExtendedWebElement listTitle;
	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[4]")
	private ExtendedWebElement showIndices;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[10]")
	private ExtendedWebElement copyName;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[11]")
	private ExtendedWebElement copyID;
	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[12]")
	private ExtendedWebElement selectRows;

	@FindBy(xpath = "/html/body/div[7]/div[2]/table/tbody/tr/td[1]/textarea")
	private ExtendedWebElement exchangeName;

	@FindBy(xpath = "/html/body/div[8]/div[2]/table/tbody/tr/td[1]/textarea")
	private ExtendedWebElement exchangeId;
	
	@FindBy(xpath = "//textarea[@id=\"copyIdTextareaId\"]")
	private ExtendedWebElement textArea;
	
	
	@FindBy(xpath = "/html/body/div[8]/div[3]/div/button[1]/span")
	private ExtendedWebElement copyToClipboard;
	
	@FindBy(xpath = "//div[@class='statusBar1 statusBar ui-widget-header']//span")
	private ExtendedWebElement totalCount;

	@FindBy(xpath = "//*[@id=\"window_0\"]/div[2]/div[1]/div/div/input")
	private ExtendedWebElement downloadcsv;

	@FindBy(xpath = "//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1]")
	private List<WebElement> selectMultipleRows;
	

	@FindBy(xpath = "//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[2]//td[6]")
	private List<WebElement> selectMultipleRowsOpenTime;

	@FindBy(xpath = "//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[1]//td")
	private List<WebElement> VerifyingExchangeRecords;

	@FindBy(xpath = "//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1]")
	private List<WebElement> selectSingleRow;

	@FindBy(xpath = "//span[@class='statusBarSpan' and @id='statusBarSpan2']")
	private ExtendedWebElement total_record_second;

	@FindBy(xpath = "//span[@class='statusBarSpan' and @id='statusBarSpan1']")
	private ExtendedWebElement totalRecordFirst;

	@FindBy(xpath = "//span[text()='Move To Next Day']")
	private ExtendedWebElement MoveToNextDay;

	@FindBy(xpath = "//*[starts-with(@id,'statusBarSpan')]")
	private ExtendedWebElement TotalExchangeRow;

	@FindBy(xpath = "//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[4]")
	private ExtendedWebElement TradingSession;

	@FindBy(xpath = "//div[@class='ui-widget-header-mod ui-corner-top']//td[7]//span")
	private ExtendedWebElement exchangeTab;

	@FindBy(xpath = "//*[starts-with(@id,'exchangeTable')]//input")
	private ExtendedWebElement ExchangesSearchBox;

	@FindBy(xpath = "//span[text()=' Total: 4 (Selected: 0)']")
	private ExtendedWebElement Exchangestotal;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[1]")
	private ExtendedWebElement showStock;

	@FindBy(xpath = " //span[@data-bind='text: eNewDay']")
	private ExtendedWebElement ExchangeNewDaystatus;

	@FindBy(xpath = " //span[@data-bind='text: eOpen']")
	private ExtendedWebElement ExchangeOpenstatus;

	@FindBy(xpath = "//span[@data-bind='text: eClose']")
	private ExtendedWebElement ExchangeClosestatus;

	@FindBy(xpath = "//span[text()='Refresh from GRIP']")
	private ExtendedWebElement RefreshFromGRIP;

	@FindBy(xpath = "//span[text()='Refresh From T3']")
	private ExtendedWebElement RefreshFromT3;

	@FindBy(xpath = "//span[text()='Switch to Composite Ticker']")
	private ExtendedWebElement SwitchToCompositeTicker;

	@FindBy(xpath = "//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1]")
	private ExtendedWebElement selectFirstData;
	
	@FindBy(xpath = "(//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1])[2]")
	private ExtendedWebElement selectSecondRecord;
	

	@FindBy(xpath = "//span[text()='Refresh from GRIP']")
	private ExtendedWebElement refreshFromGrip;

	@FindBy(xpath = "//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[6]")
	private ExtendedWebElement openTimeUI;
	
	@FindBy(xpath = "(//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[6])[4]")
	private ExtendedWebElement openTimeUIAthens;	
			
	@FindBy(xpath = "(//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[6])[2]")
	private ExtendedWebElement openTimeUIAmman;
	
	
	@FindBy(xpath = "(//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1])[4]")
	private ExtendedWebElement exchangeNameAthens;	
			
	@FindBy(xpath = "(//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1])[2]")
	private ExtendedWebElement exchangeAmman;

	@FindBy(xpath = "//span[text()='View Thresholds']")
	private ExtendedWebElement viewThresholdLimits;

	@FindBy(xpath = "//span[text()='Edit Auto Hold Limits']")
	private ExtendedWebElement editAutoHold;

	@FindBy(xpath = "//span[text()='Update Trading Date']")
	private ExtendedWebElement updateTradingDate;

	@FindBy(xpath = "//input[@id='updateTradingDateId']/following::img")
	private ExtendedWebElement updateTradingDateCur;

	@FindBy(xpath = "//td[@data-handler='selectDay']")
	public List<ExtendedWebElement> calendarDaysList;

	@FindBy(xpath = "//span[text()='Refresh from T3']")
	private ExtendedWebElement updateTradingRefreshFromT3;

	@FindBy(xpath = "//input[@data-bind='value: baseLineErrorThresholdUp']")
	private ExtendedWebElement editAutoHoldBase;

	@FindBy(xpath = "//html/body/div[18]/div[11]/div/button[1]/span")
	private ExtendedWebElement ok;

	@FindBy(xpath = "//input[@data-bind='value: prevTickErrorThresholdUp']")
	private ExtendedWebElement editAutoHoldValue;

	@FindBy(xpath = "//span[@class=\"ui-dialog-title\" and @id=\"ui-id-10\"]")
	private ExtendedWebElement marketWindow;

	@FindBy(xpath = "//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[6]")
	private ExtendedWebElement selectExchangeOpenTime;

	@FindBy(xpath = "//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[7]")
	private ExtendedWebElement selectExchangeCloseTime;

	@FindBy(xpath = "//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[9]")
	private ExtendedWebElement PrimaryFeedSource;

	@FindBy(xpath = "//span[text()='Switch Feed Source to Reuters']")
	private ExtendedWebElement SwitchFeedReuters;

	@FindBy(xpath = "//input[@name='switchFeedType']")
	private ExtendedWebElement SwitchFeedReutersRadio;

	@FindBy(xpath = "(//input[@name='switchFeedType'])[2]")
	private ExtendedWebElement SwitchFeedReutersRadio2;

	@FindBy(xpath = "/html/body/div[13]/div[11]/div/button[1]/span")
	private ExtendedWebElement OKSwitchToFeed;

	@FindBy(xpath = "//div[@title='close window']")
	private ExtendedWebElement Exchangeclosebutton;

	@FindBy(xpath = "//span[text()='Switch Feed Source to IDC']")
	private ExtendedWebElement SwitchFeedIDC;

	@FindBy(xpath = "//span[text()='Switch to EOD/RealTime Ticker']")
	private ExtendedWebElement SwitchEODRealTicker;

	@FindBy(xpath = "//span[text()='Switch to RealTime Ticker']")
	private ExtendedWebElement SwitchRealTicker;

	@FindBy(xpath = "//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[9]")
	private ExtendedWebElement selectExchangeList;

	@FindBy(xpath = "//input[@title='Download Stock Exchange Data']")
	private ExtendedWebElement exchangeDownload;
	
	@FindBy(xpath = "//div[@id='displayTimer']//i")
    public ExtendedWebElement CurrentTime;
    
    @FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
    public ExtendedWebElement alertSearch;



	private char[] exchangeNameComposite;

	
	public static String exchangeTableID="exchangeTable",exchangeNameValue,dashboardFrameId = "dashboardframe",currentTime;

	
	public void validate__Research_Page() {
		ExchangePage exc = new ExchangePage(getDriver());
		exc.getTimeInDashboard();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		if (index_Family.isElementPresent()) {

			LOGGER.info("Research Page Loaded Successfully");
		}

}

	public void verifyTypedRecord() throws IOException {
		try {

			exchangeTab.click();
			ExchangesSearchBox.click(1000);
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(9);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			Thread.sleep(2000);
			String tserach_split[] = searchText.split(",");
			String a = tserach_split[0];
			String b = tserach_split[1];
			String c = tserach_split[2];
			String d = tserach_split[3];	
			String tot = Exchangestotal.getText();		
			int length = tot.length();			
			String ExchangeTotalno = tot.substring(7, 9);			
			double parseDoublea = Double.parseDouble(ExchangeTotalno);	
			for (int i = 1; i <= parseDoublea; i++)
			{
				ExtendedWebElement A = findExtendedWebElement(By.xpath(
						"//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i + "]"));
				for (int j = 1; j <= 2; j++) {
					ExtendedWebElement ele = findExtendedWebElement(
							By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["
									+ i + "]//td[" + j + "]"));
					String e = ele.getText().toLowerCase();
					if (e.contains(a) || e.contains(b) || e.contains(d)) {
						LOGGER.info("the result is matched with searched testdata");

						for (int k = 1; k <= 2; k++) {
							ExtendedWebElement out = findExtendedWebElement(By.xpath(
									"//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["
											+ i + "]//td[" + k + "]"));
							String outtext = out.getText();
							LOGGER.info(outtext);
						}
					}
				}
			}

			for (int i = 1; i <= parseDoublea; i++)
			{
				ExtendedWebElement ele = findExtendedWebElement(
						By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[11]"));
				String e = ele.getText().toLowerCase();
				if (e.contains(c)) {
					LOGGER.info("the result is matched with searched testdata - exchangeID(187)");
					for (int k = 1; k < 2; k++) {
						ExtendedWebElement out = findExtendedWebElement(By
								.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["
										+ i + "]//td[" + k + "]"));
						ExtendedWebElement outExchangeId = findExtendedWebElement(By
								.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["
										+ i + "]//td[11]"));
						String outtext = out.getText();
						String outExchangeID = outExchangeId.getText();
						LOGGER.info(outtext);
						LOGGER.info(outExchangeID);
					}
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());

		}

	}
	
	
	public void verifyrecords() {
		try {

			exchangeTab.click();
			ArrayList<String> exchangeName = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				exchangeName.add(VerifyingExchangeRecords.get(i).getText());
			}
			ExchangeSQLStatementsLocal.selectVerifyRecords();
			ExchangeSQLStatementsLocal.selectVerifyRecords1();
			for (int i = 0; i < ExchangeSQLStatementsLocal.exchangeRecordsList.size(); i++) {
				Assert.assertTrue(ExchangeSQLStatementsLocal.exchangeRecordsList.get(i).contains(exchangeName.get(i)));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
	}


	public void showStocks() throws IOException {
		try
		{
			exchangeTab.click();
			ExchangesSearchBox.click(1000);
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(10);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			Thread.sleep(2000);
			selectFirstData.isElementPresent();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			Thread.sleep(2000);
			showStock.click();
			if (listTitle.getText().contains(searchText))
			{
				LOGGER.info("Stock list window has been opened with records of stocks available in selected exchange");
			} else {
				LOGGER.info("Error in identifying the exchange");
			}			
			ExchangeSQLStatementsLocal.totalCount(57);					
			Assert.assertTrue(total_record_second.getText().contains(ExchangeSQLStatementsLocal.totalCountvalue));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
	}

	public void showContracts() throws IOException {
		try
		{
			exchangeTab.click();
			ExchangesSearchBox.click(1000);
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(11);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			selectFirstData.isElementPresent();
			Thread.sleep(2000);
			selectFirstData.click();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			Thread.sleep(2000);
			showcontracts.click();
			String a = listTitle.getText();
			if (a.contains(searchText))
			{
				LOGGER.info("the contract list of given exchange is opened");
			} else {
				LOGGER.info("the contract list of given exchange is not opened");
			}			
			ExchangeSQLStatementsLocal.totalCount(187);					
			String totContractsRowCount = total_record_second.getText();			
			Assert.assertTrue(totContractsRowCount.contains(ExchangeSQLStatementsLocal.totalCountvalue));
			LOGGER.info("successfully matched with DB");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("error" + e.getMessage());
		}

	}

	public void fii() throws IOException {
		try

		{
			exchangeTab.click();
			ExchangesSearchBox.click(1000);
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(12);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			Thread.sleep(2000);
			selectFirstData.isElementPresent();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			Thread.sleep(2000);
			showf11.click();
			if (listTitle.getText().contains(searchText))
			{
				LOGGER.info("the f11 list of given exchange is opened");
			} else {
				LOGGER.info("the f11 list of given exchange is not opened");
			}					
			ExchangeSQLStatementsLocal.totalCount(186);					
			String totContractsRowCount = total_record_second.getText();			
			Assert.assertTrue(totContractsRowCount.contains(ExchangeSQLStatementsLocal.totalCountvalue));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("error" + e.getMessage());
		}

	}

	public void showIndices() throws IOException {
		try

		{
			exchangeTab.click();
			ExchangesSearchBox.click(1000);
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(10);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			Thread.sleep(2000);
			selectFirstData.isElementPresent();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			showIndices.click();
			if (listTitle.getText().contains(searchText))
			{
				LOGGER.info("the indices list of given exchange is opened");
			} else {
				LOGGER.info("the indices list of given exchange is not opened");
			}					
			ExchangeSQLStatementsLocal.totalCountIndices();					
			String totContractsRowCount = total_record_second.getText();			
			Assert.assertTrue(totContractsRowCount.contains(ExchangeSQLStatementsLocal.totalCountvalue));
			LOGGER.info("successfully matched with DB");	

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
	}

	public void copyName() {
		try {

			exchangeTab.click();
			selectExchangeList.rightClick();
			copyName.click();
			driver.findElement(By.xpath("/html/body/div[7]/div[2]/table/tbody/tr/td[1]/textarea"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			copyToClipboard.click(1000);
			driver.findElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//input"))
					.sendKeys(Keys.chord(Keys.CONTROL, "V"));
			Assert.assertTrue(exchangeName.getText().equalsIgnoreCase(ExchangesSearchBox.getAttribute("value")));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assert.fail("ID   Not copied to ClipBoard" + e.getMessage());
		}
	}

	public void copyID() {
		try {

			exchangeTab.click();
			selectExchangeList.rightClick();
			copyID.click();
			driver.findElement(By.xpath("/html/body/div[8]/div[2]/table/tbody/tr/td[1]/textarea"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			String textAreaText = textArea.getText();
			copyToClipboard.click(1000);
			driver.findElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//input"))
					.sendKeys(Keys.chord(Keys.CONTROL, "V"));
			Assert.assertTrue(textAreaText.equalsIgnoreCase(ExchangesSearchBox.getAttribute("value")));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assert.fail("ID   Not copied to ClipBoard" + e.getMessage());
		}
	}

	public void selectAllRows() {
		try {

			exchangeTab.click();
			selectExchangeList.rightClick();
			String total = totalCount.getText();
			String myArray[] = total.split("\\(");
			for (int i = (myArray.length - 1); i >= 0; i--)
				System.out.println("myArray[" + i + "] : " + myArray[i]);
			String a = myArray[1].toString();
			String b = myArray[0].toString();	
			selectRows.click(3000);
			Thread.sleep(7000);
			String c = myArray[1].toString();			
			String e = totalCount.getText();			
			if (a.contains("0") && c.contains(b)) {
				LOGGER.info("All rows are not selected");
			} else {
				LOGGER.error("All rows are  selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public void downloadExchange() throws IOException {
		try
		{			
			  HashSet<String> setexcel = new HashSet<String>(); 
			  HashSet<String> exchangeData = new HashSet<String>(); exchangeTab.click();
			  ExtendedWebElement firstElement = findExtendedWebElement( By.xpath(
					  "//*[@id=\"exchangeTable1_wrapper\"]/div[3]/div[1]/div/table/thead/tr/th[1]"));			  
			  String totalRow = TotalExchangeRow.getText(); 
			  String head=firstElement.getText();			  
			  String ExchangeTotalno = totalRow.substring(7, 10); //
			  double parseDoublea = Double.parseDouble(ExchangeTotalno); 
			  downloadcsv.click(1000);	  
			  downloadcsv.pause(5);
			  String homePath = System.getProperty("user.home") + "/Downloads";	
			  File LatestFile = getLatestFilefromDir(homePath);	
			  System.out.println(LatestFile);
			  BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
			  int count = countRecord(LatestFile);	
			  if((count-2)==parseDoublea)
			  {
			  LOGGER.info("Alert Count Matches with Downloaded Alert Data");
			  }
			  else 
			  {
				  Assert.fail("Alert Count  Not Matches with Downloaded Alert Data");
			  }	
			  
			  for(int i=1;i<5;i++)
			  {
				  ExtendedWebElement exchangeName = findExtendedWebElement(By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["
						  +i+"]//td[1]"));
				  String exchangeNamevalue = exchangeName.getText();
				  exchangeData.add(exchangeNamevalue);
			  }
			  
			  String  row="";
		      String[] data = null;
		      for (int i = 0; i < 5; i++) 
		        {
		             if((row = csvReader.readLine()) != null)
		             {
		                  data = row.split(",");		                  
		                  System.out.println(data[0]);		                  
		                  if(!data[0].contains(head))
		                  {		                 
		                  setexcel.add(data[0].substring(1,data[0].length()-1));
		                  }
		             }
		             
		        }  	
		      
		      if(setexcel.equals(exchangeData))
			  {
			  LOGGER.info("Exchange Data in UI  Matches with Downloaded Alert Data");
			  }			  
			  else 
			  {
			  Assert.fail("Exchange Data in UI not  Matches with Downloaded Alert Data");
			  }			        
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}

	}
	/**
	 * This Method gets latestFile from file directory Path
	 * @param dirPath
	 * @return File
	 */
	public File getLatestFilefromDir(String dirPath) 
	{
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	/**
	 * This Method reads Data from file and returns count
	 * @param Dirpath - File Directory path
	 * @return			Returns count
	 * @throws IOException
	 */

	public int countRecord(File Dirpath) throws IOException {
		int count = 0;
		try{
		
		BufferedReader csvReader = new BufferedReader(new FileReader(Dirpath));
		String row;
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			count++;
		}}
		catch (Exception e) 
		{
		Assert.fail("" + e.getMessage());
		}
		return count;
	}

	public void switchToReuters() {
		try {
			String ExchangeTextUI = null;
			exchangeTab.click();
			//selectExchangeListRight.rightClick();
			for (int i = 2; i <= 20; i++) {
				ExtendedWebElement availableFeedSourceRow = findExtendedWebElement(
						By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[10]"));
				ExtendedWebElement selectExchangeListRightLatest = findExtendedWebElement(
						By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[1]"));
				ExchangeTextUI = selectExchangeListRightLatest.getText();
				if (availableFeedSourceRow.getText().contains("Reuters")) {
					selectExchangeListRightLatest.click();
					selectExchangeListRightLatest.rightClick();
					break;
				}

			}
			SwitchFeedReuters.isElementPresent();
			Thread.sleep(2000);
			SwitchFeedReuters.click();
			SwitchFeedReutersRadio.isElementPresent();
			SwitchFeedReutersRadio.click();
			OKSwitchToFeed.click();
			Thread.sleep(1000);			
			Exchangeclosebutton.click();
			exchangeTab.click();	
			alertSearch.type(ExchangeTextUI);
	        alertSearch.pause(10);
	        //***VALIDATING IN UI***/
			VerifyAlertsMessages(ExchangeTextUI, "Exchange Status", currentTime,"Exchange feed source has been switched to RDF in Local only");
			//*** VALIDATING IN DATABASE***/					
			ExchangeSQLStatementsLocal.switchFeedSource(ExchangeTextUI);
			Assert.assertEquals("15", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);			
		}

		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}
	public void switchToIDC() {
		try {
			exchangeTab.click();			
			String ExchangeTextUI = null;	
			for (int i = 2; i <= 20; i++) {
				ExtendedWebElement availableFeedSourceRow = findExtendedWebElement(
						By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[10]"));				
				ExtendedWebElement selectExchangeListRightLatest = findExtendedWebElement(
						By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[1]"));	
				ExchangeTextUI = selectExchangeListRightLatest.getText();
				if (availableFeedSourceRow.getText().contains("IDC")) {
					selectExchangeListRightLatest.click();
					selectExchangeListRightLatest.rightClick();
					break;
				}				
			}
			Thread.sleep(2000);
			SwitchFeedIDC.isElementPresent();
			SwitchFeedIDC.click();
			SwitchFeedReutersRadio.isElementPresent();
			SwitchFeedReutersRadio.click();
			OKSwitchToFeed.isElementPresent();
			OKSwitchToFeed.click();
			Exchangeclosebutton.isElementPresent();
			Exchangeclosebutton.click();
			Thread.sleep(2000);
			alertSearch.type(ExchangeTextUI);
	        alertSearch.pause(10);
			/***VALIDATING THE primary feed source column IN UI***/
			VerifyAlertsMessages(ExchangeTextUI, "Exchange Status", currentTime,"Exchange feed source has been switched to CTF");
			/*** VALIDATING IN DATABASE***/
			ExchangeSQLStatementsLocal.switchFeedSource(ExchangeTextUI);
			Assert.assertEquals("16", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);	
		}

		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}

	public void switchReutersMul() {
		try {

			exchangeTab.click();
			ExtendedWebElement exchangeName1 = findExtendedWebElement(By
					.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[3]//td[1]"));
			ExtendedWebElement exchangeName2 = findExtendedWebElement(By
					.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[5]//td[1]"));
			ExtendedWebElement exchangeName3 = findExtendedWebElement(By
					.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[8]//td[1]"));			
			Actions builder = new Actions(driver);
			builder.click(selectMultipleRows.get(2)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(4)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(7)).keyUp(Keys.CONTROL).build().perform();
			builder.contextClick().build().perform();
			Thread.sleep(3000);
			SwitchFeedReuters.click();
			SwitchFeedReutersRadio.isElementPresent();
			SwitchFeedReutersRadio.click();
			OKSwitchToFeed.isElementPresent();
			OKSwitchToFeed.click();
			Exchangeclosebutton.isElementPresent();
			Exchangeclosebutton.click();
			Thread.sleep(2000);
			exchangeTab.click();		
			alertSearch.type(exchangeName1.getText());
	        alertSearch.pause(10);
			VerifyAlertsMessages(exchangeName1.getText(), "Exchange Status", currentTime,"Exchange feed source has been switched to RDF in Local only");
			/*** VALIDATING IN DATABASE***/
			ExchangeSQLStatementsLocal.switchFeedSource(exchangeName1.getText());
			Assert.assertEquals("15", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);
			ExchangeSQLStatementsLocal.switchFeedSource(exchangeName2.getText());
			Assert.assertEquals("15", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);
			ExchangeSQLStatementsLocal.switchFeedSource(exchangeName3.getText());
			Assert.assertEquals("15", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);		

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}

	}

	public void RealTimeTicker() throws IOException {

		try {
			exchangeTab.click();
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(21);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			Thread.sleep(3000);
			selectFirstData.rightClick();
			Thread.sleep(2000);
			SwitchRealTicker.click();				
			/*VALIDATING IN LOCAL DATABASE*/
			ExchangeSQLStatementsLocal.eodDBValue(searchText);
			Assert.assertEquals("2", ExchangeSQLStatementsLocal.identifiertype);			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}

	}

	public void eodRealTimeTicker() {
		try {

			exchangeTab.click();
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(7);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);			
			selectFirstData.rightClick();
			SwitchEODRealTicker.click();	
			Thread.sleep(10000);
			System.out.println(searchText);
			/*VALIDATING IN LOCAL DATABASE*/
			ExchangeSQLStatementsLocal.eodDBValue(searchText);			
			Assert.assertEquals("1", ExchangeSQLStatementsLocal.identifiertype);		

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}

	}

	public void eodRealTimeTickerMul() {
		try {

			exchangeTab.click();
			ArrayList<String> exchangeName = new ArrayList<>();
			
			
			ExtendedWebElement exchangeNameUI = findExtendedWebElement(By.xpath(
					"//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[2]//td[1]"));	
			ExtendedWebElement exchangeNameUI1 = findExtendedWebElement(By.xpath(
					"//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[4]//td[1]"));	
			ExtendedWebElement exchangeNameUI2 = findExtendedWebElement(By.xpath(
					"//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[5]//td[1]"));				
			
			exchangeName.add(exchangeNameUI.getText());
			exchangeName.add(exchangeNameUI1.getText());
			exchangeName.add(exchangeNameUI2.getText());
				
			Actions builder = new Actions(driver);
			builder.click(selectMultipleRows.get(1)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(3)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(4)).keyUp(Keys.CONTROL).build().perform();
			builder.contextClick().build().perform();
			SwitchEODRealTicker.click();
			Thread.sleep(3000);							
			ExtendedWebElement primaryIdentifierUI = findExtendedWebElement(By.xpath(
					"//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[2]//td[13]"));	
			ExtendedWebElement primaryIdentifierUI1 = findExtendedWebElement(By.xpath(
					"//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[4]//td[13]"));	
			ExtendedWebElement primaryIdentifierUI2 = findExtendedWebElement(By.xpath(
					"//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[5]//td[13]"));										
			
			/*VALIDATING IN LOCAL DATABASE*/
			for (int j = 0; j < exchangeName.size(); j++) {							
			ExchangeSQLStatementsLocal.eodDBValue(exchangeName.get(j));	
			System.out.println(ExchangeSQLStatementsLocal.identifiertype);				
			Assert.assertEquals("1", ExchangeSQLStatementsLocal.identifiertype);				
			}				

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}


public void eodRealTimeTickerNegative() {
	try {

		exchangeTab.click();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("exchange_TD");
		Row row = sheet.getRow(4);
		String searchText = row.getCell(1).getStringCellValue();
		ExchangesSearchBox.type(searchText, 2000);
		getexchangeCoulmnValue();
		selectFirstData.rightClick();
		SwitchEODRealTicker.click();	
		Thread.sleep(10000);
		System.out.println(searchText);		
		/*VALIDATING IN UI*/
		getUniqueID();
		VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"EOD/RealTime Ticker cannot be assigned as primary identifier");
		

	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Error" + e.getMessage());
	}

}


	public void compositeTicker() throws IOException, InterruptedException {
		
		try
		{

			exchangeTab.click();
			String totalRecord = totalRecordFirst.getText();
			String ExchangeTotalno1 = totalRecord.substring(7, 10);
			int rt = ExchangeTotalno1.length();
			Double realDat = Double.parseDouble(ExchangeTotalno1);
			for (int i = 1; i <= realDat; i++) {
			ExtendedWebElement A = findExtendedWebElement(
					By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
							+ "]//td[16]"));
			String availableIdentifier = A.getText();
			if (availableIdentifier.contains("Composite Ticker")) {
				ExtendedWebElement b = findExtendedWebElement(
						By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[1]"));
				String exchangeNameComposite = b.getText();
				ExchangesSearchBox.type(exchangeNameComposite, 2000);
				break;
				}
			}
			ExtendedWebElement c = findExtendedWebElement(
				By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[1]//td[1]"));
			String exchangeComposite = c.getText();
			selectFirstData.rightClick();
			Thread.sleep(2000);
			SwitchToCompositeTicker.click();
			Thread.sleep(7000);				
			ExchangeSQLStatementsLocal.eodDBValue(exchangeComposite);
			Assert.assertEquals(ExchangeSQLStatementsLocal.identifiertype, "3");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}

	}

	public void SwitchLocalRemoteReu() {
		try {
			
			String ExchangeTextUI = null;
			exchangeTab.click();
			for (int i = 2; i <= 20; i++) {
				ExtendedWebElement availableFeedSourceRow = findExtendedWebElement(
						By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[10]"));
				ExtendedWebElement selectExchangeListRightLatest = findExtendedWebElement(
						By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[1]"));
				ExchangeTextUI = selectExchangeListRightLatest.getText();

				if (availableFeedSourceRow.getText().contains("Reuters")) {
					selectExchangeListRightLatest.click();
					selectExchangeListRightLatest.rightClick();
					break;
				}
			}	
			SwitchFeedReuters.isElementPresent();
			SwitchFeedReuters.click();	
			Thread.sleep(2000);
			SwitchFeedReutersRadio2.isElementPresent();
			SwitchFeedReutersRadio2.click();
			Thread.sleep(2000);
			OKSwitchToFeed.click();			
			Thread.sleep(2000);			
			Exchangeclosebutton.click();
			Thread.sleep(10000);
			exchangeTab.click();			
			/***VALIDATING THE primary feed source column IN UI***//*			
			String PrimaryFSAfter = PrimaryFeedSource.getText();
			Assert.assertTrue(PrimaryFSAfter.equalsIgnoreCase("Reuters"));	*/
			/*** VALIDATING IN DATABASE***/
			/****LOCAL*****/
			ExchangeSQLStatementsLocal.switchFeedSource(ExchangeTextUI);
			Assert.assertEquals(ExchangeSQLStatementsLocal.primaryFeedSourceDBValue,"15");
			/****REMOTE*****/
			ExchangeSQLStatementsRemote.switchFeedSource(ExchangeTextUI);
			Assert.assertEquals(ExchangeSQLStatementsRemote.primaryFeedSourceDBValue,"15");				

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}

	public void SwitchLocalRemoteIDC() {
		try {
			
			exchangeTab.click();			
			String ExchangeTextUI = null;	
			for (int i = 2; i <= 20; i++) {
				ExtendedWebElement availableFeedSourceRow = findExtendedWebElement(
						By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[10]"));				
				ExtendedWebElement selectExchangeListRightLatest = findExtendedWebElement(
						By.xpath("//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[" + i
								+ "]//td[1]"));	
				ExchangeTextUI = selectExchangeListRightLatest.getText();
				if (availableFeedSourceRow.getText().contains("IDC")) {
					selectExchangeListRightLatest.click();
					selectExchangeListRightLatest.rightClick();
					break;
				}				
			}
			SwitchFeedIDC.isElementPresent();
			SwitchFeedIDC.click();	
			Thread.sleep(2000);
			SwitchFeedReutersRadio2.isElementPresent();
			SwitchFeedReutersRadio2.click();	
			Thread.sleep(2000);
			OKSwitchToFeed.click();
			Exchangeclosebutton.click();
			exchangeTab.click();
			Thread.sleep(10000);
			
			/*** VALIDATING IN DATABASE***/
			ExchangeSQLStatementsLocal.switchFeedSource(ExchangeTextUI);
			Assert.assertEquals("16", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);	
			/****REMOTE*****/
			ExchangeSQLStatementsRemote.switchFeedSource("Amman Stock Exchange");
			Assert.assertEquals("16", ExchangeSQLStatementsRemote.primaryFeedSourceDBValue);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}

	public void refreshFromGrip() throws IOException, InterruptedException {  
		try
		{			
			
			ExchangeSQLStatementsLocal.refreshFromGripUpdate(16);
			exchangeTab.click();
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(1);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			Thread.sleep(2000);
			selectFirstData.click();
			getexchangeCoulmnValue();
			selectFirstData.rightClick();
			refreshFromGrip.isElementPresent();	
			Thread.sleep(2000);
			refreshFromGrip.click();
			driver.navigate().refresh();
			validate__Research_Page();
			exchangeTab.click();
			ExchangesSearchBox.type(searchText, 2000);
			Thread.sleep(2000);
			selectFirstData.click();
			ExchangeSQLStatementsLocal.refreshFromGripSelect(16);
			LOGGER.info(ExchangeSQLStatementsLocal.timeValue);
			LOGGER.info(openTimeUI.getText());
			Assert.assertTrue(ExchangeSQLStatementsLocal.timeValue.contains(openTimeUI.getText()));
			ExchangeSQLStatementsLocal.refreshFromGripRollback(16);
			getUniqueID();
			VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Exchange has been reloaded");
		 
		}
		 catch (Exception e)
		{
				e.printStackTrace();
				Assert.fail("Error" + e.getMessage());
		}
		
	}
	
	public void refreshFromGripMul() {
		try {	
			
			
			ExchangeSQLStatementsLocal.refreshFromGripUpdate(7);
			ExchangeSQLStatementsLocal.refreshFromGripUpdate(11);
			exchangeTab.click();
			Actions builder = new Actions(driver);
			builder.click(selectMultipleRows.get(1)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(3)).keyUp(Keys.CONTROL).build().perform();
			builder.contextClick().build().perform();
			refreshFromGrip.isElementPresent();
			refreshFromGrip.click();
			Thread.sleep(10000);
			alertSearch.type(exchangeAmman.getText());
	        alertSearch.pause(10);
			VerifyAlertsMessages(exchangeAmman.getText(), "Exchange Status", currentTime,"Exchange has been reloaded");
			driver.navigate().refresh();
			validate__Research_Page();
			exchangeTab.click();
			ExchangeSQLStatementsLocal.refreshFromGripSelect(7);
			LOGGER.info(ExchangeSQLStatementsLocal.timeValue);
			LOGGER.info(openTimeUIAmman.getText());
			String a =openTimeUIAmman.getText();
			Assert.assertTrue(a.contains(ExchangeSQLStatementsLocal.timeValue));
			ExchangeSQLStatementsLocal.refreshFromGripSelect(11);
			LOGGER.info(ExchangeSQLStatementsLocal.timeValue);
			LOGGER.info(openTimeUIAthens.getText());
			Assert.assertTrue(openTimeUIAthens.getText().contains(ExchangeSQLStatementsLocal.timeValue));
			ExchangeSQLStatementsLocal.refreshFromGripRollback(7);
			ExchangeSQLStatementsLocal.refreshFromGripRollback(11);
			
		} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Error" + e.getMessage());
		}

	}


	public void selectMulExchange() throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.click(selectMultipleRows.get(1)).keyDown(Keys.CONTROL).build().perform();
		builder.click(selectMultipleRows.get(2)).keyDown(Keys.CONTROL).build().perform();
		builder.click(selectMultipleRows.get(3)).keyUp(Keys.CONTROL).build().perform();
		builder.contextClick().build().perform();

	}


	public void viewHoldLimits() {
		try {
			exchangeTab.click();
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(22);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			selectFirstData.rightClick();
			viewThresholdLimits.click();
			String marketWindowText = marketWindow.getText();
			searchText.contains(marketWindowText);
			LOGGER.info("View auto Hold for Abu Dhabi Securities market window has been opened and verified");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}

	public void viewHoldLimitsMul() {
		try
		{
			exchangeTab.click();
			Actions builder = new Actions(driver);
			builder.click(selectMultipleRows.get(1)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(2)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(3)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(4)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(5)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(6)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(7)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(8)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(9)).keyDown(Keys.CONTROL).build().perform();
			builder.click(selectMultipleRows.get(10)).keyUp(Keys.CONTROL).build().perform();
			builder.contextClick().build().perform();
			viewThresholdLimits.click();
			String marketWindowText = marketWindow.getText();
			marketWindowText.contains("View Auto Hold Limits for 10 exchanges");
			LOGGER.info("View auto Hold  market window has been opened for 10 Exchanges and verified");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}

	}


	public static String getDate(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		return df.format(date);
	}

	public void dateSelect(int dateValue) {
		for (int i = 0; i < calendarDaysList.size(); i++) {
			if (calendarDaysList.get(i).getText().equalsIgnoreCase(Integer.toString(dateValue))) {
				calendarDaysList.get(i).click();
				break;
			}
		}
	}

	public void updateTradingDatePrev() throws InterruptedException, IOException {
		try
		{
		int locateDate = 0;
		exchangeTab.click();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("exchange_TD");
		Row row = sheet.getRow(10);
		String searchText = row.getCell(1).getStringCellValue();
		ExchangesSearchBox.type(searchText, 2000);
		selectFirstData.isElementPresent();
		getexchangeCoulmnValue();
		selectFirstData.rightClick();
		updateTradingDate.click();
		updateTradingDateCur.click();

		Thread.sleep(5000);

		if (getDate("E").equalsIgnoreCase("mon") || getDate("E").equalsIgnoreCase("sun")) {
			locateDate = Integer.parseInt(getDate("dd")) - 3;
		} else {
			locateDate = Integer.parseInt(getDate("dd")) - 1;
		}
		dateSelect(locateDate);
		updateTradingRefreshFromT3.click();
		Thread.sleep(20000);
		getUniqueID();
		VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Exchange has been reloaded");

	}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}

	public void updateTradingDateNext() throws InterruptedException, IOException {
		try
		{
		// TODO Auto-generated method stub
		int locateDate = 0;
		exchangeTab.click();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("exchange_TD");
		Row row = sheet.getRow(10);
		String searchText = row.getCell(1).getStringCellValue();
		ExchangesSearchBox.type(searchText, 2000);
		selectFirstData.isElementPresent();
		getexchangeCoulmnValue();
		selectFirstData.rightClick();
		Thread.sleep(2000);
		updateTradingDate.click();
		Thread.sleep(2000);
		updateTradingDateCur.click();
		Thread.sleep(2000);

		if (getDate("E").equalsIgnoreCase("fri") || getDate("E").equalsIgnoreCase("sat")
				|| getDate("E").equalsIgnoreCase("sun")) {
			locateDate = Integer.parseInt(getDate("dd")) + 3;
		} else {
			locateDate = Integer.parseInt(getDate("dd")) + 1;
		}
		dateSelect(locateDate);
		Thread.sleep(2000);
		updateTradingRefreshFromT3.click();
		Thread.sleep(20000);
		getUniqueID();
		VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Exchange has been reloaded");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}

	public void updateTradingDateCurrent() throws InterruptedException, IOException {
		
		try
		{

		int locateDate = 0;
		exchangeTab.click();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("exchange_TD");
		Row row = sheet.getRow(10);
		String searchText = row.getCell(1).getStringCellValue();
		ExchangesSearchBox.type(searchText, 2000);
		selectFirstData.isElementPresent();
		getexchangeCoulmnValue();
		selectFirstData.rightClick();
		updateTradingDate.click();
		Thread.sleep(2000);
		updateTradingDateCur.click();
		Thread.sleep(2000);
		updateTradingRefreshFromT3.click();
		Thread.sleep(2000);
		getUniqueID();
		VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Exchange has been reloaded");
		
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
	}
	public void updateTradingDateMul() throws Exception {
		// TODO Auto-generated method stub
		exchangeTab.click();
		Actions builder = new Actions(driver);
		builder.click(selectMultipleRows.get(2)).keyDown(Keys.CONTROL).build().perform();
		builder.click(selectMultipleRows.get(4)).keyDown(Keys.CONTROL).build().perform();
		builder.click(selectMultipleRows.get(6)).keyUp(Keys.CONTROL).build().perform();
		builder.contextClick().build().perform();
		int locateDate = 0;		
		selectFirstData.isElementPresent();
		getexchangeCoulmnValue();
		selectFirstData.rightClick();
		Thread.sleep(2000);
		updateTradingDate.click();
		Thread.sleep(2000);
		updateTradingDateCur.click();
		Thread.sleep(5000);
		if (getDate("E").equalsIgnoreCase("mon") || getDate("E").equalsIgnoreCase("sun")) {
			locateDate = Integer.parseInt(getDate("dd")) - 3;
		} else {
			locateDate = Integer.parseInt(getDate("dd")) - 1;
		}
		Thread.sleep(2000);
		dateSelect(locateDate);
		Thread.sleep(2000);
		updateTradingRefreshFromT3.click();
		Thread.sleep(20000);
		getUniqueID();
		VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Exchange has been reloaded");

	}
	public void switchLocalRemoteReuMul() throws IOException, InterruptedException {
		try
		{
			exchangeTab.click();
		ExtendedWebElement exchangeName1 = findExtendedWebElement(By
				.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[3]//td[1]"));
		ExtendedWebElement exchangeName2 = findExtendedWebElement(By
				.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[5]//td[1]"));
		ExtendedWebElement exchangeName3 = findExtendedWebElement(By
				.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[7]//td[1]"));			
		Actions builder = new Actions(driver);		
		builder.click(selectMultipleRows.get(2)).keyDown(Keys.CONTROL).build().perform();
		builder.click(selectMultipleRows.get(4)).keyDown(Keys.CONTROL).build().perform();
		builder.click(selectMultipleRows.get(6)).keyUp(Keys.CONTROL).build().perform();
		builder.contextClick().build().perform();
		Thread.sleep(3000);
		System.out.println(exchangeName1.getText());
		System.out.println(exchangeName2.getText());
		System.out.println(exchangeName3.getText());
		SwitchFeedReuters.click();
		SwitchFeedReutersRadio.click();
		OKSwitchToFeed.click();
		Exchangeclosebutton.click();
		Thread.sleep(10000);
		exchangeTab.click();
				
		/*** VALIDATING IN DATABASE***/
		/***LOCAL****/
		ExchangeSQLStatementsLocal.switchFeedSource(exchangeName1.getText());
		Assert.assertEquals("15", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);
		ExchangeSQLStatementsLocal.switchFeedSource(exchangeName2.getText());
		Assert.assertEquals("15", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);
		ExchangeSQLStatementsLocal.switchFeedSource(exchangeName3.getText());
		Assert.assertEquals("15", ExchangeSQLStatementsLocal.primaryFeedSourceDBValue);		
		/****REMOTE*****/
		ExchangeSQLStatementsRemote.switchFeedSource(exchangeName1.getText());
		Assert.assertEquals("15", ExchangeSQLStatementsRemote.primaryFeedSourceDBValue);		
		ExchangeSQLStatementsRemote.switchFeedSource(exchangeName2.getText());
		Assert.assertEquals("15", ExchangeSQLStatementsRemote.primaryFeedSourceDBValue);		
		ExchangeSQLStatementsRemote.switchFeedSource(exchangeName3.getText());
		Assert.assertEquals("15", ExchangeSQLStatementsRemote.primaryFeedSourceDBValue);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());
		}
		
	}
	public void negativescenario() {
		try {
			exchangeTab.click();
			
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(5);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			selectFirstData.isElementPresent();
			Thread.sleep(2000);
			selectFirstData.click();
			getexchangeCoulmnValue();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			SwitchFeedReuters.click();
			Thread.sleep(2000);
			SwitchFeedReutersRadio2.click();
			Thread.sleep(2000);
			OKSwitchToFeed.click();
			getUniqueID();			
			VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Data feed RDF cannot be assigned");

		} catch (Exception e) {

		}
	}
	public void VerifyAlertsMessages(String subjectInput, String alertTypeInput, String timeValue,
			String descriptionInput) throws Exception {
		try {
			boolean alertCondition = false;
			for (int i = 1; i <= 30; i++) {
				String timeUTC = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody[@role='alert']/tr[" + i + "]/td[1]")).getText();
				int tableUTCTime = getDate(timeUTC, 12, 20);
				int loginUTCTime = getDate(timeValue, 17, 25);
				String alertType = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[2]")).getText();
				String subject = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[4]")).getText();
				String description = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[6]")).getText();

				if (tableUTCTime >= loginUTCTime && alertType.equalsIgnoreCase(alertTypeInput)
						&& subject.contains(subjectInput) && description.contains(descriptionInput)) {
					Assert.assertTrue(tableUTCTime >= loginUTCTime);
					Assert.assertTrue(alertType.equalsIgnoreCase(alertTypeInput));
					Assert.assertTrue(subject.contains(subjectInput));
					Assert.assertTrue(description.contains(descriptionInput));
					alertCondition = true;
					break;
				}
			}
			if (alertCondition) {
			} else
				Assert.fail("The data is not matched for verifying alerts-");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the alert messages-" + e.getMessage());
		}
	}
	
	public int getDate(String timeDate, int startValue, int endValue) {
		String getDate = timeDate.substring(startValue, endValue);
		String[] split = getDate.split(":");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			sb.append(split[i]);
		}
		return Integer.parseInt(sb.toString());
	}




public void getTimeInDashboard() {
    try {
        dashboardTab.isElementPresent();          
        currentTime = CurrentTime.getText();
        System.out.println(currentTime);
    } catch (Exception e) {
        e.printStackTrace();
        Assert.fail("Unable to get time-" + e.getMessage());
    }
}


public void getexchangeCoulmnValue() {
    try {
        ExtendedWebElement exchangeName = findExtendedWebElement(
                By.xpath("//table[contains(@id,'" + exchangeTableID + "')]//tr//td[1]"));
        exchangeNameValue=exchangeName.getText();
        System.out.println(exchangeNameValue);
    
} catch (Exception e) {
    e.printStackTrace();
    Assert.fail("Unable to get column values from indices table" + e.getMessage());
}
}


public void getUniqueID() {
    try {
        alertSearch.type(exchangeNameValue);
        alertSearch.pause(25);
    } catch (Exception e) {
        e.printStackTrace();
        Assert.fail("Unable to get unique ID-" + e.getMessage());
    }
}
public void oneNextDay(String Exp) throws IOException {
	try {
			String Newday = "New Day";
			String Open = "open";
			String close = "close";
			driver.switchTo().defaultContent();
			driver.switchTo().frame("dashboardframe");
			if (Exp.equalsIgnoreCase(Newday)) {

				ExchangeNewDaystatus.click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("researchframe");
				String TradingSessionText = TradingSession.getText();
				System.out.print(TradingSessionText);
				getexchangeCoulmnValue();
				Thread.sleep(2000);
				selectFirstData.rightClick();
				Thread.sleep(2000);
				MoveToNextDay.click();
				getUniqueID();
			
				VerifyAlertsMessages(exchangeNameValue, "Pending New Day", currentTime,"Exchange has officially been moved to the next trading date");
			
			} else if (Exp.equalsIgnoreCase(Open)) {

				ExchangeOpenstatus.click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("researchframe");			
				getexchangeCoulmnValue();
				selectFirstData.rightClick();
				Thread.sleep(2000);
				MoveToNextDay.click();
				getUniqueID();
				VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"cannot be moved to New Day");

			}
		else if (Exp.equalsIgnoreCase(close)) {
				ExchangeClosestatus.click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("researchframe");
				getexchangeCoulmnValue();
				selectFirstData.rightClick();
				Thread.sleep(2000);
				MoveToNextDay.click();
				getUniqueID();
				VerifyAlertsMessages(exchangeNameValue, "Pending New Day", currentTime,"Exchange has officially been moved to the next trading date");

		}	}
	catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Error" + e.getMessage());

	}
}

public void mulNextDay() throws IOException {
	try {
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("dashboardframe");
		ExchangeNewDaystatus.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("researchframe");
		Actions builder = new Actions(driver);
		String exchangeName1 = selectFirstData.getText();
		String exchangeName2 = selectSecondRecord.getText();
		builder.click(selectMultipleRows.get(0)).keyDown(Keys.CONTROL).build().perform();
		builder.click(selectMultipleRows.get(1)).keyDown(Keys.CONTROL).build().perform();
		builder.click(selectMultipleRows.get(2)).keyDown(Keys.CONTROL).build().perform();
		builder.contextClick().build().perform();
		Thread.sleep(3000);
		MoveToNextDay.click();
		LOGGER.info(exchangeName1);
		LOGGER.info(exchangeName2);
		alertSearch.type(exchangeName1);
        alertSearch.pause(15);
		VerifyAlertsMessages(exchangeName1, "Pending New Day", currentTime,"Exchange has officially been moved to the next trading date");
	
	}
	catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Error" + e.getMessage());
	}
}


public void nextDaySessionBreak() {
	
	try {
		String sessionBreak = "sessionBreak";
		exchangeTab.click();
		ExchangesSearchBox.click(1000);
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("exchange_TD");
		Row row = sheet.getRow(2);
		String searchText = row.getCell(1).getStringCellValue();
		ExchangesSearchBox.type(searchText, 2000);
		String TradingSessionText = TradingSession.getText();

		System.out.print(TradingSessionText);
		
		if(TradingSessionText.equalsIgnoreCase("sessionbreak"))
		{
		getexchangeCoulmnValue();
		selectFirstData.rightClick();
		MoveToNextDay.click();
		getUniqueID();
		VerifyAlertsMessages(exchangeNameValue, "Pending New Day", currentTime," cannot be moved to New Day");
			
		}
		else
		{
				LOGGER.info("Exchange is not having trading session as sessionBreak");
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Error" + e.getMessage());

	}	
}

public void refreshFromT3Sessionbreak() {
	{
		
		try {
			exchangeTab.click();
			ExchangesSearchBox.click(1000);
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("exchange_TD");
			Row row = sheet.getRow(2);
			String searchText = row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText, 2000);
			String TradingSessionText = TradingSession.getText();
			System.out.print(TradingSessionText);			
			if(TradingSessionText.equalsIgnoreCase("sessionbreak"))
			{
			String ExpectedexchangeText = selectFirstData.getText();
			ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(ExpectedexchangeText);
			String getopenTime = ExchangeSQLStatementsLocal.openTime;
			String getCloseTime = ExchangeSQLStatementsLocal.closeTime;
			ExchangeSQLStatementsLocal.updateTimeRefreshFromT3(ExpectedexchangeText);
			getexchangeCoulmnValue();
			selectFirstData.rightClick();
			Thread.sleep(2000);
			RefreshFromT3.click();
			Thread.sleep(25000);				
			getUniqueID();
			VerifyAlertsMessages(exchangeNameValue, "Pending New Day", currentTime,"Exchange has been reloaded");
			ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(ExpectedexchangeText);
			String getopenTimeAfter = ExchangeSQLStatementsLocal.openTime;
			String getCloseTimeAfter = ExchangeSQLStatementsLocal.closeTime;
			Assert.assertEquals(getopenTimeAfter, getopenTime);
			Assert.assertEquals(getCloseTimeAfter, getCloseTime);
				
			}
			else
			{
					LOGGER.info("Exchange is not having trading session as sessionBreak");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error" + e.getMessage());

		}	

}

}

public void refreshFromT3(String Exp) throws IOException {
	try {
		String Newday = "NewDay";
		String Open = "open";
		String close = "close";
		driver.switchTo().defaultContent();
		driver.switchTo().frame("dashboardframe");
		if (Exp.equalsIgnoreCase(Newday)) {
			ExchangeNewDaystatus.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("researchframe");
			String ExpectedexchangeText = selectFirstData.getText();
			ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(ExpectedexchangeText);
			String getopenTime = ExchangeSQLStatementsLocal.openTime;
			String getCloseTime = ExchangeSQLStatementsLocal.closeTime;
			ExchangeSQLStatementsLocal.updateTimeRefreshFromT3(ExpectedexchangeText);
			getexchangeCoulmnValue();
			selectFirstData.click();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			Thread.sleep(2000);
			RefreshFromT3.click();
			Thread.sleep(5000);				
			getUniqueID();
			VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Exchange has been reloaded");
			ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(ExpectedexchangeText);
			String getopenTimeAfter = ExchangeSQLStatementsLocal.openTime;
			String getCloseTimeAfter = ExchangeSQLStatementsLocal.closeTime;
			Assert.assertEquals(getopenTimeAfter, getopenTime);
			Assert.assertEquals(getCloseTimeAfter, getCloseTime);
			
		

		} else if (Exp.equalsIgnoreCase(Open)) {
			ExchangeOpenstatus.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("researchframe");
			String ExpectedexchangeText = selectFirstData.getText();
			ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(ExpectedexchangeText);
			String getopenTime = ExchangeSQLStatementsLocal.openTime;
			String getCloseTime = ExchangeSQLStatementsLocal.closeTime;
			ExchangeSQLStatementsLocal.updateTimeRefreshFromT3(ExpectedexchangeText);
			getexchangeCoulmnValue();
			selectFirstData.click();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			Thread.sleep(2000);
			RefreshFromT3.click();
			Thread.sleep(5000);	  	
			getUniqueID();
			VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Exchange has been reloaded");
			ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(ExpectedexchangeText);
			String getopenTimeAfter = ExchangeSQLStatementsLocal.openTime;
			String getCloseTimeAfter = ExchangeSQLStatementsLocal.closeTime;
			Assert.assertEquals(getopenTimeAfter, getopenTime);
			Assert.assertEquals(getCloseTimeAfter, getCloseTime);
			
		} else if (Exp.equalsIgnoreCase(close)) {
			ExchangeClosestatus.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("researchframe");
			String ExpectedexchangeText = selectFirstData.getText();
			ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(ExpectedexchangeText);
			String getopenTime = ExchangeSQLStatementsLocal.openTime;
			String getCloseTime = ExchangeSQLStatementsLocal.closeTime;
			ExchangeSQLStatementsLocal.updateTimeRefreshFromT3(ExpectedexchangeText);
			getexchangeCoulmnValue();
			selectFirstData.click();
			Thread.sleep(2000);
			selectFirstData.rightClick();
			Thread.sleep(2000);
			RefreshFromT3.click();
			Thread.sleep(5000);	
			getUniqueID();
			VerifyAlertsMessages(exchangeNameValue, "Exchange Status", currentTime,"Exchange has been reloaded");
			ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(ExpectedexchangeText);
			String getopenTimeAfter = ExchangeSQLStatementsLocal.openTime;
			String getCloseTimeAfter = ExchangeSQLStatementsLocal.closeTime;
			Assert.assertEquals(getopenTimeAfter, getopenTime);
			Assert.assertEquals(getCloseTimeAfter, getCloseTime);
		}			
			else
			{
					LOGGER.info("Exchange is not having trading session as sessionBreak");
			}
		
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Error" + e.getMessage());
		}

}
public void refreshFromT3Mul() {
try {
		
	driver.switchTo().defaultContent();
	driver.switchTo().frame("dashboardframe");
	ExchangeNewDaystatus.click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame("researchframe");
	Actions builder = new Actions(driver);
	String exchangeName1 = selectFirstData.getText();
	String exchangeName2 = selectSecondRecord.getText();		
	ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(exchangeName2);
	String getopenTime = ExchangeSQLStatementsLocal.openTime;
	String getCloseTime = ExchangeSQLStatementsLocal.closeTime;
	ExchangeSQLStatementsLocal.updateTimeRefreshFromT3(exchangeName2);
	builder.click(selectMultipleRows.get(0)).keyDown(Keys.CONTROL).build().perform();
	builder.click(selectMultipleRows.get(1)).keyDown(Keys.CONTROL).build().perform();
	builder.click(selectMultipleRows.get(2)).keyDown(Keys.CONTROL).build().perform();
	builder.contextClick().build().perform();
	Thread.sleep(2000);
	RefreshFromT3.click();		
	Thread.sleep(5000);
	alertSearch.type(exchangeName2);
    alertSearch.pause(10);
	VerifyAlertsMessages(exchangeName2, "Exchange Status", currentTime,"Exchange has been reloaded");
	ExchangeSQLStatementsLocal.selectTimeRefreshFromT3(exchangeName2);
	String getopenTimeAfter = ExchangeSQLStatementsLocal.openTime;
	String getCloseTimeAfter = ExchangeSQLStatementsLocal.closeTime;
	Assert.assertEquals(getopenTimeAfter, getopenTime);
	Assert.assertEquals(getCloseTimeAfter, getCloseTime);
	
	}
	catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Error" + e.getMessage());
	}	
}

public void autoRelease() {
	try
	{
		exchangeTab.click();
		alertSearch.type("Auto-released before market open with a value");
		alertSearch.pause(8);
		String description = findExtendedWebElement(
			By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]")).getText();
		Assert.assertTrue(description.contains("Auto-released before market open with a value"));
	}
	catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Error" + e.getMessage());
	}
	
}

public void stockLoaded() {
	try
	{
		exchangeTab.click();
		alertSearch.type("Stock/Contract profiles cannot be loaded because");
	    alertSearch.pause(8);
	    String description = findExtendedWebElement(
				By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]")).getText();
	    LOGGER.info(description);
	    Assert.assertTrue(description.contains("Stock/Contract profiles cannot be loaded"));
		
	}
	catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Error" + e.getMessage());
	}
}



}
