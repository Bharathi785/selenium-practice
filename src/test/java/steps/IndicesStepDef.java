package steps;

import com.grip.DBconnection.IndicesSQLStatements;
import com.grip.page.IndicesPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class IndicesStepDef extends CucumberRunner {
	public static String dataUpdateValue, thresoldValue, capturedsystemTime, CompareWithT3Type;
	IndicesPage indicesPage = new IndicesPage(getDriver());

	@Given("^User logs into Grip application$")
	public void User_logs_into_Grip_application() throws Throwable {
		indicesPage.deletCookies();
		indicesPage.open();
	}

	@Given("^User will fecth time in Dashboard page$")
	public void User_will_fetch_time_in_Dashboard_page() throws Throwable {
		indicesPage.getTimeInDashboard();
	}

	@Given("^User navigates to Index tab under Reasearch page$")
	public void user_navigates_to_Index_tab_under_Research_page() throws Throwable {
		indicesPage.clickResearch();
		indicesPage.clickIndex();
	}

	@Given("^User will navigate to Index tab via dashboard link as \"([^\"]*)\"$")
	public void User_navigates_to_Index_tab_via_dashboard_link_as(String dasboardLinkType) throws Throwable {
		indicesPage.clickDashboardLink(dasboardLinkType);
	}

	@Given("^User will select type of index as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void MultiAsset(String indexType, String indexValue) throws Throwable {
		indicesPage.selectIndextype(indexType, indexValue);
	}

	@Given("^User will select type of index as \"([^\"]*)\"$")
	public void MultiAsset(String indexType) throws Throwable {
		indicesPage.selectIndextypeDropDown(indexType);
	}

	@Given("^User will search for index data as \"([^\"]*)\"$")
	public void user_will_search_for_for_index_data_as(String indexData) throws Throwable {
		indicesPage.serchIndexData(indexData);
	}

	@Given("^User search for the index close from grip$")
	public void User_search_for_the_index_close_from_grip() throws Throwable {
		indicesPage.searchIndexNameCloseGrip();
	}

	@Given("^User release the without Held index and verify alert$")
	public void User_release_the_without_Held_index_and_verify_alert() throws Throwable {
		indicesPage.releaseIndexWithoutHeld();
		indicesPage.clicksaveIndexOk();
		indicesPage.verifyNotRelaseAlertMessage();

	}

	@Given("^User fetch the current price value of index$")
	public void User_fetch_the_current_price_value_of_index() throws Throwable {
		indicesPage.getIndicesCoulmnValue();
	}

	@Given("^User will perform right click action on the index$")
	public void User_will_perform_right_click_action_on_the_index() throws Throwable {
		// indicesPage.scrollToDown();
		indicesPage.performRightClickAction();
	}

	@Given("^User will perform right click action on the index without test data$")
	public void User_will_perform_right_click_action_on_the_index_without_test_data() throws Throwable {
		indicesPage.performRightClickNoTestData();
	}

	@Given("^User will perofrm actions on Show Details as \"([^\"]*)\"$")
	public void user_will_perofrm_actions_on_Show_Constituent_Details(String showType) throws Throwable {
		indicesPage.showDetails(showType);
	}

	@Given("^User Add/Edit index and verify in Maintain RTM Index tab$")
	public void user_Add_Edit_index_and_verify_in_Maintain_RTM_Index_tab() throws Throwable {
		indicesPage.clickAdvanced();
		indicesPage.advancedAddEdit();
		indicesPage.getPercentValuesDatamaiantain();
		indicesPage.verifyAddEditDataMaintain();
	}

	@Given("^User stops index calculation and verify alert as \"([^\"]*)\"$")
	public void user_stops_index_calculation_and_verify_alert(String indexType) throws Throwable {
		indicesPage.clickAdvanced();
		indicesPage.stopIndexCalcualtion(indexType);
	}

	@Given("^User reverts the stopped index calcuation to resolved state as \"([^\"]*)\"$")
	public void user_reverts_the_stopped_index_calcuation_to_resolved_state(String indexType) throws Throwable {
		indicesPage.clickRefreshFromT3();
		indicesPage.clicksaveIndexOk();
		indicesPage.verifyStatusMessageResolved(indexType);
		indicesPage.revertIndexToResolved();
	}

	@Given("^User will hold index and verify the current value as \"([^\"]*)\"$")
	public void User_will_hold_index_and_verify_the_current_value(String holdType) throws Throwable {
		indicesPage.clickHold();
		indicesPage.verifyCurrentPriceValue(holdType);
	}

	@Given("^User verify the status in table and alert table as \"([^\"]*)\"$")
	public void User_verify_the_status_in_table_and_alert_table(String holdType) throws Throwable {
		indicesPage.verifyHoldAlert(holdType);
	}

	@Given("^User Correct Statistics of trading time \"([^\"]*)\" and verify alerts$")
	public void user_correct_statistics_within_trading_time_and_verify_alerts(String correctionType) throws Throwable {
		indicesPage.clickAdvanced();
		indicesPage.correctStatistics(correctionType);
	}

	@Given("^User will release the holded index$")
	public void User_will_release_the_holded_index() throws Throwable {
		indicesPage.releaseIndex();
		indicesPage.clickReleaseOk();
	}

	@Given("^User will hold/release multiple indices as \"([^\"]*)\" and release as \"([^\"]*)\"$")
	public void User_will_hold_multiple_indices_and_release(String actionType, String indextype) throws Throwable {
		if (actionType.equalsIgnoreCase("Hold") || actionType.equalsIgnoreCase("Release"))
			indicesPage.holdReleaseMultipleIndices(actionType, indextype);
		else
			indicesPage.holdReleaseIndiceMultipleTime(actionType, indextype);
	}

	@Given("^User will perform auto based index release$")
	public void User_will_perform_auto_based_index_release() throws Throwable {
		indicesPage.verifyAutoHoldAlertMessages();
	}

	@Given("^User will update preOpen time for index in Database$")
	public void user_will_update_preOpen_time_for_index_in_database() throws Throwable {
		IndicesSQLStatements.updateOpenTimeRefreshGrip();
	}

	@Given("^User will refresh index value from Grip$")
	public void User_will_refresh_index_value_from_Grip() throws Throwable {
		indicesPage.clickAdvanced();
		indicesPage.refreshFromGrip();
	}

	@Given("^User revert the index value via refresh from T3$")
	public void User_revert_the_index_value_via_refresh_from_T3() throws Throwable {
		indicesPage.clickRefreshFromT3();
		indicesPage.clicksaveIndexOk();
		indicesPage.verifyRefreshGripMessage();
	}

	@Given("^User will modify UpDown percent values refresh mView as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_will_modify_UpDown_percent_values_refresh_mView(String dataUpdate, String thresold)
			throws Throwable {
		dataUpdateValue = dataUpdate;
		thresoldValue = thresold;
		indicesPage.clickAdvanced();
		indicesPage.advancedAddEdit();
		if (dataUpdateValue.equalsIgnoreCase("update"))
			indicesPage.getPercentValuesDatamaiantain();
		indicesPage.modifypercentValues(dataUpdateValue, thresoldValue);
		indicesPage.saveModifiedDetails();
		indicesPage.clickResearch();
	}

	@Given("^User will refresh Mview$")
	public void User_will_refresh_Mview() throws Throwable {
		indicesPage.clickAdvanced();
		indicesPage.advancedAddEdit();
		indicesPage.refreshMviewSaveDetails();
		indicesPage.clickResearch();
	}

	@Given("^User refresh index from T3 and verify alert$")
	public void User_refresh_index_from_T3_and_verify_alert() throws Throwable {
		indicesPage.clickRefreshFromT3();
		indicesPage.clickRefreshT3Ok();
		if (thresoldValue.equalsIgnoreCase("non zero"))
			indicesPage.verifyBaselineMessages(dataUpdateValue);
		else
			indicesPage.verifyHoldChangeStatus();
	}

	@Given("^User refresh index from T3 and verify Auto hold alert as \"([^\"]*)\"$")
	public void User_refresh_index_from_T3_and_verify_auto_hold_alert(String holdType) throws Throwable {
		indicesPage.clickRefreshFromT3();
		indicesPage.clickRefreshT3Ok();
		indicesPage.verifySuspectholdAlert(holdType);
	}

	@Given("^User refresh index from T3 and verify updated values from DB in alert UI as \"([^\"]*)\"$")
	public void User_refresh_index_from_T3_and_verify_updated_alert_from_DB(String thresoldType) throws Throwable {
		indicesPage.clickRefreshFromT3();
		indicesPage.clickRefreshT3Ok();
		indicesPage.verifyDBToUIAlert(thresoldType);
	}

	@Given("^User refresh index from T3 and verify no change in index status as \"([^\"]*)\"$")
	public void User_refresh_index_from_T3_and_verify_no_change_in_index_status(String refactorType) throws Throwable {
		indicesPage.clickRefreshFromT3();
		indicesPage.clickRefreshT3Ok();
		if (refactorType.equalsIgnoreCase("yes"))
			indicesPage.verifyNoChangeStatus();
		else
			getDriver().navigate().refresh();
	}

	@Given("^User will manually release the index$")
	public void User_will_manually_release_the_index() throws Throwable {
		indicesPage.releaseIndex();
		indicesPage.verifyReleaseAlertMessages();
	}

	@Given("^User publish Op/Hi/Lo to T3 and verify alerts$")
	public void User_publish_Op_Hi_Lo_to_T3() throws Throwable {
		indicesPage.clickAdvanced();
		if (IndicesPage.dasboardLink.equalsIgnoreCase("close"))
			indicesPage.clickPublishToT3();
		else if (IndicesPage.dasboardLink.equalsIgnoreCase("prelim close")
				| IndicesPage.dasboardLink.equalsIgnoreCase("prelim close older date"))
			indicesPage.clickPrelimCloseT3();
		indicesPage.feedPublishDate();
		indicesPage.verifyPublishT3Messages();
	}

	@Given("^User publish close index and verify alerts as \"([^\"]*)\"$")
	public void User_publish_close(String publishType) throws Throwable {
		if (publishType.equalsIgnoreCase("from T3")) {
			indicesPage.clickPublishCloseFromT3();
			indicesPage.verifyPublishcloseFromT3Messages();
		} else if (publishType.equalsIgnoreCase("from GRIP")) {
			indicesPage.clickAdvanced();
			indicesPage.clickPublishCloseFromGrip();
			indicesPage.verifyPublishCloseFromGripMessages();
		}
	}

	@Given("^User updates index divisor value and verify the updated value$")
	public void User_updates_index_visor_value_and_verify_the_updated_value() throws Throwable {
		indicesPage.updateIndexDivisorValue();
	}

	@Given("^User will verify the alert to find Index movement beyond thresholds message$")
	public void User_will_verify_the_alert_to_find_Index_movement_beyond_thresholds_message() throws Throwable {
		indicesPage.verifyMessageHoldMarketOpen();

	}

	@Given("^User will click on hold and enter negative value and vaildate the same$")
	public void User_will_click_on_hold_and_enter_negative_value_and_vaildate_the_same() throws Throwable {
		indicesPage.clickHold();
		indicesPage.negativeNumber();
	}

	@Given("^User update data and generate valid report for \"([^\"]*)\"$")
	public void User_update_data_and_generate_valid_report_for(String CompareWithT3) throws Throwable {
		indicesPage.updateDataGenerateReport(CompareWithT3);
	}

	@Given("^User will select compare with T3 for index data as \"([^\"]*)\"$")
	public void User_will_select_compare_with_T3_for_index_data(String CompareWithT3) throws Throwable {
		CompareWithT3Type = CompareWithT3;
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		capturedsystemTime = utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		indicesPage.clickCompareWithT3();
		if (CompareWithT3Type.equalsIgnoreCase("Index Portfolio")) {
			indicesPage.clickIndexPortfolio();
		} else if (CompareWithT3Type.equalsIgnoreCase("Index Parameters")) {
			indicesPage.clickIndexParameters();
		} else {
			indicesPage.clickIndexTimings();
		}
	}

	@And("^User verify the report generated in mail$")
	public void User_will_verify_report_generated_in_mail() throws Throwable {
		indicesPage.verifyReportInMail(CompareWithT3Type, capturedsystemTime);
	}
	
	@Given("^User revert the cache ID value via refresh from T3$")
	public void User_revert_the_cache_ID_value_via_refresh_from_T3() throws Throwable {
		indicesPage.clickRefreshFromT3();
		indicesPage.clicksaveIndexOk();
	}
	
	@Given("^User validate the attachments from mail$")
	public void User_validate_the_attachments_from_mail() throws Throwable {
		indicesPage.validateAttachmentFromMail();
	}

	@And("^User will search for the indices withcomma and verify$")
	public void User_will_search_for_the_indices_with_comma_and_verify() throws Throwable {
		indicesPage.verifySearchListedValues();
	}

	@And("^User will click Copy name and Equity Index name should get copied to the clipboard$")
	public void User_will_click_Copy_name_and_Equity_Index_name_should_get_copied_to_the_clipboard() throws Throwable {
		indicesPage.copyName();
	}

	@And("^User will click Copy Id and Equity Index id should get copied to the clipboard$")
	public void User_will_click_Copy_Id_and_Equity_Index_id_should_get_copied_to_the_clipboard() throws Throwable {
		indicesPage.copyId();
	}

	@And("^User will click Report and Equity Index name populated in Asset edit box$")
	public void User_will_click_Report_and_Equity_Index_name_populated_in_Asset_edit_box() throws Throwable {
		indicesPage.clickReport();
	}

	@And("^User selects all indices rows and verify count$")
	public void User_selects_all_indices_rows_and_verify_count() throws Throwable {
		indicesPage.selectAllRows();
	}

	@And("^User will click Publish Add Definition and Definition message should be published with alert$")
	public void User_will_click_Publish_Add_Definition_and_Definition_message_should_be_published_with_alert()
			throws Throwable {
		indicesPage.clickAdvanced();
		indicesPage.publishAddDefinition();
	}

	@And("^User will click Publish Update Definition and Definition message should be published with alert$")
	public void User_will_click_Publish_Update_Definition_and_Definition_message_should_be_published_with_alert()
			throws Throwable {
		indicesPage.performRightClickAction();
		indicesPage.clickAdvanced();
		indicesPage.publishUpdateDefinition();
	}

	@And("^User will click Publish News and Definition message should be published with alert$")
	public void User_will_click_Publish_News_and_Definition_message_should_be_published_with_alert() throws Throwable {
		indicesPage.performRightClickAction();
		indicesPage.clickAdvanced();
		indicesPage.publishNews();
	}

	@And("^User will click Move to Next day and verify the alert$")
	public void User_will_click_Move_to_Next_day_and_verify_the_alert() throws Throwable {
		indicesPage.moveToNextDay();
		indicesPage.verifyMoveToNextDayAlert();
	}

	@And("^User publish close from T3 Grip and verify alerts as \"([^\"]*)\"$")
	public void User_publish_close_from_T3_Grip_and_verify_alerts_as(String PublishType) throws Throwable {
		if (PublishType.equalsIgnoreCase("from T3")) {
			indicesPage.clickPublishCloseFromT3();
			indicesPage.verifyPublishcloseFromT3Messages();
		} else if (PublishType.equalsIgnoreCase("from GRIP")) {
			indicesPage.clickAdvanced();
			indicesPage.clickPublishCloseFromGrip();
			indicesPage.verifyPublishCloseFromGripMessages();
		}
	}

	@Given("^User publish Op/Hi/Lo to T3 and prelim close T3 and verify alerts$")
	public void User_publish_Op_Hi_Lo_to_T3_and_prelim_close_T3_and_verify_alerts() throws Throwable {
		indicesPage.clickAdvanced();
		if (IndicesPage.dasboardLink.equalsIgnoreCase("open"))
			indicesPage.clickPublishToT3();
		else if (IndicesPage.dasboardLink.equalsIgnoreCase("prelim close T3 open")
				| IndicesPage.dasboardLink.equalsIgnoreCase("prelim close older date"))
			indicesPage.clickPrelimCloseT3();
		indicesPage.clicksaveIndexOk();
		indicesPage.verifyPublishT3MessagesOpen();
	}

	@Then("^Click on Export to Excel and Report file should be generated in csv format with proper data$")
	public void indexDownload() throws InterruptedException, IOException {
		indicesPage.indexDownload();
	}

	@And("^Click on index tab and Select different option available in combo filter and verify the column names$")
	public void indexVerifyFilter() throws Exception {
		indicesPage.verifyIndexFilter();
	}

	@And("^User click on Correct Statistics modify the correct value as \"([^\"]*)\"$")
	public void User_click_on_Correct_Statistics_modify_the_correct_value_as(String modifyType) throws Exception {
		indicesPage.correctStatisticsValue(modifyType);
	}

	@Given("^User update the thresold values in RTm table in Database as \"([^\"]*)\"$")
	public void User_update_the_thresold_values_in_RTm_table_in_Database_as(String thresoldType) throws Throwable {
		indicesPage.updateThresoldValuesinDB(thresoldType);
	}

	@And("^User verify HPI flag in UI under Indices list table$")
	public void User_verify_HPI_flag_in_UI_under_Indices_list_table() throws Throwable {
		indicesPage.verifyHPIFlagUI();
		indicesPage.verifyHPIColumnSorting();
	}

	@Given("^User verify the No Ticks alert message as \"([^\"]*)\"$")
	public void User_verify_the_No_Ticks_alert_message_as(String noTickType) throws Throwable {
		indicesPage.verifyNoTicksAlertMessages(noTickType);
	}

	@Given("^User will suspect index and verify the suspect alert as \"([^\"]*)\"$")
	public void User_will_suspect_index_and_verify_the_current_value(String suspectType) throws Throwable {
		if (suspectType.equalsIgnoreCase("Publish suspect")) {
			indicesPage.clickPublishSuspect();
			indicesPage.clicksaveIndexOk();
			indicesPage.verifyPublishSuspectAlert(suspectType);
		} else {
			indicesPage.clickWithdrawSuspect();
			indicesPage.clickWithdrawSuspectOk();
			indicesPage.verifyWithDrawSuspectAlert(suspectType);
		}
	}

	@And("^User verify the status in Index list suspect window$")
	public void User_verify_the_status_in_Index_list_suspect_window() throws Throwable {
		indicesPage.verifySuspectStatus();
	}

	@And("^User will download the suspect index data report$")
	public void User_will_download_the_suspect_index_data_report() throws Throwable {
		indicesPage.downloadIndexSuspectwindow();
	}

	@Given("^User will hold the suspected Index and verify the alert as \"([^\"]*)\"$")
	public void User_will_hold_suspected_index_and_verify_the_alert_as(String holdType) throws Throwable {
		if (holdType.equalsIgnoreCase("Manual Hold")) {
			indicesPage.clickHold();
			indicesPage.verifyCurrentPriceValue(holdType);
		} else {
			indicesPage.releaseIndex();
			indicesPage.clickReleaseOk();
		}
		indicesPage.verifySuspectholdAlert(holdType);
	}

	@And("^User supect index other than open state and verify no action$")
	public void User_supect_index_other_than_open_state_and_verify_no_action() throws Throwable {
		indicesPage.clickPublishSuspect();
		indicesPage.clicksaveIndexOk();
		indicesPage.verifyNoAlertMessage();
	}

	@And("^User will verify the values in Local and remote DataBase for Close Tick as \"([^\"]*)\"$")
	public void User_will_verify_the_values_in_Local_and_remote_DataBase_for_Close_Tick(String queryType)
			throws Throwable {
		indicesPage.verifyDQFlaginDB(queryType);
	}

	@Given("^User will publish mulitple indices as \"([^\"]*)\" and withdraw as \"([^\"]*)\"$")
	public void User_will_publish_multiple_indices_and_withdraw(String actionType, String indextype) throws Throwable {
		indicesPage.publishWithdrawMultipleIndices(actionType, indextype);
	}

	@And("^User filter Suspect alert data and verify donwload$")
	public void User_filter_Suspect_alert_data_and_verify_donwload() throws Throwable {
		indicesPage.filterSuspectData();
		indicesPage.alertFilterDonwload();
	}

	@Given("^User refresh index from T3 and verify suspect alert$")
	public void User_refresh_index_from_T3_and_verify_suspect_alert() throws Throwable {
		indicesPage.clickRefreshFromT3();
		indicesPage.clickRefreshT3Ok();
		indicesPage.verifyBaselineMessages(dataUpdateValue);
	}

}
