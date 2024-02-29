Feature: Product Search Feature
  @saudemo
  Scenario Outline: Search for a product and count the number of products displayed
    Given User navigates to "https://www.saucedemo.com/"
    When User logs in with username "<username>" and password "<password>"
    Then User searches for a product "<product>"
    And User validates the number of products displayed

    Examples:
      | username | password | product                          |
      | standard_user | secret_sauce    | Sauce Labs Backpack  |

