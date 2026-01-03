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
            System.out.println("2. Run Simulation");
            System.out.println("3. View Leaderboard");
            System.out.println("4. Search Player History");
            System.out.println("5. Compare Two Players");
            System.out.println("6. List Players With >X Wins");
            System.out.println("7. Exit");

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
                        game.runSimulation();
                        break;
                    case 3:
                        game.displayLeaderboard();
                        break;
                    case 4:
                        game.searchPlayerHistory();
                        break;
                    case 5:
                        game.compareTwoPlayers();
                        break;
                    case 6:
                        game.listPlayersWithMinWins();
                        break;
                    case 7:
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
