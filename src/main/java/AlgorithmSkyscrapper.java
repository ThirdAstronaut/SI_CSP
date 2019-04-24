import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlgorithmSkyscrapper {

    public static List<Constraint> constraints = new ArrayList<>();


    public static List<Solution> skyscrapperBacktracking(HashMap<Integer, List<Integer>> board) {
        
            go(board, 1, 1);
        

        Board.showBoard(GAME_NAME.SKYSCRAPPER, board);
        return null;

    }

    private static void go(HashMap<Integer,List<Integer>> board, int row, int col) throws IllegalStateException {

        if (AlgorithmFutoshiki.isBoardFilled(board)) {
            if(checkAllConstraints(board)){

                return;
            } else throw new IllegalStateException();
            //Board.showBoard(GAME_NAME.FUTOSHIKI);
        }
        for (int j = 1; j < board.size() + 1; j++) {
                if (correctAssignment(row, col, j, board) && !AlgorithmFutoshiki.isBoardFilled(board)) {
                    board.get(row).set(col, j);

                    int[] newField = AlgorithmFutoshiki.selectNextField(row, col, board);
                    if (newField[0] != -1)
                        go(board, newField[0], newField[1]);
                    /*if (AlgorithmFutoshiki.isBoardFilled(board)) {
                        //              Board.showBoard(GAME_NAME.FUTOSHIKI);

                        return;
                    }*/
                    board.get(row).set(col, 0);

                }else if(!correctAssignment(row,col,j,board)) {
                    board.get(row).set(col, 0);
                }
                else if(AlgorithmFutoshiki.isBoardFilled(board) && checkAllConstraints(board)){
                    return;
                }
        }



    }

    private static boolean correctAssignment(int row, int column, int value, HashMap<Integer, List<Integer>> currentBoard) {
        return checkConstraints(row, column, currentBoard) && AlgorithmFutoshiki.checkUniqueRowAndColumnVal(row, column, value, currentBoard);
    }

    private static boolean checkConstraints(int row, int column, HashMap<Integer,List<Integer>> currentBoard) {

        List<List<Integer>> constr = Board.getSkyScrapperConstraints();
return        singleConstraint(constr.get(0), CONSTRAINTS.TOP, row, column,  currentBoard) &&         singleConstraint(constr.get(1), CONSTRAINTS.BOTTOM, row, column, currentBoard) &&
                singleConstraint(constr.get(2), CONSTRAINTS.LEFT, row, column,  currentBoard) &&         singleConstraint(constr.get(3), CONSTRAINTS.RIGHT, row, column, currentBoard);


    }

    private static boolean singleConstraint(List<Integer> list, CONSTRAINTS constr, int row, int column, HashMap<Integer,List<Integer>> currentBoard) {


        int numOfVisibles = 0;

        if (constr.equals(CONSTRAINTS.LEFT)) {

            int max = currentBoard.get(row).get(1);
            for (int i = 2; i < list.size() + 1; i++) {
                if (max < currentBoard.get(row).get(i) && currentBoard.get(row).get(i) != 0)
                {
                    max = currentBoard.get(row).get(i);
                    numOfVisibles++;
                }
            }
            if (numOfVisibles > list.get(row - 1) && list.get(row - 1) != 0) {
                return false;
            }
        }

        if (constr.equals(CONSTRAINTS.RIGHT)) {
            int max = currentBoard.get(row).get(currentBoard.size());
            for (int i = list.size()-1; i > 0; i--) {
                if (max < currentBoard.get(row).get(i) && currentBoard.get(row).get(i) != 0)
                {
                    max = currentBoard.get(row).get(i);
                    numOfVisibles++;
                }
            }

            if (numOfVisibles > list.get(row - 1) && list.get(row - 1) != 0) {
                return false;
            }
        }



        if (constr.equals(CONSTRAINTS.TOP)) {
            int max = currentBoard.get(1).get(column);
            for (int i = 1; i < list.size() + 1; i++) {
                if (max < currentBoard.get(i).get(column) && currentBoard.get(i).get(column) != 0)
                {
                    max = currentBoard.get(i).get(column);
                    numOfVisibles++;
                }
            }

            if (numOfVisibles > list.get(column - 1) && list.get(column - 1) != 0) {
                return false;
            }
        }



        if (constr.equals(CONSTRAINTS.BOTTOM)) {
            int max = currentBoard.get(list.size()).get(column);
            for (int i = list.size()-1; i > 0; i--) {
                if (max < currentBoard.get(i).get(column) && currentBoard.get(i).get(column) != 0)
                {
                    max = currentBoard.get(i).get(column);
                    numOfVisibles++;
                }
            }

            if (numOfVisibles > list.get(column - 1) && list.get(column - 1) != 0) {
                return false;
            }
        }
        return true;
    }
    private static boolean checkAllConstraints( HashMap<Integer,List<Integer>> currentBoard) {

        if(AlgorithmFutoshiki.isBoardFilled(currentBoard)){

            for (int i = 1; i < currentBoard.size()+1; i++) {
                for (int j = 1; j < currentBoard.size()+1; j++) {
                    if(!checkConstraints(i, j, currentBoard)){
                        return false;
                    }

                }
            }
            return true;
        } else
            return false;


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
