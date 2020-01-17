Feature: Lock Archive Metadata
  Logged in user can lock the Archive Metadata file of a library.
  
  @KP-21
  @LockArchiveMetadata
  Scenario: Lock archive metadata
    Given Logged in as User
    And Create new Library "New UI Test Library 3"
    And Open Library "New UI Test Library 3"
    When Lock archive-metadata.md file
    Then Lock symbole displayed for archive-metadata.md
    When Unlock archive-metadata.md file
    Then Lock symbole not displayed for archive-metadata.md
    # @After LibrariesSteps.deleteLibrary()
    
  #TODO: Archive metadata can be edited (by owner) while locked and after the lock
  
  #TODO: Further test case: Extract the unlock in an extra test case
  
  #TODO: Further test case: other user (with edit rights) can not edit file while locked