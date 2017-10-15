package com.esuvorov.utils;

import org.apache.log4j.Logger;

import java.io.File;

public class FileManager {
    private static final Logger LOGGER = Logger.getLogger(FileManager.class);

    public FileManager() {
        throw new IllegalStateException();
    }

    public static File[] readFilesInFolder(String jsonDataConfigFolder) {
        File folder = new File(jsonDataConfigFolder);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            LOGGER.error("No files found in " + jsonDataConfigFolder);
            throw new IllegalArgumentException();
        }
        return listOfFiles;
    }
}
