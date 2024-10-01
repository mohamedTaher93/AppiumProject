Feature: test PlayStore home page

#Scenario Outline: Verify Home Page Displayed
#  Given I Set Delivery Location Manually "<Address>"
#  Then Verify Home Screen Displayed
#
#  Examples:
#    |Address    |
#    |Riyadh Park|

Scenario Outline: Search for Restaurant
  Given I Set Delivery Location Manually "<Address>"
  Then Verify Home Screen Displayed
  When I Search For "<Restaurant>" Title
  When I Select "<TargetedRestaurant>" Title
  Then Verify "<TargetedRestaurant>" Screen Opened Successfully


  Examples:
    |Address    | Restaurant      | TargetedRestaurant    |
    |Riyadh Park| Shawerma house  | Shawarma Ajiba  |

Scenario Outline: Set App To Background
  Given I Set Delivery Location Manually "<Address>"
  When I Set The App To Background
  And I Get Back The App From Background
  Then Verify Home Screen Displayed

  Examples:
    |Address|
    |Riyadh Park|