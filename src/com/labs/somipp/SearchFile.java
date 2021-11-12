package com.labs.somipp;

import java.io.File;
import java.util.Objects;

public class SearchFile {

    /**
     * Function to search for a file in a given directory.
     * @param file instantiates a file object when the user searches for a file.
     *             Null if the file is not found. It can be a folder or a simple file.
     * @param file_to_search represents the name of the searched file.
     */
    public void searchFile(File file, String file_to_search) {
        try {
            if (file_to_search != null && !file_to_search.isEmpty()) {
                if (file != null) {
                    if (file.isDirectory()) {
                        /**do you have permission to read this directory?*/
                        if (file.canRead()) {
                            for (File sub_directory : Objects.requireNonNull(file.listFiles())) {
                                if (sub_directory.isDirectory()) {
                                    searchFile(sub_directory, file_to_search);
                                } else {
                                    if (file_to_search.equalsIgnoreCase(sub_directory.getName().toLowerCase())) {
                                        System.out.println("File Found @ : " + sub_directory.getAbsoluteFile().toString());
                                    }
                                }
                            }
                        } else {
                            System.out.println(file.getAbsoluteFile() + " Permission Denied");
                        }
                    } else {
                        System.out.println(file.getAbsoluteFile() + " is not a directory!");
                    }
                } else {
                    file = new File("/");
                    searchFile(file, file_to_search);
                }
            } else {
                System.out.println("The file given to search ");
            }

        } catch (Exception e) {
            System.out.println("Not yet");
        }
    }
}
