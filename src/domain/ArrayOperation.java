package domain;

public class ArrayOperation {

    private int mainIndex;
    private int secondaryIndex;

    protected ArrayOperation(int mainIndex, int secondaryIndex) {
        this.mainIndex = mainIndex;
        this.secondaryIndex = secondaryIndex;
    }

    public int getMainIndex() {
        return mainIndex;
    }

    public int getSecondaryIndex() {
        return secondaryIndex;
    }
}
