public class level3test {
    public static void main(String[] args) {
        System.out.println("---Level 3 tests--- \n");

        testAceOptimisation();
        testDynamicTarget();
        testCumulativeScoring();

        System.out.println("\n ---Level 3 tests complete---");
    }

    // Test 1: Verify ace optimisation
    public static void testAceOptimisation() {
        System.out.println("Test 1: Ace Optimisation");
        int target = 40;

        // Scenario 1: Ace stays at 11
        // add cards to player hand that undershoots target
        Player player1 = new Player("Test");
        player1.addCard(new Card("Queen", "♥"));
        player1.addCard(new Card("King", "♥"));
        player1.addCard(new Card("Ace", "♥"));
        // total of 31 with ace = 11

        int optimised1 = player1.calculateOptimisedTotal(target);
        player1.calculateScore(target);
        System.out.println("Scenario 1: Undershoot");
        System.out.println("Optimised Total: " + optimised1 + " (Ace stays at 11)");
        System.out.println("Score: " + player1.getScore());
        System.out.println("Expected: Total = 31, Score = 9");
        checkResult(optimised1 == 31 && player1.getScore() == 9);

        // Scenario 2: Ace converts to 1
        // add cards to player hand that overshoots target
        Player player2 = new Player("Test");
        player2.addCard(new Card("King", "♥"));
        player2.addCard(new Card("King", "♦"));
        player2.addCard(new Card("King", "♣"));
        player2.addCard(new Card("King", "♠"));
        player2.addCard(new Card("Ace", "♥"));
        // total of 51 with ace = 11, 41 with ace = 1

        int optimised2 = player2.calculateOptimisedTotal(target);
        player2.calculateScore(target);
        System.out.println("Scenario 2: Undershoot");
        System.out.println("Optimised Total: " + optimised2 + " (Ace converts to 1)");
        System.out.println("Score: " + player2.getScore());
        System.out.println("Expected: Total = 41, Score = 2");
        checkResult(optimised2 == 41 && player2.getScore() == 2);

        // Scenario 3: Multiple aces
        // add cards with multiple aces to player hand that overshoots target
        Player player3 = new Player("Test");
        player3.addCard(new Card("King", "♥"));
        player3.addCard(new Card("King", "♦"));
        player3.addCard(new Card("King", "♣"));
        player3.addCard(new Card("Ace", "♠"));
        player3.addCard(new Card("Ace", "♥"));
        // total of 52 with both aces = 11, 42 with 1 ace = 1

        int optimised3 = player3.calculateOptimisedTotal(target);
        player3.calculateScore(target);
        System.out.println("Scenario 3: Multiple Aces");
        System.out.println("Optimised Total: " + optimised3 + " (1 Ace converted to 1)");
        System.out.println("Score: " + player3.getScore());
        System.out.println("Expected: Total = 42, Score = 4");
        checkResult(optimised3 == 42 && player3.getScore() == 4);
    }

    // Test 2: Verify dynamic target changes based on player performances
    public static void testDynamicTarget() {
        System.out.println("\nTest 2: Dynamic Target Adjustment");
        int target = 40;

        // Scenario 1: Both undershoot, target increases
        Player player1_under = new Player("Player1 Under");
        Player player2_under = new Player("Player2 Under");

        // add cards to player 1 hand that undershoots target
        player1_under.addCard(new Card("10", "♥"));
        player1_under.addCard(new Card("10", "♦"));
        player1_under.addCard(new Card("10", "♣"));
        // total of 30

        // add cards to player 2 hand that undershoots target
        player2_under.addCard(new Card("10", "♥"));
        player2_under.addCard(new Card("10", "♦"));
        player2_under.addCard(new Card("10", "♣"));
        // total of 30

        // calculate player 1 and player 2 total
        int player1_under_total = player1_under.calculateOptimisedTotal(target);
        int player2_under_total = player2_under.calculateOptimisedTotal(target);
        boolean bothUndershoot = (player1_under_total < target) && (player2_under_total < target);

        System.out.println("Scenario 1: Both Undershoot");
        System.out.println("Player 1: " + player1_under_total);
        System.out.println("Player 2: " + player2_under_total);
        System.out.println("Expected: Target increases from 40 to 45");
        checkResult(bothUndershoot);

        // Scenario 2: Both overshoot, target decreases
        Player player1_over = new Player("Player1 Over");
        Player player2_over = new Player("Player2 Over");

        // add cards to player 1 hand that overshoots target
        player1_over.addCard(new Card("King", "♥"));
        player1_over.addCard(new Card("King", "♦"));
        player1_over.addCard(new Card("King", "♣"));
        player1_over.addCard(new Card("King", "♠"));
        player1_over.addCard(new Card("5", "♥"));
        // total of 45

        // add cards to player 2 hand that overshoots target
        player2_over.addCard(new Card("King", "♥"));
        player2_over.addCard(new Card("King", "♦"));
        player2_over.addCard(new Card("King", "♣"));
        player2_over.addCard(new Card("King", "♠"));
        player2_over.addCard(new Card("5", "♥"));
        // total of 45

        // calculate player 1 and player 2 total
        int player1_over_total = player1_over.calculateOptimisedTotal(target);
        int player2_over_total = player2_over.calculateOptimisedTotal(target);
        boolean bothOvershoot = (player1_over_total > target) && (player2_over_total > target);

        System.out.println("Scenario 2: Both Overshoot");
        System.out.println("Player 1: " + player1_over_total);
        System.out.println("Player 2: " + player2_over_total);
        System.out.println("Expected: Target decreases from 40 to 35");
        checkResult(bothOvershoot);

        // Scenario 3: One under, one over target, target unchanged
        Player player1_mixed = new Player("Player1 Mixed");
        Player player2_mixed = new Player("Player2 Mixed");

        // add cards to player 1 hand that undershoots target
        player1_mixed.addCard(new Card("10", "♥"));
        player1_mixed.addCard(new Card("10", "♦"));
        player1_mixed.addCard(new Card("10", "♣"));
        // total of 30

        // add cards to player 2 hand that overshoots target
        player2_mixed.addCard(new Card("King", "♥"));
        player2_mixed.addCard(new Card("King", "♦"));
        player2_mixed.addCard(new Card("King", "♣"));
        player2_mixed.addCard(new Card("King", "♠"));
        player2_mixed.addCard(new Card("5", "♥"));
        // total of 45

        // calculate player 1 and player 2 total
        int player1_mixed_total = player1_mixed.calculateOptimisedTotal(target);
        int player2_mixed_total = player2_mixed.calculateOptimisedTotal(target);
        boolean noChange = (player1_mixed_total < target && player2_mixed_total > target);

        System.out.println("Scenario 3: Mixed");
        System.out.println("Player 1: " + player1_mixed_total);
        System.out.println("Player 2: " + player2_mixed_total);
        checkResult(noChange);
    }

    // Test 3: Verify cumulative scoring works
    public static void testCumulativeScoring() {
        System.out.println("\nTest 3: Cumulative Scoring");

        Player player = new Player("Test");
        int target = 40;

        // Add cards to player hand for round 1
        player.addCard(new Card("10", "♥"));
        player.addCard(new Card("10", "♦"));
        player.addCard(new Card("10", "♣"));
        player.addCard(new Card("5", "♠"));
        // total of 35 and score of 5

        // calculate score and add it to round score
        player.calculateScore(target);
        player.addRoundScore();

        int round1_cumulative_score = player.getCumulativeScore();
        player.clearHand();

        System.out.println("Round 1 Score: 5");
        System.out.println("Cumulative score after round 1: " + round1_cumulative_score);

        // add cards to player hand for round 2
        player.addCard(new Card("10", "♥"));
        player.addCard(new Card("10", "♦"));
        player.addCard(new Card("10", "♣"));
        player.addCard(new Card("9", "♠"));
        // total of 39 and score of 1

        // calculate score and add it to round score
        player.calculateScore(target);
        player.addRoundScore();

        int round2_cumulative_score = player.getCumulativeScore();

        System.out.println("Round 2 Score: 1");
        System.out.println("Cumulative score after round 2: " + round2_cumulative_score);
        System.out.println("Expected: 6");

        checkResult(round1_cumulative_score == 5 && round2_cumulative_score == 6);
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
