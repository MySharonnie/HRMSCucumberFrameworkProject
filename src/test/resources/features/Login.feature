Feature: Login scenarios


  @login @valid
  Scenario: Admin login with valid credentials
    When user enters a valid "<username>" and a valid "<password>"
    And user clicks the login button
    Then user is navigated to the dashboard page

  @login @emptyUsername
  Scenario: Admin attempts login with a valid username and empty password field
    When user enters a valid "<username>" leaving the password field blank
    And user clicks the login button
    Then user receives "Password is Empty" for the empty password field

    @login @emptyPassword
      Scenario: Admin attempts login with an empty username field with a valid password field
      When user leaves the username field blank and enters a valid "<password>"
      And user clicks the login button
      Then user receives "Username cannot be empty" for the empty username field


  @login @invalidCredentials
  Scenario Outline: Admin attempts login with invalid username or invalid password
    When user enters an invalid "<username>" or an invalid "<password>"
    And user clicks the login button
    Then user receives an "Invalid credentials" error message next to the login button
    And user enters a valid "<username>" and a valid "<password>"
    And user clicks the login button
    Then user is navigated to the dashboard page
    Examples:
      | username | password    |
      | Admin    | Humanhrm123 |
      | min      | Hum@nhrm123 |


