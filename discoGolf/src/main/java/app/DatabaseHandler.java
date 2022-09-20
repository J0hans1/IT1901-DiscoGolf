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

    public void writeToDatabse(String name, String score, String frisbeeCourse){
        try {
            this.write(name, score, frisbeeCourse);
            this.readDatabase();
        } catch (FileNotFoundException | URISyntaxException e) {
            System.out.println("ERROR saving to database");
            e.printStackTrace();
        }
    }

    private void write(String name, String score, String frisbeeCourse) throws FileNotFoundException, URISyntaxException{
        File file = new File(DatabaseHandler.class.getResource("database/database.txt").toURI());
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("\n" + name + " " + score + " " + frisbeeCourse);
            bw.close();
            fw.close();
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
        x.writeToDatabse("Ulrik", "25", "Lade");

        System.out.println("Done");
    }

}
