package ternarysearchtree;

import pair.Pair;

import java.util.List;

public interface TernarySearchTree {

    void insert(String string, int rowNumber);
    List<Pair<List<Integer>, String>> autocomplete(String query);
}
