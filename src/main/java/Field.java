import java.util.ArrayList;
import java.util.List;

public class Field {

    private int id;
    private int value;
    private int xCord;
    private int yCord;
    private List<Integer> fieldDomain;

    Field(int value, int xCord, int yCord) {
        this.value = value;
        this.xCord = xCord;
        this.yCord = yCord;
        this.fieldDomain = new ArrayList<>();
        this.id = xCord * 10 + yCord;
    }


    private void fillArray(int dim) {
        for (int i = 1; i < dim + 1; i++) {
            fieldDomain.add(i);
        }
    }

    public boolean deleteDomainValue(Integer value) {
        return fieldDomain.remove(value);
    }

    public void deleteDomainAtIndex(int id) {
        fieldDomain.remove(id);
    }

    public List<Integer> getFieldDomain() {
        return fieldDomain;
    }

    public void setFieldDomain(List<Integer> fieldDomain) {
        this.fieldDomain = fieldDomain;
    }

}
