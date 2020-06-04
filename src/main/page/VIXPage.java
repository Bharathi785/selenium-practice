package com.grip.page;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import com.grip.DBconnection.VIXSqlStatements;
import com.grip.utils.ExcelUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class VIXPage extends AbstractPage{
    
	public static ExcelUtils excel = new ExcelUtils();
	Logger LOGGER = Logger.getLogger(VIXPage.class);
	
	public static LinkedHashMap<String, String> fetchTableColumnValues = new LinkedHashMap<>();
	
	public static String showTypeValue, indexData, alertUniqueID, currenTime, indexVIXName, contractId, indexDataValue, dasboardLink,
	StopTimeValue;
	
	public static String dashboardFrameId = "dashboardframe", indexTableID = "indexTable",
			dataMaintainFrameID = "datamaintainframe", researchFrameID = "researchframe",
			reportFrameID = "reportsframe", exchangeID = "exchange", icdID = "icd";
	
	public static int initialClosecount = 0;
	
	public VIXPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath = "//td[@data-handler='selectDay']")
	public List<ExtendedWebElement> calendarDaysList;
	
	@FindBy(xpath = "//table[@class='targetTableDisplay dataTable']//tbody//tr[1]//td[6]")
	public ExtendedWebElement displayTableFirstRow;
	
	@FindBy(id = "time_hour_id")
	private ExtendedWebElement timeTextBox;
	
	@FindBy(id = "incdecminId")
    public ExtendedWebElement minutes;
	
	@FindBy(xpath = "//input[@name='time_hour_name']")
    public ExtendedWebElement timeHour;
		
	@FindBy(xpath = "//table[contains(@id,'indexTable')]//tr[1]//td[12]")
    public ExtendedWebElement stopTime;
	
	@FindBy(xpath = "//span[text()='Report']")
    public ExtendedWebElement reportoption;
	
	@FindBy(xpath = "//span[contains(@data-bind,'iClose')]")
	public ExtendedWebElement dashboardClose;
	
	@FindBy(id = "copyIdTextareaId")
	public ExtendedWebElement copyIdClipboard;
	
	@FindBy(xpath = "//span[text()='Copy ID']")
	public ExtendedWebElement copyId;
	
	@FindBy(xpath = "//span[text()='Copy to Clipboard']")
	public ExtendedWebElement copyToClipBoard;
	
	@FindBy(xpath = "//span[text()='Show Indices']")
	public ExtendedWebElement showIndices;
	
	@FindBy(xpath = "//table[@class='display dataTable']//thead//tr")
	public ExtendedWebElement indexColumns;
	
	@FindBy(xpath = "//span[text()='Ok']")
	public ExtendedWebElement indexHoldOk;
	
	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[9]")
	private ExtendedWebElement refreshFromT3;
	
	@FindBy(xpath = "//span[contains(text(),'Refresh from GRIP')]")
	private ExtendedWebElement refreshFromGrip;
	
	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public ExtendedWebElement CurrentTime;
		
	@FindBy(xpath = "//input[@title='Column Selector']")
	private ExtendedWebElement columnSelector;
	
	@FindBy(xpath = "(//span[text()='Cancel'])[1]")
	private ExtendedWebElement cancel;
	
	@FindBy(xpath = "//span[contains(text(),'Constituent')]")
	private ExtendedWebElement showConstituentDetails;
	
	@FindBy(xpath = "//a[contains(@href,'dashboard')]")
	private ExtendedWebElement dashboardTab;
	
	@FindBy(xpath = "//span[contains(text(),'Exchanges')]")
	private ExtendedWebElement showExchange;
	
	@FindBy(xpath = "//span[contains(text(),'Publish Close from T3')]")
	private ExtendedWebElement publishCloseFromT3;

	@FindBy(xpath = "//span[contains(text(),'Publish Close from GRIP')]")
	private ExtendedWebElement publishCloseFromGrip;
	
	@FindBy(xpath = "//span[text()='Advanced']")
	private ExtendedWebElement advanced;
	
	@FindBy(xpath = "//table[contains(@id,'indexTable')]//tr//td[6]")
	public ExtendedWebElement indexName;
	
	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public ExtendedWebElement alertSearch;
	
	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr")
	public ExtendedWebElement alertTableFirstRow;
	
	public void waitForTablefirstRow(String tableID) {
		ExtendedWebElement ele = findExtendedWebElement(
				By.xpath("//table[contains(@id,'" + tableID + "')]/tbody/tr[1]"));
		ele.isElementPresent();
	}
	
	public void getIndexData(String IndexData) {
		try {
			indexData = IndexData;

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get Job ID-" + e.getMessage());
		}
	}
	


		public void showDetails(String showType, String IndexData) throws InterruptedException, IOException{
			try {
				showTypeValue = showType;
				ExtendedWebElement totalCountValue;
				ExtendedWebElement firstIndex;
				String totalCountText;
				if (showTypeValue.equalsIgnoreCase("constituent")) {
					showConstituentDetails.isElementPresent();
					showConstituentDetails.click();
					totalCountValue = findExtendedWebElement(
							By.xpath("//div[contains(@id,'" + icdID + "')]//span[@class='statusBarSpan']"));
					totalCountValue.isElementPresent();
					waitForTablefirstRow(icdID);
					
					firstIndex = findExtendedWebElement(
							By.xpath("(//table[@class='display dataTable']//tbody//tr[1]//td[6])[position()=2]"));
					firstIndex.click();
					firstIndex.rightClick();
					copyId.isElementPresent();
					copyId.click();
					contractId=copyIdClipboard.getAttribute("value");
					copyToClipBoard.click();
					
					getIndexData(IndexData);
					totalCountText = totalCountValue.getText().substring(0, totalCountValue.getText().indexOf("("));
					VIXSqlStatements.getTotalContituentValue(showType);
					Assert.assertEquals(totalCountText.replaceAll("[^0-9]", ""), VIXSqlStatements.totalCount);
					
				} else {
					showExchange.isElementPresent();
					showExchange.click();
					totalCountValue = findExtendedWebElement(
							By.xpath("//div[contains(@id,'" + exchangeID + "')]//span[@class='statusBarSpan']"));
					totalCountValue.isElementPresent();
					waitForTablefirstRow(exchangeID);
					
					getIndexData(IndexData);
					totalCountText = totalCountValue.getText().substring(0, totalCountValue.getText().indexOf("("));
					VIXSqlStatements.getTotalExchangeValue(showType);
					Assert.assertEquals(totalCountText.replaceAll("[^0-9]", ""), VIXSqlStatements.totalCount);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to perform action on show details and verify value-" + e.getMessage());
			}
		}
		
		public void showIndices(String showType) {
			try {
				ExtendedWebElement firstIndex;
				
				firstIndex = findExtendedWebElement(
						By.xpath("//table[@class='exchangeTableDisplay dataTable']//tbody//tr[1]//td[1]"));
				firstIndex.click();
				firstIndex.rightClick();
				
				showIndices.isElementPresent();
				showIndices.click();
				
				
				VIXSqlStatements.getshowIndicesValue(showType);
				Assert.assertEquals(indexData, VIXSqlStatements.indexID);
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to perform action on show indices and verify value-" + e.getMessage());
			}
			
		}
		
		
		

				public void verifyHeaders()throws InterruptedException, IOException{
			    try
			    {
			       			    
			    	columnSelector.click();
			        LinkedList<String> columnUI = new LinkedList<String>();
			        LinkedList<String> columnExcel = new LinkedList<String>();
			       
			        for(int i=1;i<16;i++)
			        {
			        ExtendedWebElement DataFields=findExtendedWebElement(By.xpath("//ul[@id='selectedList']//li["+i+"]"));
			        String Data=DataFields.getText();
			        columnUI.add(Data);
			           
			        }
			       
			        String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC33002", "TestData1");   
			        String Search_Split[]=Search.split(",");
			        for(int k=1;k<=Search_Split.length;k++)
			        {
			            ExtendedWebElement DataFields=findExtendedWebElement(By.xpath("//ul[@id='selectedList']//li["+k+"]"));
			            String Data=DataFields.getText();
			            columnExcel.add(Data);
			        }
			        if(columnUI.equals(columnExcel))
			        {
			            LOGGER.info("Expected columns matches with Index list Window");
			        }
			        System.out.println(columnUI);
			        System.out.println(columnExcel);
			        
			        cancel.click();
			                 
			           
			    }catch (Exception e) {
					e.printStackTrace();
					Assert.fail("Unable to verify the Headers-" + e.getMessage());
				}
				}
		
				public void switchToFrame(String frameID) {
					driver.switchTo().frame(frameID);
				}

				public void switchOutOfFrame() {
					driver.switchTo().defaultContent();
				}
		
			
		public void getTimeInDashboard() {
			try {
				dashboardTab.isElementPresent();
				switchToFrame(dashboardFrameId);
				initialClosecount = Integer.parseInt(dashboardClose.getText());
				switchOutOfFrame();
				currenTime = CurrentTime.getText();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to get time-" + e.getMessage());
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
					
					System.out.println("tableUTCTime="+tableUTCTime);
					System.out.println("loginUTCTime="+loginUTCTime);
					System.out.println("alertType="+alertType);
					System.out.println("alertTypeInput="+alertTypeInput);
					System.out.println("subject="+subject);
					System.out.println("subjectInput="+subjectInput);
					System.out.println("tableUTCTime="+tableUTCTime);
					System.out.println("description="+descriptionInput);
					

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

		public void clickRefreshFromT3VIX() {
			try {
				refreshFromT3.isElementPresent();
				refreshFromT3.click();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on refresh from T3 option-" + e.getMessage());
			}
		}
		
		public void clicksaveIndexOk() {
			try {
				indexHoldOk.isElementPresent();
				indexHoldOk.click();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on save index ok-" + e.getMessage());
			}
		}
		
		public void clickPublishCloseFromT3() {
			try {
				publishCloseFromT3.isElementPresent();
				publishCloseFromT3.click();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on publish close from T3-" + e.getMessage());
			}
		}

		public void clickPublishCloseFromGrip() {
			try {
				publishCloseFromGrip.isElementPresent();
				publishCloseFromGrip.click();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on publish close from Grip-" + e.getMessage());
			}
		}
		
		public void clickAdvanced() {
			try {
				advanced.isElementPresent();
				advanced.hover();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on advanced option-" + e.getMessage());
			}
		}
		
		public void getUniqueID() {
			try {
				indexVIXName=indexName.getText();
				alertSearch.type(indexName.getText());
				alertSearch.pause(30);
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to get unique ID-" + e.getMessage());
			}
		}

		public void verifyPublishcloseFromT3VIX() throws Exception {
			
			try{
				String publishT3Message = "was successfully captured from T3";
			
			publishCloseFromT3.pause(30);
			getUniqueID();
			String description = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]"))
					.getText();
			System.out.println("description="+description);
			if (description.contains(publishT3Message)) {
			} else {
				Assert.fail("The index cannot be publish close from T3");
			}
			
			VerifyAlertsMessages(indexVIXName, "EOD Value", currenTime, publishT3Message);
			
			}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to verify Publish Close from T3-" + e.getMessage());
			}
		}
		
		public void verifyPublishCloseFromGripVIX() throws Exception {
			try{
				String publishT3Message = "was successfully captured from GRIP";
			clicksaveIndexOk();
			publishCloseFromGrip.pause(30);
			getUniqueID();
			String description = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]"))
					.getText();
			if (description.contains(publishT3Message)) {
			} else {
				Assert.fail("The index cannot be publish close from T3");
			}
			VerifyAlertsMessages(indexVIXName, "EOD Value", currenTime, publishT3Message);
			}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to verify Publish Close from Grip-" + e.getMessage());
			}
		}

		
		public void verifyRefreshFromGripVIX() {
		try {
			refreshFromGrip.isElementPresent();
			refreshFromGrip.click();
			refreshFromGrip.pause(15);
			getUniqueID();
			VerifyAlertsMessages(indexVIXName, "Intra-Day Change", currenTime,
					"Index has been reloaded from GRIP");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to refresh value from GRIP-" + e.getMessage());
		}
	
		
		}

		public void verifyRefreshFromT3VIX() {
			try {
			refreshFromT3.pause(15);
			getUniqueID();
			VerifyAlertsMessages(indexVIXName, "Pending New Day", currenTime,
			"Index has been loaded");
		} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to refresh value from GRIP-" + e.getMessage());
		}
		
			
			}

		public void verifyAutoCloseValue() throws IOException{ 
			{
			try
			{
			VIXSqlStatements.autoClose();
			LOGGER.info(VIXSqlStatements.autoCloseID);
			Assert.assertTrue(VIXSqlStatements.autoCloseID.equals("1"));
			}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to verify the Auto Close value=" + e.getMessage());
			}
		}}

		
		public static String getDate(String format) 
		{
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date date = new Date();
			return df.format(date);	
		}
		
		public void verifyPreviousDay() {
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
				
					String original = getDate("yyyy")+getDate("MM")+String.format("%02d",locateDate);
					LOGGER.info(original);
					VIXSqlStatements.publishByGrip(original);
					LOGGER.info(VIXSqlStatements.countPublishByGrip);
					Assert.assertEquals("0", VIXSqlStatements.countPublishByGrip);
				
				} else {
					String locateDay = Integer.toString(locateDate);				
					String original = getDate("yyyy")+getDate("MM")+locateDay;
					VIXSqlStatements.publishByGrip(original);
					LOGGER.info(VIXSqlStatements.countPublishByGrip);
					Assert.assertEquals("0", VIXSqlStatements.countPublishByGrip);
				}
				
							
			}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("" + e.getMessage());
			}
			
		}

		public void verifyClosetag() {
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
				
					String original = getDate("yyyy")+getDate("MM")+String.format("%02d",locateDate);
					LOGGER.info(original);
					VIXSqlStatements.closeTag(original);
					LOGGER.info(VIXSqlStatements.countPublishByGrip);
					Assert.assertTrue(Integer.parseInt(VIXSqlStatements.countPublishByGrip)!=0);
				} else {
					String locateDay = Integer.toString(locateDate);				
					String original = getDate("yyyy")+getDate("MM")+locateDay;
					VIXSqlStatements.closeTag(original);
					LOGGER.info(VIXSqlStatements.countPublishByGrip);
					Assert.assertTrue(Integer.parseInt(VIXSqlStatements.countPublishByGrip)!=0);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("" + e.getMessage());
			}
					
		}

		public void clickDashboardLink(String dasboardLinkType) {
			try {
				dasboardLink = dasboardLinkType;
				switchToFrame(dashboardFrameId);
				switch (dasboardLink) {
				case "close":
					if (initialClosecount > 0)
						dashboardClose.click();
					else
						Assert.fail("None of the index are in closed state");
					break;

				default:
					break;
				}
				switchOutOfFrame();
				switchToFrame(researchFrameID);
				waitForTablefirstRow(indexTableID);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on Dashboard states link-" + e.getMessage());
			}
		}

		public void getIndicesCoulmnValue() {
			try {
				ExtendedWebElement currentPrice = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[13]"));
				fetchTableColumnValues.put("currentPriceValue", currentPrice.getText());
				ExtendedWebElement prevValue = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[14]"));
				fetchTableColumnValues.put("previuosValue", prevValue.getText());
				ExtendedWebElement openValueElementMand = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[16]"));
				fetchTableColumnValues.put("openValue", openValueElementMand.getText());
				ExtendedWebElement indexNameElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[6]"));
				fetchTableColumnValues.put("name", indexNameElement.getText());
				ExtendedWebElement calcSatusElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[3]"));
				fetchTableColumnValues.put("calcStatusValue", calcSatusElement.getText());
				ExtendedWebElement startTimeUTC = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[11]"));
				fetchTableColumnValues.put("startTimeUTCValue", startTimeUTC.getText());
				ExtendedWebElement stopTimeUTC = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[12]"));
				fetchTableColumnValues.put("stopTimeUTCValue", stopTimeUTC.getText());
				ExtendedWebElement statusele = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[2]"));
				fetchTableColumnValues.put("status", statusele.getText());
				ExtendedWebElement publish = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[8]"));
				fetchTableColumnValues.put("publishValue", publish.getText());
				ExtendedWebElement dailyHigh = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[17]"));
				fetchTableColumnValues.put("dailyHighValue", dailyHigh.getText());
				ExtendedWebElement dailyLow = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[18]"));
				fetchTableColumnValues.put("dailyLowValue", dailyLow.getText());
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to get column values from indices table" + e.getMessage());
			}
		}

		public void clickReportOption() {
			try {
				reportoption.isElementPresent();
		        reportoption.click();
		        switchOutOfFrame();
		        switchToFrame(reportFrameID);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click on report-" + e.getMessage());
			}   
        }

		public void setMinutes(String Minutes) {
			try {	
	            minutes.isElementPresent();
                minutes.pause(3);
                String selectAll = Keys.chord(Keys.CONTROL, "a");
                minutes.getElement().sendKeys(selectAll);
                minutes.pause(3);
                minutes.getElement().sendKeys(Minutes);
                LOGGER.info("Minutes value is passed successfully");
                }catch (Exception e) {
    				e.printStackTrace();
    				Assert.fail("Unable to click set minutes-" + e.getMessage());
    			}   
            }
		
		
		public void dateSelect(int dateValue) {
			for (int i = 0; i < calendarDaysList.size(); i++) {
				if (calendarDaysList.get(i).getText().equalsIgnoreCase(Integer.toString(dateValue))) {
					calendarDaysList.get(i).click();
					break;
				}
			}
		}
		
		public void clickDatePickerVIX() {
			try {
				ExtendedWebElement datePicker = findExtendedWebElement(
						By.xpath("//input[contains(@id,'slider_from_date_time_id')]/following::img[position()=1]"));
				datePicker.isElementPresent();
				datePicker.click();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to click date picker icon-" + e.getMessage());
			}
		}

		public void feedPublishDate() {
			try {		
				int olderDate = 0;
				clickDatePickerVIX();
	
				if (getDate("E").equalsIgnoreCase("mon") || getDate("E").equalsIgnoreCase("sun")) {
						olderDate = Integer.parseInt(getDate("dd")) - 3;
					} else {
						olderDate = Integer.parseInt(getDate("dd")) - 1;
					}
					dateSelect(olderDate);
					String StopValueValue = StopTimeValue.substring(12, 20);
					setTime(StopValueValue);
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to provide date in reports tab" + e.getMessage());
			}
		}
		
		public void setTime(String StopValueValue) {
			try {
				timeTextBox.isElementPresent();
				timeTextBox.pause(3);
				timeTextBox.getElement().sendKeys(Keys.chord(Keys.CONTROL, "a"));
				timeTextBox.pause(3);
				timeTextBox.getElement().sendKeys(StopValueValue);
		
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Time drop down is not passed successfully-" + e.getMessage());
			}
		}

		public void getStopTimeValue() {
			StopTimeValue=stopTime.getText();
					
		}

		public void verifyTickStatus() {
			try {
			    String Description=displayTableFirstRow.getText();
			    Assert.assertTrue(Description.contains("Tick Status: Close"));
			    
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify Tick Status-" + e.getMessage());
		}
	}

}
