package com.server.digital.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.server.digital.Constants;

public class Utils {
    public static Map<String, String[]> readCSVfile(final String path) throws IOException {
        Map<String, String[]> hash = new HashMap<String, String[]>();
        String line = "";
        BufferedReader buff = new BufferedReader(new FileReader(path));
        while ((line = buff.readLine()) != null) {
            String[] nssFmd = new String[2];
            String[] lineAux = line.split(Constants.CHAR_COMMA);
            nssFmd[0] = lineAux[0].replace("\"", Constants.CHAR_NULL); // NSS
            nssFmd[1] = lineAux[2].replace("\"", Constants.CHAR_NULL); // fmd
            hash.put(lineAux[1].replace("\"", Constants.CHAR_NULL), nssFmd);
        }
        buff.close();
        return hash;
    }

    public static void clearCSVfile(String path) throws IOException {
        BufferedWriter buffW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
        buffW.write(Constants.CHAR_NULL);
        buffW.newLine();
        buffW.close();
    }
}