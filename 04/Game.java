import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Integer> numbers;
    private final List<Board> boards;

    public Game() {
        this.numbers = new ArrayList<>();
        this.boards = new ArrayList<>();
    }

    public void readNumbers(BufferedReader br) {
        try {
            String line = br.readLine();
            String[] tokens = line.split(",");

            //add drawn numbers
            for (String s : tokens) {
                int i = Integer.parseInt(s);
                this.numbers.add(i);
            }

            //expect blank line after numbers
            br.readLine();
        } catch (IOException ignored) {
        }
    }

    public void readBoards(BufferedReader br) {
        while (true) {
            try {
                Board b = Board.readBoard(br);
                if (b == null) {
                    break;
                }

                this.boards.add(b);
            } catch(IOException ioe) {
                break;
            }
        }

        System.out.println(this.boards);
    }
}