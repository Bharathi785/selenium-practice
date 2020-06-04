package steps;

import com.grip.DBconnection.ReportSelectStatements;
import com.grip.page.ReportsPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.IOException;


public class ReportsStepDef extends CucumberRunner {


    ReportsPage reportsPage = new ReportsPage(getDriver());

    @When("^user clicks on the reports tab$")
    public void reportsTab() {
        reportsPage.reportsTab();
    }

    @And("^Index \"([^\"]*)\"is passed to Filter Report section$")
    public void getIndex(String Index) {
        reportsPage.getIndexValue(Index);
    }

    @And("^Asset \"([^\"]*)\"is passed to Filter Report section$")
    public void getAsset(String Asset) {
        reportsPage.getAssetValue(Asset);
    }

    @And("^click on Index Ticks option$")
    public void indexTickOption() {
        reportsPage.clickIndexTicks();
    }

    @And("^click on Index ICD option$")
    public void indexICDOption() {
        reportsPage.clickIndexICD();
    }

    @And("^click on Asset time and Sales option$")
    public void assetTimeAndSalesoption() {
        reportsPage.assetTimeAndSales();
    }

    @And("^uncheck Asset Time and Sales option$")
    public void uncheckAssetTimeAndSalesoption() {
        reportsPage.uncheckAssetTimeAndSalesOption();
    }

    @And("^click on Alerts option$")
    public void assetTimeSalesAndAlerts() {
        reportsPage.alerts();
    }

    @And("^click on Related Alerts option$")
    public void assetTimeSalesAndRelatedAlerts() {
        reportsPage.relatedAlerts();
    }

    @And("^Minutes \"([^\"]*)\"is passed to Filter Report section$")
    public void setMinutes(String Minutes) {
        reportsPage.setMinutes(Minutes);
    }


    @And("^select decrement option in Filter Report section$")
    public void selectIncrementOption() {
        reportsPage.selectDecrement();
    }

    @Then("^records should be generated in report$")
    public void getIndexRecordReport() {
        reportsPage.getReports();
    }

    @And("^validate the records in Filter Report section$")
    public void validateRecords() {
        reportsPage.validateRecords();
    }

    @Then("^download the CSV report$")
    public void downloadIndexRecordReport() {
        reportsPage.downloadReport();
    }

    @And("^check the count in CSV report$")
    public void checkCSVRecordCount() throws IOException {
        String homePath = System.getProperty("user.home") + "/Downloads";
        File LatestFile = reportsPage.getLatestFileFromDir(homePath);
        reportsPage.countRecord(LatestFile);
        reportsPage.readCSVData(LatestFile);
    }

    @And("^check the count in index constituents details report$")
    public void checkIndexConstituentsRecordCount() throws IOException {
        String homePath = System.getProperty("user.home") + "/Downloads";
        File LatestFile = reportsPage.getLatestFileFromDir(homePath);
        reportsPage.checkIndexConstituentsRecordCount(LatestFile);

    }

    @Then("^verify the instrument value \"([^\"]*)\"in Reports Filter section$")
    public void verifyInstrumentValue(String expected_instrument) {
        reportsPage.verifyInstrumentValue(expected_instrument);
    }

    @Then("^verify the event type value \"([^\"]*)\"in Reports Filter section$")
    public void verifyEventTypeValue(String expected_eventType) {
        reportsPage.verifyEventTypeValue(expected_eventType);
    }

    @Then("^verify the asset instrument value \"([^\"]*)\"in Reports Filter section$")
    public void verifyAssetInstrumentValue(String expected_asset_instrument) {
        reportsPage.verifyAssetInstrumentValue(expected_asset_instrument);
    }

    @Then("^verify the asset event type value \"([^\"]*)\"in Reports Filter section$")
    public void verifyAssetEventTypeValue(String expected_asset_eventType) {
        reportsPage.verifyAssetEventTypeValue(expected_asset_eventType);
    }

    @Then("^verify the alert instrument value \"([^\"]*)\"in Reports Filter section$")
    public void verifyAlertInstrumentValue(String expected_alert_instrument) {
        reportsPage.verifyAlertInstrumentValue(expected_alert_instrument);
    }

    @Then("^verify the alert event type value \"([^\"]*)\"in Reports Filter section$")
    public void verifyAlertEventTypeValue(String expected_alert_eventType) {
        reportsPage.verifyAlertEventTypeValue(expected_alert_eventType);
    }

    @And("^verify the title \"([^\"]*)\" of the Index tick record$")
    public void verifyWindowTitle(String expected_window_title) {
        reportsPage.getIndexConstituentsWindowTitle(expected_window_title);
    }

    @And("^search value \"([^\"]*)\" in index constituents details window$")
    public void searchValue(String search_value) {
        reportsPage.searchValue(search_value);
    }

    @And("^download the index constituents details report$")
    public void downloadIndexConstituentsReport() {
        reportsPage.getReportIndexConstituents();
    }

    @And("^verify the settlement window title \"([^\"]*)\"of the Index tick record$")
    public void verifySettlementWindowTitle(String expected_Settlement_window_title) {
        reportsPage.getSettlementWindowTitle(expected_Settlement_window_title);
    }

    @And("^verify the Index vix term summary's Details window is displayed$")
    public void verifyIndexTermSummary() {
        reportsPage.verifyTermSummaryWindow();
    }

    @And("^close the index constituents details window$")
    public void closeIndexConstituentsWindow() {
        reportsPage.closeIndexConstituentsWindow();
    }

    @And("^verify the total records count in the database$")
    public void totalCountDB() throws IOException {
        ReportSelectStatements.getReportsTotalCount();
    }

    @And("^user navigates to ICD list Window$")
    public void getStock() throws IOException {
        reportsPage.verifyICDListWindow();
    }

    @And("^select Open state stock from ICD list window$")
    public void selectStock() {
        reportsPage.selectStockType();
    }

    @And("^user navigates to reports tab$")
    public void navigateToReportTab() {
        reportsPage.navigateToReportTab();
    }

    @And("^user will perform right click action and hold the stock$")
    public void holdStock() {
        reportsPage.holdStock();
    }

    @And("^user will release the holded stock$")
    public void releaseStock() {
        reportsPage.releaseStock();
    }

    @And("^close the settlement details window$")
    public void closeSettlementWindow() {
        reportsPage.closeSettlementWindow();
    }

    @And("^close the term summary details window$")
    public void closeTermSummaryWindow() {
        reportsPage.closeTermSummaryWindow();
    }

    @Then("^user will perform right click action and release the index$")
    public void releaseIndex() {
        reportsPage.releaseIndex();
    }

    @And("^search value \"([^\"]*)\" in term summary window$")
    public void searchValueTermSummaryWindow(String search_value) {
        reportsPage.searchValueTermSummaryWindow(search_value);
    }

    @And("^search value \"([^\"]*)\" in index settlement details window$")
    public void searchValueSettlementDetailsWindow(String search_value) {
        reportsPage.searchValueSettlementDetailsWindow(search_value);
    }

    @And("^download the index settlement details report$")
    public void downloadIndexSettlementReport() {
        reportsPage.downloadIndexSettlementReport();
    }

    @And("^download the term summary report$")
    public void downloadTermSummaryReport() {
        reportsPage.downloadTermSummaryReport();
    }

    @And("^check the count in index settlement details report$")
    public void checkCountIndexSettlementReport() throws IOException {
        String homePath = System.getProperty("user.home") + "/Downloads";
        File LatestFile = reportsPage.getLatestFileFromDir(homePath);
        reportsPage.checkCountIndexSettlementReport(LatestFile);
    }

    @And("^check the count in term summary report$")
    public void checkCountTermSummaryReport() throws IOException {
        String homePath = System.getProperty("user.home") + "/Downloads";
        File LatestFile = reportsPage.getLatestFileFromDir(homePath);
        reportsPage.checkCountTermSummaryReport(LatestFile);
    }

    @And("^select the open status Index from the Index list and click report option$")
    public void selectOpenIndex() {
        reportsPage.selectOpenIndex();
        reportsPage.clickReportOption();
    }

    @And("^search value \"([^\"]*)\" in Filter Report section$")
    public void searchValueReport(String search_value) {
        reportsPage.searchValueReport(search_value);
    }
}