
package steps;

import com.grip.page.ProcessReadinessCheckPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ProcessReadinessCheck extends CucumberRunner {
	
	
	ProcessReadinessCheckPage processReadinesscheckPage;

	@Given("^Enter URL and validate page$")
	public void ProcessReadinesscheckPage() throws Throwable {
		processReadinesscheckPage = new ProcessReadinessCheckPage(getDriver());
		processReadinesscheckPage.open();
	}
	
	@Then("^User clicks on expand button for the node type as \"([^\"]*)\"$")
	public void user_clicks_on_expand_button_for_the_releavant_node_type_as(String nodeType) throws Throwable {
		processReadinesscheckPage.clickExpandButton(nodeType);
		
	}
	@Then("^user verifies the parent node colour as green \"([^\"]*)\"$")
	public void user_verifies_the_parent_node_colour_as_green(String colour)
	{
		processReadinesscheckPage.verifyParentNodeColour(colour);
	}
	@Then("^user verifies the child node colour as green/purple \"([^\"]*)\" and \"([^\"]*)\"$")	
	public void user_verifies_the_child_node_colour_as_green_purple(String aProcess, String bProcess )
	{
		processReadinesscheckPage.verifyChildNodeColour( aProcess, aProcess);
	}
	@Then("^user click on node to view the right pane \"([^\"]*)\"$")
	public void user_click_on_node_to_view_the_right_pane(String nodeClick)
	{
		processReadinesscheckPage.clickonNode(nodeClick);
	}
	@Then("^user scroll down to view the ticker Logger$")
	public void user_scroll_down_to_view_the_ticker_Logger()
	{
		processReadinesscheckPage.scrollDown();
	}
	
	@Then("^User navigates status page and validate it$")
	public void user_navigates_to_status_page() {
		processReadinesscheckPage.clickStatus();
	}
	@Then("^user verifies the process ready and process readiness$")
	public void user_verifies_the_process_ready_and_process_readiness()
	{
		processReadinesscheckPage.verifyStatus();
	}
	@Then("^user verifies the parent node colour as green for TL$")
	public void user_verifies_the_parent_node_colour_as_green_for_TL(String nodeColour)
	{
		processReadinesscheckPage.verifyParentNodeColour(nodeColour);
	}

}
