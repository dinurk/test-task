package converter;

public class CsvRowSplitter {

    public static String[] split(String csvString) {
        return csvString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }
}
