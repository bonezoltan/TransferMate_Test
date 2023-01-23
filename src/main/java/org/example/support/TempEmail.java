package org.example.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TempEmail {
    String email;
    String hashedEmail;

    public TempEmail(){
        generateRandomEmail();
        hashEmail();
    }

    private void hashEmail() {
        try {
            setHashedEmail(email.getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private void generateRandomEmail(){
        String randomEmail = RandomStringUtils.randomAlphabetic(15).toLowerCase();
        randomEmail = randomEmail.concat("@cevipsa.com");
        this.email = randomEmail;
    }
    private void setHashedEmail(byte[] email) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        hashedEmail = convertToHex(md.digest(email));
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

    public EmailResponse call() throws IOException {

        String url = "api.apilayer.com";

        HttpUrl emailURL = new HttpUrl.Builder()
                .scheme("https")
                .addPathSegment("temp_mail")
                .addPathSegment("mail")
                .addPathSegment("id")
                .addPathSegment(this.hashedEmail)
                .host(url)
                .build();


        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(emailURL)
                .addHeader("apikey",System.getenv("TEMPMAIL_APIKEY"))
                .get()
                .build();


        Response response = okHttpClient.newCall(request).execute();

        String body = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(body);
        String mailtext = node.get(0).get("mail_text").asText();

        return new EmailResponse(mailtext);
    }
}