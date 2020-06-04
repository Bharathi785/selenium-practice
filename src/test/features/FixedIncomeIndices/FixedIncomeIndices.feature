@FixedIncomeIndices
Feature: FixedIncome Indices in Index tab - Research Tab
	
	
	@TC11019	
	Scenario Outline: Hold multiple FixedIncome Index/ manual release
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will hold/release multiple indices as "<ActionType1>" and release as "<IndexType>"
	And User will hold/release multiple indices as "<ActionType2>" and release as "<IndexType>"
	
	Examples:
	|IndexType     | LinkType   | ActionType1  | ActionType2     |
	|Fixed Income  | open       | Hold         | Release         |
	
	@TC11020	
	Scenario Outline: Hold a Fixed Income Index multiple times in a row
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User will hold/release multiple indices as "<ActionType1>" and release as "<IndexType>"
	#Roll back script
	And User will hold/release multiple indices as "<ActionType2>" and release as "<IndexType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue     | ActionType1  | ActionType2      |
	|Index Quick View| 50000801   | Fixed Income   | holdMultiple | ReleaseMultiple  |

	@TC11021  
	Scenario Outline: Show Constituent Details - FixedIncome Indices
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	Then User will perofrm actions on Show Details as "<ShowType>"

	Examples:
	|IndexType       | IndexData  | IndexValue    | ShowType    |
	|Index Quick View| 50000801   | Fixed Income  | constituent |
	|Index Quick View| 50000801   | Fixed Income  | exchange    |

	@TC11037	
	Scenario Outline: Advanced Add/Edit Index - FixedIncome Indices
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User Add/Edit index and verify in Maintain RTM Index tab
	
	Examples:
	|IndexType       | IndexData  | IndexValue   |
	|Index Quick View| 50000801   | Fixed Income |
	
	@TC11038	
	Scenario Outline: Advanced Stop Index Calculation - FixedIncome Indices
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
	|IndexType       | IndexData  | IndexValue   | IndexTypeValue  | 
	|Index Quick View| 50000801   | Fixed Income | Non HPI Index   |
	
	@TC11035	@TC11036
	Scenario Outline: Correct Statistics within without trading - FixedIncome Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User Correct Statistics of trading time "<CorrectionType>" and verify alerts
	
	Examples:
	|IndexType       | IndexData  | IndexValue    | CorrectionType |
	|Index Quick View| 50000801   | Fixed Income  | withintime     |
	|Index Quick View| 50000801   | Fixed Income  | withouttime    |
	
	
	@TC11030	
	Scenario Outline: Advanced Refresh from GRIP - FixedIncome Indices
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
	|IndexType       | IndexData  | IndexValue   |
	|Index Quick View| 50000801   | Fixed Income |
	
	
	@TC11004	@TC11005	@TC11006	@TC11007	@TC11008	
	Scenario Outline: Manual Hold/Auto Release - FixedIncome Indices
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
	|IndexType       | IndexData  | IndexValue   | HoldType |
	|Index Quick View| 50000801   | Fixed Income | Auto Hold|
	
	
	
	@TC11026
	Scenario Outline: Update index divisor - FixedIncome Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	And User updates index divisor value and verify the updated value
	Examples:
	|IndexType       | IndexData  | IndexValue   | 
	|Index Quick View| 50000801   | Fixed Income | 	
	
	@TC11027		@TC11028	@TC11029
       Scenario Outline: Compare with T3 Index Portfolio, Parameteres, Timings - FixedIncome
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
       |IndexType       | IndexData  | IndexValue     |CompareWithT3     |
       |Index Quick View| 50000801   |  Fixed Income  |Index Portfolio   |
       |Index Quick View| 50000801   |  Fixed Income  |Index Parameters  |
       |Index Quick View| 50000801   |  Fixed Income  |Index Timings     |
       
        @TC11039     
       Scenario Outline: Export to Excel - FixedIncome Indices
       Given User logs into Grip application
	   And User navigates to Index tab under Reasearch page
		And User will select type of index as "<IndexType>" and "<IndexValue>"
	   Then Click on Export to Excel and Report file should be generated in csv format with proper data
	   
	   Examples:
       |IndexType      | IndexValue    |
       |Fixed Income   | Fixed Income  |
       
	@TC11031	@TC11032	@TC11033
	Scenario Outline: Publish close, prelim close to T3 - FixedIncome Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	Then User will perform right click action on the index without test data
	And User publish Op/Hi/Lo to T3 and verify alerts
	
	Examples:
	|IndexType     | LinkType               | 
	|Fixed Income  | close                  |
	|Fixed Income  | prelim close open      |
	|Fixed Income  | prelim close           |
	|Fixed Income  | prelim close older date|
	
	@TC11023	@TC11024
	Scenario Outline: Publish close from Grip and T3 - FixedIncome Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	 Then User will perform right click action on the index without test data
	And User publish close index and verify alerts as "<PublishType>"
	
	Examples:
	|IndexType     | LinkType        | PublishType  |
	|Fixed Income  | prelim close    | from T3      |
	|Fixed Income  | prelim close    | from GRIP    |
	
	@TC11025 	
       Scenario Outline: Move to Next Day - FixedIncome Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User will navigate to Index tab via dashboard link as "<LinkType>"
       And User will select type of index as "<IndexType>"
       When User fetch the current price value of index
       And User will click Move to Next day and verify the alert
       
       Examples:
       | LinkType  | IndexType    | IndexValue     |
       | close     | Fixed Income | Fixed Income   |
	
	@TC11034
       Scenario Outline: Advanced Correct Statistics - FixedIncome Indices
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
       |IndexType        | IndexData  | IndexValue    | ModifyType1 | ModifyType2|
       |Index Quick View | 50000801   | Fixed Income  | update      |  rollback  |
       
       @TC11001	@TC11002	@TC11003
	Scenario Outline: Manual Hold Release - FixedIncome Indices
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
	|IndexType       | IndexData  | IndexValue     | HoldType1    | HoldType2     | 
	|Index Quick View| 50000801   | Fixed Income   |  manualHold   | manualRelease | 
	
	@TC11009	@TC11010	@TC11011	@TC11012
	@TC11013	@TC11014	@TC11015	@TC11016
	Scenario Outline: Auto Hold/Auto Release - FixedIncome Indices
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
	|IndexType       | IndexData  | IndexValue     | DataUpdate1 | DataUpdate2 | ThresoldValue   |
	|Index Quick View| 50000801   | Fixed Income   | update      | rollbackdata| Non Zero        |
	
	 
	@TC11017	@TC11018
	Scenario Outline: Auto Hold/Manual Hold/ Manual Release - FixedIncome Indices
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
	|IndexType       | IndexData  | IndexValue    | DataUpdate1 | DataUpdate2 | ThresoldValue   |
	|Index Quick View| 50000801   | Fixed Income  | update      | rollbackdata| Non Zero        |