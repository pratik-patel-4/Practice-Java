package org.techinfinities.practice.SmartSense.Interview2.coding.FileUpload;

public class S3FileUpload implements FileUpload {

    @Override
    public void upload(String s) {
        System.out.println("S3 .class File Upload :: " + s);
    }
}

class DropBoxFileUpload implements FileUpload {

    @Override
    public void upload(String s) {
        System.out.println(" DropBoxFileUpload .md File Upload :: " + s );
    }
}
