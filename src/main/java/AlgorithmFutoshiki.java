import java.util.*;

public class AlgorithmFutoshiki {

    public static List<Constraint> constraints = new ArrayList<>();
    private static long iterations = 0;

    public static List<Solution> futoshikiBacktracking(HashMap<Integer, List<Integer>> board) {
        double start = Utils.geetcurrentTimeMillis();

        if (board.get(1).get(1) != 0) {
            int[] coords = selectNextField(1, 1, board);
            go(board, coords[0], coords[1]);
        } else {
            go(board, 1, 1);
        }

        Board.showBoard(GAME_NAME.FUTOSHIKI, board);
        double duration = Utils.measureTime(start);
        System.out.println(duration +"ms");
        System.out.println(iterations +" iterations");

        return null;

    }

    public static List<Solution> futoshikiForwardchecking(HashMap<Integer, List<Integer>> board) {
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
            int[] coords = selectNextField(1, 1, board);
            goForward(board, coords[0], coords[1], domains);
        } else {
            goForward(board, 1, 1, domains);
        }

        double duration = Utils.measureTime(start);
        System.out.println(duration +"ms");
        System.out.println(iterations +" iterations");

        Board.showBoard(GAME_NAME.FUTOSHIKI, board);
        return null;

    }

    /** stare */
    private static void go(HashMap<Integer, List<Integer>> board, int row, int col) {
    iterations++;
        if (checkAllConstraints(board) ) {
            //Board.showBoard(GAME_NAME.FUTOSHIKI);
            return;
        }

        for (int j = 1; j < board.size() + 1; j++) {
            if (Board.getBoard().get(row).get(col) == 0 /*&& selectNextField(row, col, board)[0] != -1*/) {
                if (correctAssignment(row, col, j, board)) {
                    board.get(row).set(col, j);

                    int[] newField = selectNextField(row, col, board);
                    if (newField[0] != -1) {
                        go(board, newField[0], newField[1]);
                    }

                    if (checkAllConstraints(board)) {
                        //Board.showBoard(GAME_NAME.FUTOSHIKI);
                        //  Board.showBoard(GAME_NAME.FUTOSHIKI, board);
                        return;
                    }

                    // if (Board.getBoard().get(newField[0]).get(newField[1]) == 0)
                    board.get(row).set(col, 0);


                }
            } else {
                board.get(row).set(col, Board.getBoard().get(row).get(col));

                int[] newField = selectNextField(row, col, board);
                if (newField[0] != -1) {
                    go(board, newField[0], newField[1]);
                }

                  /*   if (Board.getBoard().get(newField[0]).get(newField[1]) == 0)
                         board.get(row).set(col, 0);*/
            }
           /* else{
                int[] newField = selectNextField(row, col, board);
                if (newField[0] != -1)
                    go(board, newField[0], newField[1]);
                if (isBoardFilled(board)) {
                    //              Board.showBoard(GAME_NAME.FUTOSHIKI);
                    return;
                }
                }*/
        }


    }


   /* private static void go(HashMap<Integer, List<Integer>> board, int row, int col) {
iterations++;
        if (checkAllConstraints(board) ) {
            Board.showBoard(GAME_NAME.FUTOSHIKI, board);
            return;
        }

        for (int j = 1; j < board.size() + 1; j++) {
            if (Board.getBoard().get(row).get(col) == 0 && selectNextField(row, col, board)[0] != -1) {
                if (correctAssignment(row, col, j, board)) {
                    board.get(row).set(col, j);

                    int[] newField = selectNextField(row, col, board);
                    if (newField[0] != -1) {
                        go(board, newField[0], newField[1]);
                        }

                     else if (checkAllConstraints(board) ) {
                        //Board.showBoard(GAME_NAME.FUTOSHIKI);
                      //  Board.showBoard(GAME_NAME.FUTOSHIKI, board);

                        return;
                    }

                    // if (Board.getBoard().get(newField[0]).get(newField[1]) == 0)
                    board.get(row).set(col, 0);


                }
            } else if(Board.getBoard().get(row).get(col) == 0 && selectNextField(row, col, board)[0] == -1){
                int[] newField = selectNextField(row, col, board);
                if (newField[0] != -1) {
                    go(board, newField[0], newField[1]);
                }
                if (checkAllConstraints(board) ) {
                    //Board.showBoard(GAME_NAME.FUTOSHIKI);
                    //  Board.showBoard(GAME_NAME.FUTOSHIKI, board);
System.out.println("nie dziala");
                    return;
                }
              //  if (Board.getBoard().get(row).get(col) == 0)
                    board.get(row).set(col, 0);


                // if (Board.getBoard().get(newField[0]).get(newField[1]) == 0)


            } else {

            }
            }


           /* else{
                int[] newField = selectNextField(row, col, board);
                if (newField[0] != -1)
                    go(board, newField[0], newField[1]);
                if (isBoardFilled(board)) {
                    //              Board.showBoard(GAME_NAME.FUTOSHIKI);

                    return;
                }
                }*/
        //}




    private static boolean checkAllConstraints(HashMap<Integer, List<Integer>> currentBoard) {

      //  Board.showBoard(GAME_NAME.FUTOSHIKI, currentBoard);
        if (isBoardFilled(currentBoard)) {

            for (int i = 1; i < currentBoard.size() +1 ; i++) {
                for (int j = 1; j < currentBoard.size() + 1 ; j++) {
                    if (!(checkConstraints(i, j, currentBoard.get(i).get(j), currentBoard))) {
                        return false;
                    }

                }
            }

            return true;
        } else
            return false;


    }



    private static void goForward(HashMap<Integer, List<Integer>> board, int row, int col, HashMap<Integer, List<List<Integer>>> domains) {

        iterations++;

        if (isBoardFilled(board)) {
            //Board.showBoard(GAME_NAME.FUTOSHIKI);
            return;
        }

            for (int i = 1; i < domains.size(); i++) {

            if (Board.getBoard().get(row).get(col) == 0) {
                if (correctAssignment(row, col, i, board)) {
                    board.get(row).set(col, i);
if(!recalcDomain(board, row,col, domains, i))
    return;


    int[] newField = selectNextField(row, col, board);
    if (newField[0] != -1)
        goForward(board, newField[0], newField[1], domains);
    if (isBoardFilled(board)) {
                  //    Board.showBoard(GAME_NAME.FUTOSHIKI, board);

        return;

}
                    board.get(row).set(col, 0);
                } else {
                    putValueInDomainAgain(board,row,col,domains,i);
                    board.get(row).set(col, 0);
                }
            } else {
                board.get(row).set(col, Board.getBoard().get(row).get(col));

                if(!recalcDomain(board, row,col, domains, Board.getBoard().get(row).get(col)))
                    return;


                int[] newField = selectNextField(row, col, board);
                if (newField[0] != -1)
                    goForward(board, newField[0], newField[1], domains);
                if (isBoardFilled(board)) {
                    //    Board.showBoard(GAME_NAME.FUTOSHIKI, board);

                    return;

                }
                board.get(row).set(col, 0);
            }
           /* else{
                int[] newField = selectNextField(row, col, board);
                if (newField[0] != -1)
                    go(board, newField[0], newField[1]);
                if (isBoardFilled(board)) {
                    //              Board.showBoard(GAME_NAME.FUTOSHIKI);

                    return;
                }
                }*/
        }


    }

    static boolean recalcDomain(HashMap<Integer, List<Integer>> board, int row, int col, HashMap<Integer, List<List<Integer>>> domains, int value) {
        return recaldVertically(board, row, col, domains, value) &&
        recalcHorizontally(board, row, col, domains, value);
    }

    static boolean putValueInDomainAgain(HashMap<Integer, List<Integer>> board, int row, int col, HashMap<Integer, List<List<Integer>>> domains, int value) {
        return putVertically(board, row, col, domains, value) &&
                putHorizontally(board, row, col, domains, value);

    }

    private static boolean putVertically(HashMap<Integer,List<Integer>> board, int row, int col, HashMap<Integer,List<List<Integer>>> domains, int value) {

        for (int i = 1; i < domains.size()  ; i++) {
            domains.get(i).get(col).add(value);
            }
    return true;

    }

    private static boolean putHorizontally(HashMap<Integer,List<Integer>> board, int row, int col, HashMap<Integer,List<List<Integer>>> domains, int value) {

        for (int i = 1; i < domains.size()  ; i++) {
            domains.get(row).get(i).add(value);
        }
        return true;

    }

    private static boolean recalcHorizontally(HashMap<Integer,List<Integer>> board, int row, int col, HashMap<Integer,List<List<Integer>>> domains, int value) {
        for (int i = 1; i < domains.size()  ; i++) {
            if(domains.get(row).get(i).contains(-1) && domains.get(row).get(i).size() == 1 && !(row == domains.size() -1)){
                return false;
            }
            domains.get(row).get(i).remove(Integer.valueOf(value));
        }
        return true;
    }

    private static boolean recaldVertically(HashMap<Integer,List<Integer>> board, int row, int col, HashMap<Integer,List<List<Integer>>> domains, int value) {
        for (int i = 1; i < domains.size(); i++) {
            if(domains.get(i).get(col).contains(-1) && domains.get(i).get(col).size() == 1 && !(row == domains.size() -1)){
                return false;
            }
            domains.get(i).get(col).remove(Integer.valueOf(value));

        }
        return true;

    }


    private static boolean isAnyDomainEmpty(HashMap<Integer, List<List<Integer>>> domain, HashMap<Integer, List<Integer>> currentBoard, int rows, int cols) {
        for (int i = 1; i < currentBoard.size() + 1; i++) {
            for (int j = 1; j < currentBoard.size() + 1; j++) {

                if (domain.get(i).get(j).isEmpty()) {
return false;
                }

            }
        }
        return true;
    }




        public static int[] selectNextField(int row, int column, HashMap<Integer, List<Integer>> currentBoard) {
        int newRow = 1;
        int newCol = 1;


        if (row == currentBoard.size() && column == currentBoard.size()) {
            return new int[]{-1, -1};
        }

        if (column == currentBoard.size()) {
            newCol = 1;
            newRow = row + 1;
        } else {
            newCol = column + 1;
            newRow = row;
        }

        if (currentBoard.get(newRow).get(newCol) != 0 || Board.getBoard().get(newRow).get(newCol) != 0) {
            selectNextField(newRow, newCol, currentBoard);
        }
/*
        for (int i = newRow; i < currentBoard.size() +1; i++) {
            for (int j = newCol; j < currentBoard.size()+1; j++) {
                if(                currentBoard.get(i).get(j) == 0  ){
                    return new int[]{i, j};
                }

            }

        }
        return new int[]{-1,-1};
  */
        return new int[]{newRow, newCol};
    }

    public static boolean isBoardFilled(HashMap<Integer, List<Integer>> board) {
        for (Integer i : board.keySet()) {
            if (board.get(i).contains(0))
                return false;
        }
        return true;
    }

    private static boolean correctAssignment(int row, int column, int value, HashMap<Integer, List<Integer>> currentBoard) {
        return checkConstraints(row, column, value, currentBoard) && checkUniqueRowAndColumnVal2(row, column, value, currentBoard);
    }

    private static boolean checkConstraints(int row, int column, int value, HashMap<Integer, List<Integer>> currentBoard) {

        for (Constraint c : AlgorithmFutoshiki.constraints) {
            int iter = 0;
            int mapNum = 1;
            int[] constr = new int[4];
            for (int i = mapNum; i < 3; i++) {
                Integer e = (Integer) c.constr.get(mapNum).keySet().toArray()[0];
                Integer val = (Integer) c.constr.get(mapNum).values().toArray()[0];

                constr[iter] = e;
                constr[++iter] = val;
                iter++;
                ++mapNum;

            }




/*            for (Map.Entry e : c.constr.get(mapNum).entrySet()) {
  /*              if ((Integer) e.getKey() == row && (Integer) e.getValue() == column) {  //jesli dokladnie ta komorka jest pierwsza w mapie constrainów
                    if(iter == 0)
                    return value > currentBoard.get(e.getKey()).get((Integer) e.getValue());
                    else
                        return value < currentBoard.get(e.getKey()).get((Integer) e.getValue());

                } else {
                    iter++;
                }
*/
/*                constr[iter] = (Integer) e.getKey();
                constr[++iter] = (Integer) e.getValue();
                iter++;
                ++mapNum;
                System.out.println(c.constr.entrySet().size()+" entry size");

            }
*/


            if (constr[0] == row && constr[1] == column && currentBoard.get(constr[2]).get(constr[3]) != 0) {                //TODO bylo na odwrot
                return value < currentBoard.get(constr[2]).get(constr[3]);
            } else if (constr[2] == row && constr[3] == column && currentBoard.get(constr[0]).get(constr[1]) != 0) {                //mniejsze pole
                return value > currentBoard.get(constr[0]).get(constr[1]);
            }
      /*
            if (currentBoard.get(constr[0]).get(constr[1]) > value)
                return true;

            else if (currentBoard.get(constr[2]).get(constr[3]) < value)
return true;
            else return false;

        }
        return false;
*/


        }
        return true;
    }

    public static boolean checkUniqueRowAndColumnVal(int row, int column, int value, HashMap<Integer, List<Integer>> currentBoard) {

        Set<Integer> valuesInRow = new HashSet<>(currentBoard.get(row));
        Set<Integer> valuesInColumn = new HashSet<>();
        for (int i = 1; i < currentBoard.size() + 1; i++) {
            valuesInColumn.add(currentBoard.get(i).get(column));
        }
        return !valuesInRow.contains(value) && !valuesInColumn.contains(value);


    }


    public static boolean checkUniqueRowAndColumnVal2(int row, int column, int value, HashMap<Integer, List<Integer>> currentBoard) {
List<Integer> valuesInRow = new ArrayList<>();
List<Integer> valuesInColumn = new ArrayList<>();
        for (int i = 1; i < currentBoard.get(i).size() - 1; i++) {
            valuesInRow.add(currentBoard.get(row).get(i));
        }
        for (int i = 1; i < currentBoard.size() + 1; i++) {
            valuesInColumn.add(currentBoard.get(i).get(column));
        }
        return !valuesInRow.contains(value) && !valuesInColumn.contains(value);


    }

}
