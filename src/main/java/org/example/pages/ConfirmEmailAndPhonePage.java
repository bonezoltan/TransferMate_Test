package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class ConfirmEmailAndPhonePage extends BasePage{

    SelenideElement checkEmailLabel(){
        return $(By.cssSelector("#pages_content_252 > h2"));
    }

    public String getCheckEmailLabelText(){
        return checkEmailLabel().getText();
    }

    public String currentPageTitle(){
        return WebDriverRunner.getWebDriver().getTitle();
    }

}
