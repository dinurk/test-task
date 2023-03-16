package ternarysearchtree;

import java.util.ArrayList;
import java.util.List;

class SimpleTernarySearchTreeNode implements TernarySearchTreeNode {

    private char character;
    private boolean isCompleteString;

    private List<Integer> rowNumbers;

    private int rowNumber;

    private TernarySearchTreeNode left;
    private TernarySearchTreeNode right;
    private TernarySearchTreeNode equal;

    SimpleTernarySearchTreeNode(char character) {
        this.character = character;
        rowNumbers = new ArrayList<>();
        rowNumber = -1;
    }

    @Override
    public TernarySearchTreeNode getLeft() {
        return left;
    }

    @Override
    public TernarySearchTreeNode getRight() {
        return right;
    }

    @Override
    public TernarySearchTreeNode getEqual() {
        return equal;
    }

    @Override
    public void setAsLeft(TernarySearchTreeNode nodeToSet) {
        left = nodeToSet;
    }

    @Override
    public void setAsRight(TernarySearchTreeNode nodeToSet) {
        right = nodeToSet;
    }

    @Override
    public void setAsEqual(TernarySearchTreeNode nodeToSet) {
        equal = nodeToSet;
    }

    @Override
    public char getCharacter() {
        return character;
    }

    @Override
    public List<Integer> getRowNumbers() {
        if(rowNumber == -1) {
            return null;
        }

        if(rowNumber == -2 && rowNumbers != null) {
            return new ArrayList<>(rowNumbers);
        }

        return List.of(rowNumber);
    }

    @Override
    public void addRowNumber(int rowNumber) {

        if(!isCompleteString) {
            isCompleteString = true;
        }

        if(this.rowNumber == -1) {
            this.rowNumber = rowNumber;
            return;
        }

        if(this.rowNumber == -2 && rowNumbers != null) {
            rowNumbers.add(rowNumber);
            return;
        }

        rowNumbers = new ArrayList<>();
        rowNumbers.add(this.rowNumber);
        rowNumbers.add(rowNumber);
        this.rowNumber = -2;
    }

    @Override
    public boolean isCompleteString() {
        return isCompleteString;
    }
}
