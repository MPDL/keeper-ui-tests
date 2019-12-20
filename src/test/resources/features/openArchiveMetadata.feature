Feature: Open Archive Metadata
  Logged in user can open the Archive Metadata of a newly created library.
  
  @KP-18
  @openArchiveMetadata
  Scenario: Open archive metadata
    Given Logged in as User
    And Create new Library
    And Open new Library
    When Open archive metadata
    Then Archive metadata is displayed correctly
    # @After LibrariesSteps.deleteLibrary()
