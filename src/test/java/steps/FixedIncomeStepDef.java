package steps;

import com.grip.page.ContractsPage;
import com.grip.page.FixedIncomePage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

import java.io.IOException;

public class FixedIncomeStepDef extends CucumberRunner {

	public static String dataUpdateValue,WarningUpdateValue;

	Logger LOGGER = Logger.getLogger(ContractsPage.class);
	FixedIncomePage fixedincomePage = new FixedIncomePage(getDriver());
	ContractsPage contractsPage = new ContractsPage(getDriver());

	@Given("^User will fetch time for fixed income in Dashboard page$")
	public void user_will_fetch_time_for_fixed_income_in_Dashboard_page() throws Throwable {
		fixedincomePage.getTimeInDashboard();
	}

	@Given("^User navigates to Fixedincome tab under Research page$")
	public void user_navigates_to_Fixedincome_tab_under_Research_page() throws Throwable {
		fixedincomePage.clickResearch();
		fixedincomePage.clickFixedIncome();
	}

	@Given("^user selects fixedincome as \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" and perform \"([^\"]*)\"$")
	public void user_selects_fixedincome_as_and_perform(String TradingSession, String HoldingStatus, String DataFeed,
                                                        String Operation) throws Throwable {
		fixedincomePage.selectFixed(TradingSession, HoldingStatus, DataFeed, Operation);
	}

	@Then("^User will hold fixedincome and verify the current value as \"([^\"]*)\"$")
	public void user_will_hold_fixedincome_and_verify_the_current_value_as(String holdType) throws Throwable {
		fixedincomePage.verifyCurrentPriceValue(holdType);
	}

	@When("^User verify the fixedincome hold status in table and alert table as \"([^\"]*)\"$")
	public void user_verify_the_fixedincome_hold_status_in_table_and_alert_table_as(String holdType) throws Throwable {
		fixedincomePage.verifyHoldAlert(holdType);
	}

	@Given("^user verify the fixed income alert message in alert table$")
	public void user_verify_the_fixed_income_alert_message_in_alert_table() throws Throwable {
		fixedincomePage.verifyAlert();
	}

	@Given("^User will perform auto based fixedincome release$")
	public void user_will_perform_auto_based_fixedincome_release() throws Throwable {
		fixedincomePage.verifyAutoHoldAlertMessages();
	}

	@When("^User selects fixedincome as \"([^\"]*)\" and verify alert\"([^\"]*)\"$")
	public void user_selects_fixedincome_as_and_verify_alert(String holdtype, String dataUpdate) throws Throwable {
		fixedincomePage.baseLineHoldStatus(holdtype);
		fixedincomePage.verifyBaselineMessages(dataUpdate);
	}

	@Given("^User manually hold/release fixedincome as \"([^\"]*)\",\"([^\"]*)\"$")
	public void user_manually_hold_release_fixedincome_as(String fiName, String holdtype) throws Throwable {
		fixedincomePage.fixedIncomeManualHold(fiName, holdtype);
	}

	@When("^User will search for fixedincome as \"([^\"]*)\"$")
	public void user_will_search_for_fixedincome_as(String fixedID) throws Throwable {
		fixedincomePage.searchFixed(fixedID);
	}

	@Given("^User selects all fixedincome rows and verify count$")
	public void user_selects_all_fixedincome_rows_and_verify_count() throws Throwable {
		fixedincomePage.selectAllRows();
	}

    @When("^User will click Copy name and fixedincome name should get copied to the clipboard$")
    public void user_will_click_Copy_name_and_fixedincome_name_should_get_copied_to_the_clipboard() throws Throwable {
	fixedincomePage.copyName();		
     }

	@When("^User will click Copy Id and fixedincome id should get copied to the clipboard$")
	public void user_will_click_Copy_Id_and_fixedincome_id_should_get_copied_to_the_clipboard() throws Throwable {
		fixedincomePage.copyId();
	}

	@When("^User will click Report and fixedincome name populated in Asset edit box$")
	public void user_will_click_Report_and_fixedincome_name_populated_in_Asset_edit_box() throws Throwable {
		fixedincomePage.clickReport();;
	}

	@Then("^User will perform actions on fixedincome as \"([^\"]*)\"$")
	public void user_will_perform_actions_on_fixedincome_as(String showType) throws Throwable {
		fixedincomePage.showDetails(showType);
	}
	@Then("^User click on Export to Excel and Fixed Income Report file should be generated in csv format with proper data$")
	public void user_click_on_Export_to_Excel_and_Fixed_Income_Report_file_should_be_generated_in_csv_format_with_proper_data() throws Throwable {
		fixedincomePage.fixedDownload();
	}
	
	@Given("^User enters hold price as negative value and validate the same$")
	public void user_enters_hold_price_as_negative_value_and_validate_the_same() throws Throwable {
		fixedincomePage.negativeNumber();
	}

	@Given("^User will navigate to fixedincome tab via dashboard subscribed link$")
	public void user_will_navigate_to_fixedincome_tab_via_dashboard_subscribed_link() throws Throwable {
		fixedincomePage.clickOnSubscribedLink();
	}

	@Given("^User will hold/release multiple fixedIncome as \"([^\"]*)\"$")
	public void user_will_hold_release_multiple_fixedIncome_as(String actionType) throws Throwable {
		if (actionType.equalsIgnoreCase("Hold") || actionType.equalsIgnoreCase("Release"))
			fixedincomePage.holdReleaseMultipleFixed(actionType);
		else
			fixedincomePage.holdReleaseFixedMultipleTime(actionType);
	}
	

@Given("^User will search for the fixedincome with comma and verify$")
public void user_will_search_for_the_fixedincome_with_comma_and_verify() throws Throwable {
	fixedincomePage.verifySearchListedValues();
}

@When("^User updates the fixedincome baseline threshold value$")
public void user_updates_the_fixedincome_baseline_threshold_value() throws Throwable {
	fixedincomePage.updateThreshold();
}

@When("^User performs release operation on fixedincome\"([^\"]*)\"$")
public void user_performs_release_operation_on_fixedincome(String holdType) throws Throwable {
	fixedincomePage.performReleaseOperation(holdType);
}

@When("^User performs hold operation on fixedincome\"([^\"]*)\"$")
public void user_performs_hold_operation_on_fixedincome(String holdType) throws Throwable {
	fixedincomePage.performHoldOperation(holdType);
}

@When("^User verifies the fixedincome holdtype as \"([^\"]*)\"$")
public void user_verifies_the_fixedincome_holdtype_as(String holdType) throws Throwable {
	fixedincomePage.verifyHoldChangeStatus(holdType);
}
	
@When("^User selects fixedincome as \"([^\"]*)\" and verify warning alert\"([^\"]*)\"$")
public void user_selects_fixedincome_as_and_verify_warning_alert(String holdtype, String WarningUpdate) throws Throwable {
	fixedincomePage.baseLineHoldStatus(holdtype);
	fixedincomePage.verifyWarningAlertMessages(holdtype,WarningUpdate);
}
	
@Given("^User Select different option available in fixedincome combo filter and verify the column names$")
public void user_Select_different_option_available_in_fixedincome_combo_filter_and_verify_the_column_names() throws Throwable {
	fixedincomePage.verifyIndexFilter();
}

@Then("^Right click on fixedIncome and verify options in context menu$" )
public void Right_click_on_contract_and_verify_options_in_contextmenu() throws IOException
{	
	int column =19;
	String search="fixed income security was";
	contractsPage.contractMenu(search,column,"FixedIncome");
	contractsPage.Copy(search);
	contractsPage.showContract(search,9, "fiiTable2");
}

@When("^User will search for fixedincome as \"([^\"]*)\" and perform hold operation$")
public void user_will_search_for_fixedincome_as_and_perform_hold_operation(String fixedID) throws Throwable {
	fixedincomePage.searchFixed(fixedID);
	fixedincomePage.rightClickOperation();
	fixedincomePage.holdFixed();
}
}
