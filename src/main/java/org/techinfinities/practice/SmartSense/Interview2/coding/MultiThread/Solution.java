package org.techinfinities.practice.SmartSense.Interview2.coding.MultiThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        List<String> emails = new ArrayList<>(
                Arrays.asList("")
        );
    }
}
//List<String> emails =.  we have 100s of email

//Find unique domains from a list of emails

class EmailProcessor extends Thread {

    private String email;

    EmailProcessor(String email) {
        this.email = email;
    }

    private String getDomain(String email){
        //get a domain from email and return, assume this process takes 2 sec
        //maybe we are calling some APIs to get the domain out from the email
        return "domain";
    }

    public List<String> getDomain(List<String> email) {
        for(String s : email) {
            EmailProcessor emailProcessor = new EmailProcessor(s);
            Thread t1 = new Thread(emailProcessor);
            t1.start();
        }
        return null;
    }

    public void start() {
        this.getDomain(email);
    }
}
