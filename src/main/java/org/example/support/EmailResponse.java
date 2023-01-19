package org.example.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailResponse {

    String message;

    public EmailResponse(String message){
        this.message = message;
    }

    public String getActivationLink(){

        Pattern pattern = Pattern.compile("\\[(.*E-Mail-Activation)]");
        Matcher matcher = pattern.matcher(message);

        String link = "";
        if (matcher.find())
        {
            link = matcher.group(1);
        }
        return link;
    }
}
