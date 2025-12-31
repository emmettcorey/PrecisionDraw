public class level1test {
    public static void main(String[] args) {
        System.out.println("---Level 1 tests--- \n");

        testCardValues();
        testDeckCreation();
        testDeckShuffle();
        testUndershootScoring();
        testPerfectScoring();
        testOvershootScoring();

        System.out.println("\n ---Level 1 tests complete---");
    }

    // Test 1: Verify card values are calculated properly
    public static void testCardValues() {
        System.out.println("Test 1: Card Value Calculation");
        // create a number, face, and ace card
        Card numberCard = new Card("5", "Hearts");
        Card faceCard = new Card("King", "Clubs");
        Card aceCard = new Card("Ace", "Diamonds");

        // print their values
        System.out.println("5 of Hearts value: " + numberCard.getValue());
        System.out.println("King of Clubs value: " + faceCard.getValue());
        System.out.println("Ace of Diamonds value: " + aceCard.getValue());

        // call checkResult method passing expected card values
        checkResult((numberCard.getValue() == 5) &&
                (faceCard.getValue() == 10) &&
                (aceCard.getValue() == 11));
    }

    // Test 2: Verify deck has 52 cards
    public static void testDeckCreation() {
        System.out.println("\nTest 2: Deck creation (52 cards)");

        // create a new deck with 52 cards
        Deck deck = new Deck();
        System.out.println("Cards in deck: " + deck.cardsRemaining());

        // call checkResult method passing 52 as expected cards remaining
        checkResult(deck.cardsRemaining() == 52);
    }

    // Test 3: Verify shuffle changes card order
    public static void testDeckShuffle() {
        System.out.println("\nTest 3: Deck Shuffle");

        Deck deck = new Deck();
        deck.shuffle();

        // Draw first 5 cards before shuffle
        Card[] cardsBefore = new Card[5];
        for (int i = 0; i < 5; i++) {
            cardsBefore[i] = deck.drawCard();
        }

        // print drawn cards
        System.out.println("Cards before: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(cardsBefore[i].getRank() + " of " + cardsBefore[i].getSuit() + ", ");
        }

        // Restock and shuffle
        System.out.println("\nShuffling cards");
        deck.restock();

        // Draw first 5 cards after shuffle
        Card[] cardsAfter = new Card[5];
        for (int i = 0; i < 5; i++) {
            cardsAfter[i] = deck.drawCard();
        }

        // print drawn cards
        System.out.println("Cards after: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(cardsAfter[i].getRank() + " of " + cardsAfter[i].getSuit() + ", ");
        }
        System.out.println(" ");

        // Check if at least one card is different
        boolean different = false;
        for (int i = 0; i < 5; i++) {
            if (!cardsBefore[i].getRank().equals(cardsAfter[i].getRank())) {
                different = true;
                break;
            }
        }

        checkResult(different);
    }

    // Test 4: Undershoot scoring
    public static void testUndershootScoring() {
        System.out.println("\nTest 4: Undershoot Scoring");

        Player player = new Player("Test Player");
        int target = 40;

        // manually add cards that undershoots target of 40 (total of 35)
        player.addCard(new Card("5", "Hearts"));
        player.addCard(new Card("10", "Diamonds"));
        player.addCard(new Card("Queen", "Clubs"));
        player.addCard(new Card("King", "Spades"));

        int total = player.calculateHandTotal();
        player.calculateScore(target);

        // display results
        System.out.println("Target: " + target);
        System.out.println("Hand Total: " + total);
        System.out.println("Expected Score: 5");
        System.out.println("Actual Score: " + player.getScore());

        // call checkResult method and pass if score is 5 as expected
        checkResult(player.getScore() == 5);
    }

    // Test 5: Perfect scoring
    public static void testPerfectScoring() {
        System.out.println("\nTest 5: Perfect Scoring");

        Player player = new Player("Test Player");
        int target = 40;

        // manually add cards that perfectly scores target of 40
        player.addCard(new Card("10", "Hearts"));
        player.addCard(new Card("10", "Diamonds"));
        player.addCard(new Card("Queen", "Clubs"));
        player.addCard(new Card("King", "Spades"));

        int total = player.calculateHandTotal();
        player.calculateScore(target);

        // display results
        System.out.println("Target: " + target);
        System.out.println("Hand Total: " + total);
        System.out.println("Expected Score: -5");
        System.out.println("Actual Score: " + player.getScore());

        // call checkResult method and pass if score is -5 as expected
        checkResult(player.getScore() == -5);
    }

    // Test 6: Overshoot Scoring
    public static void testOvershootScoring() {
        System.out.println("\nTest 6: Overshoot Scoring");

        Player player = new Player("Test Player");
        int target = 40;

        // manually add cards that overshoots target of 40 (total of 45)
        player.addCard(new Card("10", "Hearts"));
        player.addCard(new Card("10", "Diamonds"));
        player.addCard(new Card("Queen", "Clubs"));
        player.addCard(new Card("King", "Spades"));
        player.addCard(new Card("5", "Hearts"));

        int total = player.calculateHandTotal();
        player.calculateScore(target);

        int expectedScore = 2 * (total - target);

        // display results
        System.out.println("Target: " + target);
        System.out.println("Hand Total: " + total);
        System.out.println("Expected Score: " + expectedScore);
        System.out.println("Actual Score: " + player.getScore());

        checkResult(player.getScore() == expectedScore);
    }

    // Helper method to display test results
    public static void checkResult(boolean condition) {
        if (condition) {
            System.out.println("RESULT: PASS");
        } else {
            System.out.println("RESULT: FAIL");
        }
    }
}
