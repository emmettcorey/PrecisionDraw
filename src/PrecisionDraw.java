import java.util.Scanner;

public class PrecisionDraw {
    // instance variables
    private Deck deck;
    private Player player;
    private int target;
    private Scanner scanner;

    // PrecisionDraw constructor
    public PrecisionDraw() {
        this.deck = new Deck();
        this.target = 40; // base target for level 1
        this.scanner = new Scanner(System.in);
    }

    // method to play a single player round
    public void playSingleRound() {

        // prompt user for name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        player = new Player(name);

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
}
