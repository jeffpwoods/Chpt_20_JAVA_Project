/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeffp
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RandomCapitolsQuiz {
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        
        File capitolFile = 
                new File("C:\\Users\\jeffp\\OneDrive\\Documents\\Jeff School\\Spring_2022\\INFO_211\\Chpt20\\Chpt20\\capitols.txt");
        File stateFile = new File("C:\\Users\\jeffp\\OneDrive\\Documents\\Jeff School\\Spring_2022\\INFO_211\\Chpt20\\Chpt20\\states.txt");
        
        List<String[]> list = 
                Arrays.asList(capitols(capitolFile, stateFile));
        
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i)[0].contains("-")) {
                list.get(i)[0] = list.get(i)[0].replace('-', ' ');
            }
            if (list.get(i)[1].contains("-")) {
                list.get(i)[1] = list.get(i)[1].replace('-', ' ');
            }  
        }
        
        String question = list.get((int) (Math.random() * list.size()))[1];
        System.out.printf("What is the capitol of %s"
                + " (enter 'q' to quit any time): ", question);
        
        String correctAnswer = "";
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[1].equals(question)) {
                correctAnswer = list.get(i)[0];
            }
        }
        
        String answer = in.nextLine();

        int correct = 0;
        
        try {
            if (answer.toLowerCase().charAt(0) == 'q') {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
            else if (answer.length() <= 4) {
                throw new IllegalArgumentException("Enter a state capitol");
            }
            else if (answer.toLowerCase().equals(correctAnswer)) {
                correct++;
            }
            else {
                System.out.println("The correct answer is: " + correctAnswer);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Enter the capitol of " + question);
            answer = in.nextLine();
            
            if (answer.toLowerCase().equals(correctAnswer.toLowerCase())) {
                correct++;
            }
            else {
                System.out.println("The correct answer is: " + correctAnswer);
            }
        }
        
        while (answer.toLowerCase().charAt(0) != 'q') {

            try {
                question = list.get((int) (Math.random() * list.size()))[1];
                System.out.printf("What is the capitol of %s: ", question);
                answer = in.nextLine();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i)[1].equals(question)) {
                        correctAnswer = list.get(i)[0];
                    }
                }
                if (answer.length() > 1 && answer.length() <= 4) {
                    throw new IllegalArgumentException("Enter a state capitol");
                }
                else if 
                   (answer.toLowerCase().equals(correctAnswer.toLowerCase())) {
                    correct++;
                } 
                else if 
                   (!answer.toLowerCase().equals(correctAnswer.toLowerCase())) {
                    System.out.println("The correct answer is: " + correctAnswer);
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("Enter the capitol of " + question);
                answer = in.nextLine();

                if (answer.toLowerCase().equals(correctAnswer.toLowerCase())) {
                    correct++;
                } else {
                    System.out.println("The correct answer is: " + correctAnswer);
                }
            }

        }
        
        System.out.print("Thanks for playing! ");
        System.out.println("You got " + correct + " correct");
        
            
        }
  
    
    
    public static String[][] capitols(File capitolFile, File stateFile)
            throws IOException {

        String[][] list = new String[50][2];

        if (stateFile.exists() && capitolFile.exists()) {

            Scanner capitolList = new Scanner(new FileInputStream(capitolFile));
            Scanner stateList = new Scanner(new FileInputStream(stateFile));
            int index = 0;
            while (stateList.hasNext() && capitolList.hasNext()) {
                String state = stateList.next();
                String capitol = capitolList.next();
                if (capitol.trim().length() > 0) {
                    list[index][0] = capitol;
                }
                else {
                    list[index][0] = "NA";
                }
                if (state.trim().length() > 0) { 
                    list[index][1] = state;
                }
                else {
                    list[index][1] = "NA";
                }
                
                index++;
            }
        } else {
            throw new FileNotFoundException("One of the files does not exist");
        }
        return list;
    }
    
    
}
    

    
