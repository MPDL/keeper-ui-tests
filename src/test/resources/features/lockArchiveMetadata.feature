Feature: Lock Archive Metadata
  Logged in user can lock the Archive Metadata file of a library.
  
  @KP-21
  @createLibrary
  Scenario: Lock and unlock archive metadata
    Given Logged in as User
    And Create new Library "New UI Test Library 3.1"
    And Open Library "New UI Test Library 3.1"
    When Lock archive-metadata.md file
    Then Lock symbole displayed for archive-metadata.md
    When Unlock archive-metadata.md file
    Then Lock symbole not displayed for archive-metadata.md
    
  @KP-21
  @createLibrary
  Scenario: Owner can edit locked archive metadata
    Given Logged in as User
    And Create new Library "New UI Test Library 3.2"
    And Open Library "New UI Test Library 3.2"
    When Lock archive-metadata.md file
    And Open Markdown element archive-metadata.md
    And Edit archive metadata:
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
    
  @KP-21
  @createLibrary
  Scenario: Owner can edit unlocked archive metadata
    Given Logged in as User
    And Create new Library "New UI Test Library 3.3"
    And Open Library "New UI Test Library 3.3"
    And Lock archive-metadata.md file
    Then Unlock archive-metadata.md file
    And Open Markdown element archive-metadata.md
    And Edit archive metadata:
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
  
  #Scenario: Owner can edit unlocked archive metadata
#	1. Given	Authenticate as user
#	2. And		Create library ""
#	3. And		Lock file "" in library ""
#	4. When	Unlock file "" in library ""
#	5. And		Update file "" in library "" ||
#	6. Then	File "" updated in library "" ||
  
  #TODO: Further test case: other user (with edit rights) can not edit file while locked
  