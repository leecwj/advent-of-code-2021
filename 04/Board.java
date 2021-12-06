import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int ROWS = 5;

    private final List<List<Integer>> rows;

    public Board() {
        this.rows = new ArrayList<>();
    }

    public void addRow(List<Integer> row) {
        this.rows.add(row);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Board(");
        for (List<Integer> row : this.rows) {
            sb.append(row);
        }
        sb.append(")");

        return sb.toString();
    }

    private static List<Integer> readBoardLine(BufferedReader br) throws IOException {
        List<Integer> result = new ArrayList<>();

        String line = br.readLine();
        if (line == null) {
            return null;
        }

        String[] tokens = line.split(" ");

        //add numbers to list
        for (String s : tokens) {
            try {
                int i = Integer.parseInt(s);
                result.add(i);
            } catch (NumberFormatException ignored) {
                //ignore tokens caused by extra whitespace
            }
        }

        return result;
    }

    public static Board readBoard(BufferedReader br) throws IOException {

        Board b = new Board();
        for (int i = 0; i < Board.ROWS; i++) {
            List<Integer> row = Board.readBoardLine(br);
            if (row == null) {
                return null;
            }

            b.addRow(row);
        }

        //expect blank line after numbers
        br.readLine();

        return b;
    }
}