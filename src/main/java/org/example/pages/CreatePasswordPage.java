package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreatePasswordPage extends BasePage {


    public CreatePasswordPage(String link){
        open(link);
    }

    SelenideElement welcomeLabel() {
        return $(By.cssSelector("h1"));
    }

    SelenideElement firstPasswordField(){
        return $(By.id("password"));
    }

    SelenideElement secondPasswordField(){
        return $(By.id("confirm_password"));
    }

    SelenideElement submitButton(){
        return $(By.id("button_subscribe"));
    }
    
    public String getWelcomeLabel(){
        return welcomeLabel().text();
    }

    public void setFirstPasswordField(String text){
        firstPasswordField().sendKeys(text);
    }

    public void setSecondPasswordField(String text){
        secondPasswordField().sendKeys(text);
    }

    public void clickOnSubmitButton(boolean click){
        if(click) { submitButton().click(); }
    }


}
