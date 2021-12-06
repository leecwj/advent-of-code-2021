import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Game {

    private final Queue<Integer> numbers;
    private List<Board> boards;

    public Game() {
        this.numbers = new LinkedList<>();
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
    }

    public int getWinningScore() {
        while (!this.numbers.isEmpty()) {
            int number = this.numbers.remove();

            for (Board b : this.boards) {
                b.mark(number);
                if (b.hasWon()) {
                    return b.getScore(number);
                }
            }
        }

        return -1;
    }

    public int getLastWinningScore() {
        while (!this.numbers.isEmpty()) {
            int number = this.numbers.remove();

            int wonBoards = 0;
            for (Board b : this.boards) {
                b.mark(number);

                if (b.hasWon()) {
                    wonBoards = wonBoards + 1;
                }

                //check if last board has won
                if (wonBoards == this.boards.size()) {
                    return b.getScore(number);
                }
            }

            //remove boards that have won
            this.boards = this.boards.stream().filter((Board b) -> !b.hasWon())
                    .collect(Collectors.toList());
        }

        return -1;
    }
}