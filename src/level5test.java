public class level5test {
    public static void main(String[] args) {
        System.out.println("---Level 5 tests--- \n");

        testCompareTwoPlayers();
        testListPlayersWithMinWins();
        testWinPercentageCalculation();

        System.out.println("\n ---Level 5 tests complete---");
    }

    // Test 1: Verify comparing two players displays correct stats
    public static void testCompareTwoPlayers() {
        System.out.println("Test 1: Compare Two Players");

        Leaderboard leaderboard = new Leaderboard();

        // add players
        leaderboard.addPlayer("Gerard");
        leaderboard.addPlayer("Micheal");

        PlayerRecord gerard = leaderboard.findPlayer("Gerard");
        PlayerRecord micheal = leaderboard.findPlayer("Micheal");

        // add results for Gerard 3/4 (75%)
        gerard.recordMatch(true);
        gerard.recordMatch(true);
        gerard.recordMatch(true);
        gerard.recordMatch(false);

        // add results for micheal 2/4 (50%)
        micheal.recordMatch(true);
        micheal.recordMatch(true);
        micheal.recordMatch(false);
        micheal.recordMatch(false);

        System.out.println("Gerard: " + gerard.getTotalWins() + " wins, " +
                gerard.getTotalMatches() + " matches");
        System.out.println("Micheal: " + micheal.getTotalWins() + " wins, " +
                micheal.getTotalMatches() + " matches");
        System.out.println("Expected: Gerard (3/4), Micheal (2/4)");

        // test the comparison method displays correctly
        leaderboard.compareTwoPlayers("Gerard", "Micheal");

        checkResult(gerard.getTotalWins() == 3 &&
                gerard.getTotalMatches() == 4 &&
                micheal.getTotalWins() == 2 &&
                micheal.getTotalMatches() == 4);
    }

    // Test 2: Verify listing players with minimum wins shows correctly
    public static void testListPlayersWithMinWins() {
        System.out.println("\nTest 2: List Players With Min Wins");

        Leaderboard leaderboard = new Leaderboard();

        // add players with different win counts
        leaderboard.addPlayer("Gerard");
        leaderboard.addPlayer("Micheal");
        leaderboard.addPlayer("Emmett");
        leaderboard.addPlayer("Corey");

        PlayerRecord gerard = leaderboard.findPlayer("Gerard");
        PlayerRecord micheal = leaderboard.findPlayer("Micheal");
        PlayerRecord emmett = leaderboard.findPlayer("Emmett");
        PlayerRecord corey = leaderboard.findPlayer("Corey");

        // add match results for players
        gerard.recordMatch(true);
        gerard.recordMatch(true);
        gerard.recordMatch(true);
        gerard.recordMatch(true);
        gerard.recordMatch(true);

        micheal.recordMatch(true);
        micheal.recordMatch(true);
        micheal.recordMatch(true);

        emmett.recordMatch(true);

        corey.recordMatch(false);

        System.out.println("Gerard: " + gerard.getTotalWins() + " wins");
        System.out.println("Micheal: " + micheal.getTotalWins() + " wins");
        System.out.println("Emmett: " + emmett.getTotalWins() + " wins");
        System.out.println("Corey: " + corey.getTotalWins() + " wins");

        System.out.println("\nListing players with more than 2 wins:");
        leaderboard.listPlayersWithMinWins(2);
        System.out.println("Expected: Gerard(5), Micheal(3)");

        System.out.println("\nListing players with more than 0 wins:");
        leaderboard.listPlayersWithMinWins(0);
        System.out.println("Expected: Gerard(5), Micheal(3), Emmett(1)");

        checkResult(gerard.getTotalWins() == 5 &&
                micheal.getTotalWins() == 3 &&
                emmett.getTotalWins() == 1 &&
                corey.getTotalWins() == 0);
    }

    // Test 3: Verify win percentage calculation is accurate
    public static void testWinPercentageCalculation() {
        System.out.println("\nTest 3: Win Percentage Calculation");

        // test 100% win rate
        PlayerRecord player1 = new PlayerRecord("100% Record");
        player1.recordMatch(true);

        double winRate1 = player1.getWinPercentage();
        System.out.println("Player with 1/1 wins: " + winRate1 + "%");
        System.out.println("Expected: 100.0%");

        // test 50& win rate
        PlayerRecord player2 = new PlayerRecord("50% Record");
        player2.recordMatch(true);
        player2.recordMatch(true);
        player2.recordMatch(false);
        player2.recordMatch(false);

        double winRate2 = player2.getWinPercentage();
        System.out.println("Player with 2/4 wins: " + winRate2 + "%");
        System.out.println("Expected: 50.0%");

        // test 0% win rate
        PlayerRecord player3 = new PlayerRecord("0% Record");
        player3.recordMatch(false);

        double winRate3 = player3.getWinPercentage();
        System.out.println("Player with 0/1 wins: " + winRate3 + "%");
        System.out.println("Expected: 0.0%");

        checkResult(winRate1 == 100.0 &&
                winRate2 == 50.0 &&
                winRate3 == 0.0);
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
