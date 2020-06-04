@VIX
Feature: VIX - Research Tab

    @TC33001
    Scenario: VIX Work Flow
    Given User has given with VIX index with auto close value as one
    Then User verify the publish by grip as there is no prelim close on previous day
    Then User verify the close tag

    @TC33002
	Scenario Outline: Show Constituent Details
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index without test data
	Then User will perform actions in VIX index on Show Details as "<ShowType>" and "<IndexData>"
    And Select different option available in combo filter and verify the column names
	Examples:
	| IndexType | IndexData  | IndexValue | ShowType    |
	|    VIX    | 1000000002 |     VIX    | constituent |
	
	
	
	@TC33003     @TC33004
	Scenario Outline: Show Exchange
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index without test data
	Then User will perform actions in VIX index on Show Details as "<ShowType>" and "<IndexData>"
	And User will perform Show indices and verify the index as "<ShowType>"
	Examples:
	| IndexType | IndexData  | IndexValue | ShowType    |
	|    VIX    | 1000000002 |     VIX    | exchange    |

		
   
    @TC33005    @TC33008
    Scenario Outline:
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
    Examples:
    | IndexType | IndexData  | IndexValue |Minutes|
    |    VIX    | 1000000002 |     VIX    |    1  |
    
    
    
    @TC33006    @TC33007
    Scenario Outline: Publish close from Grip & T3 - VIX
   	Given User logs into Grip application
    And User will fecth time in Dashboard page for VIX
    And User will navigate to VIX Index tab via dashboard link as "<LinkType>"
    And User will select type of index as "<IndexType>"
    Then User will perform right click action on the index without test data
    And User publish close from T3 and Grip for VIX index and verify alerts as "<PublishType>"
    Examples:
    | IndexType   | LinkType     | PublishType  |
    |   VIX       | close        | from T3      |
    |   VIX       | close        | from Grip    |
    
    
  
    @TC33009
	Scenario Outline: Advanced Refresh from GRIP - VIX
	Given User logs into Grip application
	And User will fecth time in Dashboard page for VIX
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	When User will perform right click action on the index without test data
	Then User will refresh index value from Grip and verify the Alert message in UI
	Examples:
	|IndexType  | IndexData  | IndexValue   |
	|   VIX     | 20054434   |   VIX        |
	
	
	
	@TC33010
	Scenario Outline: Refresh from T3 - VIX
	Given User logs into Grip application
	And User will fecth time in Dashboard page for VIX
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	When User will perform right click action on the index without test data
	And User will refresh index value from T3 and verify the Alert message in UI
	Examples:
	|IndexType  |  IndexData  | IndexValue   |
	|   VIX     | 1023112925  |   VIX        |
	
	
	@TC33011
	Scenario Outline: Move to Next Day - VIX
    Given User logs into Grip application
    And User will fecth time in Dashboard page
    And User will navigate to Index tab via dashboard link as "<LinkType>"
    And User will select type of index as "<IndexType>"
    When User fetch the current price value of index
    And User will click Move to Next day and verify the alert
       
    Examples:
    | LinkType       | IndexType    | IndexValue |
    | close          |   VIX        |     VIX    |
    
    
    @TC33012
	Scenario Outline: Manual Hold Release - VIX
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	When User will perform right click action on the index without test data
	Then User will hold index and verify the current value as "<HoldType1>"
	When User verify the status in table and alert table as "<HoldType1>"
	# Roll back script
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	When User will perform right click action on the index without test data
	And User will release the holded index
	When User verify the status in table and alert table as "<HoldType2>"
	Examples:
	|   IndexType     | IndexData | IndexValue  | HoldType1    | HoldType2     |
	|Index Quick View | 55648791  |   VIX       | manualHold   | manualRelease |
	
	
	@TC33013
	Scenario Outline: Compare with T3 Index Portfolio, Parameteres, Timings - Equity
    Given User logs into Grip application
    And User will fecth time in Dashboard page
    And User navigates to Index tab under Reasearch page
    And User will select type of index as "<IndexType>" and "<IndexValue>"
    When User will search for index data as "<IndexData>"
    And User fetch the current price value of index
    Then User will perform right click action on the index
    And User will select compare with T3 for index data as "<CompareWithT3>"
    Examples:
    |IndexType       | IndexData  | IndexValue          |CompareWithT3     |
    |Index Quick View| 20054434  | Equity Indices      |Index Portfolio   |
    |Index Quick View| 20011478   | Equity Indices      |Index Parameters  |
    |Index Quick View| 20011478   | Equity Indices      |Index Timings     |