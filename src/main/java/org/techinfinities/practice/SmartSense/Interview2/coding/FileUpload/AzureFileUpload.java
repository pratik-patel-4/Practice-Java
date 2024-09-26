package org.techinfinities.practice.SmartSense.Interview2.coding.FileUpload;

public class AzureFileUpload implements FileUpload {

    @Override
    public void upload(String s) {
        System.out.println("All other files goes AZURE : " + s);
    }
}
