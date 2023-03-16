package ternarysearchtree;

import pair.Pair;
import pair.SimplePair;

import java.util.LinkedList;
import java.util.List;

public class SimpleTernarySearchTree implements TernarySearchTree {
    
    TernarySearchTreeNode root;
    
    public SimpleTernarySearchTree() {
        root = null;
    }
    
    @Override
    public void insert(String string, int rowNumber) {
        if(string == null || string.isEmpty()) {
            return;
        }

        string = string.toLowerCase();

        if(root == null) {
            root = new SimpleTernarySearchTreeNode(string.charAt(0));
            if(string.length() == 1) {
                return;
            }
        }

        root = insertRecursive(root, string, 0, rowNumber);
    }

    private TernarySearchTreeNode insertRecursive(TernarySearchTreeNode currentNode,
                                                  String stringToInsert,
                                                  int characterIndex,
                                                  int rowNumber) {

        if(currentNode == null) {
            currentNode = new SimpleTernarySearchTreeNode(stringToInsert.charAt(characterIndex));
        }

        if(stringToInsert.charAt(characterIndex) < currentNode.getCharacter()) {
            currentNode.setAsLeft(insertRecursive(currentNode.getLeft(), stringToInsert, characterIndex, rowNumber));
        }
        else if(stringToInsert.charAt(characterIndex) > currentNode.getCharacter()) {
            currentNode.setAsRight(insertRecursive(currentNode.getRight(), stringToInsert, characterIndex, rowNumber));
        } else {
            if(characterIndex + 1 == stringToInsert.length()) {
                currentNode.addRowNumber(rowNumber);
            } else {
                currentNode.setAsEqual(insertRecursive(currentNode.getEqual(), stringToInsert, characterIndex + 1, rowNumber));
            }
        }
        return currentNode;
    }

    private void traverseRecursive(TernarySearchTreeNode currentNode,
                                   List<Pair<List<Integer>, String>> result,
                                   StringBuffer stringBuffer,
                                   int depth) {

        if(currentNode == null) {
            return;
        }

        traverseRecursive(currentNode.getLeft(), result, stringBuffer, depth);

        stringBuffer.replace(depth, depth + 1, String.valueOf(currentNode.getCharacter()));

        if(currentNode.isCompleteString()) {
            stringBuffer.setLength(depth + 1);
            result.add(new SimplePair<>(currentNode.getRowNumbers(), stringBuffer.toString()));
        }

        traverseRecursive(currentNode.getEqual(), result, stringBuffer, depth + 1);
        traverseRecursive(currentNode.getRight(), result, stringBuffer, depth);
    }

    public List<Pair<List<Integer>, String>> autocomplete(String query) {
        List<Pair<List<Integer>, String>> result = new LinkedList<>();
        int characterIndex = 0;

        if(query == null || query.isEmpty()) {
            return result;
        }

        query = query.toLowerCase();

        TernarySearchTreeNode currentNode = root;
        TernarySearchTreeNode previousNode = null;

        while(currentNode != null && characterIndex < query.length()) {

            if(query.charAt(characterIndex) < currentNode.getCharacter()) {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            } else if(query.charAt(characterIndex) > currentNode.getCharacter()) {
                previousNode = currentNode;
                currentNode = currentNode.getRight();
            } else if(query.charAt(characterIndex) == currentNode.getCharacter()) {
                previousNode = currentNode;
                currentNode = currentNode.getEqual();
                characterIndex +=1;
            } else {
                return result;
            }
        }

        StringBuffer stringBuffer = new StringBuffer(1000);

        stringBuffer.append(query);

        traverseRecursive(currentNode, result, stringBuffer, query.length());

//        if(currentNode==null) {
//            if(previousNode.isCompleteString()) {
//                result.add(new SimplePair<>(previousNode.getRowNumbers(), stringBuffer.toString()));
//            }
//        } else
        if(currentNode == null) {
            return result;
        }
        if(currentNode.isCompleteString()) {
            result.add(new SimplePair<>(currentNode.getRowNumbers(), stringBuffer.toString()));
        }

        return result;
    }
}
