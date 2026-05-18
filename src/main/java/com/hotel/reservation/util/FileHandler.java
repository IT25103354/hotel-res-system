package com.hotel.reservation.util;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    // Write data to file
    public static void writeToFile(String fileName, String data) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(data);
            bw.newLine();

            bw.close();
            fw.close();

        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    // Read data from file
    public static ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> list = new ArrayList<>();

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return list;
    }

    public static void overwriteFile(String fileName, ArrayList<String> data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error overwriting file");
        }
    }
}