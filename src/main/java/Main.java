import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static final String FILE_NAME_BASE_TEST = "D:\\STUDIA\\Informatyka - wiz SEMESTR 6\\si\\2018 board\\lab2\\testowe\\";
    private static final String FILE_NAME_BASE_RESEARCH = "D:\\STUDIA\\Informatyka - wiz SEMESTR 6\\si\\2018 board\\lab2\\badawcze\\";


    private static final String FILE_NAME_SUFFIX = ".txt";
    private static final String FUTUSHIKI_FILE_PREFIX_TEST = "futoshiki_";
    private static final String FUTUSHIKI_FILE_PREFIX_RESEARCH = "test_futo_";
    private static final String SKYSCRAPPER_FILE_PREFIX_TEST = "skyscrapper_";
    private static List<String> filesNames;
    private static int boardSize;
    static List<String[]> data = new ArrayList<>();


    public static void main(String... args) {
        fillFilesNames(4, 6, 5);
/** RESEARCH */ //       fillFilesNames(4, 10, 3);
        /* all tests */        /*for (String futoshikiFilesName : filesNames) {
            Board.fillBoard(readDataFutoshiki(FILE_NAME_BASE_TEST + futoshikiFilesName + FILE_NAME_SUFFIX));
        }
*/
         Board.fillBoard(readDataFutoshiki(FILE_NAME_BASE_RESEARCH + FUTUSHIKI_FILE_PREFIX_RESEARCH + filesNames.get(0) + FILE_NAME_SUFFIX), GAME_NAME.FUTOSHIKI);
//       System.out.println(FILE_NAME_BASE_TEST + FUTUSHIKI_FILE_PREFIX_RESEARCH + filesNames.get(0) + FILE_NAME_SUFFIX);
        // Board.showBoard(GAME_NAME.FUTOSHIKI, Board.getBoard());
        System.out.println(FILE_NAME_BASE_RESEARCH + FUTUSHIKI_FILE_PREFIX_RESEARCH + filesNames.get(0) + FILE_NAME_SUFFIX);
//        Board.fillBoard(readDataSkyscrapper(FILE_NAME_BASE_TEST + SKYSCRAPPER_FILE_PREFIX_TEST + filesNames.get(0) + FILE_NAME_SUFFIX), GAME_NAME.SKYSCRAPPER);
        //Board.showBoard(GAME_NAME.SKYSCRAPPER);
        //Algorithm.skyscrapperBacktracking(Board.getBoard());
        HashMap<Integer, List<Integer>> board = new HashMap<>();
        for (Integer i : Board.getBoard().keySet()) {
            board.put(i, new ArrayList<>(Board.getBoard().get(i)));
        }

    AlgorithmFutoshiki.futoshikiBacktracking(board);
//        AlgorithmSkyscrapper.skyscrapperBacktracking(board);

    }

    private static void fillFilesNames(int min, int max, int c) {
        filesNames = new ArrayList<>();
        for (int j = min; j < max; j++) {
            for (int i = 0; i < c; i++) {
                filesNames.add(j + "_" + i);
            }
        }
    }


    private static List<List<Integer>> readDataFutoshiki(String fileName) {
        List<List<Integer>> columns = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            boardSize = Integer.parseInt(in.readLine());
            in.readLine(); //START

//            System.out.println(boardSize);

            columns = new ArrayList<>();
            for (int i = 0; i < boardSize; i++) {
                columns.add(new ArrayList<>());
            }


            int counter = 0;
            while ((str = in.readLine()) != null && counter < boardSize) {
                String[] tmp = str.split(";");
                columns.get(counter).add(-1);
                for (String s : tmp) {
                    columns.get(counter).add(Integer.valueOf(s));

                }
                counter++;
            }

            while ((str = in.readLine()) != null) {
                String[] tmp = str.split(";");
                ROWS rowNameLess = ROWS.valueOf(String.valueOf(tmp[0].charAt(0)));
                ROWS rowNameGreater = ROWS.valueOf(String.valueOf(tmp[1].charAt(0)));


                String a = rowNameGreater.getRowName() + "";
                int rowNumLess = rowNameLess.getRowName();
                int rowNumGreater = rowNameGreater.getRowName();
                int colNumLess = Integer.valueOf(tmp[0].substring(1, 2));
                int colNumGreater = Integer.valueOf(tmp[1].substring(1, 2));

                Constraint constraint = new Constraint();
                constraint.constr.put(1, new HashMap<>());
                constraint.constr.get(1).put(rowNumLess, colNumLess);
                constraint.constr.put(2, new HashMap<>());
                constraint.constr.get(2).put(rowNumGreater, colNumGreater);
                System.out.println(rowNumLess + "   " + colNumLess);
                System.out.println(rowNumGreater + "   " + colNumGreater);
                System.out.println();


                AlgorithmFutoshiki.constraints.add(constraint);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columns;
    }


    private static List<List<Integer>> readDataSkyscrapper(String fileName) {
        List<List<Integer>> constraints = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            boardSize = Integer.parseInt(in.readLine());
            constraints = new ArrayList<>();
            for (int i = 0; i < boardSize; i++) {
                constraints.add(new ArrayList<>());
            }
            int counter = 0;
            while ((str = in.readLine()) != null && counter < boardSize) {
                String[] tmp = str.split(";");
                for (int i = 1; i < tmp.length; i++) {
                    constraints.get(counter).add(Integer.valueOf(tmp[i]));
                }
                counter++;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return constraints;
    }

    private static void saveData(int fileName, int numOfSolutions, double backTime, long backIter, double forTime, long forIter) {
        //TODO KOMENT DLA PYTHONA
        // data.add(new String[]{String.valueOf(i).replace(".", ","), String.valueOf(singelBest).replace(".", ","), String.valueOf(avgDistance).replace(".", ","), String.valueOf(longestDistance).replace(".", ",")});
        // data.add(new String[]{String.valueOf(i), String.valueOf(singelBest), String.valueOf(avgDistance), String.valueOf(longestDistance)});
    }


    private static void generateCsv() {
        File file = new File(FILE_NAME_BASE_TEST + "-p.csv");

        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            writer.writeAll(data);

            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
