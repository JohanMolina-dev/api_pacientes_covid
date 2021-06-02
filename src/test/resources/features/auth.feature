Feature: User authentication in the api
  Background:
    Given a "nursing assistant" that knows the path of the authentication api

    Scenario: Successful authentication
      When the user has valid credentials
      Then the user authenticates successfully

    Scenario: Authentication failed
      When user has invalid credentials
      Then the api answers with error code "403"