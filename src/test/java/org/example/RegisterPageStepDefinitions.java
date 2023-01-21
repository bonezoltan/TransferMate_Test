package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.ConfirmEmailAndPhonePage;
import org.example.steps.CreatePasswordPageSteps;
import org.example.steps.RegisterPageSteps;
import org.example.support.EmailResponse;
import org.example.support.TempEmail;
import org.example.support.TwilioSMSHandler;
import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RegisterPageStepDefinitions {
    RegisterPageSteps registerPageSteps;
    ConfirmEmailAndPhonePage confirmEmailAndPhonePage;
    TempEmail tempEmail;
    EmailResponse emailResponse;
    CreatePasswordPageSteps createPasswordPageSteps;
    
    @Given("user is on the TransferMate Sign up page")
    public void userIsOnTheTransferMateSignUpPage() {
        registerPageSteps = new RegisterPageSteps();
    }

    @When("user clicks {string} radio button")
    public void userClicksRadioButton(String type) {
        registerPageSteps.setAccountType(type);
    }

    @And("user selects {string} on Country registration")
    public void userSelectsOnCountryRegistration(String country) {
        registerPageSteps.selectCountry(country);
        
    }


    @And("user clicks First Name and enters {string}")
    public void userClicksFirstNameAndEnters(String firstName) {
        registerPageSteps.insertTextToFirstName(firstName);
        
    }

    @And("user clicks Last Name and enters {string}")
    public void userClicksLastNameAndEnters(String lastName) {
        registerPageSteps.insertTextToFirstLastName(lastName);
        
    }

    @And("user clicks Email address and enters email {string}")
    public void userClicksEmailAddressAndEntersEmail(String email) {
        registerPageSteps.insertTextToEmail(email);
        
    }

    @And("user selects {string} on Mobile Phone registration")
    public void userSelectsOnMobilePhoneRegistration(String country) {
        registerPageSteps.selectDropDownPhonePrefix(country);
    }

    @And("user clicks Mobile Phone and enters {string}")
    public void userClicksMobilePhoneAndEnters(String phoneNumber) {
        registerPageSteps.insertTextToPhone(phoneNumber);
        
    }

    @And("user clicks Terms of Use and Privacy Policy checkbox")
    public void userClicksTermsOfUseAndPrivacyPolicyCheckbox() {
        registerPageSteps.selectTermsOfUseCheckBox(true);
        
    }

    @And("user clicks hear about news and offers checkbox")
    public void userClicksHearAboutNewsAndOffersCheckbox() {
        registerPageSteps.selectMarketingCheckBox(true);
    }

    @And("user enters the captcha result")
    public void userEntersTheCaptchaResult() {
        registerPageSteps.insertSolvedCaptcha();
    }

    @And("user clicks open my free account submit button")
    public void userClicksOpenMyFreeAccountSubmitButton() {
        confirmEmailAndPhonePage = registerPageSteps.registerUser();
    }

    @Then("user lands on email and mobile number verification page")
    public void userLandsOnEmailAndMobileNumberVerificationPage() {
        Assert.assertEquals(confirmEmailAndPhonePage.getCheckEmailLabelText(),"Check your mail","The Check your mail label is not present");
        Assert.assertEquals(confirmEmailAndPhonePage.currentPageTitle(),"Email and Mobile Number Verification","The page title is not Email and Mobile Number Verification");

    }

    @Then("Terms of Use and Privacy Policy checkbox is highlighted with red border")
    public void termsOfUseAndPrivacyPolicyCheckboxIsHighlightedWithRedBorder() {
        Assert.assertTrue(registerPageSteps.getErrorTextTermsOfUse().contains("Click OK to return"),"The error label is missing");
    }

    @Then("Mobile Phone Number field is highlighted with red border")
    public void mobilePhoneNumberFieldIsHighlightedWithRedBorder() {
        Assert.assertTrue(registerPageSteps.getTextErrorForPhoneNumber().contains("Please enter correct information"),"The error label is missing");
    }

    @Then("Email Address field is highlighted with red border and a label with Already Exists")
    public void emailAddressFieldIsHighlightedWithRedBorderAndALabelWithAlreadyExists() {
        Assert.assertTrue(registerPageSteps.getTextErrorForEmailAddress().contains("Already exists"),"The error label is missing");
    }

    @Then("Mobile Phone Number field is highlighted with a label Already exists")
    public void mobilePhoneNumberFieldIsHighlightedWithALabelAlreadyExists() {
        Assert.assertTrue(registerPageSteps.getTextErrorForPhoneNumber().contains("Already exists"),"The error label is missing");
    }

    @And("user clicks Email address and enters email")
    public void userClicksEmailAddressAndEntersEmail() {
        tempEmail = new TempEmail();
        registerPageSteps.insertTextToEmail(tempEmail.getEmail());
    }

    @Then("user is redirected to verify mail page")
    public void userIsRedirectedToVerifyMailPage() throws InterruptedException {
        //To wait for TM servers to process the sign-up + the tempmail servers to process the received mail
        TimeUnit.SECONDS.sleep(10);
    }

    @And("user verify his email")
    public void userVerifyHisEmail() throws IOException {
        emailResponse = tempEmail.call();
        String link = emailResponse.getActivationLink();
        createPasswordPageSteps = new CreatePasswordPageSteps(link);
    }

    @Then("user completes the password")
    public void userCompletesThePassword() {
        createPasswordPageSteps.fillBothPassword("Testpassword1!");
        createPasswordPageSteps.clickOnSubmit(true);
    }

    @And("enters the OTP code from the SMS")
    public void entersTheOTPCodeFromTheSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        String pinCode = new TwilioSMSHandler().getPINCodeFromSMS();
        createPasswordPageSteps.insertPINFromSMS(pinCode);
    }

    @And("user clicks on verify phone number")
    public void userClicksOnVerifyPhoneNumber() {
        createPasswordPageSteps.cilckOnVerify(true);
    }

    @Then("user account is registered and verified")
    public void userAccountIsRegisteredAndVerified() {
        
    }


    @And("the main profile page is shown")
    public void theMainProfilePageIsShown() {
    }


    @And("user selects {string} and {string} on Country registration")
    public void userSelectsAndOnCountryRegistration(String country, String state) {
        registerPageSteps.selectCountry(country);
        registerPageSteps.selectDropDownCountryState(state);
    }
}
