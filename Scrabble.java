public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";
    static final int[] SCRABBLE_LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
    static int HAND_SIZE = 10;
    static int MAX_NUMBER_OF_WORDS = 100000;
    static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];
    static int NUM_OF_WORDS;

    public static void init() {
        In in = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
        while (!in.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
        }
        System.out.println(NUM_OF_WORDS + " words loaded.");
    }

    public static boolean isWordInDictionary(String word) {
        for (int i = 0; i < DICTIONARY.length; i++) {
            if (DICTIONARY[i] != null && DICTIONARY[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static int wordScore(String word) {
        int score = 0;
        int letterScore = 0;
        for (int i = 0; i < word.length(); i++) {
            letterScore += SCRABBLE_LETTER_VALUES[word.charAt(i) - 'a'];
        }
        score += letterScore * word.length();
        if (HAND_SIZE == word.length()) score += 50;
        if (word.indexOf('r') >= 0 && word.indexOf('u') >= 0 && word.indexOf('n') >= 0 && word.indexOf('i') >= 0) score += 1000;
        return score;
    }

    public static String createHand() {
        String hand = MyString.randomStringOfLetters(HAND_SIZE - 2);
        hand = MyString.insertRandomly('a', hand);
        hand = MyString.insertRandomly('e', hand);
        return hand;
    }

    public static void playHand(String hand) {
		int score = 0;
		In in = new In();
		while (hand.length() > 0) {
			System.out.println("Current Hand: " + MyString.spacedString(hand));
			System.out.println("Enter a word, or '.' to finish playing this hand:");
			String input = in.readString().toLowerCase();
	
			if (input.equals(".")) break;
	
			if (MyString.subsetOf(input, hand)) {
				if (isWordInDictionary(input) && !input.isEmpty()) {
					hand = MyString.remove(hand, input);
					int wordScore = wordScore(input);
					score += wordScore;
					System.out.println(input + " earned " + wordScore + " points. Score: " + score + " points\n");
	
					// Break out after a valid word
					if (hand.length() == 0) {
						System.out.println("Ran out of letters. Total score: " + score + " points");
						break;
					}
				} else {
					System.out.println("No such word in the dictionary. Try again.\n");
				}
			} else {
				System.out.println("Invalid word. Try again.\n");
			}
		}
	
		if (hand.length() != 0) {
			System.out.println("End of hand. Total score: " + score + " points");
		}
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
        //playHand("ocostrza");
        //playHand("arbffip");
        //playHand("aretiin");
    }
}
