package com.grip.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.grip.utils.ExcelUtils;
import com.grip.DBconnection.ContractSQLStatements;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class ContractsPage extends AbstractPage {

	public static ExcelUtils excel = new ExcelUtils();
	Logger LOGGER = Logger.getLogger(ContractsPage.class);

	public static String sessionValue, currenTime, name, status, exchangeTableID = "exchangeTable1", exchangeNameValue,
			feedStatus, dashboardFrameId = "dashboardframe", feedPriceValue, feedTimeValue,
			manualHoldMessage = "Contract held", alertUniqueID, alertUniqueID2, contractIDValue, contractNameValue,
			currentHoldStatus, contractTableID = "contractTable1", baseLineUp, baseLineDown, prevTickUp, prevTickDown,
			manualHoldValue = "Manual Hold", researchFrameID = "researchframe", reportFrameID = "reportsframe",
			searchData, showTypeValue, exchangeID = "exchange", indexID = "index",
			autoReleaseMessage = "Auto-released at", warningUp, warningDown, homePath, session = "Open",
			holdContractStatus = "Not held", subcribedstatus = "Subscribed",feedPriceSecondRow,feedPriceThirdRow,
			statusSecondRow,statusThirdRow,sessionSecondRow,sessionThirdRow;

	public int initialNoTicksGripCount = 0, finalNoTicksGripCount = 0, initialManualHoldCount = 0,
			finalManualHoldCount = 0, finalWarningCount = 0, initialWarningCount = 0;

	public static List<ExtendedWebElement> tableRowSize;
	BufferedReader csvReader;

	public ContractsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);

	}

	@FindBy(xpath = "//a[contains(@href,'dashboard')]")
	private ExtendedWebElement dashboardTab;

	@FindBy(xpath = "//span[text()='Refresh From T3']")
	private ExtendedWebElement refreshT3;

	@FindBy(xpath = "//span[contains(@data-bind,'cManualHold')]")
	public ExtendedWebElement dashboardManualHold;

	@FindBy(xpath = "//span[contains(@data-bind,'cWarning')]")
	public ExtendedWebElement dashboardWarning;

	@FindBy(xpath = "//span[contains(@data-bind,'cSubscribed')]")
	public ExtendedWebElement subscribedLink;
	
	@FindBy(xpath = "//span[contains(@data-bind,'cNotFound')]")
	public ExtendedWebElement notFoundLink;

	@FindBy(xpath = "//span[contains(@title,'Open a new Contract Window')]")
	private ExtendedWebElement contract;

	@FindBy(xpath = "//span[contains(@title,'Open a new Exchange Window')]")
	private ExtendedWebElement exchange;

	@FindBy(xpath = "//table[contains(@id,'exchangeTable')]//tbody//tr[1]//td[1]")
	private ExtendedWebElement exchangeElement;

	@FindBy(xpath = "//table[contains(@id,'exchangeTable')]//tbody//tr[1]//td[4]")
	private ExtendedWebElement exchangeSession;
	
	@FindBy(xpath = "//select [@id='contractViewsList']")
	private ExtendedWebElement viewDropDown;

	@FindBy(xpath = "//table[@id='contractTable5']//tbody//tr")
	private ExtendedWebElement contractTable;

	@FindBy(xpath = "//div[@id='contractTable1_filter']//label//input[contains(@title,'search text')]")
	private ExtendedWebElement contractSearchTextBox;

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
	private ExtendedWebElement contractHold;

	@FindBy(xpath = "//span[text()='Release']")
	private ExtendedWebElement contractRelease;

	@FindBy(id = "holdContractValueId")
	private ExtendedWebElement holdContractCurrentValue;

	@FindBy(id = "holdContractTypeManualId")
	private ExtendedWebElement manualReleaseRadioButton;

	@FindBy(id = "holdContractTypeAutoId")
	private ExtendedWebElement autoBasedRadioButton;

	@FindBy(xpath = "//span[text()='Ok']")
	public ExtendedWebElement contractHoldOk;

	@FindBy(xpath = "//div[@aria-describedby='releaseContractDialog']/div[11]/div/button/span[text()='Ok']")
	public ExtendedWebElement releaseOk;

	@FindBy(xpath = "//table[contains(@id,'contractTable')]//tbody")
	public ExtendedWebElement contractList;

	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public ExtendedWebElement alertSearch;
	
	@FindBy(xpath = "//table[@id='alertTable']//tr[1]//td[1]")
	private ExtendedWebElement alertfirst;
	
	@FindBy(xpath = "//table[@id='alertTable']//tr[1]//td[4]")
	public ExtendedWebElement subject;

	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public WebElement alertSearchScroll;

	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr")
	public ExtendedWebElement alertTableFirstRow;

	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr[2]")
	public ExtendedWebElement alertTableSecondRow;

	@FindBy(xpath = "//span[@class='statusBarSpan']")
	public ExtendedWebElement totalCount;

	@FindBy(xpath = "//input[@title=\"Download Stock Data\"] ")
	public ExtendedWebElement downloadContractData;

	@FindBy(xpath = "//div[@id='contractTable1_wrapper']//div[3]//div//div//table//thead//tr//th[8]")
	public ExtendedWebElement contractColumn;

	@FindBy(xpath = "//table[@class='display dataTable']//thead//tr")
	public ExtendedWebElement contractColumns;

	@FindBy(xpath = "//span[contains(@data-bind,'noTicksIndexSPF')]")
	public ExtendedWebElement dashboardNoTicksGrip;

	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public ExtendedWebElement CurrentTime;

	@FindBy(xpath = "//div//table[@id='contractTable1']//tbody//tr")
	private List<WebElement> tableRowData;

	@FindBy(xpath = "(//span[text()='Cancel'])[position()=5]")
	public ExtendedWebElement contractHoldCancel;

	@FindBy(xpath = "(//span[text()='Cancel'])[2]")
	public ExtendedWebElement copyNameCancel;

	@FindBy(xpath = "(//span[text()='Cancel'])[3]")
	public ExtendedWebElement copyIDCancel;

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[3]")
	private ExtendedWebElement contractData;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[1]//td[5]")
	public ExtendedWebElement contractRICValue;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[2]//td[6]")
	public ExtendedWebElement contractRealRICValue;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[3]//td[8]")
	public ExtendedWebElement contractName;

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

	@FindBy(xpath = "//h3[contains(@class,'headerfont')]")
	private ExtendedWebElement indexFamily;

	@FindBy(xpath = "//a[contains(@href,'resea')]")
	private ExtendedWebElement research;
	
	@FindBy(xpath="//textarea[@id='copyNameTextareaId']")
	public static ExtendedWebElement copyTextarea;
	
	@FindBy(xpath = "//span[text()='Reset Baseline']")
	public ExtendedWebElement resetBaseline;
	
	@FindBy(id = "resetBaselineContractDialogTableId")
	private ExtendedWebElement resetBaselineContractDialogTableId;
	
	@FindBy(id = "resetBaselineContractMultipleDialogTableId")
	private ExtendedWebElement resetBaselineContractMultipleDialogTableId;
		
	@FindBy(xpath = "//span[text()='Ok']")  
	private ExtendedWebElement resetBaselineOk;
	
	@FindBy(xpath = "//div[@title='maximize window']")
	public ExtendedWebElement maximizeWindow;

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

	public void clickContract() {
		try {
			contract.isElementPresent();
			contract.click();
			waitForTablefirstRow(contractTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on contract tab-" + e.getMessage());
		}
	}

	public void clickExchange() {
		try {
			exchange.isElementPresent();
			exchange.click();
			waitForTablefirstRow(exchangeTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on contract tab-" + e.getMessage());
		}
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

	public void getValue(String TradingSession, String HoldingStatus, String DataFeed, String Operation,
			int tableRowSize, int initialCount) {
		try {
			String release = "Release";
			String hold = "Hold";
			for (int i = initialCount; i <= tableRowSize; i++) {

			ExtendedWebElement tradingSessionElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[2]"));
				sessionValue = tradingSessionElement.getText();
				ExtendedWebElement holdStatusElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[3]"));
				status = holdStatusElement.getText();
				ExtendedWebElement dataFeedElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[4]"));
				feedStatus = dataFeedElement.getText();
				ExtendedWebElement contractNameElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[8]"));
				name = contractNameElement.getText();
				ExtendedWebElement feedPriceElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[9]"));
				feedPriceValue = feedPriceElement.getText();
				ExtendedWebElement feedTimeElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[10]"));
				feedTimeValue = feedTimeElement.getText();
				ExtendedWebElement exchangeName = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[19]"));
				exchangeNameValue = exchangeName.getText();

				if (sessionValue.equalsIgnoreCase(TradingSession) && status.equalsIgnoreCase(HoldingStatus)
						&& Double.parseDouble(feedPriceValue) > 0 && feedStatus.equalsIgnoreCase(DataFeed)) {

					tradingSessionElement.isElementPresent();
					tradingSessionElement.click();
					tradingSessionElement.rightClick();

					if (Operation.equalsIgnoreCase(hold)) {
						contractHold.isElementPresent();
					     contractHold.click();
					}
					if (Operation.equalsIgnoreCase(release)) {
						releaseContract();
					}
					break;
				} else {
					if (status.equalsIgnoreCase("Manual Hold") && Operation.equalsIgnoreCase(release)) {
						tradingSessionElement.isElementPresent();
						tradingSessionElement.click();
						tradingSessionElement.rightClick();
						releaseContract();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to select conracts for performing Hold/Release Operation " + e.getMessage());
		}
	}

	public void holdContract() {
		try {
			getCurrentValue();
			if (Double.parseDouble(feedPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
					&& status.equalsIgnoreCase(status) && feedStatus.equalsIgnoreCase(subcribedstatus))
			{
				contractHold.isElementPresent();
			     contractHold.click();
			}
			else {
				Assert.fail("Warning Message: Contracts are not satisfying the precondition");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("unable to click Hold Button" + e.getMessage());

		}
	}

	public void releaseContract() {
		try {
			contractRelease.isElementPresent();
			contractRelease.click();
			clickSaveContractOk();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("unable to click release Button" + e.getMessage());

		}
	}

	public void clickSaveContractOk() {
		try {
			contractHoldOk.isElementPresent();
			contractHoldOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on save contract ok-" + e.getMessage());
		}
	}

	public void selectContract(String TradingSession, String HoldingStatus, String DataFeed, String Operation) {
		try {
			tableRowSize = contractList.findExtendedWebElements(By.tagName("tr"));
			getValue(TradingSession, HoldingStatus, DataFeed, Operation, tableRowSize.size(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform hold operation on selected contracts-" + e.getMessage());
		}
	}

	public void verifyCurrentPriceValue(String holdType) {
		try {
			holdContractCurrentValue.isElementPresent();
			Assert.assertTrue(manualReleaseRadioButton.getAttribute("checked").equalsIgnoreCase("true"));
			if (feedPriceValue.equalsIgnoreCase(holdContractCurrentValue.getAttribute("value"))) {
				Assert.assertEquals(feedPriceValue, holdContractCurrentValue.getAttribute("value"));
			}
			if (holdType.equalsIgnoreCase("Auto Hold")) {
				autoBasedRadioButton.click();
			} else {
			}
			contractHoldOk.isElementPresent();
			contractHoldOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify current price value-" + e.getMessage());
		}

	}

	public void getUniqueID() {
		try {
			alertSearch.type(name);
			alertSearch.pause(22);
			alertUniqueID = alertTableFirstRow.getAttribute("id");
			alertUniqueID2 = alertTableSecondRow.getAttribute("id");
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
				String description = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[6]")).getText();

				if (tableUTCTime >= loginUTCTime && alertType.equalsIgnoreCase(alertTypeInput)
						&& subject.contains(subjectInput) && description.contains(descriptionInput)) {
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

	public void getContractID() {
		try {
			viewDropDown.select("Identifier View");
			for (int i = 1; i < 10; i++) {

				ExtendedWebElement contractID = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[1]"));
				contractIDValue = contractID.getText();
				ExtendedWebElement contractNameElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[2]"));
				contractNameValue = contractNameElement.getText();
				if (contractNameValue.equalsIgnoreCase(name)) {
					break;
				}
			}
			viewDropDown.select("Quick View");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch the contractId-" + e.getMessage());
		}
	}

	public void getCurrentStatus() {
		try {
			contractSearchTextBox.type(contractIDValue);
			ExtendedWebElement HoldStatusElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr//td[3]"));
			currentHoldStatus = HoldStatusElement.getText();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch current hold status -" + e.getMessage());

		}
	}

	public void verifyHoldAlert(String holdType) {
		try {
			String releaseHoldMessage = "Manually released";
			contractHoldOk.pause(40);
			if (holdType.equalsIgnoreCase("manualHold")) {
				getContractID();
				getCurrentStatus();
				Assert.assertTrue(currentHoldStatus.equalsIgnoreCase("Manual Hold"));
				getUniqueID();
				VerifyAlertsMessages(name, "Manual Hold", currenTime, manualHoldMessage);
				ContractSQLStatements.getAlertStatus(alertUniqueID);
				Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(manualHoldMessage));
				ContractSQLStatements.getHoldStatus();
				Assert.assertEquals(ContractSQLStatements.holdStatus, "Manual Hold");
			} else {
				getCurrentStatus();
				Assert.assertTrue(currentHoldStatus.equalsIgnoreCase("Not Held"));
				getUniqueID();
				VerifyAlertsMessages(name, "Manual Release", currenTime, releaseHoldMessage);
				ContractSQLStatements.getAlertStatus(alertUniqueID);
				Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(releaseHoldMessage));
				ContractSQLStatements.getHoldStatus();
				Assert.assertTrue(ContractSQLStatements.holdStatus.equalsIgnoreCase("Not Held"));
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
			String alertMessage = "contract was not held";
			contractHoldOk.pause(30);
			getUniqueID();
			VerifyAlertsMessages(name, "Manual Release", currenTime, alertMessage);
			ContractSQLStatements.getAlertStatus(alertUniqueID);
			Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(alertMessage));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the alert message-" + e.getMessage());
		}
	}

	public void contractsFromExchange(String ExchangeName) {
		try {
			clickExchange();
			exchangeSearchTextBox.type(ExchangeName);
			if(exchangeSession.getText().equalsIgnoreCase(session))
			{
			exchangeElement.click();
			scrollToDown();
			rightclickStaleException();
			editThresholds.isElementPresent();
			editThresholds.click();
			}
			else
			{
			Assert.fail("Warning Message : Exchange is not  in Open State");	
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Edit Threshold option for Exchange-" + e.getMessage());
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
					exchangeElement.pause(10);
				}
			} while (Counter == 0);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			Assert.fail("Failed due to stale element for right click action-" + e.getMessage());
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

	public void verifyAutoHoldAlertMessages() {
		try {
			String autoHoldMessage = "Contract manually held";
			contractHoldOk.pause(45);
			getContractID();
			getUniqueID();
			VerifyAlertsMessages(name, "Manual Hold", currenTime, autoHoldMessage);
			VerifyAlertsMessages(name, "Auto-Release", currenTime, autoReleaseMessage);
			ContractSQLStatements.getAlertStatus(alertUniqueID);
			Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(autoReleaseMessage));
			ContractSQLStatements.getAlertStatus(alertUniqueID2);
			Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(autoHoldMessage));
			ContractSQLStatements.getHoldStatus();
			Assert.assertEquals(ContractSQLStatements.holdStatus, "Not Held");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the auto based/release alert-" + e.getMessage());
		}
	}

	public void getPercentValues() {
		try {
			warningUp = warningThresholdUp.getAttribute("value");
			warningDown = warningThresholdDown.getAttribute("value");
			baseLineUp = baseLineErrorThresholdUp.getAttribute("value");
			baseLineDown = baseLineErrorThresholdDown.getAttribute("value");
			prevTickUp = prevTickErrorThresholdUp.getAttribute("value");
			prevTickDown = prevTickErrorThresholdDown.getAttribute("value");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get up down percent values-" + e.getMessage());
		}
	}

	public void modifypercentValues(String dataUpdate, String thresholdValues) {
		try {
			String warningvalue = "0";

			if (dataUpdate.equalsIgnoreCase("update")) {
				warningThresholdUp.isElementPresent();
				warningThresholdUp.pause(10);
				warningThresholdUp.type(warningvalue);
				warningThresholdDown.type(warningvalue);
				baseLineErrorThresholdUp.type(thresholdValues);
				baseLineErrorThresholdDown.type(thresholdValues);
				prevTickErrorThresholdUp.type(thresholdValues);
				prevTickErrorThresholdDown.type(thresholdValues);
			} else {
				warningThresholdUp.isElementPresent();
				warningThresholdUp.pause(10);
				warningThresholdUp.type(warningUp);
				warningThresholdDown.type(warningDown);
				baseLineErrorThresholdUp.type(baseLineUp);
				baseLineErrorThresholdDown.type(baseLineDown);
				prevTickErrorThresholdUp.type(prevTickUp);
				prevTickErrorThresholdDown.type(prevTickDown);
			}
			thresholdOk.isElementPresent();
			thresholdOk.click();
			getDriver().navigate().refresh();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to modify threshold values-" + e.getMessage());
		}
	}

	public void baseLineHoldStatus(String holdtype) {
		String autohold = "Auto Hold";
		String warning = "Warning";

		try {
			if (holdtype.equalsIgnoreCase(autohold)) {
				viewDropDown.select("On Hold");
				contractHoldOk.pause(40);

				for (int i = 1; i <= 10; i++) {

					ExtendedWebElement tradingSessionElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[2]"));
					sessionValue = tradingSessionElement.getText();
					ExtendedWebElement holdStatusElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[3]"));
					status = holdStatusElement.getText();
					ExtendedWebElement contractNameElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[8]"));
					name = contractNameElement.getText();
					if (status.equalsIgnoreCase(holdtype)) {
						break;
					}
				}
			} else if (holdtype.equalsIgnoreCase(warning)) {
				viewDropDown.select("On Warning");
				contractHoldOk.pause(40);
				for (int i = 1; i <= 10; i++) {
					ExtendedWebElement tradingSessionElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[2]"));
					sessionValue = tradingSessionElement.getText();
					ExtendedWebElement holdStatusElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[3]"));
					status = holdStatusElement.getText();
					ExtendedWebElement contractNameElement = findExtendedWebElement(
							By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[" + i + "]//td[8]"));
					name = contractNameElement.getText();
					if (status.equalsIgnoreCase(holdtype)) {
						break;
					}
				}
			} else {
				contractHoldOk.pause(10);
				contractSearchTextBox.type(name);
				ExtendedWebElement HoldStatusElement = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr//td[3]"));
				currentHoldStatus = HoldStatusElement.getText();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify hold status -" + e.getMessage());
		}
	}

	public void verifyBaselineMessages(String dataUpdate) {
		try {
			String update = "update";

			contractHoldOk.pause(25);
			getUniqueID();
			if (dataUpdate.equalsIgnoreCase(update)) {
				VerifyAlertsMessages(name, "Threshold Breach", currenTime, "will be held");
				getDriver().navigate().refresh();
			} else {
				VerifyAlertsMessages(name, "Auto-Release", currenTime, "Auto-released at");
				contractHoldOk.pause(40);
				Assert.assertTrue(currentHoldStatus.equalsIgnoreCase("Not Held"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify baseline alert messages-" + e.getMessage());
		}
	}

	public void contractManualHold(String contractName, String holdtype) {
		try {
			contractSearchTextBox.type(contractName);
			ExtendedWebElement holdStatusElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[1]//td[3]"));
			getCurrentValue();
			if(holdtype.equalsIgnoreCase(manualHoldValue))
			if (Double.parseDouble(feedPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
					&& status.equalsIgnoreCase(holdContractStatus) && feedStatus.equalsIgnoreCase(subcribedstatus)) {
				holdStatusElement.click();
				holdStatusElement.rightClick();
				contractHold.isElementPresent();
			     contractHold.click();
				contractHoldOk.isElementPresent();
				contractHoldOk.click();
			} else {
				holdStatusElement.click();
				holdStatusElement.rightClick();
				releaseContract();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify hold status of contracts -" + e.getMessage());
		}
	}

	public void contractsNoHold(String ExchangeName) {
		try {
			clickExchange();
			exchangeSearchTextBox.type(ExchangeName);
			exchangeElement.click();
			scrollToDown();
			rightclickStaleException();
			showContracts.click();
			thresholdOk.pause(20);
			getDriver().navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Contracts with hold status is present -" + e.getMessage());
		}
	}

	public void negativeNumber() {
		try {
			holdContractCurrentValue.type("-1234");
			String DefaultValue = holdContractCurrentValue.getAttribute("value");
			Assert.assertTrue(Integer.parseInt(DefaultValue) < 0, "The contract value is in negative");
			contractHoldCancel.click();
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
			waitForTablefirstRow(contractTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Subscribed link-" + e.getMessage());
		}
	}

	public void selectView(String viewType) {
		try {
			// viewDropDown.select(viewType);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]/tbody/tr[1]"));

			if (ele.isElementPresent()) {
			} else
				Assert.fail("No contracts were listed");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("No contracts were listed" + e.getMessage());
		}
	}

	public void holdReleaseMultipleContracts(String actionType) {
		try {
			Actions builder = new Actions(getDriver());
			if (actionType.equalsIgnoreCase("hold")) {
				holdingMultipleContracts();
			}
		  else 
		  {
			   waitForTablefirstRow(contractTableID);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				contractRelease.isElementPresent();
				scrollToDown();
				rightclickContractStaleException();
				contractHold.pause(25);
				getCurrentValue();
				getUniqueID();
				VerifyAlertsMessages(name, "Manual Release", currenTime, "Manually released");
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Not Held"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release multiple contracts-" + e.getMessage());
		}
	}
	
	public void holdingMultipleContracts() throws Exception 
	{
		try {
			getCurrentValue();
			Actions builder = new Actions(getDriver());
			getNextRowValue();
			if (Double.parseDouble(feedPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
			    && status.equalsIgnoreCase(holdContractStatus)  && Double.parseDouble(feedPriceSecondRow) > 0
			    && Double.parseDouble(feedPriceThirdRow) > 0 && statusSecondRow.equalsIgnoreCase(holdContractStatus)
			    && statusThirdRow.equalsIgnoreCase(holdContractStatus) && sessionSecondRow.equalsIgnoreCase(session) && sessionThirdRow.equalsIgnoreCase(session))
			{
			waitForTablefirstRow(contractTableID);
			builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
			builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
			builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
			builder.contextClick().build().perform();
			contractHold.isElementPresent();
			contractHold.click();
			contractHoldOk.isElementPresent();
			Assert.assertTrue(autoBasedRadioButton.isElementNotPresent(2), "Auto based hold option is visible");
			clickSaveContractOk();
			contractHold.pause(25);
			getCurrentValue();
			getUniqueID();
			VerifyAlertsMessages(name, manualHoldValue, currenTime, manualHoldMessage);
			getCurrentValue();
			Assert.assertTrue(status.equalsIgnoreCase(manualHoldValue));
			getDriver().navigate().refresh();
			}
			else {
				Assert.fail("Warning Message: Contracts are not satisfying the precondition");
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Failed to hold multiple contracts-" + e.getMessage());
			}
		}
	
	public void getNextRowValue()
	{
		try {
			ExtendedWebElement feedPriceElement2 = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[2]//td[9]"));
	 	feedPriceSecondRow = feedPriceElement2.getText();
			ExtendedWebElement feedPriceElement3 = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[3]//td[9]"));
		 feedPriceThirdRow = feedPriceElement3.getText();
			ExtendedWebElement holdStatusElement2 = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[2]//td[3]"));
				statusSecondRow  = holdStatusElement2.getText();
			ExtendedWebElement holdStatusElement3 = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[3]//td[3]"));
				statusThirdRow  = holdStatusElement3.getText();
			ExtendedWebElement tradingSessionElement2 = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[2]//td[2]"));
				sessionSecondRow=tradingSessionElement2.getText();
			ExtendedWebElement tradingSessionElement3 = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr[3]//td[2]"));
		    sessionThirdRow=tradingSessionElement3.getText();
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to fetch the next row values-" + e.getMessage());
		}
		
	}
	
	public void rightclickContractStaleException() {
		int Counter = 0;
		PageFactory.initElements(driver, this);
		try {
			do {
				if (contractRelease.getElement().isEnabled() && contractRelease.getElement().isDisplayed()) {
					contractRelease.pause(1);
					Counter = Counter + 1;
					contractRelease.click();
					contractRelease.pause(1);
					releaseOk.click();
				}
			} while (Counter == 0);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			Assert.fail("Failed due to stale element for right click action-" + e.getMessage());
		}

	}

	public void getCurrentValue() {
		try {
			ExtendedWebElement tradingSessionElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr//td[2]"));
			sessionValue = tradingSessionElement.getText();
			ExtendedWebElement holdStatusElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr//td[3]"));
			status = holdStatusElement.getText();
			ExtendedWebElement dataFeedElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr//td[4]"));
			feedStatus = dataFeedElement.getText();
			ExtendedWebElement contractNameElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr//td[8]"));
			name = contractNameElement.getText();
			ExtendedWebElement feedPriceElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + contractTableID + "')]//tbody//tr//td[9]"));
			feedPriceValue = feedPriceElement.getText();
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch the current value of contracts-" + e.getMessage());
		}
	}

	public void holdReleaseContractMultipleTime(String actionType) {
		try {
			int loopcount = 0;

			if (actionType.equalsIgnoreCase("holdMultiple")) {
				for (int i = 0; i < 3; i++) {
					getCurrentValue();
					performRightClickAction();
					holdContract();
					clickSaveContractOk();
					if (loopcount == 0)
						holdContractCurrentValue.pause(50);
					getCurrentValue();
					Assert.assertTrue(status.equalsIgnoreCase(manualHoldValue));
					loopcount++;
				}
			} else {
				getCurrentValue();
				performRightClickAction();
				contractRelease.isElementPresent();
				contractRelease.click();
				releaseOk.isElementPresent();
				releaseOk.click();
				thresholdOk.pause(50);
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Not Held"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release contract for multiple times-" + e.getMessage());
		}

	}

	public void performRightClickAction() {
		try {
			if (status.equalsIgnoreCase("Not Held") && feedStatus.equalsIgnoreCase("Subscribed")) {
				for (int i = 0; i < 3; i++) {
					if (contractData.isElementPresent()) {
						contractData.click();
						contractData.rightClick();
						break;
					}
				}
			} else if (status.equalsIgnoreCase(manualHoldValue) && feedStatus.equalsIgnoreCase(subcribedstatus)) {
				for (int i = 0; i < 3; i++) {
					if (contractData.isElementPresent()) {
						contractData.click();
						contractData.rightClick();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform right click action on selected contracts-" + e.getMessage());
		}
	}

	public void searchContract(String contractID) {
		try {
			searchData = contractID;
			contractSearchTextBox.type(searchData);
			waitForTablefirstRow(contractTableID);
			getCurrentValue();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search contract name-" + e.getMessage());
		}
	}

	public void copyName() {
		try {
			rightClickOperation();
			copyName.isElementPresent();
			copyName.click();
			copyNameClipboard.isElementPresent();
			Assert.assertTrue(name.equalsIgnoreCase(copyNameClipboard.getAttribute("value")));
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
			Assert.assertTrue(filterReportInput.getAttribute("value").contains(name));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate Report" + e.getMessage());
		}
	}

	public void rightClickOperation() {
		try {
			contractData.isElementPresent();
			contractData.click();
			contractData.rightClick();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate Report" + e.getMessage());
		}
	}

	public void selectAllRows() {
		try {
			contractData.click();
			contractData.rightClick();
			selectAllRows.isElementPresent();
			selectAllRows.click();
			selectAllRows.pause(10);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//div[contains(@id,'" + contractTableID + "')]//span[@class='statusBarSpan']"));
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
				ContractSQLStatements.getExchangeCount();
				Assert.assertEquals(totalCountText.replaceAll("[^0-9]", ""), ContractSQLStatements.exchangeCount);
			} else {
				rightClickOperation();
				showIndices.isElementPresent();
				showIndices.click();
				totalCountValue = findExtendedWebElement(
						By.xpath("//div[contains(@id,'" + indexID + "')]//span[@class='statusBarSpan']"));
				totalCountValue.isElementPresent();
				waitForTablefirstRow(indexID);
				totalCountText = totalCountValue.getText().substring(0, totalCountValue.getText().indexOf("("));
				ContractSQLStatements.getIndexCount();
				Assert.assertEquals(totalCountText.replaceAll("[^0-9]", ""), ContractSQLStatements.indicesCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform action on show details and verify value-" + e.getMessage());
		}
	}

	public void verifyHoldChangeStatus(String holdType) {
		try {
			contractHold.pause(40);
			getCurrentValue();
			alertSearch.type(name);
			alertSearch.pause(10);
			VerifyAlertsMessages(name, "Auto-Release", currenTime, autoReleaseMessage);
			Assert.assertTrue(status.equalsIgnoreCase(holdType));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to verify hold status-" + e.getMessage());
		}
	}

	public void performReleaseOperation(String holdType) {
		try {
			contractHold.pause(30);
			getCurrentValue();

			if (status.equalsIgnoreCase(holdType)) {
				rightClickOperation();
				releaseContract();
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
			thresholdOk.pause(25);
			if (status.equalsIgnoreCase(holdType)) {
				rightClickOperation();
				holdContract();
				clickSaveContractOk();
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform hold operation-" + e.getMessage());
		}
	}

	public void updateThreshold() {
		try {
			if (Double.parseDouble(feedPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
					&& status.equalsIgnoreCase(status) && feedStatus.equalsIgnoreCase(subcribedstatus))
			{
			ContractSQLStatements.updateBaselineValue();
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
			else
			{
				Assert.fail("Warning Message: Contracts are not satisfying the precondition");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update baseline threshold value-" + e.getMessage());

		}
	}

	public void verifySearchListedValues() {
		try {
			ArrayList<String> ricColumnValues = new ArrayList<>();
			ArrayList<String> realRicColumnValues = new ArrayList<>();
			ArrayList<String> nameColumnValues = new ArrayList<>();

			String previousRICValue = contractRICValue.getText();
			String previousRealRICValue = contractRealRICValue.getText();
			String previousName = contractName.getText();
			String tSearch = (previousRICValue + "," + previousRealRICValue + "," + previousName);
			contractSearchTextBox.type(tSearch);
			waitForTablefirstRow(contractTableID);

			for (int i = 0; i < ricColumn.size(); i++) {
				ricColumnValues.add(ricColumn.get(i).getText());
				realRicColumnValues.add(realRicColumn.get(i).getText());
				nameColumnValues.add(nameColumn.get(i).getText());
			}

			Assert.assertTrue(ricColumnValues.contains(previousRICValue));
			Assert.assertTrue(realRicColumnValues.contains(previousRealRICValue));
			Assert.assertTrue(nameColumnValues.contains(previousName));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the searched contract value with comma separated-" + e.getMessage());
		}
	}

	public void contractDownload() throws IOException, InterruptedException {
		HashSet<String> SetExcel = new HashSet<String>();
		HashSet<String> IndexData = new HashSet<String>();

		String Alert = totalCount.getText();
		String Column = contractColumn.getText();

		IndexData.add(Column);
		SetExcel.add(Column);
		String requiredString = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf("(") - 1);
		int Al_count = Integer.parseInt(requiredString);

		downloadContractData.click();
		downloadContractData.pause(10);

		homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);

		int count = countRecord(LatestFile);
		csvReader = new BufferedReader(new FileReader(LatestFile));

		Assert.assertEquals((count - 2), Al_count);

		for (int i = 1; i < 5; i++) {
			ExtendedWebElement A_Name = findExtendedWebElement(
					By.xpath("//table[@class='display dataTable']//tr[" + i + "]//td[6]"));
			String R_Name = A_Name.getText();
			IndexData.add(R_Name);
		}

		String row = "";
		String[] data = null;
		csvReader = new BufferedReader(new FileReader(LatestFile));

		for (int i = 0; i < 5; i++) {
			if ((row = csvReader.readLine()) != null) {
				data = row.split(",");
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

	public void verifyIndexFilter() {
		try {
			String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC04033", "TestData1");
			int index = 0;
			String View = "Quick View";
			indexVerifyFilter(Search, index, View);
			index = 1;
			View = "Identifier View";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC04033", "TestData2");
			indexVerifyFilter(Search, index, View);
			index = 3;
			View = "Technical Support";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC04033", "TestData4");
			indexVerifyFilter(Search, index, View);
			index = 4;
			View = "Thresholds View";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC04033", "TestData5");
			indexVerifyFilter(Search, index, View);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify filter headers combo-" + e.getMessage());
		}
	}

	public void indexVerifyFilter(String Search, int index, String View) {
		try {
			viewDropDown.select(View);
			waitForTablefirstRow(contractTableID);
			HashSet<String> column = new HashSet<String>();
			HashSet<String> quickView = new HashSet<String>();
			List<ExtendedWebElement> tableRows = contractColumns.findExtendedWebElements(By.tagName("th"));

			for (int i = 1; i <= tableRows.size(); i++) {
				ExtendedWebElement contractsColumn = findExtendedWebElement(
						By.xpath("//table[@id='contractTable1']//tr[1]//td[" + i + "]"));
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
				warningThresholdUp.isElementPresent();
				warningThresholdUp.pause(10);
				warningThresholdUp.type(warningValue);
				warningThresholdDown.type(warningValue);
			} else {
				warningThresholdUp.isElementPresent();
				warningThresholdUp.pause(10);
				warningThresholdUp.type(warningUp);
				warningThresholdDown.type(warningDown);
			}
			thresholdOk.isElementPresent();
			thresholdOk.click();
			warningThresholdUp.pause(10);
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

	public void verifyWarningAlertMessages(String WarningUpdate, String holdtype) {
		try {
			String update = "update";
			String warning = "warning";
			contractHoldOk.pause(25);
			getUniqueID();
			if (WarningUpdate.equalsIgnoreCase(update)) {
				VerifyAlertsMessages(name, "Warning Threshold", currenTime, "Current price");
			} else {
				VerifyAlertsMessages(name, "Warning Threshold", currenTime, "Commodity is set off warning");
				contractHoldOk.pause(15);
			}
			clickDashboard();
			dashboardWarning.isElementPresent();
			dashboardWarning.pause(30);
			finalWarningCount = Integer.parseInt(dashboardWarning.getText());
			if (holdtype.equalsIgnoreCase(warning))
			{
				Assert.assertTrue(initialWarningCount < finalWarningCount);
				getDriver().navigate().refresh();
			}
			else
			{
				Assert.assertTrue(initialWarningCount < finalWarningCount);
				getDriver().navigate().refresh();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify warning alert messages-" + e.getMessage());
		}
	}
	
	
	public void contractMenu(String alertSch,int column,String Search) throws IOException
	{ 	
		Dashboardpage dash= new Dashboardpage(getDriver());
		dash.scrollToDown();
		clickResearch();
		alertSearch.type(alertSch);
		alertSearch.pause(5);
		String sub=subject.getText();
		alertfirst.rightClick();
		SupportPage supportPage = new SupportPage(getDriver());
		supportPage.verify_Context(Search,column);
		if( (!Search.contains("Exchange"))&&(!Search.contains("Forex")))
		{
		verifyReport(sub);
		}
		
	}

	public void verifyReport( String Subject)
	{
		
		ExtendedWebElement Refresh_Frm = findExtendedWebElement(By.xpath("/html/body/ul/li[1]/span"));
		Refresh_Frm.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("reportsframe");
		ExtendedWebElement Asset_value = findExtendedWebElement(By.xpath("//div[@class='topleftcell']//tbody//tr[2]//td[3]//input"));
		String Aset_vl = Asset_value.getAttribute("value");
		if (Aset_vl.contains(Subject)) 
		{
					
		LOGGER.info("Report Tab opened With Selected Index");
		}
		else
		{
			Assert.fail("Report Tab not  opened With Selected Index");
		}

		
	}

	public void Copy(String alertsearch)
	{
		if((!alertsearch.contains("exchange"))&&(!alertsearch.contains("Forex")))
		{
		clickResearch();
		alertSearch.type(alertsearch);
		alertSearch.pause(5);
		alertfirst.rightClick();
		}
		ExtendedWebElement Refresh_Frm = findExtendedWebElement(By.xpath("/html/body/ul/li[4]/span"));
		Refresh_Frm.click();
		String text=copyTextarea.getText();
		ExtendedWebElement Copy_msg = findExtendedWebElement(By.xpath("//span[contains(text(),'Copy to Clipboard')]"));
		Copy_msg.click();
		Copy_msg.pause(5);
		driver.findElement(By.xpath("//input[@aria-controls='alertTable']")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		driver.findElement(By.xpath("//input[@aria-controls='alertTable']")).sendKeys(Keys.chord(Keys.CONTROL,"V"));
		Copy_msg.pause(5);
		String alert_msg = Researchpage.Alert_Search.getAttribute("value");
		if(text.equalsIgnoreCase(alert_msg))
		{
		LOGGER.info("Alert Message Copied to ClipBoard");
		}
		else
		{
			Assert.fail("Alert Message not Copied to ClipBoard");
		}
	}
		
		public void showContract(String search,int i,String ID)
		{
		alertSearch.type(search);
		alertSearch.pause(10);
		alertfirst.rightClick();
		String Index_name="";
		String sub=subject.getText();
		ExtendedWebElement show = findExtendedWebElement(By.xpath("/html/body/ul/li["+i+"]/span"));
		show.click();
		if(search.contains("exchange"))
		{
		ExtendedWebElement Ind_List1 = findExtendedWebElement(By.xpath("//table[@id='"+ID+"']//tr[1]//td[1]"));
		Index_name= Ind_List1.getText();
		
		}
		else if(search.contains("fixed")){
			
		ExtendedWebElement Ind_List1 = findExtendedWebElement(By.xpath("//table[@id='"+ID+"']//tr[1]//td[5]"));
		Index_name= Ind_List1.getText();
		}
			
		else if(search.contains("Forex")){
		ExtendedWebElement Ind_List1 = findExtendedWebElement(By.xpath("//table[@id='"+ID+"']//tr[1]//td[4]"));
		Index_name= Ind_List1.getText();
		}	
		else
		{
		ExtendedWebElement Ind_List1 = findExtendedWebElement(By.xpath("//table[@id='"+ID+"']//tr[1]//td[6]"));
		Index_name= Ind_List1.getText();
		}
			
		if (sub.contains(Index_name)) 
		{
		LOGGER.info("List Window Opened With Selected Index");
		
		}
			
		else
		{
			Assert.fail(" List Window not Opened With Selected Index");
		}
			
		}

		
		public void verifyResetBaseline() {
			try {
					
			    LinkedList<String> MenuList = new LinkedList<String>();
		    	       
		        for(int i=1;i<15;i++){
		        ExtendedWebElement DataFields=findExtendedWebElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//li["+i+"]"));
		        String Data=DataFields.getText();
		        MenuList.add(Data);
		        }
		        System.out.println(MenuList);
		            if(MenuList.contains("Reset Baseline")) {
		        	LOGGER.info("Reset Baseline is available in the context menu list");
		        }
		    } catch (Exception e) {
		    	e.printStackTrace();
				Assert.fail("Unable to verify Rest Baseline data-" + e.getMessage());
			}
		}
		
		public void performRightClickNoTestData() {
			try {
				scrollToDown();
				rightclickBaselineStaleException();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Warning Message - Unable to perfrom right click action on selected contracts-" + e.getMessage());
			}
		}
		
		public void rightclickBaselineStaleException() {
			int Counter = 0;
			PageFactory.initElements(driver, this);
			try {
				do {
					if (contractData.getElement().isEnabled() && contractData.getElement().isDisplayed()) {
						contractData.pause(1);
						Counter = Counter + 1;
						contractData.click();
						contractData.pause(1);
						contractData.rightClick();
					}
				} while (Counter == 0);
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
				Assert.fail("Failed due to stale element for right click action-" + e.getMessage());
			}
		}
			
			public void clickResetBaseLine(){
				try {
					if (sessionValue.equalsIgnoreCase(session) && status.equalsIgnoreCase(holdContractStatus)
							&& Double.parseDouble(feedPriceValue) > 0 && feedStatus.equalsIgnoreCase(subcribedstatus)) 
					{
					resetBaseline.isElementPresent();
					resetBaseline.click();
					}
					else {
						Assert.fail("Warning Message:contracts are satisfying the precondition");
					}
				} catch (Exception e) {
					e.printStackTrace();
					Assert.fail("Unable to click Reset Baseline-" + e.getMessage());
				}
			}
			
			public void verifyResetBaseLinePopup(){
				try {
					String Message="Would you like to reset the baseline for this contract?";
					String Resetbaselinepopup=resetBaselineContractDialogTableId.getText();
					getCurrentValue();
					Assert.assertTrue(Resetbaselinepopup.contains(feedPriceValue) && (Resetbaselinepopup.contains(Message)));
					resetBaselineOk.click();
				} catch (Exception e) {
					e.printStackTrace();
					Assert.fail("Unable to verify Reset Baseline popup-" + e.getMessage());
				}
			}

			public void resetBaselineMultipleContracts() {  
				try {
			    getCurrentValue();
			    Actions builder = new Actions(getDriver());
				getNextRowValue();
				if (Double.parseDouble(feedPriceValue) > 0 && sessionValue.equalsIgnoreCase(session)
				    && status.equalsIgnoreCase(holdContractStatus)  && Double.parseDouble(feedPriceSecondRow) > 0
				    && Double.parseDouble(feedPriceThirdRow) > 0 && statusSecondRow.equalsIgnoreCase(holdContractStatus)
				    && statusThirdRow.equalsIgnoreCase(holdContractStatus) && sessionSecondRow.equalsIgnoreCase(session) && sessionThirdRow.equalsIgnoreCase(session))
				{
			    waitForTablefirstRow(contractTableID);
				//maximizeWindow.click();
				//maximizeWindow.pause(4);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				resetBaseline.isElementPresent();
				resetBaseline.click();
				
				String Message="Would you like to reset the baseline for these contracts?";
				String Resetbaselinepopup=resetBaselineContractMultipleDialogTableId.getText();
				System.out.println("Resetbaselinepopup="+Resetbaselinepopup);
				
				Assert.assertTrue(Resetbaselinepopup.contains(Message));

				resetBaselineOk.click();
				}	
				else {
					
					Assert.fail("Warning Message:Contracts are not satisfying the precondition");
				}
				}
				catch (Exception e) {
					e.printStackTrace();
					Assert.fail("Unable to verify Reset Baseline popup for multiple contracts-" + e.getMessage());
				}
			}
			
			public void clickOnNotFoundLink() {
				try {
					switchToFrame(dashboardFrameId);
					notFoundLink.isElementPresent();
					notFoundLink.click();
					switchOutOfFrame();
					switchToFrame(researchFrameID);
					waitForTablefirstRow(contractTableID);
				} catch (Exception e) {
					e.printStackTrace();
					Assert.fail("Unable to click on notfound link-" + e.getMessage());
				}
			}
}

			
