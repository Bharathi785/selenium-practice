@processReadinessCheck
Feature: Process Readiness Check in status Tab

#TC024001 ICE process verification
@TC024001
Scenario Outline: ICE process verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then user verifies the parent node colour as green "<colour1>"
Then User clicks on expand button for the node type as "<colour1>"
Then user click on node to view the right pane "<AProcess>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour1>"
Then user verifies the parent node colour as green "<colour2>"
Then User clicks on expand button for the node type as "<colour2>"
Then user click on node to view the right pane "<AProcess1>"
Then user verifies the child node colour as green/purple "<AProcess1>" and "<BProcess1>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour2>"
Then user verifies the parent node colour as green "<colour3>"
Then User clicks on expand button for the node type as "<colour3>"
Then user click on node to view the right pane "<AProcess2>"
Then user verifies the child node colour as green/purple "<AProcess2>" and "<BProcess2>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour3>"

Examples:
|NodeType1|NodeType2    ||colour1  ||AProcess|BProcess||colour2  ||AProcess1|BProcess1||colour3  ||AProcess2|BProcess2|
|GRIP     |Processes    ||ICE 1	  ||ICE1-A   |ICE1-B  ||ICE 10	 ||ICE10-A  |ICE10-B  ||ICE 11	 ||ICE11-A  |ICE11-B  |

#TC024001 ICE process verification
@TC024001
Scenario Outline: ICE process verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then user verifies the parent node colour as green "<colour1>"
Then User clicks on expand button for the node type as "<colour1>"
Then user click on node to view the right pane "<AProcess>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour1>"
Then user verifies the parent node colour as green "<colour2>"
Then User clicks on expand button for the node type as "<colour2>"
Then user click on node to view the right pane "<AProcess1>"
Then user verifies the child node colour as green/purple "<AProcess1>" and "<BProcess1>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour2>"
Then user verifies the parent node colour as green "<colour3>"
Then User clicks on expand button for the node type as "<colour3>"
Then user click on node to view the right pane "<AProcess2>"
Then user verifies the child node colour as green/purple "<AProcess2>" and "<BProcess2>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour3>"

Examples:
|NodeType1|NodeType2    ||colour1  ||AProcess|BProcess  ||colour2  ||AProcess1|BProcess1||colour3  ||AProcess2|BProcess2|
|GRIP     |Processes    ||ICE 12   ||ICE12-A   |ICE12-B  ||ICE 13	 ||ICE13-A  |ICE13-B  ||ICE 14	 ||ICE14-A  |ICE14-B  |

#TC024001 ICE process verification
@TC024001
Scenario Outline: ICE process verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then user scroll down to view the ticker Logger
Then user verifies the parent node colour as green "<colour1>"
Then User clicks on expand button for the node type as "<colour1>"
Then user click on node to view the right pane "<AProcess>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour1>"
Then user verifies the parent node colour as green "<colour2>"
Then User clicks on expand button for the node type as "<colour2>"
Then user click on node to view the right pane "<AProcess1>"
Then user verifies the child node colour as green/purple "<AProcess1>" and "<BProcess1>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour2>"
Then user verifies the parent node colour as green "<colour3>"
Then User clicks on expand button for the node type as "<colour3>"
Then user click on node to view the right pane "<AProcess2>"
Then user verifies the child node colour as green/purple "<AProcess2>" and "<BProcess2>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour3>"

Examples:
|NodeType1|NodeType2    ||colour1  ||AProcess|BProcess||colour2  ||AProcess1|BProcess1||colour3  ||AProcess2|BProcess2|
|GRIP     |Processes    ||ICE 15   ||ICE15-A |ICE15-B ||ICE 16	 ||ICE16-A  |ICE16-B  ||ICE 17	 ||ICE17-A  |ICE17-B  |

#TC024001 ICE process verification
@TC024001
Scenario Outline: ICE process verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then user scroll down to view the ticker Logger
Then user verifies the parent node colour as green "<colour1>"
Then User clicks on expand button for the node type as "<colour1>"
Then user click on node to view the right pane "<AProcess>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour1>"
Then user verifies the parent node colour as green "<colour2>"
Then User clicks on expand button for the node type as "<colour2>"
Then user click on node to view the right pane "<AProcess1>"
Then user verifies the child node colour as green/purple "<AProcess1>" and "<BProcess1>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour2>"
Then user verifies the parent node colour as green "<colour3>"
Then User clicks on expand button for the node type as "<colour3>"
Then user click on node to view the right pane "<AProcess2>"
Then user verifies the child node colour as green/purple "<AProcess2>" and "<BProcess2>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour3>"

Examples:
|NodeType1|NodeType2    ||colour1  ||AProcess|BProcess||colour2  ||AProcess1|BProcess1||colour3  ||AProcess2|BProcess2|
|GRIP     |Processes    ||ICE 2    ||ICE2-A  |ICE2-B  ||ICE 3	 ||ICE3-A   |ICE3-B   ||ICE 4	 ||ICE4-A   |ICE4-B   |



#TC024001 ICE process verification
@TC024001
Scenario Outline: ICE process verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then user scroll down to view the ticker Logger
Then user verifies the parent node colour as green "<colour1>"
Then User clicks on expand button for the node type as "<colour1>"
Then user click on node to view the right pane "<AProcess>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour1>"
Then user verifies the parent node colour as green "<colour2>"
Then User clicks on expand button for the node type as "<colour2>"
Then user click on node to view the right pane "<AProcess1>"
Then user verifies the child node colour as green/purple "<AProcess1>" and "<BProcess1>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour2>"
Then user verifies the parent node colour as green "<colour3>"
Then User clicks on expand button for the node type as "<colour3>"
Then user click on node to view the right pane "<AProcess2>"
Then user verifies the child node colour as green/purple "<AProcess2>" and "<BProcess2>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour3>"

Examples:
|NodeType1|NodeType2    ||colour1  ||AProcess|BProcess||colour2  ||AProcess1|BProcess1||colour3  ||AProcess2|BProcess2|
|GRIP     |Processes    ||ICE 5   ||ICE5-A |ICE5-B   ||ICE 6	 ||ICE6-A   |ICE6-B   ||ICE 7	 ||ICE7-A   |ICE7-B   |


#TC024001 ICE process verification
@TC024001
Scenario Outline: ICE process verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then user scroll down to view the ticker Logger
Then user verifies the parent node colour as green "<colour1>"
Then User clicks on expand button for the node type as "<colour1>"
Then user click on node to view the right pane "<AProcess>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour1>"
Then user verifies the parent node colour as green "<colour2>"
Then User clicks on expand button for the node type as "<colour2>"
Then user click on node to view the right pane "<AProcess1>"
Then user verifies the child node colour as green/purple "<AProcess1>" and "<BProcess1>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour2>"

Examples:
|NodeType1|NodeType2    ||colour1  ||AProcess|BProcess||colour2  ||AProcess1|BProcess1|
|GRIP     |Processes    ||ICE 8    ||ICE8-A |ICE8-B   ||ICE 9	 ||ICE9-A   |ICE9-B   |

#TC024002 TL Process verification
@TC024002
Scenario Outline: ICE process verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then user scroll down to view the ticker Logger
Then User clicks on expand button for the node type as "<colour1>"
Then  user verifies the parent node colour as green "<colour1>"
Then user click on node to view the right pane "<nodeclick>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness
Then user verifies the parent node colour as green "<colour2>"
Then User clicks on expand button for the node type as "<colour2>"
Then user click on node to view the right pane "<nodeclick1>"
Then user verifies the child node colour as green/purple "<MDPTLA>" and "<MDPTLB>"
Then user verifies the process ready and process readiness

Examples:
|NodeType1||NodeType2||colour1    |nodeclick ||AProcess|BProcess|colour2        |nodeclick1|MDPTLA |MDPTLB |
|GRIP     ||Processes||Tick Logger|TL-A      ||TL-A    |TL-B    |MDP Tick Logger|MDPTL-A   |MDPTL-A|MDPTL-A|


#TC024003 GRIP Console verification
@TC024003
Scenario Outline: GRIP Console verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then User clicks on expand button for the node type as "<NodeType3>"
Then  user verifies the parent node colour as green "<colour1>"
Then user click on node to view the right pane "<nodeclick>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness

Examples:
|NodeType1||NodeType2||NodeType3   ||colour1     ||nodeclick ||AProcess|BProcess|
|GRIP     ||Processes||GRIP Console||GRIP Console||UI-A      ||UI-A    |UI-B    |


#TC024004 Handlers verification
@TC024004
Scenario Outline: Handlers verification
Given  Enter URL and validate page
And User navigates status page and validate it
Then User clicks on expand button for the node type as "<NodeType1>"
Then User clicks on expand button for the node type as "<NodeType2>"
Then User clicks on expand button for the node type as "<NodeType3>"
Then  user verifies the parent node colour as green "<colour1>"
Then user click on node to view the right pane "<nodeclick>"
Then user verifies the child node colour as green/purple "<AProcess>" and "<BProcess>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<NodeType3>"
Then User clicks on expand button for the node type as "<colour2>"
Then  user verifies the parent node colour as green "<colour2>"
Then user click on node to view the right pane "<nodeclick1>"
Then user verifies the child node colour as green/purple "<AProcess1>" and "<BProcess1>"
Then user verifies the process ready and process readiness
Then User clicks on expand button for the node type as "<colour2>"

Examples:
|NodeType1||NodeType2||NodeType3||colour1||nodeclick ||AProcess|BProcess||colour2||nodeclick1 ||AProcess1|BProcess1|
|GRIP     ||Handlers ||IDS      ||IDS    ||IDS-A     ||IDS-A   |IDS-B   || CME-DR||CME-DR     ||CMENR-A  |CMENR-A |


