package com.grip.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

import com.grip.DBconnection.DatawatchSQLStatements;
import com.grip.utils.ExcelUtils;
import com.amazonaws.services.s3.transfer.Copy;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

import org.testng.Assert;

public class Researchpage extends AbstractPage

{
	Logger LOGGER = Logger.getLogger(Dashboardpage.class);

	public Researchpage(WebDriver driver)

	{
		super(driver);
		PageFactory.initElements(getDriver(), this);

	}
	
	public static ExcelUtils excel = new ExcelUtils();
	
	@FindBy(xpath="//input[@aria-controls='alertTable']")
	public static ExtendedWebElement Alert_Search;
	
	@FindBy(xpath="//div[@class='closeImg window_icon_button no-draggable']")
	private ExtendedWebElement close_button;

	@FindBy(id="ui-id-3")
	public ExtendedWebElement dashboardTab;

	@FindBy(xpath="//table[@class='display dataTable']//tr//td[13]")
	private ExtendedWebElement held_Price_Value; 
	
	@FindBy(xpath="//input[@aria-controls='indexTable1']")
	public ExtendedWebElement indexInput;
	
	@FindBy(xpath="/html/body/ul/li[9]/span")
	public ExtendedWebElement refreshT3;
	
	@FindBy(xpath="//span[contains(text(),'Ok')]")
	public ExtendedWebElement Ok;
	
	@FindBy(xpath="//input[@id='stockHoldTypeAutoId']")
	private ExtendedWebElement radio_btn1;

	@FindBy(xpath="//div[starts-with(@id, 'bottom')]//table//tbody//tr[5]//td[3]//span")
	private ExtendedWebElement manual_hold_value_initial;

	@FindBy(xpath="(//div[@id='releaseStockDialog']/following-sibling::div/div/button/span)[last()-1]")
	private ExtendedWebElement releaseStockOk;

	@FindBy(xpath="(//div[@id='holdStockDialog']/following-sibling::div/div/button/span)[last()-1]")
	private ExtendedWebElement holdStockOk;

	@FindBy(xpath="//table[@class='display dataTable']//tr//td[3]")
	private ExtendedWebElement hold_status_button;

	@FindBy(id="releaseStockDialog")
	private ExtendedWebElement release_box1;

	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[2]")
	private ExtendedWebElement release;

	@FindBy(id="ui-id-27")
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
	public  ExtendedWebElement researchTab;

	@FindBy(xpath = "//table[@class='exchangeTableDisplay dataTable']//tbody//tr//td[1]")
	private ExtendedWebElement Exchange_data;

	@FindBy(xpath = "//table[@id='stockTable1']//tbody//tr//td[7]")
	public ExtendedWebElement Stock_Data;
	

	@FindBy(xpath = "//table[@id='indexTable2']//tbody//tr[1]")
	public ExtendedWebElement Related_Indices_Data;
	
	@FindBy(xpath="//table[@id='exchangeTable2']//tbody//tr[1]")
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
	
	
	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[4]//span")
	public ExtendedWebElement Related_Indices;
	

	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[5]//span")
	public ExtendedWebElement Exchanges;

	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[1]//span")
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
	
	@FindBy(xpath="//table[@id='icdTable3']//tbody//tr//td[7]")
	public ExtendedWebElement Equi_data;
	
	@FindBy(xpath="//table[@id='stockTable3']//tbody//tr//td[7]")
	public ExtendedWebElement Stock_data;
	
	
	@FindBy(xpath="//*[@id='window_1']//div[1]//div[1]")
	public ExtendedWebElement Related_Indices_Window;
	
	
	
	@FindBy(xpath="/table[@id='exchangeTable2']//tbody//tr//td[1]")
	public ExtendedWebElement Exchange_Data;
		
	

	/*** Exchange locators***/	

	

	  @FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[11]")
	  private ExtendedWebElement copyID;
	  @FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[12]")
	  private ExtendedWebElement selectRows;
	  
	@FindBy(xpath="/html/body/div[7]/div[2]/table/tbody/tr/td[1]/textarea")
	private ExtendedWebElement exchangeName;
	
	@FindBy(xpath="/html/body/div[8]/div[2]/table/tbody/tr/td[1]/textarea")
	private ExtendedWebElement exchangeId;
	@FindBy(xpath="/html/body/div[8]/div[3]/div/button[1]/span")
	private ExtendedWebElement copyToClipboard;
	@FindBy(xpath="//div[@class='statusBar1 statusBar ui-widget-header']//span")
	private ExtendedWebElement totalCount;
	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[2]")
	private ExtendedWebElement showcontracts;
	
	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[3]")
	private ExtendedWebElement showf11;
	
	@FindBy(xpath="//div[@class='window_header window_header_normal ui-widget-header ui-corner-top no-resizable  researchWindowheader']")
	private ExtendedWebElement listTitle;
	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[4]")
	private ExtendedWebElement showIndices;

	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[10]")
	private ExtendedWebElement copyName;
	
	@FindBy(xpath="//*[@id=\"window_0\"]/div[2]/div[1]/div/div/input")
	private ExtendedWebElement downloadcsv;
	
	@FindBy(xpath="//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1]")
	private List<WebElement> selectMultipleRows;
	
	@FindBy(xpath="//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1]")
	private List<WebElement> selectSingleRow;
	
	
	@FindBy(xpath="//span[@class='statusBarSpan' and @id='statusBarSpan2']")
	private ExtendedWebElement total_record_second; 
	
	
	@FindBy(xpath="//span[text()='Move To Next Day']")
	private ExtendedWebElement MoveToNextDay; 
	
	@FindBy(xpath="//*[starts-with(@id,'statusBarSpan')]")
	private ExtendedWebElement TotalExchangeRow; 
	
	@FindBy(xpath="//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[4]")
	private ExtendedWebElement TradingSession;
	
	@FindBy(xpath="//div[@class='ui-widget-header-mod ui-corner-top']//td[7]//span")
	private ExtendedWebElement SelectExchange;
	
	@FindBy(xpath="//*[starts-with(@id,'exchangeTable')]//input")
	private ExtendedWebElement ExchangesSearchBox;
	
	@FindBy(xpath="//span[text()=' Total: 4 (Selected: 0)']")
	private ExtendedWebElement Exchangestotal;
	
	@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[1]")
	private ExtendedWebElement showStock;
	
	
	@FindBy(xpath=" //span[@data-bind='text: eNewDay']")
	private ExtendedWebElement ExchangeNewDaystatus;
	
	@FindBy(xpath=" //span[@data-bind='text: eOpen']")
	private ExtendedWebElement ExchangeOpenstatus;
	
	@FindBy(xpath="//span[@data-bind='text: eClose']")
	private ExtendedWebElement ExchangeClosestatus;
	
	@FindBy(xpath="//div[@id=\"rightspannedid\"]/table/tbody/tr[3]/td[3]/span")
	private ExtendedWebElement ExchangeTotalNumber;
	
	
	
	
	
	@FindBy(xpath="//span[text()='Refresh from GRIP']")
	private ExtendedWebElement RefreshFromGRIP;
	
	@FindBy(xpath="//span[text()='Refresh From T3']")
	private ExtendedWebElement RefreshFromT3;
	
	
	
	@FindBy(xpath="//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1]")
	private ExtendedWebElement selectExchangeListRight;
	
	@FindBy(xpath="//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[6]")
	private ExtendedWebElement selectExchangeOpenTime;
	
	@FindBy(xpath="//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[7]")
	private ExtendedWebElement selectExchangeCloseTime;
	
	
	@FindBy(xpath="//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[9]")
	private ExtendedWebElement PrimaryFeedSource;
	
	@FindBy(xpath="//span[text()='Switch Feed Source to Reuters']")
	private ExtendedWebElement SwitchFeedReuters;
	
	@FindBy(xpath="//input[@name='switchFeedType']")
	private ExtendedWebElement SwitchFeedReutersRadio;
	
	@FindBy(xpath="/html/body/div[13]/div[11]/div/button[1]/span")
	private ExtendedWebElement OKSwitchToFeed;
	
	@FindBy(xpath="//div[@title='close window']")
	private ExtendedWebElement Exchangeclosebutton;
	
	@FindBy(xpath="//span[text()='Switch Feed Source to IDC']")
	private ExtendedWebElement SwitchFeedIDC;
	
	
	@FindBy(xpath="//span[text()='Switch to EOD/RealTime Ticker']")
	private ExtendedWebElement SwitchRealTicker;
	
	@FindBy(xpath="//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr[9]")
	private ExtendedWebElement selectExchangeList;
	
	@FindBy(xpath="//input[@title='Download Stock Exchange Data']")
	private ExtendedWebElement exchangeDownload;

	

	/**
	 * This method Validates Grip HommPage.
	 * 
	 * @author Rangesh_TV
	 * @param xpath
	 *            - data type string, xpath of the element passed as input
	 * @exception Exception
	 * @return - no return
	 */
	public void validate__Research_Page() {
		ExchangePage exc = new ExchangePage(getDriver());
		exc.getTimeInDashboard();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		if (index_Family.isElementPresent()) {

			LOGGER.info("Research Page Loaded Successfully");
		}

		// driver.switchTo().defaultContent();

	}

	public void Select_Exchange(int Base_Percnt) throws Exception {

		select_Exchange.click();
		String Exchange_Name= excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC03011", "TestData1");
		search_Exchange.type(Exchange_Name);
		Exchange_data.click();
		Exchange_data.rightClick();
		editlimits.click();
		ExtendedWebElement Default = findExtendedWebElement(
				By.xpath("//tbody[@id='editAutoHoldDtlTblId']//tr[@id='threshold1']//td[" + Base_Percnt + "]//input"));
		String DF_Txt = Default.getAttribute("value");

		ExtendedWebElement BasleLine_Up = findExtendedWebElement(
				By.xpath("//tbody[@id='editAutoHoldDtlTblId']//tr[@id='threshold1']//td[" + Base_Percnt + "]//input"));
		BasleLine_Up.type("0.01");
		AutoLimit_OK.click();
		Thread.sleep(10000);
		Exchange_data.rightClick();
		Show_Stocks.click();
		Thread.sleep(10000);

		Select view = new Select(driver.findElement(By.id("stockViewsList")));
		view.selectByIndex(2);
		List<ExtendedWebElement> tableRowA = AutoHold.findExtendedWebElements(By.tagName("tr"));
		Thread.sleep(10000);

		ExtendedWebElement Stock_Name = findExtendedWebElement(By.xpath("//table[@id='stockTable2']//tbody[@role='alert']//tr[1]//td[7]"));
		Stock_Name.click();
		Stock_Name.rightClick();
		Copy_Id.click();
		ExtendedWebElement ID = findExtendedWebElement(
				By.xpath("//table[@id='copyIdDialogTableId']//tbody//tr//td[1]"));
		String Object_Id = ID.getText();

		ExtendedWebElement Copy_msg = findExtendedWebElement(
				By.xpath("//div[@aria-describedby='copyIdDialog']//div[@class='ui-dialog-buttonset']//button[1]"));
		Copy_msg.click();
		String Stock_NM = Stock_Name.getText();
	 

		HashSet<String> setapp = new HashSet<String>();
		for (int z = 1; z < tableRowA.size(); z++) {
			ExtendedWebElement alertOptions = findExtendedWebElement(
					By.xpath("//table[@id='stockTable2']//tbody[@role='alert']//tr[1]//td[3]"));
			String alerttype = alertOptions.getText();
			setapp.add(alerttype);
		}
		HashSet<String> a = setapp;
		Object[] arr = a.toArray();

		 
		for (int j = 0; j < arr.length; j++) {
			if (!arr[j].equals("Auto Hold")) {
				LOGGER.info("All stocks are not  in Auto Hold State");
			} else {
				LOGGER.info("All stocks are   in Auto Hold State");
			}
		}
		Close_B.click();
		Exchange_data.click();
		Exchange_data.rightClick();
		editlimits.click();
		BasleLine_Up.type(DF_Txt);
		AutoLimit_OK.click();
		driver.switchTo().defaultContent();

		String CUR_TM = Current_Time.getText();
	 

		String CR_TM = CUR_TM.substring(CUR_TM.indexOf(",") + 2, CUR_TM.indexOf("U") - 1);
		Thread.sleep(15000);

		ExtendedWebElement dashboard = findExtendedWebElement(By.xpath("//a[@id='ui-id-3']"));
		dashboard.click();
		try {
			String Query1 = "SELECT * FROM (\r\n" + "\r\n" + "        SELECT * FROM rt_t_alert WHERE object_id='"
					+ Object_Id + "' AND MESSAGE LIKE '%Auto-release%' ORDER BY 2 DESC) \r\n"
					+ "        WHERE ROWNUM=1\r\n" + "        AND TIME >to_timestamp(' " + CR_TM
					+ " ', 'DD/MM/YYYY HH24:MI:SS') ";

		 
			String DB_desc = DatawatchSQLStatements.sql_connect(Query1, 3);
		 
			String Subject = DatawatchSQLStatements.sql_connect(Query1, 8);
			String Message = DatawatchSQLStatements.sql_connect(Query1, 9);
			 
			if (Message.contains("Auto-released")) {
				LOGGER.info("Stock " + Subject + " " + Message + "");
			} else {
				LOGGER.info("Stock " + Subject + " Not Released");

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void hold_Stock() throws IOException {

		select_Stock.click();
		search_Stock.click();

		search_Stock.type("342978", 2000);

		String a = feed_Price_Value.getText();
		 
		stock_Right_Click.click();
		// String a = feed_Price_Value.getText();
		stock_Right_Click.rightClick();
		// search_Stock.type((int)row1.getCell(1).getNumericCellValue());

		hold.click();
		driver.switchTo().alert();

		String b = feed_Price_alert_value.getText();
	 

		Assert.assertEquals(b, a, "value are equal");

	}

	public void Search_stock() throws IOException {
		Stocks.click();

	}

	public String Testdata() throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/Utils/TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		Row row1 = sheet.getRow(4);
		String Stock_Nm = row1.getCell(1).getStringCellValue();
		Search_stocks.type(Stock_Nm);
		return Stock_Nm;

	}

	public void Copy_ID() throws InterruptedException, IOException {

		try {
			Testdata();
			Stock_Data.click();
			Stock_Data.rightClick();
			// String Stock_Name =Stock_Data.getText();
			Copy_ID.click();
			String Actual_ID = CP_TXT.getText();
			ExtendedWebElement Copy_msg = findExtendedWebElement(
					By.xpath("//div[@aria-describedby='copyIdDialog']//div[@class='ui-dialog-buttonset']//button[1]"));
			Copy_msg.click();
			Search_stocks.click();
			driver.findElement(By.xpath("//div[@id='stockTable1_filter']//input"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@id='stockTable1_filter']//input"))
					.sendKeys(Keys.chord(Keys.CONTROL, "V"));
			Assert.assertTrue(Actual_ID.equalsIgnoreCase(Search_stocks.getAttribute("value")));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("ID   Not copied to ClipBoard" + e.getMessage());
		}
	}

	public void Report() throws InterruptedException, IOException {
		Search_stocks.click();
		driver.findElement(By.xpath("//div[@id='stockTable1_filter']//input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		String Name = Testdata();

		// Thread.sleep(5000);
		Stock_Data.isElementPresent();
		Stock_Data.click();
		Stock_Data.rightClick();
		Report.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("reportsframe");
		ExtendedWebElement Index_value = findExtendedWebElement(
				By.xpath("//div[@class='topleftcell']//tbody//tr[2]//td[3]//input"));
		String Asset_vl = Index_value.getAttribute("value");

		if (Asset_vl.contains(Name)) {
			LOGGER.info("Report Tab opened With Selected Stocks");

		}

		else

		{
			LOGGER.error("Report Tab opened With incorrect Stocks");

		}

	}

	public void Copy_name() {

		try {

			Search_stocks.click();
			driver.findElement(By.xpath("//div[@id='stockTable1_filter']//input"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			String Name = Testdata();

			Stock_Data.isElementPresent();
			Stock_Data.click();
			String A_Name = Stock_Data.getText();
		 
			Stock_Data.rightClick();
			Copy_Name.click();
			CC.click();
			Search_stocks.click();
			driver.findElement(By.xpath("//div[@id='stockTable1_filter']//input"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a"));

			driver.findElement(By.xpath("//div[@id='stockTable1_filter']//input"))
					.sendKeys(Keys.chord(Keys.CONTROL, "V"));

			Assert.assertTrue(A_Name.equalsIgnoreCase(Search_stocks.getAttribute("value")));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("ID   Not copied to ClipBoard" + e.getMessage());
		}
	}

	public void Search_Stock_Comma() {
		try {
			String A_RIC = RIC.getText();
			String A_IDC_Ticker = IDC_Ticker.getText();
			String A_Name = Name.getText();
			String tSearch = (A_RIC + "," + A_IDC_Ticker + "," + A_Name);
			Search_stocks.type(tSearch);
			Thread.sleep(20000);
			RIC.isElementPresent();
			List<ExtendedWebElement> tableRowA = Stock_Table.findExtendedWebElements(By.tagName("tr"));

			for (int i = 1; i <= tableRowA.size(); i++) {
				ExtendedWebElement R_RIC = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[5]"));
				ExtendedWebElement R_IDC_Ticker = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[6]"));
				ExtendedWebElement R_Name = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[7]"));
				String Rt_RIC = R_RIC.getText();
				String Rt_IDC_Ticker = R_IDC_Ticker.getText();
				String Rt_Name = R_Name.getText();
				Assert.assertTrue(A_RIC.equalsIgnoreCase(Rt_RIC) || A_IDC_Ticker.equalsIgnoreCase(Rt_IDC_Ticker)|| A_Name.equalsIgnoreCase(Rt_Name));

				LOGGER.info("Result retrieved is matching with search criteria");

			}
		}

		catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Result retrieved is not matching the search criteria" + e.getMessage());
		}

	}

	public void Verify_filter() {

		try {

			Select view = new Select(driver.findElement(By.id("stockViewsList")));
			view.selectByIndex(0);
			int j = 0, k = 0;

			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
			XSSFRow row = sheet.getRow(1);
				//JavascriptExecutor js = (JavascriptExecutor)getDriver();
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			String quickViewNameDetails = row.getCell(5).getStringCellValue();
			String[] quickViewListDetails = quickViewNameDetails.split(",");
			ArrayList<String> list = new ArrayList<>();
			
			 

			for (int i = 1; i <= quickViewList.size(); i++) {
			WebElement until = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='display dataTable']//tr[1]//td[" +i + "]")));
				
				
			js.executeScript("arguments[0].scrollIntoView(true);", until);
			System.out.println( until.getText());
			
				
			}
			
			for (int i = 0; i < quickViewListDetails.length; i++) {
				Assert.assertTrue(list.contains(quickViewListDetails[i]));
			}

			workbook.close();

			 
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Result retrieved is not matching the search criteria-" + e.getMessage());
		}
	}
	
	public void Show_Indices()
	{
		

		try {
			Testdata();
			Stock_Data.click();
			Stock_Data.rightClick();
			Related_Indices.click();
			String Window=Related_Indices_Window.getText();
			String Stock_Nm = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC0335", "TestData1");
			Assert.assertTrue(Window.contains(Stock_Nm));
			LOGGER.info("Related Indices for Stock Matches ");
			Related_Indices_Data.rightClick();
			Show_constituents.click();
			
			Search_Equi.type(Stock_Nm);
			String Eq_Dta=Equi_data.getText();
			Assert.assertTrue(Stock_Nm.equalsIgnoreCase(Eq_Dta));
			LOGGER.info("Related Indices for Stock Matches in Constituents");
		 
		}
		
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(" Incorrect Indices for Stock" + e.getMessage());
		}
	}
	
	public void Show_Exchanges()
	{
	

	try {
		Testdata();
		Stock_Data.click();
		Stock_Data.rightClick();
		Exchanges.click();
		String Window=Related_Indices_Window.getText();
		String Stock_Nm = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC0335", "TestData1");
		Assert.assertTrue(Window.contains(Stock_Nm));
		Related_Exchange_Data.rightClick();
		Show_constituents.click();
		Search_Stocks.type(Stock_Nm);
		String Stock_Dta=Stock_data.getText();
		Assert.assertTrue(Stock_Nm.equalsIgnoreCase(Stock_Dta));
		LOGGER.info("Related Exchanges for Stock Matches in Exchange List Window ");
	}
	
	catch (Exception e) {
		e.printStackTrace();
		Assert.fail(" Incorrect Indices for Stock" + e.getMessage());
	}

	
	}
	
	public void Show_Stocks() throws Exception
	{
		 
		
		String Exchange_Name=excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC03034", "TestData1)");
		search_Exchange.type(Exchange_Name);
		Exchange_data.click();
		Exchange_data.rightClick();
		Show_Stocks.click();
		
		 }
	
	public void Hold_Stock()
	{
		for(int i=1;i<=5;i++)
		{
			
			 ExtendedWebElement Data_feed=findExtendedWebElement(By.xpath("//table[@id='stockTable2']//tbody//tr["+i+"]//td[4]"));
			 String Data_Feed_T=Data_feed.getText();
			 ExtendedWebElement Feed_Price =findExtendedWebElement(By.xpath("//table[@id='stockTable2']//tbody//tr["+i+"]//td[8]"));
			 String Feed_Price_T =Feed_Price.getText();
			 Double feed_price =Double.parseDouble(Feed_Price_T);
			 
			 WebElement wd = driver.findElement(By.xpath("//table[@id='stockTable2']//tbody//tr["+i+"]//td[4]"));
			 Actions action = new Actions(driver);
			 if(Data_Feed_T.equalsIgnoreCase("Subscribed")&&feed_price>0)
			 {
				action.keyDown(Keys.LEFT_CONTROL).click(wd).build().perform();
				
				 
				 
			 }
			 if(i==5)
			 {
				 ExtendedWebElement Data_R =findExtendedWebElement(By.xpath("//table[@id='stockTable2']//tbody//tr[5]//td[8]"));
				 Data_R.rightClick();
				 hold.click();
				 Data_R.rightClick();
				 Release.click();
				 
				 
			 }
			
		}
		
	
	}
	
	public void Manual_Hold_Stock() throws Exception
	{
		select_Stock.click();
		search_Stock.click(2000);
	 
		
		String Search=excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC03002", "TestData1");
		search_Stock.type(Search);
		String a =  feed_Price_Value.getText();
		 
		stock_Right_Click.click();
		stock_Right_Click.rightClick(1000);
		hold.click();
		String b = feed_Price_alert_value.getAttribute("value");
	 
		if(a.equals(b))
		{
		LOGGER.info("Hold Stock box  display the current feed price ");
		}
		else
		{	
		LOGGER.error("Hold Stock box not display the current feed price");
	    }
		String btn =radio_btn.getAttribute("checked");
	    if (btn.equalsIgnoreCase("true"))
		{
		LOGGER.info("Manual  Release at analyst discretion Radio button is checked");
		}
		else
		{
		LOGGER.error("Manual  Release at analyst discretion Radio button is not checked");
		}

	}
		     
	public void manual_hold() throws IOException

	{ 
		try
		{
			driver.switchTo().defaultContent();	
			dashboardTab.click();
			driver.switchTo().frame("dashboardframe");
			String initialCount= manual_hold_value_initial.getText();
			int parseInt1 = Integer.parseInt(initialCount);
		 
			driver.switchTo().defaultContent();
			researchTab.click();
			driver.switchTo().frame("researchframe");
			select_Stock.click();
			search_Stock.click(2000);
	    	 
			
			String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC03002", "TestData1");
			 
			search_Stock.type(Search);
			String a =  feed_Price_Value.getText();
			 
			stock_Right_Click.click();
			stock_Right_Click.rightClick(1000);
			hold.click();
			String b = feed_Price_alert_value.getAttribute("value");
			 
	        if(a.equals(b))
			{
			LOGGER.info("Hold Stock box  display the current feed price ");
			}
			else
			{	
			LOGGER.error("Hold Stock box not display the current feed price");

			}
			String btn =radio_btn.getAttribute("checked");

			if (btn.equalsIgnoreCase("true"))
			{
			LOGGER.info("Manual  Release at analyst discretion Radio button is checked");
			}
			else
			{
			LOGGER.error("Manual  Release at analyst discretion Radio button is not checked");
			}
	        holdStockOk.click();
			close_button.click(1000);
		//	driver.navigate().refresh();
			select_Stock.click(3000);
			search_Stock.click(3000);
			search_Stock.type(Search);
			String c =  hold_status_button.getText();
			
			if(c.equalsIgnoreCase("Manual Hold"))
			{
			LOGGER.info("The holding status is manual hold");

			}
			else
			{
			LOGGER.error("The holding status is not manual hold");
			}
			driver.switchTo().defaultContent();	
			driver.navigate().refresh();
			dashboardTab.click(1000);
			driver.switchTo().frame("dashboardframe");
			String final_count= manual_hold_value_initial.getText();
			int parseInt2=Integer.parseInt(final_count);
			if(parseInt1==(--parseInt2))
			{
			LOGGER.info("Manual hold count increased by 1");
			}
			else
			{
			LOGGER.info("Manual hold count not increased");
			}

			}
			catch(Exception e)
			{
			e.printStackTrace();
			System.out.println(e.getMessage());
			}
		}

	public void Release() throws IOException
	{
		
 
			try
			{
		     select_Stock.click();
		     search_Stock.isElementPresent();
		    search_Stock.click();
		 
			
			String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC03002", "TestData1");
			
		 
			search_Stock.type(Search);
			String a =  feed_Price_Value.getText();
		 
			stock_Right_Click.click();
	        stock_Right_Click.rightClick(1000);
	        release.click();
	        String c= release_box.getText();
	     
	        String d= release_box1.getText();
	        
	      
	        if(c.contains("Release"))
	        {
	        LOGGER.info("Release stock box is appeared");
	        }
	        else
	        {
	        	LOGGER.error("Release stock box is not appeared");
	        }
	        if(d.contains(a))
	        {
	        LOGGER.info("the current feed price is reset to baseline feed price value");
	        }
	        else
	        {
	        	  LOGGER.error("the current feed price is not  reset to baseline feed price value");
	        }
	        
	        close_button.click(2000);
	        select_Stock.click();
		    search_Stock.isElementPresent();
		    search_Stock.click();
		    search_Stock.type(Search);
	      String h=  held_Price_Value.getText();
	      Assert.assertTrue(h.isEmpty());
	      
			}
	       
			
			catch(Exception e)
			{
				
				e.printStackTrace();
				Assert.fail("held price is NOT cleared"+e.getMessage());
				LOGGER.info("held price is not cleared");
				
				
			}
	        
	        
	        
	        
	}
	public void hold_Stock_Auto() throws Exception

	{
		select_Stock.click();
		search_Stock.click(2000);
		 
		String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC03002", "TestData1");
	 
		search_Stock.type(Search);
		String a =  held_Price_Value.getText();
		if(a.isEmpty())
		{
		LOGGER.info("held price is blank");
		}
		else
		{
		LOGGER.info("the held price is not blank");
		}
	 
		stock_Right_Click.click();
		stock_Right_Click.rightClick(1000);
		hold.click();
		String b = feed_Price_alert_value.getAttribute("value");
	 
		radio_btn1.click();
		holdStockOk.click(1000);
	    close_button.click(1000);
		select_Stock.click(1000);
		search_Stock.click(2000);
		search_Stock.type(Search, 3000);
		String e =  hold_status_button.getText();
	 
		String d = "Auto Hold";
		String g =  held_Price_Value.getText();
		if(e.equalsIgnoreCase(d))
		{
		LOGGER.info("The holding status is Auto hold");
		}
		else
		{
		LOGGER.error("The holding status is not Auto hold");
		}

		if(b.equalsIgnoreCase(g))
		{
		LOGGER.info("The stock is held at valid feed price");

		}
		else
		{
		LOGGER.error("The Stock is not held at valid feed price");
		}
	}

	public void held_at_negative() throws Exception
	{
	    select_Stock.click();
	    search_Stock.click(2000);
	     
		
		String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC03002", "TestData1");
	 
		search_Stock.type(Search);
		stock_Right_Click.click();
	    stock_Right_Click.rightClick(1000);
		hold.click();
	    driver.findElement(By.xpath("//table[@id='holdStockTableId']//tr//input")).sendKeys(Keys.chord(Keys.CONTROL, "a")); 
	    driver.findElement(By.xpath("//table[@id='holdStockTableId']//tr//input")).sendKeys("-123"); 
	    String b = feed_Price_alert_value.getAttribute("value");
		 
		int parseInt=Integer.parseInt(b);
		if(parseInt>0)
		{
		LOGGER.info("System is not allowing negative value for holding stock");
		}
		else
		{
				
		LOGGER.info("System is  allowing negative value for holding stock");	
		}
	

}
	

	public void verifyExchangeRecord() throws IOException
	{
		try
		{
		
			SelectExchange.click();
			ExchangesSearchBox.click(1000);
			FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
			Row row =sheet.getRow(9);
			String searchText =row.getCell(1).getStringCellValue();
			ExchangesSearchBox.type(searchText,2000);

			String tserach_split[]=searchText.split(",");
			String a=tserach_split[0];
			String b =tserach_split[1];
			String c=tserach_split[2];
			String d=tserach_split[3];
			String tot = Exchangestotal.getText();
			
			System.out.println(tot);
			
			int length = tot.length();
			System.out.println(length);
			String ExchangeTotalno  = tot.substring(7,9);
	//		System.out.println(ExchangeTotalno);
			double parseDoublea = Double.parseDouble(ExchangeTotalno);
	//		int parseIntno = Integer.parseInt(ExchangeTotalno);
		
			for(int i=1;i<=parseDoublea;i++)

			{

				ExtendedWebElement A = findExtendedWebElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["+i+"]"));

				for(int j=1;j<=2;j++)
				{
					ExtendedWebElement ele = findExtendedWebElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["+i+"]//td["+j+"]"));	
					String e = ele.getText().toLowerCase();					
					if(e.contains(a)||e.contains(b)||e.contains(d))
					{
						LOGGER.info("the result is matched with searched testdata");
				
				
						for(int k=1;k<=2;k++)
						{
							ExtendedWebElement out = findExtendedWebElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["+i+"]//td["+k+"]"));
							String outtext = out.getText();
							LOGGER.info(outtext);
					
						}			
				
					}		
				}
			}
			
			for(int i=1;i<=parseDoublea;i++)

			{				
				
					ExtendedWebElement ele = findExtendedWebElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["+i+"]//td[11]"));	
					String e = ele.getText().toLowerCase();
					
					if(e.contains(c))
					{
						
						LOGGER.info("the result is matched with searched testdata - exchangeID(187)");
				
						for(int k=1;k<2;k++)
						{
							ExtendedWebElement out = findExtendedWebElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["+i+"]//td["+k+"]"));
							ExtendedWebElement outExchangeId = findExtendedWebElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr["+i+"]//td[11]"));
							String outtext = out.getText();
							String outExchangeID = outExchangeId.getText();
							LOGGER.info(outtext);
							LOGGER.info(outExchangeID);							
					
						}			
				
					}		
				}
			}

		
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
			
		}
		
			
	}			
	
public void showStocks() throws IOException
{
	try

	{
		SelectExchange.click();

		ExchangesSearchBox.click(1000);
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		Row row =sheet.getRow(10);
		String searchText =row.getCell(1).getStringCellValue();
		ExchangesSearchBox.type(searchText,5000);
		selectExchangeListRight.rightClick();
		showStock.click();
		String a= listTitle.getText();	
		if(a.contains(searchText))

		{
			LOGGER.info("Stock list window has been opened with records of stocks available in selected exchange");	
		}
		else
		{
			LOGGER.info("Error in identifying the exchange");	
		}
	
		String tot_stock_no_row=total_record_second.getText();	
		
		String query = "select COUNT(*) from RT_M_STK_PRFL where EXCH_SEGMENT_ID = 57 ";
		String DB_stock_value = DatawatchSQLStatements.sql_connect(query, 1);	
	
		Assert.assertTrue(tot_stock_no_row.contains(DB_stock_value));
		
		LOGGER.info("Successfully matched with DB");
	
	}	
	catch (Exception e)
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}
public void showContracts() throws IOException
{
try

{
	SelectExchange.click();
	ExchangesSearchBox.click(1000);
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
	Row row =sheet.getRow(11);
	String searchText =row.getCell(1).getStringCellValue();
	ExchangesSearchBox.type(searchText,5000);
	selectExchangeListRight.rightClick();
	showcontracts.click();
	String a= listTitle.getText();
	System.out.print(a);
	if(a.contains(searchText))

	{
		LOGGER.info("the contract list of given exchange is opened");	
	}
	else
	{
		LOGGER.info("the contract list of given exchange is not opened");	
	}


	String tot_contracts_no_row=total_record_second.getText();

	String query = "select Count(*) from RT_M_CONTR_PRFL where EXCH_SEGMENT_ID = 187";
		
	String DB_contracts_value_row = DatawatchSQLStatements.sql_connect(query, 1);

	Assert.assertTrue(tot_contracts_no_row.contains(DB_contracts_value_row));
	LOGGER.info("Successfully matched with DB");


}
catch(Exception e)
{
	e.printStackTrace();
	Assert.fail("error"+e.getMessage());

}

}

public void fii() throws IOException
{
try

{
	SelectExchange.click();
	ExchangesSearchBox.click(1000);
	
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
	Row row =sheet.getRow(12);
	String searchText =row.getCell(1).getStringCellValue();
	ExchangesSearchBox.type(searchText,5000);
	selectExchangeListRight.rightClick();
	showf11.click();
	String a= listTitle.getText();
	System.out.print(a);
	if(a.contains(searchText))

	{
	LOGGER.info("the fii list of given exchange is opened");	
	}
	else
	{
		LOGGER.info("the fii list of given exchange is not opened");	
	}


	String tot_contracts_no_row=total_record_second.getText();

	String query = "select count(*) from RT_M_FII_PRFL where EXCH_SEGMENT_ID = 186";
		
	String DB_contracts_value_row = DatawatchSQLStatements.sql_connect(query, 1);

	Assert.assertTrue(tot_contracts_no_row.contains(DB_contracts_value_row));
	LOGGER.info("Successfully matched with DB");

}
catch(Exception e)
{
	e.printStackTrace();
	Assert.fail("error"+e.getMessage());
}

}






public void showIndices() throws IOException
{
	try

{
	SelectExchange.click();
	ExchangesSearchBox.click(1000);
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
	Row row =sheet.getRow(10);
	String searchText =row.getCell(1).getStringCellValue();
	ExchangesSearchBox.type(searchText,5000);
	selectExchangeListRight.rightClick();
	showIndices.click();
	String a= listTitle.getText();
	if(a.contains(searchText))

	{
	LOGGER.info("the indices list of given exchange is opened");	
	}
	else
	{
		LOGGER.info("the indices list of given exchange is not opened");	
	}
	
	String tot_indices_no_row=total_record_second.getText();

	
	String query = "Select count(distinct index_id) from ui_research_exchange_data_MV a,\r\n" + 
			"Rt_m_STK_prfl b, rt_t_eqi_index_stk_comp c\r\n" + 
			"Where a.exch_segment_Id = b.exch_segment_Id\r\n" + 
			"And b.STK_Id = c.STK_Id\r\n" + 
			"And b.exch_segment_Id =57\r\n" + 
			" ";
	String DB_index_value_row = DatawatchSQLStatements.sql_connect(query, 1);

	
	Assert.assertTrue(tot_indices_no_row.contains(DB_index_value_row));
	LOGGER.info("Successfully matched with DB");
	
}
	catch (Exception e)
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}	
}

 public void copyName()
 {
	 try {
		 
	 
	 SelectExchange.click();
	 selectExchangeList.rightClick();
     copyName.click();
     driver.findElement(By.xpath("/html/body/div[7]/div[2]/table/tbody/tr/td[1]/textarea")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
     String b =  exchangeName.getText(); 
     copyToClipboard.click(1000);
     driver.findElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//input")).sendKeys(Keys.chord(Keys.CONTROL, "V"));
     Assert.assertTrue(b.equalsIgnoreCase(ExchangesSearchBox.getAttribute("value")));

 
 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 System.out.println(e.getMessage());
		 Assert.fail("ID   Not copied to ClipBoard" + e.getMessage());
 }}
 
 public void copyID()
 {
	 try {
		 
	 
	 SelectExchange.click();
	 selectExchangeList.rightClick();
     copyID.click();
     driver.findElement(By.xpath("/html/body/div[8]/div[2]/table/tbody/tr/td[1]/textarea")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
     String b =  exchangeId.getText(); 
     copyToClipboard.click(1000);
     driver.findElement(By.xpath("//*[starts-with(@id,'exchangeTable')]//input")).sendKeys(Keys.chord(Keys.CONTROL, "V"));
     Assert.assertTrue(b.equalsIgnoreCase(ExchangesSearchBox.getAttribute("value")));

 
 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 System.out.println(e.getMessage());
		 Assert.fail("ID   Not copied to ClipBoard" + e.getMessage());
 }}
 
 
 
 
 public void selectAllRows()
 {
	 try {
		 
	 
		 SelectExchange.click();
		 selectExchangeList.rightClick();
		 String total =  totalCount.getText();
		 String myArray[] = total.split("\\(");
		 for(int i=(myArray.length-1); i>=0; i--) 
		   System.out.println("myArray["+i+"] : "+ myArray[i]);
		 String a = myArray[1].toString();
		 String b = myArray[0].toString();	 
		 selectRows.click(3000);
		 Thread.sleep(7000);
		 String c = myArray[1].toString();
		 String e = totalCount.getText();
		 if(a.contains("0") && c.contains(b))
		 {
			 LOGGER.info("All rows are selected");
		 }
		 else
		 {
			 LOGGER.error("All rows are not selected");
		 }
	 	}
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 Assert.fail("Error"+e.getMessage());
	 }
} 
 
 
 public void oneNextDay(String Exp) throws IOException
 {
	 try
	 {
		 String Newday = "New Day";
		 String Open = "open";
		 String close = "close";
		 
		 
		 driver.switchTo().defaultContent();

		 driver.switchTo().frame("dashboardframe");
		 if(Exp.equalsIgnoreCase(Newday))
		 {
		 
		 ExchangeNewDaystatus.click(); 
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("researchframe");		
		 String TradingSessionText = TradingSession.getText();
		 String ExpectedexchangeText = selectExchangeListRight.getText();			 
	
		 selectExchangeListRight.rightClick();
		 MoveToNextDay.click();
		 
		 FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row1 =sheet.getRow(16);
		 String ExpectedexchangeTextVerify =row1.getCell(1).getStringCellValue();		 

		 Thread.sleep(21000);		 
		 
		 if(TradingSessionText.equalsIgnoreCase(Newday)) 
		 {	  
			 System.out.println("trading session checking done"); 	
			
		 	 for(int i=1;i<10;i++) 
		 	 {
		 		ExtendedWebElement a=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		String AlertDescription = a.getText();
		 		System.out.println(AlertDescription);   
		 		if(AlertDescription.contains(ExpectedexchangeTextVerify))
		 		{
		 			LOGGER.info("successfully matched with description- exchange name");
		 		}
		 		break;
		 	}   
		 
		 }	
		 }
		 else if(Exp.equalsIgnoreCase(Open))
		 {
		 
	     ExchangeOpenstatus.click(); 
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("researchframe");
		 
		 String TradingSessionText = TradingSession.getText();
		 String ExpectedexchangeText = selectExchangeListRight.getText();			 
	
		 selectExchangeListRight.rightClick();
		 MoveToNextDay.click();
		 
		 FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row1 =sheet.getRow(17);
		 String ExpectedexchangeTextVerify =row1.getCell(1).getStringCellValue();	 
		 
		 Thread.sleep(21000);
		 
		 
		 if(TradingSessionText.equalsIgnoreCase(Open))
		 {	  
			 System.out.println("trading session checking done"); 	
			
		 	 for(int i=1;i<10;i++) 
		 	 {
		 		ExtendedWebElement a=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		String AlertDescription = a.getText();
		 		System.out.println(AlertDescription);   
		 		if(AlertDescription.contains(ExpectedexchangeTextVerify))
		 		{
		 			LOGGER.info("successfully matched with description- exchange name");
		 		}
		 		break;
		 	}   
		 
		 }	
		 }
		 
		 else if(Exp.equalsIgnoreCase(close))
		 {
		 
		 ExchangeClosestatus.click();  
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("researchframe");
		 
		 String TradingSessionText = TradingSession.getText();
		 String ExpectedexchangeText = selectExchangeListRight.getText();			 

		 selectExchangeListRight.rightClick();
		 MoveToNextDay.click();
		 
		 FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row1 =sheet.getRow(16);
		 String ExpectedexchangeTextVerify =row1.getCell(1).getStringCellValue();		 
		 Thread.sleep(21000);		 
		 
		 if(TradingSessionText.equalsIgnoreCase(close))
		 {	  
			 System.out.println("trading session checking done"); 	
			
		 	 for(int i=1;i<10;i++) 
		 	 {
		 		ExtendedWebElement a=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		String AlertDescription = a.getText();
	  
		 		if(AlertDescription.contains(ExpectedexchangeTextVerify))
		 		{
		 			LOGGER.info("successfully matched with description- exchange name");
		 		}
		 		break;
		 	}   
		 
		 }	
		 }
	 }	    
	 
	 
	 catch (Exception e)
	 {
		 e.printStackTrace();
		 Assert.fail("Error"+e.getMessage());
		 
	 }
 }


 
 
 
 public void oneNextDay() throws IOException
 {
	 try
	 {
		 String sessionBreak = "sessionBreak";
		 SelectExchange.click();		 
		 ExchangesSearchBox.click(1000);
		 FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row =sheet.getRow(18);
		 String searchText =row.getCell(1).getStringCellValue();
		 ExchangesSearchBox.type(searchText,2000);
		 String TradingSessionText = TradingSession.getText();
		 String ExpectedexchangeText = selectExchangeListRight.getText();	 
		 
		 System.out.print(TradingSessionText);
		 selectExchangeListRight.rightClick();
		 MoveToNextDay.click();		 
		
		 Row row1 =sheet.getRow(17);
		 String ExpectedexchangeTextVerify =row1.getCell(1).getStringCellValue();
		 System.out.print(ExpectedexchangeTextVerify);		 
		 
		 Thread.sleep(21000);		 
		 
		 if(TradingSessionText.equalsIgnoreCase(sessionBreak))
		 {	  
			 System.out.println("trading session checking done"); 	
			
		 	 for(int i=1;i<10;i++) 
		 	 {
		 		ExtendedWebElement a=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		String AlertDescription = a.getText();
		 		System.out.println(AlertDescription);   
		 		if(AlertDescription.contains(ExpectedexchangeTextVerify))
		 		{
		 			LOGGER.info("successfully matched with description- exchange name");
		 		}
		 		break;
		 	}   
		 
		 }	
	 }
	 
	 catch (Exception e)
	 {
		 e.printStackTrace();
		 Assert.fail("Error"+e.getMessage());
		 
	 }
 }
 
 public void mulNextDay() throws IOException
 {
	 try
	 {
		 driver.switchTo().defaultContent();

		 driver.switchTo().frame("dashboardframe");
		 ExchangeNewDaystatus.click();			 
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("researchframe");			 
		 		 
		 
		 
		 ArrayList<String> exchangeName = new ArrayList<>();
			//int exchangeNameListsize = exchangeNameList.size();
		 for (int i = 1; i < 4; i++) 
		 {
				exchangeName.add(selectMultipleRows.get(i).getText());
				
		 }
		 		
		 
		 Actions builder = new Actions(driver);
	     builder.click(selectMultipleRows.get(1)).keyDown(Keys.CONTROL).build().perform();
	     builder.click(selectMultipleRows.get(3)).keyDown(Keys.CONTROL).build().perform();
	     builder.click(selectMultipleRows.get(4)).keyUp(Keys.CONTROL).build().perform();
	     builder.contextClick().build().perform();
	     Thread.sleep(3000);
	     MoveToNextDay.click();
	
	     
	     FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row =sheet.getRow(16);
		 String ExpectedexchangeText =row.getCell(1).getStringCellValue();
		 System.out.println(ExpectedexchangeText); 
	     for(int i=1;i<10;i++) 
		 	{
	    	 
		 		ExtendedWebElement d=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		String AlertDescription = d.getText();
	
		 		ExtendedWebElement e=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[4]"));
		 		String subjectName = e.getText(); 	
		 		
		 		if(AlertDescription.contains(ExpectedexchangeText))
		 		{
		 			
		 				LOGGER.info("The description has been verified in the application whether the exchange has been moved to new day or not"+exchangeName.get(1));			    	
		 		}	
		 			       
		 		
		 		
		 		
		 		}
	     
	 }
	     
	 
	 catch (Exception e)
	 {
		 e.printStackTrace();
		 Assert.fail("Error"+e.getMessage());
		 
	 }
 }
 
 
 public void refreshFromT3(String Exp) throws IOException
 {
	 try
	 {	 
		 String Newday = "NewDay";
		 String Open = "open";
		 String close = "close";
		 String sessionBreak = "sessionBreak";
		 
		 driver.switchTo().defaultContent();

		 driver.switchTo().frame("dashboardframe");
		 if(Exp.equalsIgnoreCase(Newday))
		 {
		 
		 ExchangeNewDaystatus.click(); 
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("researchframe");
		 String ExpectedexchangeText=selectExchangeListRight.getText();
		 String OpenTime = selectExchangeOpenTime.getText();
		 String CloseTime = selectExchangeCloseTime.getText();
		 selectExchangeListRight.rightClick();
		 RefreshFromT3.click();
		 Thread.sleep(25000);
		 FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row1 =sheet.getRow(13);
		 String ExpectedexchangeTextVerify =row1.getCell(1).getStringCellValue();
		 
		 for(int i=1;i<10;i++) 
		 	{
	 	 	
		 		ExtendedWebElement Description=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		ExtendedWebElement ExchangeAlert=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[4]"));
		 		String AlertDescription = Description.getText();
		 		String AlertExchangeName = ExchangeAlert.getText();	 		
		 		System.out.println(AlertExchangeName); 
		 		System.out.println(AlertDescription); 
		 		if(AlertDescription.contains(ExpectedexchangeTextVerify)||AlertExchangeName.contains(ExpectedexchangeText))
		 		{
		 			LOGGER.info("The description has been verified in the application whether the exchange has been reloaded");
		 		}
		 		break;
		 	}  
		 String Appopentime=selectExchangeOpenTime.getText(); 	 

		 String OpenDate  = Appopentime.substring(0,11);		
		 String OpenTim = Appopentime.substring(12,20);		 

		 String Appclosetime=selectExchangeCloseTime.getText();		 
		 String CloseDate  = Appclosetime.substring(0,11);
		 String CloseTim = Appclosetime.substring(12,20);		 
		 
		 String queryExchangeID = "select EXCH_SEGMENT_ID from RT_M_STK_EXCH_SEG where SEGMENT_NAME='"+ExpectedexchangeText+"'";
			
		 String ExchangeId = DatawatchSQLStatements.sql_connect(queryExchangeID, 1);
		 
		 int ExchangeIdNo = Integer.parseInt(ExchangeId);	
		 
		
		 String QueryOpenDate="select to_char(to_date(OPEN_TIME),'DD-MON-YYYY')from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;	 
		 String QueryCloseDate = "Select to_char(to_date(cLOSE_TIME),'DD-MON-YYYY')from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 String QueryOpenTime ="select to_char(OPEN_TIME,'hh24:mi:ss') from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 String QueryCloseTime ="select to_char(cLOSE_TIME,'hh24:mi:ss') from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 
		 String DBOpenDate = DatawatchSQLStatements.sql_connect(QueryOpenDate,1);
		 String DBCloseDate = DatawatchSQLStatements.sql_connect(QueryCloseDate,1);
		 String DBOpenTime = DatawatchSQLStatements.sql_connect(QueryOpenTime,1);
		 String DBCloseTime = DatawatchSQLStatements.sql_connect(QueryCloseTime,1);		 
	 
		 
		 if(OpenDate.equalsIgnoreCase(DBOpenDate)) 
			 
		 {

			 if(OpenTim.equalsIgnoreCase(DBOpenTime ))
			 {

				 if(CloseTim.equalsIgnoreCase( DBCloseTime) )
				 {

					 if(CloseDate.equalsIgnoreCase(DBCloseDate))
					 {
						 LOGGER.info("The open and close time of given exchange has been verified with database");
					 }			

				 }
					 
			 }
			 
		 }
		 
	 }
	 else if(Exp.equalsIgnoreCase(Open))
	 {
		 
		 ExchangeOpenstatus.click(); 
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("researchframe");

		 String OpenTime = selectExchangeOpenTime.getText();
		 String CloseTime = selectExchangeCloseTime.getText();
		 String ExpectedexchangeText = selectExchangeListRight.getText();
		 selectExchangeListRight.rightClick();
		 RefreshFromT3.click();
		 Thread.sleep(25000);
		 
		 FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row1 =sheet.getRow(13);
		 String ExpectedexchangeTextVerify =row1.getCell(1).getStringCellValue();
		 
		 for(int i=1;i<10;i++) 
		 	{
	 	 	
		 		ExtendedWebElement Description=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		ExtendedWebElement ExchangeAlert=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[4]"));
		 		String AlertDescription = Description.getText();
		 		String AlertExchangeName = ExchangeAlert.getText();				 		
		 		if(AlertDescription.contains(ExpectedexchangeTextVerify)||AlertExchangeName.contains(ExpectedexchangeText))
		 		{
		 			LOGGER.info("The description has been verified in the application whether the exchange has been reloaded");
		 		}
		 		break;
		 	}  
		
		 
		 String Appopentime=selectExchangeOpenTime.getText(); 
			
		 String OpenDate  = Appopentime.substring(0,11);
		
		 String OpenTim = Appopentime.substring(12,20);
	
		 String Appclosetime=selectExchangeCloseTime.getText();
		 
		 String CloseDate  = Appclosetime.substring(0,11);
		 String CloseTim = Appclosetime.substring(12,20);
		 
		 String queryExchangeID = "select EXCH_SEGMENT_ID from RT_M_STK_EXCH_SEG where SEGMENT_NAME='"+ExpectedexchangeText+"'";
		
		 String ExchangeId = DatawatchSQLStatements.sql_connect(queryExchangeID, 1);
		 
		 int ExchangeIdNo = Integer.parseInt(ExchangeId); 		 
		
		 String QueryOpenDate="select to_char(to_date(OPEN_TIME),'DD-MON-YYYY')from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;	 
		 String QueryCloseDate = "Select to_char(to_date(cLOSE_TIME),'DD-MON-YYYY')from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 String QueryOpenTime ="select to_char(OPEN_TIME,'hh24:mi:ss') from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 String QueryCloseTime ="select to_char(cLOSE_TIME,'hh24:mi:ss') from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 
		 String DB_desc = DatawatchSQLStatements.sql_connect(QueryOpenDate,1);
		 String DB_desc1 = DatawatchSQLStatements.sql_connect(QueryCloseDate,1);
		 String DB_desc2 = DatawatchSQLStatements.sql_connect(QueryOpenTime,1);
		 String DB_desc3 = DatawatchSQLStatements.sql_connect(QueryCloseTime,1);
		 
 
		 
		 if(OpenDate.equalsIgnoreCase(DB_desc)) 
			 
		 {
		
			 if(OpenTim.equalsIgnoreCase(DB_desc2 ))
			 {
		
				 if(CloseTim.equalsIgnoreCase(DB_desc3) )
				 {
			
					 if(CloseDate.equalsIgnoreCase(DB_desc1))
					 {
						 LOGGER.info("The open and close time of given exchange has been verified with database");
					 }			

				 }
					 
			 }
			 
		 }
		 
	 }
	 else if(Exp.equalsIgnoreCase(close))
	 {
		 Thread.sleep(2000);		
		 
		 String ExchangeTotalClose=ExchangeTotalNumber.getText();
		 if(ExchangeTotalClose.equals(0))
		 {
			 LOGGER.info("Exchange not available in closed state");
			 
		 }
		 else
		 {
		 ExchangeClosestatus.click(); 
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("researchframe");

		 String ExpectedexchangeText=selectExchangeListRight.getText();
		 String OpenTime = selectExchangeOpenTime.getText();
		 String CloseTime = selectExchangeCloseTime.getText();
		 selectExchangeListRight.rightClick();
		 RefreshFromT3.click();
		 Thread.sleep(25000);
		 FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row1 =sheet.getRow(13);
		 String ExpectedexchangeTextVerify =row1.getCell(1).getStringCellValue();
		 
		 for(int i=1;i<10;i++) 
		 	{
	 	 	
		 		ExtendedWebElement Description=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		ExtendedWebElement ExchangeAlert=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[4]"));
		 		String AlertDescription = Description.getText();
		 		String AlertExchangeName = ExchangeAlert.getText();	 		
		 		System.out.println(AlertExchangeName); 
		 		System.out.println(AlertDescription); 
		 		if(AlertDescription.contains(ExpectedexchangeTextVerify)||AlertExchangeName.contains(ExpectedexchangeText))
		 		{
		 			LOGGER.info("The description has been verified in the application whether the exchange has been reloaded");
		 		}
		 		break;
		 	}  
		 String Appopentime=selectExchangeOpenTime.getText();  
	
		 String OpenDate  = Appopentime.substring(0,11);		
		 String OpenTim = Appopentime.substring(12,20);			
		 String Appclosetime=selectExchangeCloseTime.getText();		 
		 String CloseDate  = Appclosetime.substring(0,11);
		 String CloseTim = Appclosetime.substring(12,20);		 
		 String queryExchangeID = "select EXCH_SEGMENT_ID from RT_M_STK_EXCH_SEG where SEGMENT_NAME='"+ExpectedexchangeText+"'";			
		 String ExchangeId = DatawatchSQLStatements.sql_connect(queryExchangeID, 1);		 
		 int ExchangeIdNo = Integer.parseInt(ExchangeId);
		 
		
		 String QueryOpenDate="select to_char(to_date(OPEN_TIME),'DD-MON-YYYY')from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;	 
		 String QueryCloseDate = "Select to_char(to_date(cLOSE_TIME),'DD-MON-YYYY')from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 String QueryOpenTime ="select to_char(OPEN_TIME,'hh24:mi:ss') from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 String QueryCloseTime ="select to_char(cLOSE_TIME,'hh24:mi:ss') from RT_T_EXCH_SEG_TIMINGS WHERE EXCH_SEGMENT_ID = "+ExchangeIdNo;
		 
		 String DB_desc = DatawatchSQLStatements.sql_connect(QueryOpenDate,1);
		 String DB_desc1 = DatawatchSQLStatements.sql_connect(QueryCloseDate,1);
		 String DB_desc2 = DatawatchSQLStatements.sql_connect(QueryOpenTime,1);
		 String DB_desc3 = DatawatchSQLStatements.sql_connect(QueryCloseTime,1);		 
		 	 
		 
		 if(OpenDate.equalsIgnoreCase(DB_desc))			 
		 {		
			 if(OpenTim.equalsIgnoreCase(DB_desc2 ))
			 {		
				 if(CloseTim.equalsIgnoreCase(DB_desc3) )
				 {			
					 if(CloseDate.equalsIgnoreCase(DB_desc1))
					 {
						 LOGGER.info("The open and close time of given exchange has been verified with database");
					 }			

				 }
					 
			 }
			 
		 }
		 
	 } 
	 }
	 
	
	 
	 else
	 {
		 LOGGER.info("not a valid");
	 }
	 
	 
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 Assert.fail("Error"+e.getMessage());
	 }
 
 
}
 
 public void refreshFromT3Mul() throws IOException
 {
	 try
	 {
		 driver.switchTo().defaultContent();

		 driver.switchTo().frame("dashboardframe");
		 ExchangeNewDaystatus.click();	
		 
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("researchframe");	  
		  
		 
		 Actions builder = new Actions(driver);
	     builder.click(selectMultipleRows.get(1)).keyDown(Keys.CONTROL).build().perform();
	     builder.click(selectMultipleRows.get(3)).keyDown(Keys.CONTROL).build().perform();
	     builder.click(selectMultipleRows.get(4)).keyUp(Keys.CONTROL).build().perform();
	     builder.contextClick().build().perform();
	     Thread.sleep(3000);	     
	     RefreshFromT3.click();	    
	     Thread.sleep(20000);	     
	     FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("Dynamic_TestData");
		 Row row =sheet.getRow(13);
		 String ExpectedexchangeTextDes =row.getCell(1).getStringCellValue();
	
	      
	     for(int i=1;i<10;i++) 
		 	{
	    	 	
		 		ExtendedWebElement d=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[6]"));
		 		String AlertDescription = d.getText();	
		 		ExtendedWebElement f=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr['+i+']//td[4]"));
		 		String subjectName = d.getText();
		 		if(AlertDescription.contains("Exchange has been reloaded"))
		 		{
		 			LOGGER.info("The description has been verified in the application whether the exchange has been refreshfromT3 ");
		 		}
		 		break;
		 	}     	   	
	        
		 
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 Assert.fail("Error"+e.getMessage());
	 }
 
	 
 }




}


	



 









	






