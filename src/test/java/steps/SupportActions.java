package steps;

import com.grip.page.SupportPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.IOException;

public class SupportActions extends CucumberRunner {

	SupportPage support = new SupportPage(getDriver());

	@Given("^Enter URL and Validate Supportpage$")
	public void SupportActions() throws Throwable {

		support.open();
		support.ValidateSupportPage();

	}

	@Then("^Select Handler for processes and verify all available support actions \\\"([^\\\"]*)\\\", \\\"([^\\\"]*)\\\"$")
	public void verify_Actions(String ProcessA, String ProcessB) throws Exception {
		support.Verify_Handler(ProcessA, ProcessB);
	}

	@Then("Click on Passivate All and Verify Routing tO QA is checked")
	public void route() {
		support.RoutePassiveQA();

	}

	@Then("Click on Passivate All and Verify Routing to ActiveQA is checked")
	public void Activeroute() throws InterruptedException, IOException {
		support.RouteActiveeQA();

	}

	@Then("^Verify output for all Sites set to true$")
	public void Verify() throws InterruptedException, IOException {
		String statusTrue = "true";
		String statusFalse = "false";
		support.Verify(statusTrue, statusFalse);
	}

	@Then("^Uncheck Enable route to QA and verify all Sites set to true$")
	public void Verifyuncheck() throws InterruptedException, IOException {
		String statusTrue = "true";
		String statusFalse = "false";
		support.ActivateVerify(statusTrue, statusFalse);
	}

	@And("^Uncheck Disable route to QA and verify all Sites set to false$")
	public void PassiveUncheck() throws InterruptedException, IOException {
		String statusTrue = "false";
		String statusFalse = "true";
		support.passivateVerify(statusTrue, statusFalse);
	}

	@And("^check Disable route to QA and verify all Sites set to false$")
	public void Passivecheck() throws InterruptedException, IOException {
		String statusTrue = "false";
		String statusFalse = "true";
		support.Verify(statusTrue, statusFalse);
	}

	@Then("^Rollback routing to Default \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\"$")
	public void rollback(String ProcessA, String ProcessB, String Action) throws Throwable {

		support.Rollback(ProcessA, Action);
	}

	@Then("Select all process and select lockError Mail in SelectAction")
	public void lockError() throws IOException

	{
		support.lockError();
	}

	@Then("Select all process and select unlockError Mail in SelectAction")
	public void unLockError() throws IOException

	{
		support.unlockError();
	}

	@Then("Select all process and select check site active in SelectAction")
	public void siteActive() throws IOException

	{
		support.siteActive();
	}

	@Then("Select all process and select check error mail in SelectAction")
	public void checkerror() throws IOException

	{
		support.checkError();
	}

	@Then("^Select all process and select passivate site in SelectAction$")
	public void Passivateall() throws IOException {
		support.passivateAll();
	}

	@Then("^Select all process and select Activate site in SelectAction$")
	public void Activateall() throws IOException {
		support.ActivateAll();
	}

	@Then("^Select Handler and select Activate site in SelectAction$")
	public void Activate() throws IOException, InterruptedException {
		support.handlerActive();
	}

	@Then("^Select Handler and select passivate site in SelectAction$")
	public void passive() throws IOException, InterruptedException {
		support.handlerPassive();
	}

	@Then("^verify handler in UI process for Activated sites$")
	public void handlerActive() throws IOException, InterruptedException {
		support.verifyHandlerActive();
	}

	@Then("^verify handler in UI process for passivated sites$")
	public void handlerpassive() throws IOException, InterruptedException {
		support.verifyHandlerpassive();
	}

	@Then("^click on ActiveMQ console link and verify all avaliable brokers links$")
	public void activeMQ() throws Exception {
		support.activeMQ();
	}

}
