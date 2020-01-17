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
	Then File About.markdown is viewed
	
	Scenario: Open Cared Data Commitment
	Given Logged in as User
	When Open Cared Data Commitment
	Then File CaredDataPrinciples.markdown is viewed
	
	@KP-12
	Scenario: Open Terms of Service
	Given Logged in as User
	When Open Terms of Service
	Then File TermsOfService.markdown is viewed
	
	@KP-39
	Scenario: Open Download Client
	Given Logged in as User
	When Open Download Client page
	Then Download Client page is opend
	
	@KP-13
	Scenario: Check Seafile link
	Given Logged in as User
	Then Seafile link is https://www.seafile.com/en/home/
	
	@KP-7
	Scenario: Check MPDL link
	Given Logged in as User
	Then MPDL link is https://www.mpdl.mpg.de/
	
	@KP-15
	Scenario: Check Contact Keeper Support email
	Given Logged in as User
	Then Contact Keeper Support email is keeper@mpdl.mpg.de
	
	@KP-16
	Scenario: Open Impressum
	Given Logged in as User
	When Open Impressum
	Then File Impressum.markdown is viewed
	
	Scenario: Open Privacy Policy
	Given Logged in as User
	When Open Privacy Policy
	Then File DSGVO_Keeper.md is viewed
	