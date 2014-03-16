package tpcrypto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File {

    public static String readFile(String nom) throws FileNotFoundException, IOException {
        BufferedReader buff = new BufferedReader(new FileReader(nom));
        String str = null;
        while ((str = buff.readLine()) != null) {
            return str;
        }
        return null;
    }

    public static void createFile(String fileName, String txt) {
        String filePath = System.getProperty("user.dir") + "/" + fileName;

        try {
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter output = new BufferedWriter(fw);
            output.write(txt);
            output.flush();
            output.close();
            System.out.println("File: "+fileName+" created");
        } catch (IOException ioe) {
            System.out.print("Error : ");
            ioe.printStackTrace();
        }

    }
}
