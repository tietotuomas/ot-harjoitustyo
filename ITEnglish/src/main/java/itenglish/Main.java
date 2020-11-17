/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aaltonet
 */
public class Main {
    public static void main(String[] args) {

        HashMap<String, String> beginner = new HashMap<>();
        HashMap<String, String> average = new HashMap<>();
        HashMap<String, String> master = new HashMap<>();
        HashMap[] content = {beginner, average, master};
        String[] filenames = {"beginner.txt", "average.txt", "master.txt"};
        for (int i = 0; i < filenames.length; i++) {
            String contentFile = filenames[i];
            try (Scanner fileReader = new Scanner(new File(contentFile), "UTF-8")) {

                while (fileReader.hasNextLine()) {
                    String row = fileReader.nextLine();

                    if (row.trim().length() == 0) {
                        continue;
                    }

                    String[] parts = row.split(",");

                    String english = parts[0].trim().toLowerCase();
                    String finnish = parts[1].trim().toLowerCase();
                    content[i].put(english, finnish);

                }
            } catch (Exception e) {
                System.out.println("Virhe: " + e.getMessage());
            }

        }
        Scanner commandReader = new Scanner(System.in, "UTF-8");
        System.out.println("Moi, tervetuloa oppimaan IT-englantia!");
        int howManyQuestions = 5;

        while (true) {
            int correctAnswers = 0;
            System.out.print("Valitse vaikeustaso tai poistu sovelluksesta. Komennot: ");
            System.out.println("\"1\" - helppo, \"2\" - normaali, \"3\" - vaikea, \"quit\" - lopeta");
            String komento = commandReader.nextLine();
            if (komento.equals("1")) {
                System.out.println("Helppo vaikeustaso valittu. Käännä seuraavat sanat englanniksi:");
                correctAnswers = englishTest(beginner, commandReader, howManyQuestions);
                System.out.println("Sait " + correctAnswers + "/" + howManyQuestions + " oikein!");
                continue;
            }
            else if (komento.equals("2")) {
                System.out.println("Normaali vaikeustaso valittu. Käännä seuraavat sanat englanniksi:");
                correctAnswers = englishTest(average, commandReader, howManyQuestions);
                System.out.println("Sait " + correctAnswers + "/" + howManyQuestions + " oikein!");
                continue;
            }
            else if (komento.equals("3")) {
                System.out.println("Vaikea vaikeustaso valittu. Käännä seuraavat sanat englanniksi:");
                correctAnswers = englishTest(average, commandReader, howManyQuestions);
                System.out.println("Sait " + correctAnswers + "/" + howManyQuestions + " oikein!");
                
            }
            else if (komento.equals("quit")) {
                System.out.println("Kiitos ja tervetuloa opiskelemaan uudelleen!");
                break;
            } else {
                System.out.println("Et antanut kelvollista komentoa.");
            }

        }
    }

    public static int englishTest(HashMap difficulty, Scanner commandReader, int howManyQuestions) {
        int correctAnswers = 0;

        List<String> keys = new ArrayList(difficulty.keySet());
        Collections.shuffle(keys);
        for (String english : keys) {
            if (howManyQuestions == 0) {
                break;
            }
            System.out.println(difficulty.get(english));
            String answer = commandReader.nextLine();
            if (answer.trim().toLowerCase().equals(english)) {
                correctAnswers++;
            }
            howManyQuestions--;
        }
        return correctAnswers;

    }
}

