package steps;

import com.grip.page.ContractsPage;
import com.grip.page.ExchangePage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Then;

import java.io.IOException;


public class Exchange extends  CucumberRunner {

	ExchangePage exchangeobj = new ExchangePage(getDriver());
	ContractsPage contractsPage = new ContractsPage(getDriver());

	@Then("^Exchange List window and verify typed records$")
	public void Exchange_List_window_and_verify_typed_records() throws Throwable {

		exchangeobj.verifyTypedRecord();

	}
	
	@Then("^Exchange List window and verify all records$")
	public void verifyallrecords() throws Throwable {
		exchangeobj.verifyrecords();
	}

	@Then("^select exchange and show stocks of that exchange$")
	public void showStocksExchanges() throws Throwable {

		exchangeobj.showStocks();

	}

	@Then("^select exchange and show indices of that exchange$")
	public void showIndicesExchanges() throws Throwable {

		exchangeobj.showIndices();

	}

	@Then("^select exchange and copy name of that exchange$")
	public void copyName() throws Throwable {

		exchangeobj.copyName();

	}

	@Then("^select exchange and copy id of that exchange$")
	public void copyId() throws Throwable {

		exchangeobj.copyID();

	}

	@Then("^select exchange and select All Rows$")
	public void selectAllRows() throws Throwable {

		exchangeobj.selectAllRows();

	}

	@Then("^select exchange and show contracts of that exchange$")
	public void showContracts() throws Throwable {
		exchangeobj.showContracts();
	}

	@Then("^select exchange and show f11 of that exchange$")
	public void showFii() throws Throwable {
		exchangeobj.fii();
	}

	@Then("^click on download exchange data and verify file has been generated$")
	public void downloadExchange() throws Throwable {
		exchangeobj.downloadExchange();
	}

	@Then("^select an Exchange and Switch to Local Feed Source:Reuters And verify the alert message$")
	public void switchToReuters() throws Throwable {

		exchangeobj.switchToReuters();
	}

	@Then("^select an Exchange and Switch to Local Feed Source:IDC And verify the alert message$")
	public void switchToIDC() throws Throwable {

		exchangeobj.switchToIDC();
	}

	@Then("^select an multiple Exchange and Switch to Local Feed Source:Reuters And verify the alert message$")
	public void switchReutersMul() throws Throwable {

		exchangeobj.switchReutersMul();
	}

	@Then("^select an Exchange and Switch to Local & Remote Feed Source:Reuters And verify the alert message$")
	public void switchLocalRemoteReu() {
		exchangeobj.SwitchLocalRemoteReu();
	}

	@Then("^select an Exchange and Switch to Local & Remote Feed Source:IDC And verify the alert message$")
	public void switchLocalRemoteIDC() {
		exchangeobj.SwitchLocalRemoteIDC();
	}
	@Then("^select multiple Exchange and Switch to Local & Remote Feed Source:Reuters And verify the alert message$")
	public void select_multiple_Exchange_and_Switch_to_Local_Remote_Feed_SourceReuters_And_verify_the_alert_message() throws IOException, InterruptedException
	{
		exchangeobj.switchLocalRemoteReuMul();
	}

	@Then("^select an Exchange and Switch to EOD/RealTime Ticker And verify the alert message$")
	public void eodRealTimeTicker() throws Throwable {

		exchangeobj.eodRealTimeTicker();
	}

	@Then("^select and Exchange and Switch to RealTime Ticker And verify the alert message$")
	public void RealTimeTicker() throws IOException {
		exchangeobj.RealTimeTicker();
	}

	@Then("^select an multiple Exchange and Switch to EOD/RealTime Ticker And verify the alert message$")
	public void EODRealTimeTickerMul() throws Throwable {

		exchangeobj.eodRealTimeTickerMul();
	}

	@Then("^select an Exchange and Switch to EOD/RealTime Ticker And verify the alert message-Negative$")
	public void select_an_Exchange_and_Switch_to_EOD_RealTime_Ticker_And_verify_the_alert_message_Negative() throws Throwable
	{
		exchangeobj.eodRealTimeTickerNegative();
	}
	@Then("^select an Exchange and click on refresh from GRIP And verify the alert message$")
	public void refreshFromGrip() throws Throwable {
		exchangeobj.refreshFromGrip();
	}
	
	@Then("^select multiple Exchange and click refresh from GRIP And verify the alert message$")
	public void refreshFromGripMul() throws Throwable {
		exchangeobj.refreshFromGripMul();
	}

	@Then("^select an Exchange and Switch to Composite Ticker And verify the alert message$")
	public void CompositeTicker() throws IOException, InterruptedException {
		exchangeobj.compositeTicker();
	}

	@Then("^View auto Hold market window will open with proper data$")
	public void viewHoldLimits() {
		exchangeobj.viewHoldLimits();
	}

	@Then("^Select multiple Exchanges - Right Click and choose View Auto Hold Limits$")
	public void viewHoldLimitsMul() {
		exchangeobj.viewHoldLimitsMul();
	}

	@Then("^Select an Exchanges Right Click and choose Update Trading date Previous date$")
	public void updateTradingDatePrev() throws InterruptedException, IOException {
		exchangeobj.updateTradingDatePrev();
	}

	@Then("^Select an Exchanges Right Click and choose Update Trading date Next date$")
	public void updateTradingDateNext() throws InterruptedException, IOException {
		exchangeobj.updateTradingDateNext();
	}

	@Then("^Select an Exchanges Right Click and choose Update Trading date current date$")
	public void updateTradingDateCurrent() throws InterruptedException, IOException {
		exchangeobj.updateTradingDateCurrent();
	}
	@Then("^Select multiple Exchanges Right Click and choose Update Trading date current date$")
	public void Select_multiple_Exchanges_Right_Click_and_choose_Update_Trading_date_current_date() throws Exception
	{
		exchangeobj.updateTradingDateMul();
	}
	@Then("^Select an Exchanges Right Click and choose switch to rueters$")
	public void Select_an_Exchanges_Right_Click_and_choose_switch_to_rueters()
	{
		exchangeobj.negativescenario();
	}
	/*@Then("^select an Exchange and click on next day And verify the alert message pop-up$")
	public void NextDay() throws Throwable {

		String NewDay = "New Day";
		exchangeobj.oneNextDay(NewDay);
	}*/
	@Then("^select an Exchange and click on next day And verify the alert message sessionbreak$")
	public void select_an_Exchange_and_click_on_next_day_And_verify_the_alert_message_sessionbreak()
	{
		exchangeobj.nextDaySessionBreak();
	}

	@Then("^select an Exchange and click on next day And verify the alert message open$")
	public void NextDay1() throws Throwable {

		String Open = "open";
		exchangeobj.oneNextDay(Open);
	}

	@Then("^select an Exchange and click on next day And verify the alert message closed state$")
	public void NextDay3() throws Throwable {

		String Close = "close";
		exchangeobj.oneNextDay(Close);
	}
	/*@Then("^select multiple Exchange and click on next day$")
	public void select_multiple_Exchange_and_click_on_next_day() throws IOException
	{
		exchangeobj.mulNextDay();
	}*/
	
	@Then("^select an Exchange and click on refresh from T3 And verify the alert message session break$")
	public void refreshFromT3sessionBreak() throws Throwable {
		exchangeobj.refreshFromT3Sessionbreak();

	}
	@Then("^select an Exchange and click on refresh from T3 And verify the alert message New Day$")
	public void refreshFromT3NewDay() throws Throwable {
		String NewDay = "NewDay";
		exchangeobj.refreshFromT3(NewDay);
	}

	@Then("^select an Exchange and click on refresh from T3 And verify the alert message open$")
	public void refreshFromT3Open() throws Throwable {
		String Open = "open";
		exchangeobj.refreshFromT3(Open);

	}
	@Then("^select an Exchange and click on refresh from T3 And verify the alert message closed$")
	public void refreshFromT3close() throws Throwable {
		String close = "close";
		exchangeobj.refreshFromT3(close);

	}
	@Then("^select an Exchange and click on refresh from T3 And verify the alert message multiple$")
	public void refreshFromT3Mul() throws Throwable {
		
		exchangeobj.refreshFromT3Mul();

	}
	@Then("^verify the alert message in alert windown of autorelease$")
	public void verify_the_alert_message_in_alert_windown_of_autorelease()
	{
		exchangeobj.autoRelease();
	}
	@Then("^verify the alert message in alert window of stock cannot be loaded$")
	public void verify_the_alert_message_in_alert_window_of_stock_cannot_be_loaded()
	{
		exchangeobj.stockLoaded();
	}
	
	@Then("^Right click on Exchange and verify options in context menu$" )
	public void Right_click_on_contract_and_verify_options_in_contextmenu() throws IOException
	{
		int column =20;
		String search="exchange%offi";
		contractsPage.contractMenu(search,column,"Exchange");
		contractsPage.Copy(search);
		
		contractsPage.showContract(search,12, "exchangeTable2");
		
	}



}
	 


	  
	   
	  
	  

	  
	  
