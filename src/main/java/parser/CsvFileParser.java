package parser;

import converter.CsvToTernaryTreeArrayConverter;
import pair.Pair;
import reader.CsvFileReader;
import ternarysearchtree.TernarySearchTree;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

public class CsvFileParser {

    private String filePath;
    private TernarySearchTree[] trees;

    public CsvFileParser(String filePath) {
        if(filePath == null) {
            throw new IllegalArgumentException("Autocompleter::constructor(): filePath can't be null!");
        }

        this.filePath = filePath;
    }

    public List<Pair<List<Integer>, String>> autocompleteByColumn(int columnNumber, String query) {
        return trees[columnNumber].autocomplete(query);
    }

    public boolean parseFile() {
        try(CsvFileReader reader = new CsvFileReader(new InputStreamReader(getClass().getResourceAsStream(filePath)))) {
            trees = CsvToTernaryTreeArrayConverter.convert(reader);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
