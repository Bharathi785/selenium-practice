@databaseconfig
Feature: database configuration

#TC23001  Auto Close - true
@TC23001
Scenario: Auto Close - true
Given user has given with index with auto close value as one
Then user verifies the publish by grip as there is no pre-lim close on previous day
Then user verifies the close tag


#TC23002 Auto Close - false
@TC23002
Scenario: Auto Close - false
Given user has given with index with auto close value as zero
Then user verifies the publish by grip with PC/CLOSE tag on previous day
Then user verifies the close tag for this index


#TC23012 Datapoint Mandatory flag - OFF
@TC23012
Scenario: Datapoint Mandatory flag - OFF
Given Enter URL and Research page
Then user verifies the mandatory flag database value as zero
Then user verifies the datapoint field in available data point screen