package com.grip.page;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.grip.DBconnection.ExchangeSQLStatementsLocal;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.testng.Assert;


public class Databaseconfigpage extends AbstractPage
{
	
	public Databaseconfigpage(WebDriver driver) {
		super(driver);
	}

	Logger LOGGER = Logger.getLogger(Dashboardpage.class);
	public String currentdate;
	
	@FindBy(xpath = "//div[@class='ui-widget-header-mod ui-corner-top']//td[1]//span")
	private ExtendedWebElement selectIndex;
	
	@FindBy(xpath = "//div[@id=\"indexTable2_filter\"]/label/input")
	private ExtendedWebElement indexSearch;
	
	@FindBy(xpath = "//table[@class='display dataTable']//tr//td")
	private ExtendedWebElement indexData;
	
	
	@FindBy(xpath = "//*[starts-with(@id,'indexTable')]//input[contains(@title,'search text')]")
	private ExtendedWebElement indexSearchTextBox;
	
	@FindBy(xpath = "//span[text()='Advanced']")
	private ExtendedWebElement advanced;
	
	@FindBy(xpath = "//span[contains(text(),'Add/Edit Index')]")
	public ExtendedWebElement addEditIndex;
	
	@FindBy(xpath = "//a[text()='RT_T_INDEX_DATAPOINT_MAP']")
	public ExtendedWebElement dataMapPoint;
	
	@FindBy(xpath = "//option[text()='INDEX KEY']")
	public ExtendedWebElement indexKey;
	
	@FindBy(xpath = "//a[@id='ui-id-4']")
	public  ExtendedWebElement researchTab;
	
	@FindBy(xpath = "//div[@class='ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active']")
	private ExtendedWebElement index_Family;
	

	public void verifyAutoCloseValue() throws IOException 
	{
		try
		{
		ExchangeSQLStatementsLocal.autoClose();
		LOGGER.info(ExchangeSQLStatementsLocal.autoCloseID);
		Assert.assertTrue(ExchangeSQLStatementsLocal.autoCloseID.equals("1"));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
	}
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
				ExchangeSQLStatementsLocal.publishByGrip(original);
				LOGGER.info(ExchangeSQLStatementsLocal.countPublishByGrip);
				Assert.assertEquals("0", ExchangeSQLStatementsLocal.countPublishByGrip);
			
			} else {
				String locateDay = Integer.toString(locateDate);				
				String original = getDate("yyyy")+getDate("MM")+locateDay;
				ExchangeSQLStatementsLocal.publishByGrip(original);
				LOGGER.info(ExchangeSQLStatementsLocal.countPublishByGrip);
				Assert.assertEquals("0", ExchangeSQLStatementsLocal.countPublishByGrip);
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
				ExchangeSQLStatementsLocal.closeTag(original);
				LOGGER.info(ExchangeSQLStatementsLocal.countPublishByGrip);
				Assert.assertTrue(Integer.parseInt(ExchangeSQLStatementsLocal.countPublishByGrip)!=0);
			} else {
				String locateDay = Integer.toString(locateDate);				
				String original = getDate("yyyy")+getDate("MM")+locateDay;
				ExchangeSQLStatementsLocal.closeTag(original);
				LOGGER.info(ExchangeSQLStatementsLocal.countPublishByGrip);
				Assert.assertTrue(Integer.parseInt(ExchangeSQLStatementsLocal.countPublishByGrip)!=0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
		
	}
	public void verifyAutoClose() {
		try
		{
		ExchangeSQLStatementsLocal.autoCloseValue();
		Assert.assertTrue(ExchangeSQLStatementsLocal.autoCloseID.equals("0"));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
	}
	public void verifyPCTag() {
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
				ExchangeSQLStatementsLocal.prelimClose(original);
				LOGGER.info("PC tag");
				LOGGER.info(ExchangeSQLStatementsLocal.countPublishByGrip);	
				String a =ExchangeSQLStatementsLocal.countPublishByGrip;
				Assert.assertTrue(Integer.parseInt(ExchangeSQLStatementsLocal.countPublishByGrip)!=0);
				
			} else {
				String locateDay = Integer.toString(locateDate);				
				String original = getDate("yyyy")+getDate("MM")+locateDay;
				ExchangeSQLStatementsLocal.prelimClose(original);
				LOGGER.info("PC tag");
				LOGGER.info(ExchangeSQLStatementsLocal.countPublishByGrip);
				Assert.assertTrue(Integer.parseInt(ExchangeSQLStatementsLocal.countPublishByGrip)!=0);
				
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
	}
	public void verifyCloseStatus() {
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
				ExchangeSQLStatementsLocal.closeStatus(original);
				LOGGER.info("close tag");
				LOGGER.info(ExchangeSQLStatementsLocal.countPublishByGrip);
				Assert.assertTrue(Integer.parseInt(ExchangeSQLStatementsLocal.countPublishByGrip)!=0);
			} else {
				String locateDay = Integer.toString(locateDate);				
				String original = getDate("yyyy")+getDate("MM")+locateDay;
				ExchangeSQLStatementsLocal.closeStatus(original);
				LOGGER.info("close tag");
				LOGGER.info(ExchangeSQLStatementsLocal.countPublishByGrip);
				Assert.assertTrue(Integer.parseInt(ExchangeSQLStatementsLocal.countPublishByGrip)!=0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
	}
	public void verifyMandatoryFlagValue() {
		try
		{
		ExchangeSQLStatementsLocal.mandatoryFlag();
		Assert.assertTrue(ExchangeSQLStatementsLocal.mandatoryFlagValue.equals("0"));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}
	}
	public void verifyDatapoint() {
		try
		{
			selectIndex.click();
			indexSearchTextBox.type("sp600");
			indexData.isElementPresent();
			indexData.rightClick();			
			advanced.isElementPresent();
			advanced.click();
			addEditIndex.isElementPresent();
			addEditIndex.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("datamaintainframe");
			dataMapPoint.click();
			
			Thread.sleep(9000);
			Assert.assertTrue(indexKey.isElementPresent());			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("" + e.getMessage());
		}	
		
	}
	public void validateResearchPage() {		
		researchTab.click();
		driver.switchTo().frame("researchframe");
		if (index_Family.isElementPresent()) {

			LOGGER.info("Research Page Loaded Successfully");
		}

	}
}
