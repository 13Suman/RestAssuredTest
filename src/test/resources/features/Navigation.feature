Feature: API Testing

  Scenario: Hit the URL
    When User hits the Url
    Then User is getting Response


  Scenario: To create user
    When User hits the request
      | name     | job    |
      | morpheus | leader |
    Then User gets created
    And User is saved in the DB

  Scenario: To update user
    When User enters the details
      | name     | job           |
      | morpheus | zion resident |
    Then User gets updated
    And User details gets updated in DB



