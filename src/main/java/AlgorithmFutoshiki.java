import java.util.*;

public class AlgorithmFutoshiki {

    public static List<Constraint> constraints = new ArrayList<>();

    public static List<Solution> futoshikiBacktracking(HashMap<Integer, List<Integer>> board) {
        if (board.get(1).get(1) != 0) {
            int[] coords = selectNextField(1, 1, board);
            go(board, coords[0], coords[1]);
        } else {
            go(board, 1, 1);
        }

        Board.showBoard(GAME_NAME.FUTOSHIKI, board);
        return null;

    }

    public static List<Solution> futoshikiForwardchecking(HashMap<Integer, List<Integer>> board) {
        HashMap<Integer, List<Domain>> domains = new HashMap<>();
        for (int i = 0; i < board.size(); i++) {
            List<Domain> domains1 = new ArrayList<Domain>();
            for (int j = 0; j < board.size(); j++) {
                domains1.add(new Domain(board.size()));
            }
            domains.put(i, domains1);
        }

        if (board.get(1).get(1) != 0) {
            int[] coords = selectNextField(1, 1, board);
            goForward(board, coords[0], coords[1], domains);
        } else {
            goForward(board, 1, 1, domains);
        }

        Board.showBoard(GAME_NAME.FUTOSHIKI, board);
        return null;

    }



    private static void go(HashMap<Integer, List<Integer>> board, int row, int col) {

        if (checkAllConstraints(board) || isBoardFilled(board)) {
            //Board.showBoard(GAME_NAME.FUTOSHIKI);
            return;
        }

        for (int j = 1; j < board.size() + 1; j++) {
             if (Board.getBoard().get(row).get(col) == 0) {
                 if (correctAssignment(row, col, j, board)) {
                     board.get(row).set(col, j);

                     int[] newField = selectNextField(row, col, board);
                     if (newField[0] != -1) {
                         go(board, newField[0], newField[1]);

                         if (checkAllConstraints(board) || isBoardFilled(board)) {
                             //Board.showBoard(GAME_NAME.FUTOSHIKI);
                             Board.showBoard(GAME_NAME.FUTOSHIKI, board);

                             return;
                         }
                     }

                    // if (Board.getBoard().get(newField[0]).get(newField[1]) == 0)
                         board.get(row).set(col, 0);


                 } /*else j=1;*/
             }
             else {
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

    private static boolean checkAllConstraints( HashMap<Integer,List<Integer>> currentBoard) {

        if(isBoardFilled(currentBoard)){

            for (int i = 1; i < currentBoard.size()+1; i++) {
                for (int j = 1; j < currentBoard.size()+1; j++) {
                    if(!checkConstraints(i, j, currentBoard.get(i).get(j),currentBoard)){
                        return false;
                    }

                }
            }
            return true;
        } else
            return false;


    }


    private static void goForward(HashMap<Integer, List<Integer>> board, int row, int col, HashMap<Integer, List<Domain>> domains) {



        if (isBoardFilled(board)) {
            //Board.showBoard(GAME_NAME.FUTOSHIKI);
            return;
        }

for(Integer i : domains.get(row).get(col).getFieldDomain()){
            if (Board.getBoard().get(row).get(col) == 0) {
                if (correctAssignment(row, col, i, board)) {
                    board.get(row).set(col, i);
//recalcDomain(board, row,col);
//boolean isCorrect = isDomainEmpty(domain, board, row, col);
                    int[] newField = selectNextField(row, col, board);
                    if (newField[0] != -1)
                        go(board, newField[0], newField[1]);
                    if (isBoardFilled(board)) {
                        //              Board.showBoard(GAME_NAME.FUTOSHIKI);

                        return;
                    }
                }
                board.get(row).set(col, 0);
            }
            else {
                board.get(row).set(col, Board.getBoard().get(row).get(col));

                int[] newField = selectNextField(row, col, board);
                if (newField[0] != -1)
                    go(board, newField[0], newField[1]);
                if (isBoardFilled(board)) {
                    //              Board.showBoard(GAME_NAME.FUTOSHIKI);

                    return;
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
        }


    }


private static boolean isDomainEmpty(Domain domain, HashMap<Integer, List<Integer>> currentBoard){
    for (int i = 0; i < currentBoard.size(); i++) {
        for (int j = 0; j < currentBoard.size(); j++) {

            if(currentBoard.get(i).get(j) == 0){

               // if()

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
        return checkConstraints(row, column, value, currentBoard) && checkUniqueRowAndColumnVal(row, column, value, currentBoard);
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

}
