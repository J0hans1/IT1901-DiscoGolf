package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseHandler {
    
    private List<String[]> databaseList;

    public void writeToDatabse(String name, String score, String frisbeeCourse) throws IOException{
        try {
            String path = getPath(); //file path
            File file = new File(path); 
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("\n" + name + "," + score + "," + frisbeeCourse); //Write a line consisting of "name,score,frisbeeCourse" to the .txt file
            bw.close();
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR saving to database");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public void readDatabase() throws URISyntaxException, FileNotFoundException{
        String path = getPath();
        
        File file = new File(path);
        
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(file)
            );
            List<String[]> data = reader
            .lines()
            .parallel()
            .map(scorecard -> scorecard.split(","))
            .toList();

            this.databaseList = data;
            reader.close();
        } catch (IOException e) {
            System.out.println("Error in reading the database");
            e.printStackTrace();
        }
        
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
    }

    public List<String[]> getDatabase(){
        return this.databaseList;
    }
    
    private String getPath() throws URISyntaxException{
        String path = new File(getClass().getResource("").toURI())
        .getAbsolutePath()
        .split("target")[0]; //Obtain a absolute path to the "discoGolf" folder in a way that will work for every user
        path = path + "src/main/data/database.txt"; //Add the final part of the path
        return path;
    }
}
