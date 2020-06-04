package steps;

import com.grip.page.ContractsPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ContractsStepDef extends CucumberRunner {
	
	public static String dataUpdateValue,thresholdValue,WarningUpdateValue;

	Logger LOGGER = Logger.getLogger(ContractsPage.class);
	ContractsPage contractsPage = new ContractsPage(getDriver());
	


	@Given("^User will fetch time in Dashboard page$")
    public void user_will_fetch_time_in_Dashboard_page() throws Throwable {
     	contractsPage.getTimeInDashboard();
      }

	@Given("^User navigates to Contracts tab under Research page$")
	public void user_navigates_to_Contracts_tab_under_Research_page() throws Throwable {
		contractsPage.clickResearch();
		contractsPage.clickContract();
	}

	@Given("^User selects contract from Exchange as \"([^\"]*)\"$")
	public void user_selects_contract_from_Exchange_as(String ExchangeName) throws Throwable {
		contractsPage.clickResearch();
		contractsPage.contractsNoHold(ExchangeName);
	}

	@Given("^user selects contracts as \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" and perform \"([^\"]*)\"$")
	public void user_selects_contracts_as_and_perform(String TradingSession, String HoldingStatus, String DataFeed,
                                                      String Operation) throws Throwable {
		contractsPage.selectContract(TradingSession, HoldingStatus, DataFeed, Operation);
	}

	@Then("^User will hold contracts and verify the current value as \"([^\"]*)\"$")
	public void user_will_hold_contracts_and_verify_the_current_value_as(String holdType) throws Throwable {
		contractsPage.verifyCurrentPriceValue(holdType);
	}

	@When("^User verify the hold status in table and alert table as \"([^\"]*)\"$")
	public void user_verify_the_hold_status_in_table_and_alert_table_as(String holdType) throws Throwable {
		contractsPage.verifyHoldAlert(holdType);
	}

	@Given("^user verify the alert message in alert table$")
	public void user_verify_the_alert_message_in_alert_table() throws Throwable {
		contractsPage.verifyAlert();
	}

	@Given("^User will perform auto based contract release$")
	public void user_will_perform_auto_based_contract_release() throws Throwable {
		contractsPage.verifyAutoHoldAlertMessages();
	}

	@When("^User navigates to Exchange as \"([^\"]*)\"$")
	public void user_navigates_to_Exchange_as(String ExchangeName) throws Throwable {
		contractsPage.clickResearch();
		contractsPage.contractsFromExchange(ExchangeName);
	}

	@Then("^User selects contracts as \"([^\"]*)\" and verify alert\"([^\"]*)\"$")
	public void user_selects_contracts_as_and_verify_alert(String holdtype, String dataUpdate) throws Throwable {
		contractsPage.baseLineHoldStatus(holdtype);
		contractsPage.verifyBaselineMessages(dataUpdate);
	}
	
	@Then("^User will modify UpDown percent values as \"([^\"]*)\",\"([^\"]*)\"$")
	public void user_will_modify_UpDown_percent_values_as(String dataUpdate, String thresholdValues) throws Throwable {
		dataUpdateValue = dataUpdate;
		thresholdValue = thresholdValues;
		if (dataUpdateValue.equalsIgnoreCase("update"))
			contractsPage.getPercentValues();
		    contractsPage.modifypercentValues(dataUpdateValue,thresholdValue);
	}
	
	@Given("^User manually hold/release contracts as \"([^\"]*)\",\"([^\"]*)\"$")
	public void user_manually_hold_release_contracts_as(String contractName, String holdtype) throws Throwable {
		contractsPage.contractManualHold(contractName, holdtype);
	}
	
	@Given("^User enters negative value and validate the same$")
	public void user_enters_negative_value_and_validate_the_same() throws Throwable {
		contractsPage.negativeNumber();
	}
	
	@Given("^User will navigate to Contract tab via dashboard subscribed link$")
	public void user_will_navigate_to_Contract_tab_via_dashboard_subscribed_link() throws Throwable {
		contractsPage.clickOnSubscribedLink();
	}

	@Given("^User will select view as \"([^\"]*)\"$")
	public void user_will_select_view_as(String viewType) throws Throwable {
		contractsPage.selectView(viewType);
	}
	
	@When("^User will search for contract as \"([^\"]*)\"$")
	public void user_will_search_for_contract_as(String ContractID) throws Throwable {
		contractsPage.searchContract(ContractID);
	}

	@When("^User will hold/release multiple contracts as \"([^\"]*)\"$")
	public void user_will_hold_release_multiple_contracts_as(String actionType) throws Throwable {
		if (actionType.equalsIgnoreCase("Hold") || actionType.equalsIgnoreCase("Release"))
			contractsPage.holdReleaseMultipleContracts(actionType);
		else
			contractsPage.holdReleaseContractMultipleTime(actionType);
	}
	
	@When("^User will click Copy name and Contract name should get copied to the clipboard$")
	public void user_will_click_Copy_name_and_Contract_name_should_get_copied_to_the_clipboard() throws Throwable {
		contractsPage.copyName();
	}

	@When("^User will click Copy Id and Contract id should get copied to the clipboard$")
	public void user_will_click_Copy_Id_and_Contract_id_should_get_copied_to_the_clipboard() throws Throwable {
		contractsPage.copyId();
	}

	@When("^User will click Report and Contract name populated in Asset edit box$")
	public void user_will_click_Report_and_Contract_name_populated_in_Asset_edit_box() throws Throwable {
		contractsPage.clickReport();
	}
	
	@Given("^User selects all contract rows and verify count$")
	public void user_selects_all_contract_rows_and_verify_count() throws Throwable {
		contractsPage.selectAllRows();
	}
	
	@Then("^User will perform actions on Show Details as \"([^\"]*)\"$")
	public void user_will_perform_actions_on_Show_Details_as(String showType) throws Throwable {
		contractsPage.showDetails(showType);
	}
	@Given("^User updates the baseline threshold value$")
	public void user_updates_the_baseline_threshold_value() throws Throwable {
		contractsPage.updateThreshold();
	}

	@Given("^User verifies the holdtype as \"([^\"]*)\"$")
	public void user_verifies_the_holdtype_as(String holdType) throws Throwable {
		contractsPage.verifyHoldChangeStatus(holdType);
	}
	
	@Given("^User performs hold operation on contracts\"([^\"]*)\"$")
	public void user_performs_hold_operation_on_contracts(String holdType) throws Throwable {
		contractsPage.performHoldOperation(holdType);
	}

	@Given("^User performs release operation on contracts\"([^\"]*)\"$")
	public void user_performs_release_operation_on_contracts(String holdType) throws Throwable {
		contractsPage.performReleaseOperation(holdType);
	}
	
	@Given("^User will search for the contracts with comma and verify$")
	public void user_will_search_for_the_contracts_with_comma_and_verify() throws Throwable {
		contractsPage.verifySearchListedValues();
	}
	
	
	@Then("^User click on Export to Excel and Report file should be generated in csv format with proper data$")
	public void user_click_on_Export_to_Excel_and_Report_file_should_be_generated_in_csv_format_with_proper_data() throws Throwable {
		contractsPage.contractDownload();
	}
	
	@Given("^User Select different option available in combo filter and verify the column names$")
	public void user_Select_different_option_available_in_combo_filter_and_verify_the_column_names() throws Throwable {
		contractsPage.verifyIndexFilter();
	}
	

@When("^User will search for contract as \"([^\"]*)\" and perform hold Operation$")
public void user_will_search_for_contract_as_and_perform_hold_Operation(String contractID) throws Throwable {
	contractsPage.searchContract(contractID);
	contractsPage.rightClickOperation();
	contractsPage.holdContract();
}

@When("^User will modify the warning threshold as \"([^\"]*)\",\"([^\"]*)\"$")
public void user_will_modify_the_warning_threshold_as(String WarningUpdate, String warningValue) throws Throwable {
	WarningUpdateValue = WarningUpdate;

	if (WarningUpdateValue.equalsIgnoreCase("update"))
		contractsPage.getWarningThresholdhValues();
	    contractsPage.modifyWarningValues(WarningUpdate, warningValue);
}

@When("^User selects contracts as \"([^\"]*)\" and verify warning alert\"([^\"]*)\"$")
public void user_selects_contracts_as_and_verify_warning_alert(String holdtype, String WarningUpdate) throws Throwable {
	if (WarningUpdateValue.equalsIgnoreCase("update"))
	contractsPage.baseLineHoldStatus(holdtype);
	contractsPage.verifyWarningAlertMessages(WarningUpdate, holdtype);
}

@Then("^Right click on contract and verify options in context menu$" )
public void Right_click_on_contract_and_verify_options_in_contextmenu() throws IOException
{
	int column =18;
	String search="contract was ";
	contractsPage.contractMenu(search,column,"contracts");
	contractsPage.Copy(search);
	contractsPage.showContract(search,8, "contractTable1");
	
}



@Then("^User will perform right click action on the contracts without test data$")
public void user_will_perform_right_click_action_on_the_contracts_without_test_data() throws Throwable {
	contractsPage.performRightClickNoTestData();
}

@Then("^User will verify the Reset baseline option is present or not for contracts$")
public void user_will_verify_the_Reset_baseline_option_is_present_or_not_for_contracts() throws Throwable {
	contractsPage.verifyResetBaseline();
}

@Then("^User will click on Reset baseline and verify the popup$")
public void user_will_click_on_Reset_baseline_and_verify_the_popup() throws Throwable {
	contractsPage.clickResetBaseLine();
	contractsPage.verifyResetBaseLinePopup();
}

@Given("^User will reset baseline multiple contracts and validate the popup$")
public void user_will_reset_baseline_multiple_contracts_and_validate_the_popup() throws Throwable {
	contractsPage.resetBaselineMultipleContracts();
}


@Given("^User navigates to Contracts tab via dashboard unsubscribed link$")
public void user_navigates_to_Contracts_tab_via_dashboard_unsubscribed_link() throws Throwable {
	contractsPage.clickOnNotFoundLink();
}

}
