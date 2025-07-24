@epic=GrandTotalCalculation @feature=GrandTotalCalculationFeature
Feature: Grand Total Calculation Functionality
  As an Admin
  I want to be able to see correct calculation Grand Total for the deleted booked days from the activity by Admin
  So that I can see actual Grand Total that reflect all possible reconciled amounts into Parent wallet.

  /**
  * Past cancelled without credit note
  * Future cancelled with credit note
  */

  Background:
    Given I am logged in as Admin on the Admin Dashboard

  @severity=critical @story=GrandTotalCalculationFeature @regression @smoke
  Scenario: Delete 1 lesson in the past. Bank card payment

