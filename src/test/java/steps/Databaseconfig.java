package steps;

import com.grip.page.Databaseconfigpage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class Databaseconfig extends  CucumberRunner {

	Databaseconfigpage databaseobj = new Databaseconfigpage(getDriver());
	
	@Given("^Enter URL and Research page$")
	public void Enter_URL_and_Research_page()
	{
		databaseobj = new Databaseconfigpage(getDriver());
		databaseobj.open();
		databaseobj.validateResearchPage();
	}

	@Then("^user has given with index with auto close value as one$")
	public void Exchange_List_window_and_verify_typed_records() throws Throwable {

		databaseobj.verifyAutoCloseValue();

	}
	@Then("^user verifies the publish by grip as there is no pre-lim close on previous day$")
	public void user_verifies_the_publish_by_grip_as_there_is_no_pre_lim_close_on_previous_day()
	{
		databaseobj.verifyPreviousDay();
	}
	@Then("^user verifies the close tag$")
	public void user_verifies_the_close_tag()
	{
		databaseobj.verifyClosetag();
	}
	@Given("^user has given with index with auto close value as zero$")
	public void user_has_given_with_index_with_auto_close_value_as_zero()
	{
		databaseobj.verifyAutoClose();
	}
	@Then("^user verifies the publish by grip with PC/CLOSE tag on previous day$")
	public void user_verifies_the_publish_by_grip_with_PC_CLOSE_tag_on_previous_day()
	{
		databaseobj.verifyPCTag();
	}
	@Then("^user verifies the close tag for this index$")
	public void user_verifies_the_close_tag_for_this_index()
	{
		databaseobj.verifyCloseStatus();
	}
	@Then("^user verifies the mandatory flag database value as zero$")
	public void user_verifies_the_mandatory_flag_database_value_as_zero()
	{
		databaseobj.verifyMandatoryFlagValue();
	}
	@Then("^user verifies the datapoint field in available data point screen$")
	public void user_verifies_the_datapoint_field_in_available_data_point_screen()
	{
		databaseobj.verifyDatapoint();
	}
}