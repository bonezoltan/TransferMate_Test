package org.example;

import org.example.pages.RegisterPage;
import org.example.steps.RegisterPageSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.script.ScriptException;


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
    public void fillInWithData(){
        registerPageSteps
                .setAccountType("partnership")
                .insertTextToFirstName("Test")
                .insertTextToFirstLastName("Tester")
                .insertTextToEmail("Hey@Oh.ho")
                .selectCountry("Iceland")
                .insertTextToPhone("98312039123")
                .selectDropDownPhonePrefix("Hungary")
                .selectMarketingCheckBox(true)
                .selectTermsOfUseCheckBox(true)
                .insertSolvedCaptcha();
    }


}
