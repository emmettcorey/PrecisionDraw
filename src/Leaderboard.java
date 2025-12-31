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

    // method to compare two players
    public void compareTwoPlayers(String name1, String name2) {
        PlayerRecord player1 = findPlayer(name1);
        PlayerRecord player2 = findPlayer(name2);

        // check if both players exist
        boolean player1Found = player1 != null;
        boolean player2Found = player2 != null;

        if (!player1Found) {
            System.out.println(name1 + " not found");
        }

        if (!player2Found) {
            System.out.println(name2 + " not found");
        }

        // if either player is not found exit method
        if (!player1Found || !player2Found) {
            return;
        }

        // display player comparison
        // AI (Claude AI) was used to create table format to display player comparison
        System.out.println("\n----- Player Comparison -----");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Player", "Match Count", "Wins", "Wins%");
        System.out.printf("%-15s %-15d %-15d %.1f%%%n",
                player1.getName(),
                player1.getTotalMatches(),
                player1.getTotalWins(),
                player1.getWinPercentage());
        System.out.printf("%-15s %-15d %-15d %.1f%%%n",
                player2.getName(),
                player2.getTotalMatches(),
                player2.getTotalWins(),
                player2.getWinPercentage());
        System.out.println("-----------------------------");
    }

    // method to display players with >x wins
    public void listPlayersWithMinWins(int minWins) {
        // sort players
        sortPlayers();

        // count players with more than x wins
        int count = 0;

        for (int i = 0; i < playerCount; i++) {
            if (players[i].getTotalWins() > minWins) {
                count++;
            }
        }

        // check if any players meet criteria
        if (count == 0) {
            System.out.println("\nNo players have more than " + minWins + " wins");
            return;
        }

        // display leaderboard of players with >x wins
        System.out.println("\nPlayers with more than " + minWins + ":");
        int rank = 1;
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getTotalWins() > minWins) {
                System.out.println(rank + ". " + players[i].toString());
                rank++;
            }
        }
    }

    // method to get total number of players
    public int getPlayerCount() {
        return playerCount;
    }
}
