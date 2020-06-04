package com.grip.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.grip.DBconnection.DerivativesSQLStatements;
import com.grip.utils.ExcelUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class DerivativesPage extends AbstractPage {

	public DerivativesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}

	public static String derivativeTableID = "derivativesTable", exchangeID = "exchange",
			researchFrameID = "researchframe", dashboardFrameId = "dashboardframe", reportFrameID = "reportsframe",
			reportsTabId = "selectReportsTable", supportFrame = "supportframe";
	public static String nextMonth, subscribedCount, notFound, dashboardLinkVaue, columnValue, time;
	public static LinkedHashMap<String, String> fetchTableColumnValues = new LinkedHashMap<>();
	Logger LOGGER = Logger.getLogger(DerivativesPage.class);
	public static ExcelUtils excel = new ExcelUtils();
	ArrayList<String> columnUI = new ArrayList<>();
	BufferedReader csvReader;

	@FindBy(xpath = "//span[contains(@title,'Open a new Derivatives')]")
	private ExtendedWebElement derivative;

	@FindBy(xpath = "//span[contains(@title,'Open a new Exchange')]")
	private ExtendedWebElement exchange;

	@FindBy(xpath = "//table[@class='display dataTable']//tr//td[3]")
	private ExtendedWebElement derivativeData;

	@FindBy(xpath = "//table[@class='exchangeTableDisplay dataTable']//tr//td[3]")
	private ExtendedWebElement exchangeData;

	@FindBy(xpath = "//span[text()='Show Derivatives']")
	public ExtendedWebElement showDerivatives;

	@FindBy(xpath = "//span[text()='Copy Name']")
	public ExtendedWebElement copyName;

	@FindBy(xpath = "//span[text()='Copy ID']")
	public ExtendedWebElement copyId;

	@FindBy(id = "copyNameTextareaId")
	public ExtendedWebElement copyNameClipboard;

	@FindBy(id = "copyIdTextareaId")
	public ExtendedWebElement copyIdClipboard;

	@FindBy(xpath = "(//span[text()='Cancel'])[2]")
	public ExtendedWebElement copyNameCancel;

	@FindBy(xpath = "(//span[text()='Copy to Clipboard'])[1]")
	public ExtendedWebElement copyToClipborad;

	@FindBy(xpath = "(//span[text()='Cancel'])[3]")
	public ExtendedWebElement copyIDCancel;

	@FindBy(xpath = "//span[text()='Select All Rows']")
	public ExtendedWebElement selectAllRows;

	@FindBy(xpath = "//span[contains(text(),'Show Exchange')]")
	private ExtendedWebElement showExchange;

	@FindBy(xpath = "//input[@title='Download Derivatives Data']")
	public ExtendedWebElement downloadDerivativeData;

	@FindBy(xpath = "//table[@class='display dataTable']//thead//tr")
	public ExtendedWebElement derivativeColumns;

	@FindBy(xpath = "//*[starts-with(@id,'derivativesTable')]//input[contains(@title,'search text')]")
	private ExtendedWebElement derivativeSearchTextBox;

	@FindBy(xpath = "//*[starts-with(@id,'exchangeTable')]//input[contains(@title,'search text')]")
	private ExtendedWebElement exchangeSearchTextBox;

	@FindBy(xpath = "(//span[contains(@data-bind,'Subscribed')])[last()]")
	public ExtendedWebElement dashboardSubscribed;

	@FindBy(xpath = "(//span[contains(@data-bind,'NotFound')])[last()]")
	public ExtendedWebElement dashboardNotFoundEle;

	@FindBy(xpath = "//div[contains(@class,'window_title')]")
	public ExtendedWebElement windowTitleText;

	@FindBy(xpath = "//a[contains(@href,'dashboard')]")
	private ExtendedWebElement dashboardTab;

	@FindBy(xpath = "//input[@title='Column Selector']")
	private ExtendedWebElement columnSelector;

	@FindBy(id = "adfFilter")
	private ExtendedWebElement availableDataFields;

	@FindBy(id = "sdfFilter")
	private ExtendedWebElement selectedDataFields;

	@FindBy(xpath = "//ul[@id='availableList']/li[@class='visible']")
	private ExtendedWebElement adfDisplayList;

	@FindBy(xpath = "//ul[@id='selectedList']/li[@class='visible']")
	private ExtendedWebElement sdfDisplayList;

	@FindBy(id = "btnRightDataPoints")
	private ExtendedWebElement rightArrow;

	@FindBy(id = "btnLeftDataPoints")
	private ExtendedWebElement leftArrow;

	@FindBy(xpath = "//span[text()='OK']")
	public ExtendedWebElement OkButton;

	@FindBy(xpath = "//span[text()='Ok']")
	public ExtendedWebElement reportsOkButton;

	@FindBy(xpath = "//span[text()='Report']")
	public ExtendedWebElement report;

	@FindBy(id = "assetTextId")
	public ExtendedWebElement filterReportInput;

	@FindBy(id = "indexTicks")
	private ExtendedWebElement indexTickcheckBox;

	@FindBy(id = "assetTicks")
	private ExtendedWebElement indexICDcheckBox;

	@FindBy(id = "getReports")
	private ExtendedWebElement getReports;

	@FindBy(id = "time_hour_id")
	private ExtendedWebElement timeTextBox;

	@FindBy(id = "slider_from_date_time_id")
	private ExtendedWebElement dateTextBox;

	@FindBy(xpath = "//button[@title='Download Reports']")
	public ExtendedWebElement downloadReports;

	@FindBy(xpath = "//table[contains(@id,'selectReportsTable')]/tbody/tr[1]/td")
	public List<ExtendedWebElement> reportsFirstRowData;

	@FindBy(xpath = "(//table[@class='targetTableDisplay dataTable'])[1]/thead/tr/th")
	public List<ExtendedWebElement> reportsTableHeader;

	@FindBy(xpath = "//div[contains(@id,'reports')]//span[@class='reportsBarSpan']")
	public ExtendedWebElement reportstatusBar;

	@FindBy(xpath = "//span[text()='Compare with T3']")
	private ExtendedWebElement compareWithT3;

	@FindBy(xpath = "//span[text()='Exchange Portfolio']")
	private ExtendedWebElement exchangePortfolio;

	@FindBy(xpath = "//span[text()='Exchange Timings']")
	private ExtendedWebElement exchangeTimings;

	@FindBy(xpath = "//a[contains(@href,'supportTab')]")
	private ExtendedWebElement supportTab;

	@FindBy(xpath = "//div[@id='activeMQConsoleTableId']")
	public ExtendedWebElement activeMQ;

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

	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public WebElement alertSearchScroll;

	public void waitForTablefirstRow(String tableID) {
		ExtendedWebElement ele = findExtendedWebElement(
				By.xpath("//table[contains(@id,'" + tableID + "')]/tbody/tr[1]"));
		ele.isElementPresent();
	}

	public void clickDerivative() {
		try {
			derivative.isElementPresent();
			derivative.click();
			waitForTablefirstRow(derivativeTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on derivative tab-" + e.getMessage());
		}
	}

	public void clickExchangeTab() {
		try {
			exchange.isElementPresent();
			exchange.click();
			waitForTablefirstRow(exchangeID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on exchange tab-" + e.getMessage());
		}
	}

	public void cilckShowDerivative() {
		try {
			showDerivatives.isElementPresent();
			showDerivatives.click();
			waitForTablefirstRow(derivativeTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on show derivative-" + e.getMessage());
		}
	}

	public void getDerivativeCoulmnValue() {
		try {
			ExtendedWebElement derivativeID = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr//td[1]"));
			fetchTableColumnValues.put("derivativeIDValue", derivativeID.getText());
			LOGGER.info("Derivative ID - " + fetchTableColumnValues.get("derivativeIDValue"));
			ExtendedWebElement derivativeName = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr//td[7]"));
			fetchTableColumnValues.put("derivativeNameValue", derivativeName.getText());
			LOGGER.info("Derivative Name - " + fetchTableColumnValues.get("derivativeNameValue"));
			ExtendedWebElement derivativeCode = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr//td[6]"));
			fetchTableColumnValues.put("derivativeCodeValue", derivativeCode.getText());
			LOGGER.info("Derivative code - " + fetchTableColumnValues.get("derivativeCodeValue"));
			ExtendedWebElement feedPrice = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr//td[8]"));
			fetchTableColumnValues.put("feedPriceValue", feedPrice.getText());
			LOGGER.info("Derivative code - " + fetchTableColumnValues.get("feedPriceValue"));
			ExtendedWebElement bidPrice = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr//td[11]"));
			fetchTableColumnValues.put("bidPriceValue", bidPrice.getText());
			LOGGER.info("Derivative code - " + fetchTableColumnValues.get("feedPriceValue"));
			ExtendedWebElement tradingSession = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr//td[3]"));
			fetchTableColumnValues.put("tradingSessionValue", tradingSession.getText());
			LOGGER.info("Derivative code - " + fetchTableColumnValues.get("tradingSessionValue"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get column values from derivative table" + e.getMessage());
		}

	}

	public void performRightClickAction() {
		try {
			for (int i = 0; i < 3; i++) {
				if (derivativeData.isElementPresent()) {
					derivativeData.click();
					derivativeData.rightClick();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perfrom right click action on selected derivative-" + e.getMessage());
		}
	}

	public void scrollToDown() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// alertSearch.scrollTo();
			js.executeScript("arguments[0].scrollIntoView(true);", alertSearchScroll);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to scroll to alert search box-" + e.getMessage());
		}
	}

	public void performRightClickActionExchange() {
		try {
			scrollToDown();
			for (int i = 0; i < 3; i++) {
				if (exchangeData.isElementPresent()) {
					exchangeData.click();
					exchangeData.rightClick();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perfrom right click action on selected exchange-" + e.getMessage());
		}
	}

	public void copyName() {
		try {
			copyName.isElementPresent();
			copyName.click();
			copyNameClipboard.isElementPresent();
			LOGGER.info("Derivative Name - " + fetchTableColumnValues.get("derivativeNameValue"));
			LOGGER.info("Copy Name value from clip board - " + copyNameClipboard.getAttribute("value"));
			Assert.assertTrue(
					fetchTableColumnValues.get("derivativeNameValue")
							.equalsIgnoreCase(copyNameClipboard.getAttribute("value")),
					"Derivative name and Copy name from clipboard values are not matching");
			copyToClipborad.click();
			copyNameCancel.click();
			copyNameCancel.pause(2);
			derivativeSearchTextBox.type(Keys.chord(Keys.CONTROL, "V"));
			Assert.assertEquals(derivativeSearchTextBox.getAttribute("value"),
					fetchTableColumnValues.get("derivativeNameValue"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate copy name" + e.getMessage());
		}
	}

	public void copyId() {
		try {
			copyId.isElementPresent();
			copyId.click();
			copyIdClipboard.isElementPresent();
			LOGGER.info("Derivative ID - " + fetchTableColumnValues.get("derivativeIDValue"));
			LOGGER.info("Copy ID value from clip board - " + copyIdClipboard.getAttribute("value"));
			Assert.assertTrue(
					copyIdClipboard.getAttribute("value")
							.equalsIgnoreCase(fetchTableColumnValues.get("derivativeIDValue")),
					"Derivative ID and Copy ID from clipboard values are not matching");
			copyToClipborad.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate copy id" + e.getMessage());
		}
	}

	public void selectAllRows() {
		try {
			selectAllRows.isElementPresent();
			selectAllRows.click();
			selectAllRows.pause(10);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//div[contains(@id,'" + derivativeTableID + "')]//span[@class='statusBarSpan']"));
			String[] countValues = ele.getText().split("S");
			LOGGER.info("Total Drivative rows - " + countValues[0].replaceAll("[^0-9]", ""));
			LOGGER.info("Select All Drivative rows - " + countValues[1].replaceAll("[^0-9]", ""));
			Assert.assertEquals(countValues[0].replaceAll("[^0-9]", ""), countValues[1].replaceAll("[^0-9]", ""));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the count for all slected rows-" + e.getMessage());
		}
	}

	public void clickExchange() {
		try {
			showExchange.isElementPresent();
			showExchange.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on show exchange-" + e.getMessage());
		}

	}

	public void showDetails() throws InterruptedException, IOException {
		try {
			int derivativeCountList = 1;
			String totalCountText;
			clickExchange();
			ExtendedWebElement totalCountValue = findExtendedWebElement(
					By.xpath("//div[contains(@id,'" + exchangeID + "')]//span[@class='statusBarSpan']"));
			totalCountValue.isElementPresent();
			waitForTablefirstRow(exchangeID);
			totalCountText = totalCountValue.getText().substring(0, totalCountValue.getText().indexOf("("));
			Assert.assertEquals(totalCountText.replaceAll("[^0-9]", ""), Integer.toString(derivativeCountList));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform action on show details and verify value-" + e.getMessage());
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

	public int countRecord(File Dirpath) throws IOException {
		int count = 0;
		csvReader = new BufferedReader(new FileReader(Dirpath));
		String row = null;
		while ((row = csvReader.readLine()) != null) {
			count++;
		}
		return count;
	}

	public void derivativeDownload() throws IOException, InterruptedException {
		try {
			downloadDerivativeData.click();
			downloadDerivativeData.pause(10);

			String home = System.getProperty("user.home");
			File LatestFile = getLatestFilefromDir(home + "/Downloads");
			LOGGER.info("File is-" + LatestFile);

			csvReader = new BufferedReader(new FileReader(LatestFile));
			int count = countRecord(LatestFile);
			DerivativesSQLStatements.getDerivativetotalCount();
			Assert.assertEquals(Integer.toString(count - 2), DerivativesSQLStatements.derivativeTotalcount,
					"The count is not matched with Downloaded CSV file and DB");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify count of derivative records with DB and download data-" + e.getMessage());
		}
	}

	public void verifyFilter(String Search) {
		try {
			waitForTablefirstRow(derivativeTableID);
			columnUI = new ArrayList<String>();
			ArrayList<String> colunInput = new ArrayList<String>();
			List<ExtendedWebElement> tableRows = derivativeColumns.findExtendedWebElements(By.tagName("th"));
			System.out.println(tableRows);

			for (int i = 1; i <= tableRows.size(); i++) {
				ExtendedWebElement derivativeColumn = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr[1]//td[" + i + "]"));
				derivativeColumn.click();
				ExtendedWebElement columnName = findExtendedWebElement(
						By.xpath("//table[@class='display dataTable']//thead//tr//th[" + i + "]"));
				columnUI.add(columnName.getText());
			}
			String searchSplit[] = Search.split(",");
			for (int k = 0; k < searchSplit.length; k++) {
				colunInput.add(searchSplit[k]);
			}
			Assert.assertTrue(columnUI.equals(colunInput), "Column headers filters are not matching");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify derivative filter headers" + e.getMessage());
		}
	}

	public void verifyColumnHeader() {
		try {
			String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC02002", "TestData1");
			verifyFilter(Search);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify filter derivatve table headers combo-" + e.getMessage());
		}
	}

	public static String getDate(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		return df.format(date);
	}

	public String getMonthForInt(int num) {
		String month = "";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month.substring(0, 3);
	}

	public void searchDerivativeData() {
		try {
			nextMonth = getMonthForInt(Integer.parseInt(getDate("MM")));
			derivativeSearchTextBox.type(nextMonth);
			derivativeSearchTextBox.pause(3);
			waitForTablefirstRow(derivativeTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search the month for derivative data-" + e.getMessage());
		}
	}

	public void searchExchangeDerivativeData() {
		try {
			String derivativeID = "1981";
			exchangeSearchTextBox.type(derivativeID);
			exchangeSearchTextBox.pause(2);
			waitForTablefirstRow(exchangeID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search derivative datain exchange table-" + e.getMessage());
		}
	}

	public void verifySearchedData() {
		try {
			int rowLimit = 30;
			for (int i = 1; i < rowLimit; i++) {
				ExtendedWebElement derivativeColumn = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr[" + i + "]//td[7]"));
				Assert.assertTrue(derivativeColumn.getText().contains(nextMonth));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the seacrhed month for derivative data-" + e.getMessage());
		}
	}

	public void switchToFrame(String frameID) {
		driver.switchTo().frame(frameID);
	}

	public void switchOutOfFrame() {
		driver.switchTo().defaultContent();
	}

	public void clickDashboardLink(String dasboardLinkType) {
		try {
			dashboardLinkVaue = dasboardLinkType;
			switchToFrame(dashboardFrameId);
			dashboardTab.isElementPresent();
			switch (dashboardLinkVaue) {
			case "Subscribed":
				dashboardSubscribed.isElementPresent();
				subscribedCount = dashboardSubscribed.getText();
				dashboardSubscribed.click();
				break;

			case "Not Found":
				dashboardNotFoundEle.isElementPresent();
				notFound = dashboardNotFoundEle.getText();
				dashboardNotFoundEle.click();
				break;

			default:
				break;
			}
			switchOutOfFrame();
			switchToFrame(researchFrameID);
			waitForTablefirstRow(derivativeTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Dashboard states link-" + e.getMessage());
		}
	}

	public void verifyCount() {
		try {
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//div[contains(@id,'" + derivativeTableID + "')]//span[@class='statusBarSpan']"));
			String[] countValues = ele.getText().split("S");
			if (dashboardLinkVaue.equalsIgnoreCase("Subscribed")) {
				Assert.assertEquals(countValues[0].replaceAll("[^0-9]", ""), subscribedCount);
				Assert.assertTrue(windowTitleText.getText().contains(dashboardLinkVaue));
				getDriver().navigate().refresh();
			} else {
				Assert.assertEquals(countValues[0].replaceAll("[^0-9]", ""), notFound);
				Assert.assertTrue(windowTitleText.getText().contains(dashboardLinkVaue));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify dashboard and derivative window count-" + e.getMessage());
		}
	}

	public void addColumn() {
		try {
			columnValue = "Baseline";
			columnSelector.click();
			availableDataFields.isElementPresent();
			availableDataFields.type(columnValue);
			adfDisplayList.isElementPresent();
			adfDisplayList.click();
			rightArrow.click();
			OkButton.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to add column in column header table-" + e.getMessage());
		}
	}

	public void fetchTableColumnHearder() {
		try {
			List<ExtendedWebElement> tableRows = derivativeColumns.findExtendedWebElements(By.tagName("th"));
			for (int i = 1; i <= tableRows.size(); i++) {
				ExtendedWebElement derivativeColumn = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr[1]//td[" + i + "]"));
				derivativeColumn.click();
				ExtendedWebElement columnName = findExtendedWebElement(
						By.xpath("//table[@class='display dataTable']//thead//tr//th[" + i + "]"));
				columnUI.add(columnName.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to add the header values in column header table-" + e.getMessage());
		}
	}

	public void verifyAddedColumn() {
		try {
			waitForTablefirstRow(derivativeTableID);
			fetchTableColumnHearder();
			Assert.assertTrue(columnUI.contains(columnValue), "The column is not added to the table");
			columnUI.clear();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify added column in column header table-" + e.getMessage());
		}

	}

	public void removeColumn() {
		try {
			columnSelector.click();
			selectedDataFields.isElementPresent();
			selectedDataFields.type(columnValue);
			sdfDisplayList.isElementPresent();
			sdfDisplayList.click();
			leftArrow.click();
			OkButton.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to remove column in column header table-" + e.getMessage());
		}
	}

	public void verifyRemovedColumn() {
		try {
			waitForTablefirstRow(derivativeTableID);
			fetchTableColumnHearder();
			Assert.assertTrue(!columnUI.contains(columnValue), "The column is not added to the table");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify removed column in column header table-" + e.getMessage());
		}
	}

	public void clickReport() {
		try {
			report.isElementPresent();
			report.click();
			switchOutOfFrame();
			switchToFrame(reportFrameID);
			filterReportInput.isElementPresent();
			Assert.assertTrue(filterReportInput.getAttribute("value")
					.contains(fetchTableColumnValues.get("derivativeNameValue")));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate ID in Asset text box" + e.getMessage());
		}
	}

	public void setTime(String timeValue) {
		try {
			timeTextBox.isElementPresent();
			timeTextBox.pause(3);
			timeTextBox.getElement().sendKeys(Keys.chord(Keys.CONTROL, "a"));
			timeTextBox.pause(3);
			timeTextBox.getElement().sendKeys(timeValue);
			if (reportsOkButton.isElementPresent()) {
				reportsOkButton.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Time drop down is not passed successfully-" + e.getMessage());
		}
	}

	public void setDate(String date) {
		try {
			dateTextBox.isElementPresent();
			dateTextBox.pause(3);
			dateTextBox.getElement().sendKeys(Keys.chord(Keys.CONTROL, "a"));
			dateTextBox.pause(3);
			dateTextBox.getElement().sendKeys(date);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to set the date-" + e.getMessage());
		}
	}

	public void runReports() {
		try {
			String dateValue = null;
			String monthValue = null;
			if (getDate("E").equalsIgnoreCase("mon") || getDate("E").equalsIgnoreCase("sun")) {
				if (getDate("dd").equalsIgnoreCase("1")) {
					dateValue = "29";
					monthValue = Integer.toString(Integer.parseInt(getDate("MM")) - 1);
				} else {
					dateValue = Integer.toString(Integer.parseInt(getDate("dd")) - 3);
					monthValue = getDate("MM");
				}
			} else {
				dateValue = Integer.toString(Integer.parseInt(getDate("dd")) - 1);
				monthValue = getDate("MM");
			}

			time = monthValue + "/" + dateValue + "/" + getDate("yyyy");
			System.out.println("Time-" + time);
			indexTickcheckBox.click();
			indexICDcheckBox.click();
			setDate(time);
			setTime("18:31:00");
			getReports.click();
			getReports.pause(25);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + reportsTabId + "')]/tbody/tr[1]/td"));
			ele.isElementPresent();
			if (ele.getAttribute("class").contains("empty")) {
				Assert.fail("No reports were generated for todays date");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to generate reports-" + e.getMessage());
		}
	}

	public void verifyReportWihtDB() {
		try {
			ArrayList<String> UIReportsValue = new ArrayList<>();
			for (int i = 0; i < reportsFirstRowData.size(); i++) {
				UIReportsValue.add(reportsFirstRowData.get(i).getText());
			}
			DerivativesSQLStatements.getRunReportValues();
			for (int i = 0; i < DerivativesSQLStatements.DBReportsValue.size(); i++) {
				Assert.assertTrue(UIReportsValue.contains(DerivativesSQLStatements.DBReportsValue.get(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate reports with DB and UI-" + e.getMessage());
		}
	}

	public void verifyReportWithDownload() {
		try {
			ArrayList<String> SetExcel = new ArrayList<String>();
			ArrayList<String> IndexData = new ArrayList<String>();

			downloadReports.click();
			downloadReports.pause(10);

			String home = System.getProperty("user.home");
			// String home = R.CONFIG.getProperties().getProperty("auto_download_folder");
			File LatestFile = getLatestFilefromDir(home + "/Downloads");
			LOGGER.info("File is-" + LatestFile);

			csvReader = new BufferedReader(new FileReader(LatestFile));
			int count = countRecord(LatestFile);
			String countValueStatusBar[] = reportstatusBar.getText().split("S");
			Assert.assertEquals(countValueStatusBar[0].replaceAll("[^0-9]", ""), Integer.toString(count - 1),
					"Count is not matched with UI table and downloaded report");

			for (int i = 0; i < 6; i++) {
				IndexData.add(reportsTableHeader.get(i).getText());
			}

			String row = "";
			String[] data = null;
			csvReader = new BufferedReader(new FileReader(LatestFile));
			for (int i = 0; i < 5; i++) {
				if ((row = csvReader.readLine()) != null) {
					data = row.split(",");
					for (int j = 0; j < data.length; j++) {
						SetExcel.add(data[j].replaceAll("^\"|\"$", ""));
					}
					break;
				}
			}
			csvReader.close();
			Assert.assertTrue(IndexData.equals(SetExcel), "Table headers in UI and Downloaded file are not matching-");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate reports with Downloaded one and UI-" + e.getMessage());
		}
	}

	public void compareExchange(String exchangeType) {
		try {
			waitForTablefirstRow(exchangeID);
			performRightClickActionExchange();
			compareWithT3.isElementPresent();
			compareWithT3.hover();
			if (exchangeType.equalsIgnoreCase("Exchange Portfolio")) {
				exchangePortfolio.isElementPresent();
				exchangePortfolio.click();
				exchangePortfolio.pause(3);
			} else {
				exchangeTimings.isElementPresent();
				exchangeTimings.click();
				exchangeTimings.pause(3);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to compare exchange with t3-" + e.getMessage());
		}

	}

	public void clickSupportTab() {
		try {
			switchOutOfFrame();
			supportTab.click();
			switchToFrame(supportFrame);
			Assert.assertTrue(activeMQ.isElementPresent());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Support tab-" + e.getMessage());
		}
	}

	public void executeOperations(String processType1, String processType2, String selectType) {
		try {
			String key = "9995";
			String[] values = { processType1, processType2 };
			selectProcess.select(values);
			selectAction.select(selectType);
			IDTextBox.isElementPresent();
			IDTextBox.type(fetchTableColumnValues.get("derivativeIDValue"));
			execute.click();
			execute.pause(5);
			String UIText = ouputITextArea.getAttribute("value");
			String verifyText = "key/value=" + key + "/" + fetchTableColumnValues.get("derivativeCodeValue") + "";
			Assert.assertTrue(UIText.contains(verifyText),
					"Key values are not matching after executig TL support actions");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to execute support TL operations-" + e.getMessage());
		}
	}

	public void validateBidPriceData() {
		try {
			getDerivativeCoulmnValue();
			int rowLimit = 30;
			for (int i = 1; i < rowLimit; i++) {
				ExtendedWebElement bidPrice = findExtendedWebElement(
						By.xpath("//table[contains(@id,'" + derivativeTableID + "')]//tr[" + i + "]//td[11]"));
				if (i < 2)
					bidPrice.click();
				Assert.assertTrue(Double.valueOf(bidPrice.getText()) > 0,
						"The Bid price price valuesa are zero in place");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate bid price data-" + e.getMessage());
		}
	}
}