@Contracts
Feature: Contracts - Research Tab

	@TC04001 @TC04002  @TC04003
	Scenario Outline: Manual Hold Release - contract module
	Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User navigates to Contracts tab under Research page
	And user selects contracts as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation1>"
	Then User will hold contracts and verify the current value as "<HoldType1>"
	When User verify the hold status in table and alert table as "<HoldType1>"
	And User navigates to Contracts tab under Research page
	And user selects contracts as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation2>"
	When User verify the hold status in table and alert table as "<HoldType2>"
	
		
	Examples:
	|TradingSession|HoldingStatus|DataFeed  |Operation1| HoldType1    | HoldType2     |Operation2| 
	|Open          |   Not Held  |Subscribed| Hold     | manualHold   | manualRelease | Release  |
	
	
	@TC04004 @TC01027 @TC01034 @Datawatch
	Scenario Outline: Release for Not Held - contract module
	Given User logs into Grip application
	And User will fetch time in Dashboard page
    And User navigates to Contracts tab under Research page
	And user selects contracts as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation>" 
	And user verify the alert message in alert table
	Then Right click on contract and verify options in context menu
	Examples:
	|TradingSession|HoldingStatus|DataFeed  |Operation|
	|Open          |   Not Held  |Subscribed|Release  |
	
     @TC04005 @TC04006 @TC04007 @TC04008 @TC04009
	Scenario Outline: Hold by auto based /Auto Release - Contracts
	Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User navigates to Contracts tab under Research page
    When User will search for contract as "<ContractID>" and perform hold Operation
	Then User will hold contracts and verify the current value as "<HoldType>"
	And User will perform auto based contract release
	
	Examples:
	|ContractID|HoldType |
    |  858298  |Auto Hold|
    
    
    @TC04019-manualhold
	Scenario Outline: Contract on manual hold before threshold value change
    Given User logs into Grip application
	And User will fetch time in Dashboard page 
	And User navigates to Contracts tab under Research page
	And User manually hold/release contracts as "<contractName>","<holdtype>"
	
	Examples:
	|contractName|holdtype    |
    |  505346    | Manual Hold|
      
                                          
	 @TC04011 @TC04012 @TC04013 @TC04014 @TC04015 @TC04016 @TC04017 @TC04018
	 Scenario Outline: Auto Hold/Auto Release - Contracts
	 Given User logs into Grip application
	And User will fetch time in Dashboard page 
	When User navigates to Exchange as "<ExchangeName>"
	Then User will modify UpDown percent values as "<DataUpdate1>","<thresholdValues>"
	And User navigates to Contracts tab under Research page
	And User selects contracts as "<holdtype1>" and verify alert"<DataUpdate1>"
	When User navigates to Exchange as "<ExchangeName>"
	Then User will modify UpDown percent values as "<DataUpdate2>","<thresholdValues>"
	And User navigates to Contracts tab under Research page
	And User selects contracts as "<holdtype2>" and verify alert"<DataUpdate2>"
	
	Examples:
	|ExchangeName   | holdtype1  | DataUpdate1 | DataUpdate2 |holdtype2|thresholdValues|
	| Nymex Division|  Auto Hold | update      | rollbackdata| Not Held|0.00000001     |
	
	@TC04019-release
	Scenario Outline: Contract on manual hold after threshold value change and release.
    Given User logs into Grip application
	And User will fetch time in Dashboard page 
	And User navigates to Contracts tab under Research page
	And User manually hold/release contracts as "<contractName>","<holdtype>"
	
	Examples:
	|contractName|holdtype|
    |505346      |Not Held| 
    
    @TC04020
    Scenario Outline:No Contracts on hold-Threshold value set to 0.00
	 Given User logs into Grip application
	And User will fetch time in Dashboard page 
	When User navigates to Exchange as "<ExchangeName>"
	Then User will modify UpDown percent values as "<DataUpdate1>","<thresholdValues>"
	And User selects contract from Exchange as "<ExchangeName>"
	When User navigates to Exchange as "<ExchangeName>"
	Then User will modify UpDown percent values as "<DataUpdate2>","<thresholdValues>"

	Examples:
	|ExchangeName   | DataUpdate1 | DataUpdate2 |thresholdValues|
	| Nymex Division| update      | rollbackdata|0.00           |
	
	
	@TC04022 @TC04023
	Scenario Outline: Auto Hold/ Manual Release -Contracts
	Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User navigates to Contracts tab under Research page
	When User will search for contract as "<ContractID>"
	And User updates the baseline threshold value
	And User navigates to Contracts tab under Research page
	When User will search for contract as "<ContractID>"
	And User performs release operation on contracts"<holdType1>"
	And User verifies the holdtype as "<holdType2>"
	
	Examples:
	|ContractID|holdType1 |holdType2|
	| 858298   |Auto Hold |Not Held |
	
	@TC04024 @TC04025 @TC04026 
	Scenario Outline: Auto Hold/Manual Hold/ Manual Release -Contracts
	Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User navigates to Contracts tab under Research page
	When User will search for contract as "<ContractID>"
	And User updates the baseline threshold value
	And User navigates to Contracts tab under Research page
	When User will search for contract as "<ContractID>"
	And User performs hold operation on contracts"<holdType1>"
	And User performs release operation on contracts"<holdType2>"
	And User verifies the holdtype as "<holdType3>"
	
	Examples:
	|ContractID|holdType1 |holdType2   |holdType3|
	| 858298   |Auto Hold | Manual Hold|Not Held |	
	
	@TC04029
	 Scenario Outline: Hold Contracts  at a negative number
	 Given User logs into Grip application
	And User will fetch time in Dashboard page 
	And User navigates to Contracts tab under Research page
	And user selects contracts as "<TradingSession>","<HoldingStatus>","<DataFeed>" and perform "<Operation1>"
	And User enters negative value and validate the same
	
	Examples:
	|TradingSession|HoldingStatus|DataFeed  |Operation1|
	|Open          |   Not Held  |Subscribed| Hold     |
	
	
	@TC04030	
	Scenario Outline: Hold multiple Contracts/ manual release
    Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User will navigate to Contract tab via dashboard subscribed link 
	And User will hold/release multiple contracts as "<ActionType1>"
	And User will navigate to Contract tab via dashboard subscribed link 
	And User will hold/release multiple contracts as "<ActionType2>"
	
	Examples:
	|ActionType1   | ActionType2 |
	| Hold         | Release     |
	
	@TC04031	
	Scenario Outline: Hold a Contract multiple times in a row
	Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User navigates to Contracts tab under Research page
	When User will search for contract as "<ContractName>"
	And User will hold/release multiple contracts as "<ActionType1>"
	And User will hold/release multiple contracts as "<ActionType2>" 
	
	Examples:
	|ContractName | ActionType1  | ActionType2      |
	|328667       | holdMultiple | ReleaseMultiple  |
	
	@TC04032
       Scenario: Search in Contract List window - Contracts
      Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User navigates to Contracts tab under Research page
    And User will search for the contracts with comma and verify 
    
    @TC04033 
	Scenario: Filter combo box 
	Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User navigates to Contracts tab under Research page
	And User Select different option available in combo filter and verify the column names
  
	  @TC04034  @TC04035  @TC04037
       Scenario Outline: Copy Name/ Copy Id /Reports -Contracts
       Given User logs into Grip application
	   And User will fetch time in Dashboard page
	   And User navigates to Contracts tab under Research page
	   When User will search for contract as "<ContractID>"
       And User will click Copy name and Contract name should get copied to the clipboard
       And User will click Copy Id and Contract id should get copied to the clipboard
       And User will click Report and Contract name populated in Asset edit box
       
       Examples:
       | ContractID|
       | 328667    | 

        @TC04036
       Scenario: Select All Rows-Contracts
       Given User logs into Grip application
	   And User will fetch time in Dashboard page
	   And User navigates to Contracts tab under Research page
       And User selects all contract rows and verify count
       
       @TC04038  @TC04039
	Scenario Outline: Show Details - Equity Indices
	Given User logs into Grip application
	And User navigates to Contracts tab under Research page
	 When User will search for contract as "<ContractID>"
	Then User will perform actions on Show Details as "<ShowType>"

	Examples:
	|ContractID  | ShowType    |
	| 328667     | exchange    |
	| 505191     | indices     |
	
  @TC04040 
  Scenario: Export to Excel - Contracts 
	Given User logs into Grip application 
	And User navigates to Contracts tab under Research page 
	Then User click on Export to Excel and Report file should be generated in csv format with proper data 
	
@TC04041 @TC04042 
Scenario Outline: Warning Threshold - Contracts 
	Given User logs into Grip application 
	And User will fetch time in Dashboard page 
	When User navigates to Exchange as "<ExchangeName>" 
	And User will modify the warning threshold as "<WarningUpdate1>","<warningValue>" 
	And User navigates to Contracts tab under Research page 
	And User selects contracts as "<holdtype1>" and verify warning alert"<WarningUpdate1>" 
	When User navigates to Exchange as "<ExchangeName>" 
	And User will modify the warning threshold as "<WarningUpdate2>","<warningValue>" 
	And User navigates to Contracts tab under Research page 
	And User selects contracts as "<holdtype2>" and verify warning alert"<WarningUpdate2>" 
	
	Examples: 
		|ExchangeName   |WarningUpdate1|WarningUpdate2|warningValue|holdtype1|holdtype2|
		| Nymex Division| update       |rollbackdata  | 0.02       |Warning  |Not Held |
		
		
		@TC04043 @TC04044
	Scenario Outline: Check reset baseline option - contract module
	Given User logs into Grip application
	And User navigates to Contracts tab under Research page
	 When User will search for contract as "<ContractID>"
     Then User will perform right click action on the contracts without test data
    And User will verify the Reset baseline option is present or not for contracts
    And User will click on Reset baseline and verify the popup
    
    Examples:
	|ContractID  |
	|328667      |
	
	@TC04045
 Scenario: Hold multiple Contracts -Reset Baseline
    Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User will navigate to Contract tab via dashboard subscribed link 
	And User will reset baseline multiple contracts and validate the popup 
	
	
	@TC04050
	Scenario Outline: Check reset baseline option - contracts in Not found Status
	Given User logs into Grip application
	And User will fetch time in Dashboard page
	And User navigates to Contracts tab via dashboard unsubscribed link
	When User will search for contract as "<ContractID>"
   Then User will perform right click action on the contracts without test data
   And User will verify the Reset baseline option is present or not for contracts
    And User will click on Reset baseline and verify the popup
	
	 Examples:
	|ContractID|
	|22306829  |
	
	
	
