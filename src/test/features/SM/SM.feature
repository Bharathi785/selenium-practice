@sm
Feature: SM in status Tab

#TC014001 Default view
@TC014001
Scenario: Default view
Given  Enter URL
And User navigates to status page
Then Default view of filter window,status tree,status

#TC014002 Filter SM Tree - Left pane filter
@TC014002
Scenario Outline: Filter SM Tree - Left pane filter
Given  Enter URL
And User navigates to status page
When User search for filter type
Then User clicks on expand button for the releavant node type as "<NodeType1>"
Then User clicks on expand button for the releavant node type as "<NodeType2>"
Then User clicks on expand button for the releavant node type as "<NodeType3>"
Then User clicks on expand button for the releavant node type as "<NodeType4>"
Then Filter Status tree with key words
Then User clicks on expand button for the releavant node type as "<NodeType1>"
Then User clicks on expand button for the releavant node type as "<NodeType2>"
Then User clicks on expand button for the releavant node type as "<NodeType3>"
Then User clicks on expand button for the releavant node type as "<NodeType4>"

Examples:
|NodeType1|NodeType2    |NodeType3  |NodeType4 |
|Active   |Broker 1/1BK |Broker-1   |Queues    |



#TC014003 Filter Status tree with color status
@TC014003 
Scenario Outline: Filter SM Tree - Left pane filter
Given  Enter URL
And User navigates to status page
Then uncheck the checkboxes for green and purple
Then User clicks on expand button for the releavant node type as "<NodeType1>"
Then verify the purple colour

Examples:
|NodeType1|NodeType2    |NodeType3  |NodeType4 |
|Ticker   |Broker 1/1BK |Broker-1   |Queues    |

#TC014004 Filter Status tree with key words and combination of color status check boxes 
@TC014004 
Scenario Outline: Filter Status tree with key words and combination of color status check boxes 
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<NodeType1>"
Then uncheck the checkboxes for green and purple
Then User search for filter type
Then User clicks on expand button for exchange
Then User clicks on expand button and user finds the colour of the node as "<colour1>"
Then verify the colour and text with sub-node

Examples:
|NodeType1|colour1			 |
|GRIP	  |Amman Stock Exchange|

#TC014005 Clear text entered in filter box 
@TC014005
Scenario Outline:Clear text entered in filter box
Given  Enter URL
And User navigates to status page
Then User click on search button
Then verify all the node without text "<node>"
Then verify all the node without text "<node1>"
Then verify all the node without text "<node2>"
Then verify all the node without text "<node3>"
Then verify all the node without text "<node4>"
Then verify all the node without text "<node5>"
Then verify all the node without text "<node6>"
Then verify all the node without text "<node7>"
Then verify all the node without text "<node8>"
Then verify all the node without text "<node9>"
Then verify all the node without text "<node10>"

Examples:
|node	  ||node1	   ||node2	  ||node3		      ||node4     ||node5    ||node6                 ||node7           ||node8  ||node9    ||node10  |
|Active MQ||System Node||PreMarket||Index Constituents||Data Feeds||Exchanges||Ticker Monitor Indices||Holiday Exchange||Indices||Processes||Handlers|

#TC014006 Untick all  color status check boxes combination
@TC014006
Scenario Outline:Untick all  color status check boxes combination
Given  Enter URL
And User navigates to status page
Then User untick all check boxes click on search button
Then verify all the node without text "<node>"
Then verify all the node without text "<node1>"
Then verify all the node without text "<node2>"
Then verify all the node without text "<node3>"
Then verify all the node without text "<node4>"
Then verify all the node without text "<node5>"
Then verify all the node without text "<node6>"
Then verify all the node without text "<node7>"
Then verify all the node without text "<node8>"
Then verify all the node without text "<node9>"
Then verify all the node without text "<node10>"

Examples:
|node	  |node1	  |node2	|node3		       |node4     |node5    |node6                 |node7           |node8  |node9    |node10  |
|Active MQ|System Node|PreMarket|Index Constituents|Data Feeds|Exchanges|Ticker Monitor Indices|Holiday Exchange|Indices|Processes|Handlers|

#TC014007 Right pane filter
@TC014007
Scenario Outline: Right pane filter
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for the releavant node type as "<Node2>"
Then User clicks on expand button for the releavant node type as "<Node3>"
And click on IDC and verify the right pane filter with keyword

Examples:
|Node1|Node2                		     |Node3  							 |
|GRIP |Index Constituents			     |NASDAQ Stock Exchange Composite	 |


#TC014008 Right pane filter
@TC014008
Scenario Outline: Right pane filter
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for the releavant node type as "<Node2>"
Then User clicks on expand button for the releavant node type as "<Node3>"
And click on IDC and verify the right pane filter after unchecking checkbox


Examples:
|Node1|Node2                		     |Node3  							 |
|GRIP |Index Constituents			     |NASDAQ Stock Exchange Composite	 |

#TC014009  Copy Node 
@TC014009 
Scenario Outline: Copy Node 
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user identifies the bombay stock exchange
Then verify final copy node notification child

Examples:
|Node1   |Node2				   |
|GRIP    |Bombay Stock Exchange|

#TC014011 #TC014010 Reload tree in Support script
@TC014011 @TC014010  @TC014023 @TC014022
Scenario Outline: Reload tree in Support script
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user identifies the bombay stock exchange
Then verify final copy node notification child
Then user pastes the node child
Then user switches to supportpage to reload
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user verifies the paste node child
And verify the database for node creation


Examples:
|Node1   |Node2				   |
|GRIP    |Bombay Stock Exchange|




#TC014012  Copy Node
@TC014012
Scenario Outline: Copy Node 
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user identifies the Australian stock exchange sibling

Examples:
|Node1   |Node2				       |
|GRIP    |Australian Stock Exchange|

#TC014013 Paste Node as Sibling
@TC014013
Scenario Outline: Copy Node 
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user identifies the Australian stock exchange sibling
Then user pastes the node sibling
Then user switches to supportpage to reload

Examples:
|Node1   |Node2			      	   |
|GRIP    |Australian Stock Exchange|

#TC014014 Reload tree in Support script
@TC014014
Scenario Outline: Reload tree in Support script
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then user verifies the paste node Sibling
And verify the database for node creation-Australia
Then delete the node
Then user switches to supportpage to reload

Examples:
|Node1   |Node2				       |
|GRIP    |Australian Stock Exchange|


#TC014015 Copy Node and sub node
@TC014015
Scenario Outline: Copy Node and sub node
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user identifies the Budapest stock exchange subnode child


Examples:
|Node1   |Node2				       |
|GRIP    |Budapest Stock Exchange  |


#TC014016 Paste Node as Child
@TC014016
Scenario Outline: Paste Node as Child
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user identifies the Budapest stock exchange subnode child
Then user pastes the subNode child
Then user switches to supportpage to reload

Examples:
|Node1   |Node2			      	  |
|GRIP    |Budapest Stock Exchange |

#TC014017 Reload tree in Support script
@TC014017
Scenario Outline: Reload tree in Support script
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user verifies the paste SubNode child
Then User clicks on expand button for the releavant node type as "<Node3>"
And verify the database for Subnode creation-Budapest
Then delete the Node finally
Then user switches to supportpage to reload

Examples:
|Node1   |Node2				       |Node3						   |
|GRIP    |Budapest Stock Exchange  |COPY OF Budapest Stock Exchange|



#TC014018 Copy Node and sub node
@TC014018
Scenario Outline: Copy Node and sub node
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user identifies the colombo stock exchange SubNode sibling

Examples:
|Node1   |Node2				       |
|GRIP    |COLOMBO STOCK EXCHANGE   |


#TC014019 Paste Node as Sibling
@TC014019
Scenario Outline: Copy Node 
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user identifies the colombo stock exchange SubNode sibling
Then user pastes the Subnode sibling
Then user switches to supportpage to reload
Examples:
|Node1   |Node2			      	   |
|GRIP    |COLOMBO STOCK EXCHANGE   |

#TC014020 Reload tree in Support script
@TC014020
Scenario Outline: Reload tree in Support script
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then User clicks on expand button for the releavant node type as "<Node3>"
Then user verifies the paste SubNode Sibling 
And verify the database for Subnode creation-Budapest
And verify the database for Subnode creation-Colombo
And delete the node-Colombo
Then user switches to supportpage to reload

Examples:
|Node1   |Node2				       |Node3						   |
|GRIP    |COLOMBO STOCK EXCHANGE   |COPY OF COLOMBO STOCK EXCHANGE |


#TC014021  Edit node
@TC014021
Scenario Outline: Reload tree in Support script
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user clicks on node and edit the value 
Then user switches to supportpage to reload
Then User clicks on expand button for the releavant node type as "<Node1>"
And verify the database the value of param

Examples:
|Node1|Node2                     |
|GRIP |Ticker Monitor Indices     |


#TC014022 disable
@TC014022
Scenario Outline:
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
And disable the node
Then user switches to supportpage to reload
And verify in the database



Examples:
|Node1   |Node2				   |
|GRIP    |Bombay Stock Exchange|

#TC014023 enable
@TC014022
Scenario Outline:
Given  Enter URL
And User navigates to status page
And update the enable ID to one
Then user switches to supportpage to reload
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then verify the node in UI after enabling to one



Examples:
|Node1   |Node2				   |
|GRIP    |Bombay Stock Exchange| 


#TC014024  #TC014025 deleteNode
@TC014024 @TC014025 @TC014011 @TC014023 @TC014022
Scenario Outline: deleteNode for node creation
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"
Then user deletes the node and user verifies the delete notification
Then user switches to supportpage to reload
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for exchange
Then User clicks on expand button for the releavant node type as "<Node2>"

Examples:
|Node1   |Node2				   |
|GRIP    |Bombay Stock Exchange| 


 
#TC014026 Special case of Dual Primary
@TC014026
Scenario Outline: Special case of Dual Primary
Given  Enter URL
And User navigates to status page
Then User clicks on expand button for the releavant node type as "<Node1>"
Then User clicks on expand button for the releavant node type as "<Node2>"
Then User clicks on expand button for the releavant node type as "<Node3>"
And click on IDS and verify process is yes or not

Examples:
|Node1|Node2                     |Node3  |
|GRIP |Handlers				     |IDS	 |





    












