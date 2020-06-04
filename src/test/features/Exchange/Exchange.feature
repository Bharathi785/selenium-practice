@exchange
Feature: Exchanges in Research Tab

#TC07001  Exchange List window verification
@TC07001
Scenario: Exchange List window verification
Given  Enter URL and Validate_Research page
Then Exchange List window and verify all records

#TC07002 Search in Stock List window
 @TC07002
 Scenario: Search in Stock List window
 Given  Enter URL and Validate_Research page
 Then Exchange List window and verify typed records 
 
 
 #TC07003 Select Exchange and right click 'Show Stocks':
 @TC07003
 Scenario: Show stock list of given Exchanges
 Given  Enter URL and Validate_Research page
 Then select exchange and show stocks of that exchange
 
 #TC07004 Select Exchange and right click 'Show Indices':
 @TC07004
 Scenario: Show indices list of given Exchanges
 Given  Enter URL and Validate_Research page
 Then select exchange and show indices of that exchange
 
 
 #TC07005 Select Exchange and right click 'Show Contracts':
 @TC07005
 Scenario: Show contracts list of given Exchanges
 Given  Enter URL and Validate_Research page
 Then select exchange and show contracts of that exchange
 
 #TC07006 Select Exchange and right click 'Show f11':
 @TC07006
 Scenario: Show f11 list of given Exchanges
 Given  Enter URL and Validate_Research page
 Then select exchange and show f11 of that exchange
 
 #TC07007 Select Exchange and right click 'Copy Name':
  @TC07007
 Scenario: Copy Name of Exchanges
 Given  Enter URL and Validate_Research page
 Then select exchange and copy name of that exchange
 
#TC07008 Select Exchange and right click 'Copy ID':
@TC07008
Scenario: Copy Id of Exchanges
Given Enter URL and Validate_Research page
Then select exchange and copy id of that exchange
  
#TC07009 Select Exchange and right click 'select All Rows':
@TC07009
Scenario: select All Rows of Exchanges
Given  Enter URL and Validate_Research page
Then select exchange and select All Rows 
 
#TC07010 Export to Excel
@TC07010
Scenario: Export to Excel
Given Enter URL and Validate_Research page
Then click on download exchange data and verify file has been generated 

#TC07011 Refresh from T3 - NewDay Status
@TC07011 @TC01030  @TC01037  @Datawatch
Scenario: Refresh from T3 - NewDay Status
Given Enter URL and Validate_Research page
Then select an Exchange and click on refresh from T3 And verify the alert message New Day 
Then Right click on Exchange and verify options in context menu
 	
 	
#TC07012 Refresh from T3 - Open Status
@TC07012
Scenario: Refresh from T3 - Open Status
Given Enter URL and Validate_Research page
Then select an Exchange and click on refresh from T3 And verify the alert message open 
 	
 	
#TC07013 Refresh from T3 - Session break Status
@TC07013
Scenario: Refresh from T3 - Session break Status
Given Enter URL and Validate_Research page
Then select an Exchange and click on refresh from T3 And verify the alert message session break
 	
 	
#TC07014 Refresh fromT3 - Closed Status
@TC07014
Scenario: Refresh from T3 - Closed Status
Given Enter URL and Validate_Research page
Then select an Exchange and click on refresh from T3 And verify the alert message closed
 	
 	
#TC07015 Refresh fromT3 - Multiple Exchange
@TC07015
Scenario: Refresh from T3 - Multiple
Given Enter URL and Validate_Research page
Then select an Exchange and click on refresh from T3 And verify the alert message multiple

#if we daily run these cases it will put the GRIP QA exchange data in an invalid state and index calculation will be impacted.
#TC07016 Move to Next Day - NewDay Status
#@TC07016
#Scenario: Move to Next Day - One Exchange
#Given Enter URL and Validate_Research page
#Then select an Exchange and click on next day And verify the alert message pop-up 

 	
 	
#TC07017 Move to Next Day - open Status
@TC07017
Scenario: Move to Next Day - open
Given Enter URL and Validate_Research page
Then select an Exchange and click on next day And verify the alert message open
 	
 	
#TC07018 Move to Next Day - Session Break Status
@TC07018
Scenario: Move to Next Day - sessionBreak
Given Enter URL and Validate_Research page
Then select an Exchange and click on next day And verify the alert message sessionbreak
 	
#TC07019 Move to Next Day - Closed Status
@TC07019
Scenario: Move to Next Day - closed
Given Enter URL and Validate_Research page
Then select an Exchange and click on next day And verify the alert message closed state 

#if we daily run these cases it will put the GRIP QA exchange data in an invalid state and index calculation will be impacted. 	
#TC07020 select multiple exchange and move to next day
#@TC07020
#Scenario: Move to Next Day - Multiple Exchange
#Given Enter URL and Validate_Research page
#Then select multiple Exchange and click on next day
	
	

#TC07021 Refresh Exchange from GRIP
@TC07021
Scenario: Refresh Exchange from GRIP
Given Enter URL and Validate_Research page
Then select an Exchange and click on refresh from GRIP And verify the alert message


#TC07022 Refresh Exchange from GRIP (Multiple)
@TC07022
Scenario: Refresh Exchange from GRIP(Multiple)
Given Enter URL and Validate_Research page
Then select multiple Exchange and click refresh from GRIP And verify the alert message


#TC07025 Switch to Local Feed Source:Reuters
@TC07025
Scenario: Switch to Local Feed Source:Reuters
Given Enter URL and Validate_Research page
Then select an Exchange and Switch to Local Feed Source:Reuters And verify the alert message


#TC07026 Switch to Local Feed Source: IDC
@TC07026 
Scenario: Switch to Local Feed Source:IDC
Given Enter URL and Validate_Research page
Then select an Exchange and Switch to Local Feed Source:IDC And verify the alert message

#TC07028 Switch multiple exchanges Feed Source to  (Reuters)
@TC07028 
Scenario: Switch multiple exchanges Feed Source to  (Reuters)
Given Enter URL and Validate_Research page
Then select an multiple Exchange and Switch to Local Feed Source:Reuters And verify the alert message

#TC07029 Switch to Local & Remote (Permanent) Feed Source:Reuters
@TC07029 
Scenario: Switch Feed Source to  (Reuters)
Given Enter URL and Validate_Research page
Then select an Exchange and Switch to Local & Remote Feed Source:Reuters And verify the alert message


#TC07030 Switch to Local & Remote (Permanent) Feed Source: IDC
@TC07030
Scenario: Switch Feed Source to  (IDC)
Given Enter URL and Validate_Research page
Then select an Exchange and Switch to Local & Remote Feed Source:IDC And verify the alert message

#TC07032 Switch Local & Remote (Permanent) Feed Source to (Reuters) for multiple exchanges
@TC07032
Scenario: Switch Local & Remote (Permanent) Feed Source to (Reuters) for multiple exchanges
Given Enter URL and Validate_Research page
Then select multiple Exchange and Switch to Local & Remote Feed Source:Reuters And verify the alert message

#TC07033 Switch exchanges to Feed Source (Reuters)
@TC07033
Scenario: Update Trading date (Current date)
Given Enter URL and Validate_Research page
Then Select an Exchanges Right Click and choose switch to rueters


#TC07034 Switch to EOD/RealTime Ticker
@TC07034
Scenario: Switch to EOD/RealTime Ticker 
Given Enter URL and Validate_Research page
Then select an Exchange and Switch to EOD/RealTime Ticker And verify the alert message

#TC07035 Switch to Real Time Ticker 
@TC07035
Scenario: Switch to Real Time Ticker 
Given Enter URL and Validate_Research page
Then select and Exchange and Switch to RealTime Ticker And verify the alert message

#TC07036 Switch to Composite Ticker 
@TC07036
Scenario: Swtich to Composite Ticker
Given Enter URL and Validate_Research page
Then select an Exchange and Switch to Composite Ticker And verify the alert message


#TC07037 Switch multiple exchanges to EOD/RealTime Ticker 
@TC07037
Scenario: Switch to EOD/RealTime Ticker 
Given Enter URL and Validate_Research page
Then select an multiple Exchange and Switch to EOD/RealTime Ticker And verify the alert message


#TC07038 Switch to EOD/RealTime Ticker
@TC07038
Scenario: Switch to EOD/RealTime Ticker 
Given Enter URL and Validate_Research page
Then select an Exchange and Switch to EOD/RealTime Ticker And verify the alert message-Negative

#TC07039 View Auto HOLD Limits
@TC07039
Scenario: View Auto HOLD Limits
Given Enter URL and Validate_Research page
Then View auto Hold market window will open with proper data


#TC07040  View Auto HOLD Limits (Multiple Exchanges)
@TC07040
Scenario: View Auto HOLD Limits (Multiple Exchanges)
Given Enter URL and Validate_Research page
Then Select multiple Exchanges - Right Click and choose View Auto Hold Limits


#commented since it is creating lot of problem in UI and it is suggested by functional team to comment
#TC07044 Update Trading date (Previous date)
#@TC07044
#Scenario: Update Trading date (Previous date)
#Given Enter URL and Validate_Research page
#Then Select an Exchanges Right Click and choose Update Trading date Previous date

#TC07045 Update Trading date (Next date)
#@TC07045
#Scenario: Update Trading date (Next date)
#Given Enter URL and Validate_Research page
#Then Select an Exchanges Right Click and choose Update Trading date Next date

#TC07046 Update Trading date (Current date)
@TC07046
Scenario: Update Trading date (Current date)
Given Enter URL and Validate_Research page
Then Select an Exchanges Right Click and choose Update Trading date current date

#TC07047 Update Trading date (Previous date) for Multiple exchanges
#@TC07047
#Scenario: Update Trading date (Current date)
#Given Enter URL and Validate_Research page
#Then Select multiple Exchanges Right Click and choose Update Trading date current date

#TC07053 Auto Release when exchange is in new day
@TC07053
Scenario: Auto Release when exchange is in new day
Given Enter URL and Validate_Research page
Then verify the alert message in alert windown of autorelease

#TC07055 stock cannot be loaded when exchange is moved the support team
@TC07055
Scenario: stock is on hold when exchange is moved to next day
Given Enter URL and Validate_Research page
Then verify the alert message in alert window of stock cannot be loaded




 	
 	
 	
 	
 	
  