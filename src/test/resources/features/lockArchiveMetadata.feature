Feature: Lock Archive Metadata
  Logged in user can lock the Archive Metadata file of a library.
  
  @KP-19
  @LockArchiveMetadata
  Scenario: Lock archive metadata
    Given Logged in as User
    And Create new Library
    And Open new Library
    When Lock archive metadata file
    Then Archive metadata lock symbole is displayed
    When Unlock archive metadata file
    Then Archive metadata lock symbole is not displayed
    # @After LibrariesSteps.deleteLibrary()
    
  #TODO: Archive metadata can be edited (by owner) while locked and after the lock
  
  #TODO: Further test case: Extract the unlock in an extra test case
  
  #TODO: Further test case: other user (with edit rights) can not edit file while locked