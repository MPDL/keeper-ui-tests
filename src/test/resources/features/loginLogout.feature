Feature: Login / Logout
  Registrated users can log in to Keeper.
  Logged in user can log out of Keeper.
  
  @KP-1
  Scenario: Login as user
    Given Open LoginPage
    When Login as user
    Then User Homepage is opend
    
	Scenario: Login with empty credentials
		Given Open LoginPage
    When Login with empty credentials
    Then LoginPage stays open

	@KP-2
	Scenario: Logout as user
    Given Logged in as User
    # And Homepage is opened
    When Logout as user
    Then Logout Page is opend