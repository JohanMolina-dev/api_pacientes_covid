Feature: Create a medical appointment for a patient
  Scenario: Successful appointment creation
    Given the "nursing assistant" knows the path of the api for creating patients the patient is successfully created
    When the information of the medical appointment is possessed
    Then the medical appointment is created in the api, the api responds with a code "201"