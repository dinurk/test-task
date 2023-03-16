package ternarysearchtree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pair.Pair;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TernarySearchTreeTest {

    @DisplayName("autocomplete-test")
    @Test
    public void autocompleteTest() {
        TernarySearchTree tree = new SimpleTernarySearchTree();
        tree.insert("abce2", 5);
        tree.insert("abcde1", 1);
        tree.insert("abcde3", 3);
        tree.insert("abce3", 6);
        tree.insert("abcde2", 2);
        tree.insert("abce1", 4);

        assertEquals(List.of("abcde1", "abcde2", "abcde3", "abce1", "abce2","abce3"),
                tree.autocomplete("a").stream().map(Pair::second).toList());

        assertEquals(List.of("abcde1", "abcde2", "abcde3", "abce1", "abce2","abce3"),
                tree.autocomplete("ab").stream().map(Pair::second).toList());

        assertEquals(List.of("abcde1", "abcde2", "abcde3", "abce1", "abce2","abce3"),
                tree.autocomplete("abc").stream().map(Pair::second).toList());

        assertEquals(List.of("abcde1", "abcde2", "abcde3"),
                tree.autocomplete("abcd").stream().map(Pair::second).toList());

        assertEquals(List.of("abcde1", "abcde2", "abcde3"),
                tree.autocomplete("abcde").stream().map(Pair::second).toList());

        assertEquals(List.of("abcde1"),
                tree.autocomplete("abcde1").stream().map(Pair::second).toList());

        assertEquals(List.of("abcde2"),
                tree.autocomplete("abcde2").stream().map(Pair::second).toList());

        assertEquals(List.of("abcde3"),
                tree.autocomplete("abcde3").stream().map(Pair::second).toList());

        assertEquals(List.of("abce1", "abce2", "abce3"),
                tree.autocomplete("abce").stream().map(Pair::second).toList());

        assertEquals(List.of("abce1"),
                tree.autocomplete("abce1").stream().map(Pair::second).toList());

        assertEquals(List.of("abce2"),
                tree.autocomplete("abce2").stream().map(Pair::second).toList());

        assertEquals(List.of("abce3"),
                tree.autocomplete("abce3").stream().map(Pair::second).toList());

        assertEquals(List.of(),
                tree.autocomplete("abce12").stream().map(Pair::second).toList());

        assertEquals(List.of(),
                tree.autocomplete("abcde12").stream().map(Pair::second).toList());
    }
}
