package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoggedInAndVerifiedPage extends BasePage{

    SelenideElement processingElement(){
        return $(By.id("pages_content_49471"));
    }


    public boolean isProcessingElementVisible(){
        return processingElement().is(Condition.visible);
    }

}
