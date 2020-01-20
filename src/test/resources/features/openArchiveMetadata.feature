Feature: Open Archive Metadata
  Logged in user can open the Archive Metadata of a newly created library.
  
  @KP-18
  @createLibrary
  Scenario: Open archive metadata
    Given Logged in as User
    And Create new Library "New UI Test Library 4"
    And Open Library "New UI Test Library 4"
    When Open Markdown element archive-metadata.md
    Then Markdown file title archive-metadata.md present
    And Markdown-Viewer buttons present
    And Archive metadata content consists of:
    	| Title |
    	| Author |
    	| Publisher |
    	| Description |
    	| Year |
    	| Institute |
    	| Resource Type |
    	| License |
