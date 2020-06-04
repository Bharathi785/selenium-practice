@Datawatch
Feature: DataWatch in Dashboard & Research Tab 

Background:
	Given  Enter URL and Validate HomePage


#TC01001	Data Watch on the DashBorad Tab & Research TAB
@TC01001  
Scenario: DataWatch in Dashboard & Research Tab - Indices
 	
 	Then click on each link in DataWatch and compare count in Dashboard and research tab
 
#TC01002	Data Watch on the DashBorad Tab & Research TAB
 @TC01002 
Scenario: DataWatch in Dashboard & Research Tab - Stocks
     
	Then click on each link in Stocks and validate count in dashboard and research tab
 
 #TC01003	Data Watch on the DashBorad Tab & Research TAB
 @TC01003 
Scenario:  Verify Total count in Contracts
    
    Then click on every link in contracts and compare counts in Dashboard and research tab 
    
 #TC01004	Data Watch on the DashBorad Tab & Research TAB   
 @TC01004 
Scenario: DataWatch in Dashboard & Research Tab -Fixed Income
    
    Then click on each link in Fixed Income and Validate Count in Research Tab
    
 #TC01005	Data Watch on the DashBorad Tab & Research TAB
@TC01005 
Scenario: DataWatch in Dashboard & Research Tab -Forex
   
    Then click on each link in Forex and Validate Count in Research Tab
    
 #TC01006	Data Watch on the DashBorad Tab & Research TAB   
@TC01006 
Scenario:  Verify Total count in Exchanges
    
    Then click on every link in Exchanges and compare counts in Dashboard and research tab 
    
#TC01007	GRIP Processes (all processes within GRIP)
@TC01007 
Scenario: Verify Grip Process Status
   
    Then Check Status of all Grip Process to check either A or B Process Available
    
#TC01008	Incoming Data Feeds (All data feed available within GRIP) 
@TC01008 
Scenario: Verify Incoming Data Feeds Status
    
    Then Check Status of all Datafeed Process to check Process Status A and B  
    
 #TC01009	OutBound Destinations (all vendors that receive GRIP index values)
@TC01009 
Scenario: Verify Outbound Destination Processther

    
    Then check status for Outbound Destination
    
#TC01010 & 11 No Ticks"" for GRIP Status
@TC01010  @TC01011
  Scenario: No Ticks for GRIP Status
   
   	Then Enter Index ID in Jobs edit box in support TAB and Click on Stop Index Calc Job button 
 	Then Refresh data from T3 and verify alert

#TC01012 Alert dots on the Data Watch 	
@TC01012 
Scenario: Verify alert dots
  
	Then Verify the alert dots with color
	
#TC010013 Verify monitor view alert 	
@TC01013 
Scenario:  Verify the monitor view alerts 
     
    Then select monitor view and verify alert
    
#TC010014 Verify research view alert    
@TC01014 
Scenario:  Verify the research view alerts 
    
    Then select research view and verify alert
    
#TC01015 Search in Alerts window  
@TC01015 
Scenario: Verify Alerts window Search 
     
    Then Enter and verify search in Alerts delimited with comma
    
#TC01016 Search in Alerts window 
@TC01016 
Scenario: Verify Alerts window Search 
     
    Then Enter and verify search in Alerts delimited with percentage

#TC01017     Customize view 
@TC01017 
Scenario: Customize view will open with default selection 
    
    Then Verify the default selection for customize view 
    
    
    
#TC01018 Verify Alerts based on Severity Filter
@TC01018 
Scenario: Verify Alerts based on Severity Filter 
    
    Then Change Severity options in Customize view and verify alerts
 
#TC01019 Verify Alerts after Alert Types unchecked 
@TC01019 
Scenario: Verify Alerts after Alert Types unchecked 
    
    Then Uncheck AlertTypes in Customize view and verify alerts
    
#TC01020 Verify Alerts after Alert Types Checked
@TC01020 @failed
Scenario: Verify Alerts after Alert Types checked
     
    Then Check AlertTypes in Customize view and verify alerts
    
#TC01021 Verify Alerts after all Severity options selected 
@TC01021 @failed
Scenario: Verify Alerts after all Severity options checked
   
    Then Check all SeverityOptions in Customize view and verify alerts
    
#TC01022 Verify Alerts after all Alert Types selected
@TC01022 @failed1
Scenario: Verify Alerts after all AlertTypes checked
   
    Then Check all AlertTypes in Customize view and verify alerts
    
#TC01024 Sort each column in in ascending & descending manner alert messages window
@TC01024 @failed
Scenario: Sort each column in in ascending & descending manner alert messages window
	
	Then sort each column in alert window and Validate

#TC01025 &32 Click on options available in context menu for Index
@TC01025 @TC01032 @failed
Scenario: Click on options available in context menu for Index
   
    Then Right click on Index alert and verify options in context Menu
    
#TC01031 &38 Right click on an alert for other than assets
@TC01031 @TC01038
Scenario: Right click on an alert for other than assets
	
	Then Right click on alert and verify option Enabled
	
#TC01039 Download Alert Data 
@TC01039 
Scenario: Click on the Download Alert Data icon on the top right corner of the Alerts Message window
  
  Then Download Alert Data in alert Messgaes and Validate file with proper Data
  
  
#TC1040 Audible Alerts
@TC1040 @TC01056 @TC01057
Scenario: Audible Alerts

   Then click on status tab and update values in SM tree 
   And click on support tab and Reload SM Tree
   Then Verify Audio alert once index is flatline/No ticks
   Then Verify flatline index in dashboard and rollback changes
   Then click on status tab and update values for No ticks in SM tree
   Then Verify Audio alert once index is flatline/No ticks
   Then Verify Noticks index in dashboard and rollback changes
	
#TC01041 Verify the alert dot color against Major indices in "Ticker Monitor"
 @TC01041 
Scenario: Verify the alert dot color against Major indices in Ticker Monitor
   
  Then Verify Alert Color in Ticket Monitor
  
#TC01042 Research TAB - Asset windows
@TC01042 
Scenario: Research tab asset window
    
     Then select research tab and click on each asset and verify
     
#TC01043 Index Families Groupings Tab :    
@TC01043 
Scenario: Index Families Groupings
 
	Then double click on each index family and verify
	
#TC01044 'Click on each context menu option : 
@TC01044  
Scenario: Right click on index family
 
 Then Verify options in Index family
	
#TC01046 Processes tab  : 
@TC01046 
Scenario: Process tab Index Families Groupings
 
 	Then click on Process tab double click on each index family and verify
 	
#TC01047 Click on each context menu option : 
@TC01047 
 Scenario: Right click on index family
 
 Then verify option in Processes
	
#TC01049 Recent Launches tab  : 
@TC01049 
Scenario: Recent tab Index Families Groupings
 
	Then click on recent tab double click on each index family and verify
	
#TC01050 Click on each context menu option : 
@TC01050 
Scenario: Right click on index family

Then verify option in Recent Launch


#TC01052 Tick monitor expansion
@TC01052
Scenario: Tick monitor expansion

	Then Right click on Index and Select copy node and verify node in dashboard


#TC01053 Dot in Ticker Monitor
@TC01053 @TC01054 @TC01055   
Scenario: Dot in Ticker Monitor

	Then Verify dot in Header of ticker Monitor



#TC01058 Alert comment functionality
@TC01058 
Scenario: Alert comment functionality

	Then Add alert reason in DB and verify added reason available in addcomment window

#TC01059 Alert comment functionality
@TC01059 @TC01060  @TC01061 
Scenario: Alert comment functionality

	Then Add Comment in IndexAction reason and verify comment in UI and DB

#@TC01062 Alert Messages - Monitoring View
@TC01062 @TC01063 @TC01064 @TC01065 @failed1
Scenario: Alert Messages - Monitoring View
	Then Select add comment in Monitor view and verify default selection
	Then Add comment in Monitor view and verify maximum character allowed in add comment text
	And click submit buttom and verify comment icon in alert
	And Add new comment on related alert and validate comment
	
#@TC01066 Alert Messages - Monitoring View multiple Select
@TC01066  @TC01067 @TC01068 @TC01069 @TC01070 @TC01071 @failed
Scenario: Alert Messages - Monitoring View multiple Select
	Then Select multiple alert and add comment
	Then Add comment for multiple selected alert and verify maximum character allowed in add comment text
	And Click Submit and verify comment for selected alerts
	Then Verify alert window filter with All radio button selection
	Then Verify alert window filter with Comments radio button selection
	Then Verify alert window filter without Comments radio button selection
	
#@TC01072 Alert Messages - Research View
@TC01072 @TC01073 @TC01074 @TC01075  @failed
Scenario: Alert Messages - Research View
	Then Select add comment in Research view and verify default selection
	And Add comment in Research view and verify maximum character allowed in add comment text
	And click submit buttom and verify comment icon in alert in research view
	And Add new comment on related alert in research view and validate comment
	
#@TC01076 Alert Messages - Research View multiple Select
@TC01076 @TC01077 @TC01078 @TC01079 @TC01080 @TC01081  @failed
Scenario: Alert Messages - Monitoring View multiple Select
	Then Select multiple alert and add comment in Research view
	And Add comment for multiple selected alert in research view and verify maximum character allowed in add comment text
	Then Click Submit and verify comment for selected alerts in reseach view
	Then Verify alert window filter with All radio button selection
	Then Verify alert window filter with Comments radio button selection
	Then Verify alert window filter without Comments radio button selection
	
	
#TC01082 Alert - Download (Monitor View)
@TC01082  @failed2
Scenario: Alert - Download (Monitor View)
Then click on download alerts in monitor view and validate data with UI


#TC01083 Alert Comments - Download (Monitor View)
@TC01083  
Scenario: Alert Comments - Download (Monitor View)
Then click on download comments in monitor view and validate data with UI



#TC01084 Alert - Download (Research View)
@TC01084  @failed
Scenario: Alert - Download Research View)
Then click on download alerts in research view and validate data with UI


#TC01085 Alert Comments - Download (Research View)
@TC01085  
Scenario: Alert Comments - Download (Monitor View)
Then click on download comments in research view and validate data with UI


#TC01086 Index Warning Dashbaord
@TC01086  @TC01087 
Scenario: Index Warning Dashbaord
Then Click on Warning link and validate columns in Index list window
Then Change the Warning filter to Threshold View an verify the window

#TC01088 Stock Warning Dashbaord
@TC01088  @TC01089
Scenario: Stock Warning Dashbaord
Then Click on Warning link and validate columns in Stock list window
Then Change filter to Threshold View an verify the columns in Stock list window

#TC01090 Contract Warning Dashbaord
@TC01090 @TC01091 
Scenario: Contract Warning Dashbaord
Then Click on Warning link and validate columns in contract list window
Then Change filter to Threshold View an verify the columns in contract list window

#TC01092 Fixed Income Warning Dashbaord
@TC01092 
Scenario: Fixed Income Warning Dashbaord
Then Click on Warning link and validate columns in Fixed Income list window
Then Change filter to Threshold View an verify the columns in fixedIncome list window

#TC01094 Forex  Warning Dashbaord
@TC01094 @failed2
Scenario: Forex  Warning Dashbaord
Then Click on Warning link and validate columns in Forex list window


#TC01095 Exchange view Threshold
@TC01095 
Scenario: Exchange view Threshold
Then Update warning Threhold limits for greater than 2 in DB and validate limits in UI

#TC01096 Exchange view Threshold
@TC01096 
Scenario: Exchange view Threshold
Then Update warning Threhold limits for Lower than 2 in DB and validate limits in UI


#TC01097 Alert type for Warning Threshold
@TC01097 @failed3
Scenario: Alert type for Warning Threshold
Then Filter Waning threshold alert type in Alert Messages Window and validate alerts in DB

#TC01098 Alert download for Warning Threshold
@TC01098 @failed3
Scenario: Alert download for Warning Threshold
Then click on download alerts and validate downloaded data with UI


#TC01099 Suspect Counter on Dashboard
@TC01099 
Scenario: Suspect Counter on Dashboard
Then Click on Suspect link and validate columns in index list window
