import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILE_NAME_BASE = "D:\\STUDIA\\Informatyka - wiz SEMESTR 6\\si\\2018 board\\lab2\\testowe\\";


    private static final String FILE_NAME_SUFFIX = ".txt";
    private static final String FUTUSHIKI_FILE_PREFIX = "futoshiki_";
    private static final String SKYSCRAPPER_FILE_PREFIX = "skyscrapper_";
    private static List<String> filesNames;
    private static int boardSize;
    private static List<String> tmpConstraints;

    public static void main(String... args) {
        fillFilesNames(4, 6, 5);
/** RESEARCH */ //       fillFilesNames(4, 10, 3);
/* all tests */        /*for (String futoshikiFilesName : filesNames) {
            Board.fillBoard(readDataFutoshiki(FILE_NAME_BASE + futoshikiFilesName + FILE_NAME_SUFFIX));
        }
*/
        Board.fillBoard(readDataFutoshiki(FILE_NAME_BASE + FUTUSHIKI_FILE_PREFIX+ filesNames.get(0) + FILE_NAME_SUFFIX), GAME_NAME.FUTOSHIKI);
        Board.showBoard(GAME_NAME.FUTOSHIKI);
        System.out.println();
        Board.fillBoard(readDataSkyscrapper(FILE_NAME_BASE + SKYSCRAPPER_FILE_PREFIX+ filesNames.get(0) + FILE_NAME_SUFFIX), GAME_NAME.SKYSCRAPPER);
        Board.showBoard(GAME_NAME.SKYSCRAPPER);

        Algorithm.skyscrapperBacktracking(Board.getBoard());


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
                for (String s : tmp) {
                    columns.get(counter).add(Integer.valueOf(s));

                }
                counter++;
            }

            tmpConstraints = new ArrayList<>();
            while ((str = in.readLine()) != null) {
                tmpConstraints.add(str);
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


    private static void generateCsv() {
        File file = new File(FILE_NAME_BASE + "-p.csv");

        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

//            writer.writeAll(data);

            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
