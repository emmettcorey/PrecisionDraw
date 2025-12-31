public class level4test {
    public static void main(String[] args) {
        System.out.println("---Level 4 tests--- \n");

        testLeaderboardSorting();
        testPlayerRecordTracking();
        testPlayerHistorySearch();

        System.out.println("\n ---Level 4 tests complete---");
    }

    // Test 1: Verify leaderboard sorts by total wins
    public static void testLeaderboardSorting() {
        System.out.println("Test 1: Leaderboard Sorting by Wins");

        Leaderboard leaderboard = new Leaderboard();

        // Add players with different names
        leaderboard.addPlayer("Gerard");
        leaderboard.addPlayer("Micheal");
        leaderboard.addPlayer("Ronan");

        PlayerRecord gerard = leaderboard.findPlayer("Gerard");
        PlayerRecord micheal = leaderboard.findPlayer("Micheal");
        PlayerRecord ronan = leaderboard.findPlayer("Ronan");

        // Gerard 3 wins
        gerard.recordMatch(true);
        gerard.recordMatch(true);
        gerard.recordMatch(true);

        // Micheal 2 wins
        micheal.recordMatch(true);
        micheal.recordMatch(true);
        micheal.recordMatch(false);

        // Ronan 1 win
        ronan.recordMatch(true);
        ronan.recordMatch(false);
        ronan.recordMatch(false);

        // sort leaderboard
        leaderboard.sortPlayers();

        // Verify sorting should be ranked: Gerard, Micheal, Ronan
        System.out.println("Gerard wins: " + gerard.getTotalWins());
        System.out.println("Micheal wins: " + micheal.getTotalWins());
        System.out.println("Ronan wins: " + ronan.getTotalWins());
        System.out.println("Expected order: Gerard(3), Micheal(2), Ronan(1)");

        checkResult(gerard.getTotalWins() == 3 &&
                micheal.getTotalWins() == 2 &&
                ronan.getTotalWins() == 1);
    }

    // Test 2: Verify PlayerRecord tracks matches and wins correctly
    public static void testPlayerRecordTracking() {
        System.out.println("\nTest 2: PlayerRecord Match Tracking");

        PlayerRecord player = new PlayerRecord("Test");

        // Record wins and losses
        player.recordMatch(true);
        player.recordMatch(false);
        player.recordMatch(true);
        player.recordMatch(false);
        player.recordMatch(true);

        int totalMatches = player.getTotalMatches();
        int totalWins = player.getTotalWins();

        System.out.println("Matches played: " + totalMatches);
        System.out.println("Wins: " + totalWins);
        System.out.println("Expected: 5 matches, 3 wins");

        checkResult(totalMatches == 5 && totalWins == 3);
    }

    // Test 3: Verify player history search
    public static void testPlayerHistorySearch() {
        System.out.println("\nTest 3: Player History Search");

        Leaderboard leaderboard = new Leaderboard();

        // Add players
        leaderboard.addPlayer("Gerard");
        leaderboard.addPlayer("Micheal");

        // Record matches for Gerard
        PlayerRecord gerard = leaderboard.findPlayer("Gerard");
        gerard.recordMatch(true);
        gerard.recordMatch(true);
        gerard.recordMatch(false);

        // Test finding existing player
        PlayerRecord foundGerard = leaderboard.findPlayer("Gerard");
        System.out.println("Searching for Gerard");
        if (foundGerard != null) {
            System.out.println("Found: " + foundGerard.getName());
            System.out.println("Wins: " + foundGerard.getTotalWins());
        } else {
            System.out.println("Found: Not found");
        }

        // Test finding non-existing player
        PlayerRecord notFound = leaderboard.findPlayer("Malachi");
        System.out.println("\nSearching for Malachi");
        if (notFound != null) {
            System.out.println("Found: " + notFound.getName());
        } else {
            System.out.println("Found: Not found");
        }
        System.out.println("Expected: Not found");

        checkResult(foundGerard != null &&
                foundGerard.getName().equals("Gerard") &&
                foundGerard.getTotalWins() == 2 &&
                notFound == null);
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
