Feature: Upload file
  Logged in user can upload a file to a library.
  
  @KP-29
  @uploadFile
  Scenario: Upload file
    Given Logged in as User
    And Create new Library
    And Open new Library
    When Upload file to Library
    Then Library contains file
    # @After LibrariesSteps.deleteLibrary()
  
  @KP-29
  @receiveCertificate
  Scenario: Receive Certificate
    Given Logged in as User
    And Create new Library
    And Open new Library
    When Upload file to Library
    And Open archive metadata
    And Fill out archive metadata
    Then Library contains certificate
    # @After LibrariesSteps.deleteLibrary()