import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlgorithmSkyscrapper {

    public static List<HashMap<Integer, List<Integer>>> solutions = new ArrayList<>();
    private static long iterations = 0;

    public static List<Solution> skyscrapperBacktracking(HashMap<Integer, List<Integer>> board) {

        double start = Utils.geetcurrentTimeMillis();

        go(board, 1, 1);

        Board.showBoard(GAME_NAME.SKYSCRAPPER, board);

        double duration = Utils.measureTime(start);
        System.out.println(duration +"ms");
        System.out.println(iterations +" iterations");
        return null;

    }

    private static void go(HashMap<Integer, List<Integer>> board, int row, int col) throws IllegalStateException {
iterations++;
        if (checkAllConstraints(board) || AlgorithmFutoshiki.isBoardFilled(board)){
            solutions.add(board);
            Board.showBoard(GAME_NAME.SKYSCRAPPER, board);

            return;
        }
        for (int j = 1; j < board.size() + 1; j++) {
            if (correctAssignment(row, col, j, board) /*&& !AlgorithmFutoshiki.isBoardFilled(board)*/) {
                board.get(row).set(col, j);

                int[] newField = AlgorithmFutoshiki.selectNextField(row, col, board);
                if (newField[0] != -1) {
                    go(board, newField[0], newField[1]);
                }
                    /*if (AlgorithmFutoshiki.isBoardFilled(board)) {
                        //              Board.showBoard(GAME_NAME.FUTOSHIKI);

                        return;
                    }*/
                if (checkAllConstraints(board) || AlgorithmFutoshiki.isBoardFilled(board)) {
                    solutions.add(board);

                 //   Board.showBoard(GAME_NAME.SKYSCRAPPER, board);

                    return;
                }
                board.get(row).set(col, 0);

            }


        }


    }

    static boolean correctAssignment(int row, int column, int value, HashMap<Integer, List<Integer>> currentBoard) {
        return checkConstraints(row, column, currentBoard, value) && AlgorithmFutoshiki.checkUniqueRowAndColumnVal2(row, column, value, currentBoard);
    }

    private static boolean checkConstraints(int row, int column, HashMap<Integer, List<Integer>> currentBoard, int value) {

        List<List<Integer>> constr = Board.getSkyScrapperConstraints();
        return singleConstraint(constr.get(0), CONSTRAINTS.TOP, row, column, currentBoard, value) && singleConstraint(constr.get(1), CONSTRAINTS.BOTTOM, row, column, currentBoard, value) &&
                singleConstraint(constr.get(2), CONSTRAINTS.LEFT, row, column, currentBoard, value) && singleConstraint(constr.get(3), CONSTRAINTS.RIGHT, row, column, currentBoard, value);


    }

    private static boolean singleConstraint(List<Integer> list, CONSTRAINTS constr, int row, int column, HashMap<Integer, List<Integer>> currentBoard, int value) {

        int numOfVisibles = 1;
        HashMap<Integer, List<Integer>> mockedBoard = new HashMap<>();
        for (Integer i : currentBoard.keySet()) {
            mockedBoard.put(i, new ArrayList<>(currentBoard.get(i)));
        }
        mockedBoard.get(row).set(column, value);

        if(!AlgorithmFutoshiki.isBoardFilled(mockedBoard)) {


            if (constr.equals(CONSTRAINTS.LEFT)) {
                if (list.get(row - 1) != 0 && notAssignedRow(mockedBoard, row) == 0) {
                    int max = mockedBoard.get(row).get(1);
                    for (int i = 2; i < list.size() + 1; i++) {
                        if (max < mockedBoard.get(row).get(i)) {
                            max = mockedBoard.get(row).get(i);
                            numOfVisibles++;
                        }
                    }
                    if (numOfVisibles != list.get(row - 1)) {
                        return false;
                    }
                }

            }

            if (constr.equals(CONSTRAINTS.RIGHT)) {
                if (list.get(row - 1) != 0 && notAssignedRow(mockedBoard, row) == 0) {

                    int max = mockedBoard.get(row).get(list.size());
                    for (int i = list.size() - 1; i > 0; i--) {
                        if (max < mockedBoard.get(row).get(i)) {
                            max = mockedBoard.get(row).get(i);
                            numOfVisibles++;
                        }
                    }
                    if (numOfVisibles != list.get(row - 1)) {
                        return false;
                    }

                }
            }


            if (constr.equals(CONSTRAINTS.TOP)) {
                if (list.get(column - 1) != 0 && notAssignedColumn(mockedBoard, column) == 0) {
                    int max = mockedBoard.get(1).get(column);
                    for (int i = 2; i < mockedBoard.size() + 1; i++) {
                        if (max < mockedBoard.get(i).get(column)) {
                            max = mockedBoard.get(i).get(column);
                            numOfVisibles++;
                        }
                    }
                    if (numOfVisibles != list.get(column - 1)) {
                        return false;
                    }
                }
            }


            if (constr.equals(CONSTRAINTS.BOTTOM )) {
                if (list.get(column - 1) != 0 && notAssignedColumn(mockedBoard, column) == 0) {
                    int max = mockedBoard.get(mockedBoard.get(row).size() - 1).get(column);
                    for (int i = mockedBoard.get(row).size() - 2; i > 0; i--) {
                        if (max < mockedBoard.get(i).get(column)) {
                            max = mockedBoard.get(i).get(column);
                            numOfVisibles++;
                        }
                    }


                    if (numOfVisibles != list.get(column - 1)) {
                        return false;
                    }
                }
            }
            return true;
        }
        else {

            if (constr.equals(CONSTRAINTS.LEFT)) {
                if(list.get(row - 1) != 0) {
                    int max = mockedBoard.get(row).get(1);
                    for (int i = 2; i < list.size() + 1; i++) {
                        if (max < mockedBoard.get(row).get(i)) {
                            max = mockedBoard.get(row).get(i);
                            numOfVisibles++;
                        }
                    }
                    if (numOfVisibles != list.get(row - 1)) {
                        return false;
                    }
                }

            }

            if (constr.equals(CONSTRAINTS.RIGHT)) {
                if(list.get(row - 1) != 0) {

                    int max = mockedBoard.get(row).get(list.size());
                    for (int i = list.size() - 1; i > 0; i--) {
                        if (max < mockedBoard.get(row).get(i)) {
                            max = mockedBoard.get(row).get(i);
                            numOfVisibles++;
                        }
                    }
                    if (numOfVisibles != list.get(row - 1) ) {
                        return false;
                    }

                }
            }


            if (constr.equals(CONSTRAINTS.TOP)) {
                if(list.get(column - 1) != 0) {
                    int max = mockedBoard.get(1).get(column);
                    for (int i = 2; i < currentBoard.size() + 1; i++) {
                        if (max < mockedBoard.get(i).get(column)) {
                            max = mockedBoard.get(i).get(column);
                            numOfVisibles++;
                        }

                    }
                    if (numOfVisibles != list.get(column - 1) ) {
                        return false;
                    }
                }
            }


            if (constr.equals(CONSTRAINTS.BOTTOM)) {
                if(list.get(column - 1) != 0) {
                    int max = mockedBoard.get(list.size()).get(column);
                    for (int i = list.size() - 1; i > 0; i--) {
                        if (max < mockedBoard.get(i).get(column)) {
                            max = mockedBoard.get(i).get(column);
                            numOfVisibles++;
                        }
                    }


                    if (numOfVisibles != list.get(column - 1) ) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private static int notAssignedColumn(HashMap<Integer, List<Integer>> currentBoard, int column) {
        int num = 0;
        for (int i = 1; i < currentBoard.size()+1; i++) {
            if(currentBoard.get(i).get(column)==0){
                num++;
            }

        }
        return num;
    }

    private static int notAssignedRow(HashMap<Integer, List<Integer>> currentBoard, int row) {
        int num = 0;
        for (int i = 1; i < currentBoard.size()+1; i++) {
            if(currentBoard.get(row).get(i)==0){
                num++;
            }

        }
        return num;
    }

     static boolean checkAllConstraints(HashMap<Integer, List<Integer>> currentBoard) {

        if (AlgorithmFutoshiki.isBoardFilled(currentBoard)) {

            for (int i = 1; i < currentBoard.size() + 1; i++) {
                for (int j = 1; j < currentBoard.size() + 1; j++) {
                    if(i == currentBoard.size() && j == currentBoard.size())
                        return true;
                    if (!checkConstraints(i, j, currentBoard, currentBoard.get(i).get(j))) {
                 //   if (!correctAssignment(i, j, currentBoard.get(i).get(j), currentBoard)) {
                        return false;
                    }

                }
            }
            return true;
        } else
            return false;


    }

    public static List<Solution> skyscrapperForwardchecking(HashMap<Integer,List<Integer>> board) {

        double start = Utils.geetcurrentTimeMillis();

        HashMap<Integer, List<List<Integer>>> domains = new HashMap<>();
        domains.put(0,new ArrayList<>());
        for (int i = 1; i < board.size() + 1; i++) {
            List<List<Integer>> domains1 = new ArrayList<List<Integer>>();
            domains1.add(new ArrayList<>());
            for (int j = 1 ; j < board.size() + 1; j++) {
                List<Integer> domainValues = new ArrayList<>();
                domainValues.add(-1);
                for (int k = 1; k < board.size() + 1; k++) {
                    domainValues.add(k);
                }
                domains1.add(domainValues);
            }
            domains.put(i, domains1);
        }

        if (board.get(1).get(1) != 0) {
            int[] coords = AlgorithmFutoshiki.selectNextField(1, 1, board);
            goForwardSkyScrapper(board, coords[0], coords[1], domains);
        } else {
            goForwardSkyScrapper(board, 1, 1, domains);
        }

        double duration = Utils.measureTime(start);
        System.out.println(duration +"ms");
        System.out.println(iterations +" iterations");

        Board.showBoard(GAME_NAME.FUTOSHIKI, board);
        return null;


    }

    private static void goForwardSkyScrapper(HashMap<Integer,List<Integer>> board, int row, int col, HashMap<Integer,List<List<Integer>>> domains) {

        iterations++;
        if (AlgorithmFutoshiki.isBoardFilled(board)) {
            //Board.showBoard(GAME_NAME.FUTOSHIKI);
            return;
        }
        for (int i = 1; i < domains.size(); i++) {
            if (correctAssignment(row, col, i, board)) {
                board.get(row).set(col, i);

                if (!AlgorithmFutoshiki.recalcDomain(board, row, col, domains, i))
                    return;
                int[] newField = AlgorithmFutoshiki.selectNextField(row, col, board);
                if (newField[0] != -1)
                    goForwardSkyScrapper(board, newField[0], newField[1], domains);
                if (AlgorithmFutoshiki.isBoardFilled(board)) {
                    //    Board.showBoard(GAME_NAME.FUTOSHIKI, board);

                    return;

                }

                board.get(row).set(col, 0);



            }else{
                AlgorithmFutoshiki.putValueInDomainAgain(board, row, col, domains, i);
                board.get(row).set(col, 0);
            }
        }
    }

/*
            for (int i = 1; i < currentBoard.size()+1; i++) {

                int constraintVisibles = list.get(i-1);
int maxHeight = currentBoard.get(row).get(column)
                for (int j = 0; j < currentBoard.size(); j++) {

                    if(currentBoard.get(row).get(column) > maxHeight){
                        numOfVisibles++;
                        ma
                    }

                }

 }}

            }*/


}
