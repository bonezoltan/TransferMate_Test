package org.example.support;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class TempEmail {

    String email;
    String hashedEmail;

    EmailResponse message;
    public TempEmail(){
        generateRandomEmail();
        hashEmail();
    }

    private void hashEmail() {
        try {
            setHashedEmail(email.getBytes());
        } catch (Exception e){}

    }
    private void generateRandomEmail(){
        String randomEmail = RandomStringUtils.randomAlphabetic(15).toLowerCase();
        randomEmail = randomEmail.concat("@cevipsa.com");
        this.email = randomEmail;
    }
    private void setHashedEmail(byte[] email) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        hashedEmail =convertToHex(md.digest(email));
    }

    public String getHashedEmail(){
        return hashedEmail;
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }

    public String getEmail(){
        return email;
    }

    public EmailResponse call() {


        //Wait for Temporary Mail API Response
            try {
                Thread.sleep(5000);
            }catch (Exception e){}
             Response response =
                    RestAssured.given().queryParam("apikey", Constans.API_KEY)
                            .when().get("https://api.apilayer.com/temp_mail/mail/id/" + this.hashedEmail);

            String mail_text = response.jsonPath().getString("mail_text");

        return new EmailResponse(mail_text);
    }

}
