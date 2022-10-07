package ui;

import java.util.ArrayList;
import java.util.Arrays;

import discoGolf.core.Course;
import fxutil.Listener;

public class AppTest {
    public static void main(String[] args) {
        Listener lis = new Listener();
        lis.printWord();

        Course cor = new Course("Lade", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
    }
}
