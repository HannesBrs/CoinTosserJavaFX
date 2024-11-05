package com.example.cointosserjavafx;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class if for iterating through the stylesheets
 * from the resource directory
 * @author Hannes
 */
public class ThemeChanger {

    private static List<String> stylesheetList;
    private static final String CSS_REGEX = "^.+\\.css$";
    static {
        stylesheetList = new ArrayList<>();
        fillStylesheetList();
    }

    /**
     * Private constructor to prevent instantiation of the ThemeChanger class.
     */
    private ThemeChanger() {

    }

    /**
     * This method fills the stylesheet list with all
     * stylesheets from the resource directory
     */
    private static void fillStylesheetList() {
        try {
            URL resourceFolder = ThemeChanger.class.getResource("");
            File dir = new File(resourceFolder.getFile());
            File[] fileNameArray = dir.listFiles();
            for (int i = 0; i < fileNameArray.length; i++) {
                String fileName = fileNameArray[i].getName();
                if (fileName.matches(CSS_REGEX)) {
                    stylesheetList.add(fileNameArray[i].getName());
                    System.out.println(fileNameArray[i].getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the next stylesheet
     * @return the next stylesheet
     */
    public static String getNextStylesheet() {
        return circularIterator.next();
    }

    /**
     * This inner class provides a circularIterator which
     * iterates through all stylesheets
     */
    private static Iterator<String> circularIterator = new Iterator<>() {
        private static int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (stylesheetList.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public String next() {
            String nextString = stylesheetList.get(currentIndex);
            currentIndex = ((currentIndex + 1) % stylesheetList.size());
            return nextString;
        }
    };
}
