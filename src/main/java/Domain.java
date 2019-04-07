import java.util.List;

public class Domain {

    private List<Integer> fieldDomain;

    public Domain(int dim) {
        fillArray(dim);
    }

    private void fillArray(int dim){
        for (int i = 1; i < dim+1; i++) {
            fieldDomain.add(i);
        }
    }



    public List<Integer> getFieldDomain() {
        return fieldDomain;
    }

    public void setFieldDomain(List<Integer> fieldDomain) {
        this.fieldDomain = fieldDomain;
    }
}
