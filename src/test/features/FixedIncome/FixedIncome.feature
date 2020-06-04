@FixedIncome
Feature: FixedIncome - Research Tab

    @TC05001 @TC05002  @TC05003
	Scenario Outline: Manual Hold Release - FixedIncome module
	Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page
	And User navigates to Fixedincome tab under Research page
	And user selects fixedincome as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation1>"
	Then User will hold fixedincome and verify the current value as "<HoldType1>"
	When User verify the fixedincome hold status in table and alert table as "<HoldType1>"
	And User navigates to Fixedincome tab under Research page
	And user selects fixedincome as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation2>"
	When User verify the fixedincome hold status in table and alert table as "<HoldType2>"
		
	Examples:
	|TradingSession|HoldingStatus|DataFeed  |Operation1| HoldType1    | HoldType2     |Operation2| 
	|Open          |   Not Held  |Subscribed| Hold     | manualHold   | manualRelease | Release  |
	
	@TC05004 @TC01028 @TC01035  @Datawatch
	Scenario Outline: Release for Not Held - FixedIncome module
	Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page
	And User navigates to Fixedincome tab under Research page
	And user selects fixedincome as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation>"
	And user verify the fixed income alert message in alert table
	Then Right click on fixedIncome and verify options in context menu
	
	Examples:
	|TradingSession|HoldingStatus|DataFeed  |Operation|
	|Open          |   Not Held  |Subscribed|Release  |
	
	@TC05029
	 Scenario Outline: Hold Fixedincome at a negative number
	 Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page  
	And User navigates to Fixedincome tab under Research page
	And user selects fixedincome as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation1>"
	And User enters hold price as negative value and validate the same
	
	Examples:
|TradingSession|HoldingStatus|DataFeed  |Operation1|
|Open          |   Not Held  |Subscribed| Hold     |
	
	@TC05030	
	Scenario Outline: Hold multiple FixedIncome/ manual release
    Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page 
	And User will navigate to fixedincome tab via dashboard subscribed link 
	And User will hold/release multiple fixedIncome as "<ActionType1>"
	And User will navigate to fixedincome tab via dashboard subscribed link
	And User will hold/release multiple fixedIncome as "<ActionType2>"
	
	Examples:
	|ActionType1   | ActionType2 |
	| Hold         | Release     |
	
	@TC05031	
	Scenario Outline: Hold a FixedIncome multiple times in a row
	Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page 
	And User navigates to Fixedincome tab under Research page
	When User will search for fixedincome as "<fixedID>"
	And User will hold/release multiple fixedIncome as "<ActionType1>"
	And User will hold/release multiple fixedIncome as "<ActionType2>"
	 
	Examples:
	|fixedID     |ActionType1  | ActionType2      |
	|  13171924  |holdMultiple | ReleaseMultiple  |
	
	@TC05032
    Scenario: Search in Fixed Income List window -Fixed Income 
    Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page 
	And User navigates to Fixedincome tab under Research page
    And User will search for the fixedincome with comma and verify 
    
    @TC05033 
	Scenario: Filter combo box 
	Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page 
	And User navigates to Fixedincome tab under Research page
	Then User Select different option available in fixedincome combo filter and verify the column names
  
	  @TC05034  @TC05035  @TC05037
       Scenario Outline: Copy Name/ Copy Id /Reports- FixedIncome
       Given User logs into Grip application
	   And User will fetch time for fixed income in Dashboard page 
	   And User navigates to Fixedincome tab under Research page
	   When User will search for fixedincome as "<fixedID>"
       And User will click Copy name and fixedincome name should get copied to the clipboard
       And User will click Copy Id and fixedincome id should get copied to the clipboard
       And User will click Report and fixedincome name populated in Asset edit box
       
       Examples:
       | fixedID |
       |124723194| 
       
        @TC05036
       Scenario: Select All Rows-FixedIncome
       Given User logs into Grip application
	   And User will fetch time for fixed income in Dashboard page 
	   And User navigates to Fixedincome tab under Research page
       And User selects all fixedincome rows and verify count
       
       @TC05038  @TC05039
	Scenario Outline: Show Details - fixed incoms
	Given User logs into Grip application
	And User navigates to Fixedincome tab under Research page
	 When User will search for fixedincome as "<fixedID>"
	Then User will perform actions on fixedincome as "<ShowType>"

	Examples:
	| fixedID       | ShowType    |
	|   124723194   | exchange    |
	|   124723194   | indices     |
	
  @TC05040     
   Scenario: Export to Excel - fixed income
    Given User logs into Grip application
     And User navigates to Fixedincome tab under Research page
    Then User click on Export to Excel and Fixed Income Report file should be generated in csv format with proper data
    
    @TC05005 @TC05006 @TC05007 @TC05008 @TC05009
	Scenario Outline: Hold by auto based /Auto Release - FixedIncome
	Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page
	And User navigates to Fixedincome tab under Research page
	When User will search for fixedincome as "<fixedID>" and perform hold operation
	Then User will hold fixedincome and verify the current value as "<HoldType>"
	And User will perform auto based fixedincome release
	
	Examples:
	|fixedID   |Operation|HoldType |
    |120234979 |Hold     |Auto Hold|
    
   
@TC05041  @TC05042
 Scenario Outline:Warning Threshold - FixedIncome 
  Given User logs into Grip application
 And User will fetch time for fixed income in Dashboard page
 When User navigates to Exchange as "<ExchangeName>"
 And User will modify the warning threshold as "<WarningUpdate1>","<warningValue>"
And User navigates to Fixedincome tab under Research page
 And User selects fixedincome as "<holdtype1>" and verify warning alert"<WarningUpdate1>"
 When User navigates to Exchange as "<ExchangeName>"
 And User will modify the warning threshold as "<WarningUpdate2>","<warningValue>"
 And User navigates to Fixedincome tab under Research page
 And User selects fixedincome as "<holdtype2>" and verify warning alert"<WarningUpdate2>"
  
  	Examples:
	|ExchangeName   |WarningUpdate1|WarningUpdate2|warningValue|holdtype1|holdtype2|
	| Yield Broker  | update       |rollbackdata  | 0.02       |Warning  |Not Held |

	 
    @TC05022 @TC05023
	Scenario Outline: Auto Hold/ Manual Release- FixedIncome
	Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page 
	And User navigates to Fixedincome tab under Research page
	When User will search for fixedincome as "<fixedID>"
	And User updates the fixedincome baseline threshold value
	And User navigates to Fixedincome tab under Research page
    When User will search for fixedincome as "<fixedID>"
	And User performs release operation on fixedincome"<holdType1>"
	And User verifies the fixedincome holdtype as "<holdType2>"
	
	Examples:  
	|fixedID    |holdType1 |holdType2|
	|120234979  |Auto Hold |Not Held |
	
	@TC05024 @TC05025 @TC05026 
	Scenario Outline: Auto Hold/Manual Hold/ Manual Release-FixedIncome
	Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page 
	And User navigates to Fixedincome tab under Research page
	When User will search for fixedincome as "<fixedID>"
	And User updates the fixedincome baseline threshold value
	And User navigates to Fixedincome tab under Research page
	When User will search for fixedincome as "<fixedID>"
	And User performs hold operation on fixedincome"<holdType1>"
	And User performs release operation on fixedincome"<holdType2>"
	And User verifies the fixedincome holdtype as "<holdType2>"
	
	Examples:
	|fixedID   |holdType1 |holdType2   |holdType3|
	|120234979 |Auto Hold | Manual Hold|Not Held |	
	
	 @TC05019-manualhold
	Scenario Outline: Fixed Income on manual hold before threshold value change
    Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page
	And User navigates to Fixedincome tab under Research page
	And User manually hold/release fixedincome as "<fiName>","<holdtype>"
	
	Examples:
	|fiName                |holdtype |
    |AUGV   4.000 08/20/20 | Not Held|
      
                                          
	 @TC05011 @TC05012 @TC05013 @TC05014 @TC05015 @TC05016 @TC05017 @TC05018
	 Scenario Outline: Auto Hold/Auto Release - Fixed Income
	 Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page
	When User navigates to Exchange as "<ExchangeName>"
	Then User will modify UpDown percent values as "<DataUpdate1>","<thresholdValues>"
	And User navigates to Fixedincome tab under Research page
	And User selects fixedincome as "<holdtype1>" and verify alert"<DataUpdate1>"
	When User navigates to Exchange as "<ExchangeName>"
	Then User will modify UpDown percent values as "<DataUpdate2>","<thresholdValues>"
	And User navigates to Fixedincome tab under Research page
	And User selects fixedincome as "<holdtype2>" and verify alert"<DataUpdate2>"
     
	Examples:
	|ExchangeName  | holdtype1  | DataUpdate1 | DataUpdate2 |holdtype2|thresholdValues|
	|  Yield Broker|  Auto Hold | update      | rollbackdata| Not Held|0.00000001     |
	
	@TC05019-release
	Scenario Outline: Fixedincome on manual hold after threshold value change and release.
    Given User logs into Grip application
    And User will fetch time for fixed income in Dashboard page
	And User navigates to Fixedincome tab under Research page
	And User manually hold/release fixedincome as "<fiName>","<holdtype>"
	
	Examples:
	|fiName               |holdtype   |
    |AUGV   4.000 08/20/20|Manual Hold| 
    
    @TC05020
    Scenario Outline:No FixedIncome on hold-Threshold value set to 0.00
	 Given User logs into Grip application
	And User will fetch time for fixed income in Dashboard page 
	When User navigates to Exchange as "<ExchangeName>"
	Then User will modify UpDown percent values as "<DataUpdate1>","<thresholdValues>"
	And User navigates to Fixedincome tab under Research page
	And User selects fixedincome as "<holdtype1>" and verify alert"<DataUpdate1>"
	When User navigates to Exchange as "<ExchangeName>"
	Then User will modify UpDown percent values as "<DataUpdate2>","<thresholdValues>"
	And User navigates to Fixedincome tab under Research page
	And User selects fixedincome as "<holdtype2>" and verify alert"<DataUpdate2>"

	Examples:
	|ExchangeName   | DataUpdate1 | DataUpdate2 |thresholdValues|
	|Yield Broker   | update      | rollbackdata|0.00           |
    
	   
    
    