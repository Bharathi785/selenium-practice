package com.grip.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.grip.DBconnection.IndicesSQLStatements;
import com.grip.utils.EmailValidation;
import com.grip.utils.ExcelUtils;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class IndicesPage extends AbstractPage {

	public static ExcelUtils excel = new ExcelUtils();
	Logger LOGGER = Logger.getLogger(IndicesPage.class);

	public static String searchDataValue, currenTime, indexTypeValue, alertUniqueID, alertUniqueID2, Subject,
			correctionTypeValue, baseLineUp, baseLineDown, prevTickUp, prevTickDown, dasboardLink, showTypeValue,
			initalOpenValue, initialDailyHighValue, initialDailyLowValue, homePath, compareWithT3Value;

	public static String BASE_LINE_WARN_THRESHOLD_UP, BASE_LINE_WARN_THRESHOLD_DOWN, BASE_LINE_ERR_THRESHOLD_UP,
			BASE_LINE_ERR_THRESHOLD_DOWN, PREV_TICK_ERR_THRESHOLD_UP, PREV_TICK_ERR_THRESHOLD_DOWN;

	public static String dashboardFrameId = "dashboardframe", indexTableID = "indexTable",
			dataMaintainFrameID = "datamaintainframe", researchFrameID = "researchframe",
			reportFrameID = "reportsframe", publishOHLT3DateID = "publishOHLToT3DateId",
			publishPrelimToT3DateID = "publishPCloseToT3DateId", manualHoldMessage = "Index has been manually held",
			icdID = "icd", exchangeID = "exchange", manulaHoldValue = "Manual Hold",
			publishSuspectMessage = "Publish suspect successfully", publishUserSuspectValue = "0",
			publishDataQualitytValue = "0";

	public static int initialNoTicksGripCount = 0, finalNoTicksGripCount = 0, initialManualHoldCount = 0,
			finalManualHoldCount = 0, initialClosecount = 0, initialOpencount, initialPrelimClosecount = 0,
			initialSuspectCount = 0, finalSuspectCount = 0, initialWarning = 0, finalWarning = 0, alertTableSize = 0,
			HPICount = 1, initialAutoholdCount = 0, finalAutoholdCount = 0;

	public static LinkedHashMap<String, String> fetchTableColumnValues = new LinkedHashMap<>();
	BufferedReader csvReader;

	public IndicesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(id = "environmentListHeader")
	private ExtendedWebElement headerTitle;

	@FindBy(xpath = "//table[@class='display dataTable']/tbody/tr[1]")
	private ExtendedWebElement indexTableFirstRow;

	@FindBy(xpath = "//a[contains(@href,'dashboard')]")
	private ExtendedWebElement dashboardTab;

	@FindBy(xpath = "//a[contains(@href,'resea')]")
	private ExtendedWebElement research;

	@FindBy(xpath = "//span[contains(@title,'Open a new Index')]")
	private ExtendedWebElement index;

	@FindBy(xpath = "//*[starts-with(@id,'indexTable')]//input[contains(@title,'search text')]")
	private ExtendedWebElement indexSearchTextBox;

	@FindBy(xpath = "//span[contains(text(),'Constituent')]")
	private ExtendedWebElement showConstituentDetails;

	@FindBy(xpath = "//span[contains(text(),'Exchanges')]")
	private ExtendedWebElement showExchange;

	@FindBy(xpath = "//span[text()='Advanced']")
	private ExtendedWebElement advanced;

	@FindBy(xpath = "//span[text()='Release']")
	private ExtendedWebElement release;

	@FindBy(id = "indexHoldTypeAutoId")
	private ExtendedWebElement autoBasedRadioButton;

	@FindBy(id = "indexViewsList")
	private ExtendedWebElement indexDropdown;

	@FindBy(xpath = "//span[contains(@data-bind,'noTicksIndexSPF')]")
	public ExtendedWebElement dashboardNoTicksGrip;

	@FindBy(xpath = "(//span[contains(@data-bind,'ManualHold')])[1]")
	public ExtendedWebElement dashboardManualHold;

	@FindBy(xpath = "//span[contains(@data-bind,'iClose')]")
	public ExtendedWebElement dashboardClose;

	@FindBy(xpath = "//span[contains(@data-bind,'iWarning')]")
	public ExtendedWebElement dashboardWarning;

	@FindBy(xpath = "//span[contains(@data-bind,'iSuspect')]")
	public ExtendedWebElement dashboardSuspect;

	@FindBy(xpath = "//span[contains(@data-bind,'iAutoHold')]")
	public ExtendedWebElement dashboardAutoHold;

	@FindBy(xpath = "(//span[contains(@data-bind,'Open')])[position()=1]")
	public ExtendedWebElement dashboardOpen;

	@FindBy(xpath = "//div[@id='targetIndicesTable_wrapper']//div//div[2]//table//tbody//td[1]")
	public ExtendedWebElement targetIndicesIndexID;

	@FindBy(xpath = "//div[@id='targetIndicesTable_wrapper']//div//div[2]//table//tbody")
	public ExtendedWebElement targetIndicesTable;

	@FindBy(xpath = "//input[@onclick='saveIndexData()']")
	public ExtendedWebElement targetIndicesSave;

	@FindBy(xpath = "//input[@onclick='saveIndexData()']")
	public WebElement targetIndicesSave2;

	@FindBy(id = "baseLineErrorThresholdUp")
	public ExtendedWebElement baseLineErrorThresholdUp;

	@FindBy(id = "baseLineErrorThresholdDown")
	public ExtendedWebElement baseLineErrorThresholdDown;

	@FindBy(id = "prevTickErrorThresholdUp")
	public ExtendedWebElement prevTickErrorThresholdUp;

	@FindBy(id = "prevTickErrorThresholdDown")
	public ExtendedWebElement prevTickErrorThresholdDown;

	@FindBy(xpath = "//span[text()='Hold']")
	private ExtendedWebElement indexHold;

	@FindBy(xpath = "//span[text()='Release']")
	private ExtendedWebElement indexRelease;

	@FindBy(id = "holdIndexValueId")
	private ExtendedWebElement holdIndexCurrentValue;

	@FindBy(id = "indexHoldTypeManualId")
	private ExtendedWebElement manualReleaseRadioButton;

	@FindBy(xpath = "//span[text()='Ok']")
	public ExtendedWebElement indexHoldOk;

	@FindBy(xpath = "//div[@aria-describedby='releaseIndexDialog']/div[11]/div/button/span[text()='Ok']")
	public ExtendedWebElement releaseOk;

	@FindBy(xpath = "//div[@aria-describedby='alertDialogId']/div[11]/div/button/span")
	public ExtendedWebElement refreshT3Ok;

	@FindBy(xpath = "(//span[text()='Cancel'])[position()=5]")
	public ExtendedWebElement indexHoldCancel;

	@FindBy(xpath = "(//span[text()='Cancel'])[2]")
	public ExtendedWebElement copyNameCancel;

	@FindBy(xpath = "(//span[text()='Cancel'])[3]")
	public ExtendedWebElement copyIDCancel;

	@FindBy(id = "startTimeHrUTCId")
	public ExtendedWebElement startTimeUTC;

	@FindBy(id = "endTimeHrUTCId")
	public ExtendedWebElement endTimeUTC;

	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr")
	public ExtendedWebElement alertTableFirstRow;

	@FindBy(xpath = "//table[@id='alertTable']//tbody//tr[2]")
	public ExtendedWebElement alertTableSecondRow;

	@FindBy(xpath = "//a[@href='#rtmindexbodyid']")
	public ExtendedWebElement rTMIndex;

	@FindBy(xpath = "//a[@href='#refreshmviewbody']")
	public ExtendedWebElement refreshMViewTab;

	@FindBy(xpath = "//input[@onclick='refreshMViews()']")
	public ExtendedWebElement refreshMViewbutton;

	@FindBy(xpath = "//span[@data-bind='text: iPrelimClose']")
	private ExtendedWebElement dashboardPrelimClose;

	@FindBy(xpath = "//div//table[@class='display dataTable']//tbody/tr")
	private List<WebElement> tableRowData;

	@FindBy(xpath = "//span[text()='Compare with T3']")
	private ExtendedWebElement compareWithT3;

	@FindBy(xpath = "//span[text()='Index Portfolio']")
	private ExtendedWebElement indexPortfolio;

	@FindBy(xpath = "//span[text()='Index Parameters']")
	public ExtendedWebElement indexParameters;

	@FindBy(xpath = "//span[contains(text(),'Publish Add Definition')]")
	public ExtendedWebElement publishAddDefinition;

	@FindBy(xpath = "//span[contains(text(),'Publish Update Definition')]")
	public ExtendedWebElement publishUpdateDefinition;

	@FindBy(xpath = "//span[contains(text(),'Publish News')]")
	public ExtendedWebElement publishNews;

	@FindBy(xpath = "//span[text()='Index Timings']")
	public ExtendedWebElement indexTimings;

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

	@FindBy(xpath = "//div[@class='topleftcell']//tbody//tr[1]//td[3]//input")
	public ExtendedWebElement filterReportInput;

	@FindBy(xpath = "//span[@class='statusBarSpan']")
	public ExtendedWebElement totalCount;

	@FindBy(xpath = "(//span[text()='Ok'])[2]")
	public ExtendedWebElement updateIndexOk;

	@FindBy(xpath = "(//span[text()='Ok'])[3]")
	public ExtendedWebElement newsIndexOk;

	@FindBy(xpath = "//span[text()='Move to Next Day']")
	private ExtendedWebElement moveToNextDay;

	@FindBy(xpath = "//span[contains(@data-bind,'iNewDay')]")
	public ExtendedWebElement dashboardNewDay;

	@FindBy(id = "publishCloseFromGripValueId")
	public ExtendedWebElement publishcloseFromGripTextBox;

	@FindBy(xpath = "//h3[contains(@class,'headerfont')]")
	private ExtendedWebElement indexFamily;

	@FindBy(xpath = "//*[starts-with(@id,'indexTable')]//input")
	private ExtendedWebElement searchIndex;

	@FindBy(xpath = "//div[@aria-describedby='releaseIndexDialog']//div[11]//div//button//span")
	private ExtendedWebElement releaseIndexOk;

	@FindBy(xpath = "//div[@aria-describedby='genericSuspectIndexDialog']//div[11]//div//button//span")
	private ExtendedWebElement withdrawSuspectOk;

	@FindBy(xpath = "(//table[@class='display dataTable']//tr//td[3])[1]")
	private ExtendedWebElement indexData;

	@FindBy(xpath = "(//table[@class='display dataTable']//tr//td[3])[1]")
	private WebElement indexDataWait;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[1]//td[4]")
	public ExtendedWebElement indexRICValue;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[2]//td[5]")
	public ExtendedWebElement indexIDCValue;

	@FindBy(xpath = "//table[@class='display dataTable']//tr[3]//td[6]")
	public ExtendedWebElement indexName;

	@FindBy(xpath = "//ul[@class='context-menu-list context-menu-root']//li[9]")
	private ExtendedWebElement refreshFromT3;

	@FindBy(xpath = "//span[contains(text(),'Refresh from GRIP')]")
	private ExtendedWebElement refreshFromGrip;

	@FindBy(xpath = "//span[contains(text(),'Publish Op/Hi/Lo To T3')]")
	private ExtendedWebElement publishOpHiLoToT3;

	@FindBy(xpath = "//span[contains(text(),'Publish Close from T3')]")
	private ExtendedWebElement publishCloseFromT3;

	@FindBy(xpath = "//span[contains(text(),'Publish Close from GRIP')]")
	private ExtendedWebElement publishCloseFromGrip;

	@FindBy(xpath = "//span[contains(text(),'Publish Prelim Close To T3')]")
	private ExtendedWebElement publishPrelimCloseToT3;

	@FindBy(xpath = "//div[@id='displayTimer']//i")
	public ExtendedWebElement CurrentTime;

	@FindBy(xpath = "//span[contains(text(),'Add/Edit Index')]")
	public ExtendedWebElement addEditIndex;

	@FindBy(xpath = "//span[contains(text(),'Stop Index Calculation')]")
	public ExtendedWebElement addStopCalculation;

	@FindBy(xpath = "//input[@title='Download Index Data']")
	public ExtendedWebElement downloadIndexData;

	@FindBy(xpath = "//div[@id='indexTable1_wrapper']//div[3]//div//div//table//thead//tr//th[6]")
	public ExtendedWebElement indexColumn;

	@FindBy(xpath = "//span[text()='Correct Statistics']")
	public ExtendedWebElement correctStatistics;

	@FindBy(xpath = "//select[@data-handler='selectMonth']")
	public ExtendedWebElement datePickerMonth;

	@FindBy(xpath = "//select[@data-handler='selectYear']")
	public ExtendedWebElement datePickerYear;

	@FindBy(xpath = "//td[@data-handler='selectDay']")
	public List<ExtendedWebElement> calendarDaysList;

	@FindBy(xpath = "//input[@value='excludeIndex']")
	public ExtendedWebElement excludeIndexRadioButton;

	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public ExtendedWebElement alertSearch;

	@FindBy(xpath = "//div[@id='alertTable_filter']//label//input")
	public WebElement alertSearchScroll;

	@FindBy(xpath = "//th[text()='RIC']")
	public ExtendedWebElement ricColumnSortButton;

	@FindBy(xpath = "//table[@class='display dataTable']//tbody/tr/td[4]")
	public List<ExtendedWebElement> ricColumn;

	@FindBy(xpath = "//table[@class='display dataTable']//tbody/tr/td[5]")
	public List<ExtendedWebElement> idcColumn;

	@FindBy(xpath = "//table[@class='display dataTable']//tbody/tr/td[6]")
	public List<ExtendedWebElement> nameColumn;

	@FindBy(xpath = "//table[@class='display dataTable']//thead//tr")
	public ExtendedWebElement indexColumns;

	@FindBy(xpath = "//input[@value='correctValue']")
	public ExtendedWebElement correctValue;

	@FindBy(id = "dailyHighValueId")
	public ExtendedWebElement dailyHighValue;

	@FindBy(id = "dailyLowValueId")
	public ExtendedWebElement dailyLowValue;

	@FindBy(id = "openValueId")
	public ExtendedWebElement openValue;

	@FindBy(xpath = "//table[contains(@id,'indexTable')]//tr[1]//td[7]")
	public ExtendedWebElement HPIColumnFirstValue;

	@FindBy(xpath = "//table[@class='display dataTable']//thead//tr//th[7]")
	public ExtendedWebElement HPIColumnName;

	@FindBy(xpath = "//table[@class='display dataTable']//tr/td[text()='Yes']")
	public List<ExtendedWebElement> HPIColumnValuesYes;

	@FindBy(xpath = "//table[@class='display dataTable']//tr/td[7]")
	public List<ExtendedWebElement> HPIColumnValues;

	@FindBy(xpath = "//div[@title='maximize window']")
	public ExtendedWebElement maximizeWindow;

	@FindBy(xpath = "//span[text()='Publish Suspect']")
	private ExtendedWebElement publishSuspect;

	@FindBy(xpath = "//span[text()='Withdraw Suspect']")
	private ExtendedWebElement withdrawSuspect;

	@FindBy(xpath = "//table[contains(@id,'indexTable')]//tr//td[7]")
	private ExtendedWebElement userSuspectCoulmn;

	@FindBy(xpath = "//table[contains(@id,'indexTable')]//tr//td[8]")
	private ExtendedWebElement dataQualityColumn;

	@FindBy(xpath = "(//input[@title='Customize Data View'])[2]")
	private ExtendedWebElement customizeView;

	@FindBy(xpath = "(//span[text()='Uncheck all'])[2]")
	private ExtendedWebElement uncheckAll;

	@FindBy(xpath = "//span[text()='Suspect']/preceding-sibling::input")
	private WebElement suspectcheckBox;

	@FindBy(xpath = "(//span[text()='OK'])[2]")
	private ExtendedWebElement customizeOk;

	@FindBy(xpath = "//input[@title='Download Alert Data']")
	private ExtendedWebElement downloadAlertData;

	@FindBy(xpath = "//button[text()='Download Alerts']")
	private ExtendedWebElement downloadAlerts;

	public void clickDashboardLink(String dasboardLinkType) {
		try {
			dasboardLink = dasboardLinkType;
			switchToFrame(dashboardFrameId);
			switch (dasboardLink) {
			case "close":
				if (initialClosecount > 0)
					dashboardClose.click();
				else
					Assert.fail("Warning Message - None of the index are in closed state");
				break;

			case "open":
				if (initialOpencount > 0)
					dashboardOpen.click();
				else
					Assert.fail("None of the index are in open state");
				break;

			case "prelim close":
				if (initialPrelimClosecount > 0)
					dashboardPrelimClose.click();
				else
					Assert.fail("Warning Message - None of the index are in prelim closed state");
				break;

			case "prelim close older date":
				if (initialPrelimClosecount > 0)
					dashboardPrelimClose.click();
				else
					Assert.fail("Warning Message - None of the index are in prelim closed state");
				break;

			case "prelim close T3 open":
				if (initialOpencount > 0)
					dashboardOpen.click();
				else
					Assert.fail("Warning Message - None of the index are in open state");
				break;

			case "suspect":
				dashboardSuspect.click();
				break;

			default:
				break;
			}
			switchOutOfFrame();
			switchToFrame(researchFrameID);
			waitForTablefirstRow(indexTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Dashboard states link-" + e.getMessage());
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

	public void waitForTablefirstRow(String tableID) {
		ExtendedWebElement ele = findExtendedWebElement(
				By.xpath("//table[contains(@id,'" + tableID + "')]/tbody/tr[1]"));
		ele.isElementPresent();
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

	public void deletCookies() {
		try {
			Set<Cookie> allCookies = driver.manage().getCookies();
			for (Cookie cookie : allCookies) {
				driver.manage().deleteCookieNamed(cookie.getName());
			}
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to delete cookies- " + e.getMessage());
		}

	}

	public void clickIndex() {
		try {
			index.isElementPresent();
			index.click();
			waitForTablefirstRow(indexTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Index tab-" + e.getMessage());
		}
	}

	public void selectIndextype(String indexType, String indexValue) {
		try {
			indexTypeValue = indexValue;
			indexDropdown.select(indexType);
			waitForTablefirstRow(indexTableID);
			indexDropdown.pause(2);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to select index type dropdown-" + e.getMessage());
		}
	}

	public void selectIndextypeDropDown(String indexType) {
		try {
			indexDropdown.select(indexType);
			indexDropdown.pause(3);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]/tbody/tr[1]/td"));
			ele.isElementPresent();
			if (ele.getAttribute("class").contains("empty")) {
				Assert.fail("Warning Message - No indices were listed for the required type");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("No indices were listed" + e.getMessage());
		}
	}

	public void serchIndexData(String indexDataValue) {
		try {
			searchDataValue = indexDataValue;
			indexSearchTextBox.type(searchDataValue);
			waitForTablefirstRow(indexTableID);
			indexSearchTextBox.pause(2);
			PageFactory.initElements(driver, this);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search index data-" + e.getMessage());
		}
	}

	public void scrollToDown() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// alertSearch.scrollTo();
			js.executeScript("arguments[0].scrollIntoView(true);", alertSearchScroll);
			alertSearch.pause(1);
			PageFactory.initElements(driver, this);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to scroll to alert search box-" + e.getMessage());
		}
	}

	public void rightclickStaleException() {
		int Counter = 0;
		PageFactory.initElements(driver, this);
		try {
			do {
				if (indexData.getElement().isEnabled() && indexData.getElement().isDisplayed()
						&& indexData.isVisible()) {
					indexData.pause(1);
					Counter = Counter + 1;
					indexData.click();
					indexData.pause(1);
					indexData.rightClick();
				}
			} while (Counter == 0);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			Assert.fail("Failed due to stale element for right click action-" + e.getMessage());
		}

	}

	public void performRightClickAction() {
		try {
			//scrollToDown();
			if (indexTypeValue.contains("Fixed Income")) {
				if (fetchTableColumnValues.get("status").equalsIgnoreCase("open")) {

				} else {
					serchIndexData(R.TESTDATA.get("FixedIncomeIndexValue"));
					getIndicesCoulmnValue();
					if (fetchTableColumnValues.get("status").equalsIgnoreCase("open")) {
					} else
						Assert.fail(
								"Warning Message - Both Fixed Income indexes of IST and EST data was not in open state");
				}
				rightclickStaleException();
			} else {
				if (fetchTableColumnValues.get("status").equalsIgnoreCase("open")) {
					rightclickStaleException();
				} else
					Assert.fail("Warning Message - The index status was not in open state");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perfrom right click action on selected index-" + e.getMessage());
		}
	}

	public void performRightClickNoTestData() {
		try {
			//scrollToDown();
			rightclickStaleException();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Warning Message - Unable to perfrom right click action on selected index-" + e.getMessage());
		}
	}

	public void showDetails(String showType) throws InterruptedException, IOException {
		try {
			showTypeValue = showType;
			ExtendedWebElement totalCountValue;
			String totalCountText;
			if (showTypeValue.equalsIgnoreCase("constituent")) {
				showConstituentDetails.isElementPresent();
				showConstituentDetails.click();
				totalCountValue = findExtendedWebElement(
						By.xpath("//div[contains(@id,'" + icdID + "')]//span[@class='statusBarSpan']"));
				totalCountValue.isElementPresent();
				waitForTablefirstRow(icdID);
			} else {
				showExchange.isElementPresent();
				showExchange.click();
				totalCountValue = findExtendedWebElement(
						By.xpath("//div[contains(@id,'" + exchangeID + "')]//span[@class='statusBarSpan']"));
				totalCountValue.isElementPresent();
				waitForTablefirstRow(exchangeID);
			}
			totalCountText = totalCountValue.getText().substring(0, totalCountValue.getText().indexOf("("));
			IndicesSQLStatements.getTotalContituentValue();
			LOGGER.info("UI Show Details value - " + totalCountText.replaceAll("[^0-9]", ""));
			LOGGER.info("DB Show Details value - " + IndicesSQLStatements.totalCount);
			Assert.assertEquals(totalCountText.replaceAll("[^0-9]", ""), IndicesSQLStatements.totalCount,
					"UI Show details and DB value are not matching");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to perform action on show details and verify value-" + e.getMessage());
		}
	}

	public void switchToFrame(String frameID) {
		driver.switchTo().frame(frameID);
	}

	public void switchOutOfFrame() {
		driver.switchTo().defaultContent();
	}

	public void clickAdvanced() {
		try {
			advanced.isElementPresent();
			advanced.hover();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on advanced option-" + e.getMessage());
		}
	}

	public void clickRefreshFromT3() {
		try {
			refreshFromT3.isElementPresent();
			refreshFromT3.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on refreshfromT3 option-" + e.getMessage());
		}
	}

	public void clickPublishToT3() {
		try {
			publishOpHiLoToT3.isElementPresent();
			publishOpHiLoToT3.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on publish Op/Hi/Lo option-" + e.getMessage());
		}
	}

	public void clickPublishCloseFromT3() {
		try {
			publishCloseFromT3.isElementPresent();
			publishCloseFromT3.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on publish close from T3-" + e.getMessage());
		}
	}

	public void clickPublishCloseFromGrip() {
		try {
			publishCloseFromGrip.isElementPresent();
			publishCloseFromGrip.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on publish close from Grip-" + e.getMessage());
		}
	}

	public void clickPrelimCloseT3() {
		try {
			publishPrelimCloseToT3.isElementPresent();
			publishPrelimCloseToT3.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on prelim close T3 option-" + e.getMessage());
		}
	}

	public void clicksaveIndexOk() {
		try {
			indexHoldOk.isElementPresent();
			indexHoldOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on save index ok-" + e.getMessage());
		}
	}

	public void clickRefreshT3Ok() {
		try {
			refreshT3Ok.isElementPresent();
			refreshT3Ok.click();
			refreshT3Ok.pause(20);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Refrsh T3 ok-" + e.getMessage());
		}
	}

	public void clickReleaseOk() {
		try {
			releaseIndexOk.isElementPresent();
			releaseIndexOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on release index ok-" + e.getMessage());
		}
	}

	public void advancedAddEdit() throws InterruptedException, IOException {
		try {
			addEditIndex.isElementPresent();
			addEditIndex.click();
			switchOutOfFrame();
			switchToFrame(dataMaintainFrameID);
			rTMIndex.isElementPresent();
			baseLineErrorThresholdUp.isElementPresent();
			baseLineErrorThresholdUp.pause(2);
			LOGGER.info("Index ID value - " + searchDataValue);
			LOGGER.info("Target Index ID value - " + targetIndicesIndexID.getText());
			Assert.assertEquals(searchDataValue, targetIndicesIndexID.getText(),
					"Index Id and Target Index Id value are not matching");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to add edit advanced option and verify in RT_M index page-" + e.getMessage());
		}
	}

	public void getPercentValuesDatamaiantain() {
		try {
			baseLineUp = baseLineErrorThresholdUp.getAttribute("value");
			baseLineDown = baseLineErrorThresholdDown.getAttribute("value");
			prevTickUp = prevTickErrorThresholdUp.getAttribute("value");
			prevTickDown = prevTickErrorThresholdDown.getAttribute("value");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get up down percent values Data maintain page-" + e.getMessage());
		}
	}

	public void verifyAddEditDataMaintain() {
		try {
			IndicesSQLStatements.getAdvancedAddEdit();
			LOGGER.info("UI Base Line UP Value-" + baseLineUp + " and " + "DB Base Line UP Value-"
					+ IndicesSQLStatements.baseLineUp);
			Assert.assertEquals(IndicesSQLStatements.baseLineUp, baseLineUp,
					"UI and DB Base line UP values are mismatching");
			LOGGER.info("UI Base Line Down Value-" + baseLineDown + " and " + "DB Base Line Down Value-"
					+ IndicesSQLStatements.baseLineDown);
			Assert.assertEquals(IndicesSQLStatements.baseLineDown, baseLineDown,
					"UI and DB Base line DOWN values are mismatching");
			LOGGER.info("UI Previuos Tick UP Value-" + prevTickUp + " and " + "DB Previuos Tick UP Value-"
					+ IndicesSQLStatements.prevTickUp);
			Assert.assertEquals(IndicesSQLStatements.prevTickUp, prevTickUp,
					"UI and DB previuos Tick UP values are mismatching");
			LOGGER.info("UI Previuos Tick DOWN  Value-" + prevTickDown + " and " + "DB Previuos Tick DOWN Value-"
					+ IndicesSQLStatements.prevTickDown);
			Assert.assertEquals(IndicesSQLStatements.prevTickDown, prevTickDown,
					"UI and DB previuos Tick DOWN values are mismatching");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify values in Data maintain page-" + e.getMessage());
		}
	}

	public void modifypercentValues(String dataUpdate, String thresold) {
		try {
			String percentValues;
			if (dataUpdate.equalsIgnoreCase("update")) {
				if (thresold.equalsIgnoreCase("non zero"))
					percentValues = "0.00000001";
				else
					percentValues = "0.000";
				baseLineErrorThresholdUp.type(percentValues);
				baseLineErrorThresholdDown.type(percentValues);
				prevTickErrorThresholdUp.type(percentValues);
				prevTickErrorThresholdDown.type(percentValues);
			} else {
				baseLineErrorThresholdUp.type(baseLineUp);
				baseLineErrorThresholdDown.type(baseLineDown);
				prevTickErrorThresholdUp.type(prevTickUp);
				prevTickErrorThresholdDown.type(prevTickDown);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to modify percecnt values in Data maintain page-" + e.getMessage());
		}
	}

	public void saveModifiedDetails() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			targetIndicesTable.isElementPresent();
			targetIndicesTable.click();
			js.executeScript("arguments[0].scrollIntoView(true);", targetIndicesSave2);
			targetIndicesSave.click();
			clicksaveIndexOk();
			refreshMViewTab.click();
			refreshMViewbutton.isElementPresent();
			refreshMViewbutton.click();
			clicksaveIndexOk();
			rTMIndex.click();
			baseLineErrorThresholdUp.isElementPresent();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to save the modified percecnt values in Data maintain page-" + e.getMessage());
		}
	}

	public void verifyBaselineMessages(String dataUpdate) {
		try {
			refreshFromT3.pause(40);
			getUniqueID();
			if (dataUpdate.equalsIgnoreCase("update")) {
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Threshold Breach", currenTime,
						"will be held");
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Auto Hold"),
						"Expected index Status were not changed in index table");
				getDriver().navigate().refresh();
			} else {
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Auto-Release", currenTime,
						"Auto-released at");
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Ok"),
						"Expected index Status were not changed in index table");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify baseline alert messages-" + e.getMessage());
		}
	}

	public void verifyHoldChangeStatus() {
		try {
			refreshFromT3.pause(50);
			getIndicesCoulmnValue();
			Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Ok"),
					"Expected index Status were not changed in index table");
			getDriver().navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to change index status-" + e.getMessage());
		}
	}

	public void verifyNoChangeStatus() {
		try {
			refreshFromT3.pause(30);
			getIndicesCoulmnValue();
			Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Manual Hold"),
					"Expected index Status were not changed in index table");
			getDriver().navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to change index status-" + e.getMessage());
		}
	}

	public void verifyReleaseAlertMessages() {
		try {
			releaseOk.isElementPresent();
			releaseOk.click();
			releaseOk.pause(25);
			getUniqueID();
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Manual Release", currenTime, "Manually released");
			getIndicesCoulmnValue();
			Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Auto Hold"),
					"Expected index Status were not changed in index table");
			getDriver().navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify release alert messages-" + e.getMessage());
		}
	}

	public void verifyNotRelaseAlertMessage() {
		try {
			String notAbleToRelease = "Action was not taken";
			releaseOk.pause(25);
			getUniqueID();
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Manual Release", currenTime, notAbleToRelease);
			IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
			Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(notAbleToRelease));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify not release alert messages-" + e.getMessage());
		}
	}

	public void getTimeInDashboard() {
		try {
			dashboardTab.isElementPresent();
			switchToFrame(dashboardFrameId);
			initialNoTicksGripCount = Integer.parseInt(dashboardNoTicksGrip.getText());
			initialManualHoldCount = Integer.parseInt(dashboardManualHold.getText());
			initialClosecount = Integer.parseInt(dashboardClose.getText());
			initialOpencount = Integer.parseInt(dashboardOpen.getText());
			initialPrelimClosecount = Integer.parseInt(dashboardPrelimClose.getText());
			initialWarning = Integer.parseInt(dashboardWarning.getText());
			initialSuspectCount = Integer.parseInt(dashboardSuspect.getText());
			initialAutoholdCount = Integer.parseInt(dashboardAutoHold.getText());
			switchOutOfFrame();
			currenTime = CurrentTime.getText();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get time-" + e.getMessage());
		}
	}

	public void verifyNoTicksGrip() {
		try {
			clickDashboard();
			dashboardNoTicksGrip.isElementPresent();
			dashboardNoTicksGrip.pause(45);
			finalNoTicksGripCount = Integer.parseInt(dashboardNoTicksGrip.getText());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify no ticks grip count-" + e.getMessage());
		}

	}

	public void stopIndexCalcualtion(String indexType) throws InterruptedException, IOException {
		try {
			String indexJosStopMessage = "Index Calculation Job Stopped";
			addStopCalculation.isElementPresent();
			addStopCalculation.click();
			clicksaveIndexOk();
			addStopCalculation.pause(15);
			if (indexType.equalsIgnoreCase("Non HPI Index")) {
				searchAlert(indexJosStopMessage);
				VerifyAlertsMessages(indexJosStopMessage, "Index Maintenance", currenTime,
						"Index calculation job stopped successfully");
			} else {
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "System Monitor", currenTime,
						"not ticking in GRIP");
			}
			getDriver().navigate().refresh();
			verifyNoTicksGrip();
			Assert.assertEquals(initialNoTicksGripCount + 1, finalNoTicksGripCount);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to stop calcuation for the index and verify it-" + e.getMessage());
		}
	}

	public void revertIndexToResolved() {
		try {
			verifyNoTicksGrip();
			Assert.assertEquals(initialNoTicksGripCount, finalNoTicksGripCount);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("The Index value was not in resolved state" + e.getMessage());
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
			for (int i = 1; i <= alertTableSize; i++) {
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
				Assert.fail("Expected alert message is not displayed in alert table within the time provided");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the alert messages-" + e.getMessage());
		}
	}

	public void VerifyAlertsMessagesNoTicks(String subjectInput, String alertTypeInput, String timeValue,
			String descriptionInput) throws Exception {
		try {
			boolean alertCondition = false;
			for (int i = 1; i <= alertTableSize; i++) {
				String alertType = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[2]")).getText();
				String subject = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[4]")).getText();
				String description = findExtendedWebElement(
						By.xpath("//table[@id='alertTable']//tbody//tr[" + i + "]//td[6]")).getText();

				if (alertType.equalsIgnoreCase(alertTypeInput) && subject.contains(subjectInput)
						&& description.contains(descriptionInput)) {
					Assert.assertTrue(alertType.equalsIgnoreCase(alertTypeInput));
					Assert.assertTrue(subject.contains(subjectInput));
					Assert.assertTrue(description.contains(descriptionInput));
					alertCondition = true;
					break;
				}
			}
			if (alertCondition) {
			} else
				Assert.fail("Expected No Ticks alert message is not displayed in alert tableat this time");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the alert messages-" + e.getMessage());
		}
	}

	public void verifyStatusMessageResolved(String indexType) {
		try {
			refreshFromT3.pause(20);
			getUniqueID();
			if (indexType.equalsIgnoreCase("Non HPI Index"))
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Pending New Day", currenTime,
						"Index has been loaded");
			else
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "System Monitor", currenTime,
						"ticking in normally in GRIP");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the alerts after refresh from T3-" + e.getMessage());
		}
	}

	public void getIndicesCoulmnValue() {
		try {
			ExtendedWebElement currentPrice = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[13]"));
			fetchTableColumnValues.put("currentPriceValue", currentPrice.getText());
			ExtendedWebElement prevValue = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[14]"));
			fetchTableColumnValues.put("previuosValue", prevValue.getText());
			ExtendedWebElement openValueElementMand = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[16]"));
			fetchTableColumnValues.put("openValue", openValueElementMand.getText());
			ExtendedWebElement indexNameElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[6]"));
			fetchTableColumnValues.put("name", indexNameElement.getText());
			ExtendedWebElement calcSatusElement = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[3]"));
			fetchTableColumnValues.put("calcStatusValue", calcSatusElement.getText());
			ExtendedWebElement startTimeUTC = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[11]"));
			fetchTableColumnValues.put("startTimeUTCValue", startTimeUTC.getText());
			ExtendedWebElement statusele = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[2]"));
			fetchTableColumnValues.put("status", statusele.getText());
			ExtendedWebElement publish = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[8]"));
			fetchTableColumnValues.put("publishValue", publish.getText());
			ExtendedWebElement dailyHigh = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[17]"));
			fetchTableColumnValues.put("dailyHighValue", dailyHigh.getText());
			ExtendedWebElement dailyLow = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[18]"));
			fetchTableColumnValues.put("dailyLowValue", dailyLow.getText());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get column values from indices table" + e.getMessage());
		}
	}

	public void clickHold() {
		try {
			indexHold.isElementPresent();
			indexHold.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on hold-" + e.getMessage());
		}
	}

	public void verifyCurrentPriceValue(String holdType) {
		try {
			holdIndexCurrentValue.isElementPresent();
			Assert.assertTrue(manualReleaseRadioButton.getAttribute("checked").equalsIgnoreCase("true"));
			String[] currentPrice = fetchTableColumnValues.get("currentPriceValue").split("\\.");
			String[] holdIndexcurrent = holdIndexCurrentValue.getAttribute("value").split("\\.");
			if (fetchTableColumnValues.get("currentPriceValue") != null) {
				if (currentPrice[0].equalsIgnoreCase(holdIndexcurrent[0]))
					Assert.assertEquals(currentPrice[0], holdIndexcurrent[0],
							"Current price value and index hold value does not match");
				else
					LOGGER.info("Warning message - " + "Current price value and index hold value does not match");
			} else {
				Assert.assertEquals(fetchTableColumnValues.get("openValue"),
						holdIndexCurrentValue.getAttribute("value"));
			}
			if (holdType.equalsIgnoreCase("Auto Hold"))
				autoBasedRadioButton.click();
			else {
			}
			indexHoldOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify current price value-" + e.getMessage());
		}
	}

	public void verifyHoldAlert(String holdType) {
		try {
			String releaseHoldMessage = "Manually released";
			indexHoldOk.pause(45);

			if (holdType.equalsIgnoreCase("manualHold")) {
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Manual Hold", currenTime, manualHoldMessage);
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase(manulaHoldValue),
						"Expected index Status were not changed in index table");
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(manualHoldMessage));
				IndicesSQLStatements.getIndexCalcStatus();
				Assert.assertEquals(IndicesSQLStatements.calcStatus, manulaHoldValue);
			} else {
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Manual Release", currenTime,
						releaseHoldMessage);
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Ok"),
						"Expected index Status were not changed in index table");
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(releaseHoldMessage));
				IndicesSQLStatements.getIndexCalcStatus();
				Assert.assertTrue(IndicesSQLStatements.calcStatus.equalsIgnoreCase("Ok"));
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

	public void verifyAutoHoldAlertMessages() {
		try {
			String autoReleaseMessage = "Auto-released at";
			indexHoldOk.pause(20);
			getUniqueID2();
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), manulaHoldValue, currenTime, manualHoldMessage);
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Auto-Release", currenTime, autoReleaseMessage);
			IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
			Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(autoReleaseMessage));
			IndicesSQLStatements.getIndexMessageStatus(alertUniqueID2);
			Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(manualHoldMessage));
			IndicesSQLStatements.getIndexCalcStatus();
			Assert.assertTrue(IndicesSQLStatements.calcStatus.equalsIgnoreCase("Ok"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the auto based/release alert-" + e.getMessage());
		}
	}

	public void releaseIndex() {
		try {
			if (fetchTableColumnValues.get("calcStatusValue").contains("Hold")) {
				release.isElementPresent();
				release.click();
			} else {
				Assert.fail("Warning Message - The index calc. status was in hold state");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to release the holded index-" + e.getMessage());
		}
	}

	public void releaseIndexWithoutHeld() {
		try {
			release.isElementPresent();
			release.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to release the unholded index -" + e.getMessage());
		}
	}

	public void feedUTCTime() {
		try {
			String startUTCTime = null;
			String endUTCTime = null;
			switch (IndicesPage.indexTypeValue) {
			case "Equity Indices":
				if (correctionTypeValue.equalsIgnoreCase("withintime")) {
					startUTCTime = "02:00:00";
					endUTCTime = "19:20:00";
				} else {
					startUTCTime = "12:00:00";
					endUTCTime = "21:20:00";
				}
				break;

			case "Commodities Indices":
				if (correctionTypeValue.equalsIgnoreCase("withintime")) {
					startUTCTime = "23:20:01";
					endUTCTime = "18:10:01";
				} else {
					startUTCTime = "21:00:00";
					endUTCTime = "20:20:00";
				}
				break;

			case "MultiAsset Indices":
				if (correctionTypeValue.equalsIgnoreCase("withintime")) {
					startUTCTime = "23:00:02";
					endUTCTime = "19:20:02";
				} else {
					startUTCTime = "21:00:02";
					endUTCTime = "21:20:02";
				}
				break;

			case "Fixed Income":
				if (correctionTypeValue.equalsIgnoreCase("withintime")) {
					startUTCTime = "22:30:00";
					endUTCTime = "05:00:00";
				} else {
					startUTCTime = "20:30:00";
					endUTCTime = "07:00:00";
				}
				break;

			default:
				break;
			}
			startTimeUTC.type(startUTCTime);
			endTimeUTC.type(endUTCTime);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to feed UTC start and end time-" + e.getMessage());
		}
	}

	public void getUniqueID() {
		try {
			alertSearch.type(fetchTableColumnValues.get("name"));
			alertSearch.pause(30);
			alertTableFirstRow.scrollTo();
			alertUniqueID = alertTableFirstRow.getAttribute("id");
			ExtendedWebElement alertStatusBar = findExtendedWebElement(
					By.xpath("//div[contains(@id,'alertTable')]//span[@class='alertstatusBarSpan']"));
			alertTableSize = Integer.parseInt(alertStatusBar.getText().replaceAll("[^0-9]", ""));
			System.out.println("alert table row list size- " + alertTableSize);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get unique ID-" + e.getMessage());
		}
	}

	public void getUniqueID2() {
		try {
			alertSearch.type(fetchTableColumnValues.get("name"));
			alertSearch.pause(30);
			alertTableSecondRow.scrollTo();
			alertUniqueID = alertTableFirstRow.getAttribute("id");
			alertUniqueID2 = alertTableSecondRow.getAttribute("id");
			ExtendedWebElement alertStatusBar = findExtendedWebElement(
					By.xpath("//div[contains(@id,'alertTable')]//span[@class='alertstatusBarSpan']"));
			alertTableSize = Integer.parseInt(alertStatusBar.getText().replaceAll("[^0-9]", ""));
			System.out.println("alert table row list" + alertTableSize);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get unique ID2-" + e.getMessage());
		}
	}

	public void searchAlert(String message) {
		try {
			alertSearch.type(message);
			alertSearch.pause(30);
			alertTableFirstRow.scrollTo();
			alertUniqueID = alertTableFirstRow.getAttribute("id");
			ExtendedWebElement alertStatusBar = findExtendedWebElement(
					By.xpath("//div[contains(@id,'alertTable')]//span[@class='alertstatusBarSpan']"));
			alertTableSize = Integer.parseInt(alertStatusBar.getText().replaceAll("[^0-9]", ""));
			System.out.println("alert table row list" + alertTableSize);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to get unique ID-" + e.getMessage());
		}
	}

	public void correctStatistics(String correctionType) {
		try {
			String withinTimeMessage = "Index statistics have been recalculated";
			String withOutTimeMessage = "Index statistics cannot be corrected";
			correctionTypeValue = correctionType;
			correctStatistics.isElementPresent();
			correctStatistics.click();
			excludeIndexRadioButton.isElementPresent();
			excludeIndexRadioButton.click();
			feedUTCTime();
			indexHoldOk.click();
			indexHoldOk.pause(20);
			getUniqueID();

			if (correctionTypeValue.equalsIgnoreCase("withintime")) {
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Index Values Correction", currenTime,
						"Index statistics have been recalculated");
				IndicesSQLStatements.getCorrectstatistics();
				Assert.assertTrue(IndicesSQLStatements.correctionMessageValue.contains(withinTimeMessage));
			} else {
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Index Values Correction", currenTime,
						"Index statistics cannot be corrected");
				IndicesSQLStatements.getCorrectstatistics();
				Assert.assertTrue(IndicesSQLStatements.correctionMessageValue.contains(withOutTimeMessage));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify current statistics value-" + e.getMessage());
		}
	}

	public void refreshFromGrip() {
		try {
			refreshFromGrip.isElementPresent();
			refreshFromGrip.click();
			refreshFromGrip.pause(15);
			getUniqueID();
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Intra-Day Change", currenTime,
					"Index has been reloaded from GRIP");
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to refresh value from GRIP-" + e.getMessage());
		}
	}

	public void verifyRefreshGripMessage() {
		try {
			refreshFromGrip.pause(45);
			ExtendedWebElement startTimeUTC = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[11]"));
			Assert.assertEquals(fetchTableColumnValues.get("startTimeUTCValue").substring(0, 9),
					startTimeUTC.getText().substring(0, 9));
			getUniqueID();
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Pending New Day", currenTime,
					"Index has been loaded");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the alerts after refresh from T3-" + e.getMessage());
		}
	}

	public static String getDate(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		return df.format(date);
	}

	public void dateSelect(int dateValue) {
		for (int i = 0; i < calendarDaysList.size(); i++) {
			if (calendarDaysList.get(i).getText().equalsIgnoreCase(Integer.toString(dateValue))) {
				calendarDaysList.get(i).click();
				break;
			}
		}
	}

	public void clickDatePicker(String datePickerID) {
		try {
			ExtendedWebElement datePicker = findExtendedWebElement(
					By.xpath("//input[contains(@id,'" + datePickerID + "')]/following::img[position()=1]"));
			datePicker.isElementPresent();
			datePicker.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click date picker icon-" + e.getMessage());
		}
	}

	public void feedPublishDate() {
		try {
			int olderDate = 0;
			if (dasboardLink.equalsIgnoreCase("close"))
				clickDatePicker(publishOHLT3DateID);
			else if (dasboardLink.equalsIgnoreCase("prelim close")
					|| dasboardLink.equalsIgnoreCase("prelim close older date"))
				clickDatePicker(publishPrelimToT3DateID);
			datePickerMonth.isElementPresent();
			datePickerMonth.select(getDate("MMM"));
			datePickerYear.select(getDate("YYYY"));

			if (dasboardLink.equalsIgnoreCase("close") || dasboardLink.equalsIgnoreCase("prelim close"))
				dateSelect(Integer.parseInt(getDate("dd")));
			else {
				if (getDate("E").equalsIgnoreCase("mon") || getDate("E").equalsIgnoreCase("sun")) {
					olderDate = Integer.parseInt(getDate("dd")) - 3;
				} else {
					olderDate = Integer.parseInt(getDate("dd")) - 1;
				}
				dateSelect(olderDate);
			}
			indexHoldOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to provide date in publish Op/Hi/Lo window" + e.getMessage());
		}
	}

	public void verifyPublishT3Messages() {
		try {
			String publishT3Message = "Successfully published";
			indexHoldOk.pause(20);
			getUniqueID();
			String description = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]"))
					.getText();
			if (description.contains(publishT3Message)) {
			} else {
				Assert.fail("Warning Message - The index cannot be published from T3");
			}
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "EOD Value", currenTime, publishT3Message);
			IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
			Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(publishT3Message));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the published T3 messges in alert table-" + e.getMessage());
		}
	}

	public void verifyPublishcloseFromT3Messages() {
		try {
			if (dasboardLink.equalsIgnoreCase("prelim close")) {
				String[] currentPrice;
				String[] publish;
				String publishT3Message = "was successfully captured from T3";
				publishCloseFromT3.pause(30);
				getUniqueID();
				String description = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]"))
						.getText();
				if (description.contains(publishT3Message)) {
				} else {
					Assert.fail("Warning Message - The index cannot be publish close from T3");
				}
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "EOD Value", currenTime, publishT3Message);
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(publishT3Message));
				IndicesSQLStatements.getIndexNameStatus();
				Assert.assertTrue(IndicesSQLStatements.indexNamestatus.equalsIgnoreCase("close"));
				getIndicesCoulmnValue();
				currentPrice = fetchTableColumnValues.get("currentPriceValue").split("\\.");
				publish = fetchTableColumnValues.get("publishValue").split("\\.");
				LOGGER.info("Current price value-" + fetchTableColumnValues.get("currentPriceValue"));
				LOGGER.info("Publish value-" + fetchTableColumnValues.get("publishValue"));
				Assert.assertEquals(currentPrice[0], publish[0]);
			} else if (dasboardLink.equalsIgnoreCase("open")) {
				String publishT3Message = "Index cannot be closed because the close time has not been reached yet";
				publishCloseFromT3.pause(30);
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "EOD Value", currenTime, publishT3Message);
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(publishT3Message));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the published close from T3 messges in alert table-" + e.getMessage());
		}
	}

	public void verifyPublishCloseFromGripMessages() {
		try {
			String publishgripMessage = "was successfully captured from GRIP";
			String publishGripFailureMessage = "Index cannot be closed because the close time has not been reached yet";
			String publichcloseGripValue[] = publishcloseFromGripTextBox.getAttribute("value").split("\\.");
			if (dasboardLink.equalsIgnoreCase("prelim close")) {
				clicksaveIndexOk();
				publishCloseFromGrip.pause(30);
				getUniqueID();
				String description = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]"))
						.getText();
				if (description.contains(publishgripMessage)) {
				} else {
					Assert.fail("Warning Message - The index cannot be publish close from Grip");
				}

				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "EOD Value", currenTime,
						publichcloseGripValue[0]);
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(publishgripMessage));
				IndicesSQLStatements.getIndexNameStatus();
				Assert.assertTrue(IndicesSQLStatements.indexNamestatus.equalsIgnoreCase("close"));
				getDriver().navigate().refresh();
				headerTitle.isElementPresent();
			} else if (dasboardLink.equalsIgnoreCase("open")) {
				clicksaveIndexOk();
				publishCloseFromGrip.pause(30);
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "EOD Value", currenTime,
						publishGripFailureMessage);
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(publishGripFailureMessage));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the published close from Grip messges in alert table-" + e.getMessage());
		}
	}

	public void holdReleaseMultipleIndices(String actionType, String indexType) {
		try {
			Actions builder = new Actions(getDriver());
			if (actionType.equalsIgnoreCase("hold")) {
				waitForTablefirstRow(indexTableID);
				selectIndextype(indexType, "Indices");
				maximizeWindow.click();
				maximizeWindow.pause(4);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				clickHold();
				indexHoldOk.isElementPresent();
				// Assert.assertTrue(autoBasedRadioButton.isElementNotPresent(4), "Auto based
				// hold option is visible");
				clicksaveIndexOk();
				indexHold.pause(25);
				getIndicesCoulmnValue();
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), manulaHoldValue, currenTime,
						manualHoldMessage);
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase(manulaHoldValue),
						"Expected index Status were not changed in index table");
			} else {
				builder.contextClick().build().perform();
				release.isElementPresent();
				release.click();
				releaseOk.isElementPresent();
				releaseOk.click();
				indexHold.pause(25);
				getIndicesCoulmnValue();
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Manual Release", currenTime,
						"Manually released");
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Ok"),
						"Expected index Status were not changed in index table");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release multiple indices-" + e.getMessage());
		}
	}

	public void holdReleaseIndiceMultipleTime(String actionType, String indexType) {
		try {
			int loopcount = 0;
			if (actionType.equalsIgnoreCase("holdMultiple")) {
				for (int i = 0; i < 3; i++) {
					getIndicesCoulmnValue();
					//scrollToDown();
					performRightClickAction();
					clickHold();
					clicksaveIndexOk();
					if (loopcount == 0)
						holdIndexCurrentValue.pause(50);
					String[] currentPrice = fetchTableColumnValues.get("currentPriceValue").split("\\.");
					String[] publishValue = fetchTableColumnValues.get("publishValue").split("\\.");
					Assert.assertEquals(currentPrice[0], publishValue[0]);
					getIndicesCoulmnValue();
					Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase(manulaHoldValue),
							"Expected index Status were not changed in index table");
					loopcount++;
				}
			} else {
				//scrollToDown();
				performRightClickAction();
				releaseIndex();
				releaseIndexOk.isElementPresent();
				releaseIndexOk.click();
				releaseOk.pause(50);
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Ok"),
						"Expected index Status were not changed in index table");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release indice for multiple times-" + e.getMessage());
		}

	}

	public String IndexDivisorValue() {
		String initilaDivisorValue = null;
		try {
			ExtendedWebElement indexDivisor = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]//tr//td[19]"));
			initilaDivisorValue = indexDivisor.getText();
			LOGGER.info("Initial Divisor Value-" + initilaDivisorValue);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to locate index divisor value-" + e.getMessage());
		}
		return initilaDivisorValue;
	}

	public void updateIndexDivisorValue() {
		try {
			String initilaDivisorValue = IndexDivisorValue();
			String updateDivisorValue = "3555554444.65";
			LOGGER.info("Update Divisor Value-" + updateDivisorValue);
			IndicesSQLStatements.updateIndexDivisorValue(updateDivisorValue);
			indexHold.pause(90);
			Assert.assertEquals(IndexDivisorValue(), updateDivisorValue,
					"Index divisor values are not same after updating divisor value in local DB");
			performRightClickAction();
			clickAdvanced();
			clickRefreshFromT3();
			clicksaveIndexOk();
			indexHold.pause(90);
			Assert.assertTrue(IndexDivisorValue().equalsIgnoreCase(initilaDivisorValue),
					"Index divisor values are not same after refresh from T3");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify updated index divisor value-" + e.getMessage());
		}
	}

	public void verifyMessageHoldMarketOpen() {
		try {
			String descriptionInput = "Index movement beyond thresholds prior to market open resolved";
			alertSearch.type(descriptionInput);
			alertSearch.pause(30);
			String description = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]"))
					.getText();
			Assert.assertTrue(description.contains(descriptionInput));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(
					"Unable to verify the index movement beyond thresholds message in alert table-" + e.getMessage());
		}
	}

	public void negativeNumber() {
		try {
			holdIndexCurrentValue.type("-1234");
			String DefaultValue = holdIndexCurrentValue.getAttribute("value");
			Assert.assertTrue(Integer.parseInt(DefaultValue) < 0, "The index value is in negative");
			indexHoldCancel.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the negative valuerange in hold window-" + e.getMessage());
		}
	}

	public void clickCompareWithT3() {
		try {
			compareWithT3.isElementPresent();
			compareWithT3.hover();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on compare with T3 option-" + e.getMessage());
		}
	}

	public void clickIndexPortfolio() {
		try {
			indexPortfolio.isElementPresent();
			indexPortfolio.click();
			indexPortfolio.pause(Integer.parseInt(R.TESTDATA.get("MAIL_HALT_TIME")));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Index Portfolio option-" + e.getMessage());
		}
	}

	public void clickIndexParameters() {
		try {
			indexParameters.isElementPresent();
			indexParameters.click();
			indexParameters.pause(Integer.parseInt(R.TESTDATA.get("MAIL_HALT_TIME")));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Index Parameters option-" + e.getMessage());
		}
	}

	public void clickIndexTimings() {
		try {
			indexTimings.isElementPresent();
			indexTimings.click();
			indexTimings.pause(Integer.parseInt(R.TESTDATA.get("MAIL_HALT_TIME")));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on Index Timings option-" + e.getMessage());
		}
	}

	public void verifyReportInMail(String compareWithT3, String capturedSystemTime) {
		try {
			compareWithT3Value = compareWithT3;
			String emailSubject = null;
			String emailBodyContent = null;

			switch (compareWithT3) {
			case "Index Portfolio":
				emailSubject = "QA : Index Portfolio comparison";
				emailBodyContent = "are having Incorrect portfolio";
				EmailValidation.validateEmail(emailSubject, emailBodyContent, capturedSystemTime);
				break;

			case "Index Parameters":
				emailSubject = "QA : Index Parameters comparison";
				emailBodyContent = "having Incorrect parameters";
				EmailValidation.validateEmail(emailSubject, emailBodyContent, capturedSystemTime);
				break;

			case "Index Timings":
				emailSubject = "QA : Index Timinigs comparison";
				emailBodyContent = "having Incorrect timings";
				EmailValidation.validateEmail(emailSubject, emailBodyContent, capturedSystemTime);
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable verify the report genertaed in mail-" + e.getMessage());
		}
	}

	public void verifySearchListedValues() {
		try {
			ArrayList<String> ricColumnValues = new ArrayList<>();
			ArrayList<String> idcColumnValues = new ArrayList<>();
			ArrayList<String> nameColumnValues = new ArrayList<>();

			ricColumnSortButton.click();
			ricColumnSortButton.pause(10);
			String previousRICValue = indexRICValue.getText();
			String previousIDCValue = indexIDCValue.getText();
			String previousName = indexName.getText();
			String tSearch = (previousRICValue + "," + previousIDCValue + "," + previousName);
			searchIndex.type(tSearch);
			waitForTablefirstRow(indexTableID);
			searchIndex.pause(5);

			if (previousRICValue != null && previousIDCValue != null && previousName != null) {
				for (int i = 0; i < ricColumn.size(); i++) {
					ricColumnValues.add(ricColumn.get(i).getText());
					idcColumnValues.add(idcColumn.get(i).getText());
					nameColumnValues.add(nameColumn.get(i).getText());
				}
			} else
				Assert.fail("Values are empty in expected columns");
			Assert.assertTrue(ricColumnValues.contains(previousRICValue));
			Assert.assertTrue(idcColumnValues.contains(previousIDCValue));
			Assert.assertTrue(nameColumnValues.contains(previousName));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify the searched indices value with comma separated-" + e.getMessage());
		}
	}

	public void copyName() {
		try {
			copyName.isElementPresent();
			copyName.click();
			copyNameClipboard.isElementPresent();
			LOGGER.info("Index Name table value - " + fetchTableColumnValues.get("name"));
			LOGGER.info("Index Name copy clipboard value - " + copyNameClipboard.getAttribute("value"));
			Assert.assertTrue(
					fetchTableColumnValues.get("name").equalsIgnoreCase(copyNameClipboard.getAttribute("value")));
			copyNameCancel.click();
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
			LOGGER.info("Index ID table value - " + searchDataValue);
			LOGGER.info("Index ID copy clipboard value - " + copyIdClipboard.getAttribute("value"));
			Assert.assertTrue(copyIdClipboard.getAttribute("value").equalsIgnoreCase(searchDataValue));
			copyIDCancel.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate copy id" + e.getMessage());
		}

	}

	public void clickReport() {
		try {
			report.click();
			switchOutOfFrame();
			switchToFrame(reportFrameID);
			filterReportInput.isElementPresent();
			LOGGER.info("Index Name table value - " + fetchTableColumnValues.get("name"));
			LOGGER.info("Report tab Name value - " + filterReportInput.getAttribute("value"));
			Assert.assertTrue(filterReportInput.getAttribute("value").contains(fetchTableColumnValues.get("name")));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate Report" + e.getMessage());
		}
	}

	public void selectAllRows() {
		try {
			performRightClickNoTestData();
			selectAllRows.isElementPresent();
			selectAllRows.click();
			selectAllRows.pause(10);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//div[contains(@id,'" + indexTableID + "')]//span[@class='statusBarSpan']"));
			ele.scrollTo();
			String[] countValues = ele.getText().split("S");
			LOGGER.info("Total Value - " + countValues[0].replaceAll("[^0-9]", ""));
			LOGGER.info("Selected value  " + countValues[1].replaceAll("[^0-9]", ""));
			Assert.assertEquals(countValues[0].replaceAll("[^0-9]", ""), countValues[1].replaceAll("[^0-9]", ""),
					"Total and selected values are mismatching in index table");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the count for all slected rows-" + e.getMessage());
		}
	}

	public void publishAddDefinition() {
		try {
			Subject = "Definition Message published for 1 index(s)";
			String descritption = "Add Definition Message published for 1 index(s)";
			publishAddDefinition.isElementPresent();
			publishAddDefinition.click();
			clicksaveIndexOk();
			publishAddDefinition.pause(30);
			alertSearch.type(Subject);
			alertSearch.pause(20);
			VerifyAlertsMessages(Subject, "Process Status", currenTime, descritption);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate Publish Add Definition-" + e.getMessage());
		}
	}

	public void publishUpdateDefinition() {
		try {
			String descritption = "Update Definition Message published for 1 index(s)";
			publishUpdateDefinition.isElementPresent();
			publishUpdateDefinition.click();
			updateIndexOk.click();
			publishUpdateDefinition.pause(30);
			alertSearch.type(Subject);
			alertSearch.pause(20);
			VerifyAlertsMessages(Subject, "Process Status", currenTime, descritption);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate Publish Update Definition-" + e.getMessage());
		}
	}

	public void publishNews() {
		try {
			String descritption = "News Message published for 1 index(s)";
			publishNews.isElementPresent();
			publishNews.click();
			newsTextArea.type("Test");
			newsIndexOk.click();
			publishNews.pause(30);
			alertSearch.type(descritption);
			alertSearch.pause(20);
			VerifyAlertsMessages(descritption, "Process Status", currenTime, descritption);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate Publish News-" + e.getMessage());
		}
	}

	public void moveToNextDay() {
		try {
			performRightClickNoTestData();
			moveToNextDay.isElementPresent();
			moveToNextDay.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click Move to Next Day-" + e.getMessage());
		}
	}

	public void verifyMoveToNextDayAlert() {
		try {
			String newDayMessage = "Index has been loaded";
			String currentPrice[];
			String previousValue[];
			moveToNextDay.pause(10);
			getUniqueID();
			if (dasboardLink.equalsIgnoreCase("close")) {
				String description = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]"))
						.getText();
				if (description.contains(newDayMessage)) {
				} else {
					Assert.fail("Warning Message - The index cannot be moved to next day");
				}
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Pending New Day", currenTime, newDayMessage);
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains("Index has been loaded"));
				getDriver().navigate().refresh();
				clickDashboard();
				dashboardNewDay.isElementPresent();
				dashboardNewDay.click();
				switchOutOfFrame();
				switchToFrame(researchFrameID);
				waitForTablefirstRow(indexTableID);
				indexSearchTextBox.type(fetchTableColumnValues.get("name"));
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("status").equalsIgnoreCase("New Day"),
						"Expected index Status were not changed in index table");
				IndicesSQLStatements.getIndexNameStatus();
				Assert.assertTrue(IndicesSQLStatements.indexNamestatus.contains("New Day"));
				LOGGER.info("Current price value - " + fetchTableColumnValues.get("currentPriceValue"));
				LOGGER.info("Previuos  value - " + fetchTableColumnValues.get("previuosValue"));
				currentPrice = fetchTableColumnValues.get("currentPriceValue").split("\\.");
				previousValue = fetchTableColumnValues.get("previuosValue").split("\\.");
				if (currentPrice[0].equalsIgnoreCase(previousValue[0]))
					Assert.assertEquals(currentPrice[0], previousValue[0]);
				else
					LOGGER.info("Warning message - Previuos  value and current price value are not matching");
			} else if (dasboardLink.equalsIgnoreCase("open")) {
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Pending New Day", currenTime,
						"post close time has not been reached yet");
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(
						IndicesSQLStatements.alertMessageValue.contains("post close time has not been reached yet"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify Move to Next Day Alert-" + e.getMessage());
		}
	}

	public void verifyPublishT3MessagesOpen() {
		try {
			String publishT3Message = "Failed to publish";
			indexHoldOk.pause(20);
			getUniqueID();
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "EOD Value", currenTime, publishT3Message);
			IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
			Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(publishT3Message));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify verify Publish T3 message Alert-" + e.getMessage());
		}
	}

	public int countRecord(File Dirpath) throws IOException {
		int count = 0;
		csvReader = new BufferedReader(new FileReader(Dirpath));
		String row;
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			count++;
		}
		return count;
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

	public void indexDownload() throws IOException, InterruptedException {
		ArrayList<String> SetExcel = new ArrayList<String>();
		ArrayList<String> IndexData = new ArrayList<String>();

		String Alert = totalCount.getText();
		String Column = indexColumn.getText();

		IndexData.add(Column);
		String requiredString = Alert.substring(Alert.indexOf(":") + 2, Alert.indexOf("(") - 1);
		int Al_count = Integer.parseInt(requiredString);

		downloadIndexData.click();
		downloadIndexData.pause(10);

		homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);
		LOGGER.info("File is-" + LatestFile);

		csvReader = new BufferedReader(new FileReader(LatestFile));
		int count = countRecord(LatestFile);

		LOGGER.info("UI Table Indices Count - " + Al_count);
		LOGGER.info("downloaded file Indices Count - " + (count - 2));

		Assert.assertEquals((count - 2), Al_count);

		for (int i = 1; i < 5; i++) {
			ExtendedWebElement A_Name = findExtendedWebElement(
					By.xpath("//table[@class='display dataTable']//tr[" + i + "]//td[6]"));
			IndexData.add(A_Name.getText());
		}

		String row = "";
		String[] data = null;
		csvReader = new BufferedReader(new FileReader(LatestFile));
		for (int i = 0; i < 5; i++) {
			if ((row = csvReader.readLine()) != null) {
				data = row.split(",");
				SetExcel.add(data[5].replaceAll("^\"|\"$", ""));
			}
		}
		csvReader.close();
		Assert.assertTrue(IndexData.equals(SetExcel));
	}

	public void verifyIndexFilter() {
		try {
			String Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC08027", "TestData1");
			int index = 0;
			String View = "Index Quick View";
			indexVerifyFilter(Search, index, View);
			index = 1;
			View = "Identifier View";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC08027", "TestData2");
			indexVerifyFilter(Search, index, View);
			index = 5;
			View = "Settlement Calc";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC08027", "TestData3");
			indexVerifyFilter(Search, index, View);
			index = 6;
			View = "On Hold";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC08027", "TestData4");
			indexVerifyFilter(Search, index, View);
			index = 7;
			View = "Technical Support";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC08027", "TestData5");
			indexVerifyFilter(Search, index, View);
			index = 8;
			View = "SPXML Message View";
			Search = excel.getValue("./src/test/resources/Utils/TestData.xlsx", 1, 0, "TC08027", "TestData6");
			indexVerifyFilter(Search, index, View);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify filter headers combo-" + e.getMessage());
		}
	}

	public void indexVerifyFilter(String Search, int index, String View) {
		try {
			indexDropdown.select(View);
			waitForTablefirstRow(indexTableID);
			indexDropdown.pause(3);
			ExtendedWebElement ele = findExtendedWebElement(
					By.xpath("//table[contains(@id,'" + indexTableID + "')]/tbody/tr[1]/td"));
			ele.isElementPresent();
			if (ele.getAttribute("class").contains("empty")) {
				Assert.fail("Warning Message - No indices were listed for the required type to very filter combo scenario");
			}
			HashSet<String> column = new HashSet<String>();
			ArrayList<String> quickView = new ArrayList<String>();
			List<ExtendedWebElement> tableRows = indexColumns.findExtendedWebElements(By.tagName("th"));

			for (int i = 1; i <= tableRows.size(); i++) {
				ExtendedWebElement indexColumn = findExtendedWebElement(
						By.xpath("//table[@id='indexTable1']//tr[1]//td[" + i + "]"));
				indexColumn.click();
				ExtendedWebElement columnName = findExtendedWebElement(
						By.xpath("//table[@class='display dataTable']//thead//tr//th[" + i + "]"));
				column.add(columnName.getText());
			}
			String searchSplit[] = Search.split(",");
			for (int k = 0; k < searchSplit.length; k++) {
				quickView.add(searchSplit[k]);
			}

			for (int i = 0; i < quickView.size(); i++) {
				Assert.assertTrue(column.contains(quickView.get(i)));
			}
			column.clear();
			quickView.clear();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify filter headers" + e.getMessage());
		}
	}

	public void searchIndexNameCloseGrip() {
		try {
			indexSearchTextBox.type(fetchTableColumnValues.get("name"));
			waitForTablefirstRow(indexTableID);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to search for the index close from GRIP-" + e.getMessage());
		}
	}

	public void correctStatisticsValue(String modifyType) {
		try {
			String Message = "have been corrected successfully";
			String correctedDailyHighValue = null, correctedDailyLowValue = null, correctedDailyOpenalue = null;
			clickAdvanced();
			correctStatistics.isElementPresent();
			correctStatistics.click();
			correctValue.isElementPresent();
			correctValue.click();
			if (modifyType.equalsIgnoreCase("update")) {
				initalOpenValue = fetchTableColumnValues.get("openValue");
				initialDailyHighValue = fetchTableColumnValues.get("dailyHighValue");
				initialDailyLowValue = fetchTableColumnValues.get("dailyLowValue");
				LOGGER.info("Initial open value form table - " + initalOpenValue);
				LOGGER.info("Initial daily high value  value form table - " + initialDailyLowValue);
				LOGGER.info("Initial daily Low Value form table - " + dailyLowValue);
				if (Double.valueOf(initialDailyHighValue) != null) {
					correctedDailyHighValue = Double.toString(Double.parseDouble(initialDailyHighValue) + 100);
					correctedDailyLowValue = Double.toString(Double.parseDouble(initialDailyLowValue) - 100);
					correctedDailyOpenalue = Double.toString(Double.parseDouble(initalOpenValue) + 1000);
					LOGGER.info("Corrected open value form table - " + correctedDailyOpenalue);
					LOGGER.info("corrected Daily Low Value form table - " + correctedDailyLowValue);
					LOGGER.info("corrected Daily High Value form table - " + correctedDailyOpenalue);
				}

				dailyHighValue.type(correctedDailyHighValue);
				dailyLowValue.type(correctedDailyLowValue);
				openValue.type(correctedDailyOpenalue);
				clicksaveIndexOk();
				indexHoldOk.pause(20);
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Index Values Correction", currenTime,
						Message);
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(Message));
				getIndicesCoulmnValue();
				String[] correctedDailyLow = correctedDailyLowValue.split("\\.");
				String[] correctedDailyHigh = correctedDailyHighValue.split("\\.");
				String[] correctedDailyOpen = correctedDailyOpenalue.split("\\.");
				Assert.assertTrue(fetchTableColumnValues.get("dailyHighValue").contains(correctedDailyHigh[0]));
				Assert.assertTrue(fetchTableColumnValues.get("dailyLowValue").contains(correctedDailyLow[0]));
				Assert.assertTrue(fetchTableColumnValues.get("openValue").contains(correctedDailyOpen[0]));
			} else if (modifyType.equalsIgnoreCase("rollback")) {
				dailyHighValue.type(initialDailyHighValue);
				dailyLowValue.type(initialDailyLowValue);
				openValue.type(initalOpenValue);
				clicksaveIndexOk();
				dailyLowValue.pause(15);
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Index Values Correction", currenTime,
						Message);
				IndicesSQLStatements.getIndexMessageStatus(alertUniqueID);
				Assert.assertTrue(IndicesSQLStatements.alertMessageValue.contains(Message));
				getIndicesCoulmnValue();
				String[] dailyLow = initialDailyLowValue.split("\\.");
				String[] dailyHigh = initialDailyHighValue.split("\\.");
				String[] dailyOpen = initalOpenValue.split("\\.");
				Assert.assertTrue(fetchTableColumnValues.get("dailyHighValue").contains(dailyHigh[0]));
				Assert.assertTrue(fetchTableColumnValues.get("dailyLowValue").contains(dailyLow[0]));
				Assert.assertTrue(fetchTableColumnValues.get("openValue").contains(dailyOpen[0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to modify the correct statistics Value and verify it-" + e.getMessage());
		}
	}

	public void updateThresoldValuesinDB(String thresoldType) {
		try {

			switch (thresoldType) {
			case "normal warning":
				BASE_LINE_WARN_THRESHOLD_UP = "0.00001";
				BASE_LINE_WARN_THRESHOLD_DOWN = "0.00001";
				IndicesSQLStatements.updateThresoldValues(thresoldType, BASE_LINE_WARN_THRESHOLD_UP,
						BASE_LINE_WARN_THRESHOLD_DOWN);
				break;

			case "warning to normal":
				BASE_LINE_WARN_THRESHOLD_UP = "50";
				BASE_LINE_WARN_THRESHOLD_DOWN = "33";
				IndicesSQLStatements.updateThresoldValues(thresoldType, BASE_LINE_WARN_THRESHOLD_UP,
						BASE_LINE_WARN_THRESHOLD_DOWN);
				break;

			case "warning to auto hold":
				BASE_LINE_ERR_THRESHOLD_UP = "0.000001";
				BASE_LINE_ERR_THRESHOLD_DOWN = "0.000001";
				PREV_TICK_ERR_THRESHOLD_UP = "0.000001";
				PREV_TICK_ERR_THRESHOLD_DOWN = "0.000001";
				IndicesSQLStatements.updateMoreThresoldValues(BASE_LINE_ERR_THRESHOLD_UP, BASE_LINE_ERR_THRESHOLD_DOWN,
						PREV_TICK_ERR_THRESHOLD_UP, PREV_TICK_ERR_THRESHOLD_DOWN);
				break;

			case "auto hold to normal":
				BASE_LINE_ERR_THRESHOLD_UP = "50";
				BASE_LINE_ERR_THRESHOLD_DOWN = "33";
				PREV_TICK_ERR_THRESHOLD_UP = "50";
				PREV_TICK_ERR_THRESHOLD_DOWN = "33";
				IndicesSQLStatements.updateMoreThresoldValues(BASE_LINE_ERR_THRESHOLD_UP, BASE_LINE_ERR_THRESHOLD_DOWN,
						PREV_TICK_ERR_THRESHOLD_UP, PREV_TICK_ERR_THRESHOLD_DOWN);
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update thresold values in RTm table in DB-" + e.getMessage());
		}
	}

	public void refreshMviewSaveDetails() {
		try {
			refreshMViewTab.isElementPresent();
			refreshMViewTab.click();
			refreshMViewbutton.isElementPresent();
			refreshMViewbutton.click();
			clicksaveIndexOk();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to save the modified percecnt values in Data maintain page-" + e.getMessage());
		}
	}

	public void dashboardValidation() {
		try {
			getDriver().navigate().refresh();
			clickDashboard();
			dashboardManualHold.isElementPresent();
			dashboardManualHold.pause(35);
			finalWarning = Integer.parseInt(dashboardWarning.getText());
			finalAutoholdCount = Integer.parseInt(dashboardAutoHold.getText());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate warning value in dashboard-" + e.getMessage());
		}
	}

	public void verifyDBToUIAlert(String thresoldType) {
		try {
			getUniqueID();

			switch (thresoldType) {

			case "normal warning":
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Warning Threshold", currenTime,
						"is set on warning");
				dashboardValidation();
				Assert.assertTrue(finalWarning > initialWarning, "Warning count has not increased");
				break;

			case "warning to normal":
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Warning Threshold", currenTime,
						"is set off warning");
				dashboardValidation();
				Assert.assertTrue(initialWarning > finalWarning, "Warning count has not decreased");
				break;

			case "warning to auto hold":
				indexHoldOk.pause(25);
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Threshold Breach", currenTime,
						"will be held");
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Auto Hold"),
						"Expected index Status were not changed in index table");
				dashboardValidation();
				Assert.assertTrue(finalAutoholdCount > initialAutoholdCount, "Auto hold count had not increased");
				break;

			case "auto hold to normal":
				indexHoldOk.pause(25);
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Auto-Release", currenTime, "Auto-released");
				getIndicesCoulmnValue();
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("OK"),
						"Expected index Status were not changed in index table");
				dashboardValidation();
				Assert.assertTrue(initialAutoholdCount > finalAutoholdCount, "Auto hold count had not decreased");
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(
					"Unable to verify alert messages that been updated in DB for thresold values-" + e.getMessage());
		}
	}

	public void verifyHPIUICount() throws IOException {
		try {
			downloadIndexData.click();
			downloadIndexData.pause(10);

			homePath = System.getProperty("user.home") + "/Downloads";
			File LatestFile = getLatestFilefromDir(homePath);
			LOGGER.info("File is-" + LatestFile);

			csvReader = new BufferedReader(new FileReader(LatestFile));
			int count = countRecord(LatestFile);

			String row = "";
			String[] data = null;
			csvReader = new BufferedReader(new FileReader(LatestFile));
			for (int i = 0; i < count - 1; i++) {
				if ((row = csvReader.readLine()) != null) {
					data = row.split(",");
					if (data[6].replaceAll("^\"|\"$", "").equalsIgnoreCase("yes"))
						HPICount++;
				}
			}
			System.out.println(HPICount);
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch values fro dwonloaded excel-" + e.getMessage());
		}
	}

	public void verifyHPIFlagUI() {
		try {
			String HPIColumnNameValue = "HPI Indicator";
			HPIColumnFirstValue.click();
			HPIColumnName.click();
			HPIColumnName.pause(5);
			Assert.assertEquals(HPIColumnNameValue, HPIColumnName.getText(),
					"HPI Column is not present in Index table");
			verifyHPIUICount();
			IndicesSQLStatements.getHPICountValue();
			Assert.assertEquals(HPICount, Integer.parseInt(IndicesSQLStatements.DBHPICountValue),
					"Count mistmatch between UI and DB for HPI indices");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify HPI flag in UI-" + e.getMessage());
		}
	}

	public void verifyHPIColumnSorting() {
		try {
			ArrayList<String> HPIColunValueAfter = new ArrayList<>();
			ArrayList<String> HPIColunValueBefore = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				HPIColunValueAfter.add(HPIColumnValuesYes.get(i).getText());
			}
			Collections.sort(HPIColunValueAfter);
			HPIColumnName.click();
			HPIColumnName.pause(5);
			for (int i = 0; i < 10; i++) {
				HPIColunValueBefore.add(HPIColumnValues.get(i).getText());
			}
			Collections.sort(HPIColunValueBefore);
			Assert.assertNotEquals(HPIColunValueAfter, HPIColunValueBefore);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify sorting in HPI column in UI-" + e.getMessage());
		}

	}

	public void verifyNoTicksAlertMessages(String noTickType) {
		try {
			switch (noTickType) {
			case "No Ticks IDC":
				searchAlert("not ticking in IDC");
				VerifyAlertsMessagesNoTicks("High Profile Index", "System Monitor", currenTime, "not ticking in IDC");
				break;

			case "Flat Line IDC Resolved":
			case "No Ticks IDC Resolved":
				searchAlert("ticking in normally in IDC");
				VerifyAlertsMessagesNoTicks("High Profile Index", "System Monitor", currenTime,
						"ticking in normally in IDC");
				break;

			case "No Ticks Reuters":
				searchAlert("not ticking in Reuters");
				VerifyAlertsMessagesNoTicks("High Profile Index", "System Monitor", currenTime,
						"not ticking in Reuters");
				break;

			case "Flat Line reuters Resolved":
			case "No Ticks Reuters Resolved":
				searchAlert("ticking in normally in Reuters");
				VerifyAlertsMessagesNoTicks("High Profile Index", "System Monitor", currenTime,
						"ticking in normally in Reuters");
				break;

			case "Flat Line IDC":
				searchAlert("value flatlinedin IDC");
				VerifyAlertsMessagesNoTicks("High Profile Index", "System Monitor", currenTime,
						"value flatlinedin IDC");
				break;

			case "Flat Line reuters":
				searchAlert("flatlined in Reuters");
				VerifyAlertsMessagesNoTicks("High Profile Index", "System Monitor", currenTime, "flatlined in Reuters");
				break;

			case "Flat Line Grip":
				searchAlert("flatlined in GRIP");
				VerifyAlertsMessagesNoTicks("High Profile Index", "System Monitor", currenTime, "flatlined in GRIP");
				break;

			case "Flat Line Grip Resolved":
				searchAlert("ticking in normally in GRIP");
				VerifyAlertsMessagesNoTicks("High Profile Index", "System Monitor", currenTime,
						"ticking in normally in GRIP");
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to stop calcuation for the index and verify it-" + e.getMessage());
		}
	}

	public void clickPublishSuspect() {
		try {
			publishSuspect.isElementPresent();
			publishSuspect.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on publish suspect-" + e.getMessage());
		}
	}

	public void clickWithdrawSuspect() {
		try {
			withdrawSuspect.isElementPresent();
			withdrawSuspect.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on withdraw suspect-" + e.getMessage());
		}
	}

	public void verifyPublishSuspectAlert(String suspectType) {
		try {
			publishSuspectMessage = "Publish suspect successfully";
			publishUserSuspectValue = "1";
			publishDataQualitytValue = "2";
			indexHoldOk.pause(15);
			getUniqueID();
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Suspect", currenTime, publishSuspectMessage);
			IndicesSQLStatements.getSuspectIndexValues();
			LOGGER.info("DB Value of user suspect column after publish suspect- " + IndicesSQLStatements.userSuspect);
			LOGGER.info("DB Value of data quality column after publish suspect- " + IndicesSQLStatements.dataQuality);
			Assert.assertEquals(IndicesSQLStatements.userSuspect, publishUserSuspectValue,
					"MIsmatch in DB with user_suspect value");
			Assert.assertEquals(IndicesSQLStatements.dataQuality, publishDataQualitytValue,
					"MIsmatch in DB with data_quality value");
			getDriver().navigate().refresh();
			clickDashboard();
			dashboardManualHold.isElementPresent();
			dashboardManualHold.pause(20);
			finalSuspectCount = Integer.parseInt(dashboardSuspect.getText());
			Assert.assertEquals(initialSuspectCount + 1, finalSuspectCount);
			switchOutOfFrame();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify publish suspect for index-" + e.getMessage());
		}
	}

	public void verifyWithDrawSuspectAlert(String suspectType) {
		try {
			String withDrawMessage = "Withdraw suspect successfully";
			String publishUserSuspectValue = "0";
			String publishDataQualitytValue = "0";
			withdrawSuspectOk.pause(15);
			getUniqueID();
			VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Suspect", currenTime, withDrawMessage);
			IndicesSQLStatements.getSuspectIndexValues();
			LOGGER.info("DB Value of user suspect column after withdraw suspect- " + IndicesSQLStatements.userSuspect);
			LOGGER.info("DB Value of data quality column after withdraw suspect- " + IndicesSQLStatements.dataQuality);
			Assert.assertEquals(IndicesSQLStatements.userSuspect, publishUserSuspectValue,
					"MIsmatch in DB with user_suspect value");
			Assert.assertEquals(IndicesSQLStatements.dataQuality, publishDataQualitytValue,
					"MIsmatch in DB with data_quality value");
			getDriver().navigate().refresh();
			clickDashboard();
			dashboardManualHold.isElementPresent();
			dashboardManualHold.pause(20);
			finalSuspectCount = Integer.parseInt(dashboardSuspect.getText());
			Assert.assertEquals(initialSuspectCount, finalSuspectCount);
			switchOutOfFrame();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify publish suspect for index-" + e.getMessage());
		}
	}

	public void verifySuspectStatus() {
		try {
			Assert.assertEquals(userSuspectCoulmn.getText(), "Yes",
					"MIsmatch in Index suspect window for user suspect alert status");
			Assert.assertEquals(dataQualityColumn.getText(), "Suspect",
					"MIsmatch in Index suspect window for data quality alert status");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify on suspect alerts status-" + e.getMessage());
		}

	}

	public void verifySuspectholdAlert(String holdType) {
		try {
			indexHoldOk.pause(50);
			getIndicesCoulmnValue();
			if (holdType.equalsIgnoreCase("Manual Hold")) {
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase(manulaHoldValue),
						"Expected index Status were not changed in index table");
				Assert.assertEquals(dataQualityColumn.getText(), "Suspend",
						"MIsmatch in Index suspect window for data quality alert status");
			} else if (holdType.equalsIgnoreCase("Manual release")) {
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("OK"),
						"Expected index Status were not changed in index table");
				Assert.assertEquals(dataQualityColumn.getText(), "Suspect",
						"MIsmatch in Index suspect window for data quality alert status");
			} else if (holdType.equalsIgnoreCase("auto hold")) {
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("Auto hold"),
						"Expected index Status were not changed in index table");
				Assert.assertEquals(dataQualityColumn.getText(), "Suspend",
						"MIsmatch in Index suspect window for data quality alert status");
			} else {
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("OK"),
						"Expected index Status were not changed in index table");
				Assert.assertEquals(dataQualityColumn.getText(), "Suspect",
						"MIsmatch in Index suspect window for data quality alert status");

			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify hold release of suspect alerts status-" + e.getMessage());
		}
	}

	public void verifySuspectAutoHoldAlert(String holdType) {
		try {
			indexHoldOk.pause(50);
			getIndicesCoulmnValue();
			if (holdType.equalsIgnoreCase("Manual Hold")) {
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase(manulaHoldValue),
						"Expected index Status were not changed in index table");
				Assert.assertEquals(dataQualityColumn.getText(), "Suspend",
						"MIsmatch in Index suspect window for data quality alert status");
			} else {
				Assert.assertTrue(fetchTableColumnValues.get("calcStatusValue").equalsIgnoreCase("OK"),
						"Expected index Status were not changed in index table");
				Assert.assertEquals(dataQualityColumn.getText(), "Suspect",
						"MIsmatch in Index suspect window for data quality alert status");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify hold release of suspect alerts status-" + e.getMessage());
		}
	}

	public void clickWithdrawSuspectOk() {
		try {
			withdrawSuspectOk.isElementPresent();
			withdrawSuspectOk.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to click on withdraw suspect ok-" + e.getMessage());
		}
	}

	public void downloadIndexSuspectwindow() throws IOException {
		downloadIndexData.click();
		downloadIndexData.pause(10);

		homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);
		LOGGER.info("File is-" + LatestFile);

		String row = "";
		String[] data = null;
		csvReader = new BufferedReader(new FileReader(LatestFile));
		for (int i = 0; i < 5; i++) {
			if ((row = csvReader.readLine()) != null) {
				data = row.split(",");
				if (data[6].replaceAll("^\"|\"$", "").equalsIgnoreCase("User Suspect")
						&& data[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("Data Quality")) {
					Assert.assertTrue(data[6].replaceAll("^\"|\"$", "").equalsIgnoreCase("User Suspect"));
					LOGGER.info("Downloaded file successfull with User suspect and Data quality coulmns");
					break;
				}
			}
		}
		csvReader.close();
	}

	public void verifyNoAlertMessage() {
		try {
			indexHoldOk.pause(20);
			getUniqueID();
			String description = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[6]"))
					.getText();
			if (!description.contains("Publish suspect successfully")) {
			} else {
				Assert.fail("Warning Message - Action had been performed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify no alert message-" + e.getMessage());
		}
	}

	public void verifyDQFlaginDB(String queryType) {
		try {
			IndicesSQLStatements.getDQFlagValue(queryType);
			Assert.assertTrue(IndicesSQLStatements.userSuspect == null);
			Assert.assertTrue(IndicesSQLStatements.dataQuality == null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify DQ Flag in DB-" + e.getMessage());
		}
	}

	public void publishWithdrawMultipleIndices(String actionType, String indexType) {
		try {
			Actions builder = new Actions(getDriver());
			if (actionType.equalsIgnoreCase("Publish suspect")) {
				waitForTablefirstRow(indexTableID);
				selectIndextype(indexType, "Indices");
				maximizeWindow.click();
				maximizeWindow.pause(4);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				clickPublishSuspect();
				clicksaveIndexOk();
				indexHold.pause(25);
				getIndicesCoulmnValue();
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Suspect", currenTime, publishSuspectMessage);
				IndicesSQLStatements.getSuspectIndexValues();
				LOGGER.info(
						"DB Value of user suspect column after publish suspect- " + IndicesSQLStatements.userSuspect);
				LOGGER.info(
						"DB Value of data quality column after publish suspect- " + IndicesSQLStatements.dataQuality);
				Assert.assertEquals(IndicesSQLStatements.userSuspect, publishUserSuspectValue,
						"MIsmatch in DB with user_suspect value");
				Assert.assertEquals(IndicesSQLStatements.dataQuality, publishDataQualitytValue,
						"MIsmatch in DB with data_quality value");
				getDriver().navigate().refresh();
				clickDashboard();
				dashboardManualHold.isElementPresent();
				dashboardManualHold.pause(20);
				finalSuspectCount = Integer.parseInt(dashboardSuspect.getText());
				Assert.assertEquals(initialSuspectCount + 1, finalSuspectCount);
				switchOutOfFrame();
			} else {
				String withDrawMessage = "Withdraw suspect successfully";
				String publishUserSuspectValue = "0";
				String publishDataQualitytValue = "0";
				dashboardSuspect.click();
				waitForTablefirstRow(indexTableID);
				selectIndextype(indexType, "Indices");
				maximizeWindow.click();
				maximizeWindow.pause(4);
				builder.click(tableRowData.get(0)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(1)).keyDown(Keys.CONTROL).build().perform();
				builder.click(tableRowData.get(2)).keyUp(Keys.CONTROL).build().perform();
				builder.contextClick().build().perform();
				clickWithdrawSuspect();
				clickWithdrawSuspectOk();
				indexHold.pause(25);
				getIndicesCoulmnValue();
				getUniqueID();
				VerifyAlertsMessages(fetchTableColumnValues.get("name"), "Suspect", currenTime, withDrawMessage);
				IndicesSQLStatements.getSuspectIndexValues();
				LOGGER.info(
						"DB Value of user suspect column after withdraw suspect- " + IndicesSQLStatements.userSuspect);
				LOGGER.info(
						"DB Value of data quality column after withdraw suspect- " + IndicesSQLStatements.dataQuality);
				Assert.assertEquals(IndicesSQLStatements.userSuspect, publishUserSuspectValue,
						"MIsmatch in DB with user_suspect value");
				Assert.assertEquals(IndicesSQLStatements.dataQuality, publishDataQualitytValue,
						"MIsmatch in DB with data_quality value");
				getDriver().navigate().refresh();
				clickDashboard();
				dashboardManualHold.isElementPresent();
				dashboardManualHold.pause(20);
				finalSuspectCount = Integer.parseInt(dashboardSuspect.getText());
				Assert.assertEquals(initialSuspectCount, finalSuspectCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to hold/release multiple indices-" + e.getMessage());
		}
	}

	public void filterSuspectData() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			scrollToDown();
			customizeView.click();
			uncheckAll.isElementPresent();
			uncheckAll.click();
			js.executeScript("arguments[0].scrollIntoView(true);", suspectcheckBox);
			suspectcheckBox.click();
			customizeOk.click();
			downloadAlertData.isElementPresent();
			downloadAlertData.click();
			downloadAlerts.isElementPresent();
			downloadAlerts.click();
			downloadAlerts.pause(10);
			String alertType = findExtendedWebElement(By.xpath("//table[@id='alertTable']//tbody//tr[1]//td[2]"))
					.getText();
			Assert.assertEquals(alertType, "Suspect", "Filtered not happened properly for Alert type Suspect warning");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to filter alert suspect data-" + e.getMessage());
		}

	}

	public void alertFilterDonwload() throws IOException {
		homePath = System.getProperty("user.home") + "/Downloads";
		File LatestFile = getLatestFilefromDir(homePath);
		LOGGER.info("File is-" + LatestFile);
		boolean flag = false;
		String row = "";
		String[] data = null;
		csvReader = new BufferedReader(new FileReader(LatestFile));
		for (int i = 0; i < 5; i++) {
			if ((row = csvReader.readLine()) != null) {
				data = row.split(",");
				if (data[8].replaceAll("^\"|\"$", "").equalsIgnoreCase("Suspect")) {
					LOGGER.info(
							"Downloaded file successfull with alert generated for User suspect and Data quality coulmns");
					flag = true;
					break;
				}
			}
		}
		if (!flag)
			Assert.fail("Downloaded file not correct with alert type suspect");
		csvReader.close();
	}

	public void updateDataGenerateReport(String CompareWithT3) {
		try {
			IndicesSQLStatements.updateDataReport(CompareWithT3);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update data in DB to generate reports-" + e.getMessage());
		}
	}

	public void validateCSVFile(int dataPostion) {
		try {
			homePath = System.getProperty("user.home") + "/Downloads";
			File LatestFile = getLatestFilefromDir(homePath);
			LOGGER.info("File is-" + LatestFile);
			boolean flag = false;
			String row = "";
			String[] data = null;
			csvReader = new BufferedReader(new FileReader(LatestFile));
			for (int i = 0; i < 3; i++) {
				if ((row = csvReader.readLine()) != null) {
					data = row.split(",");
					if (data[dataPostion].replaceAll("^\"|\"$", "").equalsIgnoreCase(searchDataValue)) {
						LOGGER.info("Validation successful for Index Portfolio in CSV file from attached mail");
						flag = true;
						break;
					}
				}
			}
			if (!flag)
				Assert.fail("Unable to validate Index Id in CSV file form attached mail");
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate csv file data-" + e.getMessage());
		}
	}

	public void validateAttachmentFromMail() {
		try {
			switch (compareWithT3Value) {
			case "Index Portfolio":
				if (indexTypeValue.equalsIgnoreCase("Equity Indices"))
					validateCSVFile(2);

				else if (indexTypeValue.equalsIgnoreCase("Commodities Indices"))
					validateCSVFile(16);

				else if (indexTypeValue.equalsIgnoreCase("MultiAsset Indices"))
					validateCSVFile(10);
				break;

			case "Index Parameters":
				validateCSVFile(26);
				break;

			case "Index Timings":
				validateCSVFile(7);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to validate the attachment from mail - " + e.getMessage());
		}
	}

}