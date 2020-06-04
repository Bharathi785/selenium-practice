@AlertExceptionEmailValidation
Feature: Alert Exception Email Validation - Research Tab 

	@TC016019
	Scenario Outline: Database report  
	Given User logs into Grip application 
	And User verify mail generated as "<MailType>"
	
	Examples:
	| MailType        |
	| Database Report |

	@TC016020
	Scenario Outline: DB Query run time  
	Given User logs into Grip application 
	And User verify mail generated as "<MailType>"
	
	Examples:
	| MailType        |
	| DB Query runtime|
	
	@TC016021
	Scenario Outline: DB Query run time remote  
	Given User logs into Grip application 
	And User verify mail generated as "<MailType>"
	
	Examples:
	| MailType                |
	| DB Query runtime remote |
	
	@TC018001
	Scenario Outline: Auto Lock Exception Emails  
	Given User logs into Grip application 
	And User verify mail generated as "<MailType>"
	
	Examples:
	| MailType            |
	| Auto Lock Exception |
	
	@TC018002
	Scenario Outline: Auto Unlock Exception Emails  
	Given User logs into Grip application 
	And User verify mail generated as "<MailType>"
	
	Examples:
	| MailType              |
	| Auto UnLock Exception |
	
	@TC018003
	Scenario Outline: Auto Lock Alert Emails  
	Given User logs into Grip application 
	And User verify mail generated as "<MailType>"
	
	Examples:
	| MailType        |
	| Auto Lock Alert |
	
	@TC018004
	Scenario Outline: Auto Unlock Exception Emails  
	Given User logs into Grip application 
	And User verify mail generated as "<MailType>"
	
	Examples:
	| MailType          |
	| Auto UnLock Alert |
	
	@TC018005
	Scenario Outline: Manually Lock Error Emails
	Given User logs into Grip application 
	And User navigates to Support Tab in Grip
	And User perfroms actions on processes as "<ProcessType1>" and "<SelectAction>"
	And User verify mail generated as "<MailType>"
	
	Examples:
	| ProcessType1              | SelectAction    | MailType          |
	| CME Contribution(CHCME-A) | LOCK ERROR MAIL | Manual Lock Alert |
	
	@TC018006
	Scenario Outline: Manually UnLock Error Emails
	Given User logs into Grip application 
	And User navigates to Support Tab in Grip
	And User perfroms actions on processes as "<ProcessType1>" and "<SelectAction>"
	And User verify mail generated as "<MailType>"
	
	Examples:
	| ProcessType1              | SelectAction      | MailType            |
	| CME Contribution(CHCME-A) | UNLOCK ERROR MAIL | Manual UnLock Alert |
	

	