package org.techinfinities.practice.SmartSense.Interview2.coding.FileUpload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        fileUpload();
    }

    public static void fileUpload() {

        
        FileUpload fileUpload;

        List<String> list = new ArrayList<>(
                Arrays.asList("test.test.java", "example.java", "example.class" , "test.md", "interview.pdf", "test.doc")
        );

        for (String s : list) {
            String[] str = s.split("\\.");
            String fileType = str[str.length - 1];

            if(fileType.equalsIgnoreCase("Java")) {
                fileUpload = new GoogleFileUpload();
                fileUpload.upload(s);
                //google
            } else if(fileType.equalsIgnoreCase("class")) {
                fileUpload = new S3FileUpload();
                fileUpload.upload(s);
            } else if(fileType.equalsIgnoreCase("md")) {
                //dropbox
                fileUpload = new DropBoxFileUpload();
                fileUpload.upload(s);
            } else {
                //azure
                fileUpload = new AzureFileUpload();
                fileUpload.upload(s);
            }
        }
    }
}
