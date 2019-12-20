Feature: Fill out Archive Metadata
  Logged in user can fill out the Archive Metadata of a library.
  
  @KP-19
  @FillOutArchiveMetadata
  Scenario: Open archive metadata
    Given Logged in as User
    And Create new Library
    #TODO: Remove the explicte step Open new Library
    And Open new Library
    And Open archive metadata
    When Fill out archive metadata
    Then Edited archive metadata is displayed correctly
    # @After LibrariesSteps.deleteLibrary()