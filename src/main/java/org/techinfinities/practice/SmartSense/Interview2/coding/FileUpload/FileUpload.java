package org.techinfinities.practice.SmartSense.Interview2.coding.FileUpload;

public interface FileUpload {
    /**
     * File Upload
     * You have a list of files
     * You need to upload the file different files in different types of storage:
     *
     * .java -> google drive
     * .class -> S3
     * .md -> DropBox
     * .* -> Azure Blob
     *
     * Provide class structure for the solution.
     * You don't need to write the actual implementation of uploading the file in storage.
     */

    // a = {1.java,2,3,4,5,6}

    public void upload(String s);

}
