package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Scanner;

public class DatabaseHandler {
    
    public DatabaseHandler(){

    }

    public void writeToDatabse(String element) throws FileNotFoundException, URISyntaxException{
        File file = new File(DatabaseHandler.class.getResource("database/database.txt").toURI());
        
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("\n" + "Yeet");
            bw.close();
        } catch (IOException e) {
            System.out.println("write to database ERROR");
            e.printStackTrace();
        }
    }

    public void readDatabase() throws URISyntaxException, FileNotFoundException{
        File file = new File(DatabaseHandler.class.getResource("database/database.txt").toURI());
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        DatabaseHandler x = new DatabaseHandler();
        try {
            x.writeToDatabse("test");
            x.readDatabase();
        } catch (FileNotFoundException | URISyntaxException e) {
            // TODO Auto-generated catch block
            System.out.println("ERR");
            e.printStackTrace();
        }

        System.out.println("Done");
    }

}
