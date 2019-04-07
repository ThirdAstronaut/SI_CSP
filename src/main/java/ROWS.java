public enum ROWS {
   ROWS(0), A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10);

    private final int rowName;

    ROWS(final int value) {
        rowName = value;
    }

    public int getRowName() {
        return rowName;
    }
}