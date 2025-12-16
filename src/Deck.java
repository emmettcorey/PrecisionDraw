import java.util.Random;

public class Deck {
    // instance variables
    private Card[] cards;
    private int currentCardIndex;
    private Random random = new Random();

    // deck constructor
    public Deck() {
        this.cards = new Card[52];
        this.currentCardIndex = 0;
        initialiseDeck();
    }

    // helper method to create 52 cards
    private void initialiseDeck() {
        String[] ranks = Card.getRanks();
        String[] suits = Card.getSuits();

        // create card object
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                cards[index] = new Card(rank, suit);
                index++;
            }
        }
    }

    // shuffle method
    public void shuffle() {
        for (int i = cards.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // swap cards
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
        currentCardIndex = 0;
    }

    // method to draw a card
    public Card drawCard() {
        // check for no cards left
        if (currentCardIndex >= cards.length) {
            return null;
        }

        // draw a card
        Card drawnCard = cards[currentCardIndex];
        currentCardIndex++;
        return drawnCard;
    }

    // restock and reshuffle deck
    public void restock() {
        currentCardIndex = 0;
        shuffle();
    }

    // return number of remaining cards
    public int cardsRemaining() {
        return cards.length - currentCardIndex;
    }
}
