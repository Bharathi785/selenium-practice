package com.grip.page;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.grip.DBconnection.IndicesSQLStatements;
import com.grip.DBconnection.JobSchedulerSQLStatements;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class JobSchedulerPage extends AbstractPage {
	
	Logger LOGGER = Logger.getLogger(Dashboardpage.class);

	public static String currenTime, searchSingleRunValue, searchEnableValue, jobUniqueID, jobTypeId, jobTypeValue,
			updateSingleRunValue, exchangeSegmentValue, searchRecurringRunValue, startTimeValue, stopTimeValue,
			storeProcedureValue, sessionWaitTimeValue, indexIdValue, activeIdEnabledValue="1", activeIdDisabledValue="0",
			alertMessageValue,afterJobId;

	public static int beforeCreationJobCount = 0, afterCreationJobCount = 0, FirstRowValue = 0;
	public static String dashboardFrameId = "dashboardframe", dataMaintainFrameID = "datamaintainframe", SingleRun=null, RecurringRun=null;

	public static LinkedHashMap<String, String> fetchJobTableColumnValues = new LinkedHashMap<>();

	public JobSchedulerPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}
    
	
	@FindBy(xpath = "//span[contains(text(),'Please enable the job(s) to Force Run')]")
	public ExtendedWebElement alertDialogMessageId;
	
	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public ExtendedWebElement CurrentTime;

	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[1]")
	public ExtendedWebElement jobTableFirstRowElement;
    
	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[11]")
	public ExtendedWebElement forceRunValue;
	
	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr[1]")
	public ExtendedWebElement jobTableFirstRow;
	
	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr[4]")
	public ExtendedWebElement jobTableFourthRow;

	@FindBy(xpath = "//a[contains(@href,'#dataMaintenanceTab')]")
	private ExtendedWebElement dataMaintain;

	@FindBy(xpath = "//a[contains(@href,'#jobschedulernew')]")
	private ExtendedWebElement jobScheduler;

	@FindBy(id = "exchangeSegmentId")
	public ExtendedWebElement exchangeSegmentId;

	@FindBy(id = "addnewjob")
	public ExtendedWebElement addNewJob;

	@FindBy(id = "disableJob")
	public ExtendedWebElement disableJob;

	@FindBy(id = "enableJob")
	public ExtendedWebElement enableJob;

	@FindBy(id = "forceRunJob")
	public ExtendedWebElement forceRunJob;

	@FindBy(id = "editJob")
	public ExtendedWebElement editJob;

	@FindBy(id = "deleteJob")
	public ExtendedWebElement deleteJob;

	@FindBy(id = "jobType")
	public ExtendedWebElement jobType;

	@FindBy(id = "singleRunCheck")
	public ExtendedWebElement singleRun;

	@FindBy(id = "recurringCheck")
	public ExtendedWebElement recurringRun;

	@FindBy(id = "singleRunJobTimings")
	public ExtendedWebElement singleRunJobTimings;

	@FindBy(id = "recurringRunJobTimings")
	public ExtendedWebElement recurringRunJobTimings;

	@FindBy(id = "runStartTimeString")
	public ExtendedWebElement runStartTime;

	@FindBy(id = "runEndTimeString")
	public ExtendedWebElement runEndTime;

	@FindBy(id = "jobScheduledonMonDay")
	public ExtendedWebElement jobScheduledonMonDay;

	@FindBy(id = "jobScheduledonTuesDay")
	public ExtendedWebElement jobScheduledonTuesDay;

	@FindBy(id = "jobScheduledonWednesDay")
	public ExtendedWebElement jobScheduledonWednesDay;

	@FindBy(id = "jobScheduledonThursDay")
	public ExtendedWebElement jobScheduledonThursDay;

	@FindBy(id = "jobScheduledonFriDay")
	public ExtendedWebElement jobScheduledonFriDay;

	@FindBy(id = "jobScheduledonSaturDay")
	public ExtendedWebElement jobScheduledonSaturDay;

	@FindBy(id = "jobScheduledonSunDay")
	public ExtendedWebElement jobScheduledonSunDay;

	@FindBy(id = "activeInd")
	public ExtendedWebElement enabled;

	@FindBy(xpath = "//input[@name='storedProcedure']")
	public ExtendedWebElement storedProcedure;

	@FindBy(xpath = "//input[@name='sessionWaitTime']")
	public ExtendedWebElement sessionWaitTime;

	@FindBy(xpath = "//input[@value='Refresh Data']")
	public ExtendedWebElement refreshData;

	@FindBy(xpath = "//span[text()='Save']")
	public ExtendedWebElement save;

	@FindBy(xpath = "/html/body/div[5]/div[11]/div/button/span")
	public ExtendedWebElement saveOk;

	@FindBy(xpath = "//table[@id='tableHeaderId']//thead//tr//th")
	public ExtendedWebElement jobId;

	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']/tr")
	public List<ExtendedWebElement> jobIdRowList;

	@FindBy(xpath = "(//span[text()='Ok'])[position()=1]")
	public ExtendedWebElement confirmDisableOk;
	
	@FindBy(xpath = "(//span[text()='Ok'])[position()=2]")
	public ExtendedWebElement confirmDeleteOk;

	@FindBy(xpath = "//div[@aria-describedby='confirmDialogId']//div[11]//div//button//span")
	public ExtendedWebElement confirmForceRunOk;

	@FindBy(xpath = "//input[@name='indexIds']")
	public ExtendedWebElement indexIds;
	
	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[7]")
	public ExtendedWebElement recurringRunValue;
		
	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[6]")
	public ExtendedWebElement singleRunValue;
	
	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[5]")
	public ExtendedWebElement nextRunValue;
		
	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr[3]//td[10]")
	public ExtendedWebElement ThirdRowEnableStatus;
	
	@FindBy(xpath = "//tbody[@id='jobSchedulerDtlTblId']//tr")
	public ExtendedWebElement jobColumns;
	
	
	public void switchToFrame(String frameID) {
		driver.switchTo().frame(frameID);
	}

	public void switchOutOfFrame() {
		driver.switchTo().defaultContent();
	}

	public void clickDataMaintain() {
		try {
			switchOutOfFrame();
			dataMaintain.isElementPresent();
			dataMaintain.click();
			switchToFrame(dataMaintainFrameID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Data Maintain tab-" + e.getMessage());
		}
	}

	public void clickjobScheduler() {
		try {
			jobScheduler.isElementPresent();
			jobScheduler.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Job Scheduler tab-" + e.getMessage());
		}
	}

	public static String getDate(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		return df.format(date);
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
	
	
	public void VerifyTIme(String timeValue) throws Exception {
        try {
        	String timeUTC = findExtendedWebElement(By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[4]"))
    				.getText();

    		int loginUTCTime = getDate(timeValue, 17, 25);
    		int tableUTCTime = getDate(timeUTC, 12, 20);

    		Assert.assertTrue(tableUTCTime >= loginUTCTime);
    		
		} catch (Exception e) {e.printStackTrace();
		Assert.fail("Unable to verify Time-" + e.getMessage());
		}
		
	}
	
	public void VerifyTIme1(String timeValue) throws Exception {
	   try {
		   String timeUTC = findExtendedWebElement(By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[3]//td[5]"))
					.getText();

			int loginUTCTime = getDate(timeValue, 12, 16);
			int tableUTCTime = getDate(timeUTC, 6, 11);
					   
			Assert.assertTrue(loginUTCTime >= tableUTCTime);
		    LOGGER.info("Job should not runs as per configured scheduled");
		 	
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Unable to verify Time-" + e.getMessage());
		}
		}

		
	
	public void verifyNextRunSingleTime() throws Exception {
      try {
    	String NextRunTime = nextRunValue.getText();
  		String NextRuntimeUpdated = NextRunTime.substring(12, 17);
  		
  		String SingleRunTime = singleRunValue.getText();
  		String SingleRun[]=SingleRunTime.split(",");
  		String Time1=SingleRun[0];
  		String Time2=SingleRun[1];
  		String Time3=SingleRun[2];
  		
  		Assert.assertTrue(NextRuntimeUpdated.equals(Time1)||NextRuntimeUpdated.equals(Time2)||NextRuntimeUpdated.equals(Time3));
  	
		
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Unable to verify Next Run Single Time-" + e.getMessage());}
		
	}
	
	public void verifyNextRunRecurringTime() throws Exception {
		try {
		String NextRunTime = nextRunValue.getText();
		String NextRuntimeUpdated = NextRunTime.substring(15, 17);
		
		String RecurringRunTime = recurringRunValue.getText();
		String RecurringRun[]=RecurringRunTime.split(",");
		String Time1=RecurringRun[0];
		String Time2=RecurringRun[1];
		String Time3=RecurringRun[2];
		String Time4=RecurringRun[3];
		String Time5=RecurringRun[4];
		
		Assert.assertTrue(NextRuntimeUpdated.equals(Time1)||NextRuntimeUpdated.equals(Time2)||NextRuntimeUpdated.equals(Time3)
				||NextRuntimeUpdated.equals(Time4)||NextRuntimeUpdated.equals(Time5));
	
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Unable to verify Next Run Recurring Time-" + e.getMessage());}
}

	public void verifyNextRunSingleRecurringTime() {
		try {
		String NextRunTime = nextRunValue.getText();
		String NextRuntimeUpdated = NextRunTime.substring(12, 17);
		
		String SingleRunTime = singleRunValue.getText();
		String SingleRun[]=SingleRunTime.split(",");
		String Time1=SingleRun[0];
		String Time2=SingleRun[1];
		String Time3=SingleRun[2];
		
		String NextRunTime1 = nextRunValue.getText();
		String NextRuntimeUpdated1 = NextRunTime1.substring(15, 17);
		
		String RecurringRunTime = recurringRunValue.getText();
		String RecurringRun[]=RecurringRunTime.split(",");
		String Time11=RecurringRun[0];
		String Time22=RecurringRun[1];
		String Time33=RecurringRun[2];
		
		Assert.assertTrue(NextRuntimeUpdated1.equals(Time11)||NextRuntimeUpdated1.equals(Time22)||NextRuntimeUpdated1.equals(Time33)
				||NextRuntimeUpdated.equals(Time1)||NextRuntimeUpdated.equals(Time2)||NextRuntimeUpdated.equals(Time3));
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Unable to verify Next Run Single Recurring Time-" + e.getMessage());}
	}
	
	public void clickRefreshData() throws Exception {
		try {
			forceRunJob.pause(45);
			refreshData.isElementPresent();
			refreshData.click();
			refreshData.pause(5);
		} catch (Exception e) {
			e.printStackTrace();
		Assert.fail("Unable to click refresh data-" + e.getMessage());}
		
	}
	
	public void refreshData() throws Exception {
		try {
			refreshData.isElementPresent();
			refreshData.click();
			} catch (Exception e) {
			e.printStackTrace();
		Assert.fail("Unable to click refresh data-" + e.getMessage());}
		
	}

	public void verifyLastRun() throws Exception {
        try {
         VerifyTIme(currenTime);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify last run-" + e.getMessage());
		}
		

	}

	public void clickaddNewJob() {
		try {
			beforeCreationJobCount = jobIdRowList.size();
			addNewJob.isElementPresent();
			addNewJob.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Add New Job tab-" + e.getMessage());
		}
	}

	public void clickDisableJob() {
		try {
			disableJob.isElementPresent();
			disableJob.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Disable tab-" + e.getMessage());
		}
	}

	public void clickEnableJob() {
		try {
			enableJob.isElementPresent();
			enableJob.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Enable tab-" + e.getMessage());
		}
	}

	public void clickForceRunJob() {
		try {
			forceRunJob.isElementPresent();
			forceRunJob.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Force Run tab-" + e.getMessage());
		}
	}

	public void clickEditJob() {
		try {
			editJob.isElementPresent();
			editJob.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Edit tab-" + e.getMessage());
		}
	}

	public void clickDeleteJob() {
		try {
			if (afterCreationJobCount > beforeCreationJobCount) {
				jobTableFirstRow.click();
				
				FirstRowValue = Integer.parseInt(jobTableFirstRowElement.getText());
				if (FirstRowValue > 75) {
					
					SingleRun = singleRunValue.getText();
					RecurringRun = recurringRunValue.getText();
										
					if (SingleRun.contains("59")||RecurringRun.contains("59")) {
					deleteJob.isElementPresent();
					deleteJob.click();
					confirmDisableOk.pause(2);
					confirmDisableOk.click();
					confirmDeleteOk.click();
					LOGGER.info("The created Job is deleted");
				}else {
					LOGGER.info("The created Job is not deleted");
				}
			}

		}
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Delete tab-" + e.getMessage());
		}
	}
	
	public void clickDeleteJobCleanUp() {
		try {
			    for (int i = 0; i < 30; i++) {
					
				jobTableFirstRow.click();
				FirstRowValue = Integer.parseInt(jobTableFirstRowElement.getText());
				if (FirstRowValue > 75) {
					
					SingleRun = singleRunValue.getText();
					RecurringRun = recurringRunValue.getText();
										
					if (SingleRun.contains("59")||RecurringRun.contains("59")) {
					deleteJob.isElementPresent();
					deleteJob.click();
					confirmDisableOk.pause(3);
					confirmDisableOk.click();
					confirmDeleteOk.click();
					LOGGER.info("The created Job is deleted");
					}
					}
				}
			 			}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Delete tab-" + e.getMessage());
		}
	}

	public void selectJobType(String JobTypeName) {
		try {
			jobTypeValue = JobTypeName;
			jobType.isElementPresent();
			jobType.select(JobTypeName);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to select job type-" + e.getMessage());
		}
	}

	public void singleRun() {
		try {
			singleRun.isElementPresent();
			singleRun.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Single run-" + e.getMessage());
		}
	}

	public void recurringRun() {
		try {
			recurringRun.isElementPresent();
			recurringRun.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on recurring run-" + e.getMessage());
		}
	}

	public void singleRunJobTimings(String SingleRunValue) {
		try {
			searchSingleRunValue = SingleRunValue;
			singleRunJobTimings.isElementPresent();
			singleRunJobTimings.type(searchSingleRunValue);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to find single Run Job Timings-" + e.getMessage());
		}
	}

	public void recurringRunJobTimings(String RecurringValue) {
		try {
			searchRecurringRunValue = RecurringValue;
			recurringRunJobTimings.isElementPresent();
			recurringRunJobTimings.type(searchRecurringRunValue);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to find recurring Run Job Timings-" + e.getMessage());
		}
	}

	public void enableJob(String enableValue) {
		try {
			enabled.isElementPresent();
			enabled.select(enableValue);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Enable job-" + e.getMessage());
		}
	}

	public void scheduledAllDays() {
		try {
			jobScheduledonMonDay.click();
			jobScheduledonTuesDay.click();
			jobScheduledonWednesDay.click();
			jobScheduledonThursDay.click();
			jobScheduledonFriDay.click();
			jobScheduledonSaturDay.click();
			jobScheduledonSunDay.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on all scheduled days-" + e.getMessage());
		}
	}

	public void disableScheduledDays() {
		try {
			jobScheduledonMonDay.click();
			jobScheduledonTuesDay.click();
			jobScheduledonWednesDay.click();
			jobScheduledonThursDay.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on disable Scheduled days-" + e.getMessage());
		}
	}

	public void clicksaveOk() {
		try {
			saveOk.isElementPresent();
			saveOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on save ok-" + e.getMessage());
		}
	}

	public void save() {
		try {
			save.scrollTo();
			save.isElementPresent();
			save.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on save-" + e.getMessage());
		}
	}

	public void saveOk() {
		try {
			saveOk.isElementPresent();
			saveOk.scrollTo();
			saveOk.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on save-" + e.getMessage());
		}
	}

	public void jobIdSorting() {
		try {
			afterCreationJobCount = jobIdRowList.size();
			jobId.isElementPresent();
			jobId.click();
			jobId.pause(2);
			jobId.click();
			jobId.pause(1);
			afterJobId=jobTableFirstRowElement.getText();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to sorting the job-" + e.getMessage());
		}
	}

	public void getTimeInDashboard() {
		try {
			CurrentTime.isElementPresent();
			currenTime = CurrentTime.getText();

			System.out.println("currenTime=" + currenTime);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get time-" + e.getMessage());
		}
	}

	public void getJobCoulmnValue() {
		try {
			ExtendedWebElement jobId = findExtendedWebElement(
					By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[1]"));
			fetchJobTableColumnValues.put("jobIdValue", jobId.getText());
			ExtendedWebElement jobType = findExtendedWebElement(
					By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[2]"));
			fetchJobTableColumnValues.put("jobTypeValue", jobType.getText());
			ExtendedWebElement singleRunTiming = findExtendedWebElement(
					By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[6]"));
			fetchJobTableColumnValues.put("singleRunTimingValue", singleRunTiming.getText());
			ExtendedWebElement RecurringRunTiming = findExtendedWebElement(
					By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[7]"));
			fetchJobTableColumnValues.put("RecurringTimingValue", RecurringRunTiming.getText());
			ExtendedWebElement enabled = findExtendedWebElement(
					By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[10]"));
			fetchJobTableColumnValues.put("enabledValue", enabled.getText());
			ExtendedWebElement scheduledDays = findExtendedWebElement(
					By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[1]//td[12]"));
			fetchJobTableColumnValues.put("scheduledDaysValue", scheduledDays.getText());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get job column values from table" + e.getMessage());
		}
	}

	public void getJobID() {
		try {
			jobUniqueID = jobTableFirstRowElement.getText();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get Job ID-" + e.getMessage());
		}
	}

	public void getJobTypeId(String JobTypeId) {
		try {
			jobTypeId = JobTypeId;

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get Job ID-" + e.getMessage());
		}
	}

	public void verifyJobTableStatus(String UpdateSingleRun) {
		try {

			Assert.assertTrue(
					fetchJobTableColumnValues.get("singleRunTimingValue").equalsIgnoreCase(updateSingleRunValue));
			Assert.assertTrue(fetchJobTableColumnValues.get("jobTypeValue").equalsIgnoreCase(jobTypeValue));
			JobSchedulerSQLStatements.getJobSchdulereStatus(jobUniqueID);
			Assert.assertTrue(JobSchedulerSQLStatements.alertTableValue.contains(jobUniqueID));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the job table-" + e.getMessage());
		}
	}

	public void verifyJobTable() {
		try {
			Assert.assertTrue(fetchJobTableColumnValues.get("jobTypeValue").equalsIgnoreCase(jobTypeValue));
			LOGGER.info("The new Job was added and visible in UI");
			JobSchedulerSQLStatements.getJobSchdulereStatus(jobUniqueID);
			Assert.assertTrue(JobSchedulerSQLStatements.alertTableValue.contains(jobUniqueID));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the job table-" + e.getMessage());
		}
	}

	public void runStartStopTime() {
		try {
			runStartTime.isElementPresent();
			runStartTime.type("07:30");
			runEndTime.isElementPresent();
			runEndTime.type("09:00");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update the Run time-" + e.getMessage());
		}
	}

	public void updateRunStartStopTime(String startTime, String stopTime) {
		try {
			startTimeValue = startTime;
			stopTimeValue = stopTime;
			runStartTime.isElementPresent();
			runStartTime.type(startTimeValue);

			runEndTime.isElementPresent();
			runEndTime.type(stopTimeValue);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update the Run time-" + e.getMessage());
		}
	}

	public void runStartStopTimeUpdated() {
		try {
			runStartTime.isElementPresent();
			runStartTime.type("02:30");
			runEndTime.isElementPresent();
			runEndTime.type("10:00");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update the Run time-" + e.getMessage());
		}
	}

	public void editJob() {
		try {
			editJob.isElementPresent();
			editJob.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to edit the job-" + e.getMessage());
		}
	}

	public void clickJobTableFirstRow() {
		try {
			jobTableFirstRow.isElementPresent();
			jobTableFirstRow.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click firstRow-" + e.getMessage());
		}
	}
	
	public void clickJobTableFourthRow() {
		try {
			jobTableFourthRow.isElementPresent();
			jobTableFourthRow.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click Row-" + e.getMessage());
		}
	}

	public void enableJob() {
		try {
			enableJob.isElementPresent();
			enableJob.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to enable the job-" + e.getMessage());
		}
	}

	public void disableJob() {
		try {
			disableJob.isElementPresent();
			disableJob.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to disable the job-" + e.getMessage());
		}
	}
	
	public void verifyNextRun() {
		try {
			
			Assert.assertTrue(ThirdRowEnableStatus.getText().equalsIgnoreCase("false"));
		    VerifyTIme1(currenTime);
						
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify next Run-" + e.getMessage());
		}
		}

	public void verifyScheLastNextRun() {
        try {
        	clickJobTableFourthRow();
        	refreshData();
        	getJobCoulmnValue();
        	VerifyTIme2(currenTime);
		
        } catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify Scheduled Last Next Run-" + e.getMessage());
		}
	}
	

	private void VerifyTIme2(String timeValue) throws Exception {
		try {
		String lastRunTime = findExtendedWebElement(By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[4]//td[4]"))
				.getText();
		String nextRunTime = findExtendedWebElement(By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[4]//td[5]"))
				.getText();
				
	    int loginUTCTime = getDate(timeValue, 5, 7);
		int tableLastRunTime = getDate(lastRunTime, 0, 2);
		int tableNextRunTime = getDate(nextRunTime, 0, 2);
		
		if (loginUTCTime==(tableLastRunTime+1)||loginUTCTime==tableLastRunTime) {
			Assert.assertEquals(loginUTCTime, tableNextRunTime);
			
			String NextRuntimeUpdated = nextRunTime.substring(15, 17);
	  		String RecurringRunTime = findExtendedWebElement(By.xpath("//tbody[@id='jobSchedulerDtlTblId']//tr[4]//td[7]"))
					.getText();
	  
	  		String RecurringRun[]=RecurringRunTime.split(",");
	  		String Time1=RecurringRun[0];
	  		String Time2=RecurringRun[1];
	  		
	  		 		
	  		Assert.assertTrue(NextRuntimeUpdated.contains(Time1)||NextRuntimeUpdated.contains(Time2));
	  	
	    LOGGER.info("The Last Run & Next Run time is updated exactly at the configured Days");
	}
		else {
			Assert.fail("The Last Run & Next Run time is not updated exactly at the configured Days");
			}
		
	
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Unable to verify Time-" + e.getMessage());
	}
      
}

	public void updateSingleRun(String UpdateSingleRun) {
		try {
			updateSingleRunValue = UpdateSingleRun;

			singleRunJobTimings.isElementPresent();
			singleRunJobTimings.type(updateSingleRunValue);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to find single run-" + e.getMessage());
		}
	}

	public void confirmOk() {
		try {
			confirmDisableOk.isElementPresent();
			confirmDisableOk.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click confirm ok-" + e.getMessage());
		}
	}

	public void confirmForceRunOk() {
		try {
			confirmForceRunOk.isElementPresent();
			confirmForceRunOk.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on confirm Force Run Ok-" + e.getMessage());
		}
	}

	public void verifyDisableStatus() {
		try {

			Assert.assertTrue(fetchJobTableColumnValues.get("enabledValue").equalsIgnoreCase("false"));
			LOGGER.info("The Job scheduler was disabled");
			JobSchedulerSQLStatements.getJobDisableStatus(jobUniqueID);
			Assert.assertTrue(JobSchedulerSQLStatements.alertTableValue.contains(activeIdDisabledValue));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the disable status-" + e.getMessage());
		}
	}

	public void verifyEnableStatus() {
		try {

			Assert.assertTrue(fetchJobTableColumnValues.get("enabledValue").equalsIgnoreCase("true"));
			LOGGER.info("The Job scheduler was enabled");
			JobSchedulerSQLStatements.getJobEnableStatus(jobUniqueID);
			Assert.assertTrue(JobSchedulerSQLStatements.alertTableValue.contains(activeIdEnabledValue));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify Enable status-" + e.getMessage());
		}
	}

	public void exchangeSegmentIdValue(String exchangeSegment) {
		try {
			exchangeSegmentValue = exchangeSegment;

			exchangeSegmentId.isElementPresent();
			exchangeSegmentId.type(exchangeSegmentValue);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to edit exchange Segment Id-" + e.getMessage());
		}

	}

	public void updateStoreProcedure(String StoreProcedure) {
		try {
			storeProcedureValue = StoreProcedure;
            
			storedProcedure.scrollTo();
			storedProcedure.isElementPresent();
			storedProcedure.type(storeProcedureValue);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update Store procedure-" + e.getMessage());
		}
	}

	public void updateIndexId(String indexId) {
		try {
			indexIdValue = indexId;
            
			indexIds.scrollTo();
			indexIds.isElementPresent();
			indexIds.type(indexIdValue);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update Index id-" + e.getMessage());
		}

	}

	public void updateSessionWaitTime(String SessionWaitTime) {
		try {
			sessionWaitTimeValue = SessionWaitTime;
            
			sessionWaitTime.scrollTo();
			sessionWaitTime.isElementPresent();
			sessionWaitTime.type(sessionWaitTimeValue);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update session wait time-" + e.getMessage());
		}
	}

	public void scheduledSatDay() {
		try {

			jobScheduledonSaturDay.isElementPresent();
			jobScheduledonSaturDay.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Saturday-" + e.getMessage());
		}
	}

	public void scheduledSunDay() {
		try {
			jobScheduledonSunDay.isElementPresent();
			jobScheduledonSunDay.click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Sunday-" + e.getMessage());
		}
	}

	public void verifyAlertMassage() {
		try {
		    String Message="One or more disabled job(s) have been selected. Please enable the job(s) to Force Run";
		
		    alertDialogMessageId.isElementPresent();
		    alertMessageValue=alertDialogMessageId.getText();
		    
		    Assert.assertTrue(alertMessageValue.contains(Message));
	
		}catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Unable to verify alert message-" + e.getMessage());
	}
	}

	public void verifyForceRunStatus() {
		try {
			saveOk.pause(3);
			Assert.assertTrue(forceRunValue.getText().equalsIgnoreCase("true"));
			LOGGER.info("The Job scheduler Force Run was enabled");
		   
		}catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Unable to verify verify Force Run Status-" + e.getMessage());
	}
	
	}

	public void DeleteJob() {
		try {
			if (afterCreationJobCount > beforeCreationJobCount) {
				jobTableFirstRow.click();
				
				FirstRowValue = Integer.parseInt(jobTableFirstRowElement.getText());
				if (FirstRowValue > 75) {
					deleteJob.isElementPresent();
					deleteJob.click();
					confirmDisableOk.pause(2);
					confirmDisableOk.click();
					confirmDeleteOk.pause(2);
					confirmDeleteOk.click();
					confirmDeleteOk.pause(1);
					LOGGER.info("The created Job is deleted");
				}
		}else {
			LOGGER.info("The created Job is not deleted");
		}
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Delete tab-" + e.getMessage());
		}
	}
	
	
	
}
