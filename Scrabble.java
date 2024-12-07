import java.util.Random;

public class Scrabble {

    // Dictionary file for this Scrabble game
    static final String WORDS_FILE = "dictionary.txt";

    // The "Scrabble value" of each letter in the English alphabet.
    static final int[] SCRABBLE_LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

    // Number of random letters dealt at each round of this Scrabble game
    static int HAND_SIZE = 10;

    // Maximum number of possible words in this Scrabble game
    static int MAX_NUMBER_OF_WORDS = 100000;

    // The dictionary array (will contain the words from the dictionary file)
    static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];

    // Actual number of words in the dictionary (set by the init function, below)
    static int NUM_OF_WORDS;

    // Populates the DICTIONARY array with the lowercase version of all the words read
    public static void init() {
        In in = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
        while (!in.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
        }
        System.out.println(NUM_OF_WORDS + " words loaded.");
    }

    // Checks if the given word is in the dictionary.
    private static boolean isWordInDictionary(String word) {
        for (int i = 0; i < DICTIONARY.length; i++) {
            if (word.equals(DICTIONARY[i])) {
                return true;
            }
        }
        return false;
    }


    public static int wordScore(String word) {
        String runi = "runi";
        int score = 0;


        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int letterIndex = ch - 'a';
            score += SCRABBLE_LETTER_VALUES[letterIndex];
        }

        if (word.length() == HAND_SIZE) {
            score += 50;
        }

     
        if (MyString.subsetOf(word, runi)) {
            score += 1000;
        }

        return score;
    }

    public static String createHand() {
        Random rand = new Random();

       
        String randomLetters = MyString.randomStringOfLetters(HAND_SIZE - 2);

        int insertIndexA = rand.nextInt(randomLetters.length() + 1);
        int insertIndexE = rand.nextInt(randomLetters.length() + 1);

      
        while (insertIndexA == insertIndexE) {
            insertIndexE = rand.nextInt(randomLetters.length() + 1);
        }

 
        StringBuilder hand = new StringBuilder(randomLetters);
        hand.insert(insertIndexA, 'a');
        hand.insert(insertIndexE, 'e');

        
        return hand.toString();
    }

  
    public static void playHand(String hand) {
        int score = 0;
        In in = new In();

        while (hand.length() > 0) {
            System.out.println("Current Hand: " + MyString.spacedString(hand));
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String input = in.readString();

            if (input.equals(".")) {
                break;
            }

       
            if (isWordInDictionary(input)) {
                int wordScore = wordScore(input);
                score += wordScore;

              
                for (int i = 0; i < input.length(); i++) {
                    hand = hand.replaceFirst(String.valueOf(input.charAt(i)), "");
                }

                System.out.println("You earned " + wordScore + " points for the word '" + input + "'.");
            } else {
                System.out.println("Invalid word. Try again.");
            }
        }

        System.out.println("End of hand. Total score: " + score + " points");
    }

    
    public static void playGame() {
        init();
        In in = new In();

        while (true) {
            System.out.println("Enter n to deal a new hand, or e to end the game:");
            String input = in.readString();

            if (input.equals("n")) {
                String hand = createHand();
                playHand(hand);
            } else if (input.equals("e")) {
                System.out.println("Thanks for playing!");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'n' or 'e'.");
            }
        }
    }

    public static void main(String[] args) {
        playGame();
    }

    // Test functions
    public static void testBuildingTheDictionary() {
        init();
        for (int i = 0; i < 5; i++) {
            System.out.println(DICTIONARY[i]);
        }
        System.out.println(isWordInDictionary("mango"));
    }

    public static void testScrabbleScore() {
        System.out.println(wordScore("bee"));
        System.out.println(wordScore("babe"));
        System.out.println(wordScore("friendship"));
        System.out.println(wordScore("running"));
    }

    public static void testCreateHands() {
        System.out.println(createHand());
        System.out.println(createHand());
        System.out.println(createHand());
    }

    public static void testPlayHands() {
        init();
    
    }
}

