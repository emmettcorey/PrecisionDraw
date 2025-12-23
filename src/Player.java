public class Player {
    // instance variables
    private String name;
    private Card[] hand;
    private int cardCount;
    private int score;
    private int cumulativeScore;

    // player constructor
    public Player (String name) {
        this.name = name;
        this.hand = new Card[52];
        this.cardCount = 0;
        this.score = 0;
        this.cumulativeScore = 0;
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
    // level 3 - update method to use optimisation
    public void calculateScore(int target) {
        int handTotal = calculateOptimisedTotal(target); // updated
        score = calculateScoreForTotal(handTotal, target); // simplified method to call helper method
    }

    // display player's hand
    public void displayHand() {
        System.out.println(name + "'s cards:");
        for (int i = 0; i < cardCount; i++) {
            System.out.print(" " + hand[i]);
        }
        System.out.println();
    }

    // add round score to cumulative score
    public void addRoundScore() {
        cumulativeScore += score;
    }

    // ace optimisation method
    public int calculateOptimisedTotal(int target) {
        int baseTotal = 0;
        int aceCount = 0;

        for (int i = 0; i < cardCount; i++) {
            baseTotal += hand[i].getValue();
            if (hand[i].getRank().equals("Ace")) {
                aceCount++;
            }
        }

        // if no aces, return base total
        if (aceCount == 0) {
            return baseTotal;
        }

        int bestTotal = baseTotal;
        int bestScore = calculateScoreForTotal(baseTotal, target);

        // for each ace, try converting it from 11 to 1
        for (int i = 1; i <= aceCount; i++) {
            int adjustedTotal = baseTotal - (i * 10);
            int adjustedScore = calculateScoreForTotal(adjustedTotal, target);

            if (adjustedScore < bestScore) {
                bestScore = adjustedScore;
                bestTotal = adjustedTotal;
            }
        }
        return bestTotal;
    }

    // helper method to calculate score based on total
    private int calculateScoreForTotal(int total, int target) {
        // perfect score
        if (total == target) {
            return -5;
            // undershoot
        } else if (total < target) {
            return target - total;
            // overshoot
        } else {
            return 2 * (total - target);
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

    // getter method for cumulativeScore
    public int getCumulativeScore() {
        return cumulativeScore;
    }
}
