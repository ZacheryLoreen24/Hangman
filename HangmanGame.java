/* Zachery Loreen
CS 145 3/10/2023
Assignment 3: Alternative
This class in conjunction with the HangmanMain class
allows for the user to play the game Hangman. The
user will be asked if they are playing with 1 or 2 players
if the user says 1, then the words they are guessing will
be randomly selected from a file. If the user is playing with 2 players
then one player will be in charge of selecting the word, while the
other player must solve it. Once the user has guessed the word correctly, or
ran out of guesses, then the program will reveal the word, how many guesses it took, as-well
as the total number of wins/losses and the best game, finally the user will be asked
if they would like to play again.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HangmanGame {
    private int numGamesPlayed;
    private int numPlayers;
    private int numWins;
    private int numLosses;
    private int bestGame;
    private String word;

    /* Constructor for a single player game
    of Hangman, initializes all variables.     */
    public HangmanGame() throws IOException {
     this.numGamesPlayed = 0;
        this.numPlayers = 1;
        this.numWins = 0;
        this.numLosses = 0;
        this.bestGame = Integer.MAX_VALUE;
        this.word = getRandomWord();
} // end of HangmanGame constructor (1 player)

    /* Constructor for the double player
    game of Hangman, initializes all
    variables          */
    public HangmanGame(String word) {
        this.numGamesPlayed = 0;
        this.numPlayers = 2;
        this.numWins = 0;
        this.numLosses = 0;
        this.bestGame = Integer.MAX_VALUE;
        this.word = word;
    } // end of HangmanGame constructor (2 player)

    public void playGame() {
        Scanner scanner = new Scanner(System.in); // scanner in
        System.out.println("The word has " + word.length() + " letters." +
                " You have 6 guesses to guess the word."); // prints on amount of letters in
        // word for user
        char[] guesses = new char[word.length()]; // array of chars
        for (int i = 0; i < word.length(); i++) {
            guesses[i] = '_'; // sets each letter in the char array index to _
        } // end of for loop
        int numGuesses = 0; // number of guess = 0 so far
        boolean guessedWord = false; // word has not been guessed
        while (numGuesses < 6 && !guessedWord) { // while number of guesses is less than 6
            // and while the word hasn't been guessed continue to loop
            System.out.print("Guess a letter: ");
            char guess = scanner.next().charAt(0); // guess
            boolean correctGuess = false; // correct guess for individual letter
            for (int i = 0; i < word.length(); i++) { // checks if guess matches any letter
                // in word
                if (word.charAt(i) == guess) {
                    guesses[i] = guess;
                    correctGuess = true;
                } // end of if statement
            } // end of for loop
            if (!correctGuess) { // if guess isn't correct
                numGuesses++; // up the number of guesses
                System.out.println("Incorrect guess. You have " + (6 - numGuesses) +
                        " guesses left.");
            }
            System.out.println(guesses);
            if (String.valueOf(guesses).equals(word)) { // if the guesses array matches the word
                guessedWord = true; // set guessed word to true
                System.out.println("Congratulations! You guessed the word!"); // inform user of
                // correct guess
                numWins++; // up the number of wins
                int guessesUsed = numGuesses; // set guessesUsed to number of guessed tries for
                // the round
                if (guessesUsed < bestGame) { // if the guessed used is less than bestGame
                    bestGame = guessesUsed; // set bestGame to guessedUsed
                } // end of if statement
            } // end of if statement
        } // end of while loop
        if (!guessedWord) { // if you didn't guess the word
            System.out.println("Sorry, you didn't guess the word. The word was " + word + ".");
            numLosses++; // increment loss counter
            int guessesUsed = numGuesses;
            if (guessesUsed == 6 && numGamesPlayed == 0) { // if you used 6 guesses
            bestGame = 6;  // set best game to 6 (technically the worst score)
            } // end of if statement
        } // end of if statement
        numGamesPlayed++; // increment number of games played
        System.out.println("Games Played: " +
                numGamesPlayed); // inform user of number of games played
        System.out.println("Games won: " +
                numWins); // inform user of number of wins
        System.out.println("Games lost: " +
                numLosses); // inform user number of losses
        System.out.println("Your best game had " +
                bestGame + " incorrect guesses."); // inform user of best game
    }

    // getNumPlayers method is used to differentiate between 1 player and 2 player games
    public int getNumPlayers() {
        return this.numPlayers;
    } // end of getNumPlayers method

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
        int index = random.nextInt(words.length); // generates random int, in between index 0
        // and max lengt of the words array
        return words[index]; // returns words array with random index generated
    } // end of method

    // sets the word to be guessed to (randomized) or (player inputted)
    // depending on if a 1 player or 2 player game is being played.
    public void setWord(String word) {
        this.word = word;
    } // end of setWord method

} // end of HangmanGame class