Feature: Deletion of a patient in the api covid patients
  Background:
    Given a "nursing assistant" is correctly authenticated, he wants to delete a patient

    Scenario: Delete a patient successfully
      When a nursing assistant knows the id of the patient to be removed
      Then the patient is correctly eliminated from the api, api responds with code "200"

    Scenario: Deleting a patient with an invalid id
        When a nursing assistant does not know the id of the patient to be deleted, enters a wrong id
        Then no patient is eliminated and the api responds with code "404"