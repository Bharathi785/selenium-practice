package com.grip.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;
import org.testng.SkipException;

import com.grip.DBconnection.DatawatchSQLStatements;
import com.grip.utils.ExcelUtils;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class SupportPage extends AbstractPage{
	
	Logger LOGGER = Logger.getLogger(SupportPage.class);
	ExcelUtils excel = new ExcelUtils();
	ExcelUtils excel1 = new ExcelUtils();
	Dashboardpage dash = new Dashboardpage(getDriver());
	MasterValidation mas  = new MasterValidation(getDriver());
	
	Researchpage rsrch = new Researchpage(getDriver());
	public SupportPage(WebDriver driver) 
	{
		super(driver);
	
	}
	
	@FindBy(xpath ="//i[@data-bind='text: displayTimer()']")
	public ExtendedWebElement time;
	
	@FindBy(xpath = "//a[@title='Topics']")
	public ExtendedWebElement topics;
	
	@FindBy(xpath = "//table[@id='topics']//tbody")
	public ExtendedWebElement topicsrow;
	
	@FindBy(xpath = "//option[contains(text(),'LOCK ERROR MAIL')][1]")
	public ExtendedWebElement LockMail;

	@FindBy(xpath = "//option[contains(text(),'UNLOCK ERROR MAIL')][1]")
	public ExtendedWebElement unLockMail;

	@FindBy(xpath = "//option[contains(text(),'CHECK SITE ACTIVE')][1]")
	public ExtendedWebElement SiteActive;

	@FindBy(xpath = "//table[@id='outbounddesttable']//tr[24]//td[2]//img")
	public ExtendedWebElement IDSA;

	@FindBy(xpath = "//table[@id='outbounddesttable']//tr[24]//td[3]//img")
	public ExtendedWebElement IDSB;

	@FindBy(xpath = "//a[contains(text(),'ActiveMQ Console')]")
	public ExtendedWebElement activeMQ;

	@FindBy(xpath = "//option[contains(text(),'CHECK ERROR MAIL')][1]")
	public ExtendedWebElement checkErrorMail;

	@FindBy(xpath = "//option[contains(text(),'PASSIVATE SITE')][1]")
	public ExtendedWebElement passivateAll;

	@FindBy(xpath = "//option[contains(text(),'ACTIVATE SITE')][1]")
	public ExtendedWebElement activateAll;

	@FindBy(xpath = "//input[@id='executeSupportActionId']")
	public static ExtendedWebElement Execute;

	@FindBy(xpath = "//span[contains(text(),'Ok')]")
	public ExtendedWebElement Ok;

	@FindBy(xpath = "//span[@data-bind='text: iOpen']")
	private ExtendedWebElement Open;

	@FindBy(xpath = "//table[@id='indexTable1']//tbody//tr[1]//td[1]")
	public ExtendedWebElement index;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[16]//span")
	public ExtendedWebElement Copy_ID;

	@FindBy(xpath = "//button//span[contains(text(),'Confirm')]")
	private ExtendedWebElement Confirm;

	@FindBy(xpath = "//input[@id='passivateAllId']")
	private ExtendedWebElement passivate;

	@FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td[1]/a")
	public ExtendedWebElement Broker1;

	@FindBy(xpath = "//input[@id='activateAllId']")
	private ExtendedWebElement Active;

	@FindBy(xpath = "//textarea[@id='outputId']")
	private ExtendedWebElement OutputText;

	@FindBy(xpath = "//input[@id='routeToRemoteOneCheckbox']")
	private ExtendedWebElement Remote1;

	@FindBy(xpath = "//input[@id='routeToRemoteTwoCheckbox']")
	private ExtendedWebElement Remote2;

	@FindBy(xpath = "/html/body/div[5]/div[11]/div/button[1]/span")
	private ExtendedWebElement Stop_B;

	@FindBy(xpath = "//ul[@id='ui-id-1']//li//a")
	private ExtendedWebElement Sp_Hover;

	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public static ExtendedWebElement CurrentTime;

	@FindBy(xpath = "//a[@id='ui-id-7']")
	public static ExtendedWebElement Support_Link;

	@FindBy(xpath = "//input[@id='stopIndexCalcJobId']")
	private ExtendedWebElement Jobs_Search;

	@FindBy(xpath = "//input[@id='stopIndexCalcJobBtnId']")
	private ExtendedWebElement Stop_Index_Button;

	@FindBy(xpath = "//select[@id='processId']//option[1]")
	public ExtendedWebElement Select_Process;
	
	

	@FindBy(xpath = "//select[@id='actionId']//option[1]")
	public ExtendedWebElement Action;

	@FindBy(xpath = "//option[contains(text(),'Broker-7BK')]")
	public ExtendedWebElement Broker7;
	
	
	@FindBy(xpath = "//option[contains(text(),'EP-A')]")
	public ExtendedWebElement EventProcessor;
	
	@FindBy(xpath = "//option[contains(text(),'EP-B')]")
	public ExtendedWebElement EventProcessorB;

	@FindBy(xpath = "//a[@id='ui-id-3']")
	private ExtendedWebElement Dashboard;

	@FindBy(xpath = "//table[@id='alertTable']")
	private ExtendedWebElement Alert_TABLE;

	@FindBy(xpath = "//div[@id='topdwmiddlerightid']//table//tbody//tr[3]//td[3]//span")
	private ExtendedWebElement No_ticks;

	@FindBy(xpath = "//table[@class='display dataTable']//tbody")
	public static ExtendedWebElement Refresh_Row;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']")
	public ExtendedWebElement Context_List;

	@FindBy(xpath = "//div[@id='activeMQConsoleTableId']")
	public ExtendedWebElement ActiveMQ;
	
	
	public static String Passive_TC_Execution_During_weekday="false";
	
	
	
	public void ValidateSupportPage() {
		try {

			if (Ok.isElementPresent()) {
				Ok.click();
			}
			Support_Link.click();
			driver.switchTo().frame("supportframe");
			Assert.assertTrue(ActiveMQ.isElementPresent());
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}}
	
	/**
	 * This Method holds Job for Index and Verify alerts in UI & DB 
	 * @param 	Sheet     Fetches Test data from excel
	 * @throws 	Exception Exception
	 */
public void Support_click(String Sheet) throws Exception
{
	try {		
		Support_Link.click();
		String currenttime = CurrentTime.getText();
		String Index_IST="3000729";
		String Index_EST="40303009";
		String CR_Date = currenttime.substring(currenttime.indexOf(",") + 2, currenttime.indexOf("U") - 1);
		MasterValidation Time = new MasterValidation(getDriver());
		int CR_TM= Time.getDate(currenttime);
		String Search ="";
		if(CR_TM<93000)
		{
			 Search =Index_IST;
			 
		}
		if(CR_TM>93000)
		{
			Search =Index_EST;
		}
		driver.switchTo().frame("supportframe");
		Jobs_Search.type(Search);
		Sp_Hover.click();
		Stop_Index_Button.click();
		Stop_B.click();
		driver.switchTo().defaultContent();
		rsrch.researchTab.click();
		rsrch.researchTab.pause(60);
		driver.switchTo().frame("researchframe");
        Researchpage.Alert_Search.type("GRIP data");
        Researchpage.Alert_Search.pause(10);
        mas.Verify_UI("No ticks Index - (GRIP)", "Process Status", currenttime, "GRIP data feed did not update ");
        String Description = DatawatchSQLStatements.gripDatafeed(CR_Date, 9);
        if(Description.contains("GRIP data")) {
        	LOGGER.info("Alert Generated in DB");
        }}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}
}
        
    
 public void verify_Alert() throws IOException, InterruptedException
 {
	 	 
	 	Thread.sleep(20000);
	 	FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	 	XSSFWorkbook workbook = new XSSFWorkbook(fis);
        
        XSSFSheet sheet1 = workbook.getSheet("Static_Data");
        Row row1 =sheet1.getRow(1);
        String  Subject_Test=row1.getCell(8).getStringCellValue();
        String Severity_Test=row1.getCell(9).getStringCellValue();
        String Desc=row1.getCell(10).getStringCellValue();
        Boolean a=false;
        
        for(int i=1;i<15;i++) 
        {
        ExtendedWebElement Al_Severity =findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[3]"));
        String T_Severity =Al_Severity.getText();
        ExtendedWebElement Al_Subject =findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[4]"));
        String T_Subject =Al_Subject.getText();
        ExtendedWebElement Al_Description =findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[6]"));
        String T_Description =Al_Description.getText();
       
        
        if(Subject_Test.equalsIgnoreCase(T_Subject)&&Severity_Test.equalsIgnoreCase(T_Severity)&&Desc.equalsIgnoreCase(T_Description))
        {
    	  LOGGER.info("Alert Generated Successfully ");
    	  a= true;
    	  break;
        }
      
        }
        if(!(a)) {
        	 LOGGER.error("Alert Not Generated   ");
        }
	
	
	
	}
 
 /**
  * This Method refresh Data from T3 of Index and validates related alerts in UI & DB
  * @author rangesh_venkatesan
  * @throws Exception
  */
public void refresh_data_T3() throws Exception
 {
	
	
	 Thread.sleep(20000);
	 driver.switchTo().defaultContent();
	 String currenttime = CurrentTime.getText();
	 String CR_Date = currenttime.substring(currenttime.indexOf(",") + 2, currenttime.indexOf("U") - 1);
	 String Index_IST="3000729";
	 String Index_EST="40303009";
	 rsrch.dashboardTab.click();
	 driver.switchTo().frame("dashboardframe");
	 No_ticks.click();
	 int CR_TM= mas.getDate(currenttime);
	 String Data ="";
	 if(CR_TM<93000)
	 {
		 Data =Index_IST;	

	 }
	 if(CR_TM>93000)
	 {
		 Data =Index_EST;
	 }
	
	 driver.switchTo().defaultContent();
	 driver.switchTo().frame("researchframe");
	 rsrch.indexInput.click();
	 rsrch.indexInput.type(Data);
	 index.click();
	 index.rightClick();
	 rsrch.refreshT3.click();
	 rsrch.Ok.click();
	 index.pause(60);
	 Researchpage.Alert_Search.type("No ticks Index (GRIP) - Resolved");
	 Researchpage.Alert_Search.pause(20);;
	 mas.Verify_UI("No ticks Index (GRIP) - Resolved","Process Status" ,currenttime, "ticking normally ");
	 String Description = DatawatchSQLStatements.refreshT3(CR_Date, 9);
	 if(Description.contains("ticking"))
	 {
    	LOGGER.info("Alert Generated in DB");
	 }
}
 
 public  void verify_Context(String Index,int column) throws IOException
 
 {
	 int f=1;
	 if(Index.contains("Process")||Index.contains("System"))
	 {
	 for(int i=1;i<30;i++)
		 
	 {
		 
		 ExtendedWebElement index_Maintanence = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tr["+i+"]//td[2]"));
		 String index_text = index_Maintanence.getText(); 
		 dash.scrollToDown();
		 if(index_text.equalsIgnoreCase(Index)){
			 index_Maintanence.rightClick();
			 break;
		 }
	 
	 }
	 }
	 FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
	 XSSFWorkbook workbook = new XSSFWorkbook(fis);
     XSSFSheet sheet1 = workbook.getSheet("Static_Data");
     List<ExtendedWebElement> linkOptions =Context_List.findExtendedWebElements(By.tagName("li"));
     int Li_Size= linkOptions.size();
     int rowcount = sheet1.getLastRowNum()+1;
     for(int i=1;i<Li_Size;i++) 
     {
	 ExtendedWebElement list_value = findExtendedWebElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//li["+i+"] "));
	 if(i!=2&&i!=5) {
	 ExtendedWebElement list_text = findExtendedWebElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//li["+i+"]//span"));
	 String list_txt = list_text.getText();
	 String attrValue = list_value.getAttribute("class");
	 outer: for(int k = f; k < rowcount; k++) 
	 {
	 Row row =sheet1.getRow(k);
	 for(int j = column; j <column+1; j++) 
	 {

    String a=row.getCell(j).getStringCellValue();
   
    if(a.equalsIgnoreCase(list_txt) &&!attrValue.contains("disabled")&&!attrValue.contains("context-menu-item context-menu-separator not-selectable"))
    {
    	LOGGER.info (""+list_txt+ " is enabled for "  +Index+"");
    	
    	 
    	 
    }    
    else  if(a.equalsIgnoreCase(list_txt) &&attrValue.contains("disabled")&&!attrValue.contains("context-menu-item context-menu-separator not-selectable"))
    {
    	Assert.fail(""+list_txt+ " is not enabled "  +Index+"");
    
    	
    	
    }
    else if(!a.equalsIgnoreCase(list_txt) &&attrValue.equals("context-menu-item")&&!attrValue.contains("context-menu-item context-menu-separator not-selectable"))
    {
    	Assert.fail("Incorrect "+list_txt+ " is enabled "+Index+"");
 
    	
    
    }
    else 
    {
    	LOGGER.info (""+list_txt+ " is not enabled"+Index+"");

    	
    	
    }

    }
	break   outer;
			
    }
	 f=f+1;
    	
    	 
 }}}
 
 
 /**
  * This method Select Handler for processes and verify all available support actions.
  * @param Process1  - ProcessA
  * @param Process2  - ProcessB	
  * @throws Exception
  */
 public void Verify_Handler(String Process1, String Process2) throws Exception {
	 try {

		driver.switchTo().frame("supportframe");
		String tSearch = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 2, 0, "TC019017,18,19", "Query1");
		Handler(tSearch, Process1, Process2);
	 }
	 catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}

	}
 
 /**
  * This method  verify all available support actions.
  * @param tSearch  - DB Query
  * @param Process1 -ProcessA
  * @param Process2 -ProcessB
  * @throws InterruptedException
  * @throws FileNotFoundException
  */
 public void Handler(String tSearch, String Process1, String Process2) throws InterruptedException, FileNotFoundException {
	 try {
		Actions action = new Actions(driver);
		Select_Process.click();
		outer: for (int i = 1; i < 124; i++) {
		ExtendedWebElement processes = findExtendedWebElement(By.xpath("//select[@id='processId']//option[" + i + "]"));
		String Process = processes.getText();
		if (Process.contains(Process1)) {
		Thread.sleep(5000);
		ExtendedWebElement Action_count = findExtendedWebElement(By.xpath("//select[@id='actionId']"));
		List<ExtendedWebElement> tableRows = Action_count.findExtendedWebElements(By.tagName("option"));
		int Rowsize = tableRows.size();
		String Process_Data = "('" + Process1 + "')";
		if (Process1.equalsIgnoreCase("Broker-1")) {
		Process_Data = "('BRK-1')";
		}
		if (Process1.equalsIgnoreCase("Broker-7")) {
		Process_Data = "('BRK-7')";
		}
		String Query = tSearch.concat(Process_Data);
		LOGGER.info(Query);
		String DBcount = DatawatchSQLStatements.sql_connect(Query, 1);
		LOGGER.info(DBcount);
		LOGGER.info("Database count: "+DBcount);
		LOGGER.info("UI count: "+Rowsize);
		int Dbcount = Integer.parseInt(DBcount);
		if (Dbcount == Rowsize) {
		LOGGER.info("Select Action Data for " + Process1 + " Matches with DB");
		} else {
		Assert.fail("Select Action Data for " + Process1 + " Not Matches with DB");
		}
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		int j = i + 1;
		processes = findExtendedWebElement(By.xpath("//select[@id='processId']//option[" + j + "]"));
		String Process3 = processes.getText();
		if (Process3.contains(Process2)) {
		ExtendedWebElement Action_count1 = findExtendedWebElement(By.xpath("//select[@id='actionId']"));
		List<ExtendedWebElement> tableRows1 = Action_count1.findExtendedWebElements(By.tagName("option"));
		String Process_Data1 = "('" + Process2 + "')";
		if (Process2.equalsIgnoreCase("Broker-1BK")) {
			Process_Data1 = "('BRK-1BK')";
		}
		if (Process2.equalsIgnoreCase("Broker-7BK")) {
			Process_Data1 = "('BRK-7BK')";
		}
		int Rowsize2 = tableRows1.size();
		String Query1 = tSearch.concat(Process_Data1);
		LOGGER.info(Query1);
		String DBcount1 = DatawatchSQLStatements.sql_connect(Query1, 1);
		LOGGER.info("Database count: "+Dbcount);
		LOGGER.info("UI count: "+Rowsize2);
		int Dbcount1 = Integer.parseInt(DBcount1);
		if (Dbcount1 == Rowsize2) {
			LOGGER.info("Select Action Data for " + Process2 + " Matches with DB");
		} else {
		Assert.fail("Select Action Data for " + Process2 + " Not Matches with DB");
		}}
		break outer;
		}
		action.sendKeys(Keys.ARROW_DOWN).build().perform();

		}}
	 catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}

	}
	
	/**
	 * This Method clicks on passivateAll link and validates output.
	 *@author 	 rangesh_venkatesan
	 *@exception Exception
	*/

	public void RoutePassiveQA() 
	{
		try {
			
		String Time =time.getText();
		String Day = Time.substring(0, 3);
		if((Day.equalsIgnoreCase("Sat")||Day.equalsIgnoreCase("Sun"))||(Passive_TC_Execution_During_weekday.equalsIgnoreCase("true"))) {
		driver.switchTo().frame("supportframe");
		passivate.click();
		check();

		}
		else
		{
			LOGGER.warn("current day is not weekend");
		}
		
		}
		
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}
	}
	/**
	 * This Method uncheck disable route to QA and Verify output for brokers & all process.
	 * @param statusTrue  -Checks output for All process
	 * @param statusFalse -Checks output for Brokers 
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public void passivateVerify(String statusTrue, String statusFalse) throws InterruptedException, IOException {
		try {
			
		driver.switchTo().defaultContent();
		String Time =time.getText();
		String Day = Time.substring(0, 3);
		if((Day.equalsIgnoreCase("Sat")||Day.equalsIgnoreCase("Sun"))||(Passive_TC_Execution_During_weekday.equalsIgnoreCase("true")))
		{
		driver.switchTo().frame("supportframe");
		Remote1.click();
		Remote2.click();
		Confirm.click();
		Confirm.pause(10);
		driver.switchTo().defaultContent();
		Ok.click();
		Confirm.pause(15);
		driver.switchTo().frame("supportframe");
		String Output = OutputText.getAttribute("value");
		FileWriter fw = new FileWriter("./src/test/resources/Utils/Output.txt");
		fw.write(Output);
		fw.close();
		Verifyuncheck(statusTrue, statusFalse);
		}
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}
		
		

	}
	/**
	 * This method Passivate All process and Verify Routing to ActiveQA is checked
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public void RouteActiveeQA() throws InterruptedException, IOException {
		driver.switchTo().frame("supportframe");
		Active.click();
		check();

	}
	/**
	 * This Method get output value and validates QA routing.
	 * @author rangesh_venkatesan
	 * @param statusTrue - Process status
	 * @param statusFalse - Brokers Status
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public void Verify(String statusTrue, String statusFalse) throws InterruptedException, IOException {
		
		driver.switchTo().defaultContent();
		String Time =time.getText();
		String Day = Time.substring(0, 3);
		if((Day.equalsIgnoreCase("Sat")||Day.equalsIgnoreCase("Sun"))||(Passive_TC_Execution_During_weekday.equalsIgnoreCase("true")))
		{
		driver.switchTo().frame("supportframe");
		Confirm.click();
		Confirm.pause(10);
		driver.switchTo().defaultContent();
		Ok.click();
		driver.switchTo().frame("supportframe");
		String Output = OutputText.getAttribute("value");
		FileWriter fw = new FileWriter("./src/test/resources/Utils/Output.txt");
		fw.write(Output);
		fw.close();
		VerifyOutput(statusTrue, statusFalse);

	}
		}
	
	/**
	 * This method Uncheck Enable route to QA and verify all Sites set to true
	 * @author rangesh_venkatesan
	 * @param statusTrue  -Process Status
	 * @param statusFalse -Broker Status
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public void ActivateVerify(String statusTrue, String statusFalse) throws InterruptedException, IOException {
		Remote1.click();
		Remote2.click();
		Confirm.click();
		Thread.sleep(15000);
		String Output = OutputText.getAttribute("value");
		FileWriter fw = new FileWriter("./src/test/resources/Utils/Output.txt");
		fw.write(Output);
		fw.close();
		Verifyuncheck(statusTrue, statusFalse);

	}

	public void VerifyOutput(String statusTrue, String statusFalse) throws IOException {
		HashSet<String> SupportProcess = new HashSet<String>();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Static_Data");
		String a = "";
		for (int k = 1; k <= 124; k++) 
		{
		Row row = sheet.getRow(k);
		for (int j = 17; j < 18; j++) {
		a = row.getCell(j).getStringCellValue();
		SupportProcess.add(a);

		}
		}
		Object[] array = SupportProcess.toArray();

		File file = new File("./src/test/resources/Utils/Output.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (scanner != null) {
		String line;
		String Remote1;
		String SiteActivate;
		String Remote2;
		while (scanner.hasNextLine()) {
		line = scanner.nextLine();
		String Words[] = line.split(":");
		for (int j = 0; j < SupportProcess.size(); j++) {
		for (int i = 0; i < Words.length; i++) {
		if (Words[i].equalsIgnoreCase((String) array[j])) {
		SiteActivate = scanner.nextLine();
		Remote1 = scanner.nextLine();
		Remote2 = scanner.nextLine();
		if (!Words[i].contains("Broker") && SiteActivate.contains(statusTrue)) {
		LOGGER.info(array[j]);
		LOGGER.info(SiteActivate + "\n");
		} else if (!Words[i].contains("Broker") && SiteActivate.contains(statusFalse)) {
		LOGGER.info("Site for Process " + array[j] + "set to " + statusFalse + "");
		}					
		if (Words[i].contains("Broker") && SiteActivate.contains(statusTrue)
		&& Remote1.contains(statusTrue) && Remote2.contains(statusTrue)) {
		LOGGER.info(array[j]);
		LOGGER.info(SiteActivate);
		LOGGER.info(Remote1);
		LOGGER.info(Remote2 + "\n");

		}

		else if (Words[i].contains("Broker") && SiteActivate.contains(statusFalse)
		&& Remote1.contains(statusFalse) && Remote2.contains(statusFalse)) {
		Assert.fail("QA Routing Set to " + statusFalse + " for " + array[j]);
		}}}}}}
		
		}
	/**
	 * This method validates output of Process & Brokers
	 * @author rangesh_venkatesan
	 * @param statusTrue  - Status of process
	 * @param statusFalse - Status of Brokers
	 * @throws IOException
	 */

	public void Verifyuncheck(String statusTrue, String statusFalse) throws IOException 
	{
		HashSet<String> SupportProcess = new HashSet<String>();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Static_Data");
		String a = "";
		for (int k = 1; k <= 124; k++)
		{
		Row row = sheet.getRow(k);
		for (int j = 17; j < 18; j++) 
		{
		a = row.getCell(j).getStringCellValue();
		SupportProcess.add(a);
		}}
		Object[] array = SupportProcess.toArray();
		File file = new File("./src/test/resources/Utils/Output.txt");
		Scanner scanner = null;
		try 
		{
		scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (scanner != null) {
		String line;
		String Remote1;
		String SiteActivate;
		String Remote2;
		while (scanner.hasNextLine()) {
		line = scanner.nextLine();
		String Words[] = line.split(":");
		for (int j = 0; j < SupportProcess.size(); j++) {
		for (int i = 0; i < Words.length; i++) {
		if (Words[i].equalsIgnoreCase((String) array[j])) {
		SiteActivate = scanner.nextLine();
		Remote1 = scanner.nextLine();
		Remote2 = scanner.nextLine();
		if (!Words[i].contains("Broker") && SiteActivate.contains(statusTrue)) 
		{
		LOGGER.info(array[j]);
		LOGGER.info(SiteActivate + "\n");
		} 
		else if (!Words[i].contains("Broker") && SiteActivate.contains(statusFalse)) 
		{
		LOGGER.info("Site for Process " + array[j] + "set to " + statusFalse + "");
		}
		if (Words[i].contains("Broker") && SiteActivate.contains(statusTrue)
			&& Remote1.contains(statusFalse) && Remote2.contains(statusFalse)) 
		{
		LOGGER.info(array[j]);
		LOGGER.info(SiteActivate);
		LOGGER.info(Remote1);
		LOGGER.info(Remote2 + "\n");
		}
		else if (Words[i].contains("Broker") && SiteActivate.contains(statusTrue)
		&& Remote1.contains(statusTrue) && Remote2.contains(statusTrue)) 
		{
		Assert.fail("QA Routing Set to " + statusTrue + "for " + array[j]);
		}}}}}}
	}
/**
 * This method checks remote to QA is checked or not
 * @author rangesh_venkatesan
 * @exception Exception
 */
public void check() 
	{
		
	try {
		if (Remote1.isChecked()) {
			LOGGER.info("Route to Remote One is Checked");
		} else {
			Assert.fail("Route to Remote One is not Checked");

		}

		if (Remote2.isChecked()) {
			LOGGER.info("Route to Remote Two is Checked");
		} else {
			Assert.fail("Route to Remote Two is not Checked");

		}}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}
	}
/**
 * This method stops QA routing for Broker1
 * @author rangesh_venkatesan
 * @param Process1		- Broker1
 * @param SelectAction  - Stop QA routing
 * @throws InterruptedException
 */

	public void Rollback(String Process1, String SelectAction) throws InterruptedException 
	{
	try {
		
	driver.switchTo().defaultContent();
	String Time =time.getText();
	String Day = Time.substring(0, 3);
	if((Day.equalsIgnoreCase("Sat")||Day.equalsIgnoreCase("Sun"))||(Passive_TC_Execution_During_weekday.equalsIgnoreCase("true"))) 
	{
	driver.switchTo().frame("supportframe");
	
	EventProcessor.click();
	EventProcessor.pause(5);
	driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
	activateAll.click();
	Execute.click();
	//	StopQARouting(Process1,SelectAction);
	}
	}
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	}}
	/*Execute.pause(5);
	Broker7.click();
	Broker7.pause(5);*/
	public void StopQARouting(String Process1, String SelectAction)
	{
	try {
	Actions action = new Actions(driver);
	driver.navigate().refresh();
	Support_Link.click();
	driver.switchTo().frame("supportframe");
	EventProcessor.click();
	outer: for (int i = 1; i < 124; i++) {
	ExtendedWebElement processes = findExtendedWebElement(By.xpath("//select[@id='processId']//option[" + i + "]"));
	String Process = processes.getText();
	if (Process.equalsIgnoreCase(Process1)) 
	{
	Thread.sleep(5000);
	break outer;
	}
	action.sendKeys(Keys.ARROW_DOWN).build().perform();
	}
	action.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_DOWN).build().perform();
	Action.click();
	outer1: for (int i = 1; i < 14; i++) {
	ExtendedWebElement processes = findExtendedWebElement(By.xpath("//select[@id='actionId']//option[" + i + "]"));
	String Process = processes.getText();
	if (Process.equalsIgnoreCase(SelectAction)) 
	{
	Thread.sleep(5000);
	break outer1;
	}
	action.sendKeys(Keys.ARROW_DOWN).build().perform();
	action.keyUp(Keys.SHIFT).build().perform();
	}
	Execute.click();
	//driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME));
	//Execute.pause(4);
	//action.keyUp(Keys.CONTROL).build().perform();
	}
	catch (Exception e) 
	{
	e.printStackTrace();
	Assert.fail("" + e.getMessage());
	}
	}
	/**
	 * This method selects all process and selects lock error Mail.
	 * @author rangesh_venkatesan
	 * @throws IOException
	 */

	public void lockError() throws IOException {
		
		try {
		driver.switchTo().frame("supportframe");
		Select_Process.click();
		driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		LockMail.click();
		Execute.click();
		Execute.pause(80);
		output();
		String Action = "Lock Error for process ";
		String Status = "true";
		checkAllProcess(Action, Status);
		unLockMail.click();
		Execute.click();
		}
		
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}

	}
	
	/**
	 * This method selects all process and selects unlock error Mail.
	 * @author rangesh_venkatesan
	 * @throws IOException
	 */


	public void unlockError() throws IOException {
		driver.switchTo().frame("supportframe");
		Select_Process.click();
		driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		unLockMail.click();
		Execute.click();
		Execute.pause(80);
		output();
		String Action = "Unlock Error for process ";
		String Status = "true";
		checkAllProcess(Action, Status);

	}
/**
 * This method selects all process and selects Site active.
 * @throws IOException
 */
	public void siteActive() throws IOException {
		try {
		driver.switchTo().frame("supportframe");
		Select_Process.click();
		driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		SiteActive.click();
		Execute.click();
		Execute.pause(80);
		output();
		String Action = "Site Active";
		String Status = "true";
		checkAllProcess(Action, Status);
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}

	}
	/**
	 * This method selects all process and selects check error.
	 * @author rangesh_venkatesan
	 * @throws IOException
	 */
	

	public void checkError() throws IOException {
		
		try {
		driver.switchTo().frame("supportframe");
		Select_Process.click();
		driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		checkErrorMail.click();
		Execute.click();
		Execute.pause(80);
		output();
		String Status = "true";
		checkErrorMail(Status);
		}
		
		 catch (Exception e) 
			{
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
			}

	}
	/**
	 * This method select all process and select passivate all .
	 * @author rangesh_venkatesan
	 * @throws IOException
	 */

	public void passivateAll() throws IOException {
		
		String Time =time.getText();
		String Day = Time.substring(0, 3);
		if((Day.equalsIgnoreCase("Sat")||Day.equalsIgnoreCase("Sun"))||(Passive_TC_Execution_During_weekday.equalsIgnoreCase("true")))
		{
		driver.switchTo().frame("supportframe");
		Select_Process.click();
		driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		passivateAll.click();
		Execute.click();
		Execute.pause(30);
		driver.switchTo().defaultContent();
		Ok.click();
		driver.switchTo().frame("supportframe");
		output();
		String Status = "false";
		String passivate = "Passivate site";
		checkAllProcess(passivate, Status);
		

	}
	else
	{
		LOGGER.warn(" Current day is not weekend");
	}
		}
	
	/**
	 * This method select all process and select Activate all .
	 * @author rangesh_venkatesan
	 * @throws IOException
	 */

	public void ActivateAll() throws IOException {
		driver.switchTo().frame("supportframe");
		Select_Process.click();
		driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		activateAll.click();
		Execute.click();
		Execute.pause(80);
		output();
		String Status = "true";
		String Activate = "Activate site";
		checkAllProcess(Activate, Status);
		
		
		

	}

	public void output() throws IOException {

		String Output = OutputText.getAttribute("value");
		FileWriter fw = new FileWriter("./src/test/resources/Utils/Output.txt");
		fw.write(Output);
		fw.close();

	}
	/**
	 * This method checks all process and verify status of all process.
	 * @param Action
	 * @param statusTrue
	 * @throws IOException
	 */

	public void checkAllProcess(String Action, String statusTrue) throws IOException {
		
	try {
		HashSet<String> SupportProcess = new HashSet<String>();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Static_Data");
		String a = "";
		for (int k = 1; k <= 124; k++) {
		Row row = sheet.getRow(k);
		for (int j = 17; j < 18; j++) {
		a = row.getCell(j).getStringCellValue();
		SupportProcess.add(a);
		}}
		Object[] array = SupportProcess.toArray();
		File file = new File("./src/test/resources/Utils/Output.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (scanner != null) {
		String line = "";
		String SiteActivate = "";
		while (scanner.hasNextLine()) {
		line = scanner.nextLine();
		String Words[] = line.split(":");
		for (int j = 0; j < SupportProcess.size(); j++) {
		for (int i = 0; i < Words.length; i++) {
		if (Words[i].equalsIgnoreCase((String) array[j])) {
		SiteActivate = scanner.nextLine();
		if (SiteActivate.contains(statusTrue)) {
		LOGGER.info("" + Action + " for process " + array[j] + " set to  " + statusTrue + "");
		} else {
		Assert.fail("" + Action + " for process " + array[j] + " not  set to " + statusTrue + "");
		}}}}}}}
		
		 catch (Exception e) 
			{
			e.printStackTrace();
			Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
								
	}}
	
	
	
	/**
	 * This Method validates status of check error mail
	 * @param statusTrue   - Status true
	 * @throws IOException
	 */

	public void checkErrorMail(String statusTrue) throws IOException {
		try {
		HashSet<String> SupportProcess = new HashSet<String>();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Static_Data");
		String a = "";
		for (int k = 1; k <= 124; k++) {
		Row row = sheet.getRow(k);
		for (int j = 17; j < 18; j++) {
		a = row.getCell(j).getStringCellValue();
		SupportProcess.add(a);
		}}
		Object[] array = SupportProcess.toArray();
		File file = new File("./src/test/resources/Utils/Output.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (scanner != null) {
		String line = "";
		String AutoErrormail = "";
		String ManualErrorMail = "";
		while (scanner.hasNextLine()) {
		line = scanner.nextLine();
		String Words[] = line.split(":");
		for (int j = 0; j < SupportProcess.size(); j++) {
		for (int i = 0; i < Words.length; i++) {
		if (Words[i].equalsIgnoreCase((String) array[j])) {
		AutoErrormail = scanner.nextLine();
		ManualErrorMail = scanner.nextLine();
		if (AutoErrormail.contains(statusTrue)) {
		LOGGER.info("AutoErrormail for process " + array[j] + " set to  true");
		} else {
		Assert.fail("AutoErrormail for process " + array[j] + " not  set to  true");
		}
		if (ManualErrorMail.contains(statusTrue)) {
		LOGGER.info("AutoErrormail for process" + array[j] + " set to  true");
		} else {
		Assert.fail("ManualErrorMail for process " + array[j] + " not  set to  true");
		}}}}}}		}
		 catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
	}
	
	
	/**
	 * This method selects IDS process and Activates sites
	 * @throws InterruptedException
	 * @throws IOException
	 */
	

	public void handlerActive() throws InterruptedException, IOException 
	{
		try {
		driver.switchTo().frame("supportframe");
		Actions action = new Actions(driver);
		Select_Process.click();
		String handler = "IDS Contribution(CHIDS-A)";
		outer: for (int i = 1; i < 124; i++) {
		ExtendedWebElement processes = findExtendedWebElement(By.xpath("//select[@id='processId']//option[" + i + "]"));
		String Process = processes.getText();
		if (Process.equalsIgnoreCase(handler)) {

		break outer;
		}
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
		action.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_DOWN).build().perform();
		activateAll.click();
		String status = "true";
		String Action = "Activated site ";
		execute(Action, status);
		activateAll.pause(20);
		action.keyUp(Keys.SHIFT).build().perform();
		}
		catch (Exception e)
		{
		e.printStackTrace();
		Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}

	}
	
	/**
	 * This method selects IDS process and click on passivate site.
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public void handlerPassive() throws InterruptedException, IOException {
		try {
		driver.switchTo().frame("supportframe");
		Actions action = new Actions(driver);
		Select_Process.click();
		String handler = "IDS Contribution(CHIDS-A)";
		outer: for (int i = 1; i < 124; i++) {
		ExtendedWebElement processes = findExtendedWebElement(By.xpath("//select[@id='processId']//option[" + i + "]"));
		String Process = processes.getText();
		if (Process.equalsIgnoreCase(handler)) 
		{
			break outer;
		}
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
		action.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_DOWN).build().perform();
		passivateAll.click();
		String status = "false";
		String Action = "Passivated site ";
		execute(Action, status);
		passivateAll.pause(15);
		action.keyUp(Keys.SHIFT).build().perform();
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}

	}
/**
 * This Method clicks on execute in support action tab.
 * @param Action  - Checks status to be true
 * @param status  - Activated site
 * @throws IOException
 */
	public void execute(String Action, String status) throws IOException {
		Execute.click();
		output();
		activate(Action, status);

	}
	/**
	 * This method validates status f IDS process
	 * @param Action    - check status to be true/false
	 * @param statusTrue -Activated site
	 */

	public void activate(String Action, String statusTrue) {
		try
		{
		HashSet<String> SupportProcess = new HashSet<String>();
		String IDSA = "CHIDS-A";
		String IDSB = "CHIDS-B";
		SupportProcess.add(IDSA);
		SupportProcess.add(IDSB);
		Object[] array = SupportProcess.toArray();
		File file = new File("./src/test/resources/Utils/Output.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		if (scanner != null) {
		String line = "";
		String SiteActivate = "";
		while (scanner.hasNextLine()) 
		{
		line = scanner.nextLine();
		String Words[] = line.split(":");
		for (int j = 0; j < SupportProcess.size(); j++) {
		for (int i = 0; i < Words.length; i++) {
		if (Words[i].equalsIgnoreCase((String) array[j])) 
		
		{
		SiteActivate = scanner.nextLine();
		if (SiteActivate.contains(statusTrue)) 
		{
		LOGGER.info("" + Action + " for process " + array[j] + "   set to " + statusTrue + "");
		} else 
		{
		Assert.fail("" + Action + " for process " + array[j] + " not  set to " + statusTrue + "");
							
		}}}}}}
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}
		
	}
	
	/**
	 * This Method validates color of IDS in UI for passivated site.
	 * @author rangesh_venkatesan
	 * @exception - Exception
	 */

	public void verifyHandlerpassive() {
		driver.switchTo().defaultContent();
		dash.dashboardTab.click();
		driver.switchTo().frame("dashboardframe");
		String ProcessA = IDSA.getAttribute("src");
		String ProcessB = IDSB.getAttribute("src");
		if (ProcessA.contains("green") && ProcessB.contains("green")) {
			LOGGER.info("ProcessA & Process B are in green state");
		}

		else {
			Assert.fail("ProcessA & Process B not in green state");
		}

	}
	/**
	 * This Method validates color of IDS in UI for activated site.
	 * @author rangesh_venkatesan
	 * @exception - Exception
	 */

	public void verifyHandlerActive() {
		
		try {
		driver.switchTo().defaultContent();
		dash.dashboardTab.click();
		driver.switchTo().frame("dashboardframe");
		String ProcessA = IDSA.getAttribute("src");
		String ProcessB = IDSB.getAttribute("src");
		if (ProcessA.contains("blue") || ProcessB.contains("blue")) {
			LOGGER.info("Either ProcessA or  Process B are in green  state");
		}

		else {
			Assert.fail("Either ProcessA or  Process B not in green state");
		}}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("Unable to validate the  Supportpage-" + e.getMessage());
		}

	}
	/**
	 * This method verifies activeMQ console and broker pages.
	 * @author rangesh_venkatesan
	 * @throws Exception
	 */

	public void activeMQ() throws Exception {
		driver.switchTo().frame("supportframe");
		String parentWindow = driver.getWindowHandle();
		activeMQ.click();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		HashSet<String> SupportProcess = new HashSet<String>();
		HashSet<String> Brokerlinks = new HashSet<String>();
		FileInputStream fis = new FileInputStream(new File("./src/test/resources/Utils/TestData.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Static_Data");
		String a = "";
		for (int k = 111; k <= 124; k++) {
		Row row = sheet.getRow(k);
		for (int j = 17; j < 18; j++) {
		a = row.getCell(j).getStringCellValue();
		Brokerlinks.add(a);
		}}
		while (I1.hasNext()) {
		String child_window = I1.next();
		if (!parentWindow.equals(child_window)) {
		driver.switchTo().window(child_window);
		for (int i = 2; i <= 15; i++) {
		ExtendedWebElement Broker = findExtendedWebElement(By.xpath("/html/body/table[2]/tbody/tr[" + i + "]/td[1]/a"));
		String Data = Broker.getText();
		SupportProcess.add(Data);
		}
		if (SupportProcess.equals(Brokerlinks)) {

			LOGGER.info("All Broker links are avaliable in ActiveMQ window");
		} else {
			Assert.fail("All Broker links are not  avaliable in ActiveMQ window");
		}
		Broker1.click();
		Broker1.pause(5);
		String brokerTiltle = "Broker-1 : Queues";
		for (String winhandle : driver.getWindowHandles()) {
		if (driver.switchTo().window(winhandle).getTitle().equals(brokerTiltle)) {
		String window = driver.switchTo().window(winhandle).getTitle();
		break;
		}}
		SupportProcess.clear();
		Brokerlinks.clear();
		
		for (int i = 1; i <= 32; i++) {
		ExtendedWebElement Queues = findExtendedWebElement(By.xpath("//table[@id='queues']//tbody//tr[" + i + "]//td//a"));
		String Queue = Queues.getText();
		SupportProcess.add(Queue);
		}
		String tSearch = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC019033","TestData1");
		String Search[] = tSearch.split(",");
		for (int i = 0; i < Search.length; i++) {
			Brokerlinks.add(Search[i]);
		}
		Object[] objQueue = Brokerlinks.toArray();
		for (int i = 0; i < objQueue.length; i++) {
		if (SupportProcess.contains(objQueue[i])) {
		LOGGER.info("Queue name  " + objQueue[i] + "  Loaded Succesfully in ACtiveMQ");
		}}
		SupportProcess.clear();
		Brokerlinks.clear();
		excel= new ExcelUtils();
		String TopicSearch = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0,"TC019034","TestData1");
		String ToSearch[] = TopicSearch.split(",");
		for (int i = 0; i < ToSearch.length; i++) {
			Brokerlinks.add(ToSearch[i]);
		}
		topics.click();
		topics.pause(5);
		List<ExtendedWebElement> tableRows = topicsrow.findExtendedWebElements(By.tagName("tr"));
		for (int i = 1; i <tableRows.size();i++)
		{
		ExtendedWebElement Topics = findExtendedWebElement(By.xpath("//table[@id='topics']//tbody//tr[" + i + "]//td[1]//a"));
		String Topic = Topics.getText();
		SupportProcess.add(Topic);
		}	
		
		
		Object[] objQueue1 = Brokerlinks.toArray();
		for (int i = 0; i < objQueue1.length; i++) {
		if (SupportProcess.contains(objQueue1[i])) {
		LOGGER.info("Topic name  " + objQueue1[i] + "  Loaded Succesfully in ACtiveMQ");
		}}}}}
				
	
	
	
	public void ReloadTree(String Process1, String SelectAction)
	{
		try {
		Actions action = new Actions(driver);
		EventProcessor.click();
		outer: for (int i = 1; i < 124; i++) {
		ExtendedWebElement processes = findExtendedWebElement(By.xpath("//select[@id='processId']//option[" + i + "]"));
		String Process = processes.getText();
		if (Process.equalsIgnoreCase(Process1)) 
		{
		Thread.sleep(5000);
		break outer;
		}
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
		action.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_DOWN).build().perform();
		Action.click();
		outer1: for (int i = 1; i < 20; i++) {
		ExtendedWebElement processes = findExtendedWebElement(By.xpath("//select[@id='actionId']//option[" + i + "]"));
		String Process = processes.getText();
		if (Process.equalsIgnoreCase(SelectAction)) 
		{
		Thread.sleep(5000);
		break outer1;
		}
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		action.keyUp(Keys.SHIFT).build().perform();;
		}
		Execute.click();
		//driver.findElement(By.xpath("//select[@id='processId']")).sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME));
		//Execute.pause(4);
		//action.keyUp(Keys.CONTROL).build().perform();
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		Assert.fail("" + e.getMessage());
		}
		
	}
	
 
}

    	  
    

