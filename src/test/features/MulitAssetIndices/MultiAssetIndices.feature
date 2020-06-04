@MultiAssetIndices
Feature: MultiAsset Indices in Index tab - Research Tab

	@TC10019		
	Scenario Outline: Hold multiple MultiAsset Index/ manual release
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will hold/release multiple indices as "<ActionType1>" and release as "<IndexType>"
	And User will hold/release multiple indices as "<ActionType2>" and release as "<IndexType>"
	
	Examples:
	|IndexType                     | LinkType   | ActionType1  | ActionType2     |
	|Multi Asset (Index of Index)  | open       | Hold         | Release         |
	

	@TC10021	@TC10022  
	Scenario Outline: Show Details - MultiAsset Indices
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	Then User will perofrm actions on Show Details as "<ShowType>"

	Examples:
	|IndexType                   | IndexData  | IndexValue          | ShowType    |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices  | constituent |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices  | exchange    |

	@TC10037
	Scenario Outline: Advanced Add/Edit Index - MultiAsset Indices
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User Add/Edit index and verify in Maintain RTM Index tab
	
	Examples:
	|IndexType                   | IndexData  | IndexValue          |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices  |
	
	@TC10038	
	Scenario Outline: Advanced Stop Index Calculation - MultiAsset Indices
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
	|IndexType                   | IndexData  | IndexValue         | IndexTypeValue | 
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices | Non HPI Index  |
	
	@TC10035	@TC10036
	Scenario Outline: Correct Statistics within without trading - MultiAsset Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User Correct Statistics of trading time "<CorrectionType>" and verify alerts
	
	Examples:
	|IndexType                   | IndexData  | IndexValue          | CorrectionType |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices  | withintime     |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices  | withouttime    |
	
	@TC10030	
	Scenario Outline: Advanced Refresh from GRIP - MultiAsset Indices
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
	|IndexType                   | IndexData  | IndexValue         |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices |
	
	@TC10004	@TC10005 	@TC10006	@TC10007	@TC10008
	Scenario Outline: Manual Hold/Auto Release - MultiAsset Indices
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
	|IndexType                   | IndexData  | IndexValue          | HoldType |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices  | Auto Hold|
	
	
	@TC10026
	Scenario Outline: Update index divisor - MultiAsset Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	And User updates index divisor value and verify the updated value
	Examples:
	|IndexType                   | IndexData  | IndexValue          | 
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices  | 
	
	@TC10027		@TC10028	@TC10029
       Scenario Outline: Compare with T3 Index Portfolio, Parameteres, Timings - MultiAsset
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
       |IndexType                   | IndexData  | IndexValue          |CompareWithT3     |
       |Multi Asset (Index of Index)| 40404084   |  MultiAsset Indices |Index Portfolio   |
      # |Multi Asset (Index of Index)| 40404084   |  MultiAsset Indices |Index Parameters  |
       #|Multi Asset (Index of Index)| 40404084   |  MultiAsset Indices |Index Timings     |
	
	 @TC10039     
       Scenario Outline: Export to Excel - MultiAsset Indices
       Given User logs into Grip application
	   And User navigates to Index tab under Reasearch page
		And User will select type of index as "<IndexType>" and "<IndexValue>"
	   Then Click on Export to Excel and Report file should be generated in csv format with proper data
	   
	   Examples:
       |IndexType                     | IndexValue          |
       |Multi Asset (Index of Index)  | MultiAsset Indices  |
       
	@TC10031	@TC10032	@TC10033 @hold
	Scenario Outline: Publish Op/Hi/Lo to T3 - MultiAsset Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	Then User will perform right click action on the index without test data
	And User publish Op/Hi/Lo to T3 and verify alerts
	
	Examples:
	|IndexType                    | LinkType               | 
	|Multi Asset (Index of Index) | close                  |
	|Multi Asset (Index of Index) | prelim close           |
	|Multi Asset (Index of Index) | prelim close older date|
	
	@TC010023	@TC010024	@hold
	Scenario Outline: Publish close from T3 - MultiAsset Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	 Then User will perform right click action on the index without test data
	And User publish close index and verify alerts as "<PublishType>"
	
	Examples:
	|IndexType                    | LinkType        | PublishType  |
	|Multi Asset (Index of Index) | prelim close    | from T3      |
	|Multi Asset (Index of Index) | prelim close    | from GRIP    |
	
	 @TC10025 	@hold
       Scenario Outline: Move to Next Day - MultiAsset Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User will navigate to Index tab via dashboard link as "<LinkType>"
       And User will select type of index as "<IndexType>"
       When User fetch the current price value of index
       And User will click Move to Next day and verify the alert
       
       Examples:
       | LinkType       | IndexType                   | IndexValue      |
       | close          | Multi Asset (Index of Index)| Equity Indices  |
	
	@TC10034
       Scenario Outline: Advanced Correct Statistics - MultiAsset Indices
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
       |IndexType                    | IndexData  | IndexValue          | ModifyType1 | ModifyType2|
       |Multi Asset (Index of Index) | 40404084   | MultiAsset Indices  | update      |  rollback  |
       
       @TC10001	@TC10002	@TC10003
	Scenario Outline: Manual Hold Release - MultiAsset Indices
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
	|IndexType                   | IndexData  | IndexValue          | HoldType1    | HoldType2     | 
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices  | manualHold   | manualRelease | 
	
	@TC10020	
	Scenario Outline: Hold a MultiAsset Income Index multiple times in a row
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User will hold/release multiple indices as "<ActionType1>" and release as "<IndexType>"
	#Roll back script
	And User will hold/release multiple indices as "<ActionType2>" and release as "<IndexType>"
	
	Examples:
	|IndexType                   | IndexData  | IndexValue     | ActionType1  | ActionType2      |
	|Multi Asset (Index of Index)| 40404084   | Fixed Income   | holdMultiple | ReleaseMultiple  |
	
	@TC10009	@TC10010	@TC10011	@TC10012
	@TC10013	@TC10014	@TC10015	@TC10016
	Scenario Outline: Auto Hold/Auto Release - MultiAsset Indices
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
	|IndexType                   | IndexData  | IndexValue           | DataUpdate1 | DataUpdate2 | ThresoldValue   |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices   | update      | rollbackdata| Non Zero        |
	
	@TC10017	@TC10018
	Scenario Outline: Auto Hold/Manual Hold/ Manual Release - MultiAsset Indices
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
	|IndexType                   | IndexData  | IndexValue           | DataUpdate1 | DataUpdate2 |  ThresoldValue   |
	|Multi Asset (Index of Index)| 40404084   | MultiAsset Indices   | update      | rollbackdata|  Non Zero        |
	