package converter;

import reader.CsvFileReader;
import ternarysearchtree.SimpleTernarySearchTree;

import java.io.IOException;

public class CsvToTernaryTreeArrayConverter {

    public static SimpleTernarySearchTree[] convert(CsvFileReader csvFileReader) {
        if (csvFileReader == null) {
            throw new IllegalArgumentException("CsvToTrieConverter::convert: csvFileReader can't be null!");
        }

        try {

            String row = csvFileReader.readLine();

            if (row == null || row.isEmpty()) {
                return null;
            }

            SimpleTernarySearchTree[] trees = new SimpleTernarySearchTree[row.split(",").length];
            for (int i = 0; i < trees.length; i++) {
                trees[i] = new SimpleTernarySearchTree();
            }

            while (row != null) {

                String[] values = CsvRowSplitter.split(row);

                for (int i = 0; i < values.length; i++) {
                    try {
                        trees[i].insert(values[i].replace("\"", ""), Integer.parseInt(values[0]));
                    } catch (Exception e) {
                        System.out.println("CsvToTrieArrayConverter::convert(): error occured when parsing value to int: " + values[0]);
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }

                row = csvFileReader.readLine();
            }

            return trees;

        } catch (IOException e) {
            return null;
        }
    }
}
