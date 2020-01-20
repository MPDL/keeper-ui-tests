Feature: Create New Library
  Logged in user can create new libraries.
  
  @KP-17
  @createLibrary
  Scenario: Create new Library
    Given Logged in as User
    # And Homepage is opened
    When Create new Library "New UI Test Library 1"
    Then My Libraries contains "New UI Test Library 1"
    And Library "New UI Test Library 1" contains:
    | archive-metadata.md |
    | Cared-Data-Certificate-HowTo.pdf |
