/* Zachery Loreen
CS 145 3/10/2023
Assignment 3: Alternative project
The HangmanMain class works in conjunction with
the HangmanGame class to play a game of hangman.
This class introduces the game to the user, asks who will be playing
and then creates a HangmanGame object depending on the answer.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.*;
public class HangmanMain {

/* here is the main class which runs the program,
it introduces the game to the user,
the asks how many players are playing. Creating a new HangmanGame
 object depending on the answer */
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Hangman!");
        System.out.println("The objective of the game is to guess\na hidden " +
                "word by entering one letter at \na time. If you get 6 of your " +
                "guesses wrong\nbefore spelling the word, you lose.");


        Scanner scanner = new Scanner(System.in);
        String answer = "yes"; // sets answer to "yes" for while loop to run
        HangmanGame game = null; // sets game to null to first ask how many players are playing

        while (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
            if (game == null) { // if game = null then ask how many players are playing
                System.out.println("How many players are playing? (1/2)");
                int numPlayers = scanner.nextInt();
                scanner.nextLine();
                if (numPlayers == 1) { // if numPlayers is = 1 run HangmanGame instance with
                    // randomized word
                    game = new HangmanGame(); // creates new HangmanGame object

                } else if (numPlayers == 2) { // if numPLayers is = 2 run HangmanGame instance with
                    // word selected by user
                    System.out.println("Player 1, please enter a word for Player 2 to guess:");
                    String word = scanner.nextLine(); // input word
                    game = new HangmanGame(word); // creates new HangmanGame(word) object
                } else {
                    System.out.println("Invalid number of players. Please try again.");
                    continue;
                }
            } else { // else if game is not null.
             if (game.getNumPlayers() == 1) { // and if numPlayers == 1
                            game.setWord(getRandomWord()); // then randomize word
                        } else { // else if num players == 2, have user input next word
                            System.out.println("Player 1, please enter a new word for Player 2 to guess:");
                            String word = scanner.nextLine(); // scanner
                            game.setWord(word);
                        } // end of if else
            } // end of if else
            game.playGame();
            System.out.println("Would you like to play again? (yes/no)");
            answer = scanner.nextLine(); // if answer is no exits while loop

        } // end of while loop
        System.out.println("Thanks for playing Hangman!");
        scanner.close();
    } // end of main method

     /* The getRandomWord method takes a list of words from a text file
        splits them up adding individual words into a String array.*/
     private static String getRandomWord() throws IOException {
         String filename = "hangtest.txt"; // sets file name to name of file used to select wrods
         String[] words = null; // empty string array created

         BufferedReader br = new BufferedReader(new FileReader(filename)); // reads file
         String line;
         while ((line = br.readLine()) != null) { // reads file lines unitl line is empty
             words = line.split(","); // splits words on same line apart by commas and stores
             // them into String[] words array.
         } // end of while loop
         br.close(); // closes object

         Random random = new Random(); // creates random object
         int index = random.nextInt(words.length); // generates random int, in between index 0 and max length
         // of the words array
         return words[index]; // returns words array with random index generated
     } // end of method

} // end of class