Feature: Add a new patient
  Scenario: Add a patient successfully
    Given a "nursing assistant" is correctly authenticated in the api
    And the nursing assistant knows the path of the api for creating patients
    When the complete information of a patient is available
    Then the patient can be created in the api, the api responds with a code "201"