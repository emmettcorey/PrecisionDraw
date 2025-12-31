import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PrecisionDraw game = new PrecisionDraw();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Precision Draw");
        System.out.println("-------------------------");

        while(true) {
            // display leaderboard options
            System.out.println("\n1. Play Match");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Search Player History");
            System.out.println("4. Compare Two Players");
            System.out.println("5. List Players With >X Wins");
            System.out.println("6. Exit");

            // prompt user for choice
            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                switch (choice) {
                    case 1:
                        game.playMatch();
                        break;
                    case 2:
                        game.displayLeaderboard();
                        break;
                    case 3:
                        game.searchPlayerHistory();
                        break;
                    case 4:
                        game.compareTwoPlayers();
                        break;
                    case 5:
                        game.listPlayersWithMinWins();
                        break;
                    case 6:
                        System.out.println("Exiting");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number");
                scanner.nextLine();
            }
        }
    }
}
