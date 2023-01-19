package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static com.codeborne.selenide.Selenide.$;

public class RegisterPage extends BasePage {

    public RegisterPage(){
        BasePage.openRegisterPage();
    }

    SelenideElement countryDropDown() {
        return $(By.id("country"));
    }

    SelenideElement firstNameInput() {
        return $(By.id("first_name"));
    }

    SelenideElement lastNameInput() {
        return $(By.id("last_name"));
    }

    SelenideElement emailAdressInput() {
        return $(By.id("email"));
    }

    SelenideElement phoneNumbberPrefixDropDown() {
        return $(By.id("__pin_mobile_number_international_dialing_code"));
    }

    SelenideElement phoneNumberInput() {
        return $(By.id("__pin_mobile_number_mobile_phone"));
    }


    SelenideElement privacyAndTOUCheckBox() {
        return $(By.id("register_terms_of_use_agree_form_input"));
    }

    SelenideElement marketingCheckBox() {
        return $(By.id("register_newsletter_and_privacy_policy_agree_label_cell"));
    }

    SelenideElement captchaQuestion() {
        return $(By.id("cal_captcha_f10_question"));
    }

    SelenideElement captchaAnswer() {
        return $(By.id("__calc_captcha_text"));
    }

    SelenideElement registerButton() {
        return $(By.id("button_subscribe"));
    }

    SelenideElement educationTextLabel() {
        return $(By.cssSelector("#custom_label_field___label_education_linked > p:nth-child(2)"));
    }

    SelenideElement individualTextLabel() {
        return $(By.cssSelector("#register_individual_warning_label_form_text_paragraph > div > p"));
    }

    SelenideElement soleTraderTextLabel() {
        return $(By.cssSelector("#register_sole_trader_warning_label_form_text_paragraph > div > p"));
    }

    SelenideElement cookieAccept = $(By.cssSelector("a[id=cookies-read-more-link]"));
    SelenideElement errorForTermsOfUse(){
        return $(By.id("register_terms_of_use_agree_error"));
    }
    SelenideElement errorForPhoneNumber() {return $(By.id("register___pin_mobile_number_mobile_phone_error"));}
    SelenideElement errorForEmailAddress() {return $(By.id("register_email_error"));}

    private SelenideElement getAccountTypeCheckBoxBy(String text) {
        return $(By.id("register_account_type_" + text + "_form_input"));
    }

    public String getEducationTextLabel() {
        return educationTextLabel().getText();
    }

    public String getIndividualTextLabel() {
        return individualTextLabel().getText();
    }

    public String getSoleTraderTextLabel() {
        return soleTraderTextLabel().getText();
    }

    public void setAccountType(String text) {
        getAccountTypeCheckBoxBy(text).click();
    }

    public String getTextErrorForTermsOfUse(){
        return errorForTermsOfUse().getText();
    }

    public String getTextErrorForPhoneNumber() {return errorForPhoneNumber().getText(); }
    public String getTextErrorForEmailAddress(){
        return errorForEmailAddress().getText();
    }

    public void sendKeysToFirstName(String text) {
        firstNameInput().sendKeys(text);
    }

    public void sendKeysToLastName(String text) {
        lastNameInput().sendKeys(text);
    }

    public void sendKeysToEmailAdress(String text) {
        emailAdressInput().sendKeys(text);
    }

    public void sendKeysToPhoneNumber(String text) {
        phoneNumberInput().sendKeys(text);
    }

    public void selectPrefixForPhone(String text) {
        phoneNumbberPrefixDropDown().selectOptionContainingText(text);
    }

    public void setPrivacyAndTOUCheckBox(boolean click) {
        if (click) {
            privacyAndTOUCheckBox().click();
        }
    }

    public void setMarketingCheckBox(boolean click) {
        if (click) {
            marketingCheckBox().click();
        }
    }

    public void cookieAccept(boolean click) {

        if (click && cookieAccept.isDisplayed()) cookieAccept.click();
    }

    public void changeCountryTo(String text) {
        countryDropDown().selectOptionContainingText(text);
    }

    public void clickRegisterButton() {
        registerButton().click();
    }

    private String getCaptchaText() {
        return captchaQuestion().getText();
    }

    public String getSolveCapthca() {
        try {
            return solveCaptcha();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void fillSolvedCapthca() {
        fillCapthca(getSolveCapthca());
    }

    private void fillCapthca(String text) {
        captchaAnswer().sendKeys(text);
    }

    private String solveCaptcha() throws ScriptException {
        //Preprocessing String for JS Executer
        String capthca = getCaptchaText().replace("=", "").replace("", " ").trim();

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        return engine.eval(capthca).toString();
    }


}
