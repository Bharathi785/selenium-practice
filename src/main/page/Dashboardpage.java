package com.grip.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.grip.DBconnection.DatawatchSQLStatements;
import com.grip.utils.ExcelUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

import org.testng.Assert;

public class Dashboardpage extends AbstractPage {

Logger LOGGER = Logger.getLogger(Dashboardpage.class);

public Dashboardpage(WebDriver driver) {
super(driver);
}

@FindBy(xpath = "//input[@class='alert-download-category']")
public ExtendedWebElement download;

@FindBy(xpath="//img[@id='refreshImageId']")
public ExtendedWebElement  refreshtree;

@FindBy(xpath="//a[text()='Status']")
public ExtendedWebElement  Status;



@FindBy(xpath="//span[text()='Process Status']")
public ExtendedWebElement  processStatus;

@FindBy(xpath="//table[@id='viewCommentTable']//tbody")
public ExtendedWebElement  comments;

@FindBy(xpath="//div[@aria-describedby='viewAutoHoldDialog']//button")
public ExtendedWebElement  Thresholdclose;

@FindBy(xpath="//span[text()='View Thresholds']")
public ExtendedWebElement  viewThreshold;

@FindBy(xpath="//textarea[@id='copyNameTextareaId']")
public static ExtendedWebElement copyTextarea;

@FindBy(xpath="//table[@id='viewCommentTable']//tbody//tr[1]//td[3]")
public ExtendedWebElement  latestaddedcomment;

@FindBy(xpath="//table[@id='viewCommentTable']//tbody//tr[1]//td[2]")
public ExtendedWebElement  latestaddedID;



@FindBy(xpath="//span[text()='Warning Threshold']")
public ExtendedWebElement  warningThreshold;



@FindBy(xpath="//span[text()='Refresh From T3']")
public ExtendedWebElement  refresht3;

@FindBy(xpath="//table[@id='alertTable']//tr//td[6]")
public ExtendedWebElement img;


@FindBy(xpath="//table[@id='exchangeTable1']//tbody//tr[1]//td[1]")
public ExtendedWebElement exchageRow;



@FindBy(xpath="//span[@title='Open a new Exchange Window']")
public ExtendedWebElement research;



@FindBy(xpath="//div[@title='maximize window']")
public ExtendedWebElement maximize;


@FindBy(xpath="//input[@aria-controls='exchangeTable1']")
public ExtendedWebElement exchangesearch;



@FindBy(xpath="//div[@id='assetViews1']//input[@onclick='openCustomizeViewDialog()']")
public ExtendedWebElement customizeViewlist;



@FindBy(xpath="//button[@type='button']//span[text()='Close']")
public ExtendedWebElement  close;

@FindBy(xpath="//span[@data-bind='text: iWarning']")
public ExtendedWebElement Warning;

@FindBy(xpath="//span[@data-bind='text: sWarning']")
public ExtendedWebElement stockWarning;


@FindBy(xpath="//span[@data-bind='text: cWarning']")
public ExtendedWebElement contractWarning;


@FindBy(xpath="//span[@data-bind='text: iSuspect']")
public ExtendedWebElement suspect;


@FindBy(xpath="//span[@data-bind='text: fWarning']")
public ExtendedWebElement FIWarning;



@FindBy(xpath="//span[@data-bind='text: fxWarning']")
public ExtendedWebElement FxWarning;



@FindBy(xpath="//div[@class='select-items']//div[2]")
public ExtendedWebElement  IndexAction;


@FindBy(xpath="//div[@class='select-selected']")
public ExtendedWebElement  selectRsn;;


@FindBy(xpath="//div[@class='select-selected']")
public ExtendedWebElement  Default;



@FindBy(xpath="//table[@id='customColView']//span[text()='Cancel']")
public ExtendedWebElement  Cancel;


@FindBy(xpath="//div[@id='sound']//audio[@autoplay='autoplay']//source")
public ExtendedWebElement audio;

@FindBy(xpath="//span[text()='Save']")
public ExtendedWebElement Save;

@FindBy(xpath="//textarea[@rows='5']")
public ExtendedWebElement text;

@FindBy(xpath="//div[@title='.SPGLOBAL']//img")
public ExtendedWebElement flatlinedot;

@FindBy(xpath="//p[@class='comment-character-limit']//span")
public ExtendedWebElement charleft;

@FindBy(xpath="//input[@value='With Comments']")
public ExtendedWebElement withComments;

@FindBy(xpath="//input[@value='Without Comments']")
public ExtendedWebElement withoutComments;

@FindBy(xpath="//div[@title='.SPGLOBAL']//div[1]//span")
public ExtendedWebElement sp1200;

@FindBy(xpath="//div[@data-bind='attr: { title: tickerTitle}'][1]//div//span[1]")
public ExtendedWebElement navIndex;

@FindBy(xpath = "//table[@id='alertTable']//tr[1]//td[1]")
private ExtendedWebElement alertfirst;

@FindBy(xpath = "//table[@id='alertTable']//tr[1]//td[6]//img")
private ExtendedWebElement viewcomment;


@FindBy(xpath = "//label[text()='Alerts - ']")
private ExtendedWebElement alertFilter;



@FindBy(xpath = "//table[@id='viewCommentTable']//tbody//td[3]")
private ExtendedWebElement Alertcomment;

@FindBy(xpath="//div[@title='.SPCLXIGPA']//div//span")
public ExtendedWebElement navIndex2;

@FindBy(xpath="//span[text()='Submit']")
public ExtendedWebElement Submit;

@FindBy(xpath="//span[contains(text(),'Delete Node(s)')]")
public ExtendedWebElement  deleteConfirm;

@FindBy(xpath = "//button[@class='alert-download-button'][1]")
public ExtendedWebElement Download_ALerts;

//button[@class='alert-download-button'][2]

@FindBy(xpath = "//button[@class='alert-download-button'][2]")
public ExtendedWebElement Downloadcomments;

@FindBy(xpath = "//table[@class='alertDisplay dataTable']//th[6]")
public ExtendedWebElement Alert_Head;


@FindBy(xpath = "//select[@id='alertViewsList']//option[contains(text(),'Monitor')]")
public ExtendedWebElement Default_view;

@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[21]")
private ExtendedWebElement Compare_T3_process;

@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[22]")
private ExtendedWebElement Advanced_process;

@FindBy(xpath = "//div[@id='accordion']//h3[@id='ui-accordion-accordion-header-1']")
private ExtendedWebElement RecentLaunch_icon;

@FindBy(xpath="//div[@class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active']//li[1]//a")
private ExtendedWebElement RecentLaunch_Data;

@FindBy(xpath="//li[@class='nav-dots']//label[1]")
public ExtendedWebElement Slide1;

@FindBy(xpath="//li[@class='nav-dots']//label[2]")
public ExtendedWebElement Slide2;


@FindBy(xpath = "//div[@id='tree']//ul//li//span//a")
private ExtendedWebElement index_family_icon;

@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[19]")
private ExtendedWebElement Compare_T3;

@FindBy(xpath="//ul[@class='context-menu-list context-menu-root']//li[20]")
private ExtendedWebElement Advanced;

@FindBy(xpath="//span[@onclick='toggleAnimation()']")
public ExtendedWebElement pause;

@FindBy(xpath="//div[@id='indexTable1_filter']//input")
public ExtendedWebElement index_Filter;

@FindBy(xpath="//a[text()='GRIP']/preceding-sibling::span")
public ExtendedWebElement grip;

@FindBy(xpath="//a[contains(text(),'Ticker')]/preceding-sibling::span")
public ExtendedWebElement ticker;

@FindBy(xpath="//a[contains(text(),'S&P 1200')]")
public ExtendedWebElement SP1200;

@FindBy(xpath="//span[contains(text(),'Edit Node')]")
public ExtendedWebElement EditNode;

@FindBy(xpath="//span[contains(text(),'Copy Node')]")
public ExtendedWebElement copyNode;

@FindBy(xpath="//span[contains(text(),'Paste Node as Sibling')]")
public ExtendedWebElement pasteNode;

@FindBy(xpath="//a[contains(text(),'COPY OF S&P 1200')]")
public ExtendedWebElement cpySP;

@FindBy(xpath="//span[contains(text(),'COPY OF S&P 1200')]")
public ExtendedWebElement cpyticker;

@FindBy(xpath="//span[contains(text(),'Delete Node')]")  
public ExtendedWebElement DeleteNode;

@FindBy(xpath="//li[@class='context-menu-item']//span[text()='Add Comment']")
public ExtendedWebElement addComment;



@FindBy(xpath="//input[@id='param4']")
public ExtendedWebElement param;


@FindBy(xpath="//span[@id='researchtoolbarcolumn']")
public ExtendedWebElement index;

@FindBy(xpath="/html/body/ul/li[12]/span")
public ExtendedWebElement Release;

@FindBy(xpath="//table[@id='indexTable2']//tbody//tr[1]")
public ExtendedWebElement IndexData;

@FindBy(xpath="//span[text()='Ok']")
public ExtendedWebElement ReleaseOk;

@FindBy(xpath="//span[@id='alertstatusBarSpan']")
private ExtendedWebElement ALert_Count;

@FindBy(xpath = "//h3[@id='ui-accordion-accordion-header-2']")
private ExtendedWebElement processLaunchTab;

@FindBy(xpath = "//div[@class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active']")
private ExtendedWebElement indexFamilyName;

@FindBy(xpath = "//h3[@class='ui-accordion-header ui-helper-reset ui-state-default ui-corner-all ui-accordion-icons']")
private ExtendedWebElement recentLaunchTab;

@FindBy(xpath = "//div[@class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active']")
private ExtendedWebElement recentLaunchTabIndexFamily;

@FindBy(xpath = "//div[@id='processIndexTree']")
private ExtendedWebElement processLaunchTabIndexFamily;

@FindBy(id = "environmentListHeader")
private ExtendedWebElement listHeader;

@FindBy(xpath = "//div[@class='closeImg window_icon_button no-draggable']")
private ExtendedWebElement closeButton;

@FindBy(xpath = "//div[@class='ui-widget-header-mod ui-corner-top']")
private ExtendedWebElement assetName;

@FindBy(xpath = "//div[11]//li[1]//span[2]")
private ExtendedWebElement severityCheckall;

@FindBy(xpath = "//div[12]//li[1]//span[2]")
private ExtendedWebElement allTypCheckall;

@FindBy(xpath="//div[12]//li[2]//span[2]")
public ExtendedWebElement Unchel_all;

@FindBy(xpath = "//div[starts-with(@id, 'topd')]//table")
private ExtendedWebElement indicesDataWatch;

@FindBy(xpath = "//div[starts-with(@id, 'window_')]")
private ExtendedWebElement countWindow;

@FindBy(id = "ui-id-3")
public static ExtendedWebElement dashboardTab;

@FindBy(id = "ui-id-4")
public static ExtendedWebElement researchTab;

@FindBy(xpath = "//div[starts-with(@id, 'window')]//div[1]//div[2]//div[1]")
private ExtendedWebElement closeBtn;

@FindBy(xpath = "//span[starts-with(@id, 'statusBarSpan')]")
private ExtendedWebElement totalCount;

@FindBy(xpath = "//div[@class='window_title_text']")
private ExtendedWebElement title;

@FindBy(xpath = "//*[@id='processtbody']/tr[1]/td[2]/img")
private ExtendedWebElement status;

@FindBy(xpath = "//tbody[@id='processtbody']||//tbody[@id='incomingdatatbody']")
private ExtendedWebElement gripProcessTable;

@FindBy(xpath = "//div[@id='alertTable_filter']/label/input")
public static ExtendedWebElement alertSearch;

@FindBy(xpath = "//tbody[@role='alert']/tr[1]/td[4]")
private ExtendedWebElement alertSubject;

@FindBy(xpath = "//tbody[@role='alert']/tr[1]/td[5]")
private ExtendedWebElement alertUsername;

@FindBy(xpath = "//tbody[@role='alert']/tr[1]/td[6]")
private ExtendedWebElement alertDescription;

@FindBy(xpath = "//*[@id='alertstatusBarSpan']")
private ExtendedWebElement alertTotal;

@FindBy(xpath = "//tbody[@role='alert']")
private ExtendedWebElement alertTable;

@FindBy(xpath = "//input[@title='Customize Data View']")
private ExtendedWebElement customizeView;

@FindBy(xpath = "//*[@id='customAlertView']//tbody/tr[2]/td/button[1]")
private ExtendedWebElement customizeViewOk;

@FindBy(xpath = "//table[@class='alertDisplay dataTable']//tr//th[3]")
private ExtendedWebElement al_h_severity;

@FindBy(xpath = "//tbody[@role='alert']/tr[1]/td[3]")
private ExtendedWebElement al_f_Severity;

@FindBy(xpath = "//div[@class='dataTables_filter']//input")
public ExtendedWebElement SearchAlert;

@FindBy(id = "alertViewsList")
private ExtendedWebElement alertViewDropDown;

@FindBy(xpath = "//table[@id='alertTable']")
private ExtendedWebElement resultAlertTable;

@FindBy(xpath = "//div[12]/div/ul/li[2]/a/span[2]")
private ExtendedWebElement uncheckall;

@FindBy(xpath = "//div[@id='displayTimer']//i")
public static ExtendedWebElement CurrentTime;

public static String xpathforstocks = "//div[starts-with(@id, 'bottom')]";

public static String xpathforIndices = "//div[starts-with(@id, 'topd')]";

public static String xpathforContracts = "//div[starts-with(@id,'bottommiddleleftid')]";

public static String xpathforDerivatives="//div[starts-with(@id,'bottomrightderivativesid')]";

public static String xpathforFixedIncome = "//div[@id='bottommiddlerightid']";

public static String xpathforForex = "//div[@id='bottomrightid']";

public static String xpathForIncomingProcess = "//*[@id='incomingdatatbody']";

public static String xpathforExchanges = "//div[starts-with(@id,'rightspannedid')][1]";

public static String xpathForGripProcess = "//*[@id='processtbody']";

public static String xpathForOutDesProcess = "//*[@id='outbounddatatbody']";

public static String xpathforEODExchanges="//div[starts-with(@id,'rightspannedid')][2]";

public static String Current_Tm;

public static ExcelUtils excel = new ExcelUtils();

/**
* This method Validates Grip HommPage.
*
* @author Rangesh_TV
* @exception Exception
* @return - no return
*/
public void validateHomePage() {
try {
driver.switchTo().frame("dashboardframe");
Assert.assertTrue(resultAlertTable.isElementPresent());
driver.switchTo().defaultContent();
} catch (Exception e) {
e.printStackTrace();
Assert.fail("Unable to validate the home page-" + e.getMessage());
}

}
/**
 * This Method verify color code for Index in Ticket Monitor
 * @author rangesh_venkatesan
 * @throws Exception
 */

public void Verify_Ticket_Monitor() throws Exception
{
try {
	int f= 0;
	driver.switchTo().frame("dashboardframe");
	Slide1.click();
	Slide1.pause(5);
	pause.click();
	String Status ="";
	for(int i=1;i<=18;i++)
	{
	if(i!=17)
	{
	ExtendedWebElement Color = findExtendedWebElement(By.xpath("//div[@data-bind='attr: { title: tickerTitle}']["+i+"]//div//img"));
	String color = Color.getAttribute("src");
	ExtendedWebElement Index =findExtendedWebElement(By.xpath("//div[@data-bind='attr: { title: tickerTitle}']["+i+"]//div//span"));
	String Major_Indices = Index.getText();
	String Split = Major_Indices.substring(0, 7);
	String Split1= Major_Indices.substring(0, 3);
	driver.switchTo().defaultContent();
	researchTab.click();
	driver.switchTo().frame("researchframe");
	if(i==1)
	{
		index.click();
	}
	index_Filter.click();
	if(i<6)
		
	{
	
	index_Filter.type(Split);
	outer:for(int j=1;j<5;j++)
	{
	ExtendedWebElement Index_Name  = findExtendedWebElement(By.xpath("//table[@id='indexTable1']//tbody//tr["+j+"]//td[6]"));
	String Ind_Nm=Index_Name.getText();
	if(Ind_Nm.equalsIgnoreCase(Split))
	{
	ExtendedWebElement status  = findExtendedWebElement(By.xpath("//table[@id='indexTable1']//tbody//tr["+j+"]//td[2]"));
	Status  = status.getText();
	break outer;
		}
	}}
	if(i>=6 && i<=9)
	{
	index_Filter.type(Split1);
	outer:for(int j=1;j<5;j++)
	{
	ExtendedWebElement Index_Name  = findExtendedWebElement(By.xpath("//table[@id='indexTable1']//tbody//tr["+j+"]//td[4]"));
	String Ind_Nm=Index_Name.getText();
	if(Ind_Nm.equalsIgnoreCase(Split1))
	{
	ExtendedWebElement status  = findExtendedWebElement(By.xpath("//table[@id='indexTable1']//tbody//tr["+j+"]//td[2]"));
	Status  = status.getText();
	break outer;
	}}}
	if(i>=10 &&i<=18 &&i!=14)
	{
	String Search =excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC01041", "TestData1");	
	String Search_Split[]=Search.split(",");
	outer:	for(int k=f;k<Search_Split.length;)
	{
	index_Filter.type(Search_Split[k]);
	index_Filter.pause(5);
	f++;
	break outer;
	}
	ExtendedWebElement Index_Name  = findExtendedWebElement(By.xpath("//table[@id='indexTable1']//tbody//tr[1]//td[6]"));
	String Ind_Nm=Index_Name.getText();
	if(i==11)
	{
	Major_Indices="S&P/ASX 200";	
	}
	
	if(i==13)
	{
	Major_Indices="S&P Asia 50";	
	}
	
	if(i==15)
	{
	Major_Indices="COMP";	
	}
	
	if(i==18)
	{
	Major_Indices="S&P GLOBAL 1200";	
	}
	
	if((Ind_Nm.contains(Major_Indices))||(Ind_Nm.equalsIgnoreCase(Major_Indices)))
	{
	ExtendedWebElement status  = findExtendedWebElement(By.xpath("//table[@id='indexTable1']//tbody//tr[1]//td[2]"));
	Status  = status.getText();
	}
	if(Ind_Nm.contains("1200"))
	{
	ExtendedWebElement status  = findExtendedWebElement(By.xpath("//table[@id='indexTable1']//tbody//tr[1]//td[2]"));
			Status  = status.getText();
			
	}
	}
	
	if(!Major_Indices.contains("GRIP"))
	{
	 
	if(Status.equalsIgnoreCase("New Day") && color.contains("grey"))
	{
		LOGGER.info("Index "+Major_Indices+" is in New Day and Color Code Matches");
	}
	
	if(Major_Indices.contains("Prelim Close") && color.contains("grey") )
	{
		LOGGER.info("Index "+Major_Indices+" is in prelim Close and Color Code Matches");
	}
	
	
	if(Status.equalsIgnoreCase("Close") && color.contains("grey"))
	{
		LOGGER.info("Index "+Major_Indices+" is in New Day and Color Code Matches");
	}
	if(Status.equalsIgnoreCase("New Day") && !color.contains("grey"))
	{
		Assert.fail("Index "+Major_Indices+" is in New Day and Color Code  Not Matches");
	}
	
	if(Status.equalsIgnoreCase("Open") && color.contains("green"))
	{
		LOGGER.info("Index "+Major_Indices+" is in Open and Color Code Matches");
	}
	
	if(Status.equalsIgnoreCase("Open") && !color.contains("green"))
	{
		LOGGER.info("Index "+Major_Indices+" is in Open and Color Code not Matches");
	}
	
	if(Status.equalsIgnoreCase("flatline") && color.contains("amber"))
	{
		LOGGER.info("Index "+Major_Indices+" is in flatline and Color Code Matches");
	}
	
	if(Status.equalsIgnoreCase("flatline") && !color.contains("amber"))
	{
		LOGGER.info("Index "+Major_Indices+" is in flatline and Color Code not Matches");
	}
	}
	
	if(Major_Indices.contains("GRIP") && color.contains("green") )
	{
		LOGGER.info("Index "+Major_Indices+" is in Open and Color Code Matches");
	}
	
	
	if(Major_Indices.contains("GRIP") && !color.contains("green") )
	{
		Assert.fail("Index "+Major_Indices+" is not in  Open Status ");
	}
	
	driver.findElement(By.xpath("//div[@id='indexTable1_filter']//input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
	driver.switchTo().defaultContent();
	dashboardTab.click();
	driver.switchTo().frame("dashboardframe");
	}
	}}
catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
	
}

/**
 * This Method Validates total count in the stocks,Contracts,FixedIncome,Forex
 * @param xpath 	Locators in stocks,Contracts,FixedIncome,Forex  passed as an input	
 * @param n     	Column number in Stock,Contracts,FixedIncome,Forex 
 * @param Testcase 	Test case ID to fetch Query from excel
 */
public void dataWatch(String xpath, int n, String Testcase,String Data) 
{
try 
	{
	driver.switchTo().frame("dashboardframe");
	for (int x = 1; x <= n; x++) 
	{
	ExtendedWebElement indicesDW = findExtendedWebElement(By.xpath(xpath + "[" + x + "]"));
	List<ExtendedWebElement> tableRows = indicesDW.findExtendedWebElements(By.tagName("tr"));
	int Size=tableRows.size();
	if(Size<8 && Size>3)
	{
		Size=Size-2;
	}
	for (int i = 1; i <=Size; i++) 
	{
		
	List<ExtendedWebElement> tableColumns = findExtendedWebElements(By.xpath(xpath + "[" + x + "]//table//tbody//tr[" + i + "]//td"));
	String colName = tableColumns.get(1).getText();
	ExtendedWebElement linkName = findExtendedWebElement(By.xpath(xpath + "[" + x + "]//table//tbody//tr[" + i + "]//td[3]//span"));
	String indicesName = linkName.getText();
	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	String Dash_Text = colName.substring(0, colName.length() - 1);
	linkName.click();
	LOGGER.info("The data for " + colName + " is clicked");
	driver.switchTo().defaultContent();
	driver.switchTo().frame("researchframe");
	if(!colName.contains("Auto Hold"))//Remove if condition when Environment stable
	{
	if (countWindow.isElementPresent()) 
	{
	String total = totalCount.getText();
	String[] totalCounts = total.split("\\s");
	
	if (indicesName.equalsIgnoreCase(totalCounts[1])) 
	{
		LOGGER.info("" + colName + "  count  " + indicesName+ "in "+Data+" is equal in Dashboard tab and Research tab");
	} else 
	{
		Assert.fail("" + colName + " count  " + indicesName+ " in "+Data+" is not equal in Dashboard tab and Research tab");

	}}
	 else 
	{
		Assert.fail("The corresponding Index List Window is not displayed");
	}
	if(!Testcase.equals("TC01006")&&i!=4)
	{
	String Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0,Testcase, "Query"+i+"");
	LOGGER.info(Query);
	String DbCount=DatawatchSQLStatements.sql_connect(Query, 1);
	if(DbCount.equals(indicesName))
	{
	LOGGER.info("" + colName + " Count "+DbCount+"  in "+Data+"  Matches With DB");
	}
	else
	{
	Assert.fail("" + colName + " Count "+DbCount+"  in "+Data+" not    Matches With DB");
	}}
	if(Testcase.equals("TC01006")&&i<=6)
	{
	String Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0,Testcase, "Query"+i+"");
	LOGGER.info(Query);
	
	String DbCount=DatawatchSQLStatements.sql_connect(Query, 1);

	if(DbCount.equals(indicesName))
	{
		LOGGER.info(" " + colName + " Count" +DbCount+ "in " +Data+ " in   Matches With DB");


	}
	else
	{
		Assert.fail("" + colName + " Count " +DbCount+"in " +Data+ " Not Matches With DB");
	}}}

	if (i > 1) {
	if (title.isElementPresent()) 
	{
	String windowtitle = title.getText();
	if (windowtitle.contains(Dash_Text)) {
		LOGGER.info("Title for " + colName + " in Dashboard  Matches With Research");
	} else {
		LOGGER.info("Title for " + colName + " in Dashboard  not  Matches With Research");
	}}}
	closeBtn.click();
	driver.switchTo().defaultContent();
	dashboardTab.click();
	driver.switchTo().frame("dashboardframe");
	}}}
catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
}


public void switchdefault()
{
	driver.switchTo().defaultContent();
}

/**
 * This Method validates total count in Indices
 * @author 		rangesh_venkatesan
 * @param 		xpath Locators in Indices passed as an input
 * @param 		n Total columns in Indices
 * @exception	Exception
 * @return 		No return
 */

public void dataWatch_Indices(String xpath, int n, String Data) 
{
try 
{
	String Query="";
	driver.switchTo().frame("dashboardframe");
	for (int x = 1; x <=n; x++) 
	{
	ExtendedWebElement indicesDW = findExtendedWebElement(By.xpath(xpath + "[" + x + "]"));
	List<ExtendedWebElement> tableRows = indicesDW.findExtendedWebElements(By.tagName("tr"));
	int Size=tableRows.size();
	if(x==2)
	{
		Size=Size-1;
	}
	if(x==3)
	{
		Size=Size-2;
	}
	for (int i = 1; i <=Size; i++) 
	{
	List<ExtendedWebElement> tableColumns = findExtendedWebElements(By.xpath(xpath + "[" + x + "]//table//tbody//tr[" + i + "]//td"));
	String colName = tableColumns.get(1).getText();
	ExtendedWebElement linkName = findExtendedWebElement(By.xpath(xpath + "[" + x + "]//table//tbody//tr[" + i + "]//td[3]//span"));
	String indicesName = linkName.getText();
	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	String Dash_Text = colName.substring(0, colName.length() - 1);
	linkName.click();
	LOGGER.info("The data for " + colName + " is clicked");
	driver.switchTo().defaultContent();	
	driver.switchTo().frame("researchframe");
	if(!colName.contains("Auto Hold")&&!colName.contains("Ticks"))//Remove if condition when Environment stable
	{
	if (countWindow.isElementPresent()) {
	String total = totalCount.getText();
	String[] totalCounts = total.split("\\s");
	
	if (indicesName.equalsIgnoreCase(totalCounts[1])) {
	LOGGER.info("" + colName + "  count  " + indicesName+  " in " +Data+" is equal in Dashboard tab and Research tab");
	} 
	else 
	{
	Assert.fail("" + colName + " count  " + indicesName+ " in " +Data+" is not equal in Dashboard tab and Research tab");

	}}
	else 
	{
	Assert.fail("The corresponding Index List Window is not displayed");
	}
	if(x==1)
	{
	Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC01001", "Query"+i+"");
	LOGGER.info(Query);
	}
	/*if(x==2 &&colName.contains("Auto Hold"))
	{
	 Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC01001", "Query7"); 
	 LOGGER.info(Query);
	}*/
	if(x==2 &&colName.contains("Manual Hold"))
	{
	 Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC01001", "Query8"); 
	 LOGGER.info(Query);
	}
 
	if(x==3 &&colName.contains("Incorrect Portfolio"))
	{
	 Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC01001", "Query9"); 
	 LOGGER.info(Query);
	}
	if(x==3 &&colName.contains("Incorrect Parameters"))
	{
	 Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC01001", "Query10"); 
	 LOGGER.info(Query);
	}
 
	if(x==3 &&colName.contains("Incorrect Timings"))
	{
	 Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC01001", "Query11"); 
	 LOGGER.info(Query);
	}
	if((x==1)||(x==2 &&colName.contains("Manual Hold"))||/*(x==2 &&!colName.contains("Auto Hold"))||*/(x==3 &&colName.contains("Incorrect Portfolio"))||(x==3 &&colName.contains("Incorrect Parameters"))||(x==3 &&colName.contains("Incorrect Timings")))
	{
	String DbCount=DatawatchSQLStatements.sql_connect(Query, 1);
	if(DbCount.equals(indicesName))
	{
	LOGGER.info("" + colName + " Count "+DbCount+"  in "+Data+"  Matches With DB");
	}
	else
	{
	Assert.fail("" + colName + " Count "+DbCount+"  in "+Data+" not    Matches With DB");
	}
	}}
	if (i > 1) {
	if(title.isElementPresent()) 
	{
	String windowtitle = title.getText();
	if(Dash_Text.contains("Vix"))
	{
		Dash_Text="vixRollDate";
	}
	if (windowtitle.contains(Dash_Text)) 
	{
	LOGGER.info("Title for " + colName + " in Dashboard  Matches With Research");
	}
	else 
	{
	Assert.fail("Title for " + colName + " in Dashboard  not  Matches With Research");
	}
	}

	}
	closeBtn.pause(3);
	closeBtn.click();
	driver.switchTo().defaultContent();
	dashboardTab.click();
	driver.switchTo().frame("dashboardframe");
	}
	}
	} 
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	}
}

/**
 * This Method Validates color code of All Grip Processes
 * @param xpathForProcess   xpath of the Process passed as an input 
 * @param Sheet 			Fetch Test data from Excel
 * @param Column			Column Number of the Process
 * @throws 					IOException
 */

public void validateStatusofAllProcesses_Both(String xpathForProcess, String Sheet, int Column) throws IOException {

try 
{
	driver.switchTo().frame("dashboardframe");
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet(Sheet);
	int rowcount = sheet.getLastRowNum() + 1;
	ExtendedWebElement tableInput = findExtendedWebElement(By.xpath(xpathForProcess));
	List<ExtendedWebElement> tableRow = tableInput.findExtendedWebElements(By.tagName("tr"));
	for (int i = 1; i <= tableRow.size(); i++) {
	String processText = findExtendedWebElement(By.xpath(xpathForProcess + "/tr[" + i + "]/td[1]/div")).getText();
	ExtendedWebElement ColumnA = findExtendedWebElement(By.xpath(xpathForProcess + "/tr[" + i + "]/td[2]/img"));
	ExtendedWebElement ColumnB = findExtendedWebElement(By.xpath(xpathForProcess + "/tr[" + i + "]/td[3]/img"));
	String Stautus_A = ColumnA.getAttribute("src");
	String Stautus_B = ColumnB.getAttribute("src");
	LOGGER.info("Current Grip process is" + processText);
	String[] a1 = Stautus_A.split("/");
	String[] b1 = Stautus_B.split("/");
	outer: for (int k = 1; k < rowcount; k++) 
	{
	Row row = sheet.getRow(k);
	for (int j = Column; j < row.getLastCellNum(); j++) {
	String a = row.getCell(j).getStringCellValue();
	if (a.equalsIgnoreCase(processText) && a1[6].contains("green") && b1[6].contains("green")) 
	{
	LOGGER.info(" Both Process A and  B for  " + processText + " are  Green");
	}
	else if (!a.equalsIgnoreCase(processText) && a1[6].contains("green")&& b1[6].contains("green")) 
	{
	LOGGER.info(" Both Process A and  B for " + processText + " are  Green");
	}
	break outer;
	}}
	if (a1[6].contains("green") || b1[6].contains("green")) {
	if (!(a1[6].contains("green") && b1[6].contains("green")))
	LOGGER.info("Either of Process A or B for " + processText + "  is  Green");
	} else if (a1[6].contains("red") || b1[6].contains("red")) {
	Assert.fail("Either of Process A or B for " + processText + "  is  Red");
	} else {
	Assert.fail("None of the  Process A or B  " + processText + "  is  Green");
	}
	 workbook.close();
	}
	} catch (Exception e) {
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	}
	}

/**
 * This Method Validates Alert dots in Indices,Stocks,Fixed Income,Forex
 * @author rangesh_venkatesan
 * @param xpath 		locators of Indices,Stocks,Fixed Income,Forex passed as an input
 * @param n     		Total columns in Indices,Stocks,Fixed Income,Forex 
 * @param Sheet 		Sheet data in excel
 * @throws IOException 
 */

public void verify_col(String xpath, int n, String Sheet) throws IOException 
{
try
{
	driver.switchTo().frame("dashboardframe");
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet(Sheet);
	for (int x = 1; x <= n; x++) {
	ExtendedWebElement indicesDW = findExtendedWebElement(By.xpath(xpath + "[" + x + "]"));
	List<ExtendedWebElement> tableRows = indicesDW.findExtendedWebElements(By.tagName("tr"));
	int rowSize = tableRows.size();
	for (int i = 1; i <= rowSize; i++) {
	List<ExtendedWebElement> tableColumns = findExtendedWebElements(By.xpath(xpath + "[" + x + "]//table//tbody//tr[" + i + "]//td"));
	String colName = tableColumns.get(1).getText();
	ExtendedWebElement linkName = findExtendedWebElement(By.xpath(xpath + "[" + x + "]//table//tbody//tr[" + i + "]//td[3]//span"));
	String indicesName = linkName.getText();
	ExtendedWebElement indices = findExtendedWebElement(By.xpath(xpath + "[1]//table//tbody//tr[1]//td[2]//b"));
	String indices1 = indices.getText();
	for (int k = 1; k < 13; k++) {
	Row row = sheet.getRow(k);
	for (int j = 3; j < 4; j++) 
	{
	String a = row.getCell(j).getStringCellValue();
	if (a.equalsIgnoreCase(colName) && !indicesName.contains("0")) {
	ExtendedWebElement color = findExtendedWebElement(By.xpath(xpath + "[" + x + "]//table//tbody//tr[" + i + "]//td[1]/img"));
	String Color = color.getAttribute("src");
	if (x == 1 && Color.contains("amber")) {
	LOGGER.info("Alert color for datawatch in " + indices1 + " for" + a + "Matches");
	} else if (Color.contains("red")) 
	{
	LOGGER.info("Alert color for datawatch in  " + indices1 + " for" + a + "Matches");
	} else 
	{
	Assert.fail("Alert color for datawatch in  " + indices1 + " for" + a+ " does not Matches");
	}
	}
	}}}
} workbook.close();
	driver.switchTo().defaultContent();
}
catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
}

/**
 * This method validate search data matches with UI and DB
 * @author rangesh_venkatesan
 * @throws Exception
 */
public void Search_alert_commaDelimited() throws Exception {
try {
	driver.switchTo().frame("dashboardframe");
	Select view = new Select(driver.findElement(By.id("alertViewsList")));
	view.selectByIndex(1);
	String tSearch = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC01015", "TestData1");
	String Search[] = tSearch.split(",");
	alertSearch.type(tSearch);
	alertSearch.pause(35);
	String alertSize = ALert_Count.getText();
	String Total = alertSize.substring(alertSize.indexOf(":") + 2, alertSize.indexOf(")"));
	String al_f_tSubject = Search[0];
	String al_f_tUserName = Search[1];
	String al_f_tDescription = Search[2];
	List<ExtendedWebElement> tableRowA = alertTable.findExtendedWebElements(By.tagName("tr"));
	int j;
	if (tableRowA.size() > 10) 
	{
	j = 10;
	} else 
	{
	j = tableRowA.size();
	}
	LOGGER.info("Sample rows validate=" + j);
	for (int i = 1; i <= j; i++) {
	ExtendedWebElement aSubject = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[4]"));
	ExtendedWebElement aUserName = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[5]"));
	ExtendedWebElement aDescription = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[6]"));
	String atSubject = aSubject.getText();
	String atUserName = aUserName.getText();
	String atDescription = aDescription.getText();
	if (atSubject.contains(al_f_tSubject) || atUserName.contains(al_f_tUserName)||atDescription.contains(al_f_tDescription)) {
	LOGGER.info("Result retrieved is matching with search criteria");
	} 
	else 
	{
	Assert.fail("Result retrieved is not matching the search criteria");
	}}
	String Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC01015", "Query1");
	LOGGER.info(Query);
	String DBcount = DatawatchSQLStatements.sql_connect(Query, 1);
	if (Total.equals(DBcount)) 
	{
	LOGGER.info("Alert Matches with DB");
	}}
catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}


/**
* This method Search alert data with Percentage delimiter
* @author Rangesh_TV
* @exception Exception
* @return - no return
*/

public void Search_alert_PercentageDelimited() throws IOException {

	try {
		driver.switchTo().frame("dashboardframe");
		Select view = new Select(driver.findElement(By.id("alertViewsList")));
		view.selectByIndex(1);
		String tSearch = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC01016", "TestData1");
		String tserach_split[] = tSearch.split("%");
		String a = tserach_split[0];
		String b = tserach_split[1];
		String c = tserach_split[2];
		alertSearch.type(tSearch);
		alertSearch.pause(20);
		String alertSize = ALert_Count.getText();
		String Total = alertSize.substring(alertSize.indexOf(":") + 2, alertSize.indexOf(")"));
		int j;
		List<ExtendedWebElement> tableRowA = alertTable.findExtendedWebElements(By.tagName("tr"));
		if (tableRowA.size() > 10) 
		{
			j = 10;
		} else {
			j = tableRowA.size();
		}
		for (int i = 1; i <= j; i++) {
		ExtendedWebElement aSubject = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[4]"));
		ExtendedWebElement aUserName = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[5]"));
		ExtendedWebElement aDescription = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[6]"));
		String atSubject = aSubject.getText().toLowerCase();
		String atUserName = aUserName.getText().toLowerCase();
		String atDescription = aDescription.getText().toLowerCase();
		if ((atSubject.contains(a) && atSubject.contains(b) && atSubject.contains(c))
		|| (atUserName.contains(a) && atUserName.contains(b) && atUserName.contains(c))
		|| (atDescription.contains(a) && atDescription.contains(b) && atDescription.contains(c))) 
		{
		LOGGER.info("Result retrieved is matching with search criteria");
		} else 
		{
		Assert.fail("Result retrieved is not matching the search criteria");
		}
		}
		String Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC01016", "Query1");
		LOGGER.info(Query);
		String DBcount = DatawatchSQLStatements.sql_connect(Query, 1);
		if (Total.equals(DBcount)) 
		{
		LOGGER.info("Alert Matches with DB");
		}
	}
catch (Exception e) {
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}

/**
* This method uncheck Low & Medium filters and validate alert data
*
* @author rangesh_venkatesan
* @param Sheet Sheet Data in excel
* @exception Exception
* @return - no return
*/

public void alert_with_severity_filter(String Sheet) throws IOException 
{
	try 
	{
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(Sheet);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("dashboardframe");
		customizeView.click();
		HashSet<String> setexcel = new HashSet<String>();
		for (int i = 0; i < 4; i++) {
		ExtendedWebElement ewSeverity = findExtendedWebElement(By.id("ui-multiselect-severityObj-option-" + i));
		String xpathforsevlabel = ("//label[@for='ui-multiselect-severityObj-option-" + i + "']//span");
		ExtendedWebElement ew_l_Severity = findExtendedWebElement(By.xpath(xpathforsevlabel));
		String Severity = ew_l_Severity.getText();
		for (int k = 1; k <= 2; k++) {
		Row row = sheet.getRow(k);
		for (int j = 4; j <= 4; j++) {
		String iSeverity = row.getCell(j).getStringCellValue();
		setexcel.add(iSeverity);
		if (Severity.equalsIgnoreCase(iSeverity)) 
		{
		ewSeverity.uncheck();
		LOGGER.info(" Severity " + Severity + "  unchecked");
		}}}}
		customizeViewOk.click();
		List<ExtendedWebElement> tableRowA = alertTable.findExtendedWebElements(By.tagName("tr"));
		HashSet<String> setapp = new HashSet<String>();
		for (int z = 1; z < tableRowA.size(); z++) {
		ExtendedWebElement alertOptions = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + z + "]/td[2]"));
		String alerttype = alertOptions.getText();
		setapp.add(alerttype);
		}
		if (!setexcel.contains(setapp)) {
		LOGGER.info("Expected severity is filtered");
		} else {
		Assert.fail("Expected Severity is not filtered");
		}
		 workbook.close();
	}

	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}

/**
* This method uncheck Manual Hold & Threshold breach alert and validates alert
* @author rangesh_venkatesan
* @exception Exception
* @return - no return
*/
public void alert_without_unchecked_alerttype() 
{
try {
	driver.switchTo().frame("dashboardframe");
	customizeView.click();
	String unCheckAlertType1 = "Manual Hold";
	String unCheckAlertType2 = "Threshold breach";
	for (int i = 0; i < 20; i++) {
	ExtendedWebElement cvalert = findExtendedWebElement(By.xpath("//*[@id='ui-multiselect-alertTypeObj-option-" + i + "']"));
	int j = i + 1;
	ExtendedWebElement alerttype = findExtendedWebElement(By.xpath("/html/body/div[12]/ul/li[" + j + "]/label/span"));
	String alerttype1 = alerttype.getText();
	if (alerttype1.equalsIgnoreCase(unCheckAlertType1) || alerttype1.equalsIgnoreCase(unCheckAlertType2)) 
	{
	cvalert.uncheck();
	LOGGER.info("This" + alerttype1 + " is unchecked");
	} }
	customizeViewOk.click();
	for (int k = 1; k <= 10; k++) {
	ExtendedWebElement aAlertType = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + k + "]/td[2]"));
	String atAlertType = aAlertType.getText();
	LOGGER.info("Sample rows Validated :10");
	if (!atAlertType.equalsIgnoreCase(unCheckAlertType1) && !atAlertType.equalsIgnoreCase(unCheckAlertType1)) {
		LOGGER.info("Result retrieved is matching with search criteria");
	}

	else 
	{
	Assert.fail("Result retrieved is not matching the search criteria");
	}}
	}
catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}

}

/**
* This method filters  System Monitor ,Process Status,EOD value and validates Alert Data
* @author rangesh_venkatesan
* @exception Exception
* @return - no return
*/
public void alert_with_checked_alerttype() 
{
	try 
	{
		driver.switchTo().frame("dashboardframe");
		customizeView.click();
		Unchel_all.click();
		String CheckAlertType1 = "System Monitor";
		String CheckAlertType2 = "Process Status";
		String CheckAlertType3 = "EOD Value";
		for (int i = 0; i < 20; i++) {
		ExtendedWebElement cvalert = findExtendedWebElement(By.xpath("//*[@id='ui-multiselect-alertTypeObj-option-" + i + "']"));
		int j = i + 1;
		ExtendedWebElement alerttype = findExtendedWebElement(By.xpath("/html/body/div[12]/ul/li[" + j + "]/label/span"));
		String alerttype1 = alerttype.getText();
		if (alerttype1.equalsIgnoreCase(CheckAlertType1) || alerttype1.equalsIgnoreCase(CheckAlertType2)|| alerttype1.equalsIgnoreCase(CheckAlertType3)) 
		{
		cvalert.check();
		LOGGER.info (""+ alerttype1 +"  is checked");
		
		}}
		customizeViewOk.click();
		for (int k = 1; k <= 10; k++) {
		ExtendedWebElement aAlertType = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + k + "]/td[2]"));
		String atAlertType = aAlertType.getText();
		if (atAlertType.equalsIgnoreCase(CheckAlertType1) || atAlertType.equalsIgnoreCase(CheckAlertType2)
		|| atAlertType.equalsIgnoreCase(CheckAlertType3)) {
		LOGGER.info("Result retrieved is matching with search criteria");
		}else {
		Assert.fail("Result retrieved is not matching the search criteria");
		}
		}
	  	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}

/**
* This method filters alerts with all Severity
*
* @author Rangesh_TV
* @param xpath - data type string, xpath of the element passed as input,Sheet
*              for Accessing data in Excel
* @exception Exception
* @return - no return
*/

public void alert_with_all_severity() {
	try {
		driver.switchTo().frame("dashboardframe");
		customizeView.click();
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < 4; i++) {
		String xpathforsevlabel = "//label[@for='ui-multiselect-severityObj-option-" + i + "']//span";
		ExtendedWebElement ew_l_Severity = findExtendedWebElement(By.xpath(xpathforsevlabel));
		String Severity = ew_l_Severity.getText();
		set.add(Severity);
		}
		customizeViewOk.click();
		HashSet<String> setfromApp = new HashSet<String>();
		for (int i = 1; i < 10; i++) {
		ExtendedWebElement aSeverity = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[3]"));
		String atSeverity = aSeverity.getText();
		setfromApp.add(atSeverity);
		}
		
		Object[] arr = setfromApp.toArray();
		for (int j = 0; j < arr.length; j++) {
		if (set.contains(arr[j])) {
			LOGGER.info("Result retrieved is matching with search criteria");
		}

		else 
		{
			Assert.fail("Result retrieved is not matching the search criteria");
		}
	} }
catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}


/**
 * This method checks all alert type and validates data in UI
 * @author rangesh_venkatesan
 * @exception Exception
 */
public void alert_with_all_alerttype() {
try 
 {
	driver.switchTo().frame("dashboardframe");
	customizeView.click();
	HashSet<String> set = new HashSet<String>();
	for (int j = 1; j < 25; j++) {
	ExtendedWebElement alerttype = findExtendedWebElement(By.xpath("/html/body/div[12]/ul/li[" + j + "]/label/span"));
	String alerttype1 = alerttype.getText();
	set.add(alerttype1);
	}
	allTypCheckall.click();
	customizeViewOk.click();
	HashSet<String> setfromApp = new HashSet<String>();
	for (int i = 1; i < 10; i++) {
	ExtendedWebElement aAlertType = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[2]"));
	String atAlertType = aAlertType.getText();
	setfromApp.add(atAlertType);
	}
	Object[] arr = setfromApp.toArray();
	for (int j = 0; j < arr.length; j++) {
	if (set.contains(arr[j])) {
		LOGGER.info("Result retrieved is matching with search criteria");
	}

	else 
	{
		Assert.fail("Result retrieved is not matching the search criteria");
	}} 
}
 catch (Exception e) 
 {
	 e.printStackTrace();
	 Assert.fail("" + e.getMessage());
}
}



/**
 * This method Perform Manual Hold & Right click on index Alert
 * @author rangesh_venkatesan
 * @exception Exception
 * @return - no return
 */

public void IndexAlert() throws InterruptedException {
	try {
		driver.switchTo().frame("dashboardframe");
		ExtendedWebElement Open = findExtendedWebElement(By.xpath("//div[starts-with(@id, 'topd')]//tbody//tr[4]//td[3]//span"));
		Open.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("researchframe");
		ExtendedWebElement Ind_List = findExtendedWebElement(By.xpath("//table[@id='indexTable1']//tbody//tr[1]//td[6]"));
		Ind_List.rightClick();
		ExtendedWebElement Hold = findExtendedWebElement(By.xpath("/html/body/ul/li[11]/span"));
		Hold.click();
		ExtendedWebElement ok_but = findExtendedWebElement(By.xpath("//span[text()='Ok']"));
		ok_but.click();
		Boolean a = false;
		ok_but.pause(15);
		scrollToDown();
		Researchpage.Alert_Search.type("Manual");
		Researchpage.Alert_Search.pause(10);
		outer: for (int i = 1; i < 10; i++) 
		{
		ExtendedWebElement alert_dta = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr[" + i + "]//td[2]"));
		String alert_data = alert_dta.getText();
		ExtendedWebElement alert_dta1 = findExtendedWebElement(By.xpath("//table[@id='alertTable'][" + i + "]//td[4]"));
		String Subject = alert_dta1.getText();
		if (alert_data.equalsIgnoreCase("Manual Hold")) {
		alert_dta.rightClick();
		SupportPage sup= new SupportPage(getDriver());
		sup.verify_Context("Manual",5);
		ExtendedWebElement Refresh_Frm = findExtendedWebElement(By.xpath("/html/body/ul/li[1]/span"));
		Refresh_Frm.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("reportsframe");
		ExtendedWebElement Index_value = findExtendedWebElement(By.xpath("//div[@class='topleftcell']//tbody//tr//td[3]//input"));
		String Ind_vl = Index_value.getAttribute("value");
		if (Ind_vl.contains(Subject)) 
		{
					
		LOGGER.info("Report Tab opened With Selected Index");
		a = true;

		}
		break outer;

		}
		}
		if (!a)

		{
		LOGGER.info("Report Tab Not opened With Selected Index");
		}
		driver.switchTo().defaultContent();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		Researchpage.Alert_Search.type("Manual");
		Researchpage.Alert_Search.pause(5);
		String text ="";
		outer1: for (int i = 1; i < 10; i++) {
		ExtendedWebElement alert_dta = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr[" + i + "]//td[2]"));
		String alert_data = alert_dta.getText();
		if (alert_data.equalsIgnoreCase("Manual Hold")) 
		{
		alert_dta.rightClick();
		ExtendedWebElement Refresh_Frm = findExtendedWebElement(By.xpath("/html/body/ul/li[4]/span"));
		Refresh_Frm.click();
		text=copyTextarea.getText();
		ExtendedWebElement Copy_msg = findExtendedWebElement(By.xpath("//span[contains(text(),'Copy to Clipboard')]"));
		Copy_msg.click();
		}
		break outer1;
		}
		driver.findElement(By.xpath("//input[@aria-controls='alertTable']")).sendKeys(Keys.chord(Keys.CONTROL, "V"));
		String alert_msg = Researchpage.Alert_Search.getAttribute("value");
		if(text.equalsIgnoreCase(alert_msg))
		{
		
		LOGGER.info("Alert Message Copied to ClipBoard");
		}
		Researchpage.Alert_Search.type("Manual");
		Boolean b = false;
		outer2: for (int i = 1; i < 10; i++) {
		ExtendedWebElement alert_dta = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr[" + i + "]//td[2]"));
		String alert_data = alert_dta.getText();
		if (alert_data.equalsIgnoreCase("Manual Hold")) {
		ExtendedWebElement alert_dta1 = findExtendedWebElement(By.xpath("//table[@id='alertTable'][" + i + "]//td[4]"));
		String Subject = alert_dta1.getText();
		alert_dta.rightClick();
		ExtendedWebElement Refresh_Frm = findExtendedWebElement(By.xpath("/html/body/ul/li[6]/span"));
		Refresh_Frm.click();
		ExtendedWebElement Ind_List1 = findExtendedWebElement(By.xpath("//table[@id='indexTable2']//tr[1]//td[6]"));
		String Index_name = Ind_List1.getText();
		if (Subject.contains(Index_name)) 
		{
		LOGGER.info("Index List Window Opened With Selected Index");
		b = true;
		}
		break outer2;

		}

		}
		if (!b)

		{
			LOGGER.info("Index List Window Not Opened With Selected Index");
		}
		IndexData.rightClick();
		Release.click();
		ReleaseOk.click();
		
		
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}







/**
 * This method sorts out all columns in Alert Window
 * @author rangesh_venkatesan
 * @throws FileNotFoundException
 * @throws ParseException
 */

public void sort_alert() throws FileNotFoundException, ParseException {
	try {
		driver.switchTo().frame("dashboardframe");
		Select view = new Select(driver.findElement(By.id("alertViewsList")));
		view.selectByIndex(0);
		String name=System.getProperty("user.name");
		SearchAlert.type(name);
		SearchAlert.pause(5);
		String alertSize=ALert_Count.getText();
		String Total = alertSize.substring(alertSize.indexOf(":")+2, alertSize.indexOf(")"));
		int size=Integer.parseInt(Total);
		Sort(size,"Time",1);
		SearchAlert.pause(5);
		Sort(size,"Alert Type",2);
		SearchAlert.pause(5);
		Sort(size,"Severity",3);
		SearchAlert.pause(5);
		Sort(size,"Subject",4);
		SearchAlert.pause(5);
		Sort(size,"UserName",5);
		SearchAlert.pause(5);
		Sort(size,"Description",6);
		
		
	}
 catch (Exception e) {
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
}

/**
 * This method perform sorts of all columns in alert window
 * @author rangesh_venkatesan
 * @param size   Total rows sorted
 * @param Column Column Data to be filtered 
 * @param Header Column number
 */
public void Sort(int size,String Column, int Header)
{
try 
 {
	ExtendedWebElement srt = findExtendedWebElement(By.xpath("//table[@class='alertDisplay dataTable']//th["+Header+"]"));
	srt.click();
	srt.pause(5);
	TreeSet<String> treeSet = new TreeSet<String>();
	for(int i=1;i<=size;i++)
	{
	ExtendedWebElement alert_dta_asc = findExtendedWebElement(By.xpath("//tbody[@role='alert']//tr["+i+"]//td["+Header+"]"));
	String time_asc = alert_dta_asc.getText();
	treeSet.add(time_asc);
	}
	String Ascending =treeSet.first();
	String Descending =treeSet.last();
	ExtendedWebElement alert_dta_asc = findExtendedWebElement(By.xpath("//tbody[@role='alert']//tr[1]//td["+Header+"]"));
	String SortedAsc=alert_dta_asc.getText();
	srt.click();
	srt.pause(5);
	String SortedDesc=alert_dta_asc.getText();
	if(SortedAsc.equals(Ascending)) {
		LOGGER.info(""+Column+ " Sorted in Ascending order");
	}
	else
	{
		Assert.fail(""+Column+ "  Not Sorted in Ascending order");
	}
	if(SortedDesc.equals(Descending)) {
		LOGGER.info(""+Column+ " Sorted in Descending order");
	}
	else
	{
		Assert.fail(""+Column+ "  Not Sorted in Descending order");
	}
		 
 }
catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}

	
}
	




/**
 * This Method validates default view in alert window and verify alerts
 * @param s  Sheet data from excel
 * @param t  Monitor view value in SelectIndex
 * @throws IOException
 */

public void Monitor_view(String s, int t) throws IOException {
try {
	driver.switchTo().frame("dashboardframe");
	String view = Default_view.getAttribute("selected");
	if (view.equalsIgnoreCase("true")) {
		LOGGER.info("Default Alert Message Window is in Monitor view");
	}
	alertViewDropDown.select(t);
	customizeView.click();
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet(s);
	HashSet<String> setexcel = new HashSet<String>();
	for (int k = 1; k < 12; k++) {
	Row row = sheet.getRow(k);
	for (int j = 6; j < 7; j++) {
		String a = row.getCell(j).getStringCellValue();
		setexcel.add(a);
	}}
	customizeViewOk.click();
	HashSet<String> setapp = new HashSet<String>();
	LOGGER.info("Sample Alerts Validated :10");
	for (int z = 1; z < 11; z++) {
	ExtendedWebElement alertOptions = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + z + "]/td[2]"));
	String alerttype = alertOptions.getText();
	setapp.add(alerttype);
	if (setexcel.contains(alerttype)) {
	LOGGER.info("Default alert type  displayed in Alerts for Monitor View ");
	} else {
	Assert.fail("Invalid Alert types are displayed in Alerts for Monitor view");
	}}
	workbook.close();
	} 
catch (Exception e) 
	{
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
	}
}


/**
 * This Method validates alert type and severity type in research view
 * @author rangesh_venkatesan
 * @throws IOException
 */
public void Research_view() throws IOException
{
try 
{
	driver.switchTo().frame("dashboardframe");
	Select view = new Select(driver.findElement(By.id("alertViewsList")));
	view.selectByIndex(1);
	customizeView.click();
	List<String> Sev_list = Arrays.asList("Critical", "High", "Medium", "Low");
	for (int k = 0; k < 4; k++) {
	ExtendedWebElement ewSeverity = findExtendedWebElement(By.id("ui-multiselect-severityObj-option-" + k));
	String xpathforsevlabel = ("//label[@for='ui-multiselect-severityObj-option-" + k + "']//span");
	ExtendedWebElement ew_l_Severity = findExtendedWebElement(By.xpath(xpathforsevlabel));
	String Severity = ew_l_Severity.getText();
	if (Sev_list.contains(Severity)) 
	{
	if(ewSeverity.isChecked()) 
	{
	LOGGER.info(Severity + " is checked as default");
	} 
	else 
	{
	Assert.fail(Severity + "  is not checked");
	}} 
	else 
	{
	LOGGER.info("Severity displayed in UI is not matching with the Default Severity List");
	}}
	HashSet<String> setexcel = new HashSet<String>();
	for (int i = 0; i < 22; i++) {
	ExtendedWebElement cvalert = findExtendedWebElement(By.xpath("//*[@id='ui-multiselect-alertTypeObj-option-" + i + "']"));
	int j = i + 1;
	ExtendedWebElement alerttype = findExtendedWebElement(By.xpath("/html/body/div[12]/ul/li[" + j + "]/label/span"));
	String alerttype1 = alerttype.getText();
	setexcel.add(alerttype1);
	if (cvalert.isChecked()) 
	{
	LOGGER.info(alerttype1 + " is checked as expected");
	} 
	else 
	{
	Assert.fail(alerttype1 + "  is not checked");
	}
	}
	for (int z = 1; z < 11; z++) 
	{
	ExtendedWebElement alertOptions = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + z + "]/td[2]"));
	String alerttype = alertOptions.getText();
	setexcel.add(alerttype);
	if (setexcel.contains(alerttype)) {
	LOGGER.info("Valid  alert type  displayed in Alerts for Research View ");
	} else {
	Assert.fail("Invalid Alert types are displayed in Alerts for Research view");
	}}
	} 
catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	}
}
/**
 * This method validates default alert types in customize view 
 * @author rangesh_venkatesan
 * @param s    Sheet Data from Excel
 * @throws IOException
 */


public void customize_view(String s) throws IOException {
	try 
	{
	driver.switchTo().frame("dashboardframe");
	customizeView.click();
	List<String> Sev_list = Arrays.asList("Critical", "High", "Medium", "Low");
	for (int k = 0; k < 4; k++) {
	ExtendedWebElement ewSeverity = findExtendedWebElement(By.id("ui-multiselect-severityObj-option-" + k));
	String xpathforsevlabel = ("//label[@for='ui-multiselect-severityObj-option-" + k + "']//span");
	ExtendedWebElement ew_l_Severity = findExtendedWebElement(By.xpath(xpathforsevlabel));
	String Severity = ew_l_Severity.getText();
	if (Sev_list.contains(Severity)) 
	{
	if(ewSeverity.isChecked()) 
	{
	LOGGER.info(Severity + " is checked as default");
	} 
	else 
	{
	Assert.fail(Severity + "  is not checked");
	}} 
	else 
	{
	LOGGER.info("Severity displayed in UI is not matching with the Default Severity List");
	}}
	
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet(s);
	HashSet<String> setexcel = new HashSet<String>();
	for (int k = 1; k < 14; k++) 
	{
	Row row = sheet.getRow(k);
	for (int j = 6; j < 7; j++) {
	String a = row.getCell(j).getStringCellValue();
	setexcel.add(a);
	}}
	for (int i = 0; i < 24; i++) 
	{
	ExtendedWebElement cvalert = findExtendedWebElement(By.xpath("//*[@id='ui-multiselect-alertTypeObj-option-" + i + "']"));
	int j = i + 1;
	ExtendedWebElement alerttype = findExtendedWebElement(By.xpath("/html/body/div[12]/ul/li[" + j + "]/label/span"));
	String alerttype1 = alerttype.getText();
	if (setexcel.contains(alerttype1)) {
	if (cvalert.isChecked()) 
	{
	LOGGER.info(alerttype1 + " is checked as expected");
	} else 
	{
	Assert.fail(alerttype1 + "  is not checked");
	}} else 
	{
	if (cvalert.isChecked()) {
	Assert.fail(alerttype1 + " is checked");
	}}}
	workbook.close();
	} 
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	}}


/**
 * This Method validates assets in research page
 * @author rangesh_venkatesan
 * @throws IOException
 */
public void verify_Asset() throws IOException {
try 
{
	researchTab.click();
	driver.switchTo().frame("researchframe");
	List<ExtendedWebElement> linkOptions = assetName.findExtendedWebElements(By.tagName("td"));
	int td_Size = linkOptions.size();
	for (int i = 1; i < td_Size; i++)
	{
	ExtendedWebElement a_Name = findExtendedWebElement(By.xpath("//div[@class='ui-widget-header-mod ui-corner-top']//td[" + i + "]//span "));
	a_Name.click();
	String asset_Name = a_Name.getText();
	if(i==4)
	{
		String Fixed[] =asset_Name.split("\\s");
		asset_Name= Fixed[0];
	}

	for (int j = i - 1; j <= 7; j++)
	{
	ExtendedWebElement a_list = findExtendedWebElement(By.xpath("//div[@id='window_" + j + "']//div[@class='window_title_text']"));
	String asset_list_Name = a_list.getText();
	closeButton.click();
	if (asset_list_Name.contains(asset_Name)) 
	{
	LOGGER.info("the " + asset_list_Name + " is matched with  " + asset_Name + " asset");
	} else
	{
	Assert.fail("the " + asset_list_Name + " is Not  matched with  " + asset_Name + " asset");
	}
	break;
	}}
}

catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
}

/**
* This method clicks on  index family group link in indices family tab
  in research frame and validate the list which is opened on clicking the index
* @author Rangesh_TV
* @exception Exception
* @return - no return
*/
public void verify_index_family( ) {
try {
researchTab.click();
driver.switchTo().frame("researchframe");
validate(6,0,1,"TC01043");
validate(40,1,2,"TC01043");
validate(44,2,3,"TC01043");



} catch (Exception e) {
e.printStackTrace();
Assert.fail("" + e.getMessage());
}
}
/**
 * This Method right clicks on Index family
 * @author rangesh_venkatesan
 * @exception Exception
 */

public void rightClick_Family() 
{
	try 
	{
	researchTab.click();
	driver.switchTo().frame("researchframe");
	index_family_icon.click();
	index_family_icon.rightClick();
	}
	catch (Exception e) {
	 e.printStackTrace();
	 Assert.fail("" + e.getMessage());
	}
}

/**
 * This Method verifies options in Process
 * @throws IOException
 * @author rangesh_venkatesan
 * @throws InterruptedException
 */
	

public void Verify_ProcessOptions() throws IOException, InterruptedException 
{
	try{
	HashSet<String> setexcel= new HashSet<String>();
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet("Static_Data");
	for(int k = 1; k <=13; k++) 
	{
	Row row =sheet.getRow(k);
	String a= row.getCell(11).getStringCellValue();
	setexcel.add(a);
	}
	for(int i=1;i<=19;i++)
	{
	if(i!=2 && i!=5 && i!=8 && i!=12 &&i!=15&& i!=16&&i!=17&&i!=18&&i!=20)
	{
	ExtendedWebElement functionality =findExtendedWebElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//li["+i+"]//span"));
    String attrValue = functionality.getAttribute("class");
    if(!attrValue.contains("context-menu-item context-menu-separator not-selectable"))
    {
	String x = functionality.getText();
	{
	if (setexcel.contains(x))
	{
		LOGGER.info ( x +" option is present as expected");
	}
	else
	{
	Assert.fail(x+ " is an unexpected option");}
     }
     }}}
	Compare_T3_process.hover();
	Thread.sleep(5000);
	for(int i=1;i<5;i++)
	{
	ExtendedWebElement T3_options =findExtendedWebElement(By.xpath("//ul[@class='context-menu-list']//li["+i+"]//span"));
	String T3_menu =T3_options.getText();
	Row row =sheet.getRow(i);
	String a= row.getCell(16).getStringCellValue();
	if(a.equalsIgnoreCase(T3_menu))
	{
		LOGGER.info("Option " +a+ " is present in Compare With T3 as expected");
	}
	else
	{
		Assert.fail(a+ " is an unexpected option");}
	}
	Thread.sleep(5000);
	ExtendedWebElement Advanced_options =findExtendedWebElement(By.xpath("(//ul[@class='context-menu-list']//li[1]//span)[2]"));
	Advanced_process.hover();
	String Advanced_menu =Advanced_options.getText();
 	Row row =sheet.getRow(5);
	String a= row.getCell(16).getStringCellValue();
	if(a.equalsIgnoreCase(Advanced_menu))
	{
		LOGGER.info("Option " +a+ " is present in Advanced as expected");
	}
	else
	{
		Assert.fail(a+ " is an unexpected option");
	}
	workbook.close();
	}
catch (Exception e) {
	 e.printStackTrace();
	 Assert.fail("" + e.getMessage());
	}}
/**
 * This Method Downloads alert data and validates Data with UI
 * @throws IOException
 */
public void Download_Data() throws IOException {
try {
	HashSet<String> setexcel = new HashSet<String>();
	HashSet<String> Alert_Data = new HashSet<String>();
	driver.switchTo().frame("dashboardframe");
	Select view = new Select(driver.findElement(By.id("alertViewsList")));
	view.selectByIndex(1);
	//ALert_Count.pause(22);
	String name=System.getProperty("user.name");
	SearchAlert.type(""+name+" logged in");
	SearchAlert.pause(16);
	String Alert = ALert_Count.getText();
	String Al_Head = Alert_Head.getText();
	Alert_Data.add(Al_Head);
	setexcel.add(Al_Head);
	String Alert_Count = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf(")"));
	int Al_count = Integer.parseInt(Alert_Count);
	download.click();
	Download_ALerts.click();
	Download_ALerts.pause(15);
	String Dirpath = System.getProperty("user.home") + "/Downloads";
	File LatestFile = getLatestFilefromDir(Dirpath);
	BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
	int count = countRecord(LatestFile);
	if ((count - 2) == Al_count) {
		LOGGER.info("Alert Count "+Al_count+" Matches with Downloaded Alert Data");

	} else {
		Assert.fail("Alert Count  "+Al_count+"   Not Matches with Downloaded Alert Data");
	}
	for (int i = 1; i < 5; i++) {
	ExtendedWebElement A_Description = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td[6]"));
	String R_Description = A_Description.getText();
	String Description[]=null;
	if(R_Description.contains(","))
	{
		Description = R_Description.split(",");
		for(int j=0;i<=Description.length;i++)
		{
		Alert_Data.add(Description[j]);
	}}
	
	else
	
	Alert_Data.add(R_Description);
	}
	String row = "";
	String[] data = null;
	for (int i = 0; i < 5; i++) {
	if ((row = csvReader.readLine()) != null) {
	data = row.split(",");
	if (!data[6].contains(Al_Head)) {
	setexcel.add(data[6].substring(1, data[6].length()-1));
	}}}
	if (setexcel.equals(Alert_Data)) {
		LOGGER.info("Alert Data in UI  Matches with Downloaded Alert Data");
	}
	else
	{
	Assert.fail("Alert Data in UI not   Matches with Downloaded Alert Data");
	}
	csvReader.close();
}
catch (Exception e) 
	{
 e.printStackTrace();
 Assert.fail("" + e.getMessage());
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
		count++;
	}
	csvReader.close();}
	catch (Exception e) 
	{
	Assert.fail("" + e.getMessage());
	}
	return count;
}

/**
 * This Method verifies options in Index Family
 * @throws IOException
 * @author rangesh_venkatesan
 * @throws InterruptedException
 */
public void Verify_Options() throws IOException, InterruptedException 
{
try {
	HashSet<String> setexcel= new HashSet<String>();
	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet("Static_Data");
	for(int k = 1; k <13; k++) 
	{
	Row row =sheet.getRow(k);
	String a= row.getCell(11).getStringCellValue();
	setexcel.add(a);
	}
	for(int i=1;i<=17;i++)
	{
	if(i!=2 && i!=5 && i!=8 && i!=12 &&i!=15 &&i!=16 && i!=17 &&i!=18)
	{
	ExtendedWebElement functionality =findExtendedWebElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//li["+i+"]//span"));
    String attrValue = functionality.getAttribute("class");
    if(!attrValue.contains("context-menu-item context-menu-separator not-selectable"))
    {
	String x = functionality.getText();
	{
	if (setexcel.contains(x))
	{
	LOGGER.info ( x +" option is present as expected");
	}
	else
   	{
	Assert.fail(x+ " is an unexpected option");}
	}
    }}}
	Compare_T3.hover();
	Thread.sleep(5000);
	for(int i=1;i<5;i++)
	{
	ExtendedWebElement T3_options =findExtendedWebElement(By.xpath("//ul[@class='context-menu-list']//li["+i+"]//span"));
	String T3_menu =T3_options.getText();
	Row row =sheet.getRow(i);
	String a= row.getCell(16).getStringCellValue();
	if(a.equalsIgnoreCase(T3_menu))
	{
		LOGGER.info("Option " +a+ " is present in Compare With T3 as expected");
	}
	else
	{
		Assert.fail(a+ " is an unexpected option");}
	}
	Advanced.hover();
	Thread.sleep(5000);
	ExtendedWebElement Advanced_options =findExtendedWebElement(By.xpath("(//ul[@class='context-menu-list']//li[1]//span)[2]"));
	Advanced_options.hover();
	String Advanced_menu =Advanced_options.getText();
	Row row =sheet.getRow(5);
	String a= row.getCell(16).getStringCellValue();
	 
	if(a.equalsIgnoreCase(Advanced_menu))
	{
		LOGGER.info("Option " +a+ " is present in Advanced as expected");
	}
	else
	{
		Assert.fail(a+ " is an unexpected option");
	}
	workbook.close();}
 catch (Exception e) 
 	{
	 e.printStackTrace();
	 Assert.fail("" + e.getMessage());
 	}
 }


/**
 * This Method validates Index family Data
 * @author rangesh_venkatesan
 * @param column 	Column number of Index family
 * @param Window 	Index List Window
 * @param Testdata 	TestData from Excel
 * @param Testcase 	Test case Id from Excel
 * @throws Exception
 */

public void validate(int column, int Window,int Testdata, String Testcase) throws Exception
{
try {
	ExtendedWebElement index_Name = findExtendedWebElement(By.xpath("//div[@class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active']//li["+column+ "]//a"));
	index_Name.doubleClick(1000);
	String index_family_Name1 = index_Name.getText();
	String[] arrOfStr = index_family_Name1.split("[(]");
	String index_family_Name2 = arrOfStr[0];
	ExtendedWebElement index_family_list = findExtendedWebElement(By.xpath("//div[@id='window_" + Window + "']//div[@class='window_title_text']"));
	String index_list_Name = index_family_list.getText();
	String total = totalCount.getText();
	String[] totalCounts = total.split("\\s");
	closeButton.click(1000);
	String Query = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0,Testcase, "Query"+Testdata+"");
	LOGGER.info(Query);
	if (index_list_Name.contains(index_family_Name2)) {
		LOGGER.info("The Index family " + index_family_Name2 + " Matches in UI");
	} 
	else 
	{
		Assert.fail("The Index family " + index_family_Name2 + " Not Matches in in UI");
	}
	String DbCount=DatawatchSQLStatements.sql_connect(Query, 1);
	if(DbCount.equals(totalCounts[1]))
	{
		LOGGER.info("Count for " + index_family_Name2+ "  Matches With DB");

	}
}
catch (Exception e) {
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}

}

/**
*This method validates Data in Recent Launch
* @author rangesh_venkatesan
* @exception no exception
* @return - no return
*/
public void verify_Recent_Tab_Index_family() {
try {
	researchTab.click();
	driver.switchTo().frame("researchframe");
	recentLaunchTab.click();
	ExtendedWebElement index_Name = findExtendedWebElement(By.xpath("//div[@class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active']//li[1]//a"));
	index_Name.doubleClick(1000);
	String index_family_Name1 = index_Name.getText();
	String total = totalCount.getText();
	String[] totalCounts = total.split("\\s");
	String[] arrOfStr = index_family_Name1.split("[(]");
	String index_family_Name2 = arrOfStr[0];
	ExtendedWebElement index_family_list = findExtendedWebElement(By.xpath("//div[@id='window_0']//div[@class='window_title_text']"));
	String index_list_Name = index_family_list.getText();
	closeButton.click(1000);
	if (index_list_Name.contains(index_family_Name2)) 
	{
		LOGGER.info("Recent Launches  for " + index_family_Name2 + " Matches in UI");
	} else {
		Assert.fail("Recent Launches  for " + index_family_Name2 + " Matches in UI");
	}
	String Date=index_list_Name.substring(index_list_Name.indexOf(":") + 2, index_list_Name.indexOf(")"));	
	String Date1="('"+Date+"')";
	String Data = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0,"TC01049", "Query1");
	String Query = Data.concat(Date1);
	LOGGER.info(Query);
	String DbCount=DatawatchSQLStatements.sql_connect(Query, 1);
	if(DbCount.equals(totalCounts[1]))
	{
	LOGGER.info("Count for " + index_family_Name2 + "  Matches With DB");
	
	}


} 
catch (Exception e) {
e.printStackTrace();
Assert.fail("" + e.getMessage());
}

}

/**
* This method clicks on  Processes link in indices family tab and validates  Process Data
* @author Rangesh_TV
* @exception Exception
* @return - no return
*/
public void verify_Process_Tab_index_family() {
try {
researchTab.click();
driver.switchTo().frame("researchframe");
processLaunchTab.click();
validate(1, 0, 1,"TC01046");
validate(3, 1, 2,"TC01046");
validate(13, 2, 3,"TC01046");

 
}


 catch (Exception e) {
e.printStackTrace();
Assert.fail("" + e.getMessage());
}}

/**
 * This method right click on RecentLaunch Tab
 * @author rangesh_venkatesan
 * @exception Exception
 */

public void rightClick_RecentLaunch() 
{
	try {
	researchTab.click();
	driver.switchTo().frame("researchframe");
	RecentLaunch_icon.click();
	RecentLaunch_Data.click();
	RecentLaunch_Data.rightClick();
}
	catch (Exception e) {
	 e.printStackTrace();
	 Assert.fail("" + e.getMessage());
	 }
}


public void rightClick_Processes() 
{
	researchTab.click();
	driver.switchTo().frame("researchframe");
	processLaunchTab.click();
	RecentLaunch_Data.click();
	RecentLaunch_Data.rightClick();
}



/**
 * This Method validates options in Process Status & System Monitor
 * @author rangesh_venkatesan
 * @param  Process    Alert type
 * @throws IOException
 */
public void verify_other_assets(String Process) throws IOException {
	try {

		driver.switchTo().defaultContent();
		driver.switchTo().frame("dashboardframe");
		Select view = new Select(driver.findElement(By.id("alertViewsList")));
		view.selectByIndex(1);
		customizeView.click();
		uncheckall.click();
		for (int i = 0; i < 20; i++) {
		ExtendedWebElement cvalert = findExtendedWebElement(By.xpath("//*[@id='ui-multiselect-alertTypeObj-option-" + i + "']"));
		int j = i + 1;
		ExtendedWebElement alerttype = findExtendedWebElement(By.xpath("/html/body/div[12]/ul/li[" + j + "]/label/span"));
		String alerttype1 = alerttype.getText();
		if (alerttype1.equalsIgnoreCase(Process)) 
		{
		cvalert.check();
		}}
		customizeViewOk.click();
		/*if(Process.contains("System"))
		{
		Researchpage.Alert_Search.type("");
		}
		Researchpage.Alert_Search.pause(10);*/
		
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}



public void CopyName()
{ 
	String text="";
	ExtendedWebElement Refresh_Frm = findExtendedWebElement(By.xpath("/html/body/ul/li[4]/span"));
	Refresh_Frm.click();
	 text=copyTextarea.getText();
	ExtendedWebElement Copy_msg = findExtendedWebElement(By.xpath("//span[contains(text(),'Copy to Clipboard')]"));
	Copy_msg.click();
	driver.findElement(By.xpath("//input[@aria-controls='alertTable']")).sendKeys(Keys.chord(Keys.CONTROL, "V"));
	String alert_msg = Researchpage.Alert_Search.getAttribute("value");
	if(text.equalsIgnoreCase(alert_msg))
	{

	LOGGER.info("Alert Message Copied to ClipBoard");
	}
	driver.findElement(By.xpath("//input[@aria-controls='alertTable']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
	driver.findElement(By.xpath("//input[@aria-controls='alertTable']")).sendKeys(Keys.chord(Keys.BACK_SPACE));
	Copy_msg.pause(5);
	

}

public void Open_Exchange() {
try {

driver.switchTo().frame("dashboardframe");
ExtendedWebElement Open = findExtendedWebElement(
By.xpath("//div[starts-with(@id,'rightspannedid')]//tbody//tr[2]//td[3]//span"));
Open.click();
driver.switchTo().defaultContent();
driver.switchTo().frame("researchframe");
// R.TESTDATA.put(("testkey","testvalue"));

} catch (Exception e) {
e.printStackTrace();
Assert.fail("" + e.getMessage());
}
}


public void validateOutboundDestinations(String xpathForProcess) throws IOException {

try 
{
	driver.switchTo().frame("dashboardframe");
	ExtendedWebElement tableInput = findExtendedWebElement(By.xpath(xpathForProcess));
	List<ExtendedWebElement> tableRow = tableInput.findExtendedWebElements(By.tagName("tr"));
	for (int i = 1; i <= tableRow.size(); i++) {
	String processText = findExtendedWebElement(By.xpath(xpathForProcess + "/tr[" + i + "]/td[1]/div")).getText();
	ExtendedWebElement ColumnA = findExtendedWebElement(By.xpath(xpathForProcess + "/tr[" + i + "]/td[2]/img"));
	ExtendedWebElement ColumnB = findExtendedWebElement(By.xpath(xpathForProcess + "/tr[" + i + "]/td[3]/img"));
	String Stautus_A = ColumnA.getAttribute("src");
	String Stautus_B = ColumnB.getAttribute("src");
	String[] a1 = Stautus_A.split("/");
	String[] b1 = Stautus_B.split("/");
	if ( a1[6].contains("green") && b1[6].contains("green")) 
	{
	LOGGER.info(" Both Process A and  B for  " + processText + " are  Green");
	}
	else if (a1[6].contains("green")||b1[6].contains("green")) 
	{
	LOGGER.info(" Either Process A and  B for " + processText + " are  Green");
	}
	else if ( a1[6].contains("blue") && b1[6].contains("blue")) 
	{
	LOGGER.info(" Both Process A and  B for  " + processText + " are  Blue");
	}
	else if (a1[6].contains("blue")||b1[6].contains("blue")) 
	{
	LOGGER.info(" Either Process A and  B for " + processText + " are  Blue");
	}
	else if ( a1[6].contains("grey") && b1[6].contains("grey")) 
	{
	LOGGER.info(" Both Process A and  B for  " + processText + " are  grey");
	}
	else if (a1[6].contains("blue")||b1[6].contains("blue")) 
	{
	LOGGER.info(" Either Process A and  B for " + processText + " are  grey");
	}
	else if (a1[6].contains("red") || b1[6].contains("red")) {
	Assert.fail("Either of Process A or B for " + processText + "  is  Red");
	} 

	}
	} catch (Exception e) {
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	}
	}

public  void scrollToDown() {
    try {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // alertSearch.scrollTo();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    } catch (Exception e) {
        e.printStackTrace();
        Assert.fail("Unable to scroll to alert search box-" + e.getMessage());
    }
}


/**
 * This method update Parameter4 value in Edit node in Ticker Monitor 
 * @author rangesh_venkatesan
 * @exception - Exception
 * 
 */

public void updateParam(String value ,int param1)
{
try {
	Status.click();
	driver.switchTo().frame("statusframe");
	Status.pause(10);
	grip.click();
	ticker.click();
	SP1200.click();
	SP1200.rightClick();
	EditNode.click();
	EditNode.pause(4);
	ExtendedWebElement param=findExtendedWebElement(By.xpath("//input[@id='param"+param1+"']"));
	param.type(value);
	Save.click();
	
		
	}
	
catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}

/**
 * This method copies node  as sibling and verify node in dashboard
 * @author rangesh_venkatesan
 * @exception- Exception
 * 
 */



public void copyNode()
{
try {
	Status.click();
	driver.switchTo().frame("statusframe");
	grip.pause(10);
	grip.click();
	ticker.click();
	ticker.pause(5);
	SP1200.click();
	SP1200.rightClick();
	copyNode.click();
	SP1200.click();
	SP1200.rightClick();
	pasteNode.click();
	reloadTree();
	driver.switchTo().defaultContent();
	dashboardTab.click();
	driver.switchTo().frame("dashboardframe");
	if(cpyticker.isElementPresent())
	{
		LOGGER.info("Node copied Successfully and available in dasboaard");
		
	}
	
	else
	{
		Assert.fail("Copied Node not available in Dashboard");
	}
	driver.switchTo().defaultContent();
	Status.click();
	driver.switchTo().frame("statusframe");
	grip.pause(10);
	grip.click();
	ticker.click();
	ticker.pause(5);
	cpySP.click();
	cpySP.rightClick();
	DeleteNode.click();
	deleteConfirm.click();
	driver.switchTo().defaultContent();
	SupportPage.Support_Link.click();
	driver.switchTo().frame("supportframe");
	SupportPage.Execute.click();
	driver.switchTo().defaultContent();
	Status.click();
	driver.switchTo().frame("statusframe");
	refreshtree.click();
	refreshtree.pause(15);
	
	
		
	}
	
catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}

/**
 * This method reloads tree in support tab
 * @author rangesh_venkatesan
 * @exception Exception
 */

public void reloadTree()
{
try {
	driver.switchTo().defaultContent();
	SupportPage.Support_Link.click();
	driver.switchTo().frame("supportframe");
	SupportPage support = new SupportPage(getDriver());
	support.ReloadTree("System Monitor(SM-A)","RELOAD TREE");
	driver.switchTo().defaultContent();
	Status.click();
	driver.switchTo().frame("statusframe");
	refreshtree.click();
	refreshtree.pause(15);
	
	
		
	}
	
catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
}

/**
 * This method validates dot in header of ticker Monitor
 * @author rangesh_venkatesan
 * @exception Exception
 */
public void Navigationbar()
{
	try{
	driver.switchTo().frame("dashboardframe");
	pause.click();
	Slide1.click();
	Slide1.pause(5);
	navIndex.click();
	String nav1=navIndex.getText();
	if(nav1.contains("500"))
	{
		LOGGER.info("Navigation dot1 in header bar of ticker monitor loaded as expected" );
	}
	
	else
	{
		Assert.fail("Navigation dot1 in header bar of ticker monitor not  loaded as expected");
	}
	Slide2.click();
	Slide2.pause(7);
	navIndex2.click();
	String nav2 =navIndex2.getText();
	if(nav2.contains("IGPA"))
	{
		LOGGER.info("Navigation dot2 in header bar of ticker monitor loaded as expected" );
	}
	else
	{
		Assert.fail("Navigation dot2 in header bar of ticker monitor not loaded as expected");
	}
	Slide1.click();
	Slide1.pause(10);
	if(navIndex.isClickable())
	{
		LOGGER.info("Pause button working as Expected" );
	}
	else
	{
		Assert.fail("Pause button not working as Expected");
	}
	pause.click();
	Slide1.pause(7);
	if(navIndex2.isClickable())
	{
		LOGGER.info("Play button working as Expected" );
	}
	else
	{
		Assert.fail("Play button not working as Expected");
	}
	
}
catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
}

/**
 * This method verifies audio generated in GRIP application
 * @author rangesh_venkatesan
 * @exception- Exception
 */

public void verifyAudio()
{
	try {
	driver.switchTo().defaultContent();
	dashboardTab.click();
	driver.switchTo().frame("dashboardframe");
	String Audio=audio.getAttribute("src");
	if(Audio.contains("mp3"))
	{
		LOGGER.info("Audible alert generated Sucessfully");
	}}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
}

/**
 * This method roll back changes in Flatine Indices
 * @author rangesh_venkatesan
 * @exception Exception
 */

public void RollbackFlatline()
{
	try {
	
	driver.switchTo().defaultContent();
	updateParam("120",4);
	driver.switchTo().defaultContent();
	SupportPage.Support_Link.click();
	driver.switchTo().frame("supportframe");
	SupportPage.Execute.click();
	driver.switchTo().defaultContent();
	Status.click();
	driver.switchTo().frame("statusframe");
	refreshtree.click();
	driver.switchTo().defaultContent();
}
catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
	
	
}

/**
 * This method validates flatine indices generated in dashboard
 * @author rangesh_venkatesan
 * @exception Exception
 * 
 */

public void verifyflatlineDashboard()
{
	try {
	Select view = new Select(driver.findElement(By.className("multi_select_select")));	
	view.selectByValue("amber");
	flatlinedot.pause(10);
	String flatlinecolt=flatlinedot.getAttribute("src");
	String Index=sp1200.getText();
	if(flatlinecolt.contains("amber")&& Index.contains("1200"))
	{
		LOGGER.info("Ticker Monitor filtered with flatineindices");
	}}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}

/**
 * This method roll back changes in No ticks Indices
 * @author rangesh_venkatesan
 * @exception Exception
 */


public void RollbackNoticks()
{
	try {
	driver.switchTo().defaultContent();
	updateParam("",3);
	driver.switchTo().defaultContent();
	SupportPage.Support_Link.click();
	driver.switchTo().frame("supportframe");
	SupportPage.Execute.click();
	driver.switchTo().defaultContent();
	Status.click();
	driver.switchTo().frame("statusframe");
	refreshtree.click();
}
	
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
}/**
 * This method validates No ticks indices generated in dashboard
 * @author rangesh_venkatesan
 * @exception Exception
 * 
 */
public void verifyNoticksDashboard()
{
	try {
	Select view = new Select(driver.findElement(By.className("multi_select_select")));	
	view.selectByValue("red");
	String flatlinecolt=flatlinedot.getAttribute("src");
	String Index=sp1200.getText();
	if(flatlinecolt.contains("red")&& Index.contains("1200"))
	{
		LOGGER.info("Ticker Monitor filtered with flatineindices");
	}}
	
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
	
}

/**
 * This method clicks on execute button in SupportAction tab
 * @author rangesh_venkatesan
 * @exception Exception
 */

public void Execute()
{
	try {
	
	driver.switchTo().defaultContent();
	SupportPage.Support_Link.click();
	driver.switchTo().frame("supportframe");
	SupportPage.Execute.click();
}
catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
	
}
/**
 * This method adds reason in alert and  validates reason between UI and DB 
 * @author rangesh_venkatesan
 * @throws IOException
 */

public void AddReason() throws IOException
{try {
	DatawatchSQLStatements.insertReason();
	DatawatchSQLStatements.refreshMview();
	driver.navigate().refresh();
	driver.switchTo().frame("dashboardframe");
	alertSubject.click();
	alertSubject.rightClick();
	addComment.click();
	selectRsn.click();
	HashSet<String> reasons = new HashSet<String>();

	for(int i=8;i<10;i++) {
	ExtendedWebElement reason = findExtendedWebElement(By.xpath("//div[@class='select-items']//div["+i+"]"));
	String reasontext=reason.getText();
	reasons.add(reasontext);
	}
	if(reasons.contains("Test1") || reasons.contains("Test2"))
	{
		LOGGER.info("Reason added successfully");
	}
	
	else
	{
		Assert.fail("Reason not added in UI");
	}
	DatawatchSQLStatements.deleteReason();
	DatawatchSQLStatements.refreshMview();
}
catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
	

}

/**
 * This method adds comment in alert and  validates comments between UI and DB
 * @author rangesh_venkatesan
 * @exception Exception
 */

public void indexAction()
{
	try {
	String oldCommentID=DatawatchSQLStatements.LatestcommentID();
	int oldcount =Integer.parseInt(oldCommentID)+3;
	driver.switchTo().frame("dashboardframe");
	alertSearch.type("manually%held");
	alertSearch.pause(10);
	alertfirst.click();
	alertfirst.rightClick();
	addComment.click();
	selectRsn.click();
	IndexAction.click();
	String testcomment="This is a test comment";
	text.type(testcomment);
	Submit.click();
	Submit.pause(15);
	if(viewcomment.isElementPresent())
	{
		LOGGER.info("Chat icon appeared in UI for Selected Alert");
	}
	
	else
	{
		Assert.fail("Chat icon not  appeared in UI for Selected Alert");
	}
	viewcomment.click();
	String AlerComment=latestaddedcomment.getText();
	String ALID=latestaddedID.getText();
	if(AlerComment.contains(testcomment))
	{
		LOGGER.info("Added comments matches with view Comment window");
	}
	
	else
	{
		Assert.fail("Added comments not  matches with view Comment window");
	}
	String DBcomment=DatawatchSQLStatements.Latestaddedcomment();
	if(DBcomment.equals(testcomment))
	{
		LOGGER.info("Added comments matches with DB");
	}
	
	else
	{
		Assert.fail("Added comments not  matches with DB");
	}
	String DBalID=DatawatchSQLStatements.LatestaddedAlertID();
	if(DBalID.equals(ALID))
	{
		LOGGER.info(" alertID matches with DB");
	}
	
	else
	{
		Assert.fail(" alertID not  matches with DB");
	}
	String newCommentID=DatawatchSQLStatements.LatestcommentID();
	int newcount =Integer.parseInt(newCommentID);
	if(oldcount==newcount)
	{
		LOGGER.info("Comment ID increased by 3");
	}
	else
	{
			Assert.fail("Comment ID not increased by 3");
	}}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
	
	
}

/**This method validates default reason in alert reason drop down
 * @author rangesh_venkatesan
 * @param Row Alert Row
 */

public void defaultreason(int viewId,int Row)
{
	try {
	driver.switchTo().frame("dashboardframe");
	Select view = new Select(driver.findElement(By.id("alertViewsList")));
	view.selectByIndex(viewId);
	if(viewId==0)
		
	{
		customizeView.click();
		processStatus.click();
		customizeViewOk.click();
	}
	String name=System.getProperty("user.name");
	SearchAlert.type(""+name+" logged in");
	alertSearch.pause(10);
	ExtendedWebElement alert=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+Row+"]//td[1]"));
	alert.click();
	alert.rightClick();
	addComment.click();
	String defaultselection=Default.getText();
	String option="--Select Reason--";
	if(option.equals(defaultselection))
	{
		LOGGER.info("Option defaulted with Select Reason");
	}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}
/**
 * This method verify characters left in add comment window
 * @author rangesh_venkatesan
 * @exception Exception
 */

public void verifycharleft(String testcomment)
{
	try {
	selectRsn.click();
	IndexAction.click();
	text.type(testcomment);
	String chlf=charleft.getText();
	String remain[]=chlf.split("/");
	String remainval=remain[0];
	String Maxchar=remain[1];
	int max=Integer.parseInt(Maxchar);
	int maxallowed=4000;
	if(max==maxallowed)
	{
	LOGGER.info("Maximun character in add comment: " +Maxchar);
	}
	else
	{
		Assert.fail("Maxium allowed characters not matched");
	}
	int Charlft=Integer.parseInt(remainval);
	int cmntLength=max-testcomment.length();
	if(Charlft==cmntLength)
	{
		LOGGER.info("Remaining characters matches as excpected");
	}

}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}
/**
 * This method validates comment Icon in alert
 * @author rangesh_venkatesan
 * @exception Exception
 */

public void verifyIcon(int Row,String testcomment)
{
	try {
		
		Submit.click();
		Submit.pause(15);
		ExtendedWebElement View=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+Row+"]//td[6]//img"));
		if(View.isElementPresent())
		{
			LOGGER.info("Chat icon appeared in UI for Selected Alert");
		}
		
		else
		{
			Assert.fail("Chat icon not  appeared in UI for Selected Alert");
		}
		View.click();
		String AlerComment=latestaddedcomment.getText();
		if(AlerComment.contains(testcomment))
		{
			LOGGER.info("Added comments matches with view Comment window");
		}
		
		else
		{
			Assert.fail("Added comments not  matches with view Comment window");
		}
			
		
		
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
}

/**
 * This method adds new comment in add comment window
 * @param Row -ALert Row
 * @param testcomment2 - New comment
 */

public void addComment(int Row,String testcomment2)
{
	try {
	close.click();
	ExtendedWebElement alert=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+Row+"]//td[1]"));
	alert.click();
	alert.rightClick();
	addComment.click();
	text.type(testcomment2);
	Submit.click();
	Submit.pause(15);
	ExtendedWebElement View=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+Row+"]//td[6]//img"));
	View.click();
	String AlerComment=latestaddedcomment.getText();
	if(AlerComment.contains(testcomment2))
	{
		LOGGER.info("Added new comment matches with view Comment window");
	}
	
	else
	{
		Assert.fail("Added new comment not  matches with view Comment window");
	}}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
	

}

/**
 * This method selects multiple rows in alert window
 * @author rangesh_venkatesan
 * @param Row - Alert Row
 * @param viewID - Select view ID
 * 
 */

public void multpleSelect(int Row,int viewID)
{
	try {

	driver.switchTo().frame("dashboardframe");
	Select view = new Select(driver.findElement(By.id("alertViewsList")));
	view.selectByIndex(viewID);
	if(viewID==0)
		
	{
		customizeView.click();
		processStatus.click();
		customizeViewOk.click();
	}
	String name=System.getProperty("user.name");
	SearchAlert.type(""+name+" logged in");
	alertSearch.pause(10);
	int totalrow=Row+3;
	ExtendedWebElement alert=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+Row+"]//td[1]"));
	alert.click();
	Actions shiftClick = new Actions(driver);
	shiftClick.keyDown(Keys.SHIFT).click(driver.findElement(By.xpath("//table[@id='alertTable']//tr["+totalrow+"]//td[1]"))).keyUp(Keys.SHIFT).perform();
	//alert.click();
	alert.rightClick();
	addComment.click();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}



}
/**
 * This method validates multiple comments
 * @param Row- Alert Row
 * @param testcomment- Test comment
 */

public void verifymulcomments(int Row,String testcomment)
{ try {
	Submit.click();
	Submit.pause(15);
	int totalrow=Row+3;
	
	for(int i=Row;i<=totalrow;i++)
	{
	ExtendedWebElement View=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]//img"));
	if(View.isElementPresent())
	{
		LOGGER.info("Comment icon added successfully for alert Row: "+i+"");
		View.click();
		View.pause(2);
		String AlerComment=latestaddedcomment.getText();
		if(AlerComment.contains(testcomment))
		{
			LOGGER.info("Added multiple test comments matches with view Comment window");
		}
		
		else
		{
			Assert.fail("Added multiple test comments not  matches with view Comment window");
		}
		
		close.click();
		
	}
	else
	{
		Assert.fail("Comment icon not added in Alerts");
	}
}}
catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
	
}

/**
 * This method validates filter all function
 * @author rangesh_venkatesan
 * @exception  Exception
 */

public void filterALL()
{
	try {
		
		LOGGER.info("Validating first 10 rows in ALert Window");
		for(int i=1;i<=10;i++)
		{
		Boolean commentPresent =driver.findElements(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]//img")).size()>0;
		if(commentPresent)
		{
		ExtendedWebElement View=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]//img"));
		if(View.isElementPresent()||!View.isElementPresent())
		{
			LOGGER.info("Comment icon available in Row :"+i+"");
		}}
		
		else
		{
			ExtendedWebElement View=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]"));
			if(View.isElementPresent())
			{
				LOGGER.info("Comment icon not  available in Row :"+i+"");
			}
		}
		}
		
	}
	
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
}

/**
 * This method validates filters with comments  function
 * @author rangesh_venkatesan
 * @exception  Exception
 */

public void filterwithComments()
{
	try {
		
		alertFilter.click();
		withComments.click();
		withComments.pause(2);
		
		LOGGER.info("Validating first 4 rows in ALert Window");
		for(int i=1;i<=4;i++)
		{
		ExtendedWebElement View=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]//img"));
		if(View.isElementPresent())
		{
			LOGGER.info("Comment icon available in Row :"+i+"");
		}
		else
		{
			LOGGER.info("Comment icon not  available for in :"+i+"");
		
		}
		
		
	}}
	
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	
}

/**
 * This method validates filter with comments function
 * @author rangesh_venkatesan
 * @exception  Exception
 */

public void filterwithoutComments()
{
	try {
		alertFilter.click();
		withoutComments.click();
		withoutComments.pause(2);
		LOGGER.info("Validating first 10 rows in ALert Window");
		for(int i=1;i<=10;i++)
		{
		Boolean commentPresent =driver.findElements(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]//img")).size()>0;
		if(!commentPresent)
		{
		ExtendedWebElement View=findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]"));
		if(View.isElementPresent())
		{
			LOGGER.info("Comment icon not available in Row :"+i+"");
		}}
		else
		{
			Assert.fail("Comment icon  available for in :"+i+"");
		
		}
		
		
	}}
	
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}}

/**
 * This method validates download count with UI
 * @author rangesh_venkatesan
 * @param viewID  - Select view ID
 * @throws IOException
 */
	
	public void download(int viewID) throws IOException
	{
		try {
		driver.switchTo().frame("dashboardframe");
		String Alert="";
		Select view = new Select(driver.findElement(By.id("alertViewsList")));
		view.selectByIndex(viewID);
		if(viewID==0)
			
		{
			customizeView.click();
			processStatus.click();
			customizeViewOk.click();
		}
		String name=System.getProperty("user.name");
		SearchAlert.type(""+name+" logged in");
		alertSearch.pause(10);
		Alert = ALert_Count.getText();
		String Alert_Count = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf(")"));
		int Al_count = Integer.parseInt(Alert_Count);
		download.click();
		Download_ALerts.click();
		Download_ALerts.pause(15);
		String homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);
		BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
		int count = countRecord(LatestFile);
		if ((count - 2) == Al_count) {
			LOGGER.info("Alert Count Matches with Downloaded Alert Data");

		} else {
			Assert.fail("Alert Count  Not Matches with Downloaded Alert Data");
		}
		csvReader.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
		
	}
	/**
	 * This method validates download data with UI
	 * @param colHead - Column Header ID Download CSV
	 * @param column    Column Header Name
	 * @param ExcolHead Column Header ID UI
	 * @throws IOException
	 */
	
	public void validatedownload(int colHead,String column,int ExcolHead) throws IOException
	{
		try {
		HashSet<String> setexcel = new HashSet<String>();
		HashSet<String> Alert_Data = new HashSet<String>();
		ExtendedWebElement col=findExtendedWebElement(By.xpath("//table[@class='alertDisplay dataTable']//th["+colHead+"]"));
		String Descript = col.getText();
		String Alert = ALert_Count.getText();
		String Alert_Count = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf(")"));
		int Al_count = Integer.parseInt(Alert_Count);
		if(Al_count>5)
		{
			Al_count=5;
		}
		Alert_Data.add(Descript);
		setexcel.add(Descript);
		String homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);
		BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
		for (int i = 1; i < Al_count+1; i++) {
		ExtendedWebElement A_Description = findExtendedWebElement(By.xpath("//tbody[@role='alert']/tr[" + i + "]/td["+colHead+"]"));
		String R_Description = A_Description.getText();
		String Description[]=null;
		if(R_Description.contains(","))
		{
		Description = R_Description.split(",");
		for(int j=0;i<=Description.length;i++)
		{
		Alert_Data.add(Description[j]);
		}}
		else
		Alert_Data.add(R_Description);
		}
		String row = "";
		String[] data = null;
		for (int i = 0; i < Al_count+1; i++) {
		if ((row = csvReader.readLine()) != null) {
		data = row.split(",");
		if (!Descript.contains(data[ExcolHead])) {
		if(colHead==1) {
		String validdata=data[ExcolHead].substring(0, data[ExcolHead].length()-4);
		setexcel.add(validdata);
		}
		else
		{
			setexcel.add(data[ExcolHead].substring(1, data[ExcolHead].length()-1));
		}
		}}}
		if (setexcel.equals(Alert_Data)) {
		LOGGER.info("Alert "+column+" Data in UI  Matches with Downloaded Alert Data");
		}
		else
		{
		Assert.fail("Alert "+column+" Data in UI not   Matches with Downloaded Alert Data");
		csvReader.close();
		}
		}
	
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	}
	/**
	 * This method validates has comment column in download file
	 * @author rangesh_venkatesan
	 * @throws IOException
	 */
	
	public void hascomment() throws IOException
	{
		try {
		LinkedList<String> setexcel = new LinkedList<String>();
		LinkedList<String> Alert_Data = new LinkedList<String>();
		String columnHead="Has Comment";
		setexcel.add(columnHead);
		Alert_Data.add(columnHead);
		String Alert = ALert_Count.getText();
		String Alert_Count = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf(")"));
		int Al_count = Integer.parseInt(Alert_Count);
		if(Al_count>5)
		{
			Al_count=5;
		}
		for(int i=1;i<Al_count+1;i++)
		{
		Boolean commentPresent =driver.findElements(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]//img")).size()>0;
		if(commentPresent)
		{
			Alert_Data.add("Y");
		}
		else
		{
			Alert_Data.add("N");
		}
		}
		download.click();
		Download_ALerts.click();
		Download_ALerts.pause(15);
		String homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);
		BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
		String row = "";
		String[] data = null;
		for (int i = 0; i < 6; i++) {
		if ((row = csvReader.readLine()) != null) {
		data = row.split(",");
		if (!columnHead.contains(data[11])) {
			
		setexcel.add(data[11]);
			}
		}}
		if (setexcel.equals(Alert_Data)) {
			LOGGER.info("Alert "+columnHead+" Data in UI  Matches with Downloaded Alert Data");
		}
		else
		{
		Assert.fail("Alert "+columnHead+" Data in UI not   Matches with Downloaded Alert Data");
		}
		
	
		csvReader.close();
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
	}
	}
	
	
	
	
	
	/** 
	 * This method validates Alert ID with download data
	 * @param TimeHead         column Header in download
	 * @param ExcolHead 	   column ID in UI
	 * @param colHead		   column ID in download
	 * @param CommentTimeExcel set of data in excel
	 * @param CommentTime	   set of data in  DB
	 * @throws IOException
	 */
	public void Valdiatecomnt(String TimeHead,int ExcolHead,int colHead,HashSet<String> CommentTimeExcel,HashSet<String> CommentTime) throws IOException
	{
		
		try {
		String homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);
		BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
		String row = "";
		String[] data = null;
		for (int i = 0; i < 6; i++) {
		if ((row = csvReader.readLine()) != null) {
		data = row.split(",");
		if (!TimeHead.contains(data[ExcolHead])) {
		if(colHead==1) {
		String validdata=data[ExcolHead].substring(0, data[ExcolHead].length()-4);
		CommentTimeExcel.add(validdata);
		}
		else if(colHead==2)
		{
			CommentTimeExcel.add(data[ExcolHead].substring(0, data[ExcolHead].length()));
		}
		else
		{
			CommentTimeExcel.add(data[ExcolHead].substring(1, data[ExcolHead].length()-1));
		}
		}}}
		CommentTimeExcel.add(TimeHead);
		Object[] array = CommentTime.toArray();
		Object[] arrayExcel = CommentTimeExcel.toArray();
		String arrExcel[]=Arrays.asList(arrayExcel).toArray(new String[arrayExcel.length]);
		String arr[]=Arrays.asList(array).toArray(new String[array.length]);
		for (int j = 0; j < arr.length; j++) {
		if (arrExcel[j].equals(arr[j])) {

			if(j==arr.length-1)
			{
				LOGGER.info("Alert ID  matches with downloaded data" );
			}
		}
		else
		{
			Assert.fail("Alert ID not matches with downloaded data ");
		}
		csvReader.close();
		
		}
		
		
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}
	}
	/**
	 * This method validates Alert Comments in UI and download file.
	 * @param viewID  Select view ID
	 * @throws IOException
	 */
	
	public void validateComments(int viewID) throws IOException 
	{
		try {
		driver.switchTo().frame("dashboardframe");
		Select view = new Select(driver.findElement(By.id("alertViewsList")));
		view.selectByIndex(viewID);
		download.click();
		Downloadcomments.pause(10);
		Downloadcomments.click();
		Downloadcomments.pause(15);
		LinkedList<String> Alertcomments = new LinkedList<String>();
		LinkedList<String> AlertcommentsExcel = new LinkedList<String>();
		String Alcmmt="Alert Comment";
		Alertcomments=	DatawatchSQLStatements.commentDesc();
		Alertcomments.add(Alcmmt);
		LinkedList<String> UserName = new LinkedList<String>();
		LinkedList<String> UserNameExcel = new LinkedList<String>();
		String usrNm="User Name";
		UserName=	DatawatchSQLStatements.UserName();
		UserName.add(usrNm);
		LinkedList<String> Alertsite = new LinkedList<String>();
		LinkedList<String> AlertsiteExcel = new LinkedList<String>();
		String site="Site";
		Alertsite=DatawatchSQLStatements.commenTsite();
		Alertsite.add(site);
		Valdiatedesc(Alcmmt, 2, 3, AlertcommentsExcel,Alertcomments);
		Valdiatedesc(usrNm, 3, 4, UserNameExcel,UserName);
		Valdiatedesc(site, 4, 5, AlertsiteExcel, Alertsite);
		
	}
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	}}
	/**
	 * This method validates set of download data  with DB
	 * @param TimeHead         column Header in download
	 * @param ExcolHead 	   column ID in UI
	 * @param colHead		   column ID in download
	 * @param CommentTimeExcel set of data in excel
	 * @param CommentTime	   set of data in  DB
	 * */
	
	public void Valdiatedesc(String TimeHead,int ExcolHead,int colHead,LinkedList<String> CommentTimeExcel,LinkedList<String> CommentTime) throws IOException
	{
		
	String homePath = System.getProperty("user.home") + "/Downloads";
	File LatestFile = getLatestFilefromDir(homePath);
	BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
	String row = "";
	String[] data = null;
	for (int i = 0; i < 6; i++) {
	if ((row = csvReader.readLine()) != null) {
	data = row.split(",");
	if (!TimeHead.contains(data[ExcolHead])) {
	if(colHead==1) {
	String validdata=data[ExcolHead].substring(0, data[ExcolHead].length()-4);
	CommentTimeExcel.add(validdata);
	}
	else if(colHead==2)
	{
		CommentTimeExcel.add(data[ExcolHead].substring(0, data[ExcolHead].length()));
	}
	else
	{
		CommentTimeExcel.add(data[ExcolHead].substring(1, data[ExcolHead].length()-1));
	}
	}}}
	CommentTimeExcel.add(TimeHead);
	Object[] array = CommentTime.toArray();
	Object[] arrayExcel = CommentTimeExcel.toArray();
	String arrExcel[]=Arrays.asList(arrayExcel).toArray(new String[arrayExcel.length]);
	String arr[]=Arrays.asList(array).toArray(new String[array.length]);
	for (int j = 0; j < arr.length; j++) {
	if (arrExcel[j].contains(arr[j])) {
	if(j==arr.length-1)
	{
	LOGGER.info(""+TimeHead+"  matches with downloaded data" );
	}
	}
	else
	{
		Assert.fail(""+TimeHead+"  not matches with downloaded data ");
	}
	}
	csvReader.close();
	
	}
	
	/**
	 * This method validates index warning columns
	 * @author rangesh_venkatesan
	 * @exception-Exceptionn
	 */
	
	public void onWarning()
	{
	try 
	{
		driver.switchTo().frame("dashboardframe");
		Warning.click();
		driver.switchTo().defaultContent();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		Select view = new Select(driver.findElement(By.id("indexViewsList")));
		String selectedText = view.getFirstSelectedOption().getText();
		if(selectedText.contains("On Warning"))
		{
		LOGGER.info("Index list open with "+selectedText+" filter");
		}
		else
		{
		Assert.fail("Index list open with incorrect filter");
		}
		customizeViewlist.click();
		validateDatafields(16,"TC01086","onWarning");
		Cancel.click();
	}
	
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	
}
	
}
	
	/**
	 * This method validates column in Threshold view
	 * @param column 		Total number of columns
	 * @param TestcaseID	TestcaseID
	 * @param viewID 		Select view ID   
	 */
	
	public void Thresholdview(int column,String TestcaseID,String viewID) 
	
	{
		
	try {
			
		Select view = new Select(driver.findElement(By.id(viewID)));
		view.selectByVisibleText("Thresholds View");
		customizeViewlist.click();
		validateDatafields(column,TestcaseID,"Thresholds View");
			
	}
	
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	
}
		
	}
	
	/**
	 * This method validate Warning columns with expected columns
	 * @param column        Total number of columns
	 * @param testdata		Test case ID
	 * @throws Exception
	 */
	
	public void validateDatafields(int column,String testdata,String filter) throws Exception {
		try {
		
		LinkedList<String> columnUI = new LinkedList<String>();
		LinkedList<String> columnExcel = new LinkedList<String>();
		
		for(int i=1;i<column;i++)
		{
		ExtendedWebElement DataFields=findExtendedWebElement(By.xpath("//ul[@id='selectedList']//li["+i+"]"));
		String Data=DataFields.getText();
		columnUI.add(Data);
			
		}
		String Search =excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0,testdata, "TestData1");	
		String Search_Split[]=Search.split(",");
		for(int k=0;k<Search_Split.length;k++)
		{
			columnExcel.add(Search_Split[k]);
		}
		System.out.println(columnUI);
		System.out.println(columnExcel);
		if(columnUI.equals(columnExcel))
		{
			LOGGER.info("Expected "+filter+" columns matches with UI");
		}
		else
		{
			Assert.fail("Expected "+filter+" columns not matches with UI");
		}
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		
	}
		
		
		
	}
	
	/**
	 * This method validates waning columns in stock list
	 * @author rangesh_venkatesan
	 * @exception  Exception
	 */
	public void stockWarning()
	{
	try 
	{
		driver.switchTo().frame("dashboardframe");
		stockWarning.click();
		driver.switchTo().defaultContent();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		Select view = new Select(driver.findElement(By.id("stockViewsList")));
		String selectedText = view.getFirstSelectedOption().getText();
		if(selectedText.contains("On Warning"))
		{
		LOGGER.info("Stock list open with "+selectedText+" filter");
		}
		else
		{
		Assert.fail("Stock list open with incorrect filter");
		}
		customizeViewlist.click();
		validateDatafields(14,"TC01088","onWarning");
		Cancel.click();
	}
	catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		
	}
}
	/**
	 * This method validates columns in contract list
	 * @author 		rangesh_venkatesan
	 * @exception	Exception
	 */
	
	public void contractWarning()
	{
	try 
	{
		driver.switchTo().frame("dashboardframe");
		contractWarning.click();
		driver.switchTo().defaultContent();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		Select view = new Select(driver.findElement(By.id("contractViewsList")));
		String selectedText = view.getFirstSelectedOption().getText();
		if(selectedText.contains("On Warning"))
		{
		LOGGER.info("Contract list open with "+selectedText+" filter");
		}
		else
		{
		Assert.fail("Contract list open with incorrect filter");
		}
		customizeViewlist.click();
		validateDatafields(15,"TC01090","onWarning");
		Cancel.click();
	}
	catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		
	}
}
	
	
	
	/**
	 * This method validates columns in fixedIncome list
	 * @author 		rangesh_venkatesan
	 * @exception	Exception
	 */
	
	public void fixedWarning()
	{
	try 
	{
		driver.switchTo().frame("dashboardframe");
		FIWarning.click();
		driver.switchTo().defaultContent();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		Select view = new Select(driver.findElement(By.id("fiiViewsList")));
		String selectedText = view.getFirstSelectedOption().getText();
		if(selectedText.contains("On Warning"))
		{
		LOGGER.info("Fixed Income list open with "+selectedText+" filter");
		}
		else
		{
		Assert.fail("Fixed Income list open with incorrect filter");
		}
		customizeViewlist.click();
		validateDatafields(15,"TC01092","onWarning");
		Cancel.click();
	}
	catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		
	}}
	
	
	/**
	 * This method validates forex  expected columns With UI
	 * @author rangesh_venkatesan
	 * @exception Exception
	 */
	
	public void forexWarning()
	{
	try 
	{
		driver.switchTo().frame("dashboardframe");
		String count=FxWarning.getText();
		int fxcount=Integer.parseInt(count);
		FxWarning.click();
		driver.switchTo().defaultContent();
		researchTab.click();
		driver.switchTo().frame("researchframe");
		Select view = new Select(driver.findElement(By.id("forexViewsList")));
		String selectedText = view.getFirstSelectedOption().getText();
		if(selectedText.contains("On Warning"))
		{
		LOGGER.info("Forex Income list open with "+selectedText+" filter");
		}
		else
		{
		Assert.fail("Forex Income list open with incorrect filter");
		}
		customizeViewlist.click();
		validateDatafields(20,"TC01094A","onWarning");
	}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		
		}}
	
	/**
	 * This method validates Threshold greater value with DB
	 * @param Up - Up value
	 * @param Down - Down value
	 * @param i - Row Number
	 * @throws IOException
	 */


	public void ThresholdGreater(String Up, String Down,int i) throws IOException
	{
		try {
		researchTab.click();
		driver.switchTo().frame("researchframe");
		research.click();
		exchangesearch.click();
		exchangesearch.type("NASDAQ stock");
		exchangesearch.pause(4);
		exchageRow.rightClick();
		refresht3.click();
		exchageRow.rightClick();
		viewThreshold.click();
		ExtendedWebElement Threshold=findExtendedWebElement(By.xpath("//tbody[@id='viewAutoHoldDtlTblId']//tr["+i+"]//td[2]"));
		String Thresholdup =Threshold.getText();
		ExtendedWebElement Thresholdd=findExtendedWebElement(By.xpath("//tbody[@id='viewAutoHoldDtlTblId']//tr["+i+"]//td[3]"));
		String Thresholddown =Thresholdd.getText();
		if(Thresholdup.equals(Up)&&(Thresholddown.equals(Down)))
		{
			LOGGER.info("Threshold Limit Updated in UI");
		}
		if(i==3) {
		DatawatchSQLStatements.RollbackGreater();
		DatawatchSQLStatements.ThresholdrefreshMview();
			
		}
		Thresholdclose.click();
		exchageRow.rightClick();
		refresht3.click();
	}
	
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	
}}
		
	/**
	 * This method runs Db query for threshold upper values	
	 * @throws IOException
	 */
	public void ThresholdUpdate() throws IOException
	{
		DatawatchSQLStatements.updateThresholdgreater();
		DatawatchSQLStatements.ThresholdrefreshMview();
	}

	/**
	 * This method runs Db query for threshold lower values	
	 * @throws IOException
	 */
	public void ThresholdUpdateLower() throws IOException
	{
		DatawatchSQLStatements.updateThresholdLower();
		DatawatchSQLStatements.ThresholdrefreshMview();
	}
	
	/**
	 * This method validates warning alerts with download file
	 * @author rangesh_venkatesan
	 */


	public void Warningalerts() {
	try {

	driver.switchTo().frame("dashboardframe");
	customizeView.click();
	uncheckall.click();
	warningThreshold.check();
	customizeViewOk.click();
	LOGGER.info("Validating Subject");
	validateWarning(4);
	LOGGER.info("Validating Description");
	validateWarning(6);
	
	
	}
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	
}


}

	/**
	 * This method validates warning alerts with download file
	 * @param i row number
	 */
public void validateWarning(int i)
{
	try {
	HashSet<String> UISubject = new HashSet<String>();
	HashSet<String> DBSubject = new HashSet<String>();
	LOGGER.info("Sample rows Validated :10");
	for (int k = 1; k <= 10; k++) {
	ExtendedWebElement subjectalerts = findExtendedWebElement(By.xpath("//tbody[@role='alert']//tr[" + k + "]//td["+i+"]"));
	String subject = subjectalerts.getText();
	
	UISubject.add(subject);
	}
	if(i==4)
	{
	DBSubject=DatawatchSQLStatements.Warningsubject();
	}
	else
	{
		DBSubject=DatawatchSQLStatements.Warningdescription();
	}
	Object[] arr = UISubject.toArray();
	
	for (int j = 0; j < arr.length; j++) {
	if (DBSubject.contains(arr[j])) {
	if(j==arr.length-1)
	{
		LOGGER.info("Expected alerts Stored in DB with alertype =32");
	}}

	else 
	{
		Assert.fail("Expected alerts not Stored in DB with alertype =32");
	}

}}
	
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	
}

}


/**
 * This method validates downloaded warning alerts with UI
 * @author rangesh_venkatesan
 */
public void downloadWarning()
{
	try {
	
	driver.switchTo().frame("dashboardframe");
	customizeView.click();
	uncheckall.click();
	warningThreshold.check();
	customizeViewOk.click();
	alertSearch.type("current");
	String Desc =alertDescription.getText();
	alertSearch.type(Desc);
	alertSearch.pause(10);
	String Alert = ALert_Count.getText();
	String Alert_Count = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf(")"));
	int Al_count = Integer.parseInt(Alert_Count);
	Download_ALerts.click();
	Download_ALerts.pause(15);
	String homePath = System.getProperty("user.home") + "/Downloads";
	File LatestFile = getLatestFilefromDir(homePath);
	BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
	int count = countRecord(LatestFile);
	if ((count - 2) == Al_count) {
		LOGGER.info("Alert Count Matches with Downloaded Alert Data");

	} else {
		Assert.fail("Alert Count  Not Matches with Downloaded Alert Data");
	}
	csvReader.close();

	
	
	
	}
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	

	
	
}}

public void AlertType() throws IOException
{
	try {
	HashSet<String> setexcel = new HashSet<String>();
	HashSet<String> Alert_Data = new HashSet<String>();
	String columnHead="Alert Type Id";
	setexcel.add(columnHead);
	Alert_Data.add(columnHead);
	Alert_Data.add("32");
	String Alert = ALert_Count.getText();
	String Alert_Count = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf(")"));
	int Al_count = Integer.parseInt(Alert_Count);
	
	String homePath = System.getProperty("user.home") + "/Downloads";
	File LatestFile = getLatestFilefromDir(homePath);
	BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
	String row = "";
	String[] data = null;
	for (int i = 0; i < Al_count+1; i++) {
	if ((row = csvReader.readLine()) != null) {
	data = row.split(",");
	if (!columnHead.contains(data[9])) {
		
	setexcel.add(data[9]);
		}
	}}
	
	if (setexcel.equals(Alert_Data)) {
		LOGGER.info( ""+columnHead+" Data 32  Matches with Downloaded Alert Data");
	}
	else
	{
	Assert.fail(""+columnHead+" Data 32 not   Matches with Downloaded Alert Data");
	}
	

	csvReader.close();
	
}
catch (Exception e) 
{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
}
}

/**
 * This method validates suspect columns with UI
 * @author rangesh_venkatesan
 */

public void suspect()
{
try 
{
	driver.switchTo().frame("dashboardframe");
	suspect.click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame("researchframe");
	Select view = new Select(driver.findElement(By.id("indexViewsList")));
	String selectedText = view.getFirstSelectedOption().getText();
	if(selectedText.contains("Suspect"))
	{
	LOGGER.info("Index list open with "+selectedText+" filter");
	}
	else
	{
	Assert.fail("Index list open with incorrect filter");
	}
	customizeViewlist.click();
	validateDatafields(27,"TC01099","Suspect");
	Cancel.click();
}
catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	
}
}











}
