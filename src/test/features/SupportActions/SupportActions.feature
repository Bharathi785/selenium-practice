@SupportActions
Feature: SupportActions

Background: 
Given Enter URL and Validate Supportpage

@TC19001 @TC19002 @passive
Scenario Outline: Passivate All - QA
	Then Click on Passivate All and Verify Routing tO QA is checked
	And Uncheck Disable route to QA and verify all Sites set to false
	Then Rollback routing to Default "<ProcessA>", "<ProcessB>","<Action>"
	Examples:
	|ProcessA|ProcessB|Action|
	|Broker-1|Broker-1BK|STOP MR QA ROUTING|


@TC019003 @TC19004 @passive
Scenario Outline: Passivate All - QA
	Then Click on Passivate All and Verify Routing tO QA is checked
	And check Disable route to QA and verify all Sites set to false
	Then Rollback routing to Default "<ProcessA>", "<ProcessB>","<Action>"
	Examples:
	|ProcessA|ProcessB|Action|
	|Broker-1|Broker-1BK|STOP MR QA ROUTING|

@TC019005 @TC019006 
Scenario: Activate All - QA
	Then Click on Passivate All and Verify Routing to ActiveQA is checked
	And Uncheck Enable route to QA and verify all Sites set to true

@TC019007  @TC019008 
Scenario: Activate All - QA
	Then Click on Passivate All and Verify Routing to ActiveQA is checked
	And Verify output for all Sites set to true
	
	
@TC019009 
Scenario: Passivate Site - Handler

	Then Select Handler and select passivate site in SelectAction
	Then verify handler in UI process for passivated sites
	

@TC019010
Scenario: Activate Site - Handler

	Then Select Handler and select Activate site in SelectAction
	Then verify handler in UI process for Activated sites



@TC019011 
Scenario: Select All Process Unlock Error Mail
Then Select all process and select unlockError Mail in SelectAction


@TC019012
Scenario: Select All Process Lock Error Mail
	Then Select all process and select lockError Mail in SelectAction



@TC019013
Scenario: Select All Process Check Error Mail
	Then Select all process and select check error mail in SelectAction


@TC019014  @passive
Scenario Outline: Select All Process paasivate site
	Then Select all process and select passivate site in SelectAction
	Then Rollback routing to Default "<ProcessA>", "<ProcessB>","<Action>"
	Examples:
	|ProcessA|ProcessB|Action|
	|Broker-1|Broker-1BK|STOP MR QA ROUTING|
	


@TC019015 
Scenario: Select All Process Activate site
	Then Select all process and select Activate site in SelectAction
	
@TC019016 
Scenario: Select All Process check site Active
	Then Select all process and select check site active in SelectAction

@TC019017 
Scenario Outline: Handler Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|CHCME-A|CHCME-B|


@TC019018
Scenario Outline: EP Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|EP-A|EP-B|



@TC019019
Scenario Outline: Broker Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|Broker-1|Broker-1BK|
	|Broker-7|Broker-7BK|
	


@TC019020
Scenario Outline: SM Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|SM-A|SM-B|

@TC019021
Scenario Outline: JS Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|JS-A|JS-B|

@TC019022
Scenario Outline: ICE Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|ICE1-A|ICE1-B|
	|ICE17-A|ICE17-B|

 
@TC019023
Scenario Outline: ICLP Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|ICLP-A|ICLP-B|

@TC019024
Scenario Outline: ICLHP Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|ICLHP-A|ICLHP-B|

@TC019025
Scenario Outline: ICLRP Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|ICLRP-A|ICLRP-B|


@TC019026
Scenario Outline: ICDP Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|ICDP-A|ICDP-B|


@TC019027
Scenario Outline: MDP1 Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|MDP1-A|MDP1-B|


@TC019028
Scenario Outline: TL Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|TL-A|TL-B|

@TC019029
	Scenario Outline: UI Support actions
	Then Select Handler for processes and verify all available support actions "<ProcessA>", "<ProcessB>"
	Examples:
	|ProcessA|ProcessB|
	|UI-A|UI-B|


@TC019032 @TC019033  @TC019034 
Scenario: ActiveMQ Console
	Then click on ActiveMQ console link and verify all avaliable brokers links
