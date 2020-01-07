Feature: Open Project Catalog
  Logged in user can open the Project Catalog.
  
  @KP-20
  @openProjectCatalog
  Scenario: Open Project Catalog
    Given Logged in as User
    When Open Project Catalog
    Then Project Catalog Page is opened
    #TODO: Project Catalog is displayed correctly