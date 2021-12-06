import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Game game = new Game();

        game.readNumbers(br);
        game.readBoards(br);

        System.out.println(game.getWinningScore());
    }
}