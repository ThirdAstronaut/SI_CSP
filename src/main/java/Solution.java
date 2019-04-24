import java.util.HashMap;
import java.util.List;

public class Solution {
    private String problemName;
    private int numOfmoves;
    private List< HashMap<Integer, List<Integer>>> assignedList;

    public Solution(int numOfmoves, List<HashMap<Integer, List<Integer>>> assignedList) {
        this.numOfmoves = numOfmoves;
        this.assignedList = assignedList;
    }

    public int getNumOfmoves() {
        return numOfmoves;
    }

    public void setNumOfmoves(int numOfmoves) {
        this.numOfmoves = numOfmoves;
    }

    public List<HashMap<Integer, List<Integer>>> getAssignedList() {
        return assignedList;
    }

    public void setAssignedList(List<HashMap<Integer, List<Integer>>> assignedList) {
        this.assignedList = assignedList;
    }

    public void addSoulution(HashMap<Integer, List<Integer>> solution){
        assignedList.add(solution);
    }
}
