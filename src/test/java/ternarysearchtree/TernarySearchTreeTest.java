package ternarysearchtree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TernarySearchTreeTest {

    @DisplayName("autocomplete-test")
    @Test
    public void autocompleteTest() {
        TernarySearchTree tree = new SimpleTernarySearchTree();
        tree.insert("abcde", 1);
        tree.insert("abcdf1", 2);
        tree.insert("abcdg", 3);

        System.out.println(tree.autocomplete("abc"));
    }
}
