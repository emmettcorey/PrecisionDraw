public class Leaderboard {
    // instance variables
    private PlayerRecord[] players;
    private int playerCount;
    private static final int INITIAL_CAPACITY = 75; // max no. players

    // Leaderboard constructor
    public Leaderboard() {
        this.players = new PlayerRecord[INITIAL_CAPACITY];
        this.playerCount = 0;
    }

    // method to add a new player to leaderboard
    public void addPlayer(String name) {
        if (playerCount < players.length) {
            players[playerCount] = new PlayerRecord(name);
            playerCount++;
        } else {
            System.out.println("Maximum number of players reached");
        }
    }

    // method to find a player
    public PlayerRecord findPlayer(String name) {
        for (int i =0; i < playerCount; i++) {
            if (players[i].getName().equalsIgnoreCase(name)) {
                return players[i];
            }
        }
        return null; // player not found
    }

    // method to get or create player record
    public PlayerRecord getPlayer(String name) {
        PlayerRecord player = findPlayer(name);
        // if player doesn't exist create new player
        if (player == null) {
            addPlayer(name);
            player = findPlayer(name);
        }
        return player;
    }

    // method to check if player exists
    public boolean playerExists(String name) {
        return findPlayer(name) != null;
    }

    // method to sort players by total wins
    public void sortPlayers() {
        for (int i = 0; i < playerCount - 1; i++) {
            for (int j = 0; j < playerCount - i - 1; j++) {
                if (players[j].getTotalWins() < players[j + 1].getTotalWins()) {
                    PlayerRecord temp = players[j];
                    players[j] = players[j +1];
                    players[j + 1] = temp;
                }
            }
        }
    }

    // method to display leaderboard
    public void displayLeaderboard() {
        if (playerCount == 0) {
            System.out.println("No players available to display");
            return;
        }
        // sort before displaying
        sortPlayers();
        System.out.println("\nLeaderboard:");
        for (int i = 0; i < playerCount; i++) {
            System.out.println((i + 1) + ". " + players[i].toString());
        }
    }

    // method to search and display player's history
    public void searchPlayer(String name) {
        PlayerRecord player = findPlayer(name);

        if (player == null) {
            System.out.println(name + " was not found");
        } else {
            System.out.println("\nPlayer History:");
            player.displayStats();
        }
    }

    // method to get total number of players
    public int getPlayerCount() {
        return playerCount;
    }
}
