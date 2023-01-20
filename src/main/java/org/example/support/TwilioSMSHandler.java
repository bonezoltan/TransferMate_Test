package org.example.support;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwilioSMSHandler {

    public static final String ACCOUNT_SID ="AC68ea9383136eaf1953e65e60d2d98bbb";
    public static final String AUTH_TOKEN = "5ff3a05cdd287c18cf6a66087f770376";


    public String getPINCodeFromSMS() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.fetcher("SMae06c8619543bf2893e3d46e9af73d8a")
                .fetch();

        System.out.println(message.getBody());
        String body = message.getBody();

        Pattern pattern = Pattern.compile("\\b\\d{4}\\b");
        Matcher matcher = pattern.matcher(body);

        String pinCode = "";
        if (matcher.find())
        {
            pinCode = matcher.group(1);
        }
        return pinCode;

    }


}
