import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    //Movie list properties
    private String[] movieList;
    private int movieCount;

    //Game properties
    String movieName;
    private int score = 10;
    String rightLetters = "";
    String wrongLetters = "";
    public Boolean youWon = false;

    Game() {
        movieList = new String[500];
        movieCount = 0;

        try {
            loadMoviesList();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        movieName = getRandomMovie();
    }

    public int getScorePointsRemaining() {
        return score;
    }

    private void loadMoviesList() throws FileNotFoundException {
        File file = new File("MovieList.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            movieList[movieCount] = scanner.nextLine();
            movieCount++;
        }
    }

    private int getRandomIndex() {
        return (int) (Math.random() * (movieCount - 1));
    }

    private String getRandomMovie() {
        return movieList[getRandomIndex()];
    }

    public String getMovieName() {
        return movieName;
    }

    public String getHiddenMovieName() {
        return movieName.replaceAll("[a-zA-Z0-9&&[^" + rightLetters + "^]]", "_");
    }

    public void handleUserInput(String letter) {
        if (letter.matches("[a-zA-Z0-9]")) {
            if (rightLetters.contains(letter.toLowerCase()) || wrongLetters.contains(letter.toUpperCase())) {
                System.out.println("You already entered this letter!");
            } else {
                checkIfMovieContainsLetter(letter);
            }
        } else {
            System.out.println("Only letters and numbers are accepted");
        }
    }

    private void checkIfMovieContainsLetter (String letter) {
        if(movieName.contains(letter.toLowerCase())||movieName.contains(letter.toUpperCase())) {
            rightLetters += letter.toLowerCase();
            rightLetters += letter.toUpperCase();
        } else {
            wrongLetters += letter.toLowerCase();
            wrongLetters += letter.toUpperCase();
            reduceScore();
            System.out.println("You guess is wrong :(");
        }
    }

    private void reduceScore() {
        score--;
    }

    public void checkIfPlayerWon() {
        if (getHiddenMovieName().equals(movieName)) {
            youWon = true;
        }
    }
}
