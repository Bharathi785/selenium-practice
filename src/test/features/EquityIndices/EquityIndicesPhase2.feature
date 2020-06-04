@EquityIndicesNew
Feature: Equity Indices Additional TC in Index tab - Research Tab

	 @TC08071
       Scenario: HPI Flag in DB, UI and verify sorting column - Equity Indices
       Given User logs into Grip application
       And User will fecth time in Dashboard page
       And User navigates to Index tab under Reasearch page
       And User verify HPI flag in UI under Indices list table
	
	@TC08087		@TC08088	@TC08069	@TC08070
	Scenario Outline: Manual Hold Release an HPI Index - Equity Indices
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
	|Index Quick View| 3001574    | Equity Indices      | manualHold   | manualRelease | 
	
	@TC08089	@TC08090
	Scenario Outline: Auto Hold/Auto Release an HPI Index - Equity Indices
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
	|Index Quick View| 3001574    | Equity Indices   | update      | rollbackdata| Non Zero        |
	
	@TC08079	@TC08080
	Scenario Outline: Advanced Stop Index Calculation for HPI - Equity Indices
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
	|IndexType       | IndexData  | IndexValue       | IndexTypeValue |
	|Index Quick View| 3001574    | Equity Indices   | HPI Index      |
	
	@TC08075
	Scenario Outline: No ticks IDC HPI index - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType   |
	|Index Quick View| 3001574    | Equity Indices   | No Ticks IDC |
	
	@TC08076
	Scenario Outline: No ticks IDC HPI index Resolved - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType            |
	|Index Quick View| 3001574    | Equity Indices   | No Ticks IDC Resolved |
	
	@TC08077
	Scenario Outline: No ticks Reuters HPI index - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType       |
	|Index Quick View| 3001574    | Equity Indices   | No Ticks Reuters |
	
	@TC08078
	Scenario Outline: No ticks Reuters HPI index Resolved - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType                |
	|Index Quick View| 3001574    | Equity Indices   | No Ticks Reuters Resolved |
	
	@TC08081
	Scenario Outline: Flat line IDC HPI index - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType    |
	|Index Quick View| 3001574    | Equity Indices   | Flat Line IDC |
	
	@TC08082
	Scenario Outline: Flat line IDC HPI index Resolved - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType             |
	|Index Quick View| 3001574    | Equity Indices   | Flat Line IDC Resolved |
	
	@TC08083
	Scenario Outline: Flat line Reuters HPI index - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType        |
	|Index Quick View| 3001574    | Equity Indices   | Flat Line reuters |
	
	@TC08084
	Scenario Outline: Flat line Reuters HPI index Resolved - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType                 |
	|Index Quick View| 3001574    | Equity Indices   | Flat Line reuters Resolved |
	
	@TC08085
	Scenario Outline: Flat line GRIP HPI index - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType     |
	|Index Quick View| 3001574    | Equity Indices   | Flat Line Grip |
	
	@TC08086
	Scenario Outline: Flat line GRIP HPI index Resolved - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User verify the No Ticks alert message as "<NoTickType>"
	
	Examples:
	|IndexType       | IndexData  | IndexValue       | NoTickType              |
	|Index Quick View| 3001574    | Equity Indices   | Flat Line Grip Resolved |
	
	@TC08101	@TC08102	@TC08104	@TC08105	@TC08106	@TC08103
	@TC08107	@TC08112	@TC08113	@TC08125	@TC08126	@TC08114	@TC08115
	Scenario Outline: Suspect Withdraw - equity Index
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType1>"
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	Then User will perform right click action on the index
	Then User will suspect index and verify the suspect alert as "<SuspectType1>"
	And User will navigate to Index tab via dashboard link as "<LinkType2>"
	And User verify the status in Index list suspect window
	And User will download the suspect index data report
	Then User will perform right click action on the index
	Then User will hold the suspected Index and verify the alert as "<HoldType1>"
	Then User will perform right click action on the index
	Then User will hold the suspected Index and verify the alert as "<HoldType2>"
	Then User will perform right click action on the index
	And User will modify UpDown percent values refresh mView as "<DataUpdate1>" and "<ThresoldValue>"
	Then User will perform right click action on the index
	And User refresh index from T3 and verify Auto hold alert as "<HoldType3>"
	Then User will perform right click action on the index
	And User will modify UpDown percent values refresh mView as "<DataUpdate2>" and "<ThresoldValue>"
	Then User will perform right click action on the index
	And User refresh index from T3 and verify Auto hold alert as "<HoldType4>"
	Then User will perform right click action on the index
	Then User will suspect index and verify the suspect alert as "<SuspectType2>"
	
	
	Examples:
	|IndexType       | IndexValue     |LinkType1  | IndexData  | SuspectType1    | LinkType2  | HoldType1   | HoldType2      | SuspectType2     |  DataUpdate1 | DataUpdate2 | ThresoldValue   | HoldType3 | HoldType4    |
	|Index Quick View| Equity Indices |  open     | 3001574    | Publish Suspect | suspect    | Manual Hold | manual Release | Withdraw Suspect |  update      | rollbackdata| Non Zero        | auto hold | Auto release |
        
    @TC08116
	Scenario Outline: Suspect Index other than Open state
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType1>"
	And User will select type of index as "<IndexType>"
	When User fetch the current price value of index
	Then User will perform right click action on the index without test data
	And User supect index other than open state and verify no action
	
	Examples:
	|IndexType       | IndexValue     |  LinkType1    |
	|Index Quick View| Equity Indices | prelim close  |
	
	@TC08129
	Scenario: DQ flag for close tick
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will verify the values in Local and remote DataBase for Close Tick as "Local DB1"
	And User will verify the values in Local and remote DataBase for Close Tick as "Local DB2"
	And User will verify the values in Local and remote DataBase for Close Tick as "Remote DB"
	
	@TC08108	@TC08109	@TC08110	@TC08111	
	Scenario Outline: Publish suspect (multiple indices)
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User will navigate to Index tab via dashboard link as "<LinkType>"
	And User will select type of index as "<IndexType>"
	And User will publish mulitple indices as "<SuspectType1>" and withdraw as "<IndexType>"
	And User will publish mulitple indices as "<SuspectType2>" and withdraw as "<IndexType>"
	
	Examples:
	|IndexType       | LinkType   | SuspectType1     | SuspectType2     |
	|Index Quick View| open       | Publish Suspect  | Withdraw Suspect |
	
	
	@TC08127	@TC08128
	Scenario: Filter and download suspects
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User filter Suspect alert data and verify donwload
	
	@TC08063		@TC08065	@TC08068
	Scenario Outline: Normal - Warning - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	And User update the thresold values in RTm table in Database as "<ThresoldType>"
	Then User will perform right click action on the index
	And User will refresh Mview
	Then User will perform right click action on the index
	And User refresh index from T3 and verify updated values from DB in alert UI as "<ThresoldType>"
	
	Examples:
	|IndexType        | IndexData  | IndexValue       | ThresoldType   | 
	|Index Quick View | 3001574    | Equity Indices   | normal warning |
	
	@TC08064	
	Scenario Outline: Warning to Normal - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	And User update the thresold values in RTm table in Database as "<ThresoldType>"
	Then User will perform right click action on the index
	And User will refresh Mview
	Then User will perform right click action on the index
	And User refresh index from T3 and verify updated values from DB in alert UI as "<ThresoldType>"
	
	Examples:
	|IndexType        | IndexData  | IndexValue       | ThresoldType       | 
	|Index Quick View | 3001574    | Equity Indices   | warning to normal  |
	
	
	 @TC08066	
	Scenario Outline: Warning to Auto Hold - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	And User update the thresold values in RTm table in Database as "<ThresoldType>"
	Then User will perform right click action on the index
	And User will refresh Mview
	Then User will perform right click action on the index
	And User refresh index from T3 and verify updated values from DB in alert UI as "<ThresoldType>"
	
	Examples:
	|IndexType        | IndexData  | IndexValue       | ThresoldType          | 
	|Index Quick View | 3001574    | Equity Indices   | warning to auto hold  |
	
	@TC08067
	Scenario Outline: Auto Hold to normal - Equity Indices
	Given User logs into Grip application
	And User will fecth time in Dashboard page
	And User navigates to Index tab under Reasearch page
	And User will select type of index as "<IndexType>" and "<IndexValue>"
	When User will search for index data as "<IndexData>"
	And User fetch the current price value of index
	And User update the thresold values in RTm table in Database as "<ThresoldType>"
	Then User will perform right click action on the index
	And User will refresh Mview
	Then User will perform right click action on the index
	And User refresh index from T3 and verify updated values from DB in alert UI as "<ThresoldType>"
	
	Examples:
	|IndexType        | IndexData  | IndexValue       | ThresoldType          | 
	|Index Quick View | 3001574    | Equity Indices   | auto hold to normal   |
	
	
	