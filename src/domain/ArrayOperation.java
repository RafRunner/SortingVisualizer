package domain;

public class ArrayOperation {

    private final int index;
    private final int secondaryValue;

    protected ArrayOperation(final int index, final int secondaryValue) {
        this.index = index;
        this.secondaryValue = secondaryValue;
    }

    public int getIndex() {
        return index;
    }

    public int getSecondaryValue() {
        return secondaryValue;
    }
}
