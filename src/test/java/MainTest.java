import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainTest {

    @Test
    public  void checkAllConstraintsBoardTest(){
        Board.fillBoard(Main.readDataSkyscrapper(Main.FILE_NAME_BASE_RESEARCH + Main.SKYSCRAPPER_FILE_PREFIX_RESEARCH + "4_0" + Main.FILE_NAME_SUFFIX), GAME_NAME.SKYSCRAPPER);
       int[] tmp = new int[]{2,1,3,4,3,2,4,1,1,4,2,3,4,3,1,2};
        List<List<Integer>> list = new ArrayList<>();
            list.add(new ArrayList());
            list.add(new ArrayList());
            list.add(new ArrayList());
            list.add(new ArrayList());

            for (int i = 0; i < list.size(); i++){
                for (int j = 0; j < 5; j++) {
                    list.get(i).add(tmp[j*i % 4]);
                }
            }



        HashMap<Integer, List<Integer>> board = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            board.put(i, new ArrayList<Integer>());
            for (int j = 0; j < list.get(i-1).size(); j++) {
                board.get(i).add(list.get(i-1).get(j));
            }
        }
        System.out.println(board.get(1).size());
Assert.assertEquals(true, AlgorithmSkyscrapper.correctAssignment(3, 3, board.get(3).get(3), board));
  //      Assert.assertEquals(true, AlgorithmSkyscrapper.checkAllConstraints(board));
    }


}
