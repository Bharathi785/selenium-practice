@mdp
Feature: MDP

#TC025002 MDP process mapped to exchange
@TC025002
Scenario: MDP process mapped to exchange
Given  Enter URL and validate dashboard page
Then User clicks on EODexchange and it directs to exchange window
Then user verifies the exchange id with database
And user verifies the total count eodexchange with database

#TC025003 Exchange mapping to Reuters feed source
@TC025003
Scenario: Exchange mapping to Reuters feed source
Given  Enter URL and validate dashboard page
Then user provided with column and table to verify
Then user verify the columns with mview Reuters feed source


#TC025004 Exchange mapping to IDC feed source
@TC025004
Scenario: Exchange mapping to IDC feed source
Given  Enter URL and validate dashboard page
Then user provided with column and table
Then user verify the columns with another table IDC feed source

#TC025005 Data verification in view
@TC025005
Scenario: Data verification in view
Given Enter URL and validate dashboard page
Then User clicks on EODexchange and it directs to exchange window
Then user click on exchange and select show stock
Then user verifies the total count stock with databasecount

#TC025006 Persistent in RT_T_MDP_TICK_LOG
@TC025006
Scenario: Persistent in RT_T_MDP_TICK_LOG
Given Enter URL and validate dashboard page
Then Verify persistent in tick log table for NASDAQ Stock Exchange

#TC025007 Refresh Exchange from T3
@TC025007
Scenario: Persistent in RT_T_MDP_TICK_LOG
Given Enter URL and validate dashboard page
Then Verify persistent in history table for NASDAQ Stock Exchange
