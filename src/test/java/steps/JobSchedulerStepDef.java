package steps;


import com.grip.page.JobSchedulerPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Given;


public class JobSchedulerStepDef extends CucumberRunner {

	JobSchedulerPage jobScheduler = new JobSchedulerPage(getDriver());

	@Given("^User navigates to JobSchduler under Data maintain page$")
	public void User_navigates_to_JobSchduler_under_Data_maintain_page() throws Throwable {
		jobScheduler.clickDataMaintain();
		jobScheduler.clickjobScheduler();
	}
	
	@Given("^User will click on Add new job and choose the job type as \"([^\"]*)\"$")
	public void User_will_click_on_Add_new_job_and_choose_the_job_type_as(String JobTypeName) throws Throwable {
		jobScheduler.clickaddNewJob();
        jobScheduler.selectJobType(JobTypeName);
	}
	
	@Given("^User will click on schedule type as Single run$")
	public void User_will_click_on_schedule_type_as_Single_run() throws Throwable {
		jobScheduler.singleRun();
	}
	
	@Given("^User will click on schedule type as Recurring run$")
	public void User_will_click_on_schedule_type_as_Recurring_run() throws Throwable {
		jobScheduler.recurringRun();
	}
	
	
	@Given("^User will type the single run timing as \"([^\"]*)\"$")
	public void User_will_type_the_single_run_timing_as(String SingleRun) throws Throwable {
		jobScheduler.singleRunJobTimings(SingleRun);
	}
	
	@Given("^User will select the scheduled days as \"([^\"]*)\"$")
	public void User_will_select_the_scheduled_days_as(String ScheduledDays) throws Throwable {
		if(ScheduledDays.equalsIgnoreCase("Sun")) {
		jobScheduler.scheduledSunDay();
		}
		else if(ScheduledDays.equalsIgnoreCase("Sat")) {
	    jobScheduler.scheduledSatDay();
		}
	}
	
	
	@Given("^User will delete the job from the table as \"([^\"]*)\"$")
	public void User_will_delete_the_job_from_the_table_as(String Delete) throws Throwable {
		if(Delete.contains("yes")) {
		jobScheduler.clickDeleteJob();
		}
	}
		
	@Given("^User will delete the job id from the table$")
	public void User_will_delete_the_job_id_from_the_table() throws Throwable {
		jobScheduler.DeleteJob();
	}		
	
	@Given("^User will type the Store procedure as \"([^\"]*)\"$")
	public void User_will_type_the_Store_procedure_as(String StoreProcedure) throws Throwable {
		jobScheduler.updateStoreProcedure(StoreProcedure);
	}
	
	@Given("^User will type the Session Wait Time as \"([^\"]*)\"$")
	public void User_will_type_the_Session_Wait_Time_as(String SessionWaitTime) throws Throwable {
		jobScheduler.updateSessionWaitTime(SessionWaitTime);
	}
	
	@Given("^User will type the Index Ids as \"([^\"]*)\"$")
	public void User_will_type_the_Index_Ids_as (String IndexId) throws Throwable {
		jobScheduler.updateIndexId(IndexId);
	}
	
	
	@Given("^User will click on Edit button and modify JOB and save as \"([^\"]*)\"$")
	public void User_will_click_on_Edit_button_and_modify_JOB_and_save_as(String SingleRun) throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.editJob();
		jobScheduler.singleRun();
		jobScheduler.singleRunJobTimings(SingleRun);
	}
	
	
	
	
	@Given("^User will type the Recurring run timing as \"([^\"]*)\"$")
	public void User_will_type_the_Recurring_run_timing_as(String Recurring) throws Throwable {
		jobScheduler.recurringRunJobTimings(Recurring);
	}
	
	@Given("^User will type run Start and Stop time as \"([^\"]*)\"$")
	public void User_will_type_run_Start_and_Stop_time_as(String RunTime) throws Throwable {
		if(RunTime.equalsIgnoreCase("Yes")) {
		jobScheduler.runStartStopTime();
		}
		else if (RunTime.equalsIgnoreCase("No")) {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.editJob();	
	    jobScheduler.runStartStopTimeUpdated();
		jobScheduler.save();
		jobScheduler.saveOk();
		jobScheduler.getJobCoulmnValue();
		jobScheduler.verifyJobTable();
		
		}
	}
	
	
	@Given("^User will enable the job and save as \"([^\"]*)\"$")
	public void User_will_enable_the_job_and_save_as(String Enabled) throws Throwable {
		jobScheduler.enableJob(Enabled);
		jobScheduler.save();
		jobScheduler.saveOk();
		jobScheduler.jobIdSorting();
	}
	
	@Given("^User will type run StartTime and StopTime as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_will_type_run_StartTime_and_StopTime_as(String StartTime, String StopTime) throws Throwable {
	    jobScheduler.updateRunStartStopTime(StartTime, StopTime);
	}
	
	
		
	@Given("^User will disable the job and save as \"([^\"]*)\"$")
	public void User_will_disable_the_job_and_save_as(String EnableUpdate) throws Throwable {
		jobScheduler.enableJob(EnableUpdate);
		jobScheduler.save();
		jobScheduler.saveOk();
		
	}
		
	@Given("^User will select all the scheduled days$")
	public void User_will_select_all_the_scheduled_days() throws Throwable {
		jobScheduler.scheduledAllDays();
	}
	
	@Given("^User fetch the current job id details$")
	public void User_fetch_the_current_job_id_details() throws Throwable {
		jobScheduler.getJobCoulmnValue();
	}
	
	
	@Given("^User will verify the jobs table and database as \"([^\"]*)\"$")
	public void User_will_verify_the_jobs_table_and_database_as(String JobTypeId) throws Throwable {
		jobScheduler.getJobID();
		jobScheduler.getJobTypeId(JobTypeId);
		jobScheduler.verifyJobTable();
	}
	
	@Given("^User will click on Edit button and modify JOB and save as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_will_click_on_Edit_button_and_modify_JOB_and_save_as(String SingleRun, String UpdateSingleRun) throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.editJob();
		jobScheduler.updateSingleRun(UpdateSingleRun);
		jobScheduler.disableScheduledDays();
		jobScheduler.save();
		jobScheduler.saveOk();
		jobScheduler.getJobCoulmnValue();
		if(UpdateSingleRun.equalsIgnoreCase("12:50,03:20,06:30")) {
			jobScheduler.verifyJobTableStatus(UpdateSingleRun);
		}
				
	}
	
	
	  
	 @Given("^User will verify the Disable status and Enable the Job$")
	 public void User_will_verify_the_Disable_status_and_Enable_the_Job() throws Throwable {
			jobScheduler.getJobCoulmnValue();
			jobScheduler.verifyDisableStatus();
			jobScheduler.clickJobTableFirstRow();
			jobScheduler.enableJob();
			jobScheduler.confirmOk();
			jobScheduler.saveOk();
			jobScheduler.getJobCoulmnValue();
			jobScheduler.verifyEnableStatus();
			
		}

	@Given("^User will disable the job and verify the same$")
	public void User_will_disable_the_job_and_verify_the_same() throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.disableJob();
		jobScheduler.confirmOk();
		jobScheduler.saveOk();
		jobScheduler.getJobCoulmnValue();
		jobScheduler.verifyDisableStatus();
		
	}
	
	@Given("^User will enable the job and verify the same$")
	public void User_will_enable_the_job_and_verify_the_same() throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.enableJob();
		jobScheduler.confirmOk();
		jobScheduler.saveOk();
		jobScheduler.getJobCoulmnValue();
		jobScheduler.verifyEnableStatus();
	
	}
	
	
	
	@Given("^User will force run the inactive job and verify the alert message$")
	public void User_will_force_run_the_inactive_job_and_verify_the_alert_message() throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmOk();
		jobScheduler.verifyAlertMassage();
		jobScheduler.saveOk();
	
	}
	
	@Given("^User will enable the job and force run the job$")
	public void User_will_enable_the_job_and_force_run_the_job() throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.enableJob();
		jobScheduler.confirmOk();
		jobScheduler.saveOk();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmForceRunOk();
		jobScheduler.saveOk();
	}
	
	
	@Given("^User will verify the Force run status and refresh the job and verify the last run$")
	public void User_will_verify_the_Force_run_status_and_refresh_the_job_and_verify_the_last_run() throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickRefreshData();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmForceRunOk();
		jobScheduler.saveOk();
		jobScheduler.verifyForceRunStatus();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickRefreshData();
		jobScheduler.verifyLastRun();
	}
	
	
	@Given("^User will type Exchange Segment Id as \"([^\"]*)\"$")
	public void User_will_type_Exchange_Segment_Id_as(String ExchangeSegmentId) throws Throwable {
		jobScheduler.exchangeSegmentIdValue(ExchangeSegmentId);
	
      }
	
	
	@Given("^User will fetch time in Dashboard page for jobscheduler$")
	public void User_will_fetch_time_in_Dashboard_page_for_jobscheduler() throws Throwable {
		jobScheduler.getTimeInDashboard();
	}
	
	@Given("^User will refresh the job and verify the last and next run for Single time$")
	public void User_will_refresh_the_job_and_verify_the_last_and_next_run_for_Single_time() throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmForceRunOk();
		jobScheduler.saveOk();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickRefreshData();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmForceRunOk();
		jobScheduler.saveOk();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickRefreshData();
		jobScheduler.verifyLastRun();
		jobScheduler.verifyNextRunSingleTime();
	}
	
	
	@Given("^User will refresh the job and verify the last and next run for Recurring time$")
	public void User_will_refresh_the_job_and_verify_the_last_and_next_run_for_Recurring_time() throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmForceRunOk();
		jobScheduler.saveOk();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickRefreshData();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmForceRunOk();
		jobScheduler.saveOk();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickRefreshData();
		jobScheduler.verifyLastRun();
		jobScheduler.verifyNextRunRecurringTime();
		
	}
	
	
	@Given("^User will refresh the job and verify the last and next run for Single and Recurring time$")
	public void User_will_refresh_the_job_and_verify_the_last_and_next_run_for_Single_and_Recurring_time() throws Throwable {
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmForceRunOk();
		jobScheduler.saveOk();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickRefreshData();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickForceRunJob();
		jobScheduler.confirmForceRunOk();
		jobScheduler.saveOk();
		jobScheduler.clickJobTableFirstRow();
		jobScheduler.clickRefreshData();
		jobScheduler.verifyLastRun();
		jobScheduler.verifyNextRunSingleRecurringTime();
		
	}
	
	
	@Given("^User will check the enable status and verify the last run$")
	public void User_will_check_the_enable_status_and_verify_the_last_run() throws Throwable {
		jobScheduler.verifyNextRun();
		
	}
	
	
	@Given("^User will refresh the data for Scheduled Days and verify the last run and next run$")
	public void User_will_refresh_the_data_for_Scheduled_Days_and_verify_the_last_run_and_next_run() throws Throwable {
		
		jobScheduler.verifyScheLastNextRun();
		
	}
	
	@Given("^User will delete the Jobs from the table as \"([^\"]*)\"$")
	public void User_will_delete_the_Jobs_from_the_table_as(String Delete) throws Throwable {
		if(Delete.contains("yes")) {
		jobScheduler.jobIdSorting();
		jobScheduler.clickDeleteJobCleanUp();
		
		}
	}
	
	
}

