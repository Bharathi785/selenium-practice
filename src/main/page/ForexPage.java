package com.grip.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.grip.DBconnection.ContractSQLStatements;
import com.grip.DBconnection.ForexSQLStatements;
import com.grip.utils.ExcelUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class ForexPage extends AbstractPage {

	public static ExcelUtils excel = new ExcelUtils();

	public static String currenTime, name, status, forexPriceValue, exchangeTableID = "exchangeTable1",
			exchangeNameValue, dashboardFrameId = "dashboardframe", manualHoldMessage = "Forex manually held",
			alertUniqueID, alertUniqueID2, forexIDValue, currentHoldStatus, forexTableID = "forexTable1", baseLineUp,
			baseLineDown, prevTickUp, prevTickDown, manualHoldValue = "Manual Hold", researchFrameID = "researchframe",
			reportFrameID = "reportsframe", searchData, showTypeValue, exchangeID = "exchange", indexID = "index",
			autoReleaseMessage = "Auto-released at", warningUp, warningDown, homePath, feedStatus,
			holdForexStatus = "Not held", subcribedstatus = "Subscribed", forexStatusSecondValue, forexStatusThirdValue;

	public int initialNoTicksGripCount = 0, finalNoTicksGripCount = 0, initialManualHoldCount = 0,
			finalManualHoldCount = 0;

	public ForexPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);

	}

	@FindBy(xpath = "//a[contains(@href,'dashboard')]")
	private ExtendedWebElement dashboardTab;

	@FindBy(xpath = "//span[contains(@data-bind,'fxManualHold')]")
	public ExtendedWebElement dashboardManualHold;

	@FindBy(xpath = "//span[contains(@data-bind,'fxSubscribed')]")
	public ExtendedWebElement subscribedLink;

	@FindBy(xpath = "//span[contains(@title,'Open a new Forex Window')]")
	private ExtendedWebElement forex;

	@FindBy(xpath = "//div[@id='forexTable1_filter']//label//input[contains(@title,'search text')]")
	private ExtendedWebElement forexSearchTextBox;

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
	private ExtendedWebElement forexHold;

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[2]")
	private ExtendedWebElement forexData;

	@FindBy(xpath = "//span[text()='Release']")
	private ExtendedWebElement forexRelease;

	@FindBy(xpath = "(//table[@class='display dataTable']//th[2])[1]")
	private ExtendedWebElement dataFeedElement;

	@FindBy(id = "holdForexValueId")
	private ExtendedWebElement holdForexCurrentValue;

	@FindBy(id = "holdForexTypeManualId")
	private ExtendedWebElement manualReleaseRadioButton;

	@FindBy(id = "holdForexTypeAutoId")
	private ExtendedWebElement autoBasedRadioButton;

	@FindBy(xpath = "//span[text()='Ok']")
	public ExtendedWebElement forexHoldOk;

	@FindBy(xpath = "//div[@aria-describedby='releaseForexDialog']/div[11]/div/button/span[text()='Ok']")
	public ExtendedWebElement releaseOk;

	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public ExtendedWebElement alertSearch;

	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr")
	public ExtendedWebElement alertTableFirstRow;

	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr[2]")
	public ExtendedWebElement alertTableSecondRow;

	@FindBy(xpath = "//span[@class='statusBarSpan']")
	public ExtendedWebElement totalCount;

	@FindBy(xpath = "//input[@title='Download Forex Rate Data'] ")
	public ExtendedWebElement downloadForexData;

	@FindBy(xpath = "//div[@id='forexTable1_wrapper']//div[3]//div//div//table//thead//tr//th[8]")
	public ExtendedWebElement forexColumn;
	
	@FindBy(xpath = "//span[contains(@data-bind,'noTicksIndexSPF')]")
	public ExtendedWebElement dashboardNoTicksGrip;

	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public ExtendedWebElement CurrentTime;

	@FindBy(xpath = "//div//table[@class='display dataTable']//tbody//tr")
	private List<WebElement> tableRowData;

	@FindBy(xpath = "(//span[text()='Cancel'])[position()=5]")
	public ExtendedWebElement forexHoldCancel;

	@FindBy(xpath = "(//span[text()='Cancel'])[2]")
	public ExtendedWebElement copyNameCancel;

	@FindBy(xpath = "(//span[text()='Cancel'])[3]")
	public ExtendedWebElement copyIDCancel;

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[4]")
	public ExtendedWebElement forexRICValue;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[2]//td[4]")
	public ExtendedWebElement forexCode;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[3]//td[4]")
	public ExtendedWebElement forexFeedPrice;

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

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[3]")
	public List<ExtendedWebElement> ricColumn;

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[4]")
	public List<ExtendedWebElement> codeColumn;
	
	@FindBy(xpath = "(//table[@class='display dataTable']//tr[1]//th[1])[1]")
	private ExtendedWebElement holdingStatusElement;

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[5]")
	public List<ExtendedWebElement> priceColumn;

	@FindBy(xpath = "//a[contains(@href,'resea')]")
	private ExtendedWebElement research;

	@FindBy(xpath = "//h3[contains(@class,'headerfont')]")
	private ExtendedWebElement indexFamily;

	public void switchToFrame(String frameID) {
		driver.switchTo().frame(frameID);
	}

	public void switchOutOfFrame() {
		driver.switchTo().defaultContent();
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

	public void searchForexData(String searchDataValue) {
		try {
			searchData = searchDataValue;
			forexSearchTextBox.type(searchDataValue);
			waitForTablefirstRow(forexTableID);
			getCurrentValue();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search forex data-" + e.getMessage());
		}
	}

	public void holdReleaseOperation(String Operation) {
		try {
			String holdValue = "Hold";
			String release = "Release";
			rightClickOperation();
			if (Operation.equalsIgnoreCase(holdValue)) {
				holdForex();

			} else {
			     forexHoldOk.pause(10);
				releaseForex();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform Hold/release Operation-" + e.getMessage());
		}
	}

	public void getCurrentValue() {
		try {
			ExtendedWebElement holdingStatus = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + forexTableID + "')]//tr//td[1]"));
			status = holdingStatus.getText();
			ExtendedWebElement feed = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + forexTableID + "')]//tr//td[2]"));
			feedStatus = feed.getText();
			ExtendedWebElement forexName = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + forexTableID + "')]//tr//td[4]"));
			name = forexName.getText();
			ExtendedWebElement forexPrice = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + forexTableID + "')]//tr//td[5]"));
			forexPriceValue = forexPrice.getText();
			ExtendedWebElement forexID = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + forexTableID + "')]//tr//td[13]"));
			forexIDValue = forexID.getText();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch the current value of columns-" + e.getMessage());
		}

	}

	public void rightClickOperation() {
		try {
			forexData.isElementPresent();
			forexData.click();
			forexData.rightClick();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform right click operation-" + e.getMessage());
		}
	}

	public void verifyCurrentPriceValue(String holdType) {
		try {
			holdForexCurrentValue.isElementPresent();
			Assert.assertTrue(manualReleaseRadioButton.getAttribute("checked").equalsIgnoreCase("true"));
			if (forexPriceValue.equalsIgnoreCase(holdForexCurrentValue.getAttribute("value"))) {
				Assert.assertEquals(forexPriceValue, holdForexCurrentValue.getAttribute("value"));
			}
			if (holdType.equalsIgnoreCase("Auto Hold")) {
				autoBasedRadioButton.click();
			} else {
			}
			forexHoldOk.isElementPresent();
			forexHoldOk.click();
			forexHoldOk.pause(10);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify current price value-" + e.getMessage());
		}

	}

	public void clickDashboard() {
		try {
			switchOutOfFrame();
			dashboardTab.isElementPresent();
			dashboardTab.click();
			switchToFrame(dashboardFrameId);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Index tab-" + e.getMessage());
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

	public void clickForex() {
		try {
			forex.isElementPresent();
			forex.click();
			waitForTablefirstRow(forexTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on forex tab-" + e.getMessage());
		}
	}

	public void holdForex() {
		try {
			getCurrentValue();
			if (status.equalsIgnoreCase(holdForexStatus) && feedStatus.equalsIgnoreCase(subcribedstatus)
					&& Double.parseDouble(forexPriceValue) > 0) {
				forexHold.isElementPresent();
				forexHold.click();
			} else {
				Assert.fail("Warning Message:Forex is not satisying the precondition");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("unable to click Hold Button" + e.getMessage());
		}
	}

	public void releaseForex() {
		try {
			forexRelease.isElementPresent();
			forexRelease.click();
			clickSaveForexOk();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("unable to click release Button" + e.getMessage());
		}
	}

	public void clickSaveForexOk() {
		try {
			forexHoldOk.isElementPresent();
			forexHoldOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on save contract ok-" + e.getMessage());
		}
	}

	public void getUniqueID() {
		try {
			alertSearch.type(name);
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

	public void verifyHoldAlert(String holdType) {
		try {
			String releaseHoldMessage = "manually released";
			forexHoldOk.pause(40);
			if (holdType.equalsIgnoreCase("manualHold")) {
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Manual Hold"));
				getUniqueID();
				VerifyAlertsMessages(name, "Manual Hold", currenTime, manualHoldMessage);
				ContractSQLStatements.getAlertStatus(alertUniqueID);
				Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(manualHoldMessage));
				ForexSQLStatements.getHoldStatus();
				Assert.assertTrue(ForexSQLStatements.holdStatus.equalsIgnoreCase( "Manual Hold"));
			} 
			else {
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Not Held"));
				getUniqueID();
				VerifyAlertsMessages(name, "Manual Release", currenTime, releaseHoldMessage);
				ContractSQLStatements.getAlertStatus(alertUniqueID);
				Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(releaseHoldMessage));
				ForexSQLStatements.getHoldStatus();
				Assert.assertTrue(ForexSQLStatements.holdStatus.equalsIgnoreCase("Not Held"));
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
			String alertMessage = "Forex was not held";
			forexHoldOk.pause(30);
			getUniqueID();
			VerifyAlertsMessages(name, "Manual Release", currenTime, alertMessage);
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
			String autoHoldMessage = "Forex manually held";
			forexHoldOk.pause(50);
			getUniqueID();
			VerifyAlertsMessages(name, "Manual Hold", currenTime, autoHoldMessage);
			VerifyAlertsMessages(name, "Auto-Release", currenTime, autoReleaseMessage);
			ContractSQLStatements.getAlertStatus(alertUniqueID);
			Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(autoReleaseMessage));
			ContractSQLStatements.getAlertStatus(alertUniqueID2);
			Assert.assertTrue(ContractSQLStatements.alertMessageValue.contains(autoHoldMessage));
			ForexSQLStatements.getHoldStatus();
			Assert.assertEquals(ForexSQLStatements.holdStatus, "Not Held");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the auto based/release alert-" + e.getMessage());
		}
	}

	public void forexDownload() throws IOException, InterruptedException {
		HashSet<String> SetExcel = new HashSet<String>();
		HashSet<String> IndexData = new HashSet<String>();

		String Alert = totalCount.getText();
		String Column = forexColumn.getText();

		IndexData.add(Column);
		SetExcel.add(Column);
		String requiredString = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf("(") - 1);
		int Al_count = Integer.parseInt(requiredString);

		downloadForexData.click();
		downloadForexData.pause(10);

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
		csvReader = new BufferedReader(new FileReader(LatestFile));

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

	public void selectAllRows() {
		try {
			rightClickOperation();
			selectAllRows.isElementPresent();
			selectAllRows.click();
			selectAllRows.pause(10);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//div[contains(@id,'" + forexTableID + "')]//span[@class='statusBarSpan']"));
			String[] countValues = ele.getText().split("S");
			Assert.assertEquals(countValues[0].replaceAll("[^0-9]", ""), countValues[1].replaceAll("[^0-9]", ""));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the count for all selected rows-" + e.getMessage());
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

	public void verifySearchListedValues() {
		try {
			// ArrayList<String> ricColumnValues = new ArrayList<>();
			ArrayList<String> codeColumnValues = new ArrayList<>();
			// ArrayList<String> feedpriceColumnValues = new ArrayList<>();

			String previousRICValue = forexRICValue.getText();
			String previousCodeValue = forexCode.getText();
			String previousFeedPrice = forexFeedPrice.getText();
			String tSearch = (previousRICValue + "," + previousCodeValue + "," + previousFeedPrice);
			forexSearchTextBox.type(tSearch);
			forexSearchTextBox.pause(5);
			//waitForTablefirstRow(forexTableID);

			for (int i = 0; i < 3; i++) {
				// ricColumnValues.add(ricColumn.get(i).getText());
				codeColumnValues.add(codeColumn.get(i).getText());
				// feedpriceColumnValues.add(priceColumn.get(i).getText());
			}

			Assert.assertTrue(codeColumnValues.contains(previousRICValue));
			Assert.assertTrue(codeColumnValues.contains(previousCodeValue));
			Assert.assertTrue(codeColumnValues.contains(previousFeedPrice));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the searched forex value with comma separated-" + e.getMessage());
		}
	}

	public void negativeNumber() {
		try {
			holdForexCurrentValue.type("-1234");
			String DefaultValue = holdForexCurrentValue.getAttribute("value");
			Assert.assertTrue(Integer.parseInt(DefaultValue) < 0, "The contract value is in negative");
			forexHoldCancel.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the negative value range in hold window-" + e.getMessage());
		}
	}

	public void holdReleaseMultipleForex(String actionType) {
		try {
			Actions builder = new Actions(getDriver());
			if (actionType.equalsIgnoreCase("hold")) {
				holdingStatusElement.isElementPresent();
				holdingStatusElement.click();
				holdingMutlipleForex();
			} else {
				holdingStatusElement.isElementPresent();
				holdingStatusElement.click();
				waitForTablefirstRow(forexTableID);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				forexRelease.isElementPresent();
				forexRelease.click();
				releaseOk.isElementPresent();
				releaseOk.click();
				forexHold.pause(25);
				getCurrentValue();
				getUniqueID();
				VerifyAlertsMessages(name, "Manual Release", currenTime, "Forex manually released");
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Not Held"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release multiple Forex-" + e.getMessage());
		}
	}

	public void holdingMutlipleForex() {
		try {
			getCurrentValue();
			ExtendedWebElement forexPricesecondRow = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + forexTableID + "')]//tr[2]//td[1]"));
			forexStatusSecondValue = forexPricesecondRow.getText();
			ExtendedWebElement forexPriceThirdRow = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + forexTableID + "')]//tr[3]//td[1]"));
			forexStatusThirdValue = forexPriceThirdRow.getText();
			if(forexPriceValue != null)
			if (status.equalsIgnoreCase(holdForexStatus) && forexStatusSecondValue.equalsIgnoreCase(holdForexStatus)
					&& forexStatusThirdValue.equalsIgnoreCase(holdForexStatus) && Double.parseDouble(forexPriceValue)>0) {
				Actions builder = new Actions(getDriver());
				waitForTablefirstRow(forexTableID);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				forexHold.isElementPresent();
				forexHoldOk.isElementPresent();
				forexHold.click();
				Assert.assertTrue(autoBasedRadioButton.isElementNotPresent(2), "Auto based hold option is visible");
				clickSaveForexOk();
				forexHold.pause(25);
				getCurrentValue();
				getUniqueID();
				VerifyAlertsMessages(name, manualHoldValue, currenTime, manualHoldMessage);
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase(manualHoldValue));
				getDriver().navigate().refresh();
			} else {
				Assert.fail("Warning Message: forex is not satisfyig the precondition");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold multiple contracts-" + e.getMessage());
		}
	}

	public void holdReleaseForexMultipleTime(String actionType) {
		try {
			getCurrentValue();
			if (status.equalsIgnoreCase(holdForexStatus) && feedStatus.equalsIgnoreCase(subcribedstatus)
					&& Double.parseDouble(forexPriceValue) > 0) {
				holdingMultipleTimes(actionType);
			} 
			else {
				getCurrentValue();
				performRightClickAction();
				forexRelease.isElementPresent();
				forexRelease.click();
				releaseOk.isElementPresent();
				releaseOk.click();
				thresholdOk.pause(50);
				getCurrentValue();
				Assert.assertTrue(status.equalsIgnoreCase("Not Held"));
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release contract for multiple times-" + e.getMessage());
		}
	}
	
	public void holdingMultipleTimes(String actionType )
	{
		try {
			int loopcount = 0;
			if (actionType.equalsIgnoreCase("holdMultiple")) {
				for (int i = 0; i < 3; i++) {
					rightClickOperation();
					forexHold.isElementPresent();
					forexHold.click();
					clickSaveForexOk();
					if (loopcount == 0)
						holdForexCurrentValue.pause(50);
					getCurrentValue();
					Assert.assertTrue(status.equalsIgnoreCase(manualHoldValue));
					loopcount++;
				}
			}}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to hold forex for multiple times-" + e.getMessage());
			}
	}
	public void performRightClickAction() {
		try {
			if (status.equalsIgnoreCase("Not Held")) {
				for (int i = 0; i < 3; i++) {
					rightClickOperation();
					break;
				}
			} else if (status.equalsIgnoreCase("Manual Hold")) {
				for (int i = 0; i < 3; i++) {
					rightClickOperation();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform right click action on selected forex-" + e.getMessage());
		}
	}

	public void clickOnSubscribedLink() {
		try {
			switchToFrame(dashboardFrameId);
			subscribedLink.isElementPresent();
			subscribedLink.click();
			switchOutOfFrame();
			switchToFrame(researchFrameID);
			waitForTablefirstRow(forexTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Subscribed link-" + e.getMessage());
		}
	}

}
