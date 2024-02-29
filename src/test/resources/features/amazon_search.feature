Feature: Amazon Product Search

  Scenario Outline: Search Samsung phones with specific specifications
    Given User is on Amazon.co.uk homepage
    When User clicks on All Menu
    And User navigates to Electronics and Computers > Phones and Accessories > Mobile Phones
    And User applies filters for Camera Resolution <CameraResolution>, Model Year <ModelYear>, and Price Range <PriceRange>
    Then User should see Samsung phones with the specified specifications
    And User verifies the details of each Samsung phone

    Examples:
      | CameraResolution | ModelYear | PriceRange  |
      | 20 MP and above  | 2023      | £50 - £100  |
      | 12 MP and above  | 2022      | £100 - £200 |
      | 16 MP and above  | 2024      | £200 - £300 |