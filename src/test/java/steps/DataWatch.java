package steps;

import com.grip.page.Dashboardpage;
import com.grip.page.SupportPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class DataWatch extends CucumberRunner {

	Dashboardpage dash = null;

	SupportPage supportPage = new SupportPage(getDriver());

	@Given("^Enter URL and Validate HomePage$")
	public void HomePage() throws Throwable {
		dash = new Dashboardpage(getDriver());
		dash.open();
		dash.validateHomePage();

	}
	
	@Then("^Verify Alert Color in Ticket Monitor")
	 public void TicketMonitor() throws Exception
	 {
		 dash.Verify_Ticket_Monitor();
		 
	 }


	@Given("^Enter URL and Validate SupportPage$")
	public void SupportPage() throws Throwable {

		supportPage.open();
	}


	@Then("^click on each link in DataWatch and compare count in Dashboard and research tab$")
	public void Compare_Count() throws Throwable {
	

		int n = 5;
		String Data="Indices";
		dash.dataWatch_Indices(Dashboardpage.xpathforIndices, n,Data);
	}

	@Then("^click on each link in Stocks and validate count in dashboard and research tab$")
	public void Compare_Count_Stocks() throws Throwable
	{
		int n = 1;
		String Testcase="TC01002";
		String Data="Stocks";
		dash.dataWatch(Dashboardpage.xpathforstocks, n,Testcase,Data);
		
	}
		
 
	@Then("^click on every link in contracts and compare counts in Dashboard and research tab$")
	public void  Compare_Count_Contracts () throws Throwable
 	{
	 int  n=1;
	 String Testcase="TC01003";
	 String Data="contracts";
	 dash.dataWatch(Dashboardpage.xpathforContracts,n,Testcase,Data);
	 n=1;
	 dash.switchdefault();
	 Data="Derivatives";
	 Testcase="TC01003D";
	 dash.dataWatch(Dashboardpage.xpathforDerivatives,n,Testcase,Data);
			 
 	}
		
		 
	@Then("^click on each link in Fixed Income and Validate Count in Research Tab$")
	public void Click_FixedIncome() throws Throwable
	{
	 System.out.println("Test");
	 int n=1;
	 String Testcase="TC01004";
	 String Data="contracts";
	 dash.dataWatch(Dashboardpage.xpathforFixedIncome,n,Testcase,Data);
     
		}
	@Then("^click on each link in Forex and Validate Count in Research Tab$")
	public void Click_each_link_in_Forex() throws Throwable
	{
				 
	 int n=1;
	 String Data="Forex";
	 String Testcase="TC01005";
	 dash.dataWatch(Dashboardpage.xpathforForex,n,Testcase,Data);
	}
	 
	@Then("^click on every link in Exchanges and compare counts in Dashboard and research tab$")
	 public void  Comapre_Exchanges () throws Throwable
	{
	 int n=1;
	 String Data= "Exchanges";
	 String Testcase="TC01006";
	 dash.dataWatch(Dashboardpage.xpathforExchanges,n,Testcase,Data);
	 dash.switchdefault();
	 Data="EODExchanges";
	 Testcase="TC01006E";
	 dash.dataWatch(Dashboardpage.xpathforEODExchanges,n,Testcase,Data);
			
	 }	
			 
	@Then("^Check Status of all Grip Process to check either A or B Process Available")
 	public void CheckStatus() throws IOException
 	{
	 int column =0;
	 String Sheet ="Static_Data";
	 dash.validateStatusofAllProcesses_Both(Dashboardpage.xpathForGripProcess,Sheet,column);
	 }
				 
 	@Then("^Check Status of all Datafeed Process to check Process Status A and B")
	 public void CheckStatus_DataFeed() throws IOException
		 
	 {
		int column =1;
		String Sheet ="Static_Data";
		dash.validateStatusofAllProcesses_Both(Dashboardpage.xpathForIncomingProcess,Sheet,column);
			 
	 }
		 
 	@Then("^check status for Outbound Destination$")
 	public void CheckStatus_OutboundDestination() throws IOException
 	{			    
 		dash.validateOutboundDestinations(Dashboardpage.xpathForOutDesProcess);
					 
	}
	 @Then("^Verify the alert dots with color$")
	 public void Verify_color() throws IOException
	 {	 		
		 String Sheet ="Static_Data";
 		dash.verify_col(Dashboardpage.xpathforIndices,5,Sheet);
 		dash.verify_col(Dashboardpage.xpathforstocks,1,Sheet);
 		dash.verify_col(Dashboardpage.xpathforContracts,1,Sheet);
	    dash.verify_col(Dashboardpage.xpathforForex,1,Sheet);
	    dash.verify_col(Dashboardpage.xpathforFixedIncome,1,Sheet);
	    dash.verify_col(Dashboardpage.xpathforFixedIncome,1,Sheet);
	  }

	 @Then("^Enter and verify search in Alerts delimited with comma$")
	 public void Verify_Search_alert_commaDelimited() throws Throwable
	  {
	  dash.Search_alert_commaDelimited();
	  }
		 

	 @Then ("^Enter and verify search in Alerts delimited with percentage$") 
	 public void Verify_Search_alert_with_PercentageDelimited() throws IOException
	  {
	  dash.Search_alert_PercentageDelimited();
	  }


	 @Then("^Enter Index ID in Jobs edit box in support TAB and Click on Stop Index Calc Job button$")
	 public void  Click_Index_calc() throws Exception
	 {
		 String Sheet ="Dynamic_TestData";
		 supportPage.Support_click(Sheet);
		 
		 
	 }
	
	 
	 @And("^Verify alerts in Dashboard when index Stops Ticking$")
	 public void Verify_Index_Stop_Alert() throws IOException, InterruptedException
	 {
		 supportPage.verify_Alert();
	 }
	 
	 @Then("^Refresh data from T3 and verify alert$")
	 public void Refresh_data() throws Exception
	 {
		 supportPage.refresh_data_T3();
	 }
	 
	 @And("^right click context menu of Alert and Verify Index enabled$")
	 public void  verify_contextMenu_Index() throws IOException
	 {
		 String Index="Index Maintenance";
		 int column =2;
		 supportPage.verify_Context(Index,column);
	 }
	 
	  @Then ("^Change Severity options in Customize view and verify alerts$")
	public void Verify_alert_with_Severity_filter() throws Throwable
	  {
		 String Sheet = "Static_Data";
	  dash.alert_with_severity_filter(Sheet);
	  }
		 

	 @Then ("^Uncheck AlertTypes in Customize view and verify alerts$")
	 public void Verify_alert_after_alerttype_unchecked() 
	  {
	  dash.alert_without_unchecked_alerttype();
	  }
		 
	 @Then ("^Check AlertTypes in Customize view and verify alerts$")
	 public void Verify_alert_after_alerttype_checked() 
	  {
	  dash.alert_with_checked_alerttype();
	  }
	 
	 @Then ("^Check all SeverityOptions in Customize view and verify alerts$")
	 public void Verify_alert_after_allSeverity_checked() 
	 {
		 dash.alert_with_all_severity();
	 }

	 @Then ("^Check all AlertTypes in Customize view and verify alerts$")
	 public void Verify_alert_after_allalerttype_checked() 
	 {
		 dash.alert_with_all_alerttype();
	 }
	 
	
	 
	 @Then("^sort each column in alert window and Validate$")
	 public void  sort_alert() throws FileNotFoundException, ParseException
	 {
		 dash.sort_alert();
		 
		 
	 }
	 
	 @Then("^Right click on Index alert and verify options in context Menu$")
	 public void IndexAlert() throws InterruptedException {
		 dash.IndexAlert();
		 
		 
	 }
		 
		 
	 @Then("^Right click on alert and verify option Enabled$")
	 public void verify_other_Assets() throws IOException, InterruptedException
	 {
		 String Process="Process Status";
		 dash.verify_other_assets(Process);
		 int column =12;
		 supportPage.verify_Context(Process,column);
		 dash.CopyName();
		 String Sys_M="System Monitor";
		 dash.verify_other_assets(Sys_M);
		 supportPage.verify_Context(Sys_M,column);
		 dash.CopyName();
	 }
	 @Then("^select monitor view and verify alert$")
	 public void Verify_monitor_view() throws Throwable
	 {
		String sheet="Static_Data";
		int t =0;
		dash.Monitor_view(sheet, t);
	 }
			 
	 @Then("^select research view and verify alert$")
	 public void Verify_research_view() throws Throwable
	 {
		 dash.Research_view();
	 }
			 
	 @Then("^Verify the default selection for customize view$")
     public void Verify_Customize_view_default_Selection() throws Throwable
     {
		 String sheet="Static_Data";
		 dash.customize_view(sheet);
     }
			 

	 @Then("^select research tab and click on each asset and verify$")
	 public void select_research_tab_and_click_on_each_asset_and_verify()  throws Throwable
	 {
			 
			 dash.verify_Asset();
	 }
			 

     @Then("^double click on each index family and verify$")
	 public void  double_click_on_each_index_family_and_verify() 
	 {
	 
         dash.verify_index_family();
	 }
             
     @Then("^Verify options in Index family")  
   	 public void All_options_should_work_as_per_functionality() throws Throwable
 	 {
 		dash.rightClick_Family();
 		dash.Verify_Options();


	 }
	 @Then("^click on recent tab double click on each index family and verify$")
	 public void  click_on_recent_tab_double_click_on_each_index_family_and_verify() 
	 {
		
        dash.verify_Recent_Tab_Index_family();
	 }
	 
	 @Then("^click on Process tab double click on each index family and verify$")
	 public void  click_on_process_tab_double_click_on_each_index_family_and_verify() 
	 {
		 
	 dash.verify_Process_Tab_index_family();
	 }
	 
	 @Then("Download Alert Data in alert Messgaes and Validate file with proper Data")
	 public void Download_Data() throws IOException
	 {
		 dash.Download_Data();
	 }
	 
	 @Then("^verify option in Recent Launch$")
	 public void verify_option() throws IOException, InterruptedException
	 {

	 		dash.rightClick_RecentLaunch();
	 		dash.Verify_Options();

	 }
	 
	 @Then("^verify option in Processes")
	 public void verify_Processes_option() throws IOException, InterruptedException
	 {

	 		dash.rightClick_Processes();
	 		dash.Verify_ProcessOptions();

	 }

	 
	 @And("^click on Open link in Exchange in Datawatch$")
	 public void Open_Exchange()
	 {
		 dash.Open_Exchange();
	 }
	 
	 
	 @Then("^click on status tab and update values in SM tree$")
	 public void updtaeparam()
	 {
		 String param="1";
		 int param1=4;
		 dash.updateParam(param,param1);
	 }
	 
	 @And("^click on support tab and Reload SM Tree$")
	 public void ReloadTree()
	 {
		 dash.reloadTree();
	 }
	 
	 @Then("Right click on Index and Select copy node and verify node in dashboard")
	 public void copyNode()
	 {
		 dash.copyNode();
		 
	 }
	 
	 
	 @Then("^Verify dot in Header of ticker Monitor$")
	 public void verifyDot()
	 {
		 dash.Navigationbar();
	 }
	 
	 @Then("^Verify Audio alert once index is flatline/No ticks$")
	 public void AuidioAlert() 
	 { 
	 dash.verifyAudio();
			 
	 }
	 
	 @Then("Verify flatline index in dashboard and rollback changes$")
	 public void flatline()
	 {
		 dash.verifyflatlineDashboard();
		 dash.RollbackFlatline();
	 }
	 
	 @Then("^click on status tab and update values for No ticks in SM tree$")
	 public void UpdateNoticks()
	 {
		 String param="1";
		 int param1=3;
		 dash.updateParam(param,param1);
		 dash.Execute();
	 }
	 
	 @Then("^Verify Noticks index in dashboard and rollback changes$")
	 public void Noticks()
	 {
		 dash.verifyNoticksDashboard();
		 dash.RollbackNoticks();
	 }
	 
	 @Then("^Add alert reason in DB and verify added reason available in addcomment window$")
	 public void Reason() throws IOException
	 {
		 dash.AddReason();
	 }
	 
	 @Then("^Add Comment in IndexAction reason and verify comment in UI and DB$")
	 public void IndexAction()
	 {
		 dash.indexAction();
	 }
	 
	 @Then("^Select add comment in Monitor view and verify default selection$")
	 public void defaulutReason()
	 { 
		
		 dash.defaultreason(0,3);
	 }
	 
	 @Then("^Add comment in Monitor view and verify maximum character allowed in add comment text$")
	 public void maxChar()
	 {
		 String testcomment="This is a Monitor view test comment";
		 dash.verifycharleft(testcomment);
	 }
	 @And("^click submit buttom and verify comment icon in alert$")
	 public void  verifycommennticon()
	 {
		 String Testcomment="This is a Monitor view test comment";
		 dash.verifyIcon(3,Testcomment);
	 }
	 @Then("^Add new comment on related alert and validate comment$")
	 public void newComment()
	 {
		 String testcomment2 ="This is a Monitor view test comment2";
		 dash.addComment(3,testcomment2);
	 }
	 
	 @Then("^Select multiple alert and add comment$")
	 public void multplecomment()
	 {
		 dash.multpleSelect(4,1);
	 }
	 
	 @Then("^Add comment for multiple selected alert and verify maximum character allowed in add comment text$")
	 public void mulcomment()
	 {
		 String testcomment="This is a Monitor view multiple test comment";
		 dash.verifycharleft(testcomment);
	 }
	 @Then("^Click Submit and verify comment for selected alerts$")
	 public void veifymulSelectedComments()
	 {
		 String testcomment="This is a Monitor view multiple test comment";
		 dash.verifymulcomments(4,testcomment);
	 }
	 
	 @Then("^Verify alert window filter with All radio button selection$")
	 public void filterALL()
	 {
		 dash.filterALL();
	 }
	 
	 @Then("^Verify alert window filter with Comments radio button selection$")
	 public void filterwithComments()
	 {
		 dash.filterwithComments();
	 }
	 
	 @Then("^Verify alert window filter without Comments radio button selection$")
	 public void filterwithoutComments()
	 {
		 dash.filterwithoutComments();
	 }
	 
	 @Then("^Select add comment in Research view and verify default selection$")
	 public void defaultReason()
	 { 
		
		 dash.defaultreason(1,7);
	 }
	 
	 @Then("^Add comment in Research view and verify maximum character allowed in add comment text$")
	 public void maxCharReseach()
	 {
		 String testcomment="This is a Research view test comment";
		 dash.verifycharleft(testcomment);
	 }
	 
	 @Then("^Add new comment on related alert in research view and validate comment$")
	 public void newCommentResearch()
	 {
		 String testcomment2 ="This is a research view test comment2";
		 dash.addComment(7,testcomment2);
	 }
	 
	 @And("^click submit buttom and verify comment icon in alert in research view$")
	 public void  verifycommennticonResearch()
	 {
		 String testcomment="This is a Research view test comment";
		 dash.verifyIcon(7,testcomment);
	 }
	 
	 @Then("^Add comment for multiple selected alerts and verify maximum character allowed in add comment text$")
	 public void mulResearchcomment()
	 {
		 String testcomment="This is a Research view multiple test comment";
		 dash.verifycharleft(testcomment);
	 }
	 
	 @Then("^Select multiple alert and add comment in Research view$")
	 public void multplecommentResearch()
	 {
		 dash.multpleSelect(7,1);
	 }
	 
	 @Then("^Add comment for multiple selected alert in research view and verify maximum character allowed in add comment text$")
	 public void mulcommentResearch()
	 {
		 String testcomment="This is a research view multiple test comment";
		 dash.verifycharleft(testcomment);
	 }
	 
	 @Then("^Click Submit and verify comment for selected alerts in reseach view$")
	 public void veifymulSelectedCommentsResearch()
	 {
		 String testcomment="This is a research view multiple test comment";
		 dash.verifymulcomments(7,testcomment);
	 }
	 
	 @Then("^click on download alerts in monitor view and validate data with UI$")
	 public void downloadMonitor() throws IOException
	 {
		 dash.download(0);
		 dash.validatedownload(1,"Time",1);
		 dash.validatedownload(4,"Subject",5);
		 dash.validatedownload(6,"Description",6);
		 dash.hascomment();
		 
	 }
	 
	 @Then("^click on download comments in monitor view and validate data with UI$")
	 public void downloadComments() throws IOException
	 {
		 dash.validateComments(0);
	 }
	 
	 @Then("^click on download alerts in research view and validate data with UI$")
	 public void downloadResearchr() throws IOException
	 {
		 dash.download(1);
		 dash.validatedownload(1,"Time",1);
		 dash.validatedownload(4,"Subject",5);
		 dash.validatedownload(6,"Description",6);
		 dash.validatedownload(5,"User Name",7);
		 dash.validatedownload(2,"Alert Type",8);
		 dash.validatedownload(3,"Severity",10);
		 dash.hascomment();
		 
	 }
	 
	 @Then("^click on download comments in research view and validate data with UI$")
	 public void downloadresearchComments() throws IOException
	 {
		
		 dash.validateComments(1);
	 }
	 
	 @Then("^Click on Warning link and validate columns in Index list window$")
	 public void onWarning()
	 {
		dash.onWarning(); 
	 }
	 
	 @Then("^Change the Warning filter to Threshold View an verify the window$")
	 public void Threshold() {
		int columns=16;
		String TestcaseID="TC01086";
		String viewID="indexViewsList";
		dash.Thresholdview(columns,TestcaseID,viewID);
		 
	 }
	 
	 @Then("^Click on Warning link and validate columns in Stock list window$")
	 public void stockWarning()
	 {
		dash.stockWarning(); 
	 }
	 
	 @Then("^Change filter to Threshold View an verify the columns in Stock list window$")
	 public void StockThreshold() 
	 {
		int columns=14;
		String TestcaseID="TC01088";
		String viewID="stockViewsList";
		dash.Thresholdview(columns,TestcaseID,viewID);
		 
	 }
	 
	 @Then("^Click on Warning link and validate columns in contract list window$")
	 public void contractWarning()
	 {
		dash.contractWarning(); 
	 }
	 
	 @Then("^Change filter to Threshold View an verify the columns in contract list window$")
	 public void contractThreshold() 
	 {
		int columns=15;
		String TestcaseID="TC01090";
		String viewID="contractViewsList";
		dash.Thresholdview(columns,TestcaseID,viewID);
		 
	 }
	 
	 @Then("^Click on Warning link and validate columns in Fixed Income list window$")
	 public void FIWarning()
	 {
		dash.fixedWarning(); 
	 }
	 
	 @Then("^Change filter to Threshold View an verify the columns in fixedIncome list window$")
	 public void FIThreshold() 
	 {
		int columns=15;
		String TestcaseID="TC01092";
		String viewID="fiiViewsList";
		dash.Thresholdview(columns,TestcaseID,viewID);
		 
	 }
	 
	 @Then("^Click on Warning link and validate columns in Forex list window$")
	 public void FxWarning()
	 {
		dash.forexWarning(); 
	 }
	 
	 @Then("Update warning Threhold limits for greater than 2 in DB and validate limits in UI")
	 public void thresholdGreater() throws IOException
	 {
		 dash.ThresholdUpdate();
		 dash.ThresholdGreater("16.5","12.5",3);
	 }
	 
	 @Then("Update warning Threhold limits for Lower than 2 in DB and validate limits in UI")
	 public void thresholdLower() throws IOException
	 {
		 dash.ThresholdUpdateLower();
		 dash.ThresholdGreater("50","25",2);
	 }
	 
	 
	 @Then("^Filter Waning threshold alert type in Alert Messages Window and validate alerts in DB$")
	 public void WarningThreshold()
	 {
		 dash.Warningalerts();
	 }
	 
	 
	 @Then("^click on download alerts and validate downloaded data with UI$")
	 public void downloadWarnr() throws IOException
	 {
		 dash.downloadWarning();
		 dash.validatedownload(1,"Time",1);
		 dash.validatedownload(4,"Subject",5);
		 dash.validatedownload(6,"Description",6);
		 dash.AlertType();
		 
		 
	 }
	 
	 
	 @Then("^Click on Suspect link and validate columns in index list window$")
	 public void Suspect()
	 {
		dash.suspect(); 
	 }

	 
			 






				 
				 
				 
}
