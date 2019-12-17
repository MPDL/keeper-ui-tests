Feature: Create New Library
  Logged in user can create new libraries.
  
  @KP-17
  @createNewLibrary
  Scenario: Create new Library
    Given Logged in as User
    # And Homepage is opened
    When Create new Library
    Then New Library listed in My Libraries
    And New Library contains default documents
    # @After LibrariesSteps.deleteLibrary()
