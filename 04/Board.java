import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    public static final int ROWS = 5;
    public static final int COLUMNS = 5;

    private final List<List<Integer>> rows;
    private final List<List<Boolean>> marks;

    public Board() {
        this.rows = new ArrayList<>();
        this.marks = new ArrayList<>();
    }

    public void addRow(List<Integer> row) {
        this.rows.add(row);

        List<Boolean> markRow = row.stream().map((__) -> false).collect(Collectors.toList());
        this.marks.add(markRow);
    }

    public void mark(int number) {
        for (int i = 0; i < this.rows.size(); i++) {
            for (int j = 0; j < this.rows.get(i).size(); j++) {
                if (this.rows.get(i).get(j) == number) {
                    this.marks.get(i).set(j, true);
                }
            }
        }
    }

    private boolean hasWonRow() {
        for (int i = 0; i < this.rows.size(); i++) {
            if (!this.marks.get(i).contains(false)) {
                return true;
            }
        }

        return false;
    }

    private boolean columnHasWon(int j) {
        for (int i = 0; i < this.rows.size(); i++) {
            if (!this.marks.get(i).get(j)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasWonColumn() {
        //assume that the number of columns is fixed
        for (int j = 0; j < Board.COLUMNS; j++) {
            if (columnHasWon(j)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasWon() {
        return hasWonColumn() || hasWonRow();
    }

    public int getScore(int called) {
        int sum = 0;

        for (int i = 0; i < this.rows.size(); i++) {
            for (int j = 0; j < this.rows.get(i).size(); j++) {
                if (!this.marks.get(i).get(j)) {
                    sum = sum + this.rows.get(i).get(j);
                }
            }
        }

        return sum * called;
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