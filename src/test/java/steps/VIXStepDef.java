package steps;

import com.grip.DBconnection.*;
import com.grip.page.VIXPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class VIXStepDef extends CucumberRunner{
	
	VIXPage VIX = new VIXPage(getDriver());
	
	@Then("^User has given with VIX index with auto close value as one$")
	public void User_has_given_with_VIX_index_with_auto_close_value_as_one() throws Throwable {
		VIX.verifyAutoCloseValue();
	}
	
	@Then("^User verify the publish by grip as there is no prelim close on previous day$")
	public void User_verify_the_publish_by_grip_as_there_is_no_prelim_close_on_previous_day(){
		VIX.verifyPreviousDay();
	}
	
	@Then("^User verify the close tag$")
	public void User_verify_the_close_tag() {
		VIX.verifyClosetag();
	}
	
	@Given("^User will perform actions in VIX index on Show Details as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_will_perform_actions_in_VIX_index_on_Show_Details_as(String showType, String IndexData) throws Throwable {
		VIX.showDetails(showType,IndexData);
	}
	
	@Given("^User will fecth time in Dashboard page for VIX$")
	public void user_will_fecth_time_in_Dashboard_page_for_VIX() throws Throwable {
		    VIX.getTimeInDashboard();
	}
	 
	@Given("^User will perform Show indices and verify the index as \"([^\"]*)\"$")
	public void User_will_perform_Show_indices_and_verify_the_index_as(String ShowType) throws Throwable {
		    VIX.showIndices(ShowType);
	}
	
	@And("^User publish close from T3 and Grip for VIX index and verify alerts as \"([^\"]*)\"$")
	public void User_publish_close_from_T3_and_Grip_for_VIX_index_and_verify_alerts_as(String PublishType) throws Throwable {
		if (PublishType.equalsIgnoreCase("from T3")) {
			VIX.clickPublishCloseFromT3();
			VIX.verifyPublishcloseFromT3VIX();
		} else if (PublishType.equalsIgnoreCase("from GRIP")) {
			VIX.clickAdvanced();
			VIX.clickPublishCloseFromGrip();
			VIX.verifyPublishCloseFromGripVIX();
		}
	}
	
	@Given("^User will refresh index value from Grip and verify the Alert message in UI$")
	public void User_will_refresh_index_value_from_Grip_and_verify_the_Alert_message_in_UI() throws Throwable {
		    VIX.clickAdvanced();
		    VIX.verifyRefreshFromGripVIX();
	}
	
	@Given("^User will refresh index value from T3 and verify the Alert message in UI$")
	public void User_will_refresh_index_value_from_T3_and_verify_the_Alert_message_in_UI() throws Throwable {
		    VIX.clickRefreshFromT3VIX();
		    VIX.clicksaveIndexOk();
		    VIX.verifyRefreshFromT3VIX();    
		
	}
	
	@And("^Select different option available in combo filter and verify the column names$")
	public void Select_different_option_available_in_combo_filter_and_verify_the_column_names() throws Exception {
		    VIX.verifyHeaders();
	}
	
	@Given("^User will navigate to VIX Index tab via dashboard link as \"([^\"]*)\"$")
	public void User_navigates_to_VIX_Index_tab_via_dashboard_link_as(String dasboardLinkType) throws Throwable {
		    VIX.clickDashboardLink(dasboardLinkType);
	}
	
	@Given("^User will update Date Time and Minutes passed to Filter Report section as \"([^\"]*)\"$")
	public void User_will_update_Date_Time_and_Minutes_passed_to_Filter_Report_section_as(String Minutes) throws Throwable {
		    VIX.feedPublishDate();
		    VIX.setMinutes(Minutes);
	}
	
	@And("^User fetch the current price value of index for VIX$")
	public void User_fetch_the_current_price_value_of_index_for_VIX() throws Exception {
		    VIX.getIndicesCoulmnValue();
	}
	
	@And("^User will verify the Tick status for the Index$")
	public void User_will_verify_the_Tick_status_for_the_Index() throws Exception {
		    VIX.verifyTickStatus();
	}
	
	@And("^User fetch the stop time value of index for VIX$")
	public void User_fetch_the_stop_time_value_of_index_for_VIX() throws Exception {
		    VIX.getStopTimeValue();
	}
	
	@And("^User will click on report$")
	public void User_will_click_on_report() throws Exception {
		    VIX.clickReportOption();
	}
}
