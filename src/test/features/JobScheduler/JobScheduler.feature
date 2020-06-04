@JobScheduler
Feature: JobScheduler - Data Maintain Tab

  @TC015001   @TC015025 
  Scenario Outline: Add New Job, Edit Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will type the single run timing as "<SingleRun>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will click on Edit button and modify JOB and save as "<SingleRun>" and "<UpdateSingleRun>"
  And User will delete the job from the table as "<Delete>"
  Examples:
  |           JobTypeName           |  SingleRun      | Enabled |JobTypeId| UpdateSingleRun | Delete |
  |Index Portfolio Comparison Report|11:59,02:59,05:59|  true   |    1    |12:59,03:59,06:59|   no   |
  
  
  
  @TC015002   @TC015026    @TC015031
  Scenario Outline: Add New Job, Edit Job, Enable Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Recurring run
  And User will type the Recurring run timing as "<Recurring>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will click on Edit button and modify JOB and save as "<SingleRun>"
  And User will disable the job and save as "<EnableUpdate>"
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will verify the Disable status and Enable the Job
  And User will delete the job from the table as "<Delete>"
   Examples:
  |           JobTypeName              |       Recurring     | Enabled |JobTypeId|    SingleRun    |EnableUpdate|Delete|
  |Exchange Portfolio Comparison Report|0,5,9,15,29,45,49,59 |   true  |    2    |02:59,05:59,09:59|   false    |  no  |
  
  
  @TC015003    @TC015027
  Scenario Outline: Add New Job, Edit Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Recurring run
  And User will type the Recurring run timing as "<Recurring>"
  And User will type run Start and Stop time as "<RunTime1>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will type run Start and Stop time as "<RunTime2>"
  And User will delete the job from the table as "<Delete>"
   Examples:
  |           JobTypeName            |       Recurring     | Enabled |JobTypeId|RunTime1|RunTime2|Delete|
  |Exchange Timings Comparison Report|0,5,9,15,29,45,49,59 |   true  |    3    |   Yes  |  No    |  no  |
  
  
  
  
  @TC015013   @TC015018  @TC015019   @TC015028 
  Scenario Outline: Add New Job, Delete Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will type the single run timing as "<SingleRun>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will delete the job from the table as "<Delete>"
  
  Examples:
  |           JobTypeName          |                  SingleRun              | Enabled |JobTypeId|Delete|
  |      Refresh MViews            |02:59,10:59,12:59,14:59,16:59,18:59,23:59|   true  |   13    |  no  |
  | Index Prelim Close Report      |02:59,10:59,12:59,14:59,16:59,18:59,23:59|   true  |   17    |  no  |
  |    Index Definition Update     |                  16:59,19:59            |   true  |   18    |  no  |
  

  
  @TC015005    @TC015006
  Scenario Outline: Add New Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Recurring run
  And User will type the Recurring run timing as "<Recurring>"
  And User will type run StartTime and StopTime as "<StartTime>" and "<StopTime>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will delete the job from the table as "<Delete>"
  Examples:
  |           JobTypeName             |       Recurring     | Enabled |JobTypeId|StartTime|StopTime|Delete|
  |Index Calc Params Comparison Report|0,5,9,15,29,45,49,59 |   true  |    5    |         |        |  no  |
  |           Database Report         |0,5,9,15,29,45,49,59 |   true  |    6    |  07:30  |  09:00 |  no  |
  
  
  @TC015007     @TC015039
  Scenario Outline: Add New Job, Refresh data for Single Run and Recurring Frequency
  Given User logs into Grip application
  And User will fetch time in Dashboard page for jobscheduler
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will click on schedule type as Recurring run
  And User will type the single run timing as "<SingleRun>"
  And User will type the Recurring run timing as "<Recurring>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will refresh the job and verify the last and next run for Single and Recurring time
  And User will delete the job from the table as "<Delete>"
   Examples:
  |           JobTypeName              |   Recurring   | Enabled |JobTypeId|    SingleRun    |Delete|
  |     Datawatch Comparison Report    |   15,29,59    |   true  |    7    |11:59,02:59,05:59|  no  |
  
  
  
  @TC015008   @TC015033
  Scenario Outline: Add New Job, Disable New Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will type the single run timing as "<SingleRun>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will disable the job and verify the same
  And User will delete the job from the table as "<Delete>"
  Examples:
  |           JobTypeName           |    SingleRun      | Enabled |JobTypeId|Delete|
  |Index Values Comparison Report   | 11:59,02:59,05:59 |   true  |    8    |  no  |
  
  
  @TC015009   @TC015034    @TC015036
  Scenario Outline: Add New Job, Disable New Job, Force run
  Given User logs into Grip application
  And User will fetch time in Dashboard page for jobscheduler
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Recurring run
   And User will type the Recurring run timing as "<Recurring>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will disable the job and verify the same
  And User will enable the job and force run the job
  And User will verify the Force run status and refresh the job and verify the last run
  And User will delete the job from the table as "<Delete>"
   Examples:
  |           JobTypeName     |   Recurring  | Enabled |JobTypeId|Delete|
  |Move Exchange to new Day   |19,29,39,49,59|   true  |    9    |  no  |
    
  
  
  @TC015010   @TC015035
  Scenario Outline: Add New Job, Disable New Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will type the single run timing as "<SingleRun>"
  And User will select all the scheduled days
  And User will type Exchange Segment Id as "<ExchangeSegment>"
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will disable the job and verify the same
  And User will delete the job from the table as "<Delete>"
  Examples:
  |           JobTypeName        |SingleRun| Enabled |JobTypeId|ExchangeSegment|Delete|
  |Reset Option Contract Prices  |  21:59  |   true  |    10   |     166       |  no  |
  
  
  @TC015011    @TC015024
  Scenario Outline: Add New Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will type the single run timing as "<SingleRun>"
  And User will select the scheduled days as "<ScheduledDays>"
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will delete the job from the table as "<Delete>"
  Examples:
  |      JobTypeName      |  SingleRun  | Enabled |JobTypeId|ScheduledDays|Delete|
  |Reset Realtime Prices  |   23:59     |   true  |   11    |    Sun      |  no  |
  |Evaluate Process Status|   20:59     |   true  |   23    |    Sat      |  no  |

  
  @TC015012
  Scenario Outline: Add New Job
  Given User logs into Grip application
  And User will fetch time in Dashboard page for jobscheduler
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will type the single run timing as "<SingleRun>"
  And User will select all the scheduled days
  And User will type the Index Ids as "<IndexId>"
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will delete the job from the table as "<Delete>"
  
  Examples:
  |       JobTypeName     |                   SingleRun              | Enabled |JobTypeId| IndexId |Delete|
  | Index Pricing Report  |02:59,10:59,12:59,14:59,16:59,18:59,23:59 |   true  |   26    | 3003558 |  no  |
  
  
  
  @TC015015   @TC015016   @TC015017    @TC015022
  Scenario Outline: Add New Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Recurring run
  And User will type the Recurring run timing as "<Recurring>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will delete the job from the table as "<Delete>"
  Examples:
  |            JobTypeName        |   Recurring   | Enabled |JobTypeId|Delete|
  |   Publish Index Close Values  |19,29,39,49,59 |   true  |    14   |  no  |
  |      Move Index to New Day    |19,29,39,49,59 |   true  |    15   |  no  |
  |Publish Index Settlement Values|19,29,39,49,59 |   true  |    16   |  no  |
  |   Auto Adjust Index Base Line |      59       |   true  |    21   |  no  |
  
  
  @TC015020    @TC015029
  Scenario Outline: Add New Job, Delete Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will type the single run timing as "<SingleRun>"
  And User will select all the scheduled days
  And User will type the Store procedure as "<StoreProcedure>"
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will delete the job from the table as "<Delete>"
  
  Examples:
  |       JobTypeName     |   SingleRun      | Enabled |JobTypeId|    StoreProcedure     |Delete|
  |  Run Stored Procedure |05:59,09:59,13:59 |   true  |   19    |COMPILE_INVALID_OBJECTS|  no  |
  
  
  @TC015021   @TC015030
  Scenario Outline: Add New Job, Delete Job
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Recurring run
  And User will type the Recurring run timing as "<Recurring>"
  And User will select all the scheduled days
  And User will type the Session Wait Time as "<SessionWaitTime>"
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will delete the job from the table as "<Delete>"
  Examples:
  |   JobTypeName  |            Recurring           | Enabled |JobTypeId|SessionWaitTime|Delete|
  |DB Query Runtime|0,5,9,15,19,29,35,39,45,49,55,59|   true  |    20   |       600     |  no  |
  
  
  @TC015023   @TC015032   @TC015043
  Scenario Outline: Add New Job, Enable New Job, Active Flag - False (Force Run)
  Given User logs into Grip application
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will force run the inactive job and verify the alert message
  And User will enable the job and verify the same
  And User will delete the job id from the table
  Examples:
  |           JobTypeName         | Enabled |JobTypeId|
  |Resend EOD Index Values to T3  |  false  |    22   |



  @TC015004    @TC015037    @TC015041
  Scenario Outline: Add New Job, Refresh data for Single Run, Active Flag - True
  Given User logs into Grip application
  And User will fetch time in Dashboard page for jobscheduler
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Single run
  And User will type the single run timing as "<SingleRun>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will refresh the job and verify the last and next run for Single time
  And User will delete the job from the table as "<Delete>"
  
  Examples:
  |           JobTypeName          |    SingleRun    | Enabled |JobTypeId|Delete|
  |Index Timings Comparison Report |11:59,02:59,05:59|   true  |    4    |  no  |


  @TC015014    @TC015038
  Scenario Outline: Add New Job, Refresh data for Recurring Frequency
  Given User logs into Grip application
  And User will fetch time in Dashboard page for jobscheduler
  And User navigates to JobSchduler under Data maintain page
  And User will click on Add new job and choose the job type as "<JobTypeName>"
  And User will click on schedule type as Recurring run
  And User will type the Recurring run timing as "<Recurring>"
  And User will select all the scheduled days
  And User will enable the job and save as "<Enabled>"
  And User fetch the current job id details
  And User will verify the jobs table and database as "<JobTypeId>"
  And User will refresh the job and verify the last and next run for Recurring time
  And User will delete the job from the table as "<Delete>"
  Examples:
  |            JobTypeName        |   Recurring   | Enabled |JobTypeId|Delete|
  |          Refresh MViews       |19,29,39,49,59 |   true  |    13   |  no  |
  
  
  @TC015040
  Scenario: Refresh data for Scheduled Days
  Given User logs into Grip application
  And User will fetch time in Dashboard page for jobscheduler
  And User navigates to JobSchduler under Data maintain page
  And User will refresh the data for Scheduled Days and verify the last run and next run
  
  @TC015042
  Scenario: Active Flag - False
  Given User logs into Grip application
  And User will fetch time in Dashboard page for jobscheduler
  And User navigates to JobSchduler under Data maintain page
  And User will check the enable status and verify the last run
  
  
  @Delete
  Scenario Outline: Delete Jobs
  Given User logs into Grip application
  And User will fetch time in Dashboard page for jobscheduler
  And User navigates to JobSchduler under Data maintain page
  And User will delete the Jobs from the table as "<Delete>"
  Examples:
  |Delete |
  |  yes  |