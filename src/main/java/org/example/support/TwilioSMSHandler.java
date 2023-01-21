package org.example.support;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwilioSMSHandler {

    public static final String ACCOUNT_SID = System.getenv("TWILIOACCOUNTSID");
    public static final String AUTH_TOKEN = System.getenv("TWILIOACCOUNTTOKEN");
    private String body;
    public void getLastMessage(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Message> messages = Message.reader().limit(5000).read();

        this.body = messages.iterator().next().getBody();
    }

    public String getPINCodeFromSMS() {

        getLastMessage();

        Pattern pattern = Pattern.compile("\\b\\d{4}\\b");
        Matcher matcher = pattern.matcher(this.body);

        String pinCode = "";
        if (matcher.find())
        {
            pinCode = matcher.group(0);
        }
        return pinCode;

    }


}
