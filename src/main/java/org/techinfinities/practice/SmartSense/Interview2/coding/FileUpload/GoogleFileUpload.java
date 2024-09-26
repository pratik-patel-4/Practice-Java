package org.techinfinities.practice.SmartSense.Interview2.coding.FileUpload;

public class GoogleFileUpload implements  FileUpload{
    @Override
    public void upload(String s) {
        System.out.println("Uploading .java in Google : " + s);
    }
}
