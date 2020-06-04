package steps;

import com.grip.page.ContractsPage;
import com.grip.page.ForexPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ForexStepDef extends CucumberRunner {
	Logger LOGGER = Logger.getLogger(ContractsPage.class);
	ForexPage Forexpage = new ForexPage(getDriver());
	ContractsPage contractsPage = new ContractsPage(getDriver());

	@Given("^User will fetch current time for forex in Dashboard page$")
	public void user_will_fetch_current_time_for_forex_in_Dashboard_page() throws Throwable {
		Forexpage.getTimeInDashboard();
	}

	@When("^User navigates to Forex tab under Research page$")
	public void user_navigates_to_Forex_tab_under_Research_page() throws Throwable {
		Forexpage.clickResearch();
		Forexpage.clickForex();
	}

	@Then("^User searches forex data as \"([^\"]*)\" and perform \"([^\"]*)\"$")
	public void user_searches_forex_data_as_and_perform(String searchDataValue, String Operation) throws Throwable {
		Forexpage.searchForexData(searchDataValue);
		Forexpage.holdReleaseOperation(Operation);
	}

	@Then("^User will hold forex and verify the current value as \"([^\"]*)\"$")
	public void user_will_hold_forex_and_verify_the_current_value_as(String holdType) throws Throwable {
		Forexpage.verifyCurrentPriceValue(holdType);
	}

	@When("^User verify the forex hold status and alert table as \"([^\"]*)\"$")
	public void user_verify_the_forex_hold_status_and_alert_table_as(String holdType) throws Throwable {
		Forexpage.verifyHoldAlert(holdType);
	}

	@When("^user verify the forex alert message in alert table$")
	public void user_verify_the_forex_alert_message_in_alert_table() throws Throwable {
		Forexpage.verifyAlert();
	}

	@Then("^User will perform auto based forex release$")
	public void user_will_perform_auto_based_forex_release() throws Throwable {
		Forexpage.verifyAutoHoldAlertMessages();
	}

	@When("^User will search for the forex with comma and verify$")
	public void user_will_search_for_the_forex_with_comma_and_verify() throws Throwable {
		Forexpage.verifySearchListedValues();
	}

	@When("^User searches for Forex Name as \"([^\"]*)\"$")
	public void user_searches_for_Forex_Name_as(String searchDataValue) throws Throwable {
		Forexpage.searchForexData(searchDataValue);
	}

	@When("^User will click Copy name and Forex name should get copied to the clipboard$")
	public void user_will_click_Copy_name_and_Forex_name_should_get_copied_to_the_clipboard() throws Throwable {
		Forexpage.copyName();
	}

	@When("^User will click Copy Id and Forex id should get copied to the clipboard$")
	public void user_will_click_Copy_Id_and_Forex_id_should_get_copied_to_the_clipboard() throws Throwable {
		Forexpage.copyName();
	}

	@When("^User selects all forex rows and verify count$")
	public void user_selects_all_forex_rows_and_verify_count() throws Throwable {
		Forexpage.selectAllRows();
	}

	@Then("^User click on Export to Excel and Forex Report file should be generated in csv format with proper data$")
	public void user_click_on_Export_to_Excel_and_Forex_Report_file_should_be_generated_in_csv_format_with_proper_data()
			throws Throwable {
		Forexpage.forexDownload();
	}

	@Then("^User enters Forex Currentvalue as negative value and validate the same$")
	public void user_enters_Forex_Currentvalue_as_negative_value_and_validate_the_same() throws Throwable {
		Forexpage.negativeNumber();
	}

	@Given("^User will navigate to Forex tab via dashboard subscribed link$")
	public void user_will_navigate_to_Forex_tab_via_dashboard_subscribed_link() throws Throwable {
		Forexpage.clickOnSubscribedLink();
	}

	@When("^User will hold/release multiple forex as \"([^\"]*)\"$")
	public void user_will_hold_release_multiple_forex_as(String actionType) throws Throwable {
		if (actionType.equalsIgnoreCase("Hold") || actionType.equalsIgnoreCase("Release")) {
			Forexpage.holdReleaseMultipleForex(actionType);
		} else {
			Forexpage.holdReleaseForexMultipleTime(actionType);
		}

	}
	
	@Then("^Right click on forex and verify options in context menu$" )
	public void Right_click_on_contract_and_verify_options_in_contextmenu() throws IOException
	{
		int column =21;
		String search="Forex was not held";
		contractsPage.contractMenu(search,column,"Forex");
		contractsPage.Copy(search);
		contractsPage.showContract(search,10, "forexTable2");
		
	}
	
	
}
