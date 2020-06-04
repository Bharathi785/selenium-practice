package com.grip.page;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.grip.utils.EmailValidation;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class AlertExceptionEmailValidationPage extends AbstractPage {

	public AlertExceptionEmailValidationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(id = "processId")
	private ExtendedWebElement selectProcess;

	@FindBy(id = "actionId")
	private ExtendedWebElement selectAction;

	@FindBy(id = "indexActionId")
	private ExtendedWebElement IDTextBox;

	@FindBy(id = "executeSupportActionId")
	private ExtendedWebElement execute;

	@FindBy(id = "outputId")
	private ExtendedWebElement ouputITextArea;

	@FindBy(xpath = "//a[contains(@href,'supportTab')]")
	private ExtendedWebElement supportTab;

	@FindBy(xpath = "//div[@id='activeMQConsoleTableId']")
	public ExtendedWebElement activeMQ;

	Logger LOGGER = Logger.getLogger(AlertExceptionEmailValidationPage.class);
	public static String capturedsystemTime;

	public void clickSupportTab() {
		try {
			supportTab.isElementPresent();
			supportTab.click();
			getDriver().switchTo().frame("supportframe");
			Assert.assertTrue(activeMQ.isElementPresent());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Support tab-" + e.getMessage());
		}
	}

	public void executeOperations(String processType1, String selectType) {
		try {
			String verifyText = null;
			selectProcess.select(processType1);
			selectAction.select(selectType);
			execute.click();
			ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
			capturedsystemTime = utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			execute.pause(5);
			String UIText = ouputITextArea.getAttribute("value");
			if (selectType.contains("LOCK"))
				verifyText = "Error Mailing Manually locked";
			else
				verifyText = "Error Mailing Manually unlocked";
			Assert.assertTrue(UIText.contains(verifyText),
					"Key values are not matching after executig TL support actions");
			execute.pause(R.TESTDATA.getDouble("MAIL_HALT_TIME"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to execute support TL operations-" + e.getMessage());
		}
	}

	public void verifyGeneratedEmail(String mailType) {
		try {
			String emailSubject = null;
			String emailBodyContent = null;

			switch (mailType) {
			case "DB Query runtime":
				emailSubject = "QA : DB_QUERY_RUNTIME_REPORT";
				emailBodyContent = "Report attached";
				break;

			case "DB Query runtime remote":
				emailSubject = "QA : DB_QUERY_RUNTIME_REPORT";
				emailBodyContent = "Report attached";
				break;

			case "Auto Lock Exception":
				emailSubject = "QA : Error mailing Auto locked for process";
				emailBodyContent = "Error mailing has been Auto locked for process";
				break;

			case "Auto UnLock Exception":
				emailSubject = "QA : Error mailing Auto unlocked for process";
				emailBodyContent = "Error mailing has been Auto unlocked for process";
				break;

			case "Auto Lock Alert":
				emailSubject = "QA : Alert mailing has been locked for process";
				emailBodyContent = "Alert mailing has been locked for process";
				break;

			case "Auto UnLock Alert":
				emailSubject = "QA : Alert mailing Auto unlocked for process";
				emailBodyContent = "Alert mailing has been unlocked for process";
				break;

			case "Manual Lock Alert":
				emailSubject = "QA : Error mailing Manually locked for process";
				emailBodyContent = "Error mailing has been Manually locked for process";
				break;

			case "Manual UnLock Alert":
				emailSubject = "QA : Error mailing Manually unlocked for process";
				emailBodyContent = "Error mailing has been Manually unlocked for process";
				break;

			default:
				break;
			}

			if (!mailType.equalsIgnoreCase("Manual Lock Alert") || !mailType.equalsIgnoreCase("Manual UnLock Alert"))
				Assert.assertTrue(EmailValidation.isMailReceivedBySubject(emailSubject, emailBodyContent),
						"Unable to verify Email generated for " + mailType);
			else
				Assert.assertTrue(EmailValidation.isMailReceivedByTimeAndSubject(emailSubject, emailBodyContent,
						capturedsystemTime), "Unable to verify Email generated for " + mailType);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify generated email -" + e.getMessage());
		}
	}

}
