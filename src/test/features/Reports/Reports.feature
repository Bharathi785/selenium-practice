@Reports
Feature: As a GRIP user I want to generate the following reports Index Ticks, Index ICDs,Asset Time &Sales ,Alerts and Related Alerts

  @TC017001
  Scenario Outline: Verify Index Ticks record is generated in report with valid values
    Given User logs into Grip application
    When user clicks on the reports tab
    And Index "<Index>"is passed to Filter Report section
    And click on Index Ticks option
    And select decrement option in Filter Report section
    And Minutes "<Minutes>"is passed to Filter Report section
    Then records should be generated in report
    And validate the records in Filter Report section
    And verify the instrument value "<expected_instrument>"in Reports Filter section
    And verify the event type value "<expected_eventType>"in Reports Filter section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database

    Examples:
      | Index   | Minutes | expected_instrument | expected_eventType |
      | 3011703 | 1       | S&P GLOBAL 1200     | Index Ticks        |


  @TC017002
  Scenario Outline: Verify Index Ticks along with Index ICDs record is generated in report with valid values
    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And user navigates to ICD list Window
    And select Open state stock from ICD list window
    And user navigates to reports tab
    And Index "<Index>"is passed to Filter Report section
    And uncheck Asset Time and Sales option
    And select decrement option in Filter Report section
    And Minutes "<Minutes>"is passed to Filter Report section
    Then records should be generated in report
    And validate the records in Filter Report section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database

    Examples:
      | Index   | IndexType        | IndexValue     | IndexData | Minutes |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       |


  @TC017003
  Scenario Outline: Verify Index Ticks along with Index ICDs & Asset Ticks record is generated in report with valid values
    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And user navigates to ICD list Window
    And select Open state stock from ICD list window
    And user navigates to reports tab
    And Index "<Index>"is passed to Filter Report section
    And select decrement option in Filter Report section
    And Minutes "<Minutes>"is passed to Filter Report section
    Then records should be generated in report
    And validate the records in Filter Report section
    And validate the records in Filter Report section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database

    Examples:
      | Index   | IndexType        | IndexValue     | IndexData | Minutes |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       |

  @TC017004
  Scenario Outline: Verify Index Ticks along with Index ICDs,Asset Ticks & Alert record is generated in report with valid values
    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And User fetch the current price value of index
    Then User will perform right click action on the index
    Then User will hold index and verify the current value as "<HoldType1>"
    And user navigates to ICD list Window
    And select Open state stock from ICD list window
    And user navigates to reports tab
    And Index "<Index>"is passed to Filter Report section
    And click on Alerts option
    And select decrement option in Filter Report section
    And Minutes "<Minutes>"is passed to Filter Report section
    Then records should be generated in report
    And validate the records in Filter Report section
    And validate the records in Filter Report section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database

    #Release the Index
    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    Then user will perform right click action and release the index


    Examples:
      | Index   | IndexType        | IndexValue     | IndexData | Minutes | HoldType1  |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | manualHold |


  @TC017005
  Scenario Outline: Verify Index Ticks along with Index ICDs,Asset Ticks, Alert and Related Alerts is generated in report with valid values
    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And User fetch the current price value of index
    Then User will perform right click action on the index
    Then User will hold index and verify the current value as "<HoldType1>"
    And user navigates to ICD list Window
    And select Open state stock from ICD list window
    And user will perform right click action and hold the stock
    And user navigates to reports tab
    And Index "<Index>"is passed to Filter Report section
    And click on Alerts option
    And click on Related Alerts option
    And select decrement option in Filter Report section
    And Minutes "<Minutes>"is passed to Filter Report section
    Then records should be generated in report
    And validate the records in Filter Report section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database

        #Release the Index
    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    Then user will perform right click action and release the index

      #Release the Stock
    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And user navigates to ICD list Window
    And select Open state stock from ICD list window
    And user will release the holded stock

    Examples:
      | Index   | IndexType        | IndexValue     | IndexData | Minutes | HoldType1  |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | manualHold |


  @TC017006 @TC017007 @TC017008 @TC017009 @TC017010 @TC017011 @TC017012 @TC017013
  Scenario Outline: Verify Index Ticks and Index ICD reports are generated with search criteria
    Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And user navigates to ICD list Window
    And select Open state stock from ICD list window
    And user navigates to reports tab
    And Index "<Index>"is passed to Filter Report section
    And uncheck Asset Time and Sales option
    And select decrement option in Filter Report section
    And Minutes "<Minutes>"is passed to Filter Report section
    Then records should be generated in report
    And validate the records in Filter Report section
    And search value "<search_value>" in Filter Report section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database

    Examples:
      | Index   | IndexType        | IndexValue     | IndexData | Minutes | search_value     |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | S&P Global 1200  |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | Index Ticks      |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | Index ICDs       |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | Mkt              |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | Forex Rate       |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | Forex Price Type |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | Price Type       |
      | 3011703 | Index Quick View | Equity Indices | 3011703   | 1       | Price            |

  @TC017014 @TC017015 @TC017016
  Scenario Outline:
  a)Verify Index constituents Details window open with all valid records
  b)Verify Window should be filtered with given criteria
  c)Verify Report should be generated with all available records with valid filtered data
    Given User logs into Grip application
    When user clicks on the reports tab
    And Index "<Index>"is passed to Filter Report section
    And click on Index Ticks option
    And select decrement option in Filter Report section
    And Minutes "<Minutes>"is passed to Filter Report section
    Then records should be generated in report
    And validate the records in Filter Report section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database
    And verify the title "<expected_window_title>" of the Index tick record
    And search value "<search_value>" in index constituents details window
    And download the index constituents details report
    And check the count in index constituents details report
    And close the index constituents details window

    Examples:
      | Index   | Minutes | expected_window_title                       | search_value |
      | 3011703 | 1       | Index Constituents Details: S&P GLOBAL 1200 | china        |

  @TC017017 @TC017018 @TC017019
  Scenario Outline:
  a)Verify Index settlements Details is open with all valid records
  b)Verify Window should be filtered with given criteria
  c)Report should be generated with all available records with valid filtered data
    Given User logs into Grip application
    When user clicks on the reports tab
    And Index "<Index>"is passed to Filter Report section
    And click on Index Ticks option
    And select decrement option in Filter Report section
    And Minutes "<Minutes>"is passed to Filter Report section
    Then records should be generated in report
    And validate the records in Filter Report section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database
    And verify the settlement window title "<expected_Settlement_window_title>"of the Index tick record
    And search value "<search_value>" in index settlement details window
    And download the index settlement details report
    And check the count in index settlement details report
    And close the settlement details window

    Examples:
      | Index    | Minutes | expected_Settlement_window_title          | search_value |
      | 20468674 | 1       | Index Settlement Details: S&P BRICT Index | china        |

  @TC017020 @TC017021 @TC017022
  Scenario Outline:
  a)Verify Index vix term summary's Details window should open with all valid records
  b)Verify Window should be filtered with given criteria
  c)Report should be generated with all available records with valid filtered data

    Given User logs into Grip application
    And User will fecth time in Dashboard page for VIX
    And User navigates to Index tab under Reasearch page
    When User will search for index data as "<IndexData>"
    When User fetch the stop time value of index for VIX
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will perform right click action on the index without test data
    And User will click on report
    And User will update Date Time and Minutes passed to Filter Report section as "<Minutes>"
    Then records should be generated in report
    And User will verify the Tick status for the Index
    And validate the records in Filter Report section
    And download the CSV report
    And check the count in CSV report
    And verify the total records count in the database
    And verify the Index vix term summary's Details window is displayed
    And search value "<search_value>" in term summary window
    And download the term summary report
    And check the count in term summary report
    And close the term summary details window

    Examples:
      | IndexType | IndexData  | IndexValue | Minutes | search_value |
      | VIX       | 1000000002 | VIX        | 1       | NEXT         |