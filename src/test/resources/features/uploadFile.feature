Feature: Upload file
  Logged in user can upload a file to a library.
  
  @KP-17
  @uploadFile
  Scenario: Upload file
    Given Logged in as User
    And Create new Library
    And Open new Library
    When Upload file to Library
    Then Library contains file
    # @After LibrariesSteps.deleteLibrary()
    
  #TODO: Further test case: Check -> @And Library contains certificate
  # Which preconditions must be met to get the certificate?