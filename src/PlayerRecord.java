public class PlayerRecord {
    // instance variables
    private String name;
    private int totalMatches;
    private int totalWins;

    // PlayerRecord constructor
    public PlayerRecord(String name) {
        // name as parameter
        // default values for total wins and matches
        this.name = name;
        this.totalMatches = 0;
        this.totalWins = 0;
    }

    // method to record match result
    public void recordMatch(boolean won) {
        totalMatches++;
        if (won) {
            totalWins++;
        }
    }

    // Accessor (getter) methods --

    // getter method for name
    public String getName() {
        return name;
    }

    // getter method for totalMatches
    public int getTotalMatches() {
        return totalMatches;
    }

    // getter method for totalWins
    public int getTotalWins() {
        return totalWins;
    }

    // method to display player stats
    public void displayStats() {
        System.out.println(name + " Wins: " + totalWins +
                ", Matches: " + totalMatches);
    }

    // toString method
    @Override
    public String toString() {
        return name + " - Wins: " + totalWins;
    }
}
