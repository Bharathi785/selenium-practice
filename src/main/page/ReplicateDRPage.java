package com.grip.page;


import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

import static com.grip.page.IndicesPage.*;


public class ReplicateDRPage extends AbstractPage {
    Logger LOGGER = Logger.getLogger(ReplicateDRPage.class);

    public ReplicateDRPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public static int ICECONTROLCOUNT, MDHOLDCOUNT, EPExchangeSegmentsCOUNT, EPNDODATACOUNT, EPINDEXLEVELSCOUNT, ExchangeOpen;

    /**
     * Locatos for Replciate DR module
     */

    //Click on Research tab

    @FindBy(xpath = "//a[contains(@href,'resea')]")
    private ExtendedWebElement research;

    //click on support tab
    @FindBy(xpath = "//a[contains(@href,'supportTab')]")
    private ExtendedWebElement support;

    //Click on Index tab
    @FindBy(xpath = "//span[contains(@title,'Open a new Index')]")
    private ExtendedWebElement index;

    //Index Manual Hold
    @FindBy(xpath = "//span[text()='Hold']")
    private ExtendedWebElement Hold;

    //Index Manual Release
    @FindBy(xpath = "//span[text()='Release']")
    private ExtendedWebElement Release;

    //Click on Stock tab
    @FindBy(xpath = "//span[contains(@title,'Open a new Stock')]")
    private ExtendedWebElement stock;

    //Click on Contracts tab
    @FindBy(xpath = "//span[contains(@title,'Open a new Contract Window')]")
    private ExtendedWebElement contracts;

    //Click on Fixed Income
    @FindBy(xpath = "//span[contains(@title,'Open a new Fixed Income Securities Window')]")
    private ExtendedWebElement fixedIncome;

    //Click on Forex
    @FindBy(xpath = "//span[contains(@title,'Open a new Forex Window')]")
    private ExtendedWebElement forex;

    // MoveToNext Day
    @FindBy(xpath = "//span[text()='Move To Next Day']")
    private ExtendedWebElement MoveToNextDay;

    //Click on Exchange tab
    @FindBy(xpath = "//spancontains[@title,'Open a new Exchange Window']")
    private ExtendedWebElement Exchange;

    //Refresh Exchange From T3
    @FindBy(xpath = "//span[text()='Refresh From T3']")
    private ExtendedWebElement refreshFromT3;

    //Publish settlement from GRIP (Index)
    @FindBy(xpath = "//span[text()='Refresh From T3']")
    private ExtendedWebElement publishSettlementFromGRIP;

    @FindBy(id = "indexHoldTypeManualId")
    private ExtendedWebElement manualReleaseRadioButton;

    @FindBy(xpath = "//span[text()='Ok']")
    public ExtendedWebElement indexHoldOk;

    //Publish Close from GRIP
    @FindBy(xpath = "//span[contains(text(),'Publish Close from GRIP')]")
    private ExtendedWebElement publishCloseFromGrip;

    //Publish Close From T3
    @FindBy(xpath = "//span[contains(text(),'Publish Close from T3')]")
    private ExtendedWebElement publishCloseFromT3;

    //ActiveMQ console
    @FindBy(xpath = "//*[@id='activeMQConsoleTableId']/div/div/table/tbody/tr/td/a")
    private ExtendedWebElement activeMQConsole;

    //BrokerRemoteSite1
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td[2]/a")
    private ExtendedWebElement brokerRemoteSite1;

    //BrokerRemoteSite2
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td[3]/a")
    private ExtendedWebElement brokerRemoteSite2;

    @FindBy(id = "holdIndexValueId")
    private ExtendedWebElement holdIndexCurrentValue;

    //Topic
    @FindBy(linkText = "Topics")
    private ExtendedWebElement Topic;

    //Queues
    @FindBy(linkText = "Queues")
    private ExtendedWebElement Queue;

    //messageEnqueueMRICECONTROL
    @FindBy(xpath = "//*[@id='topics']/tbody/tr[15]/td[3]")
    private ExtendedWebElement ICECONTROL;

    @FindBy(xpath = "//*[@id='queues']/tbody/tr[31]/td[4]")
    private ExtendedWebElement ICECONTROLQueue;

    //messageEnqueueMDHOLD
    @FindBy(xpath = "//*[@id='topics']/tbody/tr[17]/td[3]")
    private ExtendedWebElement MDHOLD;

    //messageEnqueueEPExchangeSegments
    @FindBy(xpath = "//*[@id='queues']/tbody/tr[7]/td[4]")
    private ExtendedWebElement EPExchangeSegments;

    //messageEnqueueEPNDODATA
    @FindBy(xpath = "//*[@id='queues']/tbody/tr[22]/td[4]")
    private ExtendedWebElement EPNDODATA;

    //messageEnqueueEPINDEXLEVELS
    @FindBy(xpath = "//*[@id='queues']/tbody/tr[16]/td[4]")
    private ExtendedWebElement EPINDEXLEVELS;


    @FindBy(xpath = "//h3[contains(@class,'headerfont')]")
    private ExtendedWebElement indexFamily;

    @FindBy(id = "indexViewsList")
    private ExtendedWebElement indexDropdown;

    @FindBy(xpath = "//*[starts-with(@id,'indexTable')]//input[contains(@title,'search text')]")
    private ExtendedWebElement indexSearchTextBox;

    @FindBy(xpath = "//table[@class='display dataTable']//tr//td[6]")
    private ExtendedWebElement indexData;

    @FindBy(xpath = "//span[contains(@data-bind,'iClose')]")
    public ExtendedWebElement dashboardClose;

    @FindBy(xpath = "(//span[contains(@data-bind,'Open')])[position()=1]")
    public ExtendedWebElement dashboardOpen;

    @FindBy(xpath = "//span[@data-bind='text: iPrelimClose']")
    private ExtendedWebElement dashboardPrelimClose;

    @FindBy(xpath = "//span[text()='Release']")
    private ExtendedWebElement release;

    @FindBy(xpath = "//span[text()='Hold']")
    private ExtendedWebElement indexHold;

    @FindBy(xpath = "//div[@aria-describedby='releaseIndexDialog']//div[11]//div//button//span")
    private ExtendedWebElement releaseIndexOk;

    @FindBy(xpath = "//span[@data-bind='text: eOpen']")
    public ExtendedWebElement ExchangeClick;

    @FindBy(xpath = "//a[contains(text(),'ActiveMQ Console')]")
    public ExtendedWebElement ActiveMQ;

    @FindBy(xpath = "//span[contains(text() ,'Move to Next Day')]")
    public ExtendedWebElement moveToNextDay;

    @FindBy(xpath = "//span[contains(text(),'Move To Next Day')]")
    public ExtendedWebElement moveToNextDayExchange;

    @FindBy(xpath = "//span[contains(text(),'Refresh from T3')]")
    public ExtendedWebElement RefreshfromT3;

    @FindBy(xpath = "/html/body/ul/li[7]/span")
    public ExtendedWebElement RefreshExchangefromT3;

    @FindBy(xpath = "//span[contains(text(),'Ok')]")
    public ExtendedWebElement ok;

    @FindBy(xpath = "//span[contains(@title,'Open a new Exchange Window')]")
    private ExtendedWebElement exchangeTab;

    @FindBy(xpath = "//div[starts-with(@id,'exchangeTable')]//div[@class='dataTables_scrollBody']//tr//td[1]")
    private ExtendedWebElement exchangeFirstRow;


    /**
     * Method to click on ActiveMQ Console link in support page
     *
     * @throws Exception
     * @author balaji
     */
    public void activeMQ() throws Exception {
        Thread.sleep(3000);
        driver.switchTo().frame("supportframe");
        ActiveMQ.isElementPresent();
        ActiveMQ.pause(10);
        String mainWindow = driver.getWindowHandle();
        ActiveMQ.click();
        Thread.sleep(2000);
        Set<String> set = driver.getWindowHandles();
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()) {
            String childWindow = itr.next();
            if (!mainWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                LOGGER.info(driver.switchTo().window(childWindow).getTitle());
            }
        }
    }

    /**
     * Method to click on Broker Remote Site 1 QA box in Active MQ page
     *
     * @author balaji
     */

    public void clickBrokerRemoteSite1() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(brokerRemoteSite1.isElementPresent());
        brokerRemoteSite1.pause(10);
        String mainWindow = driver.getWindowHandle();
        brokerRemoteSite1.click();
        Thread.sleep(1000);
        Set<String> set = driver.getWindowHandles();
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()) {
            String childWindow = itr.next();
            if (!mainWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                LOGGER.info(driver.switchTo().window(childWindow).getTitle());
            }
        }
    }

    /**
     * Method to click on Broker Remote Site 2 QA box in Active MQ page
     *
     * @author balaji
     */

    public void clickBrokerRemoteSite2() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(brokerRemoteSite2.isElementPresent());
        brokerRemoteSite2.pause(10);
        String mainWindow = driver.getWindowHandle();
        WebElement ele = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td[3]/a"));
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
        brokerRemoteSite2.click();
        Thread.sleep(1000);
        Set<String> set = driver.getWindowHandles();
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()) {
            String childWindow = itr.next();
            if (!mainWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                LOGGER.info(driver.switchTo().window(childWindow).getTitle());
            }
        }
    }

    /**
     * Method to click on topic
     *
     * @author balaji
     */
    public void clickTopic() {
        try {
            Assert.assertTrue(Topic.isElementPresent());
            Topic.pause(10);
            Topic.click();
            String expectedPageTitle = "Broker-1 : Topics";
            String actualPageTitle = driver.getTitle();
            LOGGER.info("The actual Page Title is: " + actualPageTitle + " ");
            Assert.assertEquals(expectedPageTitle, actualPageTitle);
            LOGGER.info("Topic link is clicked successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on topic link" + e.getMessage());
        }
    }

    /**
     * Method to click on Queue
     *
     * @author balaji
     */
    public void clickQueue() {
        try {
            Assert.assertTrue(Queue.isElementPresent());
            Queue.pause(10);
            Queue.isElementPresent();
            Queue.click();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Queue link" + e.getMessage());
        }
    }

    /**
     * Method to validate the messageEnqueue before performing the operation Hold Index
     *
     * @author balaji
     */

    public void validateMessageCountICECONTROL() {
        try {
            ICECONTROL.isElementPresent();
            ICECONTROLCOUNT = Integer.parseInt(ICECONTROL.getText());
            LOGGER.info("The count of " + ICECONTROLCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateMessageCountQueueICECONTROL() {
        try {
            ICECONTROLQueue.isElementPresent();
            ICECONTROLCOUNT = Integer.parseInt(ICECONTROLQueue.getText());
            LOGGER.info("The count of " + ICECONTROLCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateMessageCountMDHold() {
        try {
            Assert.assertTrue(MDHOLD.isElementPresent());
            MDHOLDCOUNT = Integer.parseInt(MDHOLD.getText());
            LOGGER.info("The count of " + MDHOLDCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateMessageCountEPExchangeSegments() {
        try {
            Assert.assertTrue(EPExchangeSegments.isElementPresent());
            EPExchangeSegmentsCOUNT = Integer.parseInt(EPExchangeSegments.getText());
            LOGGER.info("The count of " + EPExchangeSegmentsCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateMessageCountEPNDODATA() {
        try {
            Assert.assertTrue(EPNDODATA.isElementPresent());
            EPNDODATACOUNT = Integer.parseInt(EPNDODATA.getText());
            LOGGER.info("The count of " + EPNDODATACOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateMessageCountEPINDEXLEVELS() {
        try {
            Assert.assertTrue(EPINDEXLEVELS.isElementPresent());
            EPINDEXLEVELSCOUNT = Integer.parseInt(EPINDEXLEVELS.getText());
            LOGGER.info("The count of " + EPINDEXLEVELSCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }


    public void validateIncrementedMessageCountICECONTROL() {
        try {
            Assert.assertTrue(ICECONTROL.isElementPresent());
            ICECONTROLCOUNT = Integer.parseInt(ICECONTROL.getText());
            LOGGER.info("The count of " + ICECONTROLCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateIncrementedMessageCountQueueICECONTROL() {
        try {
            ICECONTROLQueue.isElementPresent();
            ICECONTROLCOUNT = Integer.parseInt(ICECONTROLQueue.getText());
            LOGGER.info("The count of " + ICECONTROLCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateIncrementedMessageCountMDHold() {
        try {
            Assert.assertTrue(MDHOLD.isElementPresent());
            MDHOLDCOUNT = Integer.parseInt(MDHOLD.getText());
            LOGGER.info("The count of " + MDHOLDCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateIncrementedMessageCountEPExchangeSegments() {
        try {
            Assert.assertTrue(EPExchangeSegments.isElementPresent());
            EPExchangeSegmentsCOUNT = Integer.parseInt(EPExchangeSegments.getText());
            LOGGER.info("The count of " + EPExchangeSegmentsCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the queue" + e.getMessage());
        }
    }

    public void validateIncrementedMessageCountEPNDODATA() {
        try {
            Assert.assertTrue(EPNDODATA.isElementPresent());
            EPNDODATACOUNT = Integer.parseInt(EPNDODATA.getText());
            LOGGER.info("The count of " + EPNDODATACOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void validateIncrementedMessageCountEPINDEXLEVELS() {
        try {
            Assert.assertTrue(EPINDEXLEVELS.isElementPresent());
            EPINDEXLEVELSCOUNT = Integer.parseInt(EPINDEXLEVELS.getText());
            LOGGER.info("The count of " + EPINDEXLEVELSCOUNT + " is printed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to get the message enqueue count for the topic" + e.getMessage());
        }
    }

    public void waitForTablefirstRow(String tableID) {
        ExtendedWebElement ele = findExtendedWebElement(
                By.xpath("//table[contains(@id,'" + tableID + "')]/tbody/tr[1]"));
        ele.isElementPresent();
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

    public void stockHold() {
        try {
            stock.isElementPresent();
            stock.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Stock tab" + e.getMessage());
        }
    }

    public void contractHold() {
        try {
            contracts.isElementPresent();
            contracts.click();
            indexHold.isElementPresent();
            indexHold.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Stock tab" + e.getMessage());
        }
    }

    public void fixedIncomeHold() {
        try {
            fixedIncome.isElementPresent();
            fixedIncome.click();
            indexHold.isElementPresent();
            indexHold.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Stock tab" + e.getMessage());
        }
    }

    public void forexHold() {
        try {
            forex.isElementPresent();
            forex.click();
            indexHold.isElementPresent();
            indexHold.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Stock tab" + e.getMessage());
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


    public void clickMoveToNextDay() {
        try {
            moveToNextDay.isElementPresent();
            moveToNextDay.pause(3);
            moveToNextDay.click();
            moveToNextDay.pause(3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Move to Next Day Option" + e.getMessage());
        }
    }

    public void clickmoveToNextDayExchange() {
        try {
            moveToNextDayExchange.isElementPresent();
            moveToNextDayExchange.pause(3);
            moveToNextDayExchange.click();
            moveToNextDayExchange.pause(3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Move to Next Day Option" + e.getMessage());
        }
    }

    public void clickRefreshExchangeFromT3() {
        try {
            RefreshExchangefromT3.isElementPresent();
            RefreshExchangefromT3.pause(3);
            RefreshExchangefromT3.click();
            RefreshExchangefromT3.pause(3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Refresh Exchange From T3 Option" + e.getMessage());
        }
    }

    public void clickRefreshIndexFromT3() {
        try {
            RefreshfromT3.isElementPresent();
            RefreshfromT3.pause(3);
            RefreshfromT3.click();
            RefreshfromT3.pause(3);
            ok.click();
            ok.pause(3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on RefreshIndex fromT3 Option" + e.getMessage());
        }
    }


    public void selectFirstRowInExchangeTable() {
        try {
            exchangeFirstRow.isElementPresent();
            exchangeFirstRow.pause(3);
            exchangeFirstRow.rightClick();
            exchangeFirstRow.pause(3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on First row in Exchange Table" + e.getMessage());
        }
    }

    public void clickExchange() {
        try {
            exchangeTab.isElementPresent();
            exchangeTab.pause(3);
            exchangeTab.click();
            exchangeTab.pause(3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Exchange tab" + e.getMessage());
        }
    }

    public void switchToFrame(String frameID) {
        driver.switchTo().frame(frameID);
    }

    public void switchOutOfFrame() {
        driver.switchTo().defaultContent();
    }

    public void releaseIndex() {
        try {
            if (fetchTableColumnValues.get("calcStatusValue").contains("Hold")) {
                release.isElementPresent();
                release.click();
            } else {
                Assert.fail("The index calc. status was in hold state");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to release the holded index-" + e.getMessage());
        }
    }

    public void releaseStock() {
        try {
            if (fetchTableColumnValues.get("calcStatusValue").contains("Hold")) {
                release.isElementPresent();
                release.click();
            } else {
                Assert.fail("The index calc. status was in hold state");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to release the holded Stock-" + e.getMessage());
        }
    }

    public void releaseContract() {
        try {
            if (fetchTableColumnValues.get("calcStatusValue").contains("Hold")) {
                release.isElementPresent();
                release.click();
            } else {
                Assert.fail("The index calc. status was in hold state");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to release the holded Stock-" + e.getMessage());
        }
    }

    public void releaseFixedIncome() {
        try {
            if (fetchTableColumnValues.get("calcStatusValue").contains("Hold")) {
                release.isElementPresent();
                release.click();
            } else {
                Assert.fail("The index calc. status was in hold state");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to release the holded Stock-" + e.getMessage());
        }
    }


    public void releaseForex() {
        try {
            if (fetchTableColumnValues.get("calcStatusValue").contains("Hold")) {
                release.isElementPresent();
                release.click();
            } else {
                Assert.fail("The index calc. status was in hold state");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to release the holded Stock-" + e.getMessage());
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

    public void clickDashboardTab(String dashboardLinkType) {
        try {
            dasboardLink = dashboardLinkType;
            switch (dasboardLink) {
                case "close":
                    if (initialClosecount > 0)
                        dashboardClose.click();
                    else
                        Assert.fail("None of the index are in closed state");
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
                        Assert.fail("None of the index are in prelim closed state");
                    break;

                default:
                    break;
            }
            waitForTablefirstRow(indexTableID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Dashboard states link-" + e.getMessage());
        }
    }

    public void clickExchangeTab() {
        if (ExchangeOpen > 0) {
            ExchangeClick.click();
        } else {
            Assert.fail("None of the index are in open state");
        }
    }

    public void clickSupportTab() {
        try {
            support.isElementPresent();
            support.click();
            LOGGER.info("Support tab is clicked successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on support tab" + e.getMessage());
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

    public void clickStock() {
        try {
            stock.isElementPresent();
            stock.click();
            LOGGER.info("Stock tab is clicked successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on Stock tab-" + e.getMessage());
        }
    }

    public void quitDriver() {
        driver.quit();
    }
}