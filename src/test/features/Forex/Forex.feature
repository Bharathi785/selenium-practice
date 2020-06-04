@Forex
Feature: Forex - Research Tab

	@TC06001 @TC06002  @TC06003
	Scenario Outline: Manual Hold Release - Forex module
	Given User logs into Grip application
	And User will fetch current time for forex in Dashboard page
	When User navigates to Forex tab under Research page
	Then User searches forex data as "<searchData>" and perform "<Operation1>"
	And User will hold forex and verify the current value as "<holdType1>"
	When User verify the forex hold status and alert table as "<holdType1>"
	When User navigates to Forex tab under Research page
	Then User searches forex data as "<searchData>" and perform "<Operation2>"
	When User verify the forex hold status and alert table as "<holdType2>"
		
	Examples:
	|searchData|Operation1| holdType1    | holdType2     |Operation2| 
	|INR/USD   | Hold     | manualHold   | manualRelease | Release  |
	
	
	@TC06004 @TC01029 @TC01036 @Datawatch
	Scenario Outline: Release for Not Held - Forex module
	Given User logs into Grip application
	And User will fetch current time for forex in Dashboard page
	When User navigates to Forex tab under Research page
	Then User searches forex data as "<searchData>" and perform "<Operation>"
	And user verify the forex alert message in alert table
	Then Right click on forex and verify options in context menu
	
	Examples:
	|searchData  |Operation|
	|INR/USD     |Release  |
	 
	
	@TC06017
	 Scenario Outline: Hold Forex  at a negative number
	 Given User logs into Grip application
	When User navigates to Forex tab under Research page
	Then User searches forex data as "<searchData>" and perform "<Operation>"
	And User enters Forex Currentvalue as negative value and validate the same
	
	  Examples:
	  |searchData|Operation |
	  |  INR/USD | Hold     |
	
	@TC06018
       Scenario: Search in Forex
      Given User logs into Grip application
	And User will fetch current time for forex in Dashboard page
	When User navigates to Forex tab under Research page
    And User will search for the forex with comma and verify 
    
	  @TC06019  @TC06020 
       Scenario Outline: Copy Name/Copy Id-Forex
       Given User logs into Grip application
	   When User navigates to Forex tab under Research page
	   And User searches for Forex Name as "<searchData>"
       And User will click Copy name and Forex name should get copied to the clipboard
       And User will click Copy Id and Forex id should get copied to the clipboard
       
       Examples:
       |searchData|
       |  INR/USD | 
       
        @TC06021
       Scenario: Select All Rows-Forex
       Given User logs into Grip application
	  And User will fetch current time for forex in Dashboard page
	 When User navigates to Forex tab under Research page
     And User selects all forex rows and verify count
       
	 @TC06022     
       Scenario: Export to Excel-Forex
       Given User logs into Grip application
        When User navigates to Forex tab under Research page
	   Then User click on Export to Excel and Forex Report file should be generated in csv format with proper data
	  
	@TC06015	
	Scenario Outline: Hold multiple Forex manual release
    Given User logs into Grip application
    And User will fetch current time for forex in Dashboard page
	And User will navigate to Forex tab via dashboard subscribed link 
	And User will hold/release multiple forex as "<ActionType1>"
	And User will navigate to Forex tab via dashboard subscribed link 
	And User will hold/release multiple forex as "<ActionType2>"
	
	Examples:
	|ActionType1   | ActionType2     |
	| Hold         | Release         |
	
	@TC06016
	Scenario Outline: Hold a Forex multiple times in a row
	Given User logs into Grip application
	And User will fetch current time for forex in Dashboard page
	When User navigates to Forex tab under Research page
	And User searches for Forex Name as "<searchData>"
	And User will hold/release multiple forex as "<ActionType1>"
	And User will hold/release multiple forex as "<ActionType2>" 
	
	Examples:
	|searchData   | ActionType1  | ActionType2      |
	|ARS/USD      | holdMultiple | ReleaseMultiple  |
	

	 @TC06005 @TC06006 @TC06007 
	Scenario Outline: Manual Hold/Auto Release - Forex module
	Given User logs into Grip application
	And User will fetch current time for forex in Dashboard page
	When User navigates to Forex tab under Research page
	Then User searches forex data as "<searchData>" and perform "<Operation>"
	And User will hold forex and verify the current value as "<holdType>"
	And User will perform auto based forex release
	
	Examples:
	|searchData     |Operation|holdType |
    |INR/USD        |Hold     |Auto Hold|
    
     @TC06013
	Scenario Outline: Manual Hold  - Forex module
	Given User logs into Grip application
	And User will fetch current time for forex in Dashboard page
	When User navigates to Forex tab under Research page
	Then User searches forex data as "<searchData>" and perform "<Operation1>"
	And User will hold forex and verify the current value as "<holdType1>"
	Then User searches forex data as "<searchData>" and perform "<Operation2>"
	
	Examples:
	|searchData|Operation1| holdType1    | Operation2|
	|ARS/USD   | Hold     | manualHold   | Release   |