Feature: Consult patients
  Background:
    Given a "nursing assistant" is correctly authenticated in the covid-patients api

    Scenario: Successful patient consultation
      When the nursing assistant knows the path of the patient consultation api
      And  the nursing assistant has the patient's id
      Then You can check the patient's data, the api responds with a code "200"

    Scenario: Patient consultation without id
      When the nursing assistant knows the api route that allows patient consultation
      And the nursing assistant does not know the patient's id
      Then the patient data cannot be consulted, the api responds with a code "404"


