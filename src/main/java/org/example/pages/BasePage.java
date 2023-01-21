package org.example.pages;

import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    protected BasePage(){}
    public static void openRegisterPage(){
        open("https://transfermate.io/en/register.asp?");
    }
}
