public class Card {
    // instance variables
    private final String rank;
    private final String suit;
    private final int value;

    private final static String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private final static String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};

    // card constructor
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        this.value = calculateValue(rank);
    }

    // helper method to calculate the value of a card
    private int calculateValue(String rank) {
        // number cards (2-10)
        try {
            return Integer.parseInt(rank);
        } catch (NumberFormatException e) {
            // face cards (Jack, Queen, King)
            if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
                return 10;
            }
            // ace card
            if (rank.equals("Ace")) {
                return 11;
            }
        }
        return 0;
    }

    // Accessor (getter) methods --

    // getter method for card rank
    public String getRank() {
        return rank;
    }

    // getter method for card suit
    public String getSuit() {
        return suit;
    }

    // getter method for card value
    public int getValue() {
        return value;
    }

    // getter method for RANKS array
    public static String[] getRanks() {
        return RANKS;
    }

    // getter method for SUITS array
    public static String[] getSuits() {
        return SUITS;
    }

    // toString method
    @Override
    public String toString() {
        return rank + " of " + suit + " (value: " + value + ")";
    }

}
