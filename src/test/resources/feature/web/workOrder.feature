Feature: Automation Task of the Work Order Application

  Background:
    Given User is on work order sign in page
    When User sign in with valid credential of Account Owner

  Scenario: Create an admin and cross verify the detail entered
    Given User is on "Users" screen
    When User tap on the add User button
    And User enters all the field in user screen
    And User select Client Admin as the user role
    And User taps on the Submit button
    Then Success message "User has been added successfully." should be displayed
    And Verify user detail screen
    When User clicks on delete button
    Then Success message "User has been deleted successfully." should be displayed


  Scenario: To verify validations on add user screen
    Given User is on "Users" screen
    When User tap on the add User button
    And User taps on the Submit button
    Then Error message should be displayed
      | First name is required | Email is required | Phone is required | Role is required |

  Scenario: verify GUI components of Work order dashboard
    Given User is on "Dashboard" screen
    Then User should be able to see the widgets
      | Work Order | Kiosk Health Status | Activity Log | Offline Kiosk Log |
    Then User should be able to see the workorder tiles
      | Not Started | In Progress | On Hold | Closed | Complaints |
    Then User should be able to see the company tiles
      | Administrator | Personnel | Facilities | Vendors |
    Then User should be able to see the components of Kiosk Heath Status
    Then User should be able to see the components of Offline Kiosk Log


