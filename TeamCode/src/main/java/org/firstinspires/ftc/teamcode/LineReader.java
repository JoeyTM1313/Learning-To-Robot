package org.firstinspires.ftc.teamcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LineReader {

    public static void main(String[] args) {
        ArrayList<String> keyframes = readFile("./test.txt");

        for(String line:keyframes) {
            System.out.println(line);
        }
    }

    public static ArrayList<String> readFile(String fileAddress) {
        String thisLine;
        BufferedReader textFile;
        ArrayList<String> keyframes = new ArrayList<String>();

        try {
            textFile = new BufferedReader(new InputStreamReader(new FileInputStream(fileAddress)));
            BufferedReader br = new BufferedReader(textFile);

            while ((thisLine = br.readLine()) != null) {
                keyframes.add(thisLine);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return keyframes;


    }
}
