package com.hotel.reservation.util;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHandler {

    // FIX 3: Resolve "data/" relative to the project root (where the JAR / mvn run is launched),
    // not wherever the JVM happens to set its working directory.
    // System.getProperty("user.dir") always returns the process launch directory,
    // which for both "mvn spring-boot:run" and a packaged JAR is the project root.
    private static String resolvePath(String fileName) {
        return Paths.get(System.getProperty("user.dir"), fileName).toString();
    }

    // Write data to file (append)
    public static void writeToFile(String fileName, String data) {
        String fullPath = resolvePath(fileName);
        try {
            // Ensure parent directories exist
            new File(fullPath).getParentFile().mkdirs();

            FileWriter fw = new FileWriter(fullPath, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(data);
            bw.newLine();

            bw.close();
            fw.close();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + fullPath + " — " + e.getMessage());
        }
    }

    // Read all lines from file
    public static ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        String fullPath = resolvePath(fileName);

        File file = new File(fullPath);
        if (!file.exists()) {
            // File not created yet — return empty list instead of crashing
            return list;
        }

        try {
            FileReader fr = new FileReader(fullPath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + fullPath + " — " + e.getMessage());
        }

        return list;
    }

    // Overwrite file with new list of lines
    public static void overwriteFile(String fileName, ArrayList<String> data) {
        String fullPath = resolvePath(fileName);
        try {
            new File(fullPath).getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath));

            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error overwriting file: " + fullPath + " — " + e.getMessage());
        }
    }
}