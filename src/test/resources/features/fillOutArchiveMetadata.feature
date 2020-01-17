Feature: Fill out Archive Metadata
  Logged in user can fill out the Archive Metadata of a library.
  
  @KP-19
  @FillOutArchiveMetadata
  Scenario: Open archive metadata
    Given Logged in as User
    And Create new Library "New UI Test Library 2"
    #TODO: Remove the explicte step Open new Library
    And Open Library "New UI Test Library 2"
    And Open Markdown element archive-metadata.md
    When Edit archive metadata:
     | title 				| Title for a Test-Project 																			|
		 | author 			| Author-Lastname, Author-Firstname 														|
		 | description 	| This is a Test-Description for a Test-Project. 								|
		 | year 				| 2020 																													|
		 | institute 		| Institute-Name; Department-Name; Director, Director-Lastname 	|
    Then Archive metadata contains:
     | title 				| Title for a Test-Project 																			|
		 | author 			| Author-Lastname, Author-Firstname 														|
		 | description 	| This is a Test-Description for a Test-Project. 								|
		 | year 				| 2020 																													|
		 | institute 		| Institute-Name; Department-Name; Director, Director-Lastname 	|
    # @After LibrariesSteps.deleteLibrary()