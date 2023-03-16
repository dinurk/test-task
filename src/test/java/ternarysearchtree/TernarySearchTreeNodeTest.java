package ternarysearchtree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TernarySearchTreeNodeTest {

    @DisplayName("initialization-test")
    @Test
    public void initializationTest() {
        TernarySearchTreeNode node = new SimpleTernarySearchTreeNode('a');

        assertEquals('a', node.getCharacter());
        assertNull(node.getLeft());
        assertNull(node.getRight());
        assertNull(node.getEqual());
        assertNull(node.getRowNumbers());
        assertFalse(node.isCompleteString());
    }

    @DisplayName("row-number-adding-test")
    @Test
    public void rowNumberAddingTest() {
        TernarySearchTreeNode node = new SimpleTernarySearchTreeNode('a');

        assertNull(node.getRowNumbers());

        node.addRowNumber(1);
        assertEquals(List.of(1), node.getRowNumbers());

        node.addRowNumber(2);
        assertEquals(List.of(1, 2), node.getRowNumbers());

        node.addRowNumber(3);
        assertEquals(List.of(1, 2, 3), node.getRowNumbers());

        node.addRowNumber(4);
        assertEquals(List.of(1, 2, 3, 4), node.getRowNumbers());
    }

    @DisplayName("setting-left-subtree-test")
    @Test
    public void settingLeftSubtreeTest() {
        TernarySearchTreeNode originalNode = new SimpleTernarySearchTreeNode('b');
        assertNull(originalNode.getLeft());

//        assertThrows(IllegalArgumentException.class, () -> originalNode.setAsLeft(new SimpleTernarySearchTreeNode('b')));
//        assertThrows(IllegalArgumentException.class, () -> originalNode.setAsLeft(new SimpleTernarySearchTreeNode('c')));

        TernarySearchTreeNode correctNode = new SimpleTernarySearchTreeNode('a');
        originalNode.setAsLeft(correctNode);
        assertEquals(correctNode, originalNode.getLeft());
    }

    @DisplayName("setting-right-subtree-test")
    @Test
    public void settingRightSubtreeTest() {
        TernarySearchTreeNode originalNode = new SimpleTernarySearchTreeNode('b');
        assertNull(originalNode.getRight());

//        assertThrows(IllegalArgumentException.class, () -> originalNode.setAsRight(new SimpleTernarySearchTreeNode('b')));
//        assertThrows(IllegalArgumentException.class, () -> originalNode.setAsRight(new SimpleTernarySearchTreeNode('a')));

        TernarySearchTreeNode correctNode = new SimpleTernarySearchTreeNode('c');
        originalNode.setAsRight(correctNode);
        assertEquals(correctNode, originalNode.getRight());
    }

    @DisplayName("setting-equal-subtree-test")
    @Test
    public void settingEqualSubtreeTest() {
        TernarySearchTreeNode originalNode = new SimpleTernarySearchTreeNode('b');
        assertNull(originalNode.getEqual());

//        assertThrows(IllegalArgumentException.class, () -> originalNode.setAsEqual(new SimpleTernarySearchTreeNode('a')));
//        assertThrows(IllegalArgumentException.class, () -> originalNode.setAsEqual(new SimpleTernarySearchTreeNode('c')));

        TernarySearchTreeNode correctNode = new SimpleTernarySearchTreeNode('b');
        originalNode.setAsEqual(correctNode);
        assertEquals(correctNode, originalNode.getEqual());
    }

    @DisplayName("is-complete-string-test")
    @Test
    public void isCompleteStringTest() {
        TernarySearchTreeNode node = new SimpleTernarySearchTreeNode('a');

        assertFalse(node.isCompleteString());

        node.addRowNumber(1);
        assertTrue(node.isCompleteString());
    }
}
