@epic=Authentication @feature=ClientLoginFeature
Feature: Login Functionality
  As a Parent
  I want to be able to login to the FUNDA website
  So that I can access my account

  Background:
    Given I am on the Client's login page

  @severity=critical @story=ClientLoginSuccessful @regression @smoke
  Scenario: Successful login with valid credentials
    When I enter valid credentials
    And I click on the Sign in button
    Then I redirect to the User Account page

  @severity=high @story=ClientLoginFailed @regression
  Scenario: Failed login with invalid email
    When I enter invalid email
    And I enter valid password
    And I click on the Sign in button
    Then I should see invalid email error message

  @severity=high @story=ClientLoginFailed @regression
  Scenario: Failed login with invalid password
    When I enter valid email
    And I enter invalid password
    And I click on the Sign in button
    Then I should see password mismatch error message

  @severity=medium @story=ClientLoginFailed @regression
  Scenario: Login with empty fields
    When I click on the Sign in button
    Then I should see required email error message
    And I should see required password error message

  # Additional test scenarios for better coverage
  @severity=medium @story=ClientLoginFailed @regression  
  Scenario: Failed login with empty email only
    When I enter valid password
    And I click on the Sign in button
    Then I should see required email error message

  @severity=medium @story=ClientLoginFailed @regression
  Scenario: Failed login with empty password only  
    When I enter valid email
    And I click on the Sign in button
    Then I should see required password error message
