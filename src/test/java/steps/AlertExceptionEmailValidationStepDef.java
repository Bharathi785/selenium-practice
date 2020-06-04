package steps;

import com.grip.page.AlertExceptionEmailValidationPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Given;

public class AlertExceptionEmailValidationStepDef extends CucumberRunner {
	AlertExceptionEmailValidationPage alertExceptionEmailValidationPage = new AlertExceptionEmailValidationPage(getDriver());

	@Given("^User verify mail generated as \"([^\"]*)\"$")
	public void User_verify_mail_generated_as(String mailType)  {
		alertExceptionEmailValidationPage.verifyGeneratedEmail(mailType);
	}
	
	@Given("^User navigates to Support Tab in Grip$")
	public void User_navigates_to_Support_Tab_in_Grip()  {
		alertExceptionEmailValidationPage.clickSupportTab();
	}
	
	@Given("^User perfroms actions on processes as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_perfroms_actions_on_processes_as(String processType1, String selectType)  {
		alertExceptionEmailValidationPage.executeOperations(processType1, selectType);
	}
	
	
}
