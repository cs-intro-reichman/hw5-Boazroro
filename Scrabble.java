public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";
    static final int[] SCRABBLE_LETTER_VALUES = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    static int HAND_SIZE = 10;
    static int MAX_NUMBER_OF_WORDS = 100000;
    static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];
    static int NUM_OF_WORDS;

    public static void init() {
        In fileReader = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
        while (!fileReader.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = fileReader.readString().toLowerCase();
        }
        System.out.println(NUM_OF_WORDS + " words loaded.");
    }

    public static boolean isWordInDictionary(String word) {
        for (int i = 0; i < NUM_OF_WORDS; i++) {
            if (DICTIONARY[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static int wordScore(String word) {
        int score = 0;
        int totalScore = 0;
        for (int i = 0; i < word.length(); i++) {
            int letterIndex = word.charAt(i) - 'a';
            totalScore += SCRABBLE_LETTER_VALUES[letterIndex];
        }
        score = totalScore * word.length();

        if (HAND_SIZE == word.length()) {
            score += 50;
        }

        if (MyString.countChar(word, 'r') > 0 && MyString.countChar(word, 'u') > 0 &&
            MyString.countChar(word, 'n') > 0 && MyString.countChar(word, 'i') > 0) {
            score += 1000;
        }
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
		In userInputReader = new In();
		while (hand.length() > 0) {
			System.out.println("Current Hand: " + MyString.spacedString(hand));
			System.out.println("Enter a word, or '.' to finish playing this hand:"); // updated prompt
			String userInput = userInputReader.readString();
	
			if (userInput.equals(".")) {
				break;
			}
	
			if (MyString.subsetOf(userInput, hand)) {
				if (isWordInDictionary(userInput)) {
					hand = MyString.remove(hand, userInput);
					score += wordScore(userInput);
					System.out.println(userInput + " earned " + wordScore(userInput) + " points. Total score: " + score);
					System.out.println();
				} else {
					System.out.println("Word not found in dictionary. Try again.");
					System.out.println();
				}
			} else {
				System.out.println("Invalid word. Try again.");
			}
		}
		if (hand.length() == 0) {
			System.out.println("Out of letters. Total score: " + score + " points");
		} else {
			System.out.println("End of hand. Total score: " + score + " points");
		}
	}
	
	

    public static void playGame() {
        init();
        In userInputReader = new In();
        while (true) {
            System.out.println("Press 'n' for new hand, 'e' to end the game:");
            String userInput = userInputReader.readString();

            if (userInput.equals("n")) {
                String hand = createHand();
                playHand(hand);
            } else if (userInput.equals("e")) {
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
        playHand("ocostrza");
        playHand("arbffip");
        playHand("aretiin");
    }
}
