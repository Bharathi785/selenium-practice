package steps;


import com.grip.page.ReplicateDRPage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;


public class ReplicateDR extends CucumberRunner {

    Logger LOGGER = Logger.getLogger(ReplicateDRPage.class);

    ReplicateDRPage replicateDRPage = new ReplicateDRPage(getDriver());

    /**
     * Step definition Methods for Replicate DR module
     */

    @Given("^user logs into Grip application$")
    public void user_logs_into_Grip_application() throws Throwable {
        replicateDRPage.open();
    }

    @And("^user clicks on ActiveMQ Console link in supportTab$")
    public void clickActiveMQ() throws Throwable {
        replicateDRPage.activeMQ();
    }

    @And("^user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page$")
    public void clickBrokerRemoteSite1() throws Throwable {
        replicateDRPage.clickBrokerRemoteSite1();
    }

    @And("^user clicks on Broker RemoteSite2 link in ActiveMQ Console Links page$")
    public void clickBrokerRemoteSite2() throws Throwable {
        replicateDRPage.clickBrokerRemoteSite2();
    }

    @And("^user clicks on Topic link in ActiveMQ Queue page$")
    public void clickTopic() throws Throwable {
        replicateDRPage.clickTopic();
    }

    @And("^user clicks on Queue link in ActiveMQ Queue page$")
    public void clickQueue() throws Throwable {
        replicateDRPage.clickQueue();
    }

    @Then("^user should validate message enqueued count for the topic ICE_CONTROL$")
    public void validateMessageCountICECONTROL() throws Throwable {
        replicateDRPage.validateMessageCountICECONTROL();
    }

    @Then("^user should validate message enqueued count has been incremented to 1 for the topic ICE_CONTROL$")
    public void validateIncrementedMessageCountICECONTROL() throws Throwable {
        replicateDRPage.validateIncrementedMessageCountICECONTROL();
    }

    @Then("^user should validate message enqueued count for the topic MD_HOLD$")
    public void validateMessageCountMDHold() throws Throwable {
        replicateDRPage.validateMessageCountMDHold();
    }

    @Then("^user should validate message enqueued count has been incremented to 1 for the topic MD_HOLD$")
    public void validateIncrementedMessageCountMDHold() throws Throwable {
        replicateDRPage.validateIncrementedMessageCountMDHold();
    }

    @Then("^user should validate message enqueued count for the Queue EP_EXCHANGE_SEGMENTS$")
    public void validateMessageCountEPExchangeSegments() throws Throwable {
        replicateDRPage.validateMessageCountEPExchangeSegments();
    }

    @Then("^user should validate message enqueued count has been incremented to 1 for the Queue EP_EXCHANGE_SEGMENTS$")
    public void validateIncrementedMessageCountEPExchangeSegments() throws Throwable {
        replicateDRPage.validateIncrementedMessageCountEPExchangeSegments();
    }

    @Then("^user should validate message enqueued count for the Queue ICE_CONTROL$")
    public void validateMessageCountQueueICECONTROL() throws Throwable {
        replicateDRPage.validateMessageCountQueueICECONTROL();
    }

    @Then("^user should validate message enqueued count has been incremented to 1 for the Queue ICE_CONTROL$")
    public void validateIncrementedMessageCountQueueICECONTROL() throws Throwable {
        replicateDRPage.validateIncrementedMessageCountQueueICECONTROL();
    }

    @Then("^user should validate message enqueued count for the Queue EP_NDO_DATA$")
    public void validateMessageCountEPNDODATA() throws Throwable {
        replicateDRPage.validateMessageCountEPNDODATA();
    }

    @Then("^user should validate message enqueued count has been incremented to 1 for the Queue EP_NDO_DATA$")
    public void validateIncrementedMessageCountEPNDODATA() throws Throwable {
        replicateDRPage.validateIncrementedMessageCountEPNDODATA();
    }

    @Then("^user should validate message enqueued count for the Queue EP_INDEX_LEVELS$")
    public void validateMessageCountEPINDEXLEVELS() throws Throwable {
        replicateDRPage.validateMessageCountEPINDEXLEVELS();
    }

    @Then("^user should validate message enqueued count has been incremented to 1 for the Queue EP_INDEX_LEVELS$")
    public void validateIncrementedMessageCountEPINDEXLEVELS() throws Throwable {
        replicateDRPage.validateIncrementedMessageCountEPINDEXLEVELS();
    }

    @Given("^user navigates to Index tab via dashboard link as \"([^\"]*)\"$")
    public void clickDashboardTab(String dashboardLinkType) throws Throwable {
        replicateDRPage.clickDashboardTab(dashboardLinkType);
    }

    @And("^user hold index as\"([^\"]*)\"$")
    public void holdIndex() {
        replicateDRPage.clickHold();
    }

    @And("^user release index as\"([^\"]*)\"$")
    public void releaseIndex() {
        replicateDRPage.releaseIndex();
        replicateDRPage.clickReleaseOk();
    }


    @And("^user navigates to Stock tab and hold index as\"([^\"]*)\"$")
    public void stockHold() {
        replicateDRPage.clickResearch();
        replicateDRPage.stockHold();
    }

    @And("^user navigates to Stock tab and release index as\"([^\"]*)\"$")
    public void releaseStock() {
        replicateDRPage.clickResearch();
        replicateDRPage.releaseStock();
    }

    @And("^user navigates to contract tab and hold index as\"([^\"]*)\"$")
    public void contractHold() {
        replicateDRPage.clickResearch();
        replicateDRPage.contractHold();
    }

    @And("^user navigates to contract tab and release index as\"([^\"]*)\"$")
    public void releaseContract() {
        replicateDRPage.clickResearch();
        replicateDRPage.releaseContract();
    }

    @And("^user navigates to Fixed Income tab and hold index as\"([^\"]*)\"$")
    public void fixedIncomeHold() {
        replicateDRPage.clickResearch();
        replicateDRPage.fixedIncomeHold();
    }

    @And("^user navigates to Fixed Income tab and release index as\"([^\"]*)\"$")
    public void releaseFixedIncome() {
        replicateDRPage.clickResearch();
        replicateDRPage.releaseFixedIncome();
    }

    @And("^user navigates to Forex tab and hold index as\"([^\"]*)\"$")
    public void forexHold() {
        replicateDRPage.clickResearch();
        replicateDRPage.forexHold();
    }

    @And("^user navigates to Forex tab and release index as\"([^\"]*)\"$")
    public void releaseForex() {
        replicateDRPage.clickResearch();
        replicateDRPage.releaseForex();
    }

//    @And("^user navigates to Exchange tab via dashboard link$")
//    public void clickExchangeTab() {
//        replicateDRPage.clickExchangeTab();
//    }

    @And("^user clicks on support tab$")
    public void clickSupportTab() {
        replicateDRPage.clickSupportTab();
    }

    @And("^user navigates to Index tab under Research page$")
    public void clickResearchTab() {
        replicateDRPage.clickResearch();
        replicateDRPage.clickIndex();
    }

    @And("^user navigates to stock tab under Research page$")
    public void clickStockTab() {
        replicateDRPage.clickResearch();
        replicateDRPage.clickStock();
    }

    @And("^user navigates to HomePage$")
    public void homePage() {
        replicateDRPage.open();
    }

    @And("^user will select the Move to Next Day option$")
    public void selectMoveToNextDay() {
        replicateDRPage.clickMoveToNextDay();
    }

    @And("^user will select the Refresh Index From T3 option$")
    public void selectRefreshIndexFromT3() {
        replicateDRPage.clickRefreshIndexFromT3();
    }

    @And("^user click on exchange tab$")
    public void clickExchangeTab() {
        replicateDRPage.clickResearch();
        replicateDRPage.clickExchange();
    }

    @And("^user will select move to next day option$")
    public void clickMoveToNextDay() {
        replicateDRPage.selectFirstRowInExchangeTable();
        replicateDRPage.clickmoveToNextDayExchange();
    }

    @And("^user will select Refresh Exchange From T3$")

    public void clickRefreshExchangeFromT3() {
        replicateDRPage.selectFirstRowInExchangeTable();
        replicateDRPage.clickRefreshExchangeFromT3();
    }

    @And("^quit driver$")
    public void quitDriver() {
        replicateDRPage.quitDriver();
    }
}