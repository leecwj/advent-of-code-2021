import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static final int MODE = 2;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Game game = new Game();

        game.readNumbers(br);
        game.readBoards(br);

        if (MODE == 1) {
            // Print part 1 answer
            System.out.println(game.getWinningScore());
        } else if (MODE == 2) {
            // Print part 2 answer
            System.out.println(game.getLastWinningScore());
        }
    }
}