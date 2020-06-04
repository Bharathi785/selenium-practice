package steps;

import com.grip.page.Statuspage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SM extends CucumberRunner {

	Statuspage statuspage;

	@Given("^Enter URL$")
	public void Statuspage() throws Throwable {
		statuspage = new Statuspage(getDriver());
		statuspage.open();
	}

	@Then("^Default view of filter window,status tree,status$")
	public void Default_view_of_filter_window_status_tree_status() {
		statuspage.defaultView();
	}
	
	@Then("^User navigates to status page$")
	public void user_navigates_to_status_page() {
		statuspage.clickStatus();
	}
	
	@Then("^User search for filter type$")
	public void User_search_for_filter_type() {
		String a = "Exchange";
		statuspage.searchForFilter(a);
	}
	
	@Then("^User clicks on expand button for the releavant node type as \"([^\"]*)\"$")
	public void user_clicks_on_expand_button_for_the_releavant_node_type_as(String nodeType) throws Throwable {
		statuspage.clickExpandButton(nodeType);
		
	}
	@Then("^verify the purple colour$")
	public void verify_the_purple_colour()
	{
		statuspage.verifyPurple();
	}
	@Then("^User clicks on expand button and user finds the colour of the node as \"([^\"]*)\"$")
	public void User_clicks_on_expand_button_and_user_finds_the_colour_of_the_node(String colour)
	{
		statuspage.findcolour(colour);
	}
	
	@Then("^uncheck the checkboxes for green and purple$")
	public void uncheck_the_checkboxes_for_green_and_purple() throws InterruptedException
	{
		statuspage.uncheckBox();
	}

	@Then("^Filter Status tree with key words$")
	public void filterStatusTree() {		
		String b = "ICE";
		statuspage.filterStatusTree();
		statuspage.searchForFilter(b);
	}
	
	@Then("^verify the colour and text with sub-node$")
	public void colourTextverify()
	{
		statuspage.colourkeywordVerify();
	}
	
	@Then("^User clicks on expand button for exchange$")
	public void User_clicks_on_expand_button_for_exchange()
	{
		statuspage.expandExchange();
	}
	
	@Then("^User click on search button$")
	public void User_click_on_search_button()
	{
		statuspage.clicksearchbutton();
	}
	@Then("^User untick all check boxes click on search button$")
	public void User_untick_all_check_boxes_click_on_search_button()
	{
		statuspage.untickAllCheckBox();
	}
	@Then("^verify all the node without text \"([^\"]*)\"$")
	public void verify_all_the_node_without_text(String nodeVerify)
	{
		statuspage.verifyAllTheNode(nodeVerify);
	}
	@Then("^user identifies the bombay stock exchange$")
	public void user_identifies_the_bombay_stock_exchange()
	{
		statuspage.identifyBombayStock();
	}
	@Then("^user identifies the Australian stock exchange sibling$")
	public void user_identifies_the_Australian_stock_exchange()
	{
		statuspage.identifyAustraliaStock();
	}
	@Then("^user identifies the Budapest stock exchange subnode child$")
	public void user_identifies_the_Budapest_stock_exchange()
	{
		statuspage.identifyBudapestStockExchange();
	}
	@Then("^user identifies the colombo stock exchange SubNode sibling$")
	public void user_identifies_the_colombo_stock_exchange()
	{
		statuspage.identifyColomboStock();
	}
	@Then("^verify final copy node notification child$")
	public void verify_final_copy_node_notification()
	{
		statuspage.copyNodeRightClick();
	}
	
	@Then("^user pastes the node child$")
	public void user_pastes_the_node()
	{
		statuspage.pasteNodeChild();
	}
	
	@Then("^user switches to supportpage to reload$")
	public void user_switches_to_supportpage_to_reload()
	{
		statuspage.supportPageReload();
	}
	
	@Then("^user verifies the paste node child$")
	public void user_verifies_the_paste_node()
	{
		statuspage.pasteNodeverificationChild();
	}
	
	@Then("^user pastes the node sibling$")
	public void user_pastes_the_node_sibling()
	{
		statuspage.pasteNodeSibling();
	}
	@Then("^user pastes the Subnode sibling$")
	public void user_pastes_the_node_sibling_colombo()
	{
		statuspage.pasteSubNodeSibling();
	}
	@Then("^user pastes the subNode child$")
	public void user_pastes_the_subNode()
	{
		statuspage.pasteSubNodeChild();
	}
	@Then("^user verifies the paste node Sibling$")
	public void user_verifies_the_paste_node_Sibling()
	{
		statuspage.pasteNodeVerificationSibling();
	}
	@Then("^user verifies the paste SubNode child$")
	public void user_verifies_the_paste_node_SubNode_child()
	{
		statuspage.pasteNodeVerificationSubNodeChild();
	}
	@Then("^user verifies the paste SubNode Sibling$")
	public void user_verifies_the_paste_SubNode_Sibling()
	{
		statuspage.pasteSubNodeVerificationSibling();
	}
	
	@Then("^verifies the SubNode$")
	public void verifies_the_SubNode()
	{
		statuspage.verifySubNode();
	}
	@Then("^verify the database for node creation$")
	public void verify_the_database_for_node_creation()
	{
		String name="COPY OF Bombay Stock Exchange";
		statuspage.databaseVerification(name);
	}
	@Then("^verify the database for node creation-Australia$")
	public void verify_the_database_for_node_creation_Australia()
	{
		String name="COPY OF Australian Stock Exchange";
		statuspage.databaseVerification(name);
	}
	@Then("^verify the database for Subnode creation-Budapest$")
	public void verify_the_database_for_Subnode_creation_Budapest()
	{
		String name="COPY OF Budapest Stock Exchange";
		statuspage.databaseVerification(name);
		statuspage.subNodeDatabaseVerification();
	}
	@Then("^delete the Node finally$")
	public void delete_the_Node_finally()
	{
		statuspage.clickNodeBuda();
		statuspage.deleteNode();
	}
	@And("^delete the node-Colombo$")
	public void delete_the_node_Colombo()
	{
		statuspage.clickNodeCol();
		statuspage.deleteNode();
	}
	
	@Then("^verify the database for Subnode creation-Colombo$")
	public void verify_the_database_for_Subnode_creation_Colombo()
	{
		String name="COPY OF COLOMBO STOCK EXCHANGE";
		statuspage.databaseVerification(name);
		statuspage.subNodeDatabaseVerification();
	}
	@Then("^user clicks on node and edit the value$")
		public void user_clicks_on_node_and_edit_the_value()
		{
			statuspage.editTheValue();
		}
	
	@Then("^verify the database the value of param$")
	public void verify_the_database_the_value_of_param()
	{
		statuspage.verifyParamDatabaseValue();
	}
	@Then("^disable the node$")
	public void disable_the_node()
	{
		statuspage.disableNode();
	}
	@Then("^verify in the database$")
	public void verify_in_the_database()
	{
		statuspage.disableDatabase();
	}
	@And("^update the enable ID to one$")
	public void update_the_enable_ID_to_one()
	{
		statuspage.updateEnableID();
	}
	@Then("^verify the node in UI after enabling to one$")
	public void verify_the_node_in_UI_after_enabling_to_one()
	{
		statuspage.pasteNodeverificationChild();
	}
	@Then("^user deletes the node and user verifies the delete notification$")
	public void user_deletes_the_node_and_user_verifies_the_delete_notification()
	{
		statuspage.copyNodeclick();
		statuspage.deleteNode();
	}
	@Then("^delete the node$")
	public void delete_the_node()
	{
		statuspage.clickNode();
		statuspage.deleteNode();
	}
	@And("^click on IDS and verify process is yes or not$")
	public void click_on_IDS1_and_verify_process_is_yes_or_not()
	{
		statuspage.IDSA();
		statuspage.IDSB();
	}
	
	@And("^click on IDC and verify the right pane filter with keyword$")
	public void click_on_IDC_and_verify_the_right_pane_filter()
	{
		statuspage.rightpaneFilter();
	}
	@Then("^click on IDC and verify the right pane filter after unchecking checkbox$")
	public void click_on_IDC_and_verify_the_right_pane_filter_after_unchecking_checkbox()
	{
		statuspage.rightFilter();
	}
}