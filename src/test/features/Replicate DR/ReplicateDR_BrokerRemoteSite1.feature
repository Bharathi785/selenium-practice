@ReplicateDR
Feature: Replicate DR

  @TC020001
  Scenario Outline: Validate message enqueued value for the topic ICE_CONTROL when the user performs manual hold(Index) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count for the topic ICE_CONTROL

    Given user logs into Grip application
    And user navigates to Index tab under Research page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And User fetch the current price value of index
    Then User will perform right click action on the index
    Then User will hold index and verify the current value as "<HoldType>"
    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the topic ICE_CONTROL
    And quit driver

    Examples:
      | IndexType        | IndexData | IndexValue     | HoldType   |
      | Index Quick View | 3011703   | Equity Indices | manualHold |

  @TC020002
  Scenario Outline: Validate message enqueued value for the topic ICE_CONTROL when the user performs manual release(Index) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count for the topic ICE_CONTROL

    Given User logs into Grip application
    And user navigates to Index tab under Research page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    Then user will perform right click action and release the index
    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the topic ICE_CONTROL
    And quit driver

    Examples:
      | IndexType        | IndexData | IndexValue     |
      | Index Quick View | 3011703   | Equity Indices |

  @TC020003

  Scenario Outline: Validate message enqueued value for the topic MD_HOLD when the user performs manual hold(Stock) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count for the topic MD_HOLD

    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And user navigates to ICD list Window
    And select Open state stock from ICD list window
    And user will perform right click action and hold the stock
    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the topic MD_HOLD
    And quit driver

    Examples:
      | IndexType        | IndexValue     | IndexData |
      | Index Quick View | Equity Indices | 3011703   |

  @TC020004
  Scenario Outline: Validate message enqueued value for the topic MD_HOLD when the user performs manual release(Stock) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count for the topic MD_HOLD


    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And user navigates to ICD list Window
    And select Open state stock from ICD list window
    And user will release the holded stock
    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the topic MD_HOLD
    And quit driver

    Examples:
      | IndexType        | IndexValue     | IndexData |
      | Index Quick View | Equity Indices | 3011703   |

  @TC020005
  Scenario Outline:  Validate message enqueued value for the topic MD_HOLD when the user performs manual hold(Contracts) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count for the topic MD_HOLD

    Given User logs into Grip application
    And User will fetch time in Dashboard page
    And User navigates to Contracts tab under Research page
    And user selects contracts as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation>"
    Then User will hold contracts and verify the current value as "<HoldType>"
    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the topic MD_HOLD
    And quit driver

    Examples:
      | TradingSession | HoldingStatus | DataFeed   | Operation | HoldType   |
      | Open           | Not Held      | Subscribed | Hold      | manualHold |

  @TC020006
  Scenario Outline:  Validate message enqueued value for the topic MD_HOLD when the user performs manual release(Contracts) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count for the topic MD_HOLD

    Given User logs into Grip application
    And User will fetch time in Dashboard page
    And User navigates to Contracts tab under Research page
    And user selects contracts as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation>"
    Then User will hold contracts and verify the current value as "<HoldType>"
    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the topic MD_HOLD
    And quit driver

    Examples:
      | TradingSession | HoldingStatus | DataFeed   | HoldType      | Operation |
      | Open           | Not Held      | Subscribed | manualRelease | Release   |

  @TC020009
  Scenario Outline: Validate message enqueued value for the topic MD_HOLD when the user performs manual hold(Forex) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count for the topic MD_HOLD

    Given User logs into Grip application
    And User will fetch current time for forex in Dashboard page
    When User navigates to Forex tab under Research page
    Then User searches forex data as "<searchData>" and perform "<Operation1>"
    And User will hold forex and verify the current value as "<holdType>"

    Given User logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the topic MD_HOLD
    And quit driver

    Examples:
      | searchData | Operation1 | holdType   |
      | JPY/EUR    | Hold       | manualHold |

  @TC020010
  Scenario Outline: Validate message enqueued value for the topic MD_HOLD when the user performs manual release(Forex) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count for the topic MD_HOLD

    Given User logs into Grip application
    And User will fetch current time for forex in Dashboard page
    When User navigates to Forex tab under Research page
    Then User searches forex data as "<searchData>" and perform "<Operation>"

    Given User logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Topic link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the topic MD_HOLD
    And quit driver

    Examples:
      | searchData | Operation |
      | JPY/EUR    | Release   |



  @TC012
  Scenario: Validate message enqueued value for the Queue EP_EXCHANGE_SEGMENTS when the user performs Refresh Exchange from T3(Exchange) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count for the Queue EP_EXCHANGE_SEGMENTS

    Given User logs into Grip application
    And User will fecth time in Dashboard page
    And user click on exchange tab
    And user will select Refresh Exchange From T3

    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the Queue EP_EXCHANGE_SEGMENTS
    And quit driver

  @TC013
  Scenario Outline: Validate message enqueued value for the Queue EP_NDO_DATA when the user performs Move To Next Day(Index) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count for the Queue EP_NDO_DATA

    Given User logs into Grip application
    And User will fecth time in Dashboard page
    And User will navigate to Index tab via dashboard link as "<LinkType>"
    And User will select type of index as "<IndexType>"
    When User fetch the current price value of index
    Then User will perform right click action on the index without test data
    And user will select the Move to Next Day option

    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the Queue EP_NDO_DATA
    And quit driver

    Examples:
      | IndexType        | LinkType |
      | Index Quick View | open     |


  @TC014
  Scenario Outline: Validate message enqueued value for the Queue EP_NDO_DATA when the user performs Refresh Index from T3(Index) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count for the Queue EP_NDO_DATA

    Given User logs into Grip application
    And User will fecth time in Dashboard page
    And User will navigate to Index tab via dashboard link as "<LinkType>"
    And User will select type of index as "<IndexType>"
    When User fetch the current price value of index
    Then User will perform right click action on the index without test data
    And user will select the Refresh Index From T3 option

    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the Queue EP_NDO_DATA
    And quit driver

    Examples:
      | IndexType        | LinkType |
      | Index Quick View | open     |

  @TC015
  Scenario Outline: Validate message enqueued value for the Queue ICE_CONTROL when the user performs publish settlement from GRIP(Index) operation
    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count for the Queue ICE_CONTROL

    Given User logs into Grip application
    And User will fecth time in Dashboard page
    And User will navigate to Index tab via dashboard link as "<LinkType>"
    And User will select type of index as "<IndexType>"
    When User fetch the current price value of index
    Then User will perform right click action on the index without test data
    And User publish close from T3 Grip and verify alerts as "<PublishType>"

    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the Queue ICE_CONTROL
    And quit driver

    Examples:
      | IndexType        | LinkType | PublishType                  |
      | Index Quick View | open     | Publish settlement from GRIP |

  @TC016 @TC017ReplicateDR
  Scenario Outline: Validate message enqueued value for the Queue EP_INDEX_LEVELS when the user performs publish from T3 and publish from GRIP (Index) operation

    Given user logs into Grip application
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count for the Queue EP_INDEX_LEVELS

    Given User logs into Grip application
    And User will fecth time in Dashboard page
    And User will navigate to Index tab via dashboard link as "<LinkType>"
    And User will select type of index as "<IndexType>"
    When User fetch the current price value of index
    Then User will perform right click action on the index without test data
    And User publish close from T3 Grip and verify alerts as "<PublishType>"

    And user navigates to HomePage
    And user clicks on ActiveMQ Console link in supportTab
    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
    When user clicks on Queue link in ActiveMQ Queue page
    Then user should validate message enqueued count has been incremented to 1 for the Queue EP_INDEX_LEVELS
    And quit driver

    Examples:
      | IndexType        | LinkType | PublishType |
      | Index Quick View | open     | from T3     |
      | Index Quick View | open     | from Grip   |

#  @TC016 @TC019ReplicateDR
#  Scenario Outline: Validate message enqueued value for the Queue EP_INDEX_LEVELS when the user performs publish from T3 and publish from GRIP (Index) operation
#
#    Given user logs into Grip application
#    And user clicks on ActiveMQ Console link in supportTab
#    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
#    When user clicks on Queue link in ActiveMQ Queue page
#    Then user should validate message enqueued count for the Queue EP_NDO_DATA
#
#    Given User logs into Grip application
#    And User will fecth time in Dashboard page
#    And User will navigate to Index tab via dashboard link as "<LinkType>"
#    And User will select type of index as "<IndexType>"
#    When User fetch the current price value of index
#    Then User will perform right click action on the index without test data
#    And user will select the Refresh Index From T3 option
#
#    And user navigates to HomePage
#    And user clicks on ActiveMQ Console link in supportTab
#    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
#    When user clicks on Queue link in ActiveMQ Queue page
#    Then user should validate message enqueued count has been incremented to 1 for the Queue EP_NDO_DATA
#    And quit driver
#
#
#
#  @TC016 @TC020ReplicateDR
#  Scenario Outline: Validate message enqueued value for the Queue EP_INDEX_LEVELS when the user performs publish from T3 and publish from GRIP (Index) operation
#
#    Given user logs into Grip application
#    And user clicks on ActiveMQ Console link in supportTab
#    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
#    When user clicks on Queue link in ActiveMQ Queue page
#    Then user should validate message enqueued count for the Queue EP_INDEX_LEVELS
#
#    Given User logs into Grip application
#    And User will fecth time in Dashboard page
#    And User will navigate to Index tab via dashboard link as "<LinkType>"
#    And User will select type of index as "<IndexType>"
#    When User fetch the current price value of index
#    Then User will perform right click action on the index without test data
#    And User publish close from T3 Grip and verify alerts as "<PublishType>"
#
#    And user navigates to HomePage
#    And user clicks on ActiveMQ Console link in supportTab
#    And user clicks on Broker RemoteSite1 link in ActiveMQ Console Links page
#    When user clicks on Queue link in ActiveMQ Queue page
#    Then user should validate message enqueued count has been incremented to 1 for the Queue EP_INDEX_LEVELS
#    And quit driver
#
#    Examples:
#      | IndexType        | LinkType | PublishType |
#      | Index Quick View | open     | from T3     |
#      | Index Quick View | open     | from Grip   |