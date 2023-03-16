package ternarysearchtree;

import java.util.List;

interface TernarySearchTreeNode {
    TernarySearchTreeNode getLeft();
    TernarySearchTreeNode getRight();
    TernarySearchTreeNode getEqual();

    void setAsLeft(TernarySearchTreeNode nodeToSet);
    void setAsRight(TernarySearchTreeNode nodeToSet);
    void setAsEqual(TernarySearchTreeNode nodeToSet);

    char getCharacter();

    List<Integer> getRowNumbers();

    void addRowNumber(int rowNumber);

    boolean isCompleteString();
}
