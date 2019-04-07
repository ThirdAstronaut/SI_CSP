import java.util.List;

public class Solution {

    private int numOfmoves;
    private List<Assigned> assignedList;

    public Solution(int numOfmoves, List<Assigned> assignedList) {
        this.numOfmoves = numOfmoves;
        this.assignedList = assignedList;
    }

    public int getNumOfmoves() {
        return numOfmoves;
    }

    public void setNumOfmoves(int numOfmoves) {
        this.numOfmoves = numOfmoves;
    }

    public List<Assigned> getAssignedList() {
        return assignedList;
    }

    public void setAssignedList(List<Assigned> assignedList) {
        this.assignedList = assignedList;
    }
}
