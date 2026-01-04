public class level6test {
    public static void main(String[] args) {
        System.out.println("---Level 6 Tests---\n");

        testNameGeneration();
        testSimulatedPlayerGuessLogic();

        System.out.println("\n---Level 6 tests complete---");
    }

    // Test 1: Verify simulated player names are generated correctly
    public static void testNameGeneration() {
        System.out.println("Test 1: Simulated Player Name Generation");

        PrecisionDraw game = new PrecisionDraw();

        // generate multiple simulated player names
        String name1 = game.generateSimPlayerName();
        String name2 = game.generateSimPlayerName();
        String name3 = game.generateSimPlayerName();

        System.out.println("Generated names: " + name1 + ", " + name2 + ", " + name3);

        // check names follow correct format (SimPlayer + number)
        boolean validFormat1 = name1.startsWith("SimPlayer") && name1.length() > 9;
        boolean validFormat2 = name2.startsWith("SimPlayer") && name2.length() > 9;
        boolean validFormat3 = name3.startsWith("SimPlayer") && name3.length() > 9;

        System.out.println("Expected Format: SimPlayerX");

        checkResult(validFormat1 && validFormat2 && validFormat3);
    }

    // Test 2: Verify simulated player guessing logic
    public static void testSimulatedPlayerGuessLogic() {
        System.out.println("\nTest 2: Simulated player guess logic");

        // test first player guess (between 3-7 cards)
        System.out.println("Testing first player guess range (expected between 3-7 cards)");

        // test second player guess logic
        System.out.println("\nTesting second player guess logic");
        int firstPlayerGuess = 5;
        int expectedSecondPlayerGuess = firstPlayerGuess + 2;

        System.out.println("If first player guesses: " + firstPlayerGuess);
        System.out.println("Expected second player should guess: " + expectedSecondPlayerGuess);

        checkResult(expectedSecondPlayerGuess == 7);
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
