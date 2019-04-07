
import java.util.*;

//@Slf4j
public class Board {

    int size;

    private static HashMap<Integer, List<Integer>> board;
    private static List<List<Integer>> skyScrapperConstraints;

    public static HashMap<Integer, List<Integer>> getBoard() {
        return board;
    }

    public static void fillBoard(List<List<Integer>> columns, GAME_NAME game_name) {
        HashMap<Integer, List<Integer>> tmp = new HashMap<>();
        if (game_name.equals(GAME_NAME.FUTOSHIKI)) {
            int rows = 1;
            for (List<Integer> col : columns) {
                tmp.put(rows, col);
                rows++;
            }
        } else {
            for (int i = 1; i < columns.size() + 1; i++) {
                tmp.put(i, new ArrayList<>(Collections.nCopies(columns.size(), 0)));
            }
            skyScrapperConstraints = columns;
        }

        board = tmp;
    }


    public Board(int size) {
        this.size = size;
    }

    public static void showBoard(GAME_NAME game_name) {
        System.out.println(horizontal(game_name, false));
        for (int i = 1; i < board.size() + 1; i++) {
            System.out.println(vertical(game_name, i, false) + Arrays.toString(board.get(i).toArray()) + " " + vertical(game_name, i, true));
        }
        System.out.println(horizontal(game_name, true));

    }

    private static String vertical(GAME_NAME game_name, int i, boolean right) {
        if (game_name.equals(GAME_NAME.FUTOSHIKI)) {
            return ROWS.values()[i] + " ";
        } else {
            int getId = 3;
            if (right)
                getId = 2;
            return skyScrapperConstraints.get(getId).get(i - 1) + " ";
        }
    }

    private static String horizontal(GAME_NAME game_name, boolean last) {
        StringBuilder columns = new StringBuilder("   ");
        if (game_name.equals(GAME_NAME.FUTOSHIKI)) {
            for (int i = 1; i < board.size() + 1; i++) {
                columns.append(i).append("  ");
            }
            return columns.toString();
        } else {
            int getId = 0;
            if (last)
                getId = 1;
            for (Integer i : skyScrapperConstraints.get(getId)) {
                columns.append(i).append("  ");
            }
            return columns.toString();
        }

    }

    public int getSize() {
        return size;
    }

    public static List<List<Integer>> getSkyScrapperConstraints() {
        return skyScrapperConstraints;
    }
}
