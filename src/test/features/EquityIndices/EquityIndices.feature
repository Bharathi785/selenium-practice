@EquityIndices
Feature: Equity Indices in Index tab - Research Tab

	
	@TC08024	
	Scenario Outline: Hold multiple Equity Index/ manual release
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	And User will hold/release multiple indices as "<ActionType1>" and release as "<IndexType>"
	And User will hold/release multiple indices as "<ActionType2>" and release as "<IndexType>"
	
	Examples:
	|IndexType       | LinkType   | ActionType1  | ActionType2     |
	|Index Quick View| open       | Hold         | Release         |
	
	@TC08025	
	Scenario Outline: Hold a Equity Index multiple times in a row
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User will hold/release multiple indices as "<ActionType1>" and release as "<IndexType>"
	#Roll back script
	And User will hold/release multiple indices as "<ActionType2>" and release as "<IndexType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue        | ActionType1  | ActionType2      |
	|Index Quick View| 20011478   | Index Quick View  | holdMultiple | ReleaseMultiple  |
	
	@TC08004
	Scenario Outline: Manual Release Index failure - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	And User release the without Held index and verify alert
	
	Examples:
	|IndexType       | IndexData  | IndexValue          | 
	|Index Quick View| 20011478   | Equity Indices      |

	@TC08028  	@TC08029
	Scenario Outline: Show Details - Equity Indices
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User will perofrm actions on Show Details as "<ShowType>"

	Examples:
	|IndexType       | IndexData  | IndexValue        | ShowType    |
	|Index Quick View| 20011478   | Equity Indices    | constituent |
	|Index Quick View| 20011478   | Equity Indices    | exchange    |
	
	@TC08060
	Scenario Outline: Advanced Add/Edit Index - Equity Indices
	Given User logs into Grip application
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User Add/Edit index and verify in Maintain RTM Index tab
	
	Examples:
	|IndexType       | IndexData  | IndexValue          |
	|Index Quick View| 20011478   | Equity Indices      |
	
	@TC08061
	Scenario Outline: Advanced Stop Index Calculation - Equity Indices
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
	|IndexType       | IndexData  | IndexValue         | IndexTypeValue |
	|Index Quick View| 20011478   | Equity Indices     | Non HPI Index  |
	
	@TC08053	@TC08054
	Scenario Outline: Correct Statistics within without trading - Equity Indices
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
	|Index Quick View| 20011478   | Equity Indices      | withintime     |
	|Index Quick View| 20011478   | Equity Indices      | withouttime    |
	
	@TC08046
	Scenario Outline: Advanced Refresh from GRIP - Equity Indices
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
	|Index Quick View| 20011478   | Equity Indices     |
	
	@TC08005 	@TC08006	@TC08007	@TC08008	@TC08009
	Scenario Outline: Manual Hold/Auto Release - Equity Indices
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
	|Index Quick View| 20011478   | Equity Indices      | Auto Hold|
	
	
	
	@TC08047	@TC08049	@TC08051
	Scenario Outline: Publish Op/Hi/Lo to T3 - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	Then User will perform right click action on the index without test data
	And User publish Op/Hi/Lo to T3 and verify alerts
	
	Examples:
	|IndexType        | LinkType               | 
	|Index Quick View | close                  |
	|Index Quick View | prelim close           |
	|Index Quick View | prelim close older date|
	
	@TC08030	@TC08031 	
	Scenario Outline: Publish close from T3 - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	Then User will perform right click action on the index without test data
	And User publish close index and verify alerts as "<PublishType>"
	
	Examples:
	|IndexType        | LinkType        | PublishType  |
	|Index Quick View | prelim close    | from T3      |
	
	@TC08032 	
	Scenario Outline: Publish close from Grip - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	Then User will perform right click action on the index without test data
	And User publish close index and verify alerts as "<PublishType>"
	
	Examples:
	|IndexType        | LinkType        | PublishType  |
	|Index Quick View | prelim close    | from GRIP    |
	
	
	@TC08033 	
	Scenario Outline: Publish close from T3 - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	 Then User will perform right click action on the index without test data
	And User publish close index and verify alerts as "<PublishType>"
	
	Examples:
	|IndexType        | LinkType        |  PublishType  |
	|Index Quick View | prelim close    |  from T3      |
	
	@TC08038
	Scenario Outline: Update index divisor - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	And User updates index divisor value and verify the updated value
	Examples:
	|IndexType       | IndexData  | IndexValue      | 
	|Index Quick View| 20011478   | Equity Indices  | 
	
	   @TC08022
	   Scenario: Auto Hold before market opens - Equity Indices
	   Given User logs into Grip application
	   And User will fecth time in Dashboard page
	   And User navigates to Index tab under Reasearch page
	   Then User will verify the alert to find Index movement beyond thresholds message
       
       @TC08023
       Scenario Outline: Hold Equity Index at a negative number - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User will select type of index as "<IndexType>" and "<IndexValue>"
       When User will search for index data as "<IndexData>"
       When User fetch the current price value of index
       Then User will perform right click action on the index
       And User will click on hold and enter negative value and vaildate the same
       
       Examples:
       |IndexType       | IndexData  | IndexValue          |
       |Index Quick View| 20011478   | Equity Indices      |
       
       @TC08026
       Scenario Outline: Search in Equity Index List window - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User will select type of index as "<IndexType>" and "<IndexValue>"
       And User will search for the indices withcomma and verify 
       
        Examples:
       |IndexType       | IndexData  | IndexValue          |
       |Identifier View | 20011478   | Equity Indices      |
       
       @TC08043		@TC08044	@TC08045
       Scenario Outline: Compare with T3 Index Portfolio, Parameteres, Timings - Equity
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User will select type of index as "<IndexType>" and "<IndexValue>"
       When User will search for index data as "<IndexData>"
       And User fetch the current price value of index
       And User update data and generate valid report for "<CompareWithT3>"
       Then User will perform right click action on the index
       When User will select compare with T3 for index data as "<CompareWithT3>"
       Then User verify the report generated in mail
       Then User will perform right click action on the index
       And User revert the cache ID value via refresh from T3
       And User validate the attachments from mail
       
       Examples:
       |IndexType       | IndexData  | IndexValue          |CompareWithT3     |
       |Index Quick View| 20011478   | Equity Indices      |Index Portfolio   |
       |Index Quick View| 20011478   | Equity Indices      |Index Parameters  |
       |Index Quick View| 20011478   | Equity Indices      |Index Timings     |
       
       @TC08039   
       Scenario Outline: Copy Name - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User will select type of index as "<IndexType>" and "<IndexValue>"
       When User will search for index data as "<IndexData>"
       And User fetch the current price value of index
       Then User will perform right click action on the index
       And User will click Copy name and Equity Index name should get copied to the clipboard
       
       Examples:
       |IndexType       | IndexData  | IndexValue          |
       |Index Quick View| 20011478   | Equity Indices      |
       
       @TC08040   @TC08042
       Scenario Outline: Copy Id - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User will select type of index as "<IndexType>" and "<IndexValue>"
       When User will search for index data as "<IndexData>"
       And User fetch the current price value of index
       Then User will perform right click action on the index
       And User will click Copy Id and Equity Index id should get copied to the clipboard
       
       Examples:
       |IndexType       | IndexData  | IndexValue          |
       |Index Quick View| 20011478   | Equity Indices      |
       
       @TC08042
       Scenario Outline: Reports - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User will select type of index as "<IndexType>" and "<IndexValue>"
       When User will search for index data as "<IndexData>"
       And User fetch the current price value of index
       Then User will perform right click action on the index
       And User will click Report and Equity Index name populated in Asset edit box
       
       Examples:
       |IndexType       | IndexData  | IndexValue          |
       |Index Quick View| 20011478   | Equity Indices      |
       
       
       @TC08041
       Scenario: Select All Rows- Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User selects all indices rows and verify count
       
       
       @TC08056    @TC08057   @TC08059
       Scenario Outline: Advanced Publish Add, Update and News Definition - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       When User will search for index data as "<IndexData>"
       When User fetch the current price value of index
       Then User will perform right click action on the index
       And User will click Publish Add Definition and Definition message should be published with alert
       And User will click Publish Update Definition and Definition message should be published with alert
       And User will click Publish News and Definition message should be published with alert
       
       Examples:
       |IndexType       | IndexData  | IndexValue          |
       |Index Quick View| 20011478   | Equity Indices      |
       
       @TC08036  	
       Scenario Outline: Move to Next Day in close state - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User will navigate to Index tab via dashboard link as "<LinkType>"
       And User will select type of index as "<IndexType>"
       When User fetch the current price value of index
       And User will click Move to Next day and verify the alert
       
       Examples:
       | LinkType       | IndexType      | IndexValue      |
       | close          |Index Quick View| Equity Indices  |
       
       @TC08037 	
       Scenario Outline: Move to Next Day in Open state  - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User will navigate to Index tab via dashboard link as "<LinkType>"
       And User will select type of index as "<IndexType>"
       When User fetch the current price value of index
       And User will click Move to Next day and verify the alert
       
       Examples:
       | LinkType       | IndexType      | IndexValue      |
       | open           |Index Quick View| Equity Indices  |
       
       @TC08034    @TC08035 	
       Scenario Outline: Publish close from Grip & T3 - Equity Indices
   		Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User will navigate to Index tab via dashboard link as "<LinkType>"
        And User will select type of index as "<IndexType>"
       When User fetch the current price value of index
       Then User will perform right click action on the index without test data
       And User publish close from T3 Grip and verify alerts as "<PublishType>"
       
       Examples:
       | IndexType        | LinkType     | PublishType  |
       | Index Quick View | open         | from T3      |
       | Index Quick View | open         | from Grip    |
       
       @TC08048     @TC08050
       Scenario Outline: Publish Op/Hi/Lo and T3 - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User will navigate to Index tab via dashboard link as "<LinkType>"
        And User will select type of index as "<IndexType>"
       When User fetch the current price value of index
       Then User will perform right click action on the index
       And User publish Op/Hi/Lo to T3 and prelim close T3 and verify alerts
       
       Examples:
       | IndexType         | LinkType                  | 
       | Index Quick View  | open                      |
       | Index Quick View  | prelim close T3 open      |
       
       @TC08062     
       Scenario Outline: Export to Excel - Equity Indices
       Given User logs into Grip application
	   And User navigates to Index tab under Reasearch page
		And User will select type of index as "<IndexType>" and "<IndexValue>"
	   Then Click on Export to Excel and Report file should be generated in csv format with proper data
	   
	   Examples:
       |IndexType        | IndexValue          |
       |Index Quick View | Equity Indices      |
       
      
	@TC08027  
	Scenario: Filter combo box 
	Given User logs into Grip application
    And User navigates to Index tab under Reasearch page
	And Click on index tab and Select different option available in combo filter and verify the column names
	
	@TC08052
       Scenario Outline: Advanced Correct Statistics - Equity Indices
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
       |Index Quick View | 20011478   | Equity Indices      | update      |  rollback  |
       
       
    @TC08001	@TC08002	@TC08003
	Scenario Outline: Manual Hold Release - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User will hold index and verify the current value as "<HoldType1>"
	When User verify the status in table and alert table as "<HoldType1>"
	# Roll back script
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	And User will release the holded index
	When User verify the status in table and alert table as "<HoldType2>"
	Examples:
	|IndexType       | IndexData  | IndexValue          | HoldType1    | HoldType2     | 
	|Index Quick View| 20011478   | Equity Indices      | manualHold   | manualRelease | 
	  
	@TC08010	@TC08011	@TC08012	@TC08013
	@TC08014	@TC08015	@TC08016	@TC08017
	Scenario Outline: Auto Hold/Auto Release - Equity Indices
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
	|IndexType       | IndexData  | IndexValue       | DataUpdate1 | DataUpdate2 | ThresoldValue   |
	|Index Quick View| 20011478   | Equity Indices   | update      | rollbackdata| Non Zero        |
	
	@TC08019
	Scenario Outline: Zero thresold - Equity Indices
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
	|IndexType       | IndexData  | IndexValue       | DataUpdate1 | DataUpdate2 | ThresoldValue   |
	|Index Quick View| 20011478   | Equity Indices   | update      | rollbackdata| Zero            |
	
	@TC08018
	Scenario Outline: Already on hold - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User will hold index and verify the current value as "<HoldType1>"
	When User verify the status in table and alert table as "<HoldType1>"
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	And User will modify UpDown percent values refresh mView as "<DataUpdate1>" and "<ThresoldValue>"
	Then User will perform right click action on the index
	And User refresh index from T3 and verify no change in index status as "<Refactor1>"
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	# Roll Back Script
	And User will modify UpDown percent values refresh mView as "<DataUpdate2>" and "<ThresoldValue>"
	Then User will perform right click action on the index
	And User refresh index from T3 and verify no change in index status as "<Refactor2>"
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	Then User will perform right click action on the index
	And User will release the holded index
	When User verify the status in table and alert table as "<HoldType2>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | DataUpdate1 | DataUpdate2 | HoldType1    | HoldType2     | Refactor1 | Refactor2 | ThresoldValue   |
	|Index Quick View| 20011478   | Equity Indices   | update      | rollbackdata| manualHold   | manualRelease | Yes       | No        | Non Zero        |
	
	@TC08020	@TC08021
	Scenario Outline: Auto Hold/Manual Hold/ Manual Release - Equity Indices
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
	|IndexType       | IndexData  | IndexValue       | DataUpdate1 | DataUpdate2 | ThresoldValue   |
	|Index Quick View| 20011478   | Equity Indices   | update      | rollbackdata| Non Zero        |
  