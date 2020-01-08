Feature: Open footer links
	Logged in user can open all links in the footer.

	@KP-14
	Scenario: Open Help
	Given Logged in as User
	When Open Help page
	Then Help page is opend
	
	Scenario: Open About
	Given Logged in as User
	When Open About dialog
	Then About dialog is opend
	
	@KP-4
	Scenario: Open About Keeper
	Given Logged in as User
	When Open About Keeper
	Then About Keeper is opend
	
	Scenario: Open Cared Data Commitment
	Given Logged in as User
	When Open Cared Data Commitment
	Then Cared Data Commitment is opend
	
	@KP-12
	Scenario: Open Terms of Servicet
	Given Logged in as User
	When Open Terms of Servicet
	Then Terms of Service is opend
	
	@KP-39
	Scenario: Open Download Client
	Given Logged in as User
	When Open Download Client page
	Then Download Client page is opend
	
	@KP-13
	Scenario: Check Seafile link
	Given Logged in as User
	Then Seafile link is corect
	
	@KP-7
	Scenario: Check MPDL link
	Given Logged in as User
	Then MPDL link is corect
	
	@KP-15
	Scenario: Check Contact Keeper Support email
	Given Logged in as User
	Then Contact Keeper Support email is corect
	
	@KP-16
	Scenario: Open Impressum
	Given Logged in as User
	When Open Impressum
	Then Impressum is opend
	
	Scenario: Open Privacy Policy
	Given Logged in as User
	When Open Privacy Policy
	Then Privacy Policy is opend
	