Feature: Login / Logout
  Registrated users can log in to Keeper.
  Logged in user can log out of Keeper.
  
  #Scenario: Login to Keeper
    #Given Open LoginPage
    #When Login as user
    #Then Homepage is opend
    
	Scenario: Login with empty credentials
		Given Open LoginPage
    When Login with empty credentials
    Then LoginPage stays open
