package org.example;

import org.example.pages.RegisterPage;
import org.testng.annotations.Test;


public class AppTest 
{

    @Test
    public void OpenRegisterPage(){
        RegisterPage rp = new RegisterPage();

        rp.openRegisterPage();
    }
}
