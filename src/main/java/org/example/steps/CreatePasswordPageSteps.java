package org.example.steps;

import org.example.pages.CreatePasswordPage;
import org.example.support.EmailResponse;

public class CreatePasswordPageSteps {

    CreatePasswordPage createPasswordPage;

    public CreatePasswordPageSteps(String link){
        createPasswordPage = new CreatePasswordPage(link);
    }
}
