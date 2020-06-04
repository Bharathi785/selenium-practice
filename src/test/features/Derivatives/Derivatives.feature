@Derivatives 
Feature: Derivatives Indices in Index tab - Research Tab 

@TC02003 
Scenario: Copy Name - Derivatives 
	Given User logs into Grip application 
	And User will fecth time in Dashboard page 
	And User navigates to Derivatives tab under Research page
	And User fetch the column values of derivative 
	Then User will perform right click action on the derivative 
	And User will click Copy name and derivative name should get copied to the clipboard 
	
@TC02004 
Scenario: Copy Id - Derivatives 
	Given User logs into Grip application 
	And User will fecth time in Dashboard page 
	And User navigates to Derivatives tab under Research page
	And User fetch the column values of derivative 
	Then User will perform right click action on the derivative 
	And User will click Copy Id and derivative id should get copied to the clipboard 
	
@TC02005 
Scenario: Select All Rows - Derivatives 
	Given User logs into Grip application 
	And User will fecth time in Dashboard page 
	And User navigates to Derivatives tab under Research page
	Then User will perform right click action on the derivative 
	And User selects all derivative rows and verify count 
	
@TC02007 
Scenario: Show Exchange Details - Derivatives 
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	Then User will perform right click action on the derivative 
	Then User will perform actions on Show Exchange Details 
	
@TC02008
Scenario: Export to Excel - Derivatives 
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	And User donwloads the CSV file for derivative and verify values
	
@TC02002 
Scenario: Filter combo box 
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	And User verify derivative coulmn header values 
	
@TC02001
Scenario: Search in derivatives List window 
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	When User will search for derivative data
	Then User validate the searched data for the next month
	
@TC02009
Scenario Outline: Data Watch on the DashBorad Tab & Research TAB - Derivatives
	Given User logs into Grip application 
	And User will navigate to Derivative tab via dashboard link as "<LinkType1>"
	Then User verify dashboard datawatch and research derivative count
	And User will navigate to Derivative tab via dashboard link as "<LinkType2>"
	Then User verify dashboard datawatch and research derivative count
	
	Examples:
	|LinkType1   | LinkType2  |
	|Subscribed  | Not Found  |
	
@TC02010	@TC02012
Scenario: Column selection / de-selection - Derivatives
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	When User add new column and verify the reflected column in derivative table
	When User remove new column and verify the reflected column in derivative table
	
@TC02006	@TC02015	@TC02016
Scenario: Report - Derivatives
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	And User fetch the column values of derivative 
	When User will perform right click action on the derivative 
	 Then User will click Report and run it
	 And User will verify the genarted report
	 
@TC02013	
Scenario: Compare with SPIDER (Exchange Portfolio) - Derivatives
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	When User will perform right click action on the derivative 
	Then User compare exchange data with T3 as "Exchange Portfolio"
	
@TC02014
Scenario: Compare with SPIDER (Exchange Timing) - Derivatives
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	When User will perform right click action on the derivative 
	Then User compare exchange data with T3 as "Exchange Timings"
	 
	
@TC02017
Scenario Outline: Select TL Process - Derivatives 
	Given User logs into Grip application 
	And User navigates to Derivatives tab under Research page
	And User fetch the column values of derivative 
	And User navigates to Support Tab
	And User perfroms actions on processes as "<ProcessType1>" and "<ProcessType2>" and "<SelectAction>"
	
	Examples:
	| ProcessType1      | ProcessType2       | SelectAction                  |
	| Tick Logger(TL-A) | Tick Logger(TL-B)  | GET REALTIME DERIVATIVE PRICE |
	
@TC02011
Scenario: Derivatives data under research screen 
	Given User logs into Grip application 
	And User navigates to Exchange tab under Research page
	When User will search for Exchange Derivative data
	Then User will perform right click action on the exchange
	When User will search for derivative data
	Then User validate the searched data for the next month
	And User validate the update on feed price
	
       