import java.util.Scanner;
import java.util.Random;

public class PrecisionDraw {
    // instance variables
    private Deck deck;
    private Player player1;
    private Player player2;
    private int target;
    private Scanner scanner;
    private Random random;
    private Leaderboard leaderboard;

    // PrecisionDraw constructor
    public PrecisionDraw() {
        this.deck = new Deck();
        this.target = 40; // base target
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.leaderboard = new Leaderboard();
    }

    // Helper method to return random value
    private int getRandomValue(int low, int high) {
        return random.nextInt(high - low + 1) + low;
    }

    // Main method to play a full 4-round match
    // level 3: changed from separate methods for each level to one method
    public void playMatch() {
        target = 40;

        // Ask if playing against simulated player
        System.out.print("Play against simulated player? (y/n): ");
        String choice = scanner.nextLine();

        // Get player names
        System.out.print("Enter Player 1 name: ");
        String name1 = scanner.nextLine();
        player1 = new Player(name1);

        String name2;
        boolean player2IsSimulated = false;

        if (choice.equalsIgnoreCase("y")) {
            name2 = generateSimPlayerName();
            System.out.println("You will play against: " + name2);
            player2IsSimulated = true;
        } else {
            System.out.print("Enter Player 2 name: ");
            name2 = scanner.nextLine();
        }
        player2 = new Player(name2);

        // Randomly select first player for Round 1
        int firstPlayerChoice = getRandomValue(1, 2);
        Player firstPlayer, secondPlayer;

        if (firstPlayerChoice == 1) {
            firstPlayer = player1;
            secondPlayer = player2;
        } else {
            firstPlayer = player2;
            secondPlayer = player1;
        }

        // Play 4 rounds
        for (int i = 1; i <= 4; i++) {
            System.out.println("\n--- Round " + i + " ---");
            System.out.println("The current target is: " + target);
            System.out.println("The player to go first is: " + firstPlayer.getName());

            // Restock and shuffle deck for each round
            deck.restock();

            // Determine if each player is simulated
            boolean firstIsSimulated = (firstPlayer == player2 && player2IsSimulated);
            boolean secondIsSimulated = (secondPlayer == player2 && player2IsSimulated);

            // First player's turn
            int firstPlayerGuess = playTurn(firstPlayer, firstIsSimulated, false, 0);

            // Second player's turn
            playTurn(secondPlayer, secondIsSimulated, true, firstPlayerGuess);

            // Add round scores to cumulative totals
            player1.addRoundScore();
            player2.addRoundScore();

            // Display cumulative scores
            // Display cumulative match scores
            System.out.println("\n" + player1.getName() + "'s match score is: " + player1.getCumulativeScore());
            System.out.println(player2.getName() + "'s match score is: " + player2.getCumulativeScore());

            // Adjust target based on both players' performance
            if (i < 4) {
                adjustTarget(firstPlayer, secondPlayer);
            }

            // Clear both hands for next round
            player1.clearHand();
            player2.clearHand();

            // Alternate who goes first each round
            Player temp = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = temp;
        }

        // Determine match winner
        System.out.println("\nFinal Match Score: ");
        System.out.println(player1.getName() + ": " + player1.getCumulativeScore());
        System.out.println(player2.getName() + ": " + player2.getCumulativeScore());

        if (player1.getCumulativeScore() < player2.getCumulativeScore()) {
            System.out.println(player1.getName() + " wins");
        } else if (player2.getCumulativeScore() < player1.getCumulativeScore()) {
            System.out.println(player2.getName() + " wins");
        } else {
            System.out.println("\nIt's a tie!");
        }

        recordMatchResults();
    }

    // Helper method to handle a player's turn
    private void playPlayerTurn(Player player) {

        // Prompt for guess
        System.out.println();
        System.out.print(player.getName() + ", please enter your guess: ");
        int numCards = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // validation for player guess
        if (numCards < 1 || numCards > 52) {
            System.out.println("Invalid number of cards!");
            System.out.println("Please enter a number between 1 and 52.");
            return;
        }

        // Draw phase
        for (int i = 0; i < numCards; i++) {
            Card card = deck.drawCard();
            if (card != null) {
                player.addCard(card);
            }
        }

        // Calculate score (with ace optimisation)
        player.calculateScore(target);

        // Display hand
        player.displayHand();

        // Display result with ace optimisation message
        int handTotal = player.calculateHandTotal();
        int optimisedTotal = player.calculateOptimisedTotal(target);

        // Check if ace optimisation occurred
        if (handTotal != optimisedTotal) {
            System.out.println("Ace optimised... updating round total from " + handTotal + " to " + optimisedTotal);
        }

        if (optimisedTotal < target) {
            System.out.println("Under with a round total of " + optimisedTotal + " (score: " + player.getScore() + ")");
        } else if (optimisedTotal > target) {
            int overshoot = optimisedTotal - target;
            System.out.println("Over with a round total of " + optimisedTotal + " (score of " + overshoot + " penalised to " + player.getScore() + ")");
        } else {
            System.out.println("Perfect score! Round total of " + optimisedTotal + " (score: " + player.getScore() + ")");
        }
    }

    // helper method to decide which player turn method to call
    private int playTurn(Player player, boolean isSimulated, boolean isSecondPlayer, int firstPlayerGuess) {
        if (isSimulated) {
            playSimulatedPlayerTurn(player, isSecondPlayer, firstPlayerGuess);
            return player.getCardCount();
        } else {
            playPlayerTurn(player);
            return player.getCardCount();
        }
    }

    // Helper method to adjust target based on both players' performance
    private void adjustTarget(Player player1, Player player2) {
        int player1Total = player1.calculateOptimisedTotal(target);
        int player2Total = player2.calculateOptimisedTotal(target);

        boolean player1Undershoot = player1Total < target;
        boolean player2Undershoot = player2Total < target;
        boolean player1Overshoot = player1Total > target;
        boolean player2Overshoot = player2Total > target;

        if (player1Undershoot && player2Undershoot) {
            target += 5;
            System.out.println("\nBoth players undershot - adjusting target");
        } else if (player1Overshoot && player2Overshoot) {
            target -= 5;
            System.out.println("\nBoth players overshot - adjusting target");
        } else {
            System.out.println("\nTarget remains at " + target);
        }
    }

    // method to record match results to leaderboard
    private void recordMatchResults() {
        PlayerRecord player1Record = leaderboard.getPlayer(player1.getName());
        PlayerRecord player2Record = leaderboard.getPlayer(player2.getName());

        // if player 1 cumulative score is less than player 2
        if (player1.getCumulativeScore() < player2.getCumulativeScore()) {
            // player 1 win player 2 lose
            player1Record.recordMatch(true);
            player2Record.recordMatch(false);
            System.out.println(player1.getName() + " wins with a tally of: " + player1Record.getTotalWins());
            // else if player 2 cumulative score less than player 1
        } else if (player2.getCumulativeScore() < player1.getCumulativeScore()) {
            // player 1 lose player 2 win
            player1Record.recordMatch(false);
            player2Record.recordMatch(true);
            System.out.println(player2.getName() + " wins with a tally of: " + player2Record.getTotalWins());
            // else draw
        } else {
            // player 1 draw player 2 draw
            player1Record.recordMatch(false);
            player2Record.recordMatch(false);
        }
    }

    // method to display leaderboard
    public void displayLeaderboard() {
        leaderboard.displayLeaderboard();
    }

    // method to search for player history
    public void searchPlayerHistory() {
        System.out.print("Enter player name to search: ");
        String name = scanner.nextLine();
        leaderboard.searchPlayer(name);
    }

    // method to get input for compare player method
    public void compareTwoPlayers() {
        System.out.print("Enter first player name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter second player name: ");
        String name2 = scanner.nextLine();

        leaderboard.compareTwoPlayers(name1, name2);
    }

    // method to get input for displaying minimum wins method
    public void listPlayersWithMinWins() {
        System.out.print("Enter minimum number of wins: ");
        int minWins = scanner.nextInt();

        leaderboard.listPlayersWithMinWins(minWins);
    }

    // level 6
    // helper method to generate simulated player name
    public String generateSimPlayerName() {
        int playerNumber = getRandomValue(1,10);
        return "SimPlayer" + playerNumber;
    }

    // helper method for simulated player turn
    private void playSimulatedPlayerTurn(Player player, boolean isSecondPlayer, int firstPlayerGuess) {
        int numCards;

        // second player always guesses 2 more than first player
        if (isSecondPlayer) {
            numCards = firstPlayerGuess + 2;
        } else {
            // first player guesses between 3-7 cards
            numCards = getRandomValue(3,7);
        }

        System.out.println();
        System.out.println(player.getName() + " (simulated) guesses: " + numCards);

        // draw cards
        for (int i = 0; i < numCards; i++) {
            Card card = deck.drawCard();
            if (card != null) {
                player.addCard(card);
            }
        }

        // calculate score and display
        player.calculateScore(target);
        player.displayHand();

        int handTotal = player.calculateHandTotal();
        int optimisedTotal = player.calculateOptimisedTotal(target);

        if (handTotal != optimisedTotal) {
            System.out.println("Ace optimised... updating round total from " + handTotal + " to " + optimisedTotal);
        }

        if (optimisedTotal < target) {
            System.out.println("Under with a round total of " + optimisedTotal + " (score: " + player.getScore() + ")");
        } else if (optimisedTotal > target) {
            int overshoot = optimisedTotal - target;
            System.out.println("Over with a round total of " + optimisedTotal + " (score of " + overshoot + " penalised to " + player.getScore() + ")");
        } else {
            System.out.println("Perfect score! Round total of " + optimisedTotal + " (score: " + player.getScore() + ")");
        }
    }

    // method to run simulation
    public void runSimulation() {
        System.out.print("Enter number of simulations: ");
        int numSimulations = scanner.nextInt();

        for (int i = 1; i <= numSimulations; i++) {
            System.out.println("Simulated Match " + i);
            String name1 = generateSimPlayerName();
            String name2 = generateSimPlayerName();

            // while name2 is same as name1 generate another name
            while (name1.equals(name2)) {
                name2 = generateSimPlayerName();
            }

            player1 = new Player(name1);
            player2 = new Player(name2);

            // play simulated match
            playSimulatedMatch();
            System.out.println();
        }
    }

    // helper method to play a simulated match
    private void playSimulatedMatch() {
        target = 40;

        // randomly select first player
        int firstPlayerChoice = getRandomValue(1,2);
        Player firstPlayer, secondPlayer;

        if (firstPlayerChoice == 1) {
            firstPlayer = player1;
            secondPlayer = player2;
        } else {
            firstPlayer = player2;
            secondPlayer = player1;
        }

        // Play 4 rounds
        for (int i = 1; i <= 4; i++) {
            System.out.println("\n--- Round " + i + " ---");
            System.out.println("The current target is: " + target);
            System.out.println("The player to go first is: " + firstPlayer.getName());

            // Restock and shuffle deck for each round
            deck.restock();

            // First player's turn
            int firstPlayerGuess = getRandomValue(3,7);
            playSimulatedPlayerTurn(firstPlayer, false, firstPlayerGuess);

            // Second player's turn
            playSimulatedPlayerTurn(secondPlayer, true, firstPlayerGuess);

            // Add round scores to cumulative totals
            player1.addRoundScore();
            player2.addRoundScore();

            // Display cumulative scores
            // Display cumulative match scores
            System.out.println("\n" + player1.getName() + "'s match score is: " + player1.getCumulativeScore());
            System.out.println(player2.getName() + "'s match score is: " + player2.getCumulativeScore());

            // Adjust target based on both players' performance
            if (i < 4) {
                adjustTarget(firstPlayer, secondPlayer);
            }

            // Clear both hands for next round
            player1.clearHand();
            player2.clearHand();

            // Alternate who goes first each round
            Player temp = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = temp;
        }

        // Display final results and record
        System.out.println("\nFinal Match Score:");
        System.out.println(player1.getName() + ": " + player1.getCumulativeScore());
        System.out.println(player2.getName() + ": " + player2.getCumulativeScore());

        recordMatchResults();
    }
}