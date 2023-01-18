package org.example.pages;

import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    public BasePage(){
        openRegisterPage();
    }

    public void  openRegisterPage(){
        open("https://transfermate.io/en/register.asp?");
    }
}
