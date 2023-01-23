Feature: Test TransferMate SignUp Page functionality
  User Story: As a user, I should be able to sign up for TransferMate.


  Background: For the scenarios in the feature file, user is expected to be on Sign up page
    Given user is on the TransferMate Sign up page


  @positive
  Scenario Outline: Sign up functionality with valid credentials in "<accountType>" account
    When user clicks "<accountType>" radio button
    And user selects "<country>" on Country registration
    And user clicks First Name and enters "<firstName>"
    And user clicks Last Name and enters "<lastName>"
    And user clicks Email address and enters email "<email>"
    And user selects "<countryPrefix>" on Mobile Phone registration
    And user clicks Mobile Phone and enters "<phoneNumber>"
    And user clicks Terms of Use and Privacy Policy checkbox
    And user clicks hear about news and offers checkbox
    And user enters the captcha result
    And user clicks open my free account submit button
    Then user lands on email and mobile number verification page
    Examples: Sign up credentials we are going to use in this scenario
     | accountType |   country | firstName | lastName |       email                       | countryPrefix| phoneNumber |
     | education   |  Austria  | Edu       | Cation   | edu_cat648736534ion@tester.com    | Austria      | 43423424234 |
     | individual  |  Italy    | Indiana   | Dual     | indian65456756ad5ual12@tester.com | Italy        | 756765456   |
     | sole_trader |  Romania  | Mihai     | Ion      | miha2i56537864ion45@tester.com    | Romania      | 4987567456  |

  @negative
  Scenario Outline: Sign up functionality without clicking Terms of Use and Privacy Policy checkbox
    When user clicks "<accountType>" radio button
    And user selects "<country>" on Country registration
    And user clicks First Name and enters "<firstName>"
    And user clicks Last Name and enters "<lastName>"
    And user clicks Email address and enters email "<email>"
    And user selects "<countryPrefix>" on Mobile Phone registration
    And user clicks Mobile Phone and enters "<phoneNumber>"
    And user clicks hear about news and offers checkbox
    And user enters the captcha result
    And user clicks open my free account submit button
    Then Terms of Use and Privacy Policy checkbox is highlighted with red border
    Examples: Sign up credentials we are going to use in this scenario
      | accountType |   country | firstName | lastName |       email                     | countryPrefix | phoneNumber |
      | education   |  Austria  | Edu       | Cation   | edu_cat6634534ion@tester.com    | Austria       | 43423424234 |


  @negative
  Scenario Outline: Sign up functionality with only 2 digit in phone number and any other data is valid
    When user clicks "<accountType>" radio button
    And user selects "<country>" on Country registration
    And user clicks First Name and enters "<firstName>"
    And user clicks Last Name and enters "<lastName>"
    And user clicks Email address and enters email "<email>"
    And user selects "<countryPrefix>" on Mobile Phone registration
    And user clicks Mobile Phone and enters "<phoneNumber>"
    And user clicks Terms of Use and Privacy Policy checkbox
    And user clicks hear about news and offers checkbox
    And user enters the captcha result
    And user clicks open my free account submit button
    Then Mobile Phone Number field is highlighted with red border
    Examples: Sign up credentials we are going to use in this scenario
      | accountType |   country | firstName | lastName |       email                    | countryPrefix | phoneNumber |
      | education   |  Austria  | Edu       | Cation   | edu_ca=12t61642n@tester.com    | Austria       | 32          |
      | individual  |  Romania  | Individual| Customer | custo32l@individual.com        | Romania       | 54          |
      | education   |  USA      | Individual| Customer | sdfd23s@individual.com         | USA           | 54          |


  @negative
  Scenario Outline: Sign up functionality with only 2 digit in phone number and a Country with State list and any other data is valid
    When user clicks "<accountType>" radio button
    And user selects "<country>" and "<state>" on Country registration
    And user clicks First Name and enters "<firstName>"
    And user clicks Last Name and enters "<lastName>"
    And user clicks Email address and enters email "<email>"
    And user selects "<countryPrefix>" on Mobile Phone registration
    And user clicks Mobile Phone and enters "<phoneNumber>"
    And user clicks Terms of Use and Privacy Policy checkbox
    And user clicks hear about news and offers checkbox
    And user enters the captcha result
    And user clicks open my free account submit button
    Then Mobile Phone Number field is highlighted with red border
    Examples: Sign up credentials we are going to use in this scenario
      | accountType |   country | state  | firstName | lastName |       email                    | countryPrefix | phoneNumber |
      | education   |  USA      |Oklahoma|Edu        | Cation   | educa12t61642n@tester.com      | USA           | 32          |


  @negative
  Scenario Outline: Sign up functionality with an already registered email address
    When user clicks "<accountType>" radio button
    And user selects "<country>" on Country registration
    And user clicks First Name and enters "<firstName>"
    And user clicks Last Name and enters "<lastName>"
    And user clicks Email address and enters email "<email>"
    And user selects "<countryPrefix>" on Mobile Phone registration
    And user clicks Mobile Phone and enters "<phoneNumber>"
    And user clicks Terms of Use and Privacy Policy checkbox
    And user clicks hear about news and offers checkbox
    And user enters the captcha result
    And user clicks open my free account submit button
    Then Email Address field is highlighted with red border and a label with Already Exists
    Examples: Sign up credentials we are going to use in this scenario
      | accountType |   country | firstName | lastName |       email                    | countryPrefix | phoneNumber |
      | education   |  Austria  | Edu       | Cation   | edu_cat6436534ion@tester.com   | Austria       | 32          |


  @negative
  Scenario Outline: Sign up functionality with an already registered phone number
    When user clicks "<accountType>" radio button
    And user selects "<country>" on Country registration
    And user clicks First Name and enters "<firstName>"
    And user clicks Last Name and enters "<lastName>"
    And user clicks Email address and enters email "<email>"
    And user selects "<countryPrefix>" on Mobile Phone registration
    And user clicks Mobile Phone and enters "<phoneNumber>"
    And user clicks Terms of Use and Privacy Policy checkbox
    And user clicks hear about news and offers checkbox
    And user enters the captcha result
    And user clicks open my free account submit button
    Then Mobile Phone Number field is highlighted with a label Already exists
    Examples: Sign up credentials we are going to use in this scenario
      | accountType |   country | firstName | lastName |       email                    | countryPrefix | phoneNumber |
      | education   |  Austria  | Edu       | Cation   | edu34ion@tester.com            | Austria       | 43423424234 |



    @positive
      @E2E
    Scenario Outline: Sign up functionality with with mail and phone verification
      When user clicks "<accountType>" radio button
      And user selects "<country>" on Country registration
      And user clicks First Name and enters "<firstName>"
      And user clicks Last Name and enters "<lastName>"
      And user clicks Email address and enters email
      And user clicks Mobile Phone and enters "<phoneNumber>"
      And user selects "<countryPrefix>" on Mobile Phone registration
      And user clicks Terms of Use and Privacy Policy checkbox
      And user clicks hear about news and offers checkbox
      And user enters the captcha result
      And user clicks open my free account submit button
      Then user is redirected to verify mail page
      And user verify his email
      Then user completes the password
      And enters the OTP code from the SMS
      And user clicks on verify phone number
      Then user account is registered and verified
      And the main profile page is shown
      Examples: Sign up credentials we are going to use in this scenario
        | accountType |   country | firstName | lastName   | countryPrefix | phoneNumber |
        | education   |  Austria  | Edu       | Cation     | USA           | 7753179296 |
