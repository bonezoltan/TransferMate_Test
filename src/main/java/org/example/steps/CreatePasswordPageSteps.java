package org.example.steps;

import org.example.pages.CreatePasswordPage;

public class CreatePasswordPageSteps {

    CreatePasswordPage createPasswordPage;

    public CreatePasswordPageSteps(String link){
        createPasswordPage = new CreatePasswordPage(link);
    }

    public CreatePasswordPageSteps fillBothPassword(String password){
        createPasswordPage.setFirstPasswordField(password);
        createPasswordPage.setSecondPasswordField(password);
        return this;
    }

    public CreatePasswordPageSteps clickOnSubmit(boolean click){
        createPasswordPage.clickOnSubmitButton(click);
        return this;
    }

    public CreatePasswordPageSteps insertPINFromSMS(String code){
        createPasswordPage.insertPhoneNumberPINCode(code);
        return this;
    }

    public CreatePasswordPageSteps cilckOnVerify(boolean click){
        if (click){
            createPasswordPage.verifyPhoneNumberSubmitButton(click);
        }
        return this;
    }

}
