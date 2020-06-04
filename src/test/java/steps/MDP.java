package steps;

import com.grip.page.MDPpage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class MDP extends CucumberRunner {
	
	
	MDPpage mdppage;

	@Given("^Enter URL and validate dashboard page$")
	public void validateDashboardpage() throws Throwable {
		mdppage = new MDPpage(getDriver());
		mdppage.open();
		mdppage.validatedashboard();
	}
	
	@Then("^User clicks on EODexchange and it directs to exchange window$")
	public void User_clicks_on_EODexchange_and_it_directs_to_exchange_window()
	{
		mdppage.clickEodExchange();	
		
	}
	@Then("^user verifies the exchange id with database$")
	public void user_verifies_the_exchange_id_with_database()
	{
		mdppage.verifyEodExchangeID();
	}
	@And("^user verifies the total count eodexchange with database$")
	public void user_verifies_the_total_count_eodexchange_with_database()
	{
	mdppage.verifyEodExchangeCount();
	}
	@Then("^user click on exchange and select show stock$")
	public void user_click_on_exchange_and_select_show_stock()
	{
		mdppage.verifyShowStock();
	}
	@Then("^user verifies the total count stock with databasecount$")
	public void user_verifies_the_total_count_stock_with_databasecount()
	{
		mdppage.verifyStockCount();
	}
	@Then("^user provided with column and table to verify$")
	public void user_provided_with_column_and_table_to_verify()
	{
		mdppage.verifyTableColumn();
	}
	@Then("^user verify the columns with mview Reuters feed source$")
	public void user_verify_the_columns_with_mvieww_Reuters_feed_source()
	{
		mdppage.verifymviewReuters();
	}
	@Then("^user provided with column and table$")
	public void user_provided_with_column_and_table()
	{
		mdppage.verifyTable();
	}
	@Then("^user verify the columns with another table IDC feed source$")
	public void user_verify_the_columns_with_another_table_IDC_feed_source()
	{
		mdppage.verifymviewIDC();
	}
	@Then("^Verify persistent in tick log table for NASDAQ Stock Exchange$")
	public void Verify_persistent_in_tick_log_table_for_NASDAQ_Stock_Exchange()
	{
		mdppage.verifyTickLogTable();
	}
	@Then("^Verify persistent in history table for NASDAQ Stock Exchange$")
	public void Verify_persistent_in_history_table_for_NASDAQ_Stock_Exchange()
	{
		mdppage.verifyHistoryTable();
	}
}

	