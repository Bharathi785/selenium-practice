package com.grip.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class MasterValidation extends AbstractPage {

	Logger LOGGER = Logger.getLogger(Dashboardpage.class);

	public MasterValidation(WebDriver driver) {
		super(driver);
			}

	@FindBy(xpath = "//tbody[@role='alert']")
	private ExtendedWebElement alertTable;

	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public ExtendedWebElement Current_Time;
	
	public int getDate(String a)
	{
		String getDate = a.substring(17, 25);
		String[] split = getDate.split(":");
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < split.length; i++) {
			sb.append(split[i]);
		}
		
		int firstValue = Integer.parseInt(sb.toString());
		
		return firstValue;
		
	}
	
	public int getDate1(String a)
	{
		String getDate = a.substring(12, 20);
		String[] split = getDate.split(":");
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < split.length; i++) {
			sb.append(split[i]);
		}
		int firstValue = Integer.parseInt(sb.toString());
		return firstValue;
	}

	public void Verify_UI (String Subject,String Alert_Type, String CurTime, String Description)
	{
		
	Select view = new Select(driver.findElement(By.id("alertViewsList")));
    view.selectByIndex(1);
    Boolean a =false;

	
    outer:for(int i=1;i<=15;i++) 
	{
	ExtendedWebElement Time= findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody[@role='alert']/tr["+i+"]/td[1]"));
	String A_Time = Time.getText();
	int A_time1= getDate1(A_Time);
	int T_Time=getDate(CurTime);
	ExtendedWebElement A_Alert_typet = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr["+i+"]//td[2]"));
	String R_Alert = A_Alert_typet.getText();
	ExtendedWebElement A_Subject = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr["+i+"]//td[4]"));
	String R_Subject = A_Subject.getText();
	ExtendedWebElement A_Description= findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr["+i+"]//td[6]"));
	String R_Description = A_Description.getText();
	if(A_time1>=T_Time && R_Alert.equalsIgnoreCase(Alert_Type) && R_Subject.contains(Subject)&& R_Description.contains(Description))
		
	{
		LOGGER.info("Alert in UI Generated");
		a=true;   
		break outer;
	}
	 
	}
    
    if(!a) {
		LOGGER.error("Alert in UI Not Generated");
	}
	

	}

}
