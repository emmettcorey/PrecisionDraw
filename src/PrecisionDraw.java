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

    // PrecisionDraw constructor
    public PrecisionDraw() {
        this.deck = new Deck();
        this.target = 40; // base target for level 2
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    // level 1
    // method to play a single player round
    public void playSingleRound() {

        // prompt user for name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Player player = new Player(name);

        // shuffle the deck
        deck.shuffle();

        // display target
        System.out.println("--- Round 1 ---");
        System.out.println("The current target is: " + target);

        // prompt user to enter their guess
        System.out.print(name + " please enter your guess: ");
        int numCards = scanner.nextInt();

        // validation for player guess
        if (numCards < 1 || numCards > 52) {
            System.out.println("Invalid number of cards!");
            System.out.println("Please enter a number between 1 and 52.");
            return;
        }

        // draw phase
        for (int i = 0; i < numCards; i++) {
            Card card = deck.drawCard();
            if (card != null) {
                player.addCard(card);
            }
        }

        // calculate and display score
        player.calculateScore(target);
        player.displayHand();

        // feedback message
        int handTotal = player.calculateHandTotal();
        if (handTotal < target) {
            System.out.println("Under with a round total of: " + handTotal + " (score of " + player.getScore() + ")");
        } else if(handTotal > target) {
            int overshoot = handTotal - target;
            System.out.println("Over with a round total of: " + handTotal + " (score of " + overshoot + " penalised to " +  player.getScore() + ")");
        } else {
            System.out.println("Perfect score! Round total of : " + handTotal + " (score of " + player.getScore() + ")");
        }
    }

    // level 2
    // helper method to return random value
    private int getRandomValue(int low, int high) {
        return random.nextInt(high - low + 1) + low;
    }

    // method to play two-player, one-round match
    public void playOneRound() {
        // prompt player1 for their name
        System.out.print("Enter Player1 name: ");
        String name1 = scanner.nextLine();
        player1 = new Player(name1);

        //prompt player2 for their name
        System.out.print("Enter Player2 name: ");
        String name2 = scanner.nextLine();
        player2 = new Player(name2);

        // shuffle the deck
        deck.shuffle();

        // randomly select the first player
        int firstPlayerChoice = getRandomValue(1,2);
        Player firstPlayer, secondPlayer;
        if (firstPlayerChoice == 1) {
            firstPlayer = player1;
            secondPlayer = player2;
        } else {
            firstPlayer = player2;
            secondPlayer = player1;
        }

        System.out.println("--- Round 1 ---");
        System.out.println("The current target is: " + target);
        System.out.println("The player to go first is: " + firstPlayer.getName());

        // first player's turn
        playerTurn(firstPlayer);

        // second player's turn
        playerTurn(secondPlayer);

        // determine round 1 winner
        System.out.println(player1.getName() + "'s score was: " + player1.getScore());
        System.out.println(player2.getName() + "'s score was: " + player2.getScore());

        if (player1.getScore() < player2.getScore()) {
            System.out.println(player1.getName() + " is the winner!");
        } else if (player1.getScore() > player2.getScore()) {
            System.out.println(player2.getName() + " is the winner!");
        } else {
            System.out.println("Both players received same score, it is a draw!");
        }
    }

    // helper method to handle player turn
    private void playerTurn(Player player) {
        // prompt players for their guess
        System.out.print(player.getName() + ", please enter your guess: ");
        int numCards = scanner.nextInt();

        // validation for player guess
        if (numCards < 1 || numCards > 52) {
            System.out.println("Invalid number of cards!");
            System.out.println("Please enter a number between 1 and 52.");
            return;
        }

        // draw phase
        for (int i = 0; i < numCards; i++) {
            Card card = deck.drawCard();
            if (card != null) {
                player.addCard(card);
            }
        }

        // calculate and display score
        player.calculateScore(target);
        player.displayHand();

        // feedback message
        int handTotal = player.calculateHandTotal();
        if (handTotal < target) {
            System.out.println("Under with a round total of: " + handTotal + " (score of " + player.getScore() + ")");
        } else if(handTotal > target) {
            int overshoot = handTotal - target;
            System.out.println("Over with a round total of: " + handTotal + " (score of " + overshoot + " penalised to " +  player.getScore() + ")");
        } else {
            System.out.println("Perfect score! Round total of : " + handTotal + " (score of " + player.getScore() + ")");
        }
    }
}
