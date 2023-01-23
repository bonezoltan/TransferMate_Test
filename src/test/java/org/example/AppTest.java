package org.example;

import org.example.pages.ConfirmEmailAndPhonePage;
import org.example.steps.CreatePasswordPageSteps;
import org.example.steps.RegisterPageSteps;
import org.example.support.EmailResponse;
import org.example.support.TempEmail;
import org.example.support.TwilioSMSHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class AppTest 
{

    RegisterPageSteps registerPageSteps = new RegisterPageSteps();

    @Test
    public void educationTextPrompt(){
        //Institude possibly typeo?
        Assert.assertTrue(registerPageSteps.educationPromptText().contains("Educational Institude"));
    }

    @Test
    public void individualTextPrompt(){
        Assert.assertTrue(registerPageSteps.individualPromptText().contains("Use an 'Individual' account for personal payments"));
    }

    @Test
    public void soleTraderTextPrompt(){
        Assert.assertTrue(registerPageSteps.soleTraderPrompText().contains("Use a 'Sole Trader' account if you're a registered sole trader"));
    }

    @Test
    public void testDropDown(){
        registerPageSteps.selectDropDownPhonePrefix("Ireland");
    }

    @Test
    public void fillInWithDataAndRegister(){
        ConfirmEmailAndPhonePage confirmEmailAndPhonePage = fillInWithData();

        Assert.assertEquals(confirmEmailAndPhonePage.getCheckEmailLabelText(),"Check your mail","The Check your mail label is not present");
        Assert.assertEquals(confirmEmailAndPhonePage.currentPageTitle(),"Email and Mobile Number Verification","The page title is not Email and Mobile Number Verification");

    }

    private ConfirmEmailAndPhonePage fillInWithData(){

        return registerPageSteps
                .setAccountType("partnership")
                .insertTextToFirstName("Test")
                .insertTextToFirstLastName("Tester")
                .insertTextToEmail("H6356556ey@Oh.ho")
                .selectCountry("Iceland")
                .insertTextToPhone("98312039123")
                .selectDropDownPhonePrefix("Hungary")
                .selectMarketingCheckBox(true)
                .selectTermsOfUseCheckBox(true)
                .insertSolvedCaptcha()
                .registerUser();
    }

    private ConfirmEmailAndPhonePage fillInWithDataAndSpecificEmail(String email){
        return registerPageSteps
                .setAccountType("partnership")
                .insertTextToFirstName("Test")
                .insertTextToFirstLastName("Tester")
                .insertTextToEmail(email)
                .selectCountry("Ireland")
                .insertTextToPhone("7753179296")
                .selectDropDownPhonePrefix("USA")
                .selectMarketingCheckBox(true)
                .selectTermsOfUseCheckBox(true)
                .insertSolvedCaptcha()
                .registerUser();
    }

    @Test
    public void testE2E() throws InterruptedException {
        TempEmail tempEmail = new TempEmail();
        String email = tempEmail.getEmail();

        fillInWithDataAndSpecificEmail(email);

        //We need to wait to activation link to be sent
        TimeUnit.SECONDS.sleep(15);

        EmailResponse emailResponse = new EmailResponse("");
        try {
            emailResponse = tempEmail.call();
        }catch (Exception e){
            e.printStackTrace();
        }
        
        String link = emailResponse.getActivationLink();

        CreatePasswordPageSteps createPasswordPageSteps = new CreatePasswordPageSteps(link);
        createPasswordPageSteps.fillBothPassword("TesterPassword1!");
        createPasswordPageSteps.clickOnSubmit(true);


        String pinCode = new TwilioSMSHandler().getPINCodeFromSMS();
        createPasswordPageSteps.insertPINFromSMS(pinCode);
        createPasswordPageSteps.cilckOnVerify(true);

    }

}
