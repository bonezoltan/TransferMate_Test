package org.example.steps;

import org.example.pages.ConfirmEmailAndPhonePage;
import org.example.pages.RegisterPage;

public class RegisterPageSteps {

    RegisterPage registerPage = new RegisterPage();

    public RegisterPageSteps(){
        registerPage.cookieAccept(true);
    }

    public RegisterPageSteps setAccountType(String text){
        registerPage.setAccountType(text);
        return this;
    }

    public String educationPromptText(){
        setAccountType("education");
        return registerPage.getEducationTextLabel();
    }

    public String individualPromptText(){
        setAccountType("individual");
        return registerPage.getIndividualTextLabel();
    }

    public String soleTraderPrompText(){
        setAccountType("sole_trader");
        return registerPage.getSoleTraderTextLabel();
    }

    public RegisterPageSteps insertTextToFirstName(String text){
        registerPage.sendKeysToFirstName(text);
        return this;
    }

    public RegisterPageSteps insertTextToFirstLastName(String text){
        registerPage.sendKeysToLastName(text);
        return this;
    }

    public RegisterPageSteps insertTextToPhone(String text){
        registerPage.sendKeysToPhoneNumber(text);
        return this;
    }

    public RegisterPageSteps insertTextToEmail(String text){
        registerPage.sendKeysToEmailAdress(text);
        return this;
    }

    public RegisterPageSteps selectDropDownPhonePrefix(String text) {
        registerPage.selectPrefixForPhone(text);
        return this;
    }

    public RegisterPageSteps selectMarketingCheckBox(boolean check){
        registerPage.setMarketingCheckBox(check);
        return this;
    }

    public RegisterPageSteps selectTermsOfUseCheckBox(boolean check){
        registerPage.setPrivacyAndTOUCheckBox(check);
        return this;
    }

    public RegisterPageSteps insertSolvedCaptcha(){
        registerPage.fillSolvedCapthca();
        return this;
    }


    public RegisterPageSteps selectCountry(String text){
        registerPage.changeCountryTo(text);
        return this;
    }

    public ConfirmEmailAndPhonePage registerUser(){
        registerPage.clickRegisterButton();
        return new ConfirmEmailAndPhonePage();
    }

    public String getErrorTextTermsOfUse(){
        return registerPage.getTextErrorForTermsOfUse();
    }
    public String getTextErrorForPhoneNumber(){ return registerPage.getTextErrorForPhoneNumber(); }
    public String getTextErrorForEmailAddress(){ return registerPage.getTextErrorForEmailAddress(); }

}
