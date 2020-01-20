Feature: Upload file
  Logged in user can upload a file to a library.
  
  @KP-29
  @createLibrary
  Scenario: Upload file
    Given Logged in as User
    And Create new Library "New UI Test Library 5"
    And Open Library "New UI Test Library 5"
    When Upload file Test.txt to Library
    Then Library contains file Test.txt
  
  @KP-29
  @createLibrary
  Scenario: Receive Certificate
    Given Logged in as User
    And Create new Library "New UI Test Library 6"
    And Open Library "New UI Test Library 6"
    When Upload file Test.txt to Library
    And Open Markdown element archive-metadata.md
    And Edit archive metadata:
     | title 				| Title for a Test-Project 																			|
		 | author 			| Author-Lastname, Author-Firstname 														|
		 | description 	| This is a Test-Description for a Test-Project. 								|
		 | year 				| 2020 																													|
		 | institute 		| Institute-Name; Department-Name; Director, Director-Lastname 	|
    Then Library "New UI Test Library 6" contains certificate