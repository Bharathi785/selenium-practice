package steps;

import com.grip.page.DerivativesPage;
import com.grip.page.IndicesPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.IOException;

public class DerivativesStepDef extends CucumberRunner {
	IndicesPage commodityIndicesPage = new IndicesPage(getDriver());
	DerivativesPage derivativesPage =  new DerivativesPage(getDriver());

	@Given("^User navigates to Derivatives tab under Research page$")
	public void user_navigates_to_Derivative_tab_under_Research_page()  {
		commodityIndicesPage.clickResearch();
		derivativesPage.clickDerivative();
	}
	
	@Given("^User navigates to Exchange tab under Research page$")
	public void user_navigates_to_Exchange_tab_under_Research_page()  {
		commodityIndicesPage.clickResearch();
		derivativesPage.clickExchangeTab();
	}
	
	@Given("^User navigates to Support Tab$")
	public void User_navigates_to_Support_Tab()  {
		derivativesPage.clickSupportTab();
	}
	
	@Given("^User fetch the column values of derivative$")
	public void User_fetch_the_current_price_value_of_derivative()  {
		derivativesPage.getDerivativeCoulmnValue();
	}
	
	@Given("^User perfroms actions on processes as \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_perfroms_actions_on_processes_as(String processType1, String processType2, String selectType)  {
		derivativesPage.executeOperations(processType1,processType2,selectType);
	}
	
	@Given("^User will perform right click action on the derivative$")
	public void User_will_perform_right_click_action_on_the_derivative()  {
		derivativesPage.performRightClickAction();
	}
	
	@Given("^User will perform right click action on the exchange$")
	public void User_will_perform_right_click_action_on_the_exchange()  {
		derivativesPage.performRightClickActionExchange();
		derivativesPage.cilckShowDerivative();
	}
	
	@And("^User will click Copy name and derivative name should get copied to the clipboard$")
	public void User_will_click_Copy_name_and_derivative_name_should_get_copied_to_the_clipboard()  {
		derivativesPage.copyName();
	}

	@And("^User will click Copy Id and derivative id should get copied to the clipboard$")
	public void User_will_click_Copy_Id_and_derivative_id_should_get_copied_to_the_clipboard()  {
		derivativesPage.copyId();
	}
	
	@And("^User selects all derivative rows and verify count$")
	public void User_selects_all_derivative_rows_and_verify_count()  {
		derivativesPage.selectAllRows();
	}
	
	@Given("^User will perform actions on Show Exchange Details$")
	public void user_will_perofrm_actions_on_Show_Exchange_Details() throws InterruptedException, IOException {
		derivativesPage.showDetails();
	}
	
	@Then("^User donwloads the CSV file for derivative and verify values$")
	public void click_on_export_to_excel_and_download() throws InterruptedException, IOException {
		derivativesPage.derivativeDownload();
	}
	
	@And("^User verify derivative coulmn header values$")
	public void user_Verify_coulmn_header_values() throws Exception {
		derivativesPage.verifyColumnHeader();
	}
	
	@Given("^User will search for derivative data$")
	public void user_will_search_for_for_derivative_data()  {
		derivativesPage.searchDerivativeData();
	}
	
	@Given("^User validate the update on feed price$")
	public void User_validat_the_update_on_feed_price()  {
		derivativesPage.validateBidPriceData();
	}
	
	@Given("^User will search for Exchange Derivative data$")
	public void user_will_search_for_exchange_derivative_data()  {
		derivativesPage.searchExchangeDerivativeData();
	}
	
	@Given("^User validate the searched data for the next month$")
	public void User_validate_the_searched_data_for_the_next_month()  {
		derivativesPage.verifySearchedData();
	}
	
	@Given("^User will navigate to Derivative tab via dashboard link as \"([^\"]*)\"$")
	public void User_navigates_to_derivative_tab_via_dashboard_link_as(String dasboardLinkType)  {
		derivativesPage.clickDashboardLink(dasboardLinkType);
	}
	
	@Given("^User verify dashboard datawatch and research derivative count$")
	public void User_verify_dashboard_datawatch_and_research_derivative_count()  {
		derivativesPage.verifyCount();
	}
	
	@Given("^User add new column and verify the reflected column in derivative table$")
	public void User_add_new_column_and_verify_the_reflected_column_in_derivative_table()  {
		derivativesPage.addColumn();
		derivativesPage.verifyAddedColumn();
	}
	
	@Given("^User remove new column and verify the reflected column in derivative table$")
	public void User_remove_new_column_and_verify_the_reflected_column_in_derivative_table()  {
		derivativesPage.removeColumn();
		derivativesPage.verifyRemovedColumn();
	}
	
	@And("^User will click Report and run it$")
	public void User_will_click_Report_and_run_it()  {
		derivativesPage.clickReport();
		derivativesPage.runReports();
	}
	
	@And("^User will verify the genarted report$")
	public void User_will_verify_the_genarted_report()  {
		derivativesPage.verifyReportWihtDB();
		derivativesPage.verifyReportWithDownload();
	}
	
	@And("User compare exchange data with T3 as \"([^\"]*)\"$")
	public void User_compare_exchange_data_with_T3_as(String exchangeType)  {
		derivativesPage.clickExchange();
		derivativesPage.compareExchange(exchangeType);
	}
	
	
}
