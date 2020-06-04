package com.grip.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.grip.DBconnection.ContractSQLStatements;
import com.grip.DBconnection.FixedIncomeSQLStatements;
import com.grip.utils.ExcelUtils;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class FixedIncomePage extends AbstractPage {

	public static ExcelUtils excel = new ExcelUtils();

	public static String sessionValue, currenTime, name, status, exchangeTableID = "exchangeTable1", exchangeNameValue,
			feedStatus, dashboardFrameId = "dashboardframe", securityPriceValue, feedTimeValue,
			manualHoldMessage = "Fixed Income security manually held", alertUniqueID, alertUniqueID2, securityIDValue,
			securityNameValue, currentHoldStatus, fixedTableID = "fiiTable1", baseLineUp, baseLineDown, prevTickUp,
			prevTickDown, manualHoldValue = "Manual Hold", researchFrameID = "researchframe",
			reportFrameID = "reportsframe", searchData, showTypeValue, exchangeID = "exchange", indexID = "index",
			autoReleaseMessage = "Auto-released at", warningUp, warningDown, trimmedname, homePath, session = "Open",
			holdFixedstatus = "Not held", subcribedstatus = "Subscribed",securityPriceSecondRow,securityPriceThirdRow;

	public int initialNoTicksGripCount = 0, finalNoTicksGripCount = 0, initialManualHoldCount = 0,
			finalManualHoldCount = 0, initialWarningCount = 0, finalWarningCount = 0;

	public static List<ExtendedWebElement> tableRowSize;

	public FixedIncomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);

	}

	@FindBy(xpath = "//a[contains(@href,'resea')]")
	private ExtendedWebElement research;

	@FindBy(xpath = "//table [@class='display dataTable']//tr//th[@aria-label='Trading session'])")
	private ExtendedWebElement tradingSession;

	@FindBy(xpath = "//h3[contains(@class,'headerfont')]")
	private ExtendedWebElement indexFamily;

	@FindBy(xpath = "//a[contains(@href,'dashboard')]")
	private ExtendedWebElement dashboardTab;

	@FindBy(xpath = "//span[text()='Refresh From T3']")
	private ExtendedWebElement refreshT3;

	@FindBy(xpath = "//span[contains(@data-bind,'fManualHold')]")
	public ExtendedWebElement dashboardManualHold;

	@FindBy(xpath = "//span[contains(@data-bind,'fSubscribed')]")
	public ExtendedWebElement subscribedLink;

	@FindBy(xpath = "//span[contains(@title,'Open a new Fixed Income Securities Window')]")
	private ExtendedWebElement fixedIncome;

	@FindBy(xpath = "//span[contains(@title,'Open a new Exchange Window')]")
	private ExtendedWebElement exchange;

	@FindBy(xpath = "//table[contains(@id,'exchangeTable')]//tbody//tr[1]//td[1]")
	private ExtendedWebElement exchangeElement;

	@FindBy(xpath = "//select[@id='fiiViewsList']")
	private ExtendedWebElement viewDropDown;

	@FindBy(xpath = "//table[@id='contractTable5']//tbody//tr")
	private ExtendedWebElement contractTable;

	@FindBy(xpath = "//div[@id='fiiTable1_filter']//label//input[contains(@title,'search text')]")
	private ExtendedWebElement fixedSearchTextBox;

	@FindBy(xpath = "//div[@id='exchangeTable1_filter']//label//input[contains(@title,'search text')]")
	private ExtendedWebElement exchangeSearchTextBox;

	@FindBy(xpath = "//span[text()='Show Contracts']")
	private ExtendedWebElement showContracts;

	@FindBy(xpath = "//span[text()='Edit Thresholds']")
	private ExtendedWebElement editThresholds;

	@FindBy(xpath = "//tbody[@id='editAutoHoldDtlTblId']//tr[1]//td[3]//input")
	private ExtendedWebElement warningThresholdUp;

	@FindBy(xpath = "//tbody[@id='editAutoHoldDtlTblId']//tr[1]//td[4]//input")
	private ExtendedWebElement warningThresholdDown;

	@FindBy(xpath = "//tbody[@id='editAutoHoldDtlTblId']//tr[1]//td[5]//input")
	private ExtendedWebElement baseLineErrorThresholdUp;

	@FindBy(xpath = "//tbody[@id='editAutoHoldDtlTblId']//tr[1]//td[6]//input")
	private ExtendedWebElement baseLineErrorThresholdDown;

	@FindBy(xpath = "//tbody[@id='editAutoHoldDtlTblId']//tr[1]//td[7]//input")
	private ExtendedWebElement prevTickErrorThresholdUp;

	@FindBy(xpath = "//tbody[@id='editAutoHoldDtlTblId']//tr[1]//td[8]//input")
	private ExtendedWebElement prevTickErrorThresholdDown;

	@FindBy(xpath = "//div[18]//div[11]//div//button[1]/span")
	private ExtendedWebElement thresholdOk;

	@FindBy(xpath = "//span[text()='Hold']")
	private ExtendedWebElement fixedIncomeHold;

	@FindBy(xpath = "//span[text()='Release']")
	private ExtendedWebElement fixedIncomeRelease;

	@FindBy(id = "holdFixedIncomeValueId")
	private ExtendedWebElement holdFixedCurrentValue;

	@FindBy(id = "holdFixedIncomeTypeId")
	private ExtendedWebElement manualReleaseRadioButton;

	@FindBy(id = "holdFixedIncomeTypeAutoId")
	private ExtendedWebElement autoBasedRadioButton;

	@FindBy(xpath = "//span[text()='Ok']")
	public ExtendedWebElement fixedIncomeHoldOk;

	@FindBy(xpath = "//div[@aria-describedby='releaseFixedIncomeDialog']/div[11]/div/button/span[text()='Ok']")
	public ExtendedWebElement releaseOk;

	@FindBy(xpath = "//table[contains(@id,'fiiTable')]//tbody")
	public ExtendedWebElement fixedList;

	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public ExtendedWebElement alertSearch;

	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public WebElement alertSearchScroll;

	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr")
	public ExtendedWebElement alertTableFirstRow;

	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr[2]")
	public ExtendedWebElement alertTableSecondRow;

	@FindBy(xpath = "//span[@class='statusBarSpan']")
	public ExtendedWebElement totalCount;

	@FindBy(xpath = "//input[@title='Download Fixed Income Data']")
	public ExtendedWebElement downloadFixedData;

	@FindBy(xpath = "//div[@id='fiiTable1_wrapper']//div[3]//div//div//table//thead//tr//th[8]")
	public ExtendedWebElement fixedColumn;

	@FindBy(xpath = "//table[@class='display dataTable']//thead//tr")
	public ExtendedWebElement contractColumns;

	@FindBy(xpath = "//span[contains(@data-bind,'noTicksIndexSPF')]")
	public ExtendedWebElement dashboardNoTicksGrip;

	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public ExtendedWebElement CurrentTime;

	@FindBy(xpath = "//div//table[@id='fiiTable1']//tbody//tr")
	private List<WebElement> tableRowData;

	@FindBy(xpath = "(//span[text()='Cancel'])[position()=5]")
	public ExtendedWebElement fixedHoldCancel;

	@FindBy(xpath = "(//span[text()='Cancel'])[2]")
	public ExtendedWebElement copyNameCancel;

	@FindBy(xpath = "(//span[text()='Cancel'])[3]")
	public ExtendedWebElement copyIDCancel;

	@FindBy(xpath = "//table[@id='fiiTable1']//tr//td[3]")
	private ExtendedWebElement fixedIncomeData;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[1]//td[8]")
	public ExtendedWebElement fixedIncomeRICValue;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[2]//td[8]")
	public ExtendedWebElement fixedIncomeRealRICValue;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[3]//td[8]")
	public ExtendedWebElement fixedIncomeName;

	@FindBy(xpath = "//span[text()='Copy Name']")
	public ExtendedWebElement copyName;

	@FindBy(xpath = "//span[text()='Copy ID']")
	public ExtendedWebElement copyId;

	@FindBy(xpath = "//span[text()='Select All Rows']")
	public ExtendedWebElement selectAllRows;

	@FindBy(xpath = "//span[text()='Report']")
	public ExtendedWebElement report;

	@FindBy(xpath = "//textarea[@id='publishNewsTextareaId']")
	public ExtendedWebElement newsTextArea;

	@FindBy(id = "copyNameTextareaId")
	public ExtendedWebElement copyNameClipboard;

	@FindBy(id = "copyIdTextareaId")
	public ExtendedWebElement copyIdClipboard;

	@FindBy(xpath = "//div[@class='topleftcell']//tbody//tr[2]//td//input")
	public ExtendedWebElement filterReportInput;

	@FindBy(xpath = "//span[contains(text(),'Show Exchange')]")
	private ExtendedWebElement showExchange;

	@FindBy(xpath = "//span[contains(text(),'Show Related Indices')]")
	private ExtendedWebElement showIndices;

	@FindBy(xpath = "//table[@class='display dataTable']//tbody/tr/td[5]")
	public List<ExtendedWebElement> ricColumn;

	@FindBy(xpath = "//table[@class='display dataTable']//tbody/tr/td[6]")
	public List<ExtendedWebElement> realRicColumn;

	@FindBy(xpath = "//table[@class='display dataTable']//tbody/tr/td[8]")
	public List<ExtendedWebElement> nameColumn;

	@FindBy(xpath = "//span[contains(@data-bind,'fWarning')]")
	public ExtendedWebElement dashboardWarning;

	public void switchToFrame(String frameID) {
		driver.switchTo().frame(frameID);
	}

	public void switchOutOfFrame() {
		driver.switchTo().defaultContent();
	}

	public void clickDashboard() {
		try {
			switchOutOfFrame();
			dashboardTab.isElementPresent();
			dashboardTab.click();
			switchToFrame(dashboardFrameId);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on dashboard tab-" + e.getMessage());
		}
	}

	public void getTimeInDashboard() {
		try {
			dashboardTab.isElementPresent();
			switchToFrame(dashboardFrameId);
			initialNoTicksGripCount = Integer.parseInt(dashboardNoTicksGrip.getText());
			initialManualHoldCount = Integer.parseInt(dashboardManualHold.getText());
			switchOutOfFrame();
			currenTime = CurrentTime.getText();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get time-" + e.getMessage());
		}
	}

	public void waitForTablefirstRow(String tableID) {
		ExtendedWebElement ele = findExtendedWebElement(
				By.xpath("//table[contains(@id,'" + tableID + "')]/tbody/tr[1]"));
		ele.isElementPresent();
	}

	public void clickResearch() {
		try {
			switchOutOfFrame();
			research.isElementPresent();
			research.click();
			switchToFrame(researchFrameID);
			Assert.assertTrue(indexFamily.isElementPresent());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on research tab-" + e.getMessage());
		}
	}

	public void clickFixedIncome() {
		try {
			fixedIncome.isElementPresent();
			fixedIncome.click();
			waitForTablefirstRow(fixedTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on FixedIncome tab-" + e.getMessage());
		}
	}

	public void getValue(String TradingSession, String HoldingStatus, String DataFeed, String Operation,
			int tableRowSize, int initialCount) {
		try {
			String release = "Release";
			String hold = "Hold";

			for (int i = initialCount; i <= tableRowSize; i++) {
				ExtendedWebElement tradingSessionElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[2]"));
				sessionValue = tradingSessionElement.getText();
				System.out.println(sessionValue);
				ExtendedWebElement holdStatusElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[3]"));
				status = holdStatusElement.getText();
				System.out.println(status);
				ExtendedWebElement dataFeedElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[4]"));
				feedStatus = dataFeedElement.getText();
				System.out.println(feedStatus);
				ExtendedWebElement fixedincomeNameElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[8]"));
				name = fixedincomeNameElement.getText();
				trimmedname = name.substring(0, 4);
				System.out.println(trimmedname);
				ExtendedWebElement securityPriceElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[9]"));
				securityPriceValue = securityPriceElement.getText();
				System.out.println(securityPriceValue);
				ExtendedWebElement feedTimeElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[10]"));
				feedTimeValue = feedTimeElement.getText();
				ExtendedWebElement exchangeName = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[24]"));
				exchangeNameValue = exchangeName.getText();
				System.out.println(exchangeNameValue);

				if (sessionValue.equalsIgnoreCase(TradingSession) && status.equalsIgnoreCase(HoldingStatus)
						&& feedStatus.equalsIgnoreCase(DataFeed) && Double.parseDouble(securityPriceValue) > 0) {
					tradingSessionElement.isElementPresent();
					tradingSessionElement.click();
					tradingSessionElement.rightClick();
					if (Operation.equalsIgnoreCase(hold)) {
						fixedIncomeHold.isElementPresent();
						fixedIncomeHold.click();
					}
					if (Operation.equalsIgnoreCase(release)) {
						releaseFixed();
					}
					break;
				}

				else if (status.equalsIgnoreCase("Manual Hold") && Operation.equalsIgnoreCase(release)) {
					tradingSessionElement.isElementPresent();
					tradingSessionElement.click();
					tradingSessionElement.rightClick();
					releaseFixed();
					break;
				} else {
					Assert.fail("Warning Message: Fixed Income is not in Open state ");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to select fixedincome for performing Hold/Release Operation " + e.getMessage());
		}
	}

	public void holdFixed() {
		try {
			getCurrentValue();  
			if (Double.parseDouble(securityPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
					&& status.equalsIgnoreCase(holdFixedstatus) && feedStatus.equalsIgnoreCase(subcribedstatus)) {
				fixedIncomeHold.isElementPresent();
				fixedIncomeHold.click();
			} else {
				Assert.fail("Warning Message: fixedincome is not satisfying the precondition");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("unable to click Hold Button" + e.getMessage());

		}
	}

	public void releaseFixed() {
		try {
			fixedIncomeRelease.isElementPresent();
			fixedIncomeRelease.click();
			clickSaveFixedOk();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("unable to click release Button" + e.getMessage());

		}
	}

	public void clickSaveFixedOk() {
		try {
			fixedIncomeHoldOk.isElementPresent();
			fixedIncomeHoldOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on save fixedincome ok-" + e.getMessage());
		}
	}

	public void selectFixed(String TradingSession, String HoldingStatus, String DataFeed, String Operation) {
		try {
			tableRowSize = fixedList.findExtendedWebElements(By.tagName("tr"));
			getValue(TradingSession, HoldingStatus, DataFeed, Operation, tableRowSize.size(), 1);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform hold operation on selected contracts-" + e.getMessage());
		}
	}

	public void verifyCurrentPriceValue(String holdType) {
		try {
			holdFixedCurrentValue.isElementPresent();
			Assert.assertTrue(manualReleaseRadioButton.getAttribute("checked").equalsIgnoreCase("true"));
			if (securityPriceValue.equalsIgnoreCase(holdFixedCurrentValue.getAttribute("value"))) {
				Assert.assertEquals(securityPriceValue, holdFixedCurrentValue.getAttribute("value"));
			}
			if (holdType.equalsIgnoreCase("Auto Hold")) {
				autoBasedRadioButton.click();
			} else {
			}
			fixedIncomeHoldOk.isElementPresent();
			fixedIncomeHoldOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify current price value-" + e.getMessage());
		}

	}

	public void getUniqueID() {
		try {
			alertSearch.type(trimmedname);
			alertSearch.pause(22);
			alertUniqueID = alertTableFirstRow.getAttribute("id");
			alertUniqueID2 = alertTableSecondRow.getAttribute("id");
			System.out.println("Alert ID=" + alertUniqueID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get unique ID-" + e.getMessage());
		}
	}

	public int getDate(String timeDate, int startValue, int endValue) {
		String getDate = timeDate.substring(startValue, endValue);
		String[] split = getDate.split(":");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			sb.append(split[i]);
		}
		return Integer.parseInt(sb.toString());
	}

	public void VerifyAlertsMessages(String subjectInput, String alertTypeInput, String timeValue,
			String descriptionInput) throws Exception {
		try {
			boolean alertCondition = false;
			for (int i = 1; i <= 15; i++) {
				String timeUTC = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody[@role='alert']/tr[" + i + "]/td[1]")).getText();
				int tableUTCTime = getDate(timeUTC, 12, 20);
				int loginUTCTime = getDate(timeValue, 17, 25);
				String alertType = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[2]")).getText();
				String subject = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[4]")).getText();
				String trimmedsubject = subject.substring(0, 5);
				String description = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[6]")).getText();

				if (tableUTCTime >= loginUTCTime && alertType.equalsIgnoreCase(alertTypeInput)
						&& trimmedsubject.contains(subjectInput) && description.contains(descriptionInput)) {
					Assert.assertTrue(tableUTCTime >= loginUTCTime);
					Assert.assertTrue(alertType.equalsIgnoreCase(alertTypeInput));
					Assert.assertTrue(subject.contains(subjectInput));
					Assert.assertTrue(description.contains(descriptionInput));
					alertCondition = true;
					break;
				}
			}
			if (alertCondition) {
			} else
				Assert.fail("The data is not matched for verifying alerts-");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the alert messages-" + e.getMessage());
		}
	}

	public void getSecurityID() {
		try {
			viewDropDown.select("Identifier View");
			for (int i = 1; i < 10; i++) {

				ExtendedWebElement contractID = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[1]"));
				securityIDValue = contractID.getText();
				System.out.println(securityIDValue);
				ExtendedWebElement contractNameElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[2]"));
				securityNameValue = contractNameElement.getText();
				if (securityNameValue.equalsIgnoreCase(name)) {
					break;
				}
			}
			viewDropDown.select("Quick View");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch the securityId-" + e.getMessage());
		}
	}

	public void getCurrentStatus() {
		try {
			fixedSearchTextBox.type(securityIDValue);
			ExtendedWebElement HoldStatusElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr//td[3]"));
			currentHoldStatus = HoldStatusElement.getText();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch current hold status -" + e.getMessage());

		}
	}

	public void verifyHoldAlert(String holdType) {
		try {
			String releaseHoldMessage = "Manually released";
			fixedIncomeHoldOk.pause(40);
			if (holdType.equalsIgnoreCase("manualHold")) {
				getSecurityID();
				getCurrentStatus();
				Assert.assertTrue(currentHoldStatus.equalsIgnoreCase("Manual Hold"));
				getUniqueID();
				VerifyAlertsMessages(trimmedname, "Manual Hold", currenTime, manualHoldMessage);
				ContractSQLStatements.getAlertStatus(alertUniqueID);
				Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(manualHoldMessage));
				FixedIncomeSQLStatements.getHoldStatus();
				Assert.assertEquals(FixedIncomeSQLStatements.holdStatus, "Manual Hold");
			} else {
				getCurrentStatus();
				Assert.assertTrue(currentHoldStatus.equalsIgnoreCase("Not Held"));
				getUniqueID();
				VerifyAlertsMessages(trimmedname, "Manual Release", currenTime, releaseHoldMessage);
				ContractSQLStatements.getAlertStatus(alertUniqueID);
				Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(releaseHoldMessage));
				FixedIncomeSQLStatements.getHoldStatus();
				Assert.assertTrue(FixedIncomeSQLStatements.holdStatus.equalsIgnoreCase("Not Held"));
			}
			getDriver().navigate().refresh();
			clickDashboard();
			dashboardManualHold.isElementPresent();
			dashboardManualHold.pause(25);
			finalManualHoldCount = Integer.parseInt(dashboardManualHold.getText());
			if (holdType.equalsIgnoreCase("manualHold"))
				Assert.assertEquals(initialManualHoldCount + 1, finalManualHoldCount);
			else
				Assert.assertEquals(initialManualHoldCount, finalManualHoldCount);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the hold/release alert-" + e.getMessage());
		}
	}

	public void verifyAlert() {
		try {
			String alertMessage = "fixed income security was not held";
			fixedIncomeHoldOk.pause(30);
			getUniqueID();
			VerifyAlertsMessages(trimmedname, "Manual Release", currenTime, alertMessage);
			ContractSQLStatements.getAlertStatus(alertUniqueID);
			Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(alertMessage));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the alert message-" + e.getMessage());
		}
	}

	public void verifyAutoHoldAlertMessages() {
		try {
			String autoReleaseMessage = "Auto-released at";
			String autoHoldMessage = "Fixed Income security manually held";
			fixedIncomeHoldOk.pause(30);
			getSecurityID();
			getUniqueID();
			VerifyAlertsMessages(trimmedname, "Manual Hold", currenTime, autoHoldMessage);
			fixedIncomeHoldOk.pause(40);
			VerifyAlertsMessages(trimmedname, "Auto-Release", currenTime, autoReleaseMessage);
			ContractSQLStatements.getAlertStatus(alertUniqueID);
			Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(autoReleaseMessage));
			ContractSQLStatements.getAlertStatus(alertUniqueID2);
			Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(autoHoldMessage));
			FixedIncomeSQLStatements.getHoldStatus();
			Assert.assertEquals(FixedIncomeSQLStatements.holdStatus, "Not Held");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the auto based/release alert-" + e.getMessage());
		}
	}

	public void baseLineHoldStatus(String holdtype) {
		String autohold = "Auto Hold";
		String warning = "Warning";

		try {
			if (holdtype.equalsIgnoreCase(autohold)) {
				viewDropDown.select("On Hold");
				fixedIncomeHoldOk.pause(150);

				for (int i = 1; i <= 10; i++) {
					ExtendedWebElement tradingSessionElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[2]"));
					sessionValue = tradingSessionElement.getText();
					System.out.println(sessionValue);
					ExtendedWebElement holdStatusElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[3]"));
					status = holdStatusElement.getText();
					System.out.println(status);
					ExtendedWebElement fixedincomeNameElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[8]"));
					name = fixedincomeNameElement.getText();
					trimmedname = name.substring(0, 4);
					System.out.println(trimmedname);

					if (status.equalsIgnoreCase(holdtype)) {
						break;
					}
				}
			} else if (holdtype.equalsIgnoreCase(warning)) {
				viewDropDown.select("On Warning");
			       fixedIncomeHoldOk.pause(60);
				for (int i = 1; i <= 10; i++) {
					ExtendedWebElement tradingSession = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[2]"));
					sessionValue = tradingSession.getText();
					ExtendedWebElement holdStatusFixed = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[3]"));
					status = holdStatusFixed.getText();
					ExtendedWebElement contractNameElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[" + i + "]//td[8]"));
					name = contractNameElement.getText();
					if (status.equalsIgnoreCase(holdtype)) {
						break;	
	       	}
			  }
			}
			else {
				fixedIncomeHoldOk.pause(60);
				fixedSearchTextBox.type(trimmedname);
				ExtendedWebElement HoldStatusElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr//td[3]"));
				currentHoldStatus = HoldStatusElement.getText();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify hold status -" + e.getMessage());
		}
	}

	public void verifyBaselineMessages(String dataUpdate) {
		try {
			fixedIncomeHoldOk.pause(25);
			getUniqueID();
			if (dataUpdate.equalsIgnoreCase("update")) {
				VerifyAlertsMessages(trimmedname, "Threshold Breach", currenTime, "Current price");
				getDriver().navigate().refresh();
			} else {
				VerifyAlertsMessages(trimmedname, "Auto-Release", currenTime, "Auto-released at");
				fixedIncomeHoldOk.pause(20);
				Assert.assertTrue(currentHoldStatus.equalsIgnoreCase("Not Held"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify baseline alert messages-" + e.getMessage());
		}
	}

	public void fixedIncomeManualHold(String fiName, String holdtype) {
		try {
			fixedSearchTextBox.type(fiName);
			ExtendedWebElement holdStatusElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[1]//td[3]"));
			//status = holdStatusElement.getText();
			if (holdtype.equalsIgnoreCase(holdFixedstatus)) {
				holdStatusElement.click();
				holdStatusElement.rightClick();
				holdFixed();
				fixedIncomeHoldOk.isElementPresent();
				fixedIncomeHoldOk.click();
			} else {
				holdStatusElement.click();
				holdStatusElement.rightClick();
				releaseFixed();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify hold status of contracts -" + e.getMessage());
		}
	}

	public void negativeNumber() {
		try {
			holdFixedCurrentValue.type("-1234");
			String DefaultValue = holdFixedCurrentValue.getAttribute("value");
			Assert.assertTrue(Integer.parseInt(DefaultValue) < 0, "The fixedincome value is in negative");
			fixedHoldCancel.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the negative value range in hold window-" + e.getMessage());
		}
	}

	public void clickOnSubscribedLink() {
		try {
			switchToFrame(dashboardFrameId);
			subscribedLink.isElementPresent();
			subscribedLink.click();
			switchOutOfFrame();
			switchToFrame(researchFrameID);
			waitForTablefirstRow(fixedTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Subscribed link-" + e.getMessage());
		}
	}

	public void holdReleaseMultipleContracts(String actionType) {
		try {
			Actions builder = new Actions(getDriver());
			if (actionType.equalsIgnoreCase("hold")) {
				waitForTablefirstRow(fixedTableID);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				holdFixed();
				fixedIncomeHoldOk.isElementPresent();
				Assert.assertTrue(autoBasedRadioButton.isElementNotPresent(2), "Auto based hold option is visible");
				clickSaveFixedOk();
				fixedIncomeHold.pause(25);
				getCurrentValue();
				getUniqueID();
				VerifyAlertsMessages(name, manualHoldValue, currenTime, manualHoldMessage);
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase(manualHoldValue));
			} else {
				builder.contextClick().build().perform();
				fixedIncomeRelease.isElementPresent();
				fixedIncomeRelease.click();
				releaseOk.isElementPresent();
				releaseOk.click();
				fixedIncomeHold.pause(25);
				getCurrentValue();
				getUniqueID();
				VerifyAlertsMessages(name, "Manual Release", currenTime, "Manually released");
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Not Held"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release multiple fixedincome-" + e.getMessage());
		}
	}

	public void getCurrentValue() {
		try {
			ExtendedWebElement tradingSessionElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr//td[2]"));
			sessionValue = tradingSessionElement.getText();
			System.out.println(sessionValue);
			ExtendedWebElement holdStatusElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr//td[3]"));
			status = holdStatusElement.getText();
			System.out.println(status);
			ExtendedWebElement dataFeedElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr//td[4]"));
			feedStatus = dataFeedElement.getText();
			System.out.println(feedStatus);
			ExtendedWebElement fixedincomeNameElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr//td[8]"));
			name = fixedincomeNameElement.getText();
			trimmedname = name.substring(0, 4);
			System.out.println(trimmedname);
			ExtendedWebElement feedPriceElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr//td[9]"));
			securityPriceValue = feedPriceElement.getText();
			System.out.println(securityPriceValue);
			/*ExtendedWebElement feedPriceElement2 = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[2]//td[9]"));
			securityPriceSecondRow = feedPriceElement2.getText();
			System.out.println(securityPriceSecondRow);
			ExtendedWebElement feedPriceElement3 = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[3]//td[9]"));
			securityPriceThirdRow = feedPriceElement3.getText();
			System.out.println(securityPriceThirdRow);*/
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch the current value of fixed income-" + e.getMessage());
		}
	}

	public void holdReleaseFixedMultipleTime(String actionType) {
		try {
			//int loopcount = 0;
			if (actionType.equalsIgnoreCase("holdMultiple")) {
				holdMultipleTimes();
			} else {
				getCurrentValue();
				performRightClickAction();
				fixedIncomeRelease.isElementPresent();
				fixedIncomeRelease.click();
				releaseOk.isElementPresent();
				releaseOk.click();
				thresholdOk.pause(50);
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Not Held"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release fixedincome for multiple times-" + e.getMessage());
		}
	}
	
	public void holdMultipleTimes()
	{
		int loopcount = 0;
		getCurrentValue();
		try {
			if (Double.parseDouble(securityPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
					&& status.equalsIgnoreCase(holdFixedstatus) && feedStatus.equalsIgnoreCase(subcribedstatus)) {
			for (int i = 0; i < 3; i++) {
				performRightClickAction();
				fixedIncomeHold.isElementPresent();
				fixedIncomeHold.click();
				clickSaveFixedOk();
				if (loopcount == 0)
					holdFixedCurrentValue.pause(50);
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase(manualHoldValue));
				loopcount++;
			}}
			else {
				Assert.fail("Warning Message: fixedincome is not satisfying the precondition");
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to perform hold operation  on Multiple fixedincome-" + e.getMessage());
			}
			
	}
	public void performRightClickAction() {
		try {
			if (status.equalsIgnoreCase(holdFixedstatus) && feedStatus.equalsIgnoreCase(subcribedstatus)) {
				for (int i = 0; i < 3; i++) {
					if (fixedIncomeData.isElementPresent()) {
						fixedIncomeData.click();
						fixedIncomeData.rightClick();
						break;
					}
				}
			} else if (status.equalsIgnoreCase("Manual Hold") && feedStatus.equalsIgnoreCase(subcribedstatus)) {
				for (int i = 0; i < 3; i++) {
					if (fixedIncomeData.isElementPresent()) {
						fixedIncomeData.click();
						fixedIncomeData.rightClick();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perfrom right click action on selected fixedincome-" + e.getMessage());
		}
	}

	public void searchFixed(String fixedID) {
		try {
			searchData = fixedID;
			fixedSearchTextBox.type(searchData);
			waitForTablefirstRow(fixedTableID);
			getCurrentValue();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search fixed income-" + e.getMessage());
		}
	}

	public void verifySearchListedValues() {
		try {
			ArrayList<String> nameColumnValues = new ArrayList<>();

			String previousRICValue = fixedIncomeRICValue.getText();
			String previousRealRICValue = fixedIncomeRealRICValue.getText();
			String previousName = fixedIncomeName.getText();
		String 	actualRICValue=previousRICValue.substring(0,4);
		System.out.println(actualRICValue);
		String actualRealRICValue=previousRealRICValue.substring(0,3);
		System.out.println(actualRealRICValue);
		String actualName=previousName.substring(0, 4);
		System.out.println(actualName);
		
			String tSearch = (actualRICValue + "," + actualRealRICValue + "," + actualName);
			fixedSearchTextBox.type(tSearch);
			fixedIncomeHold.pause(5);
			waitForTablefirstRow(fixedTableID);

			for (int i = 0; i < 3; i++) {
				nameColumnValues.add(nameColumn.get(i).getText());
			}
			Assert.assertTrue(nameColumnValues.contains(previousRICValue));
			 Assert.assertTrue(nameColumnValues.contains(previousRealRICValue));
			Assert.assertTrue(nameColumnValues.contains(previousName));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the searched fixed income value with comma separated-" + e.getMessage());
		}
	}

	public void verifyIndexFilter() {
		try {
			String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC05033", "TestData1");
			int index = 0;
			String View = "Quick View";
			indexVerifyFilter(Search, index, View);
			index = 1;
			View = "Identifier View";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC05033", "TestData2");
			indexVerifyFilter(Search, index, View);
			index = 3;
			View = "Technical Support";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC05033", "TestData4");
			indexVerifyFilter(Search, index, View);
			index = 4;
			View = "Thresholds View";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC05033", "TestData5");
			indexVerifyFilter(Search, index, View);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify filter headers combo-" + e.getMessage());
		}
	}

	public void indexVerifyFilter(String Search, int index, String View) {
		try {
			viewDropDown.select(index);
			waitForTablefirstRow(fixedTableID);
			HashSet<String> column = new HashSet<String>();
			HashSet<String> quickView = new HashSet<String>();
			List<ExtendedWebElement> tableRows = contractColumns.findExtendedWebElements(By.tagName("th"));

			for (int i = 1; i <= tableRows.size(); i++) {
				ExtendedWebElement contractsColumn = findExtendedWebElement(
						By.xpath("//table[@id='fiiTable1']//tr[1]//td[" + i + "]"));
				contractsColumn.click();
				ExtendedWebElement columnName = findExtendedWebElement(
						By.xpath("//table[@class='display dataTable']//thead//tr//th[" + i + "]"));
				column.add(columnName.getText());
			}
			String searchSplit[] = Search.split(",");
			for (int k = 0; k < searchSplit.length; k++) {
				quickView.add(searchSplit[k]);
			}

			Object[] array = quickView.toArray();
			System.out.println(column);
			System.out.println(quickView);

			for (int i = 0; i < quickView.size(); i++) {
				Assert.assertTrue(column.contains(array[i]));
			}
			column.clear();
			quickView.clear();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify filter headers" + e.getMessage());
		}
	}

	public void fixedDownload() throws IOException, InterruptedException {
		HashSet<String> SetExcel = new HashSet<String>();
		HashSet<String> IndexData = new HashSet<String>();

		String Alert = totalCount.getText();
		String Column = fixedColumn.getText();

		IndexData.add(Column);
		SetExcel.add(Column);
		String requiredString = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf("(") - 1);
		int Al_count = Integer.parseInt(requiredString);

		downloadFixedData.click();
		downloadFixedData.pause(10);

		/*
		 * File LatestFile = getLatestFilefromDir(R.TESTDATA.get("downloadSheetPath"));
		 * BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
		 * int count = countRecord(LatestFile);
		 */

		homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);
		int count = countRecord(LatestFile);
		BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));

		Assert.assertEquals((count - 2), Al_count);

		for (int i = 1; i < 5; i++) {
			ExtendedWebElement A_Name = findExtendedWebElement(
					By.xpath("//table[@class='display dataTable']//tr[" + i + "]//td[6]"));
			String R_Name = A_Name.getText();
			IndexData.add(R_Name);
		}

		String row = "";
		String[] data = null;

		for (int i = 0; i < 5; i++) {
			if ((row = csvReader.readLine()) != null) {
				data = row.split(",");
				System.out.println(data[5]);
				if (!data[5].contains(Column)) {
					SetExcel.add(data[5].substring(1, data[5].length() - 1));
				}
			}
		}
	}

	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	@SuppressWarnings("resource")
	public int countRecord(File Dirpath) throws IOException {
		int count = 0;
		BufferedReader csvReader = new BufferedReader(new FileReader(Dirpath));
		String row;
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			count++;
		}
		return count;
	}

	public void copyName() {
		try {
			rightClickOperation();
			copyName.isElementPresent();
			copyName.click();
			copyNameClipboard.isElementPresent();
			String trimName = copyNameClipboard.getAttribute("value").replaceAll("\\s+", " ");
			Assert.assertTrue(name.equalsIgnoreCase(trimName));
			// Assert.assertTrue(name.contains(copyNameClipboard.getAttribute("value").substring(0,3)));
			System.out.println(name);
			copyNameCancel.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate copy name" + e.getMessage());
		}
	}

	public void copyId() {
		try {
			rightClickOperation();
			copyId.isElementPresent();
			copyId.click();
			copyIdClipboard.isElementPresent();
			Assert.assertTrue(copyIdClipboard.getAttribute("value").equalsIgnoreCase(searchData));
			copyIDCancel.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate copy id" + e.getMessage());
		}
	}

	public void clickReport() {
		try {
			rightClickOperation();
			report.click();
			switchOutOfFrame();
			switchToFrame(reportFrameID);
			filterReportInput.isElementPresent();
			String reportName = filterReportInput.getAttribute("value").replaceAll("\\s+", " ");
			Assert.assertTrue(reportName.contains(name));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate Report" + e.getMessage());
		}
	}

	public void rightClickOperation() {
		try {
			fixedIncomeData.isElementPresent();
			fixedIncomeData.click();
			fixedIncomeData.rightClick();
			getCurrentValue();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate Report" + e.getMessage());
		}
	}

	public void selectAllRows() {
		try {
			fixedIncomeData.click();
			fixedIncomeData.rightClick();
			selectAllRows.isElementPresent();
			selectAllRows.click();
			selectAllRows.pause(10);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//div[contains(@id,'" + fixedTableID + "')]//span[@class='statusBarSpan']"));
			String[] countValues = ele.getText().split("S");
			Assert.assertEquals(countValues[0].replaceAll("[^0-9]", ""), countValues[1].replaceAll("[^0-9]", ""));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the count for all selected rows-" + e.getMessage());
		}
	}

	public void showDetails(String showType) throws InterruptedException, IOException {
		try {
			showTypeValue = showType;
			ExtendedWebElement totalCountValue = null;
			String totalCountText;
			if (showTypeValue.equalsIgnoreCase("exchange")) {
				rightClickOperation();
				showExchange.isElementPresent();
				showExchange.click();
				totalCountValue = findExtendedWebElement(
						By.xpath("//div[contains(@id,'" + exchangeID + "')]//span[@class='statusBarSpan']"));
				totalCountValue.isElementPresent();
				waitForTablefirstRow(exchangeID);
				totalCountText = totalCountValue.getText().substring(0, totalCountValue.getText().indexOf("("));
				FixedIncomeSQLStatements.getExchangeCount();
				Assert.assertEquals(totalCountText.replaceAll("[^0-9]", ""), FixedIncomeSQLStatements.exchangeCount);
			} else {
				rightClickOperation();
				showIndices.isElementPresent();
				showIndices.click();
				totalCountValue = findExtendedWebElement(
						By.xpath("//div[contains(@id,'" + indexID + "')]//span[@class='statusBarSpan']"));
				totalCountValue.isElementPresent();
				waitForTablefirstRow(indexID);
				totalCountText = totalCountValue.getText().substring(0, totalCountValue.getText().indexOf("("));
				FixedIncomeSQLStatements.getIndexCount();
				Assert.assertEquals(totalCountText.replaceAll("[^0-9]", ""), FixedIncomeSQLStatements.indicesCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform action on show details and verify value-" + e.getMessage());
		}
	}

	public void holdReleaseMultipleFixed(String actionType) {
		try {
			Actions builder = new Actions(getDriver());
			if (actionType.equalsIgnoreCase("hold")) {
				holdingMultipleFix();
			} else {
				waitForTablefirstRow(fixedTableID);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				fixedIncomeRelease.isElementPresent();
				scrollToDown();
				rightclickfixedStaleException();
				fixedIncomeHold.pause(25);
				getCurrentValue();
				getUniqueID();
				VerifyAlertsMessages(trimmedname, "Manual Release", currenTime, "Manually released");
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Not Held"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release multiple fixedincome -" + e.getMessage());
		}
	}

	public void scrollToDown() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", alertSearchScroll);
			alertSearch.pause(1);
			PageFactory.initElements(driver, this);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to scroll to alert search box-" + e.getMessage());
		}
	}

	public void holdingMultipleFix() throws Exception {
		try {
			getCurrentValue();
			Actions builder = new Actions(getDriver());
					ExtendedWebElement feedPriceElement2 = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[2]//td[9]"));
					securityPriceSecondRow = feedPriceElement2.getText();
					System.out.println(securityPriceSecondRow);
					ExtendedWebElement feedPriceElement3 = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + fixedTableID + "')]//tbody//tr[3]//td[9]"));
					securityPriceThirdRow = feedPriceElement3.getText();
					System.out.println(securityPriceThirdRow);
			
			if (Double.parseDouble(securityPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
					&& status.equalsIgnoreCase(holdFixedstatus) && Double.parseDouble(securityPriceSecondRow) > 0 && Double.parseDouble(securityPriceThirdRow) > 0)  {
				waitForTablefirstRow(fixedTableID);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				fixedIncomeHold.isElementPresent();
				fixedIncomeHold.click();
				fixedIncomeHoldOk.isElementPresent();
				Assert.assertTrue(autoBasedRadioButton.isElementNotPresent(2), "Auto based hold option is visible");
				clickSaveFixedOk();
				fixedIncomeHold.pause(25);
				getCurrentValue();
				getUniqueID();
				VerifyAlertsMessages(trimmedname, manualHoldValue, currenTime, manualHoldMessage);
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase(manualHoldValue));
				getDriver().navigate().refresh();
			} else {
				Assert.fail("Warning Message: fixedincome is not satisfying the precondition");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to hold multiple fixed income-" + e.getMessage());
		}
	}

	public void rightclickfixedStaleException() {
		int Counter = 0;
		PageFactory.initElements(driver, this);
		try {
			do {
				if (fixedIncomeRelease.getElement().isEnabled() && fixedIncomeRelease.getElement().isDisplayed()) {
					fixedIncomeRelease.pause(1);
					Counter = Counter + 1;
					fixedIncomeRelease.click();
					fixedIncomeRelease.pause(1);
					releaseOk.click();
				}
			} while (Counter == 0);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			Assert.fail("Failed due to stale element for right click action-" + e.getMessage());
		}
	}

	public void updateThreshold() {
		try {
			if (Double.parseDouble(securityPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
					&& status.equalsIgnoreCase(holdFixedstatus) && feedStatus.equalsIgnoreCase(subcribedstatus)) {
			FixedIncomeSQLStatements.updateBaselineValue();
			rightClickOperation();
			showExchange.isElementPresent();
			showExchange.click();
			exchangeElement.click();
			scrollToDown();
			rightclickStaleException();
			refreshT3.isElementPresent();
			refreshT3.click();
			getDriver().navigate().refresh();
			}
			else {
				Assert.fail("Fixed Income is not satisying the precondition-");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update baseline threshold value-" + e.getMessage());

		}
	}

	public void performReleaseOperation(String holdType) {
		try {
			getCurrentValue();
			if (status.equalsIgnoreCase(holdType)) {
				rightClickOperation();
				releaseFixed();
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform release operation-" + e.getMessage());
		}
	}

	public void performHoldOperation(String holdType) {
		try {
			getCurrentValue();
			thresholdOk.pause(15);
			if (status.equalsIgnoreCase(holdType)) {
				rightClickOperation();
				fixedIncomeHold.isElementPresent();
				fixedIncomeHold.click();
				clickSaveFixedOk();
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform hold operation-" + e.getMessage());
		}
	}

	public void verifyHoldChangeStatus(String holdType) {
		try {
			fixedIncomeHold.pause(50);
			getCurrentValue();
			alertSearch.type(trimmedname);
			VerifyAlertsMessages(trimmedname, "Auto-Release", currenTime, autoReleaseMessage);
			Assert.assertTrue(status.equalsIgnoreCase(holdType));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to verify hold status-" + e.getMessage());
		}
	}

	public void getWarningThresholdhValues() {
		try {
			warningThresholdUp.isElementPresent();
			warningUp = warningThresholdUp.getAttribute("value");
			warningDown = warningThresholdDown.getAttribute("value");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get warning threshold up down percent values-" + e.getMessage());
		}
	}

	public void modifyWarningValues(String WarningUpdate, String warningValue) {
		try {

			if (WarningUpdate.equalsIgnoreCase("update")) {
				warningThresholdUp.pause(10);
				warningThresholdUp.type(warningValue);
				warningThresholdDown.type(warningValue);
			} else {
				warningThresholdUp.pause(10);
				warningThresholdUp.type(warningUp);
				warningThresholdDown.type(warningDown);
			}

			thresholdOk.isElementPresent();
			thresholdOk.click();
			exchangeElement.click();
			scrollToDown();
			rightclickStaleException();
			refreshT3.click();
			getDriver().navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to modify warning threshold up down percent values-" + e.getMessage());
		}
	}

	public void verifyWarningAlertMessages(String holdtype,String WarningUpdate) {
		try {
			String update = "update";
			String warning = "warning";
			fixedIncomeHoldOk.pause(25);
			getUniqueID();
			if (WarningUpdate.equalsIgnoreCase(update)) {
				VerifyAlertsMessages(trimmedname, "Warning Threshold", currenTime, "Current price");
			} else {
				VerifyAlertsMessages(trimmedname, "Warning Threshold", currenTime, "Fixed income  is set off warning");
				fixedIncomeHoldOk.pause(45);
			}
			clickDashboard();
			dashboardWarning.isElementPresent();
			dashboardWarning.pause(30);
			finalWarningCount = Integer.parseInt(dashboardWarning.getText());
			if (holdtype.equalsIgnoreCase(warning)) {
				Assert.assertTrue(initialWarningCount < finalWarningCount);
				getDriver().navigate().refresh();
			} else {
				Assert.assertTrue(initialWarningCount < finalWarningCount);
				getDriver().navigate().refresh();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify warning alert messages-" + e.getMessage());
		}
	}

	public void rightclickStaleException() {
		int Counter = 0;
		PageFactory.initElements(driver, this);
		try {
			do {
				if (exchangeElement.getElement().isEnabled() && exchangeElement.getElement().isDisplayed()) {
					exchangeElement.pause(1);
					Counter = Counter + 1;
					exchangeElement.click();
					exchangeElement.pause(1);
					exchangeElement.rightClick();
				}
			} while (Counter == 0);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			Assert.fail("Failed due to stale element for right click action-" + e.getMessage());
		}

	}
	
	
	
	

}
