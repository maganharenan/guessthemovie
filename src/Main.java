import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Try to guess the name of the movie\n");
        System.out.println("Rules: ");
        System.out.println("You have ten shots to try to discover the movie name");
        System.out.println("You can guess one letter a time");
        System.out.println("If you type more than one character only the first will be read");

        while (!game.youWon && game.getScorePointsRemaining() > 0) {
            System.out.println(game.getHiddenMovieName());

            System.out.println("Enter a letter:");
            String letter = "" + scanner.next().charAt(0);

            System.out.println("Your guess is: " + letter);
            game.handleUserInput(letter);

            game.checkIfPlayerWon();
        }

        if (game.youWon) {
            System.out.println("Congratulations!!! You won!!!");
        } else {
            System.out.println("Ehh... You lose :(");
            System.out.println("The movie name was: "+game.getMovieName());
        }
    }
}
