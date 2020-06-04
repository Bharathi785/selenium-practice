package com.grip.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.grip.page.DerivativesPage.reportFrameID;

public class ReportsPage extends AbstractPage {
    Logger LOGGER = Logger.getLogger(ReportsPage.class);

    public ReportsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Locators in Reports functionality
     */

    //Index  Hover

    @FindBy(xpath = "//ul[@id='ui-id-3']//li//a")
    public ExtendedWebElement indexHover;

    @FindBy(xpath = "//ul[@id='ui-id-2']//li//a")
    public ExtendedWebElement assetHover;

    @FindBy(id = "ui-id-5")
    public ExtendedWebElement reports;

    //Index
    @FindBy(id = "indexTextId")
    public ExtendedWebElement index;

    //Asset
    @FindBy(id = "assetTextId")
    public ExtendedWebElement asset;

    //Index ticks
    @FindBy(id = "indexTicks")
    public ExtendedWebElement indexTicks;

    //Index ICDS
    @FindBy(id = "assetTicks")
    public ExtendedWebElement assetTicks;

    //Asset Time and Sales
    @FindBy(id = "assetTS")
    public ExtendedWebElement assetTS;

    //Alerts
    @FindBy(id = "alerts")
    public ExtendedWebElement alerts;

    //Related Alerts
    @FindBy(id = "relatedAlerts")
    public ExtendedWebElement relatedAlerts;

    //Selecting the minutes in the time format
    @FindBy(id = "incdecminId")
    public ExtendedWebElement minutes;

    @FindBy(xpath = "//table[contains(@id,'selectReportsTable')]/tbody/tr[1]/td")
    public List<ExtendedWebElement> reportData;


    @FindBy(id = "time_hour_id")
    public ExtendedWebElement time;

    @FindBy(id = "slider_from_date_time_id")
    public ExtendedWebElement Date;

    // selectReportsTable_filter

    //Get Reports
    @FindBy(id = "getReports")
    public ExtendedWebElement getReports;

    //Event Type Column
    @FindBy(xpath = "//th[text()='Event Type']")
    public ExtendedWebElement eventType;


    //Download Reports
    @FindBy(xpath = "//button[@title='Download Reports']")
    public ExtendedWebElement downloadReports;


    //Get Tick Time value

    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[1]/td[1]")
    public ExtendedWebElement getTickTime;

    //Get publishedValue

    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[1]/td[2]")
    public ExtendedWebElement getPublishedValue;

    //Get Instrument
    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[1]/td[3]")
    public ExtendedWebElement getInstrument;

    //Get Event Type

    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[1]/td[4]")
    public ExtendedWebElement getEventType;

    //Get Asset Instrument
    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[2]/td[3]")
    public ExtendedWebElement getAssetInstrument;

    //search box
    @FindBy(xpath = "//*[@id='reportDetailTable1_filter']/label/input")
    public ExtendedWebElement searchBox;

    //search box
    @FindBy(xpath = "//*[@id='selectReportsTable_filter']/label/input")
    public ExtendedWebElement searchReportValue;

    //search term summary value
    @FindBy(xpath = "//input[@aria-controls='reportDetailTable1']")
    public ExtendedWebElement searchTermSummaryReportValue;

    //Get Asset Event Type

    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[2]/td[4]")
    public ExtendedWebElement getAssetEventType;

    //Get Alert Instrument
    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[1]/td[3]")
    public ExtendedWebElement getAlertInstrument;

    //Get Alert Event Type

    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[1]/td[4]")
    public ExtendedWebElement getAlerttEventType;

    @FindBy(id = "incdecId")
    public ExtendedWebElement DecrementDropDown;

    @FindBy(xpath = "/html/body/ul[3]/li[1]/span")
    public ExtendedWebElement getWindowTitle;

    @FindBy(xpath = "//*[@id='window_0']/div[1]/div[1]")
    public ExtendedWebElement getIndexConstituentsWindowTitle;

    @FindBy(xpath = "//*[@id='reportDetailTable1_wrapper']/div[1]/button")
    public ExtendedWebElement getReportIndexConstituentsWindowTitle;

    @FindBy(xpath = "//span[text()='Show Settlement Detail']")
    public ExtendedWebElement getSettlementDetail;

    @FindBy(xpath = "//div[text()='Index Settlement Details: S&P BRICT Index']")
    public ExtendedWebElement getIndexSettlementWindowTitle;

    @FindBy(xpath = "//*[@id='selectReportsTable']/tbody/tr[1]/td[1]")
    public ExtendedWebElement getTermSummaryDetail;

    @FindBy(xpath = "//span[contains(text(),'Vix Term Summary')]")
    public ExtendedWebElement getTermSummaryWindow;

    @FindBy(xpath = "//div[@title='close window']")
    public ExtendedWebElement closeConstituentsWindow;

    @FindBy(xpath = "//div[@title='close window']")
    public ExtendedWebElement closeSettlementWindow;

    @FindBy(xpath = "//span[contains(text(),'Show Constituent Details')]")
    public ExtendedWebElement showConstituentDetails;

    @FindBy(xpath = "//table[@class='display dataTable']//tr//td[1]")
    public ExtendedWebElement indexTable;

    @FindBy(xpath = "//table[@id='icdTable2']//tr//td[1]")
    public ExtendedWebElement icdTable;


    @FindBy(xpath = "//th[contains(text(),'Trading Session')]")
    public ExtendedWebElement tradingSession;

    @FindBy(xpath = "//th[contains(text(),'Holding Status')]")
    public ExtendedWebElement holdingStatus;

    @FindBy(xpath = "//th[contains(text(),'Grip Code')]")
    public ExtendedWebElement gripCode;


    @FindBy(xpath = "//span[text()='Report']")
    public ExtendedWebElement reportoption;

    @FindBy(xpath = "//span[text()='Hold']")
    public ExtendedWebElement holdStock;

    @FindBy(xpath = "/html/body/div[39]/div[11]/div/button[1]/span")
    public ExtendedWebElement Ok;


    @FindBy(xpath = "//span[text()='Release']")
    public ExtendedWebElement releaseStockOk;

    @FindBy(xpath = "/html/body/div[38]/div[11]/div/button[1]/span")
    public ExtendedWebElement OkStock;

    @FindBy(xpath = "//table[@class='display dataTable']/tbody/tr[1]")
    private ExtendedWebElement indexTableFirstRow;

    @FindBy(xpath = "//span[contains(text(),'Release')]")
    public ExtendedWebElement releaseIndex;

    @FindBy(xpath = "/html/body/div[22]/div[11]/div/button[1]/span")
    public ExtendedWebElement OkIndex;

    @FindBy(id = "indexFilters")
    public ExtendedWebElement indexFilters;

    @FindBy(id = "statusTypeSelectId")
    public ExtendedWebElement statusType;

    @FindBy(xpath = "//span[contains(text(),'Apply')]")
    public ExtendedWebElement applyButton;

    @FindBy(xpath = "//span[contains(text(),'Advanced Search')]")
    public ExtendedWebElement advancedSearch;

    @FindBy(xpath = "//*[@id='indexTable9']")
    public ExtendedWebElement indexTableCount;


    /**
     * Method to validate the user is able to click on the reports tab
     */
    public void homePage() {
        try {
            driver.switchTo().frame("reportsframe");
            LOGGER.info("Clicked on Reports frame successfully");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Reports frame" + e.getMessage());
        }

    }

    /**
     * @author balaji
     * Method to click on the reports tab
     */
    public void reportsTab() {
        try {
            driver.switchTo().defaultContent();
            reports.click();
            driver.switchTo().frame("reportsframe");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            LOGGER.info("Reports tab is clicked successfully ");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Reports tab is not clicked successfully" + e.getMessage());
        }
    }

    /**
     * @return
     * @throws
     * @author balaji
     * Method to pass the Index value in the Filter Report section
     */
    public String getIndexValue(String Index) {
        try {
            index.isElementPresent();
            index.type(Index);
            index.pause(3);
            indexHover.click();
            LOGGER.info("Index value is passed successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Index value is not passed successfully" + e.getMessage());
        }
        return Index;
    }

    /**
     * @return
     * @throws
     * @author balaji
     * Method to pass the Asset value in the Filter Report section
     */
    public String getAssetValue(String Asset) {

        try {
            asset.isElementPresent();
            asset.type(Asset);
            asset.pause(5);
            assetHover.click();
            LOGGER.info(" Asset value is passed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Asset value is not passed successfully" + e.getMessage());
        }
        return Asset;
    }

    /**
     * Method to pass the minutes value
     *
     * @author balaji
     */

    public void setMinutes(String Minutes) {
        try {
            minutes.isElementPresent();
            minutes.pause(3);
            String selectAll = Keys.chord(Keys.CONTROL, "a");
            minutes.getElement().sendKeys(selectAll);
            minutes.pause(3);
            minutes.getElement().sendKeys(Minutes);
            LOGGER.info("Minutes value is passed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Minutes value is not passed successfully" + e.getMessage());
        }
    }

    /**
     * Method to get the reports
     *
     * @author balaji
     */

    public void getReports() {
        try {
            getReports.isElementPresent();
            getReports.click();
            getReports.pause(10);
            LOGGER.info("get reports clicked successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("get reports is not clicked successfully" + e.getMessage());
        }
    }

    /**
     * Method to validate the Event Type column in Report Filter section
     *
     * @author balaji
     */

    public void validateRecords() {
        try {
            List<WebElement> reportsData = driver.findElements(By.id("selectReportsTable"));
            for (WebElement el : reportsData) {
                LOGGER.info(el.getText());
            }
            LOGGER.info("Report values are printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Report values are not printed successfully" + e.getMessage());
        }
    }

    /**
     * Method to download the CSV report
     */
    public void downloadReport() {
        try {
            downloadReports.isElementPresent();
            downloadReports.pause(10);
            downloadReports.click();
            downloadReports.pause(20);
            LOGGER.info("report downloaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("report is not downloaded successfully" + e.getMessage());
        }
    }

    /**
     * Method to check the Index
     *
     * @param Index
     */
    public void index(String Index) {
        try {
            index.isElementPresent();
            index.type(Index);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Index value is not passed" + e.getMessage());
        }
    }

    /**
     * Method to click on IndexTicks
     */
    public void clickIndexTicks() {
        try {
            indexTicks.isElementPresent();
            indexTicks.click();
            LOGGER.info("Index Ticks clicked successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Index Ticks Option" + e.getMessage());
        }
    }

    /**
     * Method to click on IndexICDOption
     */
    public void clickIndexICD() {
        try {
            assetTicks.isElementPresent();
            assetTicks.click();
            LOGGER.info("Index ICD option is clicked successfully");
            assetTicks.pause(5);
            asset.sendKeys(Keys.TAB);
            asset.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Index ICD Option" + e.getMessage());
        }
    }

    /**
     * Method to click on Asset Time and Sales Option
     */
    public void assetTimeAndSales() {
        try {
            assetTS.isElementPresent();
            assetTS.click();
            LOGGER.info("Asset Time and Sales option is selected successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to select Asset Time and Sales option " + e.getMessage());
        }
    }

    /**
     * Method to uncheck Asset Time and Sales checkbox
     */
    public void uncheckAssetTimeAndSalesOption() {
        try {
            assetTS.isElementPresent();
            assetTS.pause(3);
            boolean checkstatus;
            checkstatus = assetTS.isChecked();
            if (checkstatus == true) {
                assetTS.click();
                LOGGER.info("Asset Time and Sales option is unchecked successfully");
            } else {
                LOGGER.info("Asset Time and Sales option is not unchecked successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Asset Time and Sales option is checked successfully" + e.getMessage());
        }
    }

    /**
     * Method to select the decrement option in Calender Section
     */

    public void selectDecrement() {
        try {
            DecrementDropDown.isElementPresent();
            LOGGER.info("DecrementDropDown is present");
            DecrementDropDown.isVisible();
            LOGGER.info("incrementDropDown is visible");
            Select DecrementID = new Select(driver.findElement(By.id("incdecId")));
            DecrementID.selectByValue("2");
            LOGGER.info("DecrementDropDown value is selected  successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("DecrementDropDown value is not selected successfully" + e.getMessage());
        }
    }

    /**
     * Method to get the getLatestFilefromDir
     *
     * @throws IOException
     * @author balaji
     */

    public File getLatestFileFromDir(String dirPath) {
        Path parentFolder = Paths.get(dirPath);

        Optional<File> mostRecentFile =
                Arrays
                        .stream(parentFolder.toFile().listFiles())
                        .filter(f -> f.isFile())
                        .max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
        File mostRecent = null;
        if (mostRecentFile.isPresent()) {
            mostRecent = mostRecentFile.get();
            LOGGER.info("most recent file is " + mostRecent.getPath());
        } else {
            LOGGER.info("file is empty!");
        }
        return mostRecent;
    }

    /**
     * Method to get the no of records in the CSV file
     *
     * @throws IOException
     * @author balaji
     */

    public int countRecord(File LatestFile) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
        csvReader.readLine();
        String row = null;
        int count = 0;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            count++;
        }
        LOGGER.info("Total number of records in the report is :" + count);
        return count;
    }


    public int checkIndexConstituentsRecordCount(File LatestFile) throws IOException {
        int count = 0;
        BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            count++;
        }
        LOGGER.info("Total number of records in the report is :" + count);
        return count;
    }

    /**
     * Method to read the data in the CSV file
     *
     * @throws IOException
     */
    public void readCSVData(File LatestFile) throws IOException {
        int count = 0;
        String row = "";
        String[] data = null;

        BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
        LOGGER.info("The " + LatestFile.getPath() + " is printed successfully");

        for (int i = 0; i < 5; i++) {
            if ((row = csvReader.readLine()) != null) {
                data = row.split(",");
                LOGGER.info(data[0]);
            }
        }


    }

    /**
     * Method to click on alerts checkbox
     */
    public void alerts() {
        try {
            alerts.isElementPresent();
            alerts.click();
            LOGGER.info("Alerts checkbox is clicked successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Alerts checkbox " + e.getMessage());
        }
    }

    /**
     * Method to click on related alerts checkbox
     */
    public void relatedAlerts() {
        try {
            relatedAlerts.isElementPresent();
            relatedAlerts.click();
            LOGGER.info("Related checkbox is clicked successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Related checkbox" + e.getMessage());
        }
    }

    public void verifyInstrumentValue(String expected_instrument) {
        try {
            String actualInstrumentValue = getInstrument.getText();
            LOGGER.info("The " + actualInstrumentValue + " is printed successfully");
            Assert.assertEquals(expected_instrument, actualInstrumentValue);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Instrument value is not displayed in Reports Filter section" + e.getMessage());
        }
    }

    public void verifyEventTypeValue(String expected_eventType) {
        try {
            String actualEventType = getEventType.getText();
            LOGGER.info("The " + actualEventType + " is printed successfully");
            Assert.assertEquals(expected_eventType, actualEventType);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Event Type value is not displayed in Reports Filter section" + e.getMessage());
        }
    }

    public void verifyAssetInstrumentValue(String expected_asset_instrument) {
        try {
            String actualAssetInstrumentValue = getAssetInstrument.getText();
            LOGGER.info("The " + actualAssetInstrumentValue + " is printed successfully");
            Assert.assertEquals(expected_asset_instrument, actualAssetInstrumentValue);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Instrument value is not displayed in Reports Filter section" + e.getMessage());
        }
    }

    public void verifyAssetEventTypeValue(String expected_asset_eventType) {
        try {
            String actualAssetEventType = getAssetEventType.getText();
            LOGGER.info("The " + actualAssetEventType + " is printed successfully");
            Assert.assertEquals(expected_asset_eventType, actualAssetEventType);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Asset Event Type value is not displayed in Reports Filter section" + e.getMessage());
        }
    }

    public void verifyAlertInstrumentValue(String expected_alert_instrument) {
        try {
            String actualAlertInstrumentValue = getAlertInstrument.getText();
            LOGGER.info("The " + actualAlertInstrumentValue + " is printed successfully");
            Assert.assertEquals(expected_alert_instrument, actualAlertInstrumentValue);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Alert Instrument value is not displayed in Reports Filter section" + e.getMessage());
        }
    }

    public void verifyAlertEventTypeValue(String expected_alert_eventType) {
        try {
            String actualAlertEventType = getAlerttEventType.getText();
            LOGGER.info("The " + actualAlertEventType + " is printed successfully");
            Assert.assertEquals(expected_alert_eventType, actualAlertEventType);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Alert Event Type value is not displayed in Reports Filter section" + e.getMessage());
        }
    }


    /**
     * Method to verify the window title
     */

    public void getIndexConstituentsWindowTitle(String expected_window_title) {
        try {
            Actions actions = new Actions(driver);
            WebElement elementLocator = driver.findElement(By.xpath("//*[@id='selectReportsTable']/tbody/tr[1]/td[4]"));
            actions.contextClick(elementLocator).perform();
            getWindowTitle.click();
            getWindowTitle.pause(5);
            String actual_window_title = getIndexConstituentsWindowTitle.getText();
            LOGGER.info("The " + actual_window_title + " is displayed");
            Assert.assertEquals(expected_window_title, actual_window_title);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Window title is not displayed" + e.getMessage());
        }
    }

    /**
     * Method to get the report values with search criteria
     *
     * @param search_value
     */
    public void searchValueReport(String search_value) {
        try {
            searchReportValue.isElementPresent();
            searchReportValue.pause(3);
            searchReportValue.getElement().sendKeys(search_value);
            LOGGER.info("search filter criteria is applied successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("search filter criteria is not applied successfully" + e.getMessage());
        }

    }

    /**
     * Method to get the report values with search criteria for Index constituents ,Settlement details and term summary reports
     *
     * @param search_value
     */

    public void searchValue(String search_value) {
        try {
            searchBox.isElementPresent();
            searchBox.pause(3);
            searchBox.getElement().sendKeys(search_value);
            LOGGER.info("search filter criteria is applied successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("search filter criteria is not applied successfully" + e.getMessage());
        }

    }

    /**
     * Method to click on IndexConstituents download report
     */

    public void getReportIndexConstituents() {
        try {
            getReportIndexConstituentsWindowTitle.isElementPresent();
            getReportIndexConstituentsWindowTitle.pause(2);
            getReportIndexConstituentsWindowTitle.click();
            LOGGER.info("Download reports button is clicked successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Index constituents report is not downloaded" + e.getMessage());
        }
    }

    /**
     * Method to click on Show Settlement Detail report
     */

    public void getSettlementWindowTitle(String expected_Settlement_window_title) {
        try {
            Actions actions = new Actions(driver);
            WebElement elementLocator = driver.findElement(By.xpath("//*[@id='selectReportsTable']/tbody/tr[1]/td[4]"));
            actions.contextClick(elementLocator).perform();
            getSettlementDetail.click();
            String actual_Settlement_window_title = getIndexSettlementWindowTitle.getText();
            LOGGER.info("The " + actual_Settlement_window_title + " is displayed");
            Assert.assertEquals(expected_Settlement_window_title, actual_Settlement_window_title);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Settlement Window title is not displayed" + e.getMessage());
        }
    }

    /**
     * Method to click on Term Summary Details report
     */

    public void verifyTermSummaryWindow() {
        try {
            Actions actions = new Actions(driver);
            WebElement elementLocator = driver.findElement(By.xpath("//*[@id='selectReportsTable']/tbody/tr[1]/td[4]"));
            actions.contextClick(elementLocator).perform();
            getTermSummaryDetail.isElementPresent();
            getTermSummaryDetail.click();
            getTermSummaryDetail.pause(3);
            getTermSummaryDetail.rightClick();
            getTermSummaryWindow.isElementPresent();
            getTermSummaryWindow.pause(3);
            getTermSummaryWindow.click();
            LOGGER.info("Term Summary Window is displayed");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Term Summary Window is not displayed" + e.getMessage());
        }
    }

    /**
     * Method to close Index constituents window
     */

    public void closeIndexConstituentsWindow() {
        try {
            closeConstituentsWindow.isElementPresent();
            closeConstituentsWindow.pause(2);
            closeConstituentsWindow.click();
            LOGGER.info("Index Constituents Details window is closed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to Close Index Constituents Details window" + e.getMessage());
        }

    }

    /**
     * Method to verify and navigate to ICD List Window
     */
    public void verifyICDListWindow() {
        try {
            indexTable.isElementPresent();
            indexTable.pause(10);
            indexTable.click();
            indexTable.rightClick();
            showConstituentDetails.click();
            LOGGER.info("ICD Window is opened successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ICD Window is not opened successfully" + e.getMessage());
        }
    }

    /**
     * Method to select the open stock from the ICD list window
     */

    public void selectStockType() {
        try {
            driver.manage().window().maximize();
            tradingSession.pause(3);
            tradingSession.isElementPresent();
            tradingSession.pause(10);
            tradingSession.isElementPresent();
            tradingSession.pause(10);
            tradingSession.doubleClick();
            LOGGER.info("Open stock type is selected successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Open stock type is not selected successfully" + e.getMessage());
        }
    }

    public void waitForTablefirstRow(String tableID) {
        ExtendedWebElement ele = findExtendedWebElement(
                By.xpath("//table[contains(@id,'" + tableID + "')]/tbody/tr[1]"));
        ele.isElementPresent();
    }

    public void switchToFrame(String frameID) {
        driver.switchTo().frame(frameID);
    }

    public void switchOutOfFrame() {
        driver.switchTo().defaultContent();
    }

    /**
     * Method to navigate to Reports tab from ICD list window
     */
    public void navigateToReportTab() {
        try {
            icdTable.isElementPresent();
            icdTable.pause(10);
            icdTable.click();
            icdTable.rightClick();
            reportoption.isElementPresent();
            reportoption.pause(10);
            reportoption.click();
            reportoption.pause(3);
            switchOutOfFrame();
            switchToFrame(reportFrameID);
            LOGGER.info("Navigated to Reports tab successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Not able to navigate to Report Tab" + e.getMessage());
        }
    }

    /**
     * Method to navigate to Reports tab from Index list window
     */

    public void clickReportOption() {
        try {
            int indexTableCount = driver.findElements(By.xpath("//table[@class='display dataTable']//tr//td[1]")).size();
            System.out.println(indexTableCount);
            if (indexTableCount > 0) {
                LOGGER.info("indices are in open state");
            } else {
                LOGGER.info("indices are not in open state");
            }
            indexTable.isElementPresent();
            indexTable.pause(10);
            indexTable.click();
            indexTable.rightClick();
            reportoption.isElementPresent();
            reportoption.click();
            switchOutOfFrame();
            switchToFrame(reportFrameID);
            LOGGER.info("Navigated to Reports tab successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Not able to navigate to Report Tab" + e.getMessage());
        }
    }

    /**
     * Method to hold the stock
     */

    public void holdStock() {
        try {
            icdTable.isElementPresent();
            icdTable.rightClick();
            holdStock.isElementPresent();
            holdStock.click();
            Ok.isElementPresent();
            Ok.click();
            Ok.pause(5);
            LOGGER.info("Stock has been put to hold");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to hold the stock" + e.getMessage());
        }
    }

    /**
     * Method to release the stock
     */

    public void releaseStock() {
        try {
            holdingStatus.isElementPresent();
            holdingStatus.click();
            icdTable.pause(3);
            icdTable.rightClick();
            releaseStockOk.isElementPresent();
            releaseStockOk.click();
            OkStock.isElementPresent();
            OkStock.pause(3);
            OkStock.click();
            LOGGER.info("Stock has been released successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to release the stock" + e.getMessage());
        }
    }

    /**
     * Method to close the settlement window
     */
    public void closeSettlementWindow() {
        try {
            closeSettlementWindow.isElementPresent();
            closeSettlementWindow.click();
            LOGGER.info("Settlement Details window is closed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to Close Settlement Details window" + e.getMessage());
        }
    }

    /**
     * Method to close the term summary window
     */
    public void closeTermSummaryWindow() {
        try {
            closeConstituentsWindow.isElementPresent();
            closeConstituentsWindow.click();
            LOGGER.info("Term summary window is closed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to Close Term summary window" + e.getMessage());
        }
    }

    /**
     * Method to release the Index
     */
    public void releaseIndex() {
        try {
            indexTableFirstRow.pause(5);
            indexTableFirstRow.click();
            indexTableFirstRow.pause(3);
            indexTableFirstRow.rightClick();
            indexTableFirstRow.pause(3);
            releaseIndex.click();
            OkIndex.isElementPresent();
            OkIndex.pause(3);
            OkIndex.click();
            LOGGER.info("Index has beem released successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to release the Index" + e.getMessage());
        }
    }

    /**
     * Method to search value in settlement details window
     *
     * @param search_value
     */
    public void searchValueSettlementDetailsWindow(String search_value) {
        try {
            searchBox.isElementPresent();
            searchBox.pause(3);
            searchBox.getElement().sendKeys(search_value);
            LOGGER.info("search filter criteria is applied successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("search filter criteria is not applied successfully" + e.getMessage());
        }

    }

    /**
     * Method to search value in Term Summary Details window
     *
     * @param search_value
     */
    public void searchValueTermSummaryWindow(String search_value) {
        try {
            searchTermSummaryReportValue.isElementPresent();
            searchTermSummaryReportValue.pause(3);
            searchTermSummaryReportValue.getElement().sendKeys(search_value);
            LOGGER.info("search filter criteria is applied successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("search filter criteria is not applied successfully" + e.getMessage());
        }

    }

    /**
     * Method to download Index Settlement details report
     */

    public void downloadIndexSettlementReport() {
        try {
            getReportIndexConstituentsWindowTitle.isElementPresent();
            getReportIndexConstituentsWindowTitle.click();
            LOGGER.info("Index Settlement details report is downloaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Index Settlement Report details report is not downloaded successfully" + e.getMessage());
        }
    }

    /**
     * Method to download term summary report
     */

    public void downloadTermSummaryReport() {
        try {
            getReportIndexConstituentsWindowTitle.isElementPresent();
            getReportIndexConstituentsWindowTitle.click();
            LOGGER.info("Term summary report is downloaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Term summary report is not downloaded successfully" + e.getMessage());
        }
    }

    /**
     * Method to check the count of records in Settlement Details Report
     *
     * @param LatestFile
     * @return
     * @throws IOException
     */

    public int checkCountIndexSettlementReport(File LatestFile) throws IOException {
        int count = 0;
        BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            count++;
        }
        LOGGER.info("Total number of records in the Index Settlement Details report is :" + count);
        return count;
    }

    /**
     * Method to check the count of records in Term Summary Report
     *
     * @param LatestFile
     * @return
     * @throws IOException
     */
    public int checkCountTermSummaryReport(File LatestFile) throws IOException {
        int count = 0;
        BufferedReader csvReader = new BufferedReader(new FileReader(LatestFile));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            count++;
        }
        LOGGER.info("Total number of records in the Term Summary report is :" + count);
        return count;
    }

    /**
     * Method to select the open Index in Index list window
     */
    public void selectOpenIndex() {
        try {
            indexFilters.isElementPresent();
            LOGGER.info("Filter option is selected successfully");
            indexFilters.click();
            if (advancedSearch.equals("Advanced Search")) {
                LOGGER.info("Advanced Search dialog box is opened successfully");
            } else {
                LOGGER.info("Advanced Search dialog box is not opened successfully");
            }
            statusType.click();
            Select select = new Select(driver.findElement(By.id("statusTypeSelectId")));
            select.selectByIndex(1);
            LOGGER.info(select);
            applyButton.pause(5);
            applyButton.click();
            LOGGER.info("Apply button is clicked successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Open Index is not selected " + e.getMessage());
        }
    }
}