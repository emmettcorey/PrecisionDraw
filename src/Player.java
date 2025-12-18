public class Player {
    // instance variables
    private String name;
    private Card[] hand;
    private int cardCount;
    private int score;

    // player constructor
    public Player (String name) {
        this.name = name;
        this.hand = new Card[52];
        this.cardCount = 0;
        this.score = 0;
    }

    // add card to player's hand
    public void addCard(Card card) {
        if (cardCount < hand.length) {
            hand[cardCount] = card;
            cardCount++;
        }
    }

    // calculate total value of cards in hand
    public int calculateHandTotal() {
        int total = 0;
        for (int i = 0; i < cardCount; i ++) {
            total += hand[i].getValue();
        }
        return total;
    }

    // clear hand after each round
    public void clearHand() {
        cardCount = 0;
    }

    // calculate score based on total
    public void calculateScore(int target) {
        int handTotal = calculateHandTotal();
        // perfect score
        if (handTotal == target) {
            score = -5; // subtract 5 for hitting target
            // undershoot
        } else if (handTotal < target) {
            score = target - handTotal;
        } else {
            // overshoot (penalty x2)
            score = 2 * (handTotal - target);
        }
    }

    // display player's hand
    public void displayHand() {
        System.out.println(name + "'s hand:");
        for (int i = 0; i < cardCount; i++) {
            System.out.println(" " + hand[i]);
        }
    }

    // Accessor (getter) methods --

    // getter method for name
    public String getName() {
        return name;
    }

    // getter method for score
    public int getScore() {
        return score;
    }

    // getter method for card count
    public int getCardCount() {
        return cardCount;
    }

    // getter method for hand
    public Card[] getHand() {
        return hand;
    }
}
