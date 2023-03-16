package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class CsvFileReader extends BufferedReader {

    public CsvFileReader(FileReader fileReader) {
        super(fileReader);
    }

    public CsvFileReader(InputStreamReader fileReader) {
        super(fileReader);
    }
}
