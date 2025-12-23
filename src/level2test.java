public class level2test {
    public static void main(String[] args) {
        System.out.println("---Level 2 tests--- \n");

        testTwoPlayerScoring();
        testWinnerDetermination();
        testTieScenario();

        System.out.println("\n ---Level 2 tests complete---");
    }

    // Test 1: Verify two-player scoring works correctly
    public static void testTwoPlayerScoring() {
        System.out.println("Test 1: Two-player scoring");

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        int target = 40;

        // Player 1 undershoots (total 35, score 5)
        player1.addCard(new Card("10", "♥"));
        player1.addCard(new Card("10", "♦"));
        player1.addCard(new Card("10", "♣"));
        player1.addCard(new Card("5", "♠"));
        player1.calculateScore(target);

        // Player 2 overshoots (total 45, score 10)
        player2.addCard(new Card("10", "♥"));
        player2.addCard(new Card("10", "♦"));
        player2.addCard(new Card("King", "♣"));
        player2.addCard(new Card("Queen", "♠"));
        player2.addCard(new Card("5", "♥"));
        player2.calculateScore(target);

        // display expected and actual scores
        System.out.println("Player 1 Expected Score: 5");
        System.out.println("Player 2 Expected Score: 10");
        System.out.println("Player 1 Total: " + player1.calculateHandTotal() + ", Score: " + player1.getScore());
        System.out.println("Player 2 Total: " + player2.calculateHandTotal() + ", Score: " + player2.getScore());

        checkResult(player1.getScore() == 5 && player2.getScore() == 10);
    }

    // Test 2: Verify winner determination
    public static void testWinnerDetermination() {
        System.out.println("\nTest2: Winner determination");

        Player player1 = new Player("Winner");
        Player player2 = new Player("Loser");
        int target = 40;

        // Player 1 is closer to target (score 2) - winner
        player1.addCard(new Card("10", "♥"));
        player1.addCard(new Card("10", "♦"));
        player1.addCard(new Card("9", "♣"));
        player1.addCard(new Card("9", "♠"));
        player1.calculateScore(target);

        // Player 2 is further from target (score 8) - loser
        player2.addCard(new Card("10", "♥"));
        player2.addCard(new Card("10", "♦"));
        player2.addCard(new Card("10", "♣"));
        player2.addCard(new Card("2", "♠"));
        player2.calculateScore(target);

        // display score for each player
        System.out.println(player1.getName() + " score: " + player1.getScore());
        System.out.println(player2.getName() + " score: " + player2.getScore());

        // if player 1's score is lower, then they are the winner, as expected
        boolean player1Wins = player1.getScore() < player2.getScore();
        System.out.println("Expected: Player 1 (Winner) has lower a score and wins");

        checkResult(player1Wins);
    }

    // Test3: Verify tie scenario
    public static void testTieScenario() {
        System.out.println("\nTest 3: Tie scenario");

        Player player1 = new Player("TiePlayer1");
        Player player2 = new Player("TiePlayer2");
        int target = 40;

        // Both players get same distance from target (score 5)
        player1.addCard(new Card("10", "♥"));
        player1.addCard(new Card("10", "♦"));
        player1.addCard(new Card("10", "♣"));
        player1.addCard(new Card("5", "♠"));
        player1.calculateScore(target);

        player2.addCard(new Card("9", "♥"));
        player2.addCard(new Card("9", "♦"));
        player2.addCard(new Card("9", "♣"));
        player2.addCard(new Card("8", "♠"));
        player2.calculateScore(target);

        // display both player's scores
        System.out.println(player1.getName() + " score: " + player1.getScore());
        System.out.println(player2.getName() + " score: " + player2.getScore());

        // if player 1 score is same as player 2 score then it's a tie
        boolean isTie = player1.getScore() == player2.getScore();
        System.out.println("Expected: Both players have equal scores (tie)");

        checkResult(isTie);
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
