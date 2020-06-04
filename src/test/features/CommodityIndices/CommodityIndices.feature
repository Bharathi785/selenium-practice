@CommodityIndices
Feature: Commodity Indices in Index tab - Research Tab

	
	@TC09019	
	Scenario Outline: Hold multiple Commodity Index/ manual release
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will hold/release multiple indices as "<ActionType1>" and release as "<IndexType>"
	And User will hold/release multiple indices as "<ActionType2>" and release as "<IndexType>"
	
	Examples:
	|IndexType    | LinkType   | ActionType1  | ActionType2      |
	|Commodity    | open       | Hold         | Release          |
	
	@TC09020	
	Scenario Outline: Hold a Commodity Index multiple times in a row
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User will hold/release multiple indices as "<ActionType1>" and release as "<IndexType>"
	#Roll back script
	And User will hold/release multiple indices as "<ActionType2>" and release as "<IndexType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue          | ActionType1  | ActionType2      |
	|Index Quick View| 20638589   | Commodities Indices | holdMultiple | ReleaseMultiple  |
	
	@TC09021	@TC09022  
	Scenario Outline: Show Details - Commodity Indices
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User will perofrm actions on Show Details as "<ShowType>"

	Examples:
	|IndexType       | IndexData  | IndexValue          | ShowType    |
	|Index Quick View| 20638589   | Commodities Indices | constituent |
	|Index Quick View| 20638589   | Commodities Indices | exchange    |

	@TC09037	
	Scenario Outline: Advanced Add/Edit Index - Commodity Indices
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User Add/Edit index and verify in Maintain RTM Index tab
	
	Examples:
	|IndexType       | IndexData  | IndexValue          |
	|Index Quick View| 20638589   | Commodities Indices |
	
	@TC09038	
	Scenario Outline: Advanced Stop Index Calculation - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User stops index calculation and verify alert as "<IndexTypeValue>"
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	And User reverts the stopped index calcuation to resolved state as "<IndexTypeValue>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue          | IndexTypeValue  |
	|Index Quick View| 20638589   | Commodities Indices | Non HPI Index   |

	@TC09035	@TC09036
	Scenario Outline: Correct Statistics within without trading - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User Correct Statistics of trading time "<CorrectionType>" and verify alerts
	
	Examples:
	|IndexType       | IndexData  | IndexValue          | CorrectionType |
	|Index Quick View| 20638589   | Commodities Indices | withintime     |
	|Index Quick View| 20638589   | Commodities Indices | withouttime    |
	
	@TC09030	
	Scenario Outline: Advanced Refresh from GRIP - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will update preOpen time for index in Database
	When User will perform right click action on the index
	Then User will refresh index value from Grip
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	When User will perform right click action on the index
	Then User revert the index value via refresh from T3
	
	Examples:
	|IndexType       | IndexData  | IndexValue         |
	|Index Quick View| 20638589   | Commodities Indices|
	
	@TC09004	@TC09005  	@TC09006	@TC09007	@TC09008	
	Scenario Outline: Manual Hold/Auto Release - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User will hold index and verify the current value as "<HoldType>"
	And User will perform auto based index release
	Examples:
	|IndexType       | IndexData  | IndexValue          | HoldType |
	|Index Quick View| 20638589   | Commodities Indices | Auto Hold|
	
	
	@TC09026
	Scenario Outline: Update index divisor - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	And User updates index divisor value and verify the updated value
	Examples:
	|IndexType       | IndexData  | IndexValue           | 
	|Index Quick View| 20638589   | Commodities Indices  | 
	
	@TC09027		@TC09028	@TC09029
       Scenario Outline: Compare with T3 Index Portfolio, Parameteres, Timings - Commodity
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User will select type of index as "<IndexType>" and "<IndexValue>"
       When User will search for index data as "<IndexData>"
       And User fetch the current price value of index
       And User update data and generate valid report for "<CompareWithT3>"
       Then User will perform right click action on the index
       And User will select compare with T3 for index data as "<CompareWithT3>"
       Then User verify the report generated in mail
       Then User will perform right click action on the index
       And User revert the cache ID value via refresh from T3
       And User validate the attachments from mail
       
       Examples:
       |IndexType       | IndexData  | IndexValue          |CompareWithT3     |
       #|Index Quick View| 20638589   | Commodities Indices |Index Portfolio   |
       |Index Quick View| 20638589   | Commodities Indices |Index Parameters  |
      # |Index Quick View| 20638589   | Commodities Indices |Index Timings     |
       
	 
	   @TC09039     
       Scenario Outline: Export to Excel - Commodity Indices
       Given User logs into Grip application
	   And User navigates to Index tab under Reasearch page
		And User will select type of index as "<IndexType>" and "<IndexValue>"
	   Then Click on Export to Excel and Report file should be generated in csv format with proper data
	   
	   Examples:
       |IndexType    | IndexValue          |
       |Commodity    | Commodities Indices |
       
	@TC09031	@TC09032	@TC09033
	Scenario Outline: Publish Op/Hi/Lo to T3 - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	Then User will perform right click action on the index without test data
	And User publish Op/Hi/Lo to T3 and verify alerts
	
	Examples:
	|IndexType     | LinkType               | 
	|Commodity     | close                  |
	|Commodity     | prelim close           |
	|Commodity     | prelim close older date|
	
	@TC09023	@TC09024	
	Scenario Outline: Publish close from Grip and T3 - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	 Then User will perform right click action on the index without test data
	And User publish close index and verify alerts as "<PublishType>"
	
	Examples:
	|IndexType   | LinkType        | PublishType  |
	|Commodity   | prelim close    | from T3      |
	|Commodity   | prelim close    | from GRIP    |
	
	@TC09025 	
       Scenario Outline: Move to Next Day - Commodity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User will navigate to Index tab via dashboard link as "<LinkType>"
       And User will select type of index as "<IndexType>"
       When User fetch the current price value of index
       And User will click Move to Next day and verify the alert
       
       Examples:
       | LinkType       | IndexType    | IndexValue      |
       | close          | Commodity    | Equity Indices  |
	
	
	@TC09034
       Scenario Outline: Advanced Correct Statistics - Commodity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User will select type of index as "<IndexType>" and "<IndexValue>"
       When User will search for index data as "<IndexData>"
       And User fetch the current price value of index
       Then User will perform right click action on the index
       Then User click on Correct Statistics modify the correct value as "<ModifyType1>"
       # Roll back script
       Then User will perform right click action on the index
       Then User click on Correct Statistics modify the correct value as "<ModifyType2>"
       
       Examples:
       |IndexType        | IndexData  | IndexValue          | ModifyType1 | ModifyType2|
       |Index Quick View | 20638589   | Commodity Indices   | update      |  rollback  |
       
       @TC09001	@TC09002	@TC09003
	Scenario Outline: Manual Hold Release - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User will hold index and verify the current value as "<HoldType1>"
	When User verify the status in table and alert table as "<HoldType1>"
	# Rollback script
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	And User will release the holded index
	When User verify the status in table and alert table as "<HoldType2>"
	Examples:
	|IndexType       | IndexData  | IndexValue          | HoldType1    | HoldType2     | 
	|Index Quick View| 20638589   | Commodities Indices | manualHold   | manualRelease | 
	
	@TC09009	@TC09010	@TC09011	@TC09012
	@TC09013	@TC09014	@TC09015	@TC09016
	Scenario Outline: Auto Hold/Auto Release - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	And User will modify UpDown percent values refresh mView as "<DataUpdate1>" and "<ThresoldValue>"
	Then User will perform right click action on the index
	And User refresh index from T3 and verify alert
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	# Roll Back script
	And User will modify UpDown percent values refresh mView as "<DataUpdate2>" and "<ThresoldValue>"
	Then User will perform right click action on the index
	And User refresh index from T3 and verify alert
	
	Examples:
	|IndexType       | IndexData  | IndexValue           | DataUpdate1 | DataUpdate2 | ThresoldValue   |
	|Index Quick View| 20638589   | Commodities Indices  | update      | rollbackdata| Non Zero        |
	
	@TC09017	@TC09018
	Scenario Outline: Auto Hold/Manual Hold/ Manual Release - Commodity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	And User will modify UpDown percent values refresh mView as "<DataUpdate1>" and "<ThresoldValue>"
	Then User will perform right click action on the index
	And User refresh index from T3 and verify alert
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	And User will manually release the index
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	# Roll Back Script
	And User will modify UpDown percent values refresh mView as "<DataUpdate2>" and "<ThresoldValue>"
	Then User will perform right click action on the index
	And User refresh index from T3 and verify alert
	
	Examples:
	|IndexType       | IndexData  | IndexValue           | DataUpdate1 | DataUpdate2 |  ThresoldValue   |
	|Index Quick View| 20638589   | Commodities Indices  | update      | rollbackdata|  Non Zero        |
	